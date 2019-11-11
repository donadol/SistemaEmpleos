package RMIServer;

import java.rmi.RemoteException;

import concurrencia.SolOferConcurrencia;
import entidadesTransversales.NotiOferta;
import entidadesTransversales.Oferta;
import interfaces.ISolicitudOferta;

public class SolicitudOferta implements ISolicitudOferta {
	
	
	
	@Override
	public NotiOferta subscribirOferta(Oferta ofer) throws RemoteException {
		
		System.out.println("oferta llegada" + ofer.toString());
		
		return SolOferConcurrencia.runSolOferta(ofer);
	}
	
}
