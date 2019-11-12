package concurrencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import entidadesTransversales.Oferta;
import entidadesTransversales.SectorEmpresa;
import estadoSistema.SingletonCitas;
import estadoSistema.SingletonOfertas;
import entidadesTransversales.Candidato;
import entidadesTransversales.Empleo;
import entidadesTransversales.NivelEstudios;
import entidadesTransversales.NotiCandidato;
import entidadesTransversales.NotiOferta;

public class SolOferConcurrencia {
	private static final ExecutorService pool;
	static {
		pool = Executors.newFixedThreadPool(25);
	}
	
	public static NotiOferta runSolOferta(Oferta ofer) {
		NotiOferta resp = null;
		try {
			HandlerSolOfer thread = new HandlerSolOfer(ofer);
			resp = thread.call();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resp;
	}
	//TODO, CONSULTAR LOS CANDIDATOS HASTA QUE SEA <3
	
	 
	 
	public static NotiOferta runConsulOferta(Oferta ofer) {
		NotiOferta resp = null;
		try {
			HandlerConsultarOfer thread = new HandlerConsultarOfer(ofer);
			resp = thread.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
		return resp;
	}
	
}

class HandlerConsultarOfer implements Callable<NotiOferta> {
	
	private final Oferta ofer;
	
	HandlerConsultarOfer(Oferta c){
		this.ofer = c;
	}

	@Override
	public NotiOferta call() throws Exception {
		NotiOferta resp = null;
		
		boolean gotData = false;
		while(gotData == false) {
			
			Date myTS = new Date();
			
			if(myTS.compareTo(SingletonCitas.getInstance().getLastWriteTS() ) < 0 ) {
				//reject read request and abort corresponding transaction
				continue;
			} else {
				Entry<Oferta, ArrayList<Candidato> > mVal = 
						SingletonCitas.getInstance().getListaCitasOfertas().get(ofer.getId());
						
				if( mVal != null) {
					resp = new NotiOferta(mVal.getValue());
					gotData=true;
					break;
				}
				
				Date maxi = SingletonCitas.getInstance().getLastReadTS().compareTo(myTS) > 0 ?
						SingletonCitas.getInstance().getLastReadTS() :
							myTS;
				SingletonCitas.getInstance().setLastReadTS(maxi);
			}
			
			Thread.sleep(1500);
		}
		
		// TODO Auto-generated method stub
		return resp;
	}
	
}

class HandlerSolOfer implements Callable<NotiOferta> {
	
	private final Oferta ofer;
	
	HandlerSolOfer(Oferta c){
		this.ofer = c;
	}
	
	@Override
	public NotiOferta call() throws Exception {
		
		// TODO Manejar transaccion de escritura en la lista
		boolean waiting = true;
		
		
		while(waiting == true) {
			Date myTS = new Date();
			ArrayList<Oferta> mList = new ArrayList<>();
			
			if(myTS.compareTo(SingletonOfertas.getInstance().getLastWriteTS() ) < 0 ) {
				//reject read request and abort corresponding transaction
				continue;
			} else {
				mList = SingletonOfertas.getInstance().getOfertas();
				Date maxi = SingletonOfertas.getInstance().getLastReadTS().compareTo(myTS) > 0 ?
						SingletonOfertas.getInstance().getLastReadTS() :
							myTS;
				SingletonOfertas.getInstance().setLastReadTS(maxi);
			}
			
			if(myTS.compareTo(SingletonOfertas.getInstance().getLastReadTS()) < 0 ||
					myTS.compareTo(SingletonOfertas.getInstance().getLastWriteTS()) < 0) {
				//reject write request
			} else {
				mList.add(this.ofer);
				SingletonOfertas.getInstance().setLastWriteTS(myTS);
				waiting = false;
			}			
		}
		
		/*
		for(Oferta c : SingletonOfertas.getInstance().getOfertas()) {
			System.out.println(c.toString());
		}
		*/
		
		
		
		// TODO Auto-generated method stub
		
		Empleo p = new Empleo("gerente",5,SectorEmpresa.COMERCIO);
		ArrayList<Empleo> lis = new ArrayList<Empleo>();
		lis.add(p);
		Candidato cand = new Candidato("Pedro","101851091", 3000, NivelEstudios.PROFESIONAL,lis);
		ArrayList<Candidato> cands = new ArrayList<>();
		cands.add(cand);
		return new NotiOferta(cands);
	}
}
