package utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import entidadesTransversales.Empresa;
import entidadesTransversales.NivelEstudios;
import entidadesTransversales.Oferta;
import entidadesTransversales.SectorEmpresa;

public class Utils {
	public static List<Empresa> cargarEmpresas(String path){
		List<Empresa> empresas = new ArrayList<Empresa>();
		int id=1;
		JSONObject jo = new JSONObject(loadJSON(path));
		JSONArray jsonEmpresas = jo.getJSONArray("empresas");
		for(int i = 0; i<jsonEmpresas.length(); ++i) {
			List<Oferta> ofertas = new ArrayList<Oferta>();
			String nombre, cargo, nivel, sector;
			long salario;
			int experiencia; 

            JSONObject empresaObject = jsonEmpresas.getJSONObject(i);
            JSONObject empresaDetails = empresaObject.getJSONObject("empresa");
            
            nombre = empresaDetails.getString("nombre");
            
            JSONArray jsonOfertas = empresaDetails.getJSONArray("ofertas");
            for (int j = 0; j < jsonOfertas.length(); ++j) {
                JSONObject ofertaObject = jsonOfertas.getJSONObject(j);
                JSONObject ofertaDetails = ofertaObject.getJSONObject("oferta");
                cargo = ofertaDetails.getString("cargo");
                salario = Long.parseLong(ofertaDetails.getString("salario"));
                experiencia = Integer.parseInt(ofertaDetails.getString("experiencia"));
                nivel = ofertaDetails.getString("nivelEstudios");
                sector = ofertaDetails.getString("sectorEmpresa");
                ofertas.add(new Oferta(id++, cargo, salario, experiencia, NivelEstudios.valueOf(nivel), SectorEmpresa.valueOf(sector), nombre));
            }
			empresas.add(new Empresa(nombre, ofertas));
		}
		return empresas;
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
