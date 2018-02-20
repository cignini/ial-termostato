package it.ial.termostato.core;

public class Temperatura extends Cella {

	private int gradi;

	public Temperatura(int gradi) {
		this.gradi = gradi;
	}

	public void setGradi(int gradi) {
		this.gradi = gradi;
	}

	public int getGradi() {
		return gradi;
	}

	public String stampa() {
		if (gradi <= 9) {
			return " " + gradi + "°";
		} else {
			return "" + gradi + "°";
		}
	}

	public void incrementa(int numero) {
		setGradi(gradi + numero);
	}

	public void decrementa(int numero) {
		setGradi(gradi - numero);
	}

	public void div(int numero) {
		setGradi(gradi / numero);
	}
}
