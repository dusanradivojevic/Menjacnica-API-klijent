package interfejs;

import java.io.IOException;
import java.util.ArrayList;

import main.domen.Valuta;

public interface MenjacnicaInterfejs {

	public String ucitajSaSajta(String url) throws IOException;
	
	public ArrayList<Valuta> vratiValute();
	
	public double vratiKurs(String iz, String u) throws Exception;
	
	public void sacuvajLog(String iz, String u, double kurs);
}
