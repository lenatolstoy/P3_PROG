package Dades;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import Exceptions.LlibreNoDisponible;
import Exceptions.ReservesDiaSuperior30;

public class LlistaLlibres {

	// Atributs
	private int numllibres;
	private Llibre[] llistallibres;

	/**
	 * Constructor
	 * 
	 * @param numllibresmax
	 *            enter que ens d�na la longitud m�xima del vector de llibres
	 */
	public LlistaLlibres(int numllibresmax) {

		llistallibres = new Llibre[numllibresmax];
		numllibres = 0;
	}

	// toString
	/**
	 * M�tode per imprimir la llista de reserves
	 * 
	 * @return aux
	 */
	public String toString() {
		String aux = "";
		aux = aux + "LLIBRE: N�mero de llibres = " + numllibres + "\n\n\n";
		for (int i = 0; i < numllibres; i++) {
			aux = aux + llistallibres[i] + "\n";
		}
		return (aux);
	}

	// M�todes

	/**
	 * M�tode per comprovar l'ordre dels codis (alfab�ticament)
	 * 
	 * @param codi
	 *            del tipus String
	 * @return pos ens retorna la posici� on ha d'estar el llibre a la llista perqu�
	 *         estigui ordenat
	 */

	// TONOOOOOOO
	// S'HA D'ACABAR AQUESTA FUNCIO
	// ET DEIXO L'ESTRUCTURA DE COM HE FET JO UNA SEMBLANT MEVA, PERO HAS DE TROBAR
	// LA MANERA
	// DE COMPROVAR SI EL CODI QUE REVISES ESTA ON TOCA
	// ES A DIR, RECORRES TOTA LA LLISTA I HAS DE TROBAR ENTRE QUIN I QUIN VA
	// RESUM, FALTA LA CONDICIO DEL IF

	public int OrdreCodis(String codi) {

		boolean trobat = false;
		int pos = 0, i = 0;
		// Volem afegir el llibre a la llista ordenada per codi
		// Anem recorrent la llista i busquem la posici� on ha d'anar

		// Parem quan trobem la posici� o arribem al final
		while (i < numllibres || !trobat) {

			if (llistallibres[i].comprovaAlfabetic(codi)) {
				trobat = true;
				pos = i - 1;
			}

			i++;
		}
		// Si no ho hem trobat �s que el nostre codi va l'�ltim de la llista
		if (!trobat) {

			pos = numllibres;
		}
		return pos;
	}

	/**
	 * M�tode per afegir un llibre a la biblioteca
	 * 
	 * @param llibre
	 *            del tipus Llibres que ens donar� la informaci� del llibre que es
	 *            vol afegir
	 */

	public void AfegirLlibre(Llibre llibre) {

		int i, j;

		// Comprovem que el llibre tingui lloc a la llista
		// Si t� lloc l'afegim directament
		if (numllibres < llistallibres.length) {

			// Agafem la posici� on ha d'anar el llibre amb una funci� auxiliar
			i = OrdreCodis(llibre.getCodi());

			// Movem tots els llibres a partir de la posici� un endavant
			for (j = i; j < numllibres; j++) {

				llistallibres[j + 1] = llistallibres[j];
			}

			// Afegim el nou llibre a la posici� trobada
			llistallibres[i] = llibre.Duplicat();
			// Actualitzem el n�mero de llibres
			numllibres++;

		}
		// Si no t� lloc ampliem la llista
		else {

			Llibre[] aux = new Llibre[numllibres + 1];

			// Copiem tot el contingut de la llista en l'auxiliar
			for (i = 0; i < numllibres; i++) {

				aux[i] = llistallibres[i];
			}

			// Volem afegir el llibre de manera ordenada a la llista
			// Agafem la posici� on ha d'anar el llibre amb una funci� auxiliar
			i = OrdreCodis(llibre.getCodi());

			// Movem tots els llibres a partir de la posici� un endavant
			for (j = i; j < numllibres; j++) {

				aux[j + 1] = aux[j];
			}

			// Finalment afegim el nou llibre a la llista ampliada a la posici� que li toca
			aux[i] = llibre.Duplicat();

			// Actualitzem el n�mero de llibres
			numllibres++;

			// Retornem la direcci� de la nova llista a la vella
			llistallibres = aux;

		}

	}

	// TOOOOOONO: AQUESTA S'HA D'IMPLEMENTAR, JO ENCARA ESTIC AMB LA MEVA DE
	// RESERVES
	// ET DEIXO LA CABECERA COM JO HO HE PENSAT DE MOMENT

	/**
	 * M�tode per llegir d'un fitxer de llibres
	 * 
	 * @return llistallibres del tipus Llibres[] per tenir ja en una llista de
	 *         llibres tots els llibres
	 * @throws FileNotFoundException
	 */
	public Llibre[] LlegirFitxer() throws FileNotFoundException {

	}

	// TOOOONI! AQUESTA A L'IVAN LI FUNCIONA I A MI EM FALTA COMPROVAR PERO HO FAIG
	// IGUAL
	// T'HO PASSO I MODIFICA EL QUE ET DIC

	/**
	 * M�tode per escriure al fitxer la llista de llibres
	 * 
	 * @throws IOException
	 */
	public void EscriureFitxer() throws IOException {

		// Creem les variables (que s�n els atributs) que anirem escrivint al fitxer
		String codillibre;
		String DNI;
		String data;
		// Ni que sigui boole� el necessitem com a String al fitxer de text
		String activa;

		BufferedWriter fitxer = new BufferedWriter(new FileWriter("Llibres.txt"));

		// TOOOOONI! CANVIA AMB ELS TEUS ATRIBUTS DE LLIBRE!
		// TINGUES EN COMPTE QUE TOT SON STRINGS, FIXAT EN L'EXEMPLE DE RESERVES I ELS
		// MEUS COMENTARIS
		// EN COM ES FA SI EN REALITAT NO EREN STRINGS I ELS HAS CONVERTIT (VALUEOF)
		// ESBORRA ELS COMENTARIS QUAN HO FACIS JAJA

		// Recorrem totes les reserves per anar posant els atributs en variables
		// I escriure reserva per reserva al fitxer
		for (int i = 0; i < numllibres; i++) {
			// Guardem en variables
			codillibre = llistareserves[i].getCodillibre();
			DNI = llistareserves[i].getDNI();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			data = sdf.format(llistareserves[i].getData());
			activa = String.valueOf(llistareserves[i].getActiva());

			// Ho escrivim al fitxer separat per *
			fitxer.write(codillibre);
			fitxer.write("*");
			fitxer.write(DNI);
			fitxer.write("*");
			fitxer.write(data);
			fitxer.write("*");
			fitxer.write(activa);
			fitxer.write("*");
			// Saltem de l�nia al fitxer per escriure la nova reserva
			fitxer.newLine();
		}
		// Tanquem el fitxer
		fitxer.close();
	}

	// BORRA ELS COMENTARIS!

}
