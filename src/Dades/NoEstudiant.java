package Dades;
import java.util.Date;

public class NoEstudiant extends Soci {
	
	//variables
	private int punts;

	//constructor
	/**
	 * constructor
	 * @param DNI
	 * @param nom
	 * @param data_naixement
	 */
	public NoEstudiant(String DNI, String nom, Date data_naixement) {
		super(DNI, nom, data_naixement);
		punts = 0;
	}
	/**
	 * constructor 2
	 * @param DNI
	 * @param nom
	 * @param data_naixement
	 * @param data_alta
	 * @param incidencias
	 * @param num_prestec
	 * @param punts
	 */
	public NoEstudiant(String DNI, String nom, Date data_naixement, Date data_alta, int incidencias, int num_prestec, int punts) {
		super(DNI, nom, data_naixement, data_alta, incidencias, num_prestec);
		this.punts = punts;
	}

	//Getter
	/**
	 * retorna punts
	 * @return punts
	 */
	public int getPunts() {
		return punts;
	}

		
	//Metodes
	@Override
	/**
	 * Metodo toString
	 * @return String
	 */
	public String toString() {
		return "NoEstudiant [ "+super.toString() + " punts=" + punts +"]";
	}
	/**
	 * modifica punts +10
	 */
	public void puntsUP(){
		punts=punts+10;
	}
	
	
}