package main.sistemskeOperacije;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import main.domen.Konverzija;

public class SOSacuvajLog {
	
	public static void izvrsi(String iz, String u, double kurs) {
		
		Konverzija konverzija = new Konverzija();
		konverzija.setIzValute(iz);
		konverzija.setuValutu(u);
		konverzija.setKurs(kurs);

		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		String date = sdf.format(now);

		konverzija.setDatumVreme(date);
		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

		JsonArray history = null;

		String obj = gson.toJson(konverzija);

		JsonObject jsonKon = gson.fromJson(obj, JsonObject.class);

		try (FileReader reader = new FileReader("data/log.json")) {
			history = gson.fromJson(reader, JsonArray.class);
		} catch (FileNotFoundException e) {
			File f = new File("data");
			f.mkdir();
		}catch (IOException e) {
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
