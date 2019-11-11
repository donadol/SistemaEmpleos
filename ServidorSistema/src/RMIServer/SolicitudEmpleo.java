package RMIServer;

import java.rmi.RemoteException;

import concurrencia.SolEmpConcurrencia;
import entidadesTransversales.Candidato;
import entidadesTransversales.NotiCandidato;
import interfaces.ISolicitudEmpleo;

public class SolicitudEmpleo implements ISolicitudEmpleo {
	@Override
	public NotiCandidato subscribirEmpleo(Candidato cand) throws RemoteException {	
		//System.out.println(cand.toString());
		System.out.println(cand);
		
		return SolEmpConcurrencia.runSolEmpleado(cand);
	}
}
