package estadoSistema;

import java.util.ArrayList;
import java.util.List;

import entidadesTransversales.Empresa;

public class SingletonEmpresas {
	private static SingletonEmpresas singletonEmpresas = null;
	private List<Empresa> empresas;
	private SingletonEmpresas() {
		super();
		this.empresas = new ArrayList<Empresa>();
	}
	
	public static SingletonEmpresas getInstance() {
		if(singletonEmpresas == null) {
			singletonEmpresas = new SingletonEmpresas();
		}
		return singletonEmpresas;
	}
	
	public void addEmpresa(Empresa e) {
		empresas.add(e);
	}
}
