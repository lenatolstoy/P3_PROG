/** 
 * Practica 3. Classe LlistaPrestecs.
 * 
 * 
 * @author Ivan Grima
 * @author Cristina Llort
 * @author Magdalena Tolstoy
 * @author Antonio Torres
 *
 */

package Dades;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.io.*;

public class LlistaPrestecs implements Serializable {
	private static final long serialVersionUID = 1L;
	private int nprestecs;
	private Prestec[] llista;

	/**
	 * Constructor
	 * 
	 * @param mida
	 *            tamany que volem que tingui l'array de prestecs
	 */
	public LlistaPrestecs(int mida) {
		nprestecs = 0;
		llista = new Prestec[mida];
	}

	/* GETTERS, SETTERS, TOSTRING */

	/**
	 * Getter
	 * 
	 * @return nombre de prestecs
	 */
	public int getNprestecs() {
		return nprestecs;
	}

	/**
	 * Getter
	 * 
	 * @return retorna l'array amb tots els prestecs
	 */
	public Prestec[] getLlista() {
		return llista;
	}

	/**
	 * Metode toString
	 * 
	 * @return String amb tota la llista
	 */
	public String toString() {
		String resultat = "Nombre de prestecs: " + nprestecs + "\n";
		for (int i = 0; i < nprestecs; i++) {
			resultat = resultat + i + 1 + ". " + llista[i].toString() + "\n";
		}
		return resultat;
	}

	/**
	 * Metode que ens fa un get d'un prestec en especific donada la posicio a la
	 * llista
	 * 
	 * @param pos
	 *            posicio a la llista
	 * @return el Prestec que coincideix amb el id i el dni, si cap coincideix
	 *         retorna null
	 */
	public Prestec retornaPrestec(int pos) {
		if (pos >= 0 && pos < nprestecs)
			return llista[pos];
		return null;
	}

	/* METODES ENUNCIAT -> PUBLICS */

	/**
	 * Metode que permet afegir un prestec de manera ordenada pel DNI de qui fa el
	 * prestec
	 * 
	 * @param p
	 *            Prestec nou a afegir
	 */
	public void afegirPrestec(Prestec p) {
		// Si la llista es troba plena augmentem la mida de la llista
		if (nprestecs >= llista.length)
			this.augmentaLlista();
		int i;
		for (i = nprestecs - 1; i >= 0 && ordreAlfabetic(llista[i].getDni(), p.getDni()); i--) {
			// Desplacem els elements una posicio a la dreta fins que trobem la posicio on
			// ha d'anar l'element a inserir
			llista[i + 1] = llista[i];
		}
		// Inserim l'element a la posició adequada
		llista[i + 1] = p.copia();
		nprestecs++;
	}

	/**
	 * Metode que mou un prestec a la llista de prestecs inactius i l'elimina de la
	 * llista de prestecs actius
	 * 
	 * @param id_llibre
	 *            codi del llibre en prestec
	 * @param dni
	 *            codi del soci que va fer el prestec
	 * @param Inactius
	 *            llista amb els prestecs ja finalitzats
	 * @return false si l'eliminacio no s'ha pogut fer perque no s'ha trobat el
	 *         prestec, i true si s'ha pogut fer correctament
	 */
	public boolean fiPrestec(String id_llibre, String dni, LlistaPrestecs inactius) {
		int pos = posicio(id_llibre, dni);
		if (pos == -1)
			return false;
		// Afegim el prestec a inactius
		inactius.afegirPrestec(retornaPrestec(posicio(id_llibre, dni)));
		// Eliminem el prestec d'actius
		eliminaPrestec(pos);
		return true;
	}

	/**
	 * Metode que elimina tots els prestecs d'un soci
	 * 
	 * @param dni
	 *            dni del soci del qual volem eliminar els prestecs
	 * @return true si s'executa el codi amb exit, o false si no es troba el dni i
	 *         el llibre
	 */
	public boolean eliminaPrestec(String dni) {
		int pos = posicio(dni);
		if (pos == -1) {
			return false;
		} else {
			while (pos < nprestecs && dni.equals(llista[pos].getDni())) {
				eliminaPrestec(pos);
				pos++;
			}
			return true;
		}
	}

	/**
	 * Metode que ens indica si hi ha algun prestec d'un llibre concret en un
	 * periode
	 * 
	 * @param id_llibre
	 *            codi del llibre
	 * @param data
	 *            data d'inici del prestec que es vol fer
	 * @param num_dies
	 *            nombre de dies maxim que es pot fer el prestec delllibre (15 si el
	 *            llibre no es cientific)
	 * @return false si el llibre no es troba en prestec i true si s'hi troba
	 */
	public boolean enPrestec(String id_llibre, String data, int num_dies) {
		Date dat = null;
		// Passem el String a tipus Data
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dat = format.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(dat);

		// Calculem la data minima en la que no es pot haver fet cap prestec
		// que es *num_dies* dies abans del dia que volem fer el nostre prestec
		cal.add(Calendar.DATE, -num_dies);
		Date data_min = cal.getTime();

		// Calculem la data maxima en la que no es pot haver fet cap prestec
		// que es *num_dies* dies despres del dia que volem fer el nostre prestec
		cal.add(Calendar.DATE, num_dies * 2);
		Date data_max = cal.getTime();

		// Busquem si hi ha algun prestec que tingui la data d'inici entre aquests
		// valors
		for (int i = 0; i < nprestecs; i++) {
			if (llista[i].getId_llibre().equals(id_llibre)) {
				Date d = llista[i].getData_ini();
				if ((d.before(data_max) && d.after(data_min)) || d.equals(data_max) || d.equals(data_min))
					return true;
			}
		}
		return false;
	}

	/**
	 * Metode que ens indica si hi ha algun prestec d'un llibre concret en un dia
	 * especific
	 * 
	 * @param id_llibre
	 *            codi del llibre
	 * @param data
	 *            data de la reserva que es vol fer
	 * @return false si el llibre no es troba en prestec i true si s'hi troba
	 */
	public boolean enPrestec(String id_llibre, String data) {
		return enPrestec(id_llibre, data, 0);
	}

	/**
	 * Metode que ens diu tots els prestecs que hi ha actius avui
	 * 
	 * @return LlistaPrestecs amb tots els prestecs que hi ha actualment
	 */
	public LlistaPrestecs enPrestecAvui() {
		LlistaPrestecs prestecsActuals = new LlistaPrestecs(10);
		for (int i = 0; i < nprestecs; i++) {
			if (llista[i].getData_ini().before(Calendar.getInstance().getTime()) && llista[i].getData_fi() == null)
				prestecsActuals.afegirPrestec(llista[i]);
		}
		return prestecsActuals;
	}

	/**
	 * Metode que retorna la posicio d'un prestec a una llista
	 * 
	 * @param id_llibre
	 *            codi del llibre
	 * @param dni
	 *            dni del soci que va fer el prestec
	 * @return posicio a la llista o -1 si no s'ha trobat
	 */
	public int posicio(String id_llibre, String dni) {
		// Mirem la posicio en la qual es troben els prestecs d'aquest soci
		int pos = posicio(dni);
		if (pos != -1) {
			// A partir de la posicio mirem si algun dels seus prestecs correspon amb el
			// llibre
			while (pos < nprestecs && dni.equals(llista[pos].getDni())) {
				// Si trobem el llibre retornem la posicio en la qual es troba
				if (id_llibre.equals(llista[pos].getId_llibre()))
					return pos;
				pos++;
			}
		}
		// Si no trobem el soci o no trobem el llibre retornem -1
		return -1;
	}

	/**
	 * Metode que ens retorna tots els prestecs d'un usuari en especific
	 * 
	 * @param dni
	 *            dni de l'usuari el qual volem els prestecs
	 * @return
	 */
	public LlistaPrestecs prestecsUsuari(String dni) {
		LlistaPrestecs p = new LlistaPrestecs(10);
		int pos = posicio(dni);
		// Si no trobem el dni retornem null
		if (pos != -1) {
			while (pos < nprestecs && llista[pos].getDni().equals(dni)) {
				p.afegirPrestec(llista[pos]);
				pos++;
			}
			return p;
		}
		return null;
	}

	/* METODES AUXILIARS -> PRIVATS */

	/**
	 * Metode que ens permet treballar amb llistes dinamiques
	 */
	private void augmentaLlista() {
		Prestec[] aux = new Prestec[this.nprestecs + 10];
		for (int i = 0; i < this.nprestecs; i++) {
			aux[i] = this.llista[i];
		}
		llista = aux;
	}

	/**
	 * Metode que retorna la posicio del primer prestec d'un soci a la llista
	 * 
	 * @param id_llibre
	 *            codi del llibre
	 * @param dni
	 *            dni del soci que va fer el prestec
	 * @return posicio a la llista o -1 si no s'ha trobat
	 */
	private int posicio(String dni) {
		for (int i = 0; i < nprestecs; i++) {
			if (dni.equals(llista[i].getDni()))
				return i;
		}
		return -1;
	}

	/**
	 * Metode que elimina un unic prestec de la llista donada la seva posicio
	 * 
	 * @param i
	 *            posicio del prestec que es vol eliminar
	 * @return false si el prestec no s'ha pogut fer i true si s'ha realitzat amb
	 *         exit
	 */
	private boolean eliminaPrestec(int i) {
		if (i >= nprestecs || i < 0)
			return false;
		else {
			nprestecs--;
			while (i < nprestecs) {
				llista[i] = llista[i + 1];
				i++;
			}
			return true;
		}
	}

	/**
	 * Metode que ens indica si s1 > s2. Es a dir que, si ordenem alfabeticament les
	 * Strings de manera ascendent, el metode ens marca si s1 hauria de ser
	 * posterior a s2
	 * 
	 * @param s1
	 *            primera String a comparar
	 * @param s2
	 *            segona String a comparar
	 * @return boolean que indica si s1 es posterior a s2
	 */
	private boolean ordreAlfabetic(String s1, String s2) {
		int i;
		// Compararem caracter a caracter mentres els caracters siguin iguals
		for (i = 0; i < s1.length() && i < s2.length(); i++) {
			// Si ens trobem amb que un caracter de s1 es major a s2, llavors s1 es
			// posterior en l'odre
			if (s1.charAt(i) > s2.charAt(i))
				return true;
			// Si ens trobem amb que un caracter de s1 es menor a s2, llavors s2 es
			// posterior en l'odre
			if (s1.charAt(i) < s2.charAt(i))
				return false;
		}
		// Si arribem al final d'un dels dos Strings hem de mirar de quin dels dos hem
		// arribat al final, ja que el mes curt sera l'anterior i el mes llarg el
		// posterior
		if (i == s1.length())
			return false;
		else
			return true;
	}

	/* METODES FITXERS */
	/**
	 * Metode que permet llegir un fitxer binari amb els prestecs i emmagatzemar les
	 * d ades en una LlistaPrestecs
	 * 
	 * @param fitxer
	 *            nom del fitxer que volem llegir
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void llegirFitxer(String fitxer) throws IOException, ClassNotFoundException {
		File fitx = new File(fitxer);
		if (fitx.exists() && !fitx.isDirectory()) {
			ObjectInputStream f = new ObjectInputStream(new FileInputStream(fitxer));

			LlistaPrestecs aux = ((LlistaPrestecs) f.readObject());
			llista = aux.getLlista();
			nprestecs = aux.getNprestecs();
			f.close();
		}
	}

	/**
	 * Metode que permet emmagatzemar les dades d'una LlistaPrestecs en un fitxer
	 * binari
	 * 
	 * @param fitxer
	 *            titol del fitxer on volem emmagatzemar les dades
	 * @throws IOException
	 */
	public void guardarFitxer(String fitxer) throws IOException {
		if (nprestecs > 0) {
			ObjectOutputStream f = new ObjectOutputStream(new FileOutputStream(fitxer));
			f.writeObject(this);
			f.close();
		}
	}
}
