package servicios;

import GUI.FrameEmpresa;
import entidadesTransversales.Candidato;
import entidadesTransversales.Empresa;
import entidadesTransversales.NivelEstudios;

public class EmpresaThread implements Runnable{
	private Empresa empresa;
	private FrameEmpresa frameEmpresa;
	public EmpresaThread(Empresa empresa) {
		super();
		this.empresa = empresa;
		frameEmpresa = new FrameEmpresa(empresa);
	}
	@Override
	public void run() {
		try {
			frameEmpresa.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		frameEmpresa.actualizarTable(new Candidato("alguien", "1234", 1000, NivelEstudios.POSGRADO, null), 0);
	}
	
}
