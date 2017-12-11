package Dades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LlistaLlibres {
	
	//Atributs
	private int numllibres;
	private Llibre[] llistallibres;
	
	public int getNumllibres() {
		return numllibres;
	}


	public Llibre[] getLlistallibres() {
		return llistallibres;
	}

	public Llibre retornaLlibre(String codi) {
		for (int i = 0; i < numllibres; i++) {
			if (llistallibres[i].getCodi().equals(codi)) {
				return llistallibres[i];
			}
		}
		return null;
	}

	/**
	 * Constructor
	 * @param numllibresmax enter que ens dóna la longitud màxima del vector de llibres
	 */
	public LlistaLlibres(int numllibresmax){
		
		llistallibres = new Llibre[numllibresmax];
		numllibres = 0;
	}
	
	
	//toString
	/**
	* Mètode per imprimir la llista de reserves
	* @return aux 
	*/
		public String toString(){
			String aux="";
			aux = aux +"LLIBRE: Número de llibres = "+numllibres+"\n\n\n";
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
		
		public int ordreCodis (String codi){
			
			boolean trobat = false;
			int pos=0, i=0;
			//Volem afegir el llibre a la llista ordenada per codi
			//Anem recorrent la llista i busquem la posició on ha d'anar
			if(numllibres == 0) trobat = true;
			
			//Parem quan trobem la posició o arribem al final
			while(i<numllibres || !trobat){
				
				if (llistallibres[i].comprovaAlfabetic(codi)){
					trobat=true;
					pos = i;
				}
				
				i++;
			}
			//Si no ho hem trobat és que el nostre codi va l'últim de la llista
			if(!trobat){
			
				pos = numllibres;
			}
			return pos;
		}
		
		
		
		/**
		 * Mètode per afegir un llibre a la biblioteca
		 * @param llibre del tipus Llibres que ens donarà la informació del llibre que es vol afegir
		 */
		
		public void afegirLlibre (Llibre llibre){
			
			int i, j;
			
			//Comprovem que el llibre tingui lloc a la llista
			//Si té lloc l'afegim directament
			if(numllibres < llistallibres.length){
				
				//Agafem la posició on ha d'anar el llibre amb una funció auxiliar
				i = ordreCodis(llibre.getCodi());
				
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
				
				Llibre[] aux = new Llibre[numllibres+1];
				
				//Copiem tot el contingut de la llista en l'auxiliar
				for(i=0; i<numllibres; i++){
					
					aux[i] = llistallibres[i];
				}
				
				//Volem afegir el llibre de manera ordenada a la llista
				//Agafem la posició on ha d'anar el llibre amb una funció auxiliar
				i = ordreCodis(llibre.getCodi());
				
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
		
		/**
		 * Mètode per afegir un llibre a la biblioteca
		 * @param llibre del tipus Llibres que ens donarà la informació del llibre que es vol afegir
		 */
		
		public void afegirLlibreCientific (Llibre_Cientific llibre_cientific){
			
			int i, j;
			
			//Comprovem que el llibre tingui lloc a la llista
			//Si té lloc l'afegim directament
			if(numllibres < llistallibres.length){
				
				//Agafem la posició on ha d'anar el llibre amb una funció auxiliar
				i = ordreCodis(llibre_cientific.getCodi());
				
				//Movem tots els llibres a partir de la posició un endavant
				for(j=i; j<numllibres; j++){
					
					llistallibres[j+1] = llistallibres[j];
				}
					
				//Afegim el nou llibre a la posició trobada
				llistallibres[i] = llibre_cientific.Duplicat();
				//Actualitzem el número de llibres
				numllibres++;
				
			}
			//Si no té lloc ampliem la llista
			else{
				
				Llibre[] aux = new Llibre[numllibres+1];
				
				//Copiem tot el contingut de la llista en l'auxiliar
				for(i=0; i<numllibres; i++){
					
					aux[i] = llistallibres[i];
				}
				
				//Volem afegir el llibre de manera ordenada a la llista
				//Agafem la posició on ha d'anar el llibre amb una funció auxiliar
				i = ordreCodis(llibre_cientific.getCodi());
				
				//Movem tots els llibres a partir de la posició un endavant
				for(j=i; j<numllibres; j++){
					
					aux[j+1] = aux[j];
				}
				
				
				//Finalment afegim el nou llibre a la llista ampliada a la posició que li toca
				aux[i] = llibre_cientific.Duplicat();
				
				//Actualitzem el número de llibres
				numllibres++;
				
				//Retornem la direcció de la nova llista a la vella
				llistallibres = aux;
				
			}			
			
		}
		
				
		/**
		 * Mètode per llegir d'un fitxer de llibres
		 * @return llistallibres del tipus Llibres[] per tenir ja en una llista de llibres tots els llibres
		 * @throws FileNotFoundException 
		 */
		public Llibre[] llegirFitxer() throws FileNotFoundException, IOException {
			
			String fileToString = "";
			String aux = "";
			
		    try {
				BufferedReader llibres = new BufferedReader(new FileReader("Llibres.txt"));
				fileToString = llibres.readLine();
				
				/* ara tenim tot el fitxer a fileToString */
				
				int p = 0;
				int f = 0;
				int i = 0;
				
				
				
				
				do {
					aux = (String) fileToString.subSequence(p, f = fileToString.indexOf('*') - 1);
					p = f + 2;
					if(aux.contentEquals("100tifiko")) {
						aux =  (String) fileToString.subSequence(p, f = fileToString.indexOf('*') - 1);
						p = f + 2;
						((Llibre_Cientific)llistallibres[i]).setDiesPrestec(Integer.parseInt(aux));
						aux =  (String) fileToString.subSequence(p, f = fileToString.indexOf('*') - 1);
						p = f + 2;
					}
			
					llistallibres[i].setTitol(aux);
					
					/* subsequence et fa una string desde la posicio p fins la f que en aquest cas es
					 * fins abans del asterisc, despres li sumem 2 per saltar-nos'el <- (O.o)
					 */
			
					
					aux =  (String) fileToString.subSequence(p, f = fileToString.indexOf('*') - 1);
					p = f + 2;
					do {
						llistallibres[i].setAutor(aux);
						aux =  (String) fileToString.subSequence(p, f = fileToString.indexOf('*') - 1);
						p = f + 2;
					}while(!aux.equals("/"));
					aux =  (String) fileToString.subSequence(p, f = fileToString.indexOf('*') - 1);
					p = f + 2;
					llistallibres[i].setTema(aux);
					aux =  (String) fileToString.subSequence(p, f = fileToString.indexOf('*') - 1);
					p = f + 2;
					llistallibres[i].setNum_edicio((Integer.parseInt(aux)));
					aux =  (String) fileToString.subSequence(p, f = fileToString.indexOf('*') - 1);
					p = f + 2;
					/* Aqui tenim al aux el date en forma de String */
					llistallibres[i].setAnyEdicio(Integer.parseInt(aux));
					aux =  (String) fileToString.subSequence(p, f = fileToString.indexOf('*') - 1);
					p = f + 2;
				
					/* A partir d'aqui ya sería el següent */
					fileToString = llibres.readLine();
					

				}while(fileToString != null);
				llibres.close();
				
			}
			catch (FileNotFoundException e) {
				System.out.println("No s'ha trobat el fitxer amb les dades de les mesures de Cobertura");
			}
			catch (IOException e) {
				System.out.println("S'ha produit un error al llegir el fitxer amb les dades de les mesures de Cobertura");
			}
		    return llistallibres;
		    
		}
		
		/**
		 * Mètode per escriure al fitxer la llista de llibres
		 * @throws IOException
		 */
		public void escriureFitxer() throws IOException{
			
			//Creem les variables (que són els atributs) que anirem escrivint al fitxer
			
			String titol;
			String[] autors;
			String tema;
			String[] temes = null;
			int num_edicio;
			int any_edicio;
			String codi;			
		
			BufferedWriter fitxer = new BufferedWriter(new FileWriter("Llibres.txt"));
			
			if(llistallibres[0].getTemes() != null) {
				temes = llistallibres[0].getTemes();
			}
			
			if(temes != null) {
				for(int z = 0; z < temes.length; z++) {
					fitxer.write(temes[z]);
					fitxer.write("*");
				}
				fitxer.newLine();
			}
			
			//Recorrem totes les reserves per anar posant els atributs en variables
			//I escriure reserva per reserva al fitxer
			for (int i = 0; i<numllibres; i++) {
				//Guardem en variables
				titol = llistallibres[i].getTitol();
				autors = llistallibres[i].getAutors();
				tema = llistallibres[i].getTema();
				num_edicio = llistallibres[i].getNumEdicio();
				codi = llistallibres[i].getCodi();
				any_edicio = llistallibres[i].getAnyEdicio();
		
				//Ho escrivim al fitxer separat per *
				if(llistallibres[i] instanceof Llibre_Cientific) {
					fitxer.write("100tifico*");
					fitxer.write(((Llibre_Cientific)llistallibres[i]).getDiesPrestec());
					fitxer.write("*");
				}
				
				fitxer.write(titol);
				fitxer.write("*");
				for(int z = 0; autors[z] != null; z++) {
					fitxer.write(autors[z]);
					fitxer.write("*");
				}
				fitxer.write("/");
				fitxer.write("*");
				fitxer.write(tema);
				fitxer.write("*");
				fitxer.write(num_edicio);
				fitxer.write("*");
				fitxer.write(codi);
				fitxer.write("*");
				fitxer.write(any_edicio);
				fitxer.write("*");
				
				//Saltem de línia al fitxer per escriure la nova reserva
				if (i != numllibres - 1) {
					fitxer.newLine();
				}
			}
			//Tanquem el fitxer
			fitxer.close();
		}
		
		/**
		 * Funcio la qual busca tots els llibres que tenen el nom o part del nom que has buscat
		 * 
		 */
		public LlistaLlibres buscaLlibresNom(String nom) {
			
			LlistaLlibres llibres = new LlistaLlibres(5);
			
			for (int i = 0; i<numllibres; i++) {
			
				if(llistallibres[i].getTitol().contains(nom)) {
					llibres.afegirLlibre(llistallibres[i]);
				}
			}
			return llibres;
		}
		/**
		 * Li passes un codi i un tema i et retorna si el llibre te el tema que li has passat
		 * @param codi
		 * @param tema
		 * @return hiEs un booleà
		 */
		public boolean esDelTema(String codi, String tema) {
			
			boolean hiEs = false;
			boolean trobat = false;
			int i;
			
			for (i = 0; i<numllibres && trobat == false; i++) 
				if(llistallibres[i].getCodi() == codi) trobat = true;
			
			if(trobat == true) hiEs = (tema == llistallibres[i].getTema());
			
			return hiEs;
		}
		
		public String buscaPerTematica(String tema) {
			
			String llibres = "";
			boolean trobat = false;
			String[] temes_llibres;
			
			temes_llibres = llistallibres[0].getTemes();
			
			for (int i = 0; i < temes_llibres.length && trobat == false; i++) {
				trobat = (temes_llibres[i].equals(tema));
			}
			
			for (int j = 0; j<numllibres; j++) {
				if(llistallibres[j].getTema().contains(tema)) {
					llibres = llibres + llistallibres[j].toString() + "";
				}
			}
			return llibres;
		}
}
		

