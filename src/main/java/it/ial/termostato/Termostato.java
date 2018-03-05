package it.ial.termostato;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Termostato {

	private List<FasceDelGiorno> griglia = new ArrayList<FasceDelGiorno>();

	private Map<Fascia, Integer> fasce = new LinkedHashMap<Fascia, Integer>();

	private boolean acceso;

	public Termostato() {
		for (GiornoDellaSettimana giorno : GiornoDellaSettimana.values()) {
			griglia.add(new FasceDelGiorno(giorno));
		}
		fasce.put(Fascia.T1, 16);
		fasce.put(Fascia.T2, 19);
		fasce.put(Fascia.T3, 21);
	}

	public FasceDelGiorno getFasciaDelGiorno(GiornoDellaSettimana giorno) {
		for (FasceDelGiorno fasceDelGiorno : griglia) {
			if (fasceDelGiorno.getGiorno().equals(giorno)) {
				return fasceDelGiorno;
			}
		}
		throw new RuntimeException("giorno non trovato: " + giorno);
	}

	public void stampa() {
		System.out.print("\t");
		for (int ora = 0; ora <= 23; ora++) {
			System.out.print("|" + TermostatoUtil.toOraAsString(ora) + " ");
		}
		System.out.println();
		for (FasceDelGiorno fasceDelGiorno : griglia) {
			System.out.println(fasceDelGiorno.toString());
		}
	}

	public void esportaInExcel() throws IOException {
		Workbook wb = new HSSFWorkbook();

		Sheet sheet = wb.createSheet("termostato");
		Row row = sheet.createRow(0);
		for (int ora = 0; ora <= 23; ora++) {
			Cell cell = row.createCell(ora + 1);
			cell.setCellValue(ora + "h");
		}
		int rigaDelGiorno = 1;
		for (GiornoDellaSettimana giorno : GiornoDellaSettimana.values()) {
			FasceDelGiorno fasceDelGiorno = getFasciaDelGiorno(giorno);
			row = sheet.createRow(rigaDelGiorno);
			Cell cell = row.createCell(0);
			cell.setCellValue(fasceDelGiorno.getGiornoAsString());
			for (int ora = 0; ora <= 23; ora++) {
				cell = row.createCell(ora + 1);
				FasciaOraria fascia = fasceDelGiorno.getFasciaOraria(ora);
				cell.setCellValue(fascia.getFasciaAsString());
			}
			rigaDelGiorno++;
		}

		String percorso = "src/main/resources/termostato.xls";
		FileOutputStream fileOut = new FileOutputStream(percorso);
		wb.write(fileOut);
		fileOut.close();

		System.out.println("TERMOSTATO ESPORTATO IN: " + percorso);
	}

	public Map<Fascia, Integer> getFasce() {
		return fasce;
	}

	public void setTemperaturaFascia(Fascia fascia, int temperatura) {
		Fascia precedente = fascia.precedente();
		if (precedente != null && getTemperatura(precedente) > temperatura) {
			throw new RuntimeException("la temperatura in " + precedente + " è superiore all'attuale");
		}
		Fascia successiva = fascia.successiva();
		if (successiva != null && getTemperatura(successiva) < temperatura) {
			throw new RuntimeException("la temperatura in " + successiva + " è inferiore all'attuale");
		}
		fasce.put(fascia, temperatura);
	}

	public int getTemperatura(Fascia fascia) {
		return fasce.get(fascia);
	}

	public void setFascia(Fascia fascia, GiornoDellaSettimana giorno, int dalle, int alle) {
		FasceDelGiorno fasceDelGiorno = getFasciaDelGiorno(giorno);
		for (int ora = dalle; ora <= alle; ora++) {
			fasceDelGiorno.aggiornaFascia(ora, fascia);
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
}
