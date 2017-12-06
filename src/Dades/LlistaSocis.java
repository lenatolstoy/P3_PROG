package Dades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LlistaSocis {
	// variables
	private Soci[] lista;
	private int lineas;

	// constructor
	public LlistaSocis(int len) {
		lista = new Soci[len];
		lineas = 0;
	}

	public int getLineas() {
		return lineas;
	}

	@Override
	public String toString() {
		String r = "";
		for (int i = 0; i < lineas; i++) {
			r = r + lista[i] + "\n";
		}
		return r;
	}

	/** HACERLO POR ORDEN ALFABETICO + LISTA DINAMICA */
	public void afegeix(Soci nuevo) {
		if (lineas <= lista.length) {
			lista[lineas] = nuevo;
			lineas++;
		}
	}

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
			SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
			data_naixement = sdf.format(lista[i].getData_naixement());
			data_alta = sdf.format(lista[i].getData_alta());
			incidencias = String.valueOf(lista[i].getIncidencias());
			num_prestec = String.valueOf(lista[i].getNum_prestec());
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

	public void leer() throws FileNotFoundException {
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

			if (punts != null) {
				int p = Integer.parseInt(punts);
				NoEstudiant nuevo = new NoEstudiant(dni, nom, data_naixement, data_alta, incidencias, num_prestec, p);
				afegeix(nuevo);
			} else {
				Soci nuevo = new Soci(dni, nom, data_naixement, data_alta, incidencias, num_prestec);
				afegeix(nuevo);
			}

		}
		scan.close();
	}

	/** PTE UN METODO QUE RETORNE EL USUARIO -> CERCA DICOTOMICA */
	public Soci retorna(String dni) {
		if (existeix(dni)) {
			for (int i = 0; i < lineas; i++) {
				if (lista[i].getDNI().equals(dni)) return new Soci(lista[i].getDNI(), lista[i].getNom(), lista[i].getData_naixement());
			}
		}
		return new Soci("", "", new Date(0));
	}

	/** MEJOR CERCA DICOTOMICA ? */
	public boolean existeix(String dni) {
		boolean r = false;
		for (int i = 0; i < lineas; i++) {
			if (lista[i].getDNI().equals(dni)) {
				return r = true;
			}
		}
		return r;
	}

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

	public void darPuntos(String dni) {
		int i = 0;
		do {
			if (lista[i] instanceof NoEstudiant && lista[i].getDNI().equals(dni)) {
				((NoEstudiant) lista[i]).puntsUP();
			}
			i++;
		} while (i < lineas);
	}

}
