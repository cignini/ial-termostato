package it.ial.assicurazione;

public class Automobile {

	private String targa;
	
	private String modello;

	public Automobile(String targa, String modello) {
		this.targa = targa;
		this.modello = modello;
	}

	public String getTarga() {
		return targa;
	}
	
	public String getModello() {
		return modello;
	}
}
