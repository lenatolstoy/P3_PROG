package Personal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Dades.*;

public class BibliotecaPersonal extends Biblioteca {

	/**
	 * Constructor Incicialitza totes les llistes a mida 20
	 */
	public BibliotecaPersonal() {
		super();
	}

	/**
	 * Metode que afegeix un llibre a la llista dels llibres
	 * 
	 * @param titol
	 *            String amb el titol del llibre
	 * @param autors
	 *            array de Strings amb els autors del llibre
	 * @param tema
	 *            String del tema
	 * @param num_edicio
	 *            enter amb el numero d'edicio del llibre
	 * @param any_edicio
	 *            enter amb l'any d'edicio del llibre
	 */
	public void afegirLlibre(String titol, String[] autors, String tema, int num_edicio, int any_edicio) {
		llibres.afegirLlibre(new Llibre(titol, autors, tema, num_edicio, any_edicio));
	}

	/**
	 * Metode que permet eliminar un llibre de la biblioteca, com que volem manteir
	 * l'historial dels llibres simplement els posem com a inactius
	 * 
	 * @param id_llibre
	 *            String que indica l'identificador del llibre
	 */
	public void eliminaLlibre(String id_llibre) {
		llibres.retornaLlibre(id_llibre).setActiu(false);
	}

	/**
	 * Metode que ens permet afegir un nou tema
	 * 
	 * @param tema
	 *            tema a afegir
	 */
	public void afegirTema(String tema) {
		Llibre.afegirTematica(tema);
	}

	/**
	 * Afegim soci a la llista de socis
	 * 
	 * @param DNI
	 *            DNI del soci a afegir
	 * @param nom
	 *            Nom del soci a afegir
	 * @param data_naixement
	 *            Data de naixement del soci a fegir
	 */
	public void afegirSoci(String DNI, String nom, String data_naixement) {
		Date dnaixement = null;
		// Passem el string a tipus data
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try {
			dnaixement = format.parse(data_naixement);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		socis.afegeix(new Soci(DNI, nom, dnaixement));
	}

	/**
	 * Metode que permet donar de baixa un soci sense mantenir les seves dades en
	 * historic
	 * 
	 * @param dni
	 *            dni del soci que volem eliminar
	 */
	public void baixaSoci(String dni) {
		socis.eliminar(dni);
		prestecsActius.eliminaPrestec(dni);
		prestecsInactius.eliminaPrestec(dni);
		reserves.EliminarReserva(dni);
	}

}
