package concurrencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import entidadesTransversales.Candidato;
import entidadesTransversales.NotiCandidato;
import entidadesTransversales.Oferta;
import estadoSistema.SingletonCandidatos;
import estadoSistema.SingletonCitas;

public class SolEmpConcurrencia {
	private static final ExecutorService pool;
	static {
		pool = Executors.newFixedThreadPool(25);
	}
	
	public static NotiCandidato runSolEmpleado(Candidato cand) {
		NotiCandidato resp = null;
		try {
			HandlerSolEmp thread = new HandlerSolEmp(cand);
			resp = thread.call();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resp;
	}
}

class HandlerSolEmp implements Callable<NotiCandidato> {
	
	private final Candidato cand;
	
	HandlerSolEmp(Candidato c){
		this.cand = c;
	}
	
	@Override
	public NotiCandidato call() throws Exception {
		
		// TODO Manejar transaccion de escritura en la lista
		boolean waiting = true;
		
		
		while(waiting == true) {
			Date myTS = new Date();
			ArrayList<Candidato> mList = new ArrayList<>();
			
			if(myTS.compareTo(SingletonCandidatos.getInstance().getLastWriteTS() ) < 0 ) {
				//reject read request and abort corresponding transaction
				continue;
			} else {
				mList = SingletonCandidatos.getInstance().getListaCandidatos();
				Date maxi = SingletonCandidatos.getInstance().getLastReadTS().compareTo(myTS) > 0 ?
						SingletonCandidatos.getInstance().getLastReadTS() :
							myTS;
				SingletonCandidatos.getInstance().setLastReadTS(maxi);
			}
			
			if(myTS.compareTo(SingletonCandidatos.getInstance().getLastReadTS()) < 0 ||
					myTS.compareTo(SingletonCandidatos.getInstance().getLastWriteTS()) < 0) {
				//reject write request
			} else {
				mList.add(this.cand);
				SingletonCandidatos.getInstance().setLastWriteTS(myTS);
				waiting = false;
			}			
		}
		
		NotiCandidato resp = null;
		
		boolean gotEmpleo = false;
		while(gotEmpleo == false) {
			
			Date myTS = new Date();
			
			if(myTS.compareTo(SingletonCitas.getInstance().getLastWriteTS() ) < 0 ) {
				//reject read request and abort corresponding transaction
				continue;
			} else {
				Entry<Candidato, Oferta> mVal = 
						SingletonCitas.getInstance().getListaCitas().get(this.cand.getDocumento());
						
				if( mVal != null) {
					resp = new NotiCandidato(mVal.getValue().getNombreEmpresa(),mVal.getValue().getId(),
							mVal.getValue().getCargo(), mVal.getValue().getSalario());
					gotEmpleo=true;
					break;
				}
				
				Date maxi = SingletonCitas.getInstance().getLastReadTS().compareTo(myTS) > 0 ?
						SingletonCitas.getInstance().getLastReadTS() :
							myTS;
				SingletonCitas.getInstance().setLastReadTS(maxi);
			}
			
			Thread.sleep(1500);
		}
		/*
		for(Candidato c : SingletonCandidatos.getInstance().getListaCandidatos()) {
			System.out.println(c.toString());
		}
		*/
		
		
		// TODO Auto-generated method stub
		return resp;
	}
}
