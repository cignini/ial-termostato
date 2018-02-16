package it.ial.termostato;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Termostato {

	private int[][] griglia = new int[7][24];

	private int tempMin = 8;

	private int tempMax = 28;

	private boolean acceso;

	public Termostato() {
		inizializza((tempMin + tempMax) / 2);
	}

	public Termostato(int tempMin, int tempMax) {
		this.tempMin = tempMin;
		this.tempMax = tempMax;
		inizializza((tempMin + tempMax) / 2);
	}

	public void esportaInExcel() throws IOException {
		Workbook wb = new HSSFWorkbook();

		Sheet sheet = wb.createSheet("termostato");
		for (int giorno = 0; giorno <= 7; giorno++) {
			Row row = sheet.createRow(giorno);
			for (int ora = -1; ora <= 23; ora++) {
				Cell cell = row.createCell(ora + 1);
				if (ora == -1) {
					// prima colonna
					cell.setCellValue(toGiorno(giorno));
				} else {
					if (giorno == 0) {
						// prima riga
						cell.setCellValue(toHour(ora));
					} else {
						// righe interne
						cell.setCellValue(toTemp(griglia[giorno - 1][ora]));
					}
				}
			}
		}

		String percorso = "src/main/resources/termostato.xls";
		FileOutputStream fileOut = new FileOutputStream(percorso);
		wb.write(fileOut);
		fileOut.close();

		nuovaRiga();
		System.out.println("TERMOSTATO ESPORTATO IN: " + percorso);
	}

	public void setTemperatura(int temp, int giorno, int dalle, int alle) {
		for (int ora = dalle; ora <= alle; ora++) {
			griglia[giorno - 1][ora] = temp;
		}
	}

	private void inizializza(int temp) {
		for (int giorno = 1; giorno <= 7; giorno++) {
			setTemperatura(temp, giorno, 0, 23);
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

	public int getTempMin() {
		return tempMin;
	}

	public int getTempMax() {
		return tempMax;
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
		for (int giorno = 0; giorno <= 7; giorno++) {
			for (int ora = -1; ora <= 23; ora++) {
				if (ora == -1) {
					// prima colonna
					System.out.print(toGiorno(giorno) + " | ");
				} else {
					if (giorno == 0) {
						// prima riga
						System.out.print(toHour(ora) + " | ");
					} else {
						// righe interne
						System.out.print(toTemp(griglia[giorno - 1][ora]) + " | ");
					}
				}
				if (ora == 23) {
					// rine riga
					nuovaRiga();
				}
			}
		}
	}

	private void nuovaRiga() {
		System.out.println();
	}

	private String toHour(int numero) {
		if (numero <= 9) {
			return " " + numero + "h";
		} else {
			return "" + numero + "h";
		}
	}

	private String toTemp(int numero) {
		if (numero <= 9) {
			return " " + numero + "°";
		} else {
			return "" + numero + "°";
		}
	}

	private String toGiorno(int giorno) {
		switch (giorno) {
		case 1:
			return "lun";
		case 2:
			return "mar";
		case 3:
			return "mer";
		case 4:
			return "gio";
		case 5:
			return "ven";
		case 6:
			return "sab";
		case 7:
			return "dom";
		default:
			return "   ";
		}
	}
}
