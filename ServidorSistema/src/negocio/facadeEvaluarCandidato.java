package negocio;

import java.util.ArrayList;

import entidadesTransversales.Candidato;
import entidadesTransversales.Empleo;
import entidadesTransversales.NivelEstudios;
import entidadesTransversales.Oferta;

public class facadeEvaluarCandidato {
	
	
	
	public static int evaluarCandidato(final Candidato cand, final Oferta oferta) {
		String cargoOferta = oferta.getCargo();
		ArrayList<Empleo> candEmpleos = cand.getExperiencias();
		int sumaPuntaje = 0;
		boolean bCargo = false;
		for(Empleo emp : candEmpleos) {
			if(emp.getCargo().equals(cargoOferta) ) {
				bCargo = true;
				break;
			}
		}
		
		if(bCargo == false) {
			return 0;
		}
		
		if(oferta.getSalario() >= cand.getAspiracionSalarial()) {
			sumaPuntaje+=20;
		}
		
		if(oferta.getSalario() - 200 >= cand.getAspiracionSalarial()) {
			sumaPuntaje+=10;
		}
		
		int sumaAniosExp = 0;
		int sumaAniosSector = 0;
		
		for(Empleo emp:candEmpleos) {
			sumaAniosExp+=emp.getDuracion();
			if(emp.getSectorEmpresa().equals(oferta.getSectorEmpresa())) {
				sumaAniosSector+=emp.getDuracion();
			}
		}
		
		if(sumaAniosExp!=0) {
			float percentage = (sumaAniosSector*1.0f/sumaAniosExp*1.0f);
			sumaPuntaje+=Math.round((20f * Math.round(percentage)));			
		}
		
		if(oferta.getExperiencia() <= sumaAniosExp ) {
			sumaPuntaje+=20;
		}
		
		if(oferta.getExperiencia() + 1 <= sumaAniosExp ) {
			sumaPuntaje+=10;
		}
		
		
		
		NivelEstudios nivel = cand.getNivelEstudios();
		switch(oferta.getNivelEstudios()) {
		case PRIMARIA:
			if(nivel.equals(NivelEstudios.PRIMARIA)) {
				sumaPuntaje+=20;
				break;
			}
		case SECUNDARIA:
			if(nivel.equals(NivelEstudios.SECUNDARIA)) {
				sumaPuntaje+=20;
				break;
			}
		case TECNICO:
			if(nivel.equals(NivelEstudios.TECNICO)) {
				sumaPuntaje+=20;
				break;
			}
		case PROFESIONAL:
			if(nivel.equals(NivelEstudios.PROFESIONAL)) {
				sumaPuntaje+=20;
				break;
			}
		case POSGRADO:
			if(nivel.equals(NivelEstudios.POSGRADO)) {
				sumaPuntaje+=20;
				break;
			}
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sumaPuntaje;
	}
	
	
}
