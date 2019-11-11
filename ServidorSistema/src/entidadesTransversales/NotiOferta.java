package entidadesTransversales;

import java.io.Serializable;
import java.util.ArrayList;

public class NotiOferta implements Serializable{
	
	private ArrayList<Candidato> candidatos;
	
	public NotiOferta(ArrayList<Candidato> candidatos) {
		super();
		this.candidatos = candidatos;
	}

	public ArrayList<Candidato> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(ArrayList<Candidato> candidatos) {
		this.candidatos = candidatos;
	}
	
	
	
}
