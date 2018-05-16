package main;

import java.io.IOException;
import java.util.ArrayList;

import interfejs.MenjacnicaInterfejs;
import main.domen.Valuta;
import main.sistemskeOperacije.SOKonvertuj;
import main.sistemskeOperacije.SOSacuvajLog;
import main.sistemskeOperacije.SOUcitajSaSajta;
import main.sistemskeOperacije.SOVratiKurs;
import main.sistemskeOperacije.SOVratiValute;

public class Menjacnica implements MenjacnicaInterfejs {

	public String ucitajSaSajta(String url) throws IOException {

		return SOUcitajSaSajta.izvrsi(url);
	}

	public ArrayList<Valuta> vratiValute() {

		return SOVratiValute.izvrsi();
	}

	public double vratiKurs(String iz, String u) throws Exception {

		return SOVratiKurs.izvrsi(iz, u);
	}

	public void sacuvajLog(String iz, String u, double kurs) {

		SOSacuvajLog.izvrsi(iz, u, kurs);
	}
	
	public double konvertuj(double iznosIz, String valIz, String valU) throws Exception {
		
		return SOKonvertuj.izvrsi(iznosIz, valIz, valU);
	}
}
