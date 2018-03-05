package it.ial.termostato;

import java.util.ArrayList;
import java.util.List;

public class FasceDelGiorno {

	private GiornoDellaSettimana giorno;

	private List<FasciaOraria> fasce = new ArrayList<FasciaOraria>();

	public FasceDelGiorno(GiornoDellaSettimana giorno) {
		this.giorno = giorno;
		for (int ora = 0; ora < 24; ora++) {
			fasce.add(new FasciaOraria(ora, Fascia.T2));
		}
	}

	public GiornoDellaSettimana getGiorno() {
		return giorno;
	}

	public List<FasciaOraria> getFasce() {
		return fasce;
	}

	public FasciaOraria getFasciaOraria(int ora) {
		for (FasciaOraria fasciaOraria : fasce) {
			if (fasciaOraria.getOra() == ora) {
				return fasciaOraria;
			}
		}
		throw new RuntimeException("ora non valida " + ora + ", inserisci un'ora tra 0 e 23");
	}

	public String getGiornoAsString() {
		return giorno.name().toLowerCase();
	}

	public void aggiornaFascia(int ora, Fascia fascia) {
		getFasciaOraria(ora).setFascia(fascia);
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer(getGiornoAsString());
		buffer.append("\t");
		for (FasciaOraria fasciaOraria : fasce) {
			buffer.append("| ");
			buffer.append(fasciaOraria.getFascia()).append(" ");
		}
		return buffer.toString();
	}
}
