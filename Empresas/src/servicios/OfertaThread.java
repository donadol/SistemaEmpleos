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
			interfaz.subscribirOferta(oferta);
			
			NotiOferta response;
			do {
				 response = interfaz.leerCandidatos(oferta);
				 frame.clean(id);
				 for(Candidato c: response.getCandidatos()) {
						frame.actualizarTable(c, id);
				}
				 
				 try {
					Thread.sleep(1700);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} while(response.getCandidatos().size()!=3);
			
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
