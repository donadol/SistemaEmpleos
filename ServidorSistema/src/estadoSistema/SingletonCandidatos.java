package estadoSistema;

import java.util.ArrayList;

import entidadesTransversales.Candidato;

public class SingletonCandidatos {
	
	private static SingletonCandidatos instance  = new SingletonCandidatos();
	
	private static ArrayList<Candidato> listaCandidatos;
	
	
	private SingletonCandidatos() {
		listaCandidatos = new ArrayList<>();
	};
	
	public static SingletonCandidatos getInstance() {
		return instance;
	}

	public static void setInstance(SingletonCandidatos instance) {
		SingletonCandidatos.instance = instance;
	}

	public static ArrayList<Candidato> getListaCandidatos() {
		return listaCandidatos;
	}

	public static void setListaCandidatos(ArrayList<Candidato> listaCandidatos) {
		SingletonCandidatos.listaCandidatos = listaCandidatos;
	}
	
}
