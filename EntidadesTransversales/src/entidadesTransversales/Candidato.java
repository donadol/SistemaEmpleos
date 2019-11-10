package entidadesTransversales;

import java.util.ArrayList;
import java.util.List;

public class Candidato {
	public String nombre;
	public String documento;
	public long aspiracionSalarial;
	public NivelEstudios nivelEstudios;
	List<Empleo> experiencias;
	List<Solicitud> solicidutes;
	public Candidato(String nombre, String documento, long aspiracionSalarial, NivelEstudios nivelEstudios,
			List<Empleo> experiencias) {
		super();
		this.nombre = nombre;
		this.documento = documento;
		this.aspiracionSalarial = aspiracionSalarial;
		this.nivelEstudios = nivelEstudios;
		this.experiencias = experiencias;
		this.solicidutes = new ArrayList<Solicitud>();
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
	public List<Empleo> getExperiencias() {
		return experiencias;
	}
	public void setExperiencias(List<Empleo> experiencias) {
		this.experiencias = experiencias;
	}
	public List<Solicitud> getSolicidutes() {
		return solicidutes;
	}
	public void setSolicidutes(List<Solicitud> solicidutes) {
		this.solicidutes = solicidutes;
	}
}
