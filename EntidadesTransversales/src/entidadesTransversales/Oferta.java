package entidadesTransversales;


public class Oferta {
	public String cargo;
	public long salario;
	public NivelEstudios nivelEstudios;
	public SectorEmpresa sectorEmpresa;
	public Cita[] citas;
	public Oferta(String cargo, long salario, NivelEstudios nivelEstudios, SectorEmpresa sectorEmpresa) {
		super();
		this.cargo = cargo;
		this.salario = salario;
		this.nivelEstudios = nivelEstudios;
		this.sectorEmpresa = sectorEmpresa;
		this.citas = new Cita[3];
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
}
