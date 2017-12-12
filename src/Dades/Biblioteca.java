package Dades;

import java.io.FileNotFoundException;
import java.io.IOException;

import Exceptions.LlibreNoDisponible;
import Exceptions.ReservesDiaSuperior30;

public abstract class Biblioteca {
	protected LlistaLlibres llibres;
	protected LlistaSocis socis;
	protected LlistaPrestecs prestecsActius;
	protected LlistaPrestecs prestecsInactius;
	protected LlistaReserves reserves;

	/**
	 * Constructor de la classe Biblioteca
	 */
	public Biblioteca() {
		llibres = new LlistaLlibres(20);
		socis = new LlistaSocis(20);
		prestecsActius = new LlistaPrestecs(20);
		prestecsInactius = new LlistaPrestecs(20);
		reserves = new LlistaReserves(20);
	}

	/**
	 * Getter de la llista de llibres
	 * 
	 * @return LlistaLlibres
	 */
	public LlistaLlibres getLlibres() {
		return llibres;
	}

	/**
	 * Getter de la llista de socis
	 * 
	 * @return LlistaSocis
	 */
	public LlistaSocis getSocis() {
		return socis;
	}

	/**
	 * Getter de la llista de prestecs que estiguin actius
	 * 
	 * @return LlistaPrestecs amb els prestecs actius
	 */
	public LlistaPrestecs getPrestecsActius() {
		return prestecsActius;
	}

	/**
	 * Getter de la llista de prestecs que ja no estiguin actius
	 * 
	 * @return LlistaPrestecs amb els prestecs inactius
	 */
	public LlistaPrestecs getPrestecsInactius() {
		return prestecsInactius;
	}

	/**
	 * Getter de les reserves actives
	 * 
	 * @return LlistaReserves amb les reserves actives
	 */
	public LlistaReserves getReserves() {
		return reserves;
	}

	/**
	 * Metode que ens permet llegir els fitxers de dades
	 */
	public void llegir() {
		try {
			llibres.llegirFitxer();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			socis.leer();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			prestecsActius.llegirFitxer("prestecsactius.dat");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			prestecsInactius.llegirFitxer("prestecsinactius.dat");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			reserves.LlegirFitxerReserves();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (LlibreNoDisponible e) {
			e.printStackTrace();
		} catch (ReservesDiaSuperior30 e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Metode que ens permet sobreescriure tots els fitxers de dades
	 */
	public void guardar() {
		if (llibres.getNumllibres() > 0) {
			try {
				llibres.escriureFitxer();
			} catch (IOException e) {
				System.out.println("Fitxer de llibres no trobat: " + e.toString());
			}
		}

		if (socis.getLineas() > 0) {
			try {
				socis.guardar();
			} catch (IOException e) {
				System.out.println("Fitxer de socis no trobat: " + e.toString());
			}
		}

		try {
			prestecsActius.guardarFitxer("prestecsactius.dat");
		} catch (IOException e) {
			System.out.println("Fitxer de prestecs actius no trobat: " + e.toString());
		}

		try {
			prestecsInactius.guardarFitxer("prestecsinactius.dat");
		} catch (IOException e) {
			System.out.println("Fitxer de prestecs inactius no trobat: " + e.toString());
		}

		if (reserves.getNumreserves() > 0) {
			try {
				reserves.EscriureFitxerReserves();
			} catch (IOException e) {
				System.out.println("Fitxer de reserves actives no trobat: " + e.toString());
			}
		}

	}
}
