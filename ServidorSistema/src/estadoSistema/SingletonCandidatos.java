package estadoSistema;

import java.util.ArrayList;
import java.util.List;

import entidadesTransversales.Candidato;

public class SingletonCandidatos {
	private static SingletonCandidatos singletonCandidatos = null;
	private List<Candidato> candidatos;

	private SingletonCandidatos() {
		super();
		this.candidatos = new ArrayList<Candidato>();
	}
	public static SingletonCandidatos getInstance() {
		if(singletonCandidatos == null) {
			singletonCandidatos = new SingletonCandidatos();
		}
		return singletonCandidatos;
	}
	
	public void addCandidato(Candidato c) {
		candidatos.add(c);
	}
}
