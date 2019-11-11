package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import entidadesTransversales.Candidato;
import entidadesTransversales.NotiCandidato;

public interface ISolicitudEmpleo extends Remote  {
	NotiCandidato subscribirEmpleo(Candidato cand) throws RemoteException;
	
}
