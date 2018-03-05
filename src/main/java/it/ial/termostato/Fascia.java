package it.ial.termostato;

public enum Fascia {
	T1, T2, T3;

	public Fascia precedente() {
		return ordinal() == 0 ? null : values()[ordinal() - 1];
	}

	public Fascia successiva() {
		return ordinal() == 2 ? null : values()[ordinal() + 1];
	}
}
