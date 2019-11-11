package interfaces;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import entidadesTransversales.Candidato;
import entidadesTransversales.Empleo;
import entidadesTransversales.NivelEstudios;
import entidadesTransversales.NotiCandidato;

public class Main {

	public static void main(String[] args) {
		
		try {
			//Registry registry = LocateRegistry.getRegistry(host);
			
			
			ArrayList l = new ArrayList<Empleo>();
			//ISolicitudEmpleo stub = (ISolicitudEmpleo) Naming.lookup("//127.0.0.1/SolEmpleo");
			Candidato cand = new Candidato("pepe", "1234", 300, NivelEstudios.PRIMARIA, l);
			
			Registry registry = LocateRegistry.getRegistry();
			
			ISolicitudEmpleo interfaz = (ISolicitudEmpleo) registry.lookup("SolEmpleo");
			
			//System.out.print(interfaz.subscribirEmpleo(15));

			NotiCandidato response = interfaz.subscribirEmpleo(cand);
			System.out.println("response: " + response);

		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

}
