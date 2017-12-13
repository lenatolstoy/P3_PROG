/** 
 * Practica 3. Classe NoEstudiant.
 * 
 * 
 * @author Ivan Grima
 * @author Cristina Llort
 * @author Magdalena Tolstoy
 * @author Antonio Torres
 *
 */

package Dades;
import java.util.Date;

public class NoEstudiant extends Soci {
	
	//variables
	private int punts;

	//constructor
	public NoEstudiant(String DNI, String nom, Date data_naixement) {
		super(DNI, nom, data_naixement);
		punts = 0;
	}
	
	public NoEstudiant(String DNI, String nom, Date data_naixement, Date data_alta, int incidencias, int num_prestec, int punts) {
		super(DNI, nom, data_naixement, data_alta, incidencias, num_prestec);
		this.punts = punts;
	}

	//Getter
	public int getPunts() {
		return punts;
	}

		
	//Metodes
	@Override
	public String toString() {
		return "NoEstudiant [ "+super.toString() + " punts=" + punts +"]";
	}
	
	public void puntsUP(){
		punts=punts+10;
	}
	
	
}