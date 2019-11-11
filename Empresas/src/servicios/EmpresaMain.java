package servicios;

import java.util.List;
import java.util.concurrent.TimeUnit;

import entidadesTransversales.Empresa;
import utils.Utils;

public class EmpresaMain {

	public static void main(String[] args) throws InterruptedException {
		List<Empresa> empresas = Utils.cargarEmpresas("./empresas.json");
		for(Empresa e: empresas) {
			EmpresaThread empresaThread = new EmpresaThread(e);
			empresaThread.run();
			TimeUnit.SECONDS.sleep(10);
		}
	}

}
