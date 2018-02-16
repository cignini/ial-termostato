package it.ial.termostato;

public class TermostatoTest {

	public static void main(String[] args) {
		Termostato termostato = new Termostato(18, 30);
		termostato.setTemperatura(22, 2, 18, 22);
		termostato.setTemperatura(20, 4, 15, 20);
		termostato.stampa();
	}

}
