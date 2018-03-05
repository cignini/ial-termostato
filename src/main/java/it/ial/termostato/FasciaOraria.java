package it.ial.termostato;

import java.util.Date;

public class FasciaOraria {

	private int ora;

	private Fascia fascia;

	public FasciaOraria(int ora, Fascia fascia) {
		this.ora = ora;
		this.fascia = fascia;
	}

	public int getOra() {
		return ora;
	}

	public void setFascia(Fascia fascia) {
		this.fascia = fascia;
	}

	public String getOraAsString() {
		return ora + "h";
	}

	public Fascia getFascia() {
		return fascia;
	}

	public String getFasciaAsString() {
		return fascia == null ? null : fascia.name();
	}
}
