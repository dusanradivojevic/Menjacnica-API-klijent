package main.sistemskeOperacije;

public class SOKonvertuj {

	public static double izvrsi(double iznosIz, String valIz, String valU) throws Exception {

		Double kurs = 0.0;
		
		kurs = SOVratiKurs.izvrsi(valIz, valU);
		
		return iznosIz * kurs;
	}
}
