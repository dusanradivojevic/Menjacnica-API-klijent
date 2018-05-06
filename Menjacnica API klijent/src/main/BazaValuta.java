package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

public class BazaValuta {
	
	private static String url = "http://free.currencyconverterapi.com/api/v3/countries";
	
	public String ucitajSaSajta() throws IOException {
		
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			con.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			boolean endReading = false;
			String response = "";
			
			while (!endReading) {
				String s = in.readLine();
				
				if (s != null) {
					response += s;
				} else {
					endReading = true;
				}
			}
			in.close();
			
			return response;		
	}
	
	public ArrayList<Valuta> vratiValute() {
	
		String response;
		ArrayList<Valuta> lista = null;
		
		try {
			response = ucitajSaSajta();
		
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
