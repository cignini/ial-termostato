package it.ial.assicurazione;

public class Persona implements Comparable<Persona> {

	private String nome;

	private String cognome;

	private String codiceFiscale;

	public Persona(String nome, String cognome, String codiceFiscale) {
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
	}

	public String getNome() {
		return nome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public String getCognome() {
		return cognome;
	}

	@Override
	public int compareTo(Persona o) {
		return nome.compareTo(o.nome);
	}
}
