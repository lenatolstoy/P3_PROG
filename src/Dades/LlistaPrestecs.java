/** 
 * Practica 3. Classe Llista_Prestecs.
 * 
 * 
 * @author Magdalena Tolstoy
 * 
 *
 */
package Dades;

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
	
	//Añadir prestamo de manera ordenada por --> nombre? fecha? --> llista dinàmica
	public void afegirPrestec(String id_llibre, String dni, String data_ini) {
		
	}
	
	//Passar
	public void fiPrestec(String id_llibre, String dni, LlistaPrestecs Inactius) {
		
	}
	
	public boolean enPrestec (String id_llibre, String data_ini, int num_dies) {
		
	}
	
	public void llegirFitxer(String nomFitxer) throws FileNotFoundException{
		
	}
	
	public void guardarFitxer(String nomFitxer) {
		
	}
	
	public Prestec retorna(String id_llibre, String dni) {
		
	}
	
}
