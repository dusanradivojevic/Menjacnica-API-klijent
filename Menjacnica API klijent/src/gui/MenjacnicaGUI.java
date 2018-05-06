package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.BazaValuta;
import main.Valuta;

import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MenjacnicaGUI extends JFrame {
	private JTextField textField1;
	private JTextField textField2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenjacnicaGUI frame = new MenjacnicaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenjacnicaGUI.class.getResource("/icons/49668274-dollar-sign-vector-icon.jpg")));
		getContentPane().setLayout(null);
		///////////////////////////////////////////
		BazaValuta b = new BazaValuta();
		ArrayList<Valuta> lista = b.vratiValute();
		///////////////////////////////////////////
		
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
		
		JComboBox comboBox1 = new JComboBox();
		comboBox1.setBounds(67, 69, 86, 20);
		getContentPane().add(comboBox1);
		
		JComboBox comboBox2 = new JComboBox();
		comboBox2.setBounds(261, 69, 86, 20);
		getContentPane().add(comboBox2);
		
		for (int i = 0; i < lista.size(); i++) {
			comboBox1.addItem(lista.get(i).getName());
			comboBox2.addItem(lista.get(i).getName());
		}
		
		JLabel lblIznos = new JLabel("Iznos:");
		lblIznos.setBounds(67, 129, 46, 14);
		getContentPane().add(lblIznos);
		
		JLabel lblIznos_1 = new JLabel("Iznos:");
		lblIznos_1.setBounds(261, 129, 46, 14);
		getContentPane().add(lblIznos_1);
		
		textField1 = new JTextField();
		textField1.setBounds(67, 153, 86, 20);
		getContentPane().add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setEditable(false);
		textField2.setBounds(261, 153, 86, 20);
		getContentPane().add(textField2);
		textField2.setColumns(10);
		
		JButton btnKonvertuj = new JButton("KONVERTUJ");
		btnKonvertuj.setBounds(137, 215, 151, 23);
		getContentPane().add(btnKonvertuj);
	}
}
