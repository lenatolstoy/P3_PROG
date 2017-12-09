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

	/**
	 * Metode que retorna tots els llibres en prestec amb les dades del soci que el
	 * te
	 * 
	 * @return
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
		String[] temes = llibres.getLlistallibres()[0].getTemes(); // Pte controlar el tamaño de la array pte afegir
																	// tema de sr tono
		for (int i = 0; i < temes.length; i++) {
			int j = comptaTema(temes[i]);
			if (j > ntemaMax)
				temaMax = temes[i];
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

	public String consultaReserves() {
		return reserves.toString();
	}

}
