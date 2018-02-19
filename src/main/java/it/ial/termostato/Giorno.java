package it.ial.termostato;

public class Giorno extends Cella {

	private int giornoDellaSettimana;

	private Temperatura[] temperature = new Temperatura[24];

	public Giorno(int giornoDellaSettimana, Temperatura temperaturaPredefinita) {
		this.giornoDellaSettimana = giornoDellaSettimana;
		for (int ora = 0; ora < temperature.length; ora++) {
			temperature[ora] = temperaturaPredefinita;
		}
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

	public String stampa(String separatore) {
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

	public String stampaGiorno() {
		return toGiorno(giornoDellaSettimana);
	}
}
