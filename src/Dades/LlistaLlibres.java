/** 
 * Practica 3. Classe LlistaLlibres.
 * 
 * 
 * @author Ivan Grima
 * @author Cristina Llort
 * @author Magdalena Tolstoy
 * @author Antonio Torres
 *
 */

package Dades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LlistaLlibres {

	// Atributs
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
	 * 
	 * @param numllibresmax
	 *            enter que ens dóna la longitud màxima del vector de llibres
	 */
	public LlistaLlibres(int numllibresmax) {

		llistallibres = new Llibre[numllibresmax];
		numllibres = 0;
	}

	// Mètodes

	/**
	* Mètode per imprimir la llista de reserves
	* @return aux 
	*/
		public String toString(){
			String aux="";
			
			aux = aux +"LLIBRE: Número de llibres = "+numllibres+"\n\n\n";
			for(int i=0; i<numllibres; i++){
				if(llistallibres[i] instanceof Llibre) {
					aux = aux +llistallibres[i]+ "\n";
				}else {
					aux = aux +(Llibre_Cientific)llistallibres[i]+ "\n";
				}
			}	
			return aux;
		}
	/**
	 * Mètode per comprovar l'ordre dels codis (alfabèticament)
	 * 
	 * @param codi
	 *            del tipus String
	 * @return pos ens retorna la posició on ha d'estar el llibre a la llista perquè
	 *         estigui ordenat
	 */

	public int ordreCodis(String codi) {

		boolean trobat = false;
		int pos = 0, i = 0;
		// Volem afegir el llibre a la llista ordenada per codi
		// Anem recorrent la llista i busquem la posició on ha d'anar
		if (numllibres == 0)
			trobat = true;

		// Parem quan trobem la posició o arribem al final
		while (i < numllibres && !trobat) {

			if (llistallibres[i].comprovaAlfabetic(codi)) {
				trobat = true;
				pos = i;
			}

			i++;
		}
		// Si no ho hem trobat és que el nostre codi va l'últim de la llista
		if (!trobat) {

			pos = numllibres;
		}
		return pos;
	}

	/**
	 * Mètode per afegir un llibre a la biblioteca
	 * 
	 * @param llibre
	 *            del tipus Llibres que ens donarà la informació del llibre que es
	 *            vol afegir
	 */

	public void afegirLlibre(Llibre llibre) {

		int i, j;

		// Comprovem que el llibre tingui lloc a la llista
		// Si té lloc l'afegim directament
		if (numllibres < llistallibres.length) {

			// Agafem la posició on ha d'anar el llibre amb una funció auxiliar
			i = ordreCodis(llibre.getCodi());

			// Movem tots els llibres a partir de la posició un endavant
			for (j = i; j < numllibres; j++) {

				llistallibres[j + 1] = llistallibres[j];
			}

			// Afegim el nou llibre a la posició trobada
			llistallibres[i] = llibre.Duplicat();
			// Actualitzem el número de llibres
			numllibres++;

		}
		// Si no té lloc ampliem la llista
		else {

			Llibre[] aux = new Llibre[numllibres + 1];

			// Copiem tot el contingut de la llista en l'auxiliar
			for (i = 0; i < numllibres; i++) {

				aux[i] = llistallibres[i];
			}

			// Volem afegir el llibre de manera ordenada a la llista
			// Agafem la posició on ha d'anar el llibre amb una funció auxiliar
			i = ordreCodis(llibre.getCodi());

			// Movem tots els llibres a partir de la posició un endavant
			for (j = i; j < numllibres; j++) {

				aux[j + 1] = aux[j];
			}

			// Finalment afegim el nou llibre a la llista ampliada a la posició que li toca
			aux[i] = llibre.Duplicat();

			// Actualitzem el número de llibres
			numllibres++;

			// Retornem la direcció de la nova llista a la vella
			llistallibres = aux;
		}
	}


	/**
	 * Mètode per afegir un llibre a la biblioteca
	 * 
	 * @param llibre
	 *            del tipus Llibres que ens donarà la informació del llibre que es
	 *            vol afegir
	 */

	public void afegirLlibreCientific(Llibre_Cientific llibre_cientific) {

		int i, j;

		// Comprovem que el llibre tingui lloc a la llista
		// Si té lloc l'afegim directament
		if (numllibres < llistallibres.length) {

			// Agafem la posició on ha d'anar el llibre amb una funció auxiliar
			i = ordreCodis(llibre_cientific.getCodi());

			// Movem tots els llibres a partir de la posició un endavant
			for (j = i; j < numllibres; j++) {

				llistallibres[j + 1] = llistallibres[j];
			}

			// Afegim el nou llibre a la posició trobada
			llistallibres[i] = llibre_cientific.Duplicat();
			// Actualitzem el número de llibres
			numllibres++;
		}
		// Si no té lloc ampliem la llista
		else {

			Llibre[] aux = new Llibre[numllibres + 1];

			// Copiem tot el contingut de la llista en l'auxiliar
			for (i = 0; i < numllibres; i++) {

				aux[i] = llistallibres[i];
			}

			// Volem afegir el llibre de manera ordenada a la llista
			// Agafem la posició on ha d'anar el llibre amb una funció auxiliar
			i = ordreCodis(llibre_cientific.getCodi());

			// Movem tots els llibres a partir de la posició un endavant
			for (j = i; j < numllibres; j++) {

				aux[j + 1] = aux[j];
			}

			// Finalment afegim el nou llibre a la llista ampliada a la posició que li toca
			aux[i] = llibre_cientific.Duplicat();

			// Actualitzem el número de llibres
			numllibres++;

			// Retornem la direcció de la nova llista a la vella
			llistallibres = aux;
		}
	}
				
		/**
		 * Mètode per llegir d'un fitxer de llibres
		 * @return llistallibres del tipus Llibres[] per tenir ja en una llista de llibres tots els llibres
		 * @throws FileNotFoundException 
		 */
		public void llegirFitxer() throws FileNotFoundException {
			File f = new File("Llibres.txt");
			if (f.exists() && !f.isDirectory()) {
			String aux = "";
			int dies_prestec = -1;
			String titol;
			String[] autors = new String[10];
			String tema;
			int num_edicio;
			int any_edicio;
			String codi;
				Scanner fitxer = new Scanner(f);
				// Llegim fins * (i tenim cada atribut separat per *)
				fitxer.useDelimiter("\\*");
				
				// Fins que no arribem al final del fitxer
				while (fitxer.hasNext()) {
					
					aux = fitxer.next();
					if(aux.contentEquals("100tifiko")) {
						dies_prestec = (Integer.parseInt(fitxer.next()));
						titol = (fitxer.next());
					}else {
		
						titol = (aux);
					}	
					
					titol.replaceAll("\n", ""); 
					System.out.println(titol);

					aux = fitxer.next();
					int z = 0;
					do {
						autors[z] = aux;
						aux = fitxer.next();
					}while(!(aux.equals("/")));
					
					tema = (fitxer.next());
					num_edicio = Integer.parseInt(fitxer.next());
					codi = fitxer.next();
					any_edicio = Integer.parseInt(fitxer.next());	
					
					if(dies_prestec == -1) {
						Llibre libro = new Llibre(titol, autors, tema, num_edicio, any_edicio, codi);
						afegirLlibre(libro);	

					}else {
						Llibre_Cientific libro100tifico = new Llibre_Cientific(titol, autors, tema, num_edicio, codi, any_edicio, dies_prestec);
						afegirLlibreCientific(libro100tifico);	
					}
					dies_prestec = -1;
					
				}
				fitxer.close();
				
			}
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
			int num_edicio;
			int any_edicio;
			String codi;			
		
			BufferedWriter fitxer = new BufferedWriter(new FileWriter("Llibres.txt"));
			
			/*if(llistallibres[0].getTemes() != null) {
				temes = llistallibres[0].getTemes();
				int z = 0;
				while(z < temes.length) {
					fitxer.write(temes[z]);
					fitxer.write("*");
					z++;
				}
				fitxer.newLine();
			}*/
			
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
				fitxer.write(""+num_edicio);
				fitxer.write("*");
				fitxer.write(codi);
				fitxer.write("*");
				fitxer.write(""+any_edicio);
				fitxer.write("*");
				fitxer.newLine();
		}
		fitxer.close();
	}


	/**
	 * Funcio la qual busca tots els llibres que tenen el nom o part del nom que has
	 * buscat
	 * 
	 */
	public LlistaLlibres buscaLlibresNom(String nom) {

		LlistaLlibres llibres = new LlistaLlibres(5);

		for (int i = 0; i < numllibres; i++) {

			if (llistallibres[i].getTitol().contains(nom)) {
				llibres.afegirLlibre(llistallibres[i]);
			}
		}
		return llibres;
	}

	/**
	 * Li passes un codi i un tema i et retorna si el llibre te el tema que li has
	 * passat
	 * 
	 * @param codi
	 * @param tema
	 * @return hiEs un booleà
	 */
	public boolean esDelTema(String codi, String tema) {

		boolean hiEs = false;
		boolean trobat = false;
		int i;

		for (i = 0; i < numllibres && trobat == false; i++)
			if (llistallibres[i].getCodi() == codi)
				trobat = true;

		if (trobat == true)
			hiEs = (tema == llistallibres[i].getTema());

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

		for (int j = 0; j < numllibres; j++) {
			if (llistallibres[j].getTema().contains(tema)) {
				llibres = llibres + llistallibres[j].toString() + "";
			}
		}
		return llibres;
	}
}
