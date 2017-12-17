/**
 * Practica 3. Classe Biblioteca.
 *
 *
 * @author Ivan Grima
 * @author Cristina Llort
 * @author Magdalena Tolstoy
 * @author Antonio Torres
 *
 */

package Personal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	 * @param dies_prestec
	 *            enter amb els dies de prestec
	 */
	public void afegirLlibreCientific(String titol, String[] autors, String tema, int num_edicio, int any_edicio,
			int dies_prestec) {
		llibres.afegirLlibreCientific(new Llibre_Cientific(titol, autors, tema, num_edicio, any_edicio, dies_prestec));
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
	 * @return
	 */
	public boolean afegirTema(String tema) {
		return Llibre.afegirTematica(tema);
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
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
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

	/**
	 * Metode que retorna tots els llibres en prestec amb les dades del soci que el
	 * te
	 *
	 * @return String amb els llibres que es troben en prestec
	 */
	public String llibresEnPrestec() {
		LlistaPrestecs prestecsActuals = prestecsActius.enPrestecAvui();
		String resultat = "";
		for (int i = 0; i < prestecsActuals.getNprestecs(); i++) {
			String dni = prestecsActuals.getLlista()[i].getId_llibre();
			String codi = prestecsActuals.getLlista()[i].getId_llibre();
			resultat = resultat + i + 1 + ".\tCodi del llibre: " + codi + "\n\tTitol del llibre: "
					+ llibres.retornaLlibre(codi).getTitol() + "\n\t Dades de l'usari que te el llibre: "
					+ socis.retornaSoci(dni).toString();
			if (prestecsActuals.getLlista()[i].enTermini(llibres.retornaLlibre(codi), Calendar.getInstance().getTime()))
				resultat = resultat + "\n\tEN TERMINI\n\n";
			else
				resultat = resultat + "\n\tFORA DE TERMINI\n\n";
		}
		return resultat;
	}

	/**
	 * Metode que retorna el tema del qual hi ha mes prestecs actius
	 *
	 * @return String amb el tema
	 */
	public String temaAmbMesPrestecs() {
		String temaMax = "";
		int ntemaMax = 0;
		String[] temes = llibres.getLlistallibres()[0].getTemes();
		for (int i = 0; i < temes.length; i++) {
			int j = comptaTema(temes[i]);
			if (j > ntemaMax)
				temaMax = temes[i];
				ntemaMax = j;
		}
		return temaMax;
	}

	/**
	 * Metode que compta quants prestecs actius hi ha d'un tema en concret
	 *
	 * @param tema
	 *            tema del qual es vol saber el nombre de prestecs
	 * @return int amb el nombre de prestecs
	 */
	private int comptaTema(String tema) {
		int compt = 0;
		for (int i = 0; i < prestecsActius.getNprestecs(); i++) {
			if (llibres.esDelTema(prestecsActius.getLlista()[i].getId_llibre(), tema))
				compt++;
		}
		return compt;
	}

	public String consultaLlibres() {
		return llibres.toString();
	}

	public String consultaSocis() {
		return socis.toString();
	}

	public String consultaPrestecsActius() {
		return prestecsActius.toString();
	}

	public String consultaPrestecsInactius() {
		return prestecsInactius.toString();
	}

	/*public String consultaReserves() throws IOException {
		reserves.EstatReserves();
		return reserves.toString();
	}*/

}
