package estadoSistema;

import java.util.ArrayList;
import java.util.List;

import entidadesTransversales.Oferta;

public class SingletonOfertas {
	private static SingletonOfertas singletonOfertas = null;
	private List<Oferta> ofertas;
	private SingletonOfertas() {
		super();
		this.ofertas = new ArrayList<Oferta>();
	}
	
	public static SingletonOfertas getInstance() {
		if(singletonOfertas == null) {
			singletonOfertas = new SingletonOfertas();
		}
		return singletonOfertas;
	}
	
	public void addOferta(Oferta o) {
		ofertas.add(o);
	}

}
