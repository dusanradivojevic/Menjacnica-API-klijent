package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BazaValuta {
	
	private static String url = "http://free.currencyconverterapi.com/api/v3/countries";
	
	public String preuzmiValute() throws IOException {
		
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
			
			return response.toString();
	}
	
}
