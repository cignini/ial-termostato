package it.ial.termostato.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import it.ial.termostato.core.Giorno;
import it.ial.termostato.core.Ora;
import it.ial.termostato.core.Temperatura;
import it.ial.termostato.core.Termostato;

public class TermostatoGui {

	private JFrame frame = new JFrame();

	private JPanel griglia = new JPanel();
	private Termostato termostato = new Termostato();

	private class ButtonTemperatura extends JButton {

		private boolean selezionato = false;

		private Temperatura temperatura;

		public ButtonTemperatura(Temperatura temperatura) {
			this.temperatura = temperatura;
			setForeground(Color.BLACK);
			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selezionato = !selezionato;
					if (selezionato) {
						setForeground(Color.RED);
					} else {
						setForeground(Color.BLACK);
					}
				}
			});
			aggiorna();
		}

		public void aggiorna() {
			setText(temperatura.stampa());
		}

		public void incrementa(int gradi) {
			temperatura.incrementa(gradi);
		}

		public void decrementa(int gradi) {
			temperatura.decrementa(gradi);
		}
	}

	private class PlusMin extends JPanel {
		public PlusMin() {
			GridLayout layout=new GridLayout(2,1,0,0);
			setLayout(layout);
			add(new JButton("+"));
			add(new JButton("-"));
		}
	}

	public TermostatoGui() {

		creaGriglia();

		frame.add(griglia);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 1000, 400);
		frame.setVisible(true);
	}

	private void creaGriglia() {
		GridLayout layout = new GridLayout(8, 25); // int rows, int cols
		griglia.setLayout(layout);
		griglia.add(new PlusMin());
		for (Ora ora : termostato.getOre()) {
			griglia.add(new JLabel(ora.stampa()));
		}
		for (Giorno giorno : termostato.getGiorni()) {
			griglia.add(new JLabel(giorno.stampa()));
			for (Ora ora : termostato.getOre()) {
				ButtonTemperatura pulsante = new ButtonTemperatura(giorno.alleOre(ora));
				griglia.add(pulsante);
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TermostatoGui();
			}
		});
	}

}
