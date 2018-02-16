package it.ial.termostato;

import java.io.IOException;

public class TermostatoTest {

	public static void main(String[] args) throws IOException {
		Termostato termostato = new Termostato(18, 30);
		termostato.setTemperatura(22, 2, 18, 22);
		termostato.setTemperatura(20, 4, 15, 20);
		termostato.accendi();
		termostato.stampa();
		
		termostato.esportaInExcel();
	}

}
