package Dades;

import java.util.Date;

public class Llibre_Cientific extends Llibre {

	private int dies_prestec;

	public Llibre_Cientific(String titol, String[] autors, String tema, int num_edicio, int any_edicio) {
		super(titol, autors, tema, num_edicio, any_edicio);
		dies_prestec = 0;
	}

	/**
	 * Getters Retorna el nombre de dies maxim en el qual es pot tenir en prestec el llibre cientific
	 * 
	 * @return dies_prestec
	 */

	public int getDiesPrestec() {
		return dies_prestec;
	}

	/**
	 * Setters
	 * 
	 * @param dies_reserva
	 *            (int) el nou nombre de dies que reservarem el llibre
	 */

	public void setDiesPrestec(int dies_reserva) {
		this.dies_prestec = dies_reserva;
	}
}
