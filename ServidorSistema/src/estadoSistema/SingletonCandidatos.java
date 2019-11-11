package estadoSistema;

import java.util.ArrayList;
import java.util.Date;

import entidadesTransversales.Candidato;

public class SingletonCandidatos {
	
	private static SingletonCandidatos instance  = new SingletonCandidatos();
	
	private ArrayList<Candidato> listaCandidatos;
	
	private Date lastWriteTS;
	private Date lastReadTS;
	
	private SingletonCandidatos() {
		listaCandidatos = new ArrayList<>();
		lastWriteTS = new Date();
		lastReadTS = new Date();
	};
	
	public static SingletonCandidatos getInstance() {
		return instance;
	}

	public static void setInstance(SingletonCandidatos instance) {
		SingletonCandidatos.instance = instance;
	}

	public ArrayList<Candidato> getListaCandidatos() {
		return listaCandidatos;
	}

	public void setListaCandidatos(ArrayList<Candidato> listaCandidatos) {
		this.listaCandidatos = listaCandidatos;
	}

	public Date getLastWriteTS() {
		return lastWriteTS;
	}

	public void setLastWriteTS(Date lastWriteTS) {
		this.lastWriteTS = lastWriteTS;
	}

	public Date getLastReadTS() {
		return lastReadTS;
	}

	public void setLastReadTS(Date lastReadTS) {
		this.lastReadTS = lastReadTS;
	}
	
}
