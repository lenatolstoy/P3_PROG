package Dades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LlistaSocis {
	// variables
	private Soci[] lista;
	private int lineas;

	// constructor
	/**
	 * Constructor que crea una lista
	 * @param len
	 */
	public LlistaSocis(int len) {
		lista = new Soci[len];
		lineas = 0;
	}
	
	/**
	 * Retorna el numero de socis de la lista
	 * @return
	 */
	public int getLineas() {
		return lineas;
	}

	@Override
	/**
	 * Metode ToString
	 * @return string
	 */
	public String toString() {
		String r = "";
		for (int i = 0; i < lineas; i++) {
			r = r + lista[i] + "\n";
		}
		return r;
	}


	/**
	 * Afegeir un soci a la llista
	 * @param nuevo
	 */
	public void afegeix(Soci nuevo) {
		if (lineas <= lista.length) {
			lista[lineas] = nuevo;
			lineas++;
		}
	}
	
	/**
	 * Guarda els socis al un fitxer
	 * @throws IOException
	 */
	public void guardar() throws IOException {
		BufferedWriter escriure = new BufferedWriter(new FileWriter("user.txt"));
		String dni;
		String nom;
		String data_naixement;
		String data_alta;
		String incidencias;
		String num_prestec;
		String punts;
		for (int i = 0; i < lineas; i++) {
			dni = lista[i].getDNI();
			nom = lista[i].getNom();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			data_naixement = sdf.format(lista[i].getData_naixement());
			data_alta = sdf.format(lista[i].getData_alta());
			incidencias = String.valueOf(lista[i].getIncidencias());
			num_prestec = String.valueOf(lista[i].getNum_prestecs());
			escriure.write(dni);
			escriure.write("*");
			escriure.write(nom);
			escriure.write("*");
			escriure.write(data_naixement);
			escriure.write("*");
			escriure.write(data_alta);
			escriure.write("*");
			escriure.write(incidencias);
			escriure.write("*");
			escriure.write(num_prestec);
			escriure.write("*");
			if (lista[i] instanceof NoEstudiant) {
				punts = String.valueOf(((NoEstudiant) lista[i]).getPunts());
				escriure.write(punts);
				escriure.write("*");
			}
			if (i != lineas - 1) {
				escriure.newLine();
			}

		}
		escriure.close();
	}

		/**
		 * llegeix un fitxer i omple una llista de socis
		 * @throws FileNotFoundException
		 */
	public void leer() throws FileNotFoundException {
<<<<<<< HEAD
		Scanner scan = new Scanner(new File("user.txt"));
		String dni;
		String nom;
		Date data_naixement;
		Date data_alta;
		int incidencias;
		int num_prestec;
		String punts = null;
		scan.useDelimiter("\\*");
		while (scan.hasNext()) {
			dni = scan.next();
			dni = dni.replaceAll("[\n\r]", "");
			nom = scan.next();
			data_naixement = new Date(scan.next());
			data_alta = new Date(scan.next());
			incidencias = Integer.parseInt(scan.next());
			num_prestec = Integer.parseInt(scan.next());
			if (scan.hasNextInt()) {
				punts = scan.next();
			} else {
				punts = null;
			}
=======
		File f = new File("user.txt");
		if (f.exists() && !f.isDirectory()) {
			Scanner scan = new Scanner(f);
			String dni;
			String nom;
			String data_naixement;
			String data_alta;
			int incidencias;
			int num_prestec;
			String punts = null;
			scan.useDelimiter("\\*");
			while (scan.hasNext()) {
				dni = scan.next();
				dni = dni.replaceAll("[\n\r]", "");
				nom = scan.next();
				data_naixement = scan.next();
				Date data_n = null;
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				try {
					data_n = format.parse(data_naixement);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				data_alta = scan.next();
				Date data_a = null;
				try {
					data_a = format.parse(data_alta);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				incidencias = Integer.parseInt(scan.next());
				num_prestec = Integer.parseInt(scan.next());
				if (scan.hasNextInt()) {
					punts = scan.next();
				} else {
					punts = null;
				}

				if (punts != null) {
					int p = Integer.parseInt(punts);
					NoEstudiant nuevo = new NoEstudiant(dni, nom, data_n, data_a, incidencias, num_prestec,
							p);
					afegeix(nuevo);

				} else {
					Soci nuevo = new Soci(dni, nom, data_n, data_a, incidencias, num_prestec);
					afegeix(nuevo);
				}
>>>>>>> 6a4fa2da781d379ffb2c9a3ee0d91a82d5c447d2

			if (punts != null) {
				int p = Integer.parseInt(punts);
				NoEstudiant nuevo = new NoEstudiant(dni, nom, data_naixement, data_alta, incidencias, num_prestec, p);
				afegeix(nuevo);
			} else {
				Soci nuevo = new Soci(dni, nom, data_naixement, data_alta, incidencias, num_prestec);
				afegeix(nuevo);
			}

		}
<<<<<<< HEAD
		scan.close();
=======

>>>>>>> 6a4fa2da781d379ffb2c9a3ee0d91a82d5c447d2
	}
	
	/**
	 * retorna un soci a partir de un dni
	 * @param dni
	 * @return soci
	 */
	public Soci retornaSoci(String dni) {
		for (int i = 0; i < lineas; i++) {
			if (lista[i].getDNI().equals(dni)) {
				return lista[i];
			}
		}
		return null;
	}
	
	/**
	 * retorna la llista de socis
	 * @return lista
	 */
	public Soci[] getLista() {
		return lista;
	}


/**
 * compueba si un socio existe en la lista a partir de su dni
 * @param dni
 * @return
 */
	public boolean existeix(String dni) {
		boolean r = false;
		for (int i = 0; i < lineas; i++) {
			if (lista[i].getDNI().equals(dni)) {
				return r = true;
			}
		}
		return r;
	}

	/**
	 * Elimina un socio de la lista a apartir del dni
	 * @param dni
	 */
	public void eliminar(String dni) {
		int i = 0;
		int posicion;
		boolean trobat = false;
		do {
			if (lista[i].getDNI().equals(dni)) {
				posicion = i;
				trobat = true;
				int j;
				for (j = posicion; j < lineas - 1; j++) {
					lista[j] = lista[j + 1];
				}
				lista[j] = null;
				lineas--;
			}
			i++;
		} while (i < lineas && !trobat);
	}


}
