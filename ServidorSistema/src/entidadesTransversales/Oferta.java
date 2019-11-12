package entidadesTransversales;

import java.io.Serializable;
import java.util.Arrays;

public class Oferta implements Serializable{
	private int id;
	private String cargo;
	private long salario;
	private int experiencia;
	private NivelEstudios nivelEstudios;
	private SectorEmpresa sectorEmpresa;
	private Cita[] citas;
	private String nombreEmpresa;
	
	public Oferta(int id, String cargo, long salario, int experiencia, NivelEstudios nivelEstudios,
			SectorEmpresa sectorEmpresa, String nombreEmpresa) {
		super();
		this.nombreEmpresa = nombreEmpresa;
		this.id = id;
		this.cargo = cargo;
		this.salario = salario;
		this.experiencia = experiencia;
		this.nivelEstudios = nivelEstudios;
		this.sectorEmpresa = sectorEmpresa;
		this.citas = new Cita[3];
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public long getSalario() {
		return salario;
	}
	public void setSalario(long salario) {
		this.salario = salario;
	}
	public int getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}
	public NivelEstudios getNivelEstudios() {
		return nivelEstudios;
	}
	public void setNivelEstudios(NivelEstudios nivelEstudios) {
		this.nivelEstudios = nivelEstudios;
	}
	public SectorEmpresa getSectorEmpresa() {
		return sectorEmpresa;
	}
	public void setSectorEmpresa(SectorEmpresa sectorEmpresa) {
		this.sectorEmpresa = sectorEmpresa;
	}
	public Cita[] getCitas() {
		return citas;
	}
	public void setCitas(Cita[] citas) {
		this.citas = citas;
	}
	
	
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	@Override
	public String toString() {
		return "Oferta [id=" + id + ", cargo=" + cargo + ", salario=" + salario + ", experiencia=" + experiencia
				+ ", nivelEstudios=" + nivelEstudios + ", sectorEmpresa=" + sectorEmpresa + ", citas="
				+ Arrays.toString(citas) + "]";
	}
	
	
}
