package estadoSistema;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import entidadesTransversales.Candidato;
import entidadesTransversales.Oferta;

public class SingletonCitas {
	
	
	private HashMap< String, Map.Entry<Candidato, Oferta> > listaCitas;
	
	private HashMap< Integer, Map.Entry<Oferta, ArrayList<Candidato> > > listaCitasOfertas;
	
	private static SingletonCitas instance = new SingletonCitas();
	private Date lastWriteTS;
	private Date lastReadTS;
	
	private SingletonCitas() {
		listaCitas = new HashMap<>();
		listaCitasOfertas = new HashMap<>();
		lastWriteTS = new Date();
		lastReadTS = new Date();
	}


	public HashMap<String, Map.Entry<Candidato, Oferta>> getListaCitas() {
		return listaCitas;
	}


	public void setListaCitas(HashMap<String, Map.Entry<Candidato, Oferta>> listaCitas) {
		this.listaCitas = listaCitas;
	}


	public static SingletonCitas getInstance() {
		return instance;
	}

	public static void setInstance(SingletonCitas instance) {
		SingletonCitas.instance = instance;
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

	public HashMap<Integer, Map.Entry<Oferta, ArrayList<Candidato>>> getListaCitasOfertas() {
		return listaCitasOfertas;
	}

	public void setListaCitasOfertas(HashMap<Integer, Map.Entry<Oferta, ArrayList<Candidato>>> listaCitasOfertas) {
		this.listaCitasOfertas = listaCitasOfertas;
	}
	
	
}
