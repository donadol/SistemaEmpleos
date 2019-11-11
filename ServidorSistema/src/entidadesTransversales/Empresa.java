package entidadesTransversales;

import java.io.Serializable;
import java.util.List;

public class Empresa implements Serializable{
	public String nombre;
	public List<Oferta> ofertas;
	public Empresa(String nombre, List<Oferta> ofertas) {
		super();
		this.nombre = nombre;
		this.ofertas = ofertas;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Oferta> getOfertas() {
		return ofertas;
	}
	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}
}
