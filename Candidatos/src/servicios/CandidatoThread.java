package servicios;

import GUI.FrameCandidato;
import entidadesTransversales.Candidato;
import entidadesTransversales.NotiCandidato;

public class CandidatoThread implements Runnable {
	private Candidato candidato;
	private FrameCandidato frameCandidato;

	public CandidatoThread(Candidato candidato) {
		super();
		this.candidato = candidato;
		frameCandidato = new FrameCandidato(candidato);
	}

	@Override
	public void run() {
		try {
			frameCandidato.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		frameCandidato.actualizarTabla(new NotiCandidato("Endava", 1, "desarrollo", 1000));
	}
}
