package servicios;

import java.util.List;
import java.util.concurrent.TimeUnit;

import entidadesTransversales.Candidato;
import utils.Utils;

public class CandidatoMain {

	public static void main(String[] args) throws InterruptedException {
		List<Candidato> candidatos = Utils.cargarCandidatos("./candidatos.json");
		for(Candidato c: candidatos) {
			CandidatoThread candidatoThread = new CandidatoThread(c);
			candidatoThread.run();
			TimeUnit.SECONDS.sleep(10);
		}
	}

}
