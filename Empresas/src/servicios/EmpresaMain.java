package servicios;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

import GUI.FrameEmpresa;
import entidadesTransversales.Empresa;
import interfaces.ISolicitudOferta;
import utils.Utils;

public class EmpresaMain {
	static Empresa empresa;
	static FrameEmpresa frameEmpresa;
	static ISolicitudOferta interfaz;
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws InterruptedException {
		try {
			Registry registry = LocateRegistry.getRegistry();
			interfaz = (ISolicitudOferta)registry.lookup("SolOferta");
		}catch(Exception e) {
			System.err.println("Empresa exception: " + e.toString());
			e.printStackTrace();
		}
		List<Empresa> empresas = Utils.cargarEmpresas("./empresas.json");
		int i = sc.nextInt();
		empresa = empresas.get(i);
		try {
			frameEmpresa = new FrameEmpresa(empresa);
			frameEmpresa.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i1=0; i1<empresa.getOfertas().size();++i1) {
			OfertaThread ofertaThread = new OfertaThread(i1, frameEmpresa, interfaz, empresa.getOfertas().get(i1));
			ofertaThread.run();
		}
	}
}
