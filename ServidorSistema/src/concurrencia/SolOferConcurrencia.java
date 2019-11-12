package concurrencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import entidadesTransversales.Oferta;
import estadoSistema.SingletonCitas;
import estadoSistema.SingletonOfertas;
import entidadesTransversales.Candidato;
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
	
	private Oferta ofer;
	
	HandlerSolOfer(Oferta c){
		this.ofer = c;
	}
	
	@Override
	public NotiOferta call() throws Exception {
		
		// TODO Manejar transaccion de escritura en la lista
		
		
		while(true) {
			
			Date myTS = new Date();
			ArrayList<Oferta> mList = new ArrayList<>();
			
			if(myTS.compareTo(SingletonOfertas.getInstance().getLastWriteTS() ) <= 0 ) {
				//reject read request and abort corresponding transaction
				continue;
			} else {
				Date maxi = SingletonOfertas.getInstance().getLastReadTS().compareTo(myTS) > 0 ?
						SingletonOfertas.getInstance().getLastReadTS() :
							myTS;
				SingletonOfertas.getInstance().setLastReadTS(maxi);
				mList = SingletonOfertas.getInstance().getOfertas();
			}
			
			
			Date myTSW = new Date();
			if(myTSW.compareTo(SingletonOfertas.getInstance().getLastReadTS()) < 0 ||
					myTSW.compareTo(SingletonOfertas.getInstance().getLastWriteTS()) <= 0) {
				//reject write request
				continue;
			} else {
				SingletonOfertas.getInstance().setLastWriteTS(myTSW);
				mList.add(ofer);
				//SingletonOfertas.getInstance().setOfertas(mList);
				
				break;
			}
		}
		
		// TODO Auto-generated method stub
		return new NotiOferta(new ArrayList<Candidato>());
	}
}
