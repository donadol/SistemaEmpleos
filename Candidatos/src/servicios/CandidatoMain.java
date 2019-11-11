package servicios;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.concurrent.TimeUnit;

import entidadesTransversales.Candidato;
import interfaces.ISolicitudEmpleo;
import utils.Utils;

public class CandidatoMain {
	static ISolicitudEmpleo interfaz;

	public static void main(String[] args) throws InterruptedException {
		try {
			Registry registry = LocateRegistry.getRegistry();
			interfaz = (ISolicitudEmpleo) registry.lookup("SolEmpleo");
		}catch(Exception e) {
			System.err.println("Candidato exception: " + e.toString());
			e.printStackTrace();
		}
		List<Candidato> candidatos = Utils.cargarCandidatos("./candidatos.json");
		for(Candidato c: candidatos) {
			CandidatoThread candidatoThread = new CandidatoThread(c, interfaz);
			candidatoThread.run();
			TimeUnit.SECONDS.sleep(5);
		}
	}

}
