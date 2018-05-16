package gui.kontroler;

import java.awt.EventQueue;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import gui.prozor.MenjacnicaGUI;
import interfejs.MenjacnicaInterfejs;
import main.Menjacnica;
import main.domen.Valuta;

public class GUIKontroler {
	
	public static MenjacnicaInterfejs menjacnica;
	public static MenjacnicaGUI mg;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menjacnica = new Menjacnica();
					
					mg = new MenjacnicaGUI();
					mg.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static String konvertuj(ArrayList<Valuta> lista, int fr, int t, String txtIz) {
		
		if (txtIz == null || txtIz.equals("") || !isNumeric(txtIz)) {
			JOptionPane.showMessageDialog(mg, "Morate ispravno uneti iznos za konvertovanje.", 
					"Greska", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		int from = fr;
		int to = t;
		String valIz = lista.get(from).getCurrencyId();
		String valU = lista.get(to).getCurrencyId();
		double iznosIz = Double.parseDouble(txtIz);
		double iznosU = 0.0;
		
		double kurs = 0.0;
		try {
			
			iznosU = menjacnica.konvertuj(iznosIz, valIz, valU);	
			
			kurs = menjacnica.vratiKurs(valIz, valU);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(mg, "Ne postoje podaci o konverziji izmedju datih valuta.",
					"Greska", JOptionPane.ERROR_MESSAGE);
		} finally {
			menjacnica.sacuvajLog(valIz, valU, kurs);
		}
		
		DecimalFormat formatter = new DecimalFormat("#0.00");

		return "" + formatter.format(iznosU);
	}
	
	private static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
			
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
