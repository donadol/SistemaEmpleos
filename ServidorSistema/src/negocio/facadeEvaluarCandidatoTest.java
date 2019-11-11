package negocio;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import entidadesTransversales.Candidato;
import entidadesTransversales.Empleo;
import entidadesTransversales.NivelEstudios;
import entidadesTransversales.Oferta;
import entidadesTransversales.SectorEmpresa;

public class facadeEvaluarCandidatoTest {

	@Test
	public void testEvaluarCandidato() {
		Oferta of = new Oferta(1,"gerente",3000, 5,NivelEstudios.PROFESIONAL,SectorEmpresa.COMERCIO);
		Empleo p = new Empleo("gerente",5,SectorEmpresa.COMERCIO);
		ArrayList<Empleo> lis = new ArrayList<Empleo>();
		lis.add(p);
		Candidato cand = new Candidato("Pedro","101851091", 3000, NivelEstudios.PROFESIONAL,lis);
		
		int actual = facadeEvaluarCandidato.evaluarCandidato(cand, of);
		//System.out.println(actual);
		
		assertEquals(80, actual);
	}
	
	@Test
	public void testEvaluarCandidatoNoApto() {
		Oferta of = new Oferta(1,"gerente",3000, 5,NivelEstudios.PROFESIONAL,SectorEmpresa.COMERCIO);
		Empleo p = new Empleo("tecnico",5,SectorEmpresa.COMERCIO);
		ArrayList<Empleo> lis = new ArrayList<Empleo>();
		lis.add(p);
		Candidato cand = new Candidato("Pedro","101851091", 3000, NivelEstudios.PROFESIONAL,lis);
		
		int actual = facadeEvaluarCandidato.evaluarCandidato(cand, of);
		//System.out.println(actual);
		
		assertEquals(0, actual);
	}
	
	@Test
	public void testEvaluarCandidatoMejorCandidato() {
		Oferta of = new Oferta(1,"gerente",3000, 5,NivelEstudios.PROFESIONAL,SectorEmpresa.COMERCIO);
		ArrayList<Empleo> lis = new ArrayList<Empleo>();
		
		Empleo p = new Empleo("gerente",6,SectorEmpresa.COMERCIO);
		//Empleo p1 = new Empleo("gerente",5,SectorEmpresa.COMERCIO);
		//lis.add(p1);
		lis.add(p);
		Candidato cand = new Candidato("Pedro","101851091", 2800, NivelEstudios.POSGRADO,lis);
		
		int actual = facadeEvaluarCandidato.evaluarCandidato(cand, of);
		//System.out.println(actual);
		
		assertEquals(100, actual);
	}

}
