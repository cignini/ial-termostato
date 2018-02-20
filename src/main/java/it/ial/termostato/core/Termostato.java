package it.ial.termostato.core;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Termostato {

	private Ora[] ore = new Ora[24];
	private Giorno[] giorni = new Giorno[7];

	private Temperatura tempMin = new Temperatura(8);

	private Temperatura tempMax = new Temperatura(28);

	private boolean acceso;

	public Termostato() {
		azzera();
	}

	public Termostato(int tempMin, int tempMax) {
		if (tempMax <= tempMin || tempMin < 0) {
			throw new IllegalArgumentException("temperature inserite non valide");
		}
		this.tempMin = new Temperatura(tempMin);
		this.tempMax = new Temperatura(tempMax);
		azzera();
	}

	public Giorno[] getGiorni() {
		return giorni;
	}

	public Ora[] getOre() {
		return ore;
	}

	public void esportaInExcel() throws IOException {
		Workbook wb = new HSSFWorkbook();

		Sheet sheet = wb.createSheet("termostato");
		Row row = sheet.createRow(0);
		for (Ora ora : ore) {
			Cell cell = row.createCell(ora.getOraDelGiorno() + 1);
			cell.setCellValue(ora.getOraDelGiorno());
		}
		for (Giorno giorno : giorni) {
			row = sheet.createRow(giorno.getGiornoDellaSettimana());
			Cell cell = row.createCell(0);
			cell.setCellValue(giorno.stampa());
			for (Ora ora : ore) {
				Temperatura temperatura = giorno.alleOre(ora);
				cell = row.createCell(ora.getOraDelGiorno() + 1);
				cell.setCellValue(temperatura.getGradi());
			}
		}

		String percorso = "src/main/resources/termostato.xls";
		FileOutputStream fileOut = new FileOutputStream(percorso);
		wb.write(fileOut);
		fileOut.close();

		nuovaRiga();
		System.out.println("TERMOSTATO ESPORTATO IN: " + percorso);
	}

	public void regolaTemperatura(int temp, int numGiorno, int dalle, int alle) {
		if (temp < tempMin.getGradi() || temp > tempMax.getGradi()) {
			throw new IllegalArgumentException("temperatura non valida");
		}
		for (int numOra = dalle; numOra <= alle; numOra++) {
			giorno(numGiorno).regolaTemperatura(ora(numOra), temp);
		}
	}

	private Ora ora(int valore) {
		if (valore < 0 || valore > 23) {
			throw new IllegalArgumentException("le ore vanno da 0 a 23");
		}
		return ore[valore];
	}

	private Giorno giorno(int valore) {
		if (valore < 1 || valore > 7) {
			throw new IllegalArgumentException("i giorni vanno da 0 a 23");
		}
		return giorni[valore - 1];
	}

	public void azzera() {
		int gradi = (tempMax.getGradi() + tempMin.getGradi()) / 2;
		for (int giorno = 0; giorno < 7; giorno++) {
			giorni[giorno] = new Giorno(giorno + 1, gradi);
		}
		for (int ora = 0; ora < ore.length; ora++) {
			ore[ora] = new Ora(ora);
		}
	}

	public void accendi() {
		acceso = true;
	}

	public void spegni() {
		acceso = false;
	}

	public boolean isAcceso() {
		return acceso;
	}

	public Temperatura getTempMin() {
		return tempMin;
	}

	public Temperatura getTempMax() {
		return tempMax;
	}

	public int getTempMedia() {
		int media = 0;
		for (Giorno giorno : giorni) {
			media += giorno.getTempMedia();
		}
		return media / giorni.length;
	}

	public void stampa() {
		nuovaRiga();
		System.out.print("TERMOSTATO: ");
		if (acceso) {
			System.out.println("ACCESO");
		} else {
			System.out.println("SPENTO");
		}
		nuovaRiga();
		System.out.print("   ");
		for (Ora ora : ore) {
			System.out.print("| ");
			System.out.print(ora.stampa());
		}
		System.out.println(" |");
		for (Giorno giorno : giorni) {
			System.out.print(giorno.stampaConTemperature("| "));
			System.out.println(" |");
		}
	}

	private void nuovaRiga() {
		System.out.println();
	}

}
