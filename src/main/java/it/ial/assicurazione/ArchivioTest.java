package it.ial.assicurazione;

import java.util.Date;

public class ArchivioTest {

	public static void main(String[] args) {
		Assicuratore assicuratore = new Assicuratore("Mario", "Rossi", "MRCRSS3434534");
		Cliente cliente1 = new Cliente("Luca", "Bianchi", "MRABCG444", "CK345");
		Cliente cliente2 = new Cliente("Maria", "Verdi", "MRAVRG444", "CK346");
		Automobile auto1 = new Automobile("CRG6577", "Megane");
		Automobile auto2 = new Automobile("GHG6577", "Picasso");

		Archivio archivio = new Archivio(assicuratore);
		archivio.assicura(cliente2, auto2, new Date(), 100.0d);
		archivio.assicura(cliente1, auto1, new Date(), 200.0d);

		//System.out.println(archivio.stampa(", "));
		System.out.println(archivio.stampa("\n"));
	}
}
