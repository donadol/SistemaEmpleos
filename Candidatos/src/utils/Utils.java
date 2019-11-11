package utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import entidadesTransversales.Candidato;
import entidadesTransversales.Empleo;
import entidadesTransversales.Empresa;
import entidadesTransversales.NivelEstudios;
import entidadesTransversales.SectorEmpresa;

public class Utils {
	public static ArrayList<Candidato> cargarCandidatos(String path){
		ArrayList<Candidato> candidatos = new ArrayList<Candidato>();
		JSONObject jo = new JSONObject(loadJSON(path));
		JSONArray jsonCandidatos = jo.getJSONArray("candidatos");
		for(int i = 0; i<jsonCandidatos.length(); ++i) {
			ArrayList<Empleo> empleos = new ArrayList<Empleo>();
			String nombre, documento, cargo, nivel, sector;
			long aspiracion;
			int duracion; 

            JSONObject candidatoObject = jsonCandidatos.getJSONObject(i);
            JSONObject candidatoDetails = candidatoObject.getJSONObject("candidato");
            
            nombre = candidatoDetails.getString("nombre");
            documento = candidatoDetails.getString("documento");
            aspiracion = Integer.parseInt(candidatoDetails.getString("aspiracion"));
            nivel = candidatoDetails.getString("nivelEstudios");
            
            JSONArray jsonEmpleos = candidatoDetails.getJSONArray("experiencias");
            for (int j = 0; j < jsonEmpleos.length(); ++j) {
                JSONObject empleoObject = jsonEmpleos.getJSONObject(j);
                JSONObject empleoDetails = empleoObject.getJSONObject("empleo");
                cargo = empleoDetails.getString("cargo");
                duracion = Integer.parseInt(empleoDetails.getString("duracion"));
                sector = empleoDetails.getString("sectorEmpresa");
                empleos.add(new Empleo(cargo, duracion, SectorEmpresa.valueOf(sector)));
            }
            candidatos.add(new Candidato(nombre, documento, aspiracion, NivelEstudios.valueOf(nivel), empleos));
		}
		return candidatos;
	}
	private static String loadJSON(String path) {
        String json = "";
        byte[] encoded = null;
        try {
            encoded = Files.readAllBytes(Paths.get(path));
            json = new String(encoded, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(json);
        return json;
    }
}
