package it.ial.assicurazione;

public class Cliente extends Persona{

	private String numeroPatente;

	public Cliente(String nome, String cognome, String codiceFiscale, String numeroPatente) {
		super(nome, cognome, codiceFiscale);
		this.numeroPatente = numeroPatente;
	}

	public String getNumeroPatente() {
		return numeroPatente;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getNome()).append(" ").append(getCognome()).append(" ").append(numeroPatente);
		return builder.toString();
	}

}
