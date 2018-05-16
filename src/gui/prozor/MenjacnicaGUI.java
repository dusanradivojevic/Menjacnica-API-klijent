package gui.prozor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.kontroler.GUIKontroler;
import interfejs.MenjacnicaInterfejs;
import main.Menjacnica;
import main.domen.Valuta;

import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MenjacnicaGUI extends JFrame {
	private JTextField textFieldIz;
	private JTextField textFieldU;
	private static MenjacnicaInterfejs menjacnica;
	private static ArrayList<Valuta> lista = GUIKontroler.menjacnica.vratiValute();
	

	/**
	 * Create the frame.
	 */
	public MenjacnicaGUI() {
		setResizable(false);
		setSize(new Dimension(450, 300));
		setPreferredSize(new Dimension(500, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.GRAY);
		setTitle("Menjacnica");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(MenjacnicaGUI.class.getResource("/icons/49668274-dollar-sign-vector-icon.jpg")));
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Iz valute zemlje:");
		lblNewLabel.setMinimumSize(new Dimension(60, 14));
		lblNewLabel.setMaximumSize(new Dimension(60, 14));
		lblNewLabel.setPreferredSize(new Dimension(60, 14));
		lblNewLabel.setBounds(67, 44, 86, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblUValutuDruge = new JLabel("U valutu zemlje:");
		lblUValutuDruge.setMinimumSize(new Dimension(60, 14));
		lblUValutuDruge.setMaximumSize(new Dimension(60, 14));
		lblUValutuDruge.setPreferredSize(new Dimension(60, 14));
		lblUValutuDruge.setBounds(261, 44, 86, 14);
		getContentPane().add(lblUValutuDruge);

		JComboBox comboBoxIz = new JComboBox();
		comboBoxIz.setBounds(67, 69, 86, 20);
		getContentPane().add(comboBoxIz);

		JComboBox comboBoxU = new JComboBox();
		comboBoxU.setBounds(261, 69, 86, 20);
		getContentPane().add(comboBoxU);
		
		napuniComboBox(comboBoxIz, comboBoxU);

		JLabel lblIznos = new JLabel("Iznos:");
		lblIznos.setBounds(67, 129, 46, 14);
		getContentPane().add(lblIznos);

		JLabel lblIznos_1 = new JLabel("Iznos:");
		lblIznos_1.setBounds(261, 129, 46, 14);
		getContentPane().add(lblIznos_1);

		textFieldIz = new JTextField();
		textFieldIz.setBounds(67, 153, 86, 20);
		getContentPane().add(textFieldIz);
		textFieldIz.setColumns(10);

		textFieldU = new JTextField();
		textFieldU.setEditable(false);
		textFieldU.setBounds(261, 153, 86, 20);
		getContentPane().add(textFieldU);
		textFieldU.setColumns(10);

		JButton btnKonvertuj = new JButton("KONVERTUJ");
		btnKonvertuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String konvertovanIznos = GUIKontroler.konvertuj(lista, comboBoxIz.getSelectedIndex(), 
						comboBoxU.getSelectedIndex(), textFieldIz.getText());
				
				textFieldU.setText(konvertovanIznos);
			}
		});
		btnKonvertuj.setBounds(137, 215, 151, 23);
		getContentPane().add(btnKonvertuj);
	}

	//ArrayList<Valuta> lista = menjacnica.vratiValute()
	private void napuniComboBox(JComboBox iz, JComboBox u) {
		
		for (int i = 0; i < lista.size(); i++) {
			iz.addItem(lista.get(i).getName());
			u.addItem(lista.get(i).getName());
		}
	}
}
