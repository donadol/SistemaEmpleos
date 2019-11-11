package servicios;

import java.rmi.RemoteException;

import GUI.FrameEmpresa;
import entidadesTransversales.Candidato;
import entidadesTransversales.NotiOferta;
import entidadesTransversales.Oferta;
import interfaces.ISolicitudOferta;

public class OfertaThread implements Runnable{
	private int id;
	private FrameEmpresa frame;
	private ISolicitudOferta interfaz;
	private Oferta oferta;

	public OfertaThread(int id, FrameEmpresa frame, ISolicitudOferta interfaz, Oferta oferta) {
		super();
		this.id = id;
		this.frame = frame;
		this.interfaz = interfaz;
		this.oferta = oferta;
	}

	@Override
	public void run() {
		try {
			NotiOferta response = interfaz.subscribirOferta(oferta);
			for(Candidato c: response.getCandidatos()) {
				frame.actualizarTable(c, id);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
