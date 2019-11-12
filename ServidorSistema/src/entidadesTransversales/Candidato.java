package entidadesTransversales;

import java.io.Serializable;
import java.util.ArrayList;

public class Candidato implements Serializable{
	private String nombre;
	private String documento;
	private long aspiracionSalarial;
	private NivelEstudios nivelEstudios;
	private ArrayList<Empleo> experiencias;
	
	public Candidato(String nombre, String documento, long aspiracionSalarial, NivelEstudios nivelEstudios,
			ArrayList<Empleo> experiencias) {
		super();
		this.nombre = nombre;
		this.documento = documento;
		this.aspiracionSalarial = aspiracionSalarial;
		this.nivelEstudios = nivelEstudios;
		this.experiencias = experiencias;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public long getAspiracionSalarial() {
		return aspiracionSalarial;
	}
	public void setAspiracionSalarial(long aspiracionSalarial) {
		this.aspiracionSalarial = aspiracionSalarial;
	}
	public NivelEstudios getNivelEstudios() {
		return nivelEstudios;
	}
	public void setNivelEstudios(NivelEstudios nivelEstudios) {
		this.nivelEstudios = nivelEstudios;
	}
	
	public ArrayList<Empleo> getExperiencias() {
		return experiencias;
	}
	public void setExperiencias(ArrayList<Empleo> experiencias) {
		this.experiencias = experiencias;
	}

	@Override
	public String toString() {
		return "Candidato [nombre=" + nombre + ", documento=" + documento + ", aspiracionSalarial=" + aspiracionSalarial
				+ ", nivelEstudios=" + nivelEstudios + ", experiencias=" + experiencias + "]";
	}
	
	
	
	
}
