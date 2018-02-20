package it.ial.termostato.core;

public class Ora extends Cella {

	private int oraDelGiorno;

	public Ora(int oraDelGiorno) {
		this.oraDelGiorno = oraDelGiorno;
	}

	public int getOraDelGiorno() {
		return oraDelGiorno;
	}

	public String stampa() {
		if (oraDelGiorno <= 9) {
			return " " + oraDelGiorno + "h";
		} else {
			return "" + oraDelGiorno + "h";
		}
	}
}
