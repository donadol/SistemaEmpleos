package entidadesTransversales;

import java.io.Serializable;

public class NotiCandidato implements Serializable{
	private String empresaName;
	private int id;
	private String cargo;
	private long salario;
	
	public NotiCandidato(String empresaName, int id, String cargo, long salario) {
		super();
		this.empresaName = empresaName;
		this.id = id;
		this.cargo = cargo;
		this.salario = salario;
	}
	
	public String getEmpresaName() {
		return empresaName;
	}
	public void setEmpresaName(String empresaName) {
		this.empresaName = empresaName;
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

	@Override
	public String toString() {
		return "NotiCandidato [empresaName=" + empresaName + ", id=" + id + ", cargo=" + cargo + ", salario=" + salario
				+ "]";
	}
	
	
	
}
