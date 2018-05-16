package main.sistemskeOperacije;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import main.domen.Valuta;

public class SOVratiValute {

	private static final String service = "/countries";
	private static final String api = "http://free.currencyconverterapi.com/api/v3";
	
	public static ArrayList<Valuta> izvrsi() {
		
		String response;
		ArrayList<Valuta> lista = null;

		try {
			response = SOUcitajSaSajta.izvrsi(api + service);

			Gson gson = new GsonBuilder().create();

			JsonObject contentJson = gson.fromJson(response, JsonObject.class);

			JsonObject countriesJson = contentJson.get("results").getAsJsonObject();

			lista = new ArrayList<Valuta>();

			for (Entry<String, JsonElement> entry : countriesJson.entrySet()) {
				Valuta item = gson.fromJson(entry.getValue().getAsJsonObject(), Valuta.class);
				lista.add(item);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}
}
