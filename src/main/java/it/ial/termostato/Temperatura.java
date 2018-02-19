package it.ial.termostato;

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

	public Temperatura plus(Temperatura temp) {
		return new Temperatura(this.gradi + temp.gradi);
	}

	public Temperatura div(int numero) {
		return new Temperatura(gradi / numero);
	}
}
