package main.sistemskeOperacije;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SOVratiKurs {
	
	private static final String service = "/convert";
	private static final String api = "http://free.currencyconverterapi.com/api/v3";

	public static double izvrsi(String iz, String u) throws Exception {

		String url = api + service + '?' + "q=" + iz + '_' + u;

		try {
			String content = SOUcitajSaSajta.izvrsi(url);
			JsonParser parser = new JsonParser();
			JsonObject con = parser.parse(content).getAsJsonObject();

			JsonObject query = con.get("query").getAsJsonObject();
			if (query.get("count").getAsInt() != 0) {
				JsonObject results = con.get("results").getAsJsonObject();
				JsonObject kurs = results.get(iz + '_' + u).getAsJsonObject();
				double k = kurs.get("val").getAsDouble();
				
				return k;
			} else
				throw new Exception("Ne postoje podaci o konverziji izmedju dve valute");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return 0;
	}
}
