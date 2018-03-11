package it.ial.assicurazione;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Archivio {

	private Assicuratore assicuratore;

	private List<Assicurazione> assicurazioni = new ArrayList<Assicurazione>();

	public Archivio(Assicuratore assicuratore) {
		this.assicuratore = assicuratore;
	}

	public Assicurazione assicura(Cliente cliente, Automobile automobile, Date dataDecorrenza, double importo) {
		Date dataScadenza = new Date(dataDecorrenza.getYear() + 1, dataDecorrenza.getMonth() + 1, 1);
		Assicurazione a = new Assicurazione(dataDecorrenza, dataScadenza, cliente, automobile, assicuratore, importo);
		assicurazioni.add(a);

		Collections.sort(assicurazioni);

		Comparator<Assicurazione> comparator = new Comparator<Assicurazione>() {
			@Override
			public int compare(Assicurazione o1, Assicurazione o2) {
				return o1.getCliente().compareTo(o2.getCliente());
			}
		};
		Collections.sort(assicurazioni, comparator);

		return a;
	}

	public String stampa(String separatore) {
		StringBuffer buffer = new StringBuffer();
		for (Assicurazione assicurazione : assicurazioni) {
			if (buffer.length() > 0) {
				buffer.append(separatore);
			}
			buffer.append(assicurazione.toString());
		}
		return buffer.toString();
	}
}
