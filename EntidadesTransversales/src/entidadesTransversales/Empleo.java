package entidadesTransversales;

public class Empleo {
	public String cargo;
	public int duracion;
	public SectorEmpresa sectorEmpresa;
	public Empleo(String cargo, int duracion, SectorEmpresa sectorEmpresa) {
		super();
		this.cargo = cargo;
		this.duracion = duracion;
		this.sectorEmpresa = sectorEmpresa;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public SectorEmpresa getSectorEmpresa() {
		return sectorEmpresa;
	}
	public void setSectorEmpresa(SectorEmpresa sectorEmpresa) {
		this.sectorEmpresa = sectorEmpresa;
	}
	
}
