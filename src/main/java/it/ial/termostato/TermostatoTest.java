package it.ial.termostato;

import java.io.IOException;

public class TermostatoTest {

	public static void main(String[] args) throws IOException {
		Termostato termostato = new Termostato();
		termostato.setFascia(Fascia.T3,GiornoDellaSettimana.LUN, 10, 18);
		termostato.accendi();
		termostato.stampa();
		
		termostato.esportaInExcel();
	}

}
