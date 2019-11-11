package EntryPoint;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import RMIServer.SolicitudEmpleo;
import RMIServer.SolicitudOferta;
import interfaces.ISolicitudEmpleo;
import interfaces.ISolicitudOferta;

public class Main {

	public static void main(String[] args) {
		
		try {
			
			SolicitudEmpleo solEmp = new SolicitudEmpleo();
			SolicitudOferta solOfer = new SolicitudOferta();
			
			ISolicitudEmpleo stub_solEmp = (ISolicitudEmpleo) UnicastRemoteObject.exportObject(solEmp, 5010);
			ISolicitudOferta stub_solOfer = (ISolicitudOferta) UnicastRemoteObject.exportObject(solOfer, 5020);
			
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("SolEmpleo", stub_solEmp);
			registry.bind("SolOferta", stub_solOfer);
			
			System.err.println("Server Ready");
		} catch (Exception e) {
			System.err.println("Server exception" + e.toString());
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
	}

}
