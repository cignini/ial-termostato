package it.ial.assicurazione;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Assicurazione implements Comparable<Assicurazione> {

	private Date dataDecorrenza;

	private Date dataScadenza;

	private Cliente cliente;

	private Automobile automobile;

	private Assicuratore assicuratore;

	private double importo;

	public Assicurazione(Date dataDecorrenza, Date dataScadenza, Cliente cliente, Automobile automobile,
			Assicuratore assicuratore, double importo) {
		this.dataDecorrenza = dataDecorrenza;
		this.dataScadenza = dataScadenza;
		this.cliente = cliente;
		this.automobile = automobile;
		this.assicuratore = assicuratore;
		this.importo = importo;
	}

	public Date getDataDecorrenza() {
		return dataDecorrenza;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Automobile getAutomobile() {
		return automobile;
	}

	public Assicuratore getAssicuratore() {
		return assicuratore;
	}

	public double getImporto() {
		return importo;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/yyyy", Locale.ENGLISH);
		StringBuffer buffer = new StringBuffer();
		buffer.append("assicurazione di ").append(cliente).append(" con decorrenza dal ");
		buffer.append(sdf.format(dataDecorrenza)).append(" di importo ").append(importo).append(" euro");
		return buffer.toString();
	}

	@Override
	public int compareTo(Assicurazione o) {
		return cliente.compareTo(o.cliente);
	}
}
