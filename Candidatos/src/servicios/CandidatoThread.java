package servicios;

import GUI.FrameCandidato;
import entidadesTransversales.Candidato;

public class CandidatoThread implements Runnable {
	private Candidato candidato;
	private FrameCandidato frameCandidato;

	public CandidatoThread(Candidato candidato, FrameCandidato frameCandidato) {
		super();
		this.candidato = candidato;
		this.frameCandidato = frameCandidato;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
