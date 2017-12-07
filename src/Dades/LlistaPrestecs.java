/** 
 * Practica 3. Classe Llista_Prestecs.
 * 
 * 
 * @author Magdalena Tolstoy
 * 
 *
 */
package Dades;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LlistaPrestecs {
	private int nprestecs;
	private Prestec[] llista;
	
	/**
	 * 
	 * @param mida
	 */
	public LlistaPrestecs(int mida) {
		nprestecs = 0;
		llista = new Prestec[mida];
	}
	
	public int getNprestecs() {
		return nprestecs;
	}

	public Prestec[] getLlista() {
		return llista;
	}
	
	public String toString() {
		return null;
		
	}
	
	//Añadir prestamo de manera ordenada por --> nombre? fecha? --> llista dinàmica
	public void afegirPrestec(String id_llibre, String dni, String data_ini) {
		
	}
	
	public void afegirPrestec(Prestec p) {
		
	}
	
	
	//Passar
	public void fiPrestec(String id_llibre, String dni, LlistaPrestecs Inactius) {
		
	}
	
	
	public boolean enPrestec(String id_llibre, String data_ini, int num_dies) {
		
	}
	
	public boolean enPrestec(String id_llibre, String data_ini) {
		
	}
	
	public LlistaPrestecs enPrestecAvui() {
		LlistaPrestecs prestecsActuals = new LlistaPrestecs(10);
		for (int i=0; i<nprestecs; i++) {
			if (llista[i].getData_ini().before(new Date()))
				prestecsActuals.afegirPrestec(llista[i]);
		}
		return prestecsActuals;
	}
	
	public void eliminaPrestec(String dni) {
		
	}
	
	public Prestec retornaPrestec(String id_llibre, String dni) {
		for (int i = 0; i < nprestecs; i++) {
			if (llista[i].getId_llibre().equals(id_llibre) && llista[i].getDni().equals(dni))
				return llista[i];
		}
		return null;
	}
	
	public LlistaPrestecs prestecsUsuari(String dni) {
		
	}
	
	public void llegirFitxer(String nomFitxer) throws FileNotFoundException{
		
	}
	
	public void guardarFitxer(String nomFitxer) {
		
	}
}
