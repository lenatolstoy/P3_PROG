/** 
 * Practica 3. Classe Llibre_Cientific.
 * 
 * 
 * @author Ivan Grima
 * @author Cristina Llort
 * @author Magdalena Tolstoy
 * @author Antonio Torres
 *
 */

package Dades;


public class Llibre_Cientific extends Llibre {

	private int dies_prestec;

	public Llibre_Cientific(String titol, String[] autors, String tema, int num_edicio, int any_edicio, int dies_prestec) {
		super(titol, autors, tema, num_edicio, any_edicio);
		this.dies_prestec = dies_prestec;
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
	
	/**
	 * toString
	 */
	public String toString() {
		return (super.toString() + "Dies de prestec: " + this.dies_prestec+ "\n");
	}
	
	/**
	 * Mètode que duplica la instància del llibre cientific
	 * 
	 * @return duplicat
	 */

	/*
	 * Creem un nou objecte perque si nomes copiem la referencia si en modifiquem un
	 * l'altre tambe es modificara perque son el mateix.
	 */
	public Llibre_Cientific Duplicat() {
		Llibre_Cientific aux = new Llibre_Cientific(this.titol, this.autors, this.tema, this.num_edicio, this.any_edicio, this.dies_prestec);
		return (aux);
	}
}
