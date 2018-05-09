package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BazaValuta {

	public String ucitajSaSajta(String url) throws IOException {

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

		String service = "/countries";
		String api = "http://free.currencyconverterapi.com/api/v3";
		String response;
		ArrayList<Valuta> lista = null;

		try {
			response = ucitajSaSajta(api + service);

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

	public double vratiKurs(String iz, String u) throws Exception {

		String service = "/convert";
		String api = "http://free.currencyconverterapi.com/api/v3";
		String url = api + service + '?' + "q=" + iz + '_' + u;

		try {
			String content = ucitajSaSajta(url);
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

	public void sacuvajLog(String iz, String u, double kurs) {

		Konverzija konverzija = new Konverzija();
		konverzija.setIzValute(iz);
		konverzija.setuValutu(u);
		konverzija.setKurs(kurs);

		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH-mm-ss");
		String date = sdf.format(now);

		konverzija.setDatumVreme(date);
		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

		JsonArray history = null;

		String obj = gson.toJson(konverzija);

		JsonObject jsonKon = gson.fromJson(obj, JsonObject.class);

		try (FileReader reader = new FileReader("data/log.json")) {
			history = gson.fromJson(reader, JsonArray.class);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		try (FileWriter writer = new FileWriter("data/log.json")) {
			if (history == null)
				history = new JsonArray();

			history.add(jsonKon);
			writer.write(gson.toJson(history));

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
