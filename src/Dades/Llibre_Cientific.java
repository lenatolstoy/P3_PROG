package Dades;

import java.util.Date;

public class Llibre_Cientific extends Llibres {

	private int dies_reserva;
	
	public Llibre_Cientific(String titol, String[] autors, String tema, int num_edicio, Date any_edicio, boolean disponible) {
		super(titol, autors, tema, num_edicio, any_edicio, disponible);
		dies_reserva = 0;
	}
	
	/**
	 * Getters
	*/	
	
	/**
	 * Retorna el nombre de dies que s'ha reservat el llibre cientific
	 * @return dies_reserva
	 */
	
	public int getDiesReserva() {
		return dies_reserva;
	}
	
	/**
	 * Setters
	*/	
	
	/**
	 * @param dies_reserva (int) el nou nombre de dies que reservarem el llibre
	 */
	
	public void setDiesReseva(int dies_reserva) {
		this.dies_reserva = dies_reserva;
	}
}
