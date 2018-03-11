package it.ial.assicurazione;

import java.util.UUID;

public class Assicuratore extends Persona {

	private String idAssicuratore = UUID.randomUUID().toString();

	public Assicuratore(String nome, String cognome, String codiceFiscale) {
		super(nome, cognome, codiceFiscale);
	}
	
	public String getIdAssicuratore() {
		return idAssicuratore;
	}

}
