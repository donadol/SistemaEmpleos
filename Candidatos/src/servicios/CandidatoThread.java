package servicios;

import GUI.FrameCandidato;
import entidadesTransversales.Candidato;
import entidadesTransversales.NotiCandidato;
import interfaces.ISolicitudEmpleo;

public class CandidatoThread implements Runnable {
	private Candidato candidato;
	private FrameCandidato frameCandidato;
	private ISolicitudEmpleo interfaz;

	public CandidatoThread(Candidato candidato, ISolicitudEmpleo interfaz) {
		super();
		this.candidato = candidato;
		this.interfaz = interfaz;
		try {
			frameCandidato = new FrameCandidato(candidato);
			frameCandidato.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try{
			NotiCandidato response  = interfaz.subscribirEmpleo(candidato);
			frameCandidato.actualizarTabla(response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
