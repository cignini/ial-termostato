package it.ial.termostato;

public class Giorno extends Cella {

	private int giornoDellaSettimana;

	private Temperatura[] temperature = new Temperatura[24];

	public Giorno(int giornoDellaSettimana, int gradi) {
		this.giornoDellaSettimana = giornoDellaSettimana;
		for (int ora = 0; ora < temperature.length; ora++) {
			temperature[ora] = new Temperatura(gradi);
		}
	}

	public Temperatura getTempMedia() {
		Temperatura media = new Temperatura(0);
		for (Temperatura t : temperature) {
			media = media.plus(t);
		}
		return media.div(temperature.length);
	}

	public int getGiornoDellaSettimana() {
		return giornoDellaSettimana;
	}

	public Temperatura alleOre(Ora ora) {
		return temperature[ora.getOraDelGiorno()];
	}

	public void regolaTemperatura(Ora ora, int gradi) {
		alleOre(ora).setGradi(gradi);
	}

	public String stampaConTemperature(String separatore) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(toGiorno(giornoDellaSettimana));
		for (Temperatura temperatura : temperature) {
			buffer.append(separatore);
			buffer.append(temperatura.stampa());
		}
		return buffer.toString();
	}

	private String toGiorno(int giorno) {
		switch (giorno) {
		case 1:
			return "lun";
		case 2:
			return "mar";
		case 3:
			return "mer";
		case 4:
			return "gio";
		case 5:
			return "ven";
		case 6:
			return "sab";
		case 7:
			return "dom";
		default:
			return "   ";
		}
	}

	@Override
	public String stampa() {
		return toGiorno(giornoDellaSettimana);
	}
}
