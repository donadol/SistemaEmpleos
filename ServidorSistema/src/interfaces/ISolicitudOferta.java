package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import entidadesTransversales.NotiOferta;
import entidadesTransversales.Oferta;

public interface ISolicitudOferta extends Remote {
	NotiOferta subscribirOferta(Oferta ofer) throws RemoteException;
	NotiOferta leerCandidatos(Oferta ofer) throws RemoteException;
}
