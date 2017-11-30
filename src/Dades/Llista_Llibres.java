package Dades;

import java.io.IOException;

import Exceptions.LlibreNoDisponible;
import Exceptions.ReservesDiaSuperior30;

public class Llista_Llibres {
	
	//Atributs
	private int numllibres;
	private Llibres[] llistallibres;
	
	/**
	 * Constructor
	 * @param numllibresmax enter que ens dóna la longitud màxima del vector de llibres
	 */
	public Llista_Llibres(int numllibresmax){
		
		llistallibres = new Llibres[numllibresmax];
		numllibres = 0;
	}
	
	
	//toString
	/**
	* Mètode per imprimir la llista de reserves
	* @return aux 
	*/
		public String toString(){
			String aux="";
			aux = aux +"LLIBRE: Número de llibres = "+numrllibres+"\n\n\n";
			for(int i=0; i<numllibres; i++){
				aux = aux +llistallibres[i]+ "\n";
			}
			return (aux);
		}
		
		
		//Mètodes
		
		/**
		 * Mètode per comprovar l'ordre dels codis (alfabèticament)
		 * @param codi del tipus String
		 * @return pos ens retorna la posició on ha d'estar el llibre a la llista perquè estigui ordenat
		 */
		public int OrdreCodis (String codi){
			
			boolean trobat = false;
			int pos = 0;
			int i = 0;
			//Volem afegir el llibre a la llista ordenada per codi
			//Anem recorrent la llista i busquem la posició on ha d'anar
			//Parem quan trobem la posició o arribem al final
			
			while(i<numllibres && !trobat){
				
				if (){
					
					trobat=true;
				}
				
				i++;
			}
			
			
			return pos;
		}
		
		
		
		/**
		 * Mètode per afegir un llibre a la biblioteca
		 * @param llibre del tipus Llibres que ens donarà la informació del llibre que es vol afegir
		 */
		
		public void AfegirLlibre (Llibres llibre){
			
			int i, j;
			
			//Comprovem que el llibre tingui lloc a la llista
			//Si té lloc l'afegim directament
			if(numllibres < llistallibres.length){
				
				//Agafem la posició on ha d'anar el llibre amb una funció auxiliar
				i = OrdreCodis(llibre.getCodi());
				
				//Movem tots els llibres a partir de la posició un endavant
				for(j=i; j<numllibres; j++){
					
					llistallibres[j+1] = llistallibres[j];
				}
					
				//Afegim el nou llibre a la posició trobada
				llistallibres[i] = llibre.Duplicat();
				//Actualitzem el número de llibres
				numllibres++;
				
			}
			//Si no té lloc ampliem la llista
			else{
				
				Llibres[] aux = new Llibres[numllibres+1];
				
				//Copiem tot el contingut de la llista en l'auxiliar
				for(i=0; i<numllibres; i++){
					
					aux[i] = llistallibres[i];
				}
				
				//Volem afegir el llibre de manera ordenada a la llista
				//Agafem la posició on ha d'anar el llibre amb una funció auxiliar
				i = OrdreCodis(llibre.getCodi());
				
				//Movem tots els llibres a partir de la posició un endavant
				for(j=i; j<numllibres; j++){
					
					aux[j+1] = aux[j];
				}
				
				
				//Finalment afegim el nou llibre a la llista ampliada a la posició que li toca
				aux[i] = llibre.Duplicat();
				
				//Actualitzem el número de llibres
				numllibres++;
				
				//Retornem la direcció de la nova llista a la vella
				llistallibres = aux;
				
			}			
			
		}
	
	
	
	

}
