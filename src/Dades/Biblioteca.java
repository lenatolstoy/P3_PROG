package Dades;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Biblioteca {
	protected LlistaLlibres llibres;
	protected LlistaSocis socis;
	protected LlistaPrestecs prestecsActius;
	protected LlistaPrestecs prestecsInactius;
	protected LlistaReserves reserves;
	
	/**
	 * Constructor de la classe Biblioteca
	 */
	public Biblioteca( ) {
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
		} catch (FileNotFoundException e) { System.out.println("Fitxer de llibres no trobat: "+e.toString());}
		
		try {
			socis.leer();
		} catch (FileNotFoundException e) { System.out.println("Fitxer de socis no trobat: "+e.toString());}
		
		try {
			prestecsActius.llegirFitxer("prestecsactius.txt");
		} catch (FileNotFoundException e) { System.out.println("Fitxer de prestecs actius no trobat: "+e.toString());}
		
		try {
			prestecsInactius.llegirFitxer("prestecsinactius.txt");
		} catch (FileNotFoundException e) { System.out.println("Fitxer de prestecs inactius no trobat: "+e.toString());}
		
		try {
			reserves.LlegirFitxerReserves();
		} catch (FileNotFoundException e) { System.out.println("Fitxer de reserves actives no trobat: "+e.toString());}
		
	}
	
	
	/**
	 * Metode que ens permet sobreescriure tots els fitxers de dades
	 */
	public void guardar() {
		try {
			llibres.escriureFitxer();
		} catch (IOException e) { System.out.println("Fitxer de llibres no trobat: "+e.toString());}
		
		try {
			socis.guardar();
		} catch (IOException e) { System.out.println("Fitxer de socis no trobat: "+e.toString());}
		
		try {
			prestecsActius.guardarFitxer("prestecsactius.txt");
		} catch (IOException e) { System.out.println("Fitxer de prestecs actius no trobat: "+e.toString());}
		
		try {
			prestecsInactius.guardarFitxer("prestecsinactius.txt");
		} catch (IOException e) { System.out.println("Fitxer de prestecs inactius no trobat: "+e.toString());}
		
		try {
			reserves.EscriureFitxerReserves();
		} catch (IOException e) { System.out.println("Fitxer de reserves actives no trobat: "+e.toString());}
		
	}
}
