package it.ial.termostato;

public class TermostatoUtil {

	public static String toOraAsString(int ora) {
		if (ora < 10) {
			return " " + ora + "h";
		} else {
			return ora + "h";
		}
	}
}
