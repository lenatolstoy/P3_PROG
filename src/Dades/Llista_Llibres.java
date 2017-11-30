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
	 * @param numllibresmax enter que ens d�na la longitud m�xima del vector de llibres
	 */
	public Llista_Llibres(int numllibresmax){
		
		llistallibres = new Llibres[numllibresmax];
		numllibres = 0;
	}
	
	
	//toString
	/**
	* M�tode per imprimir la llista de reserves
	* @return aux 
	*/
		public String toString(){
			String aux="";
			aux = aux +"LLIBRE: N�mero de llibres = "+numrllibres+"\n\n\n";
			for(int i=0; i<numllibres; i++){
				aux = aux +llistallibres[i]+ "\n";
			}
			return (aux);
		}
		
		
		//M�todes
		
		/**
		 * M�tode per comprovar l'ordre dels codis (alfab�ticament)
		 * @param codi del tipus String
		 * @return pos ens retorna la posici� on ha d'estar el llibre a la llista perqu� estigui ordenat
		 */
		public int OrdreCodis (String codi){
			
			boolean trobat = false;
			int pos = 0;
			int i = 0;
			//Volem afegir el llibre a la llista ordenada per codi
			//Anem recorrent la llista i busquem la posici� on ha d'anar
			//Parem quan trobem la posici� o arribem al final
			
			while(i<numllibres && !trobat){
				
				if (){
					
					trobat=true;
				}
				
				i++;
			}
			
			
			return pos;
		}
		
		
		
		/**
		 * M�tode per afegir un llibre a la biblioteca
		 * @param llibre del tipus Llibres que ens donar� la informaci� del llibre que es vol afegir
		 */
		
		public void AfegirLlibre (Llibres llibre){
			
			int i, j;
			
			//Comprovem que el llibre tingui lloc a la llista
			//Si t� lloc l'afegim directament
			if(numllibres < llistallibres.length){
				
				//Agafem la posici� on ha d'anar el llibre amb una funci� auxiliar
				i = OrdreCodis(llibre.getCodi());
				
				//Movem tots els llibres a partir de la posici� un endavant
				for(j=i; j<numllibres; j++){
					
					llistallibres[j+1] = llistallibres[j];
				}
					
				//Afegim el nou llibre a la posici� trobada
				llistallibres[i] = llibre.Duplicat();
				//Actualitzem el n�mero de llibres
				numllibres++;
				
			}
			//Si no t� lloc ampliem la llista
			else{
				
				Llibres[] aux = new Llibres[numllibres+1];
				
				//Copiem tot el contingut de la llista en l'auxiliar
				for(i=0; i<numllibres; i++){
					
					aux[i] = llistallibres[i];
				}
				
				//Volem afegir el llibre de manera ordenada a la llista
				//Agafem la posici� on ha d'anar el llibre amb una funci� auxiliar
				i = OrdreCodis(llibre.getCodi());
				
				//Movem tots els llibres a partir de la posici� un endavant
				for(j=i; j<numllibres; j++){
					
					aux[j+1] = aux[j];
				}
				
				
				//Finalment afegim el nou llibre a la llista ampliada a la posici� que li toca
				aux[i] = llibre.Duplicat();
				
				//Actualitzem el n�mero de llibres
				numllibres++;
				
				//Retornem la direcci� de la nova llista a la vella
				llistallibres = aux;
				
			}			
			
		}
	
	
	
	

}
