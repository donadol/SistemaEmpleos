package estadoSistema;

import java.util.ArrayList;
import java.util.Date;

import entidadesTransversales.Oferta;

public class SingletonOfertas {
	private static SingletonOfertas instance = new SingletonOfertas();
	private ArrayList<Oferta> ofertas;
	
	private Date lastWriteTS;
	private Date lastReadTS;
	
	private SingletonOfertas() {
		this.ofertas = new ArrayList<Oferta>();
		lastWriteTS = new Date();
		lastReadTS = new Date();
	}

	public static SingletonOfertas getInstance() {
		return instance;
	}

	public static void setInstance(SingletonOfertas instance) {
		SingletonOfertas.instance = instance;
	}

	public ArrayList<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(ArrayList<Oferta> ofertas) {
		this.ofertas = ofertas;
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
