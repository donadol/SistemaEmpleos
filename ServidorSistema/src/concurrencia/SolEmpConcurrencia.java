package concurrencia;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import entidadesTransversales.Candidato;
import entidadesTransversales.NotiCandidato;

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
		
		// TODO Meter en la lista de candidatos inscritos


		
		this.cand.toString();
		
		// TODO Auto-generated method stub
		return new NotiCandidato("endava", 321, "asistonto", 3000);
	}
}
