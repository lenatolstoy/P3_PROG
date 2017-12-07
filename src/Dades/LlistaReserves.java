package Dades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import Exceptions.LlibreNoDisponible;
import Exceptions.ReservesDiaSuperior30;

/**
 * 
 * @author Cristina
 *
 */

public class LlistaReserves {

	// Atributs
	private Reserva[] llistareserves;
	private int numreserves;

	/**
	 * Constructor
	 * 
	 * @param numreservesmax
	 *            enter que ens dóna la longitud màxima del vector de reserves
	 */
	public LlistaReserves(int numreservesmax) {

		llistareserves = new Reserva[numreservesmax];
		numreserves = 0;
	}

	// toString
	/**
	 * Mètode per imprimir la llista de reserves
	 * 
	 * @return aux
	 */
	public String toString() {
		String aux = "";
		aux = aux + "RESERVA: Número de reserves = " + numreserves + "\n\n\n";
		for (int i = 0; i < numreserves; i++) {
			aux = aux + llistareserves[i] + "\n";
		}
		return (aux);
	}

	// Mètodes

	/**
	 * Mètode que ens ordena la llista de reserves
	 */
	public void OrdenarReserves() {

		int pos, i, j;
		Reserva aux = null;

		// Ordenarem per selecció

		// Recorrem tota la llista menys 1 perquè comprovem amb la següent sempre
		for (i = 0; i < numreserves - 1; i++) {

			// Guardem en una auxiliar la posició que volem comprovar
			aux = llistareserves[i].Duplicat();
			pos = i;

			for (j = i + 1; j < numreserves; j++) {

				// Si la posició següent de la llista hauria d'anar abans hem d'invertir i
				// canviem l'auxiliar
				if (llistareserves[j].getData().before(aux.getData())) {

					aux = llistareserves[j].Duplicat();
					pos = j;
				}

				// Invertim (si és que s'ha d'invertir)
				llistareserves[pos] = llistareserves[i].Duplicat();
				llistareserves[i] = aux.Duplicat();
			}
		}

	}

	/**
	 * Mètode per saber quantes reserves hi ha en un dia
	 * 
	 * @param data
	 *            del tipus Date que ens dóna la data a consultar el número de
	 *            reserves
	 * @return numreservesdia enter que ens retorna el número de reserves ja fetes
	 *         aquell dia
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */

	public int NumRerservesDia(Date data) throws IOException, ClassNotFoundException {
		int numreservesdia = 0;

		// Hem de buscar a la llista de reserves totes les que tinguin aquesta data

		for (int i = 0; i < numreserves; i++) {

			// Si la data d'aquesta posició de la llista coincideix amb la que es vol fer la
			// reserva
			// Actualitzem el número de reserves del dia
			if (llistareserves[i].getData().getDay() == data.getDay()
					&& llistareserves[i].getData().getMonth() == data.getMonth()
					&& llistareserves[i].getData().getYear() == data.getYear()) {

				numreservesdia++;
			}
		}

		return numreservesdia;
	}

	/**
	 * Mètode per saber en quin estat es troba la reserva i actualitzar-lo
	 * 
	 * @throws IOException
	 */
	public void EstatReserves() throws IOException {

		int i;

		// Comprovarem l'estat de la reserva, si està activa o no, per totes les de la
		// llista de reserves
		/*
		 * for (i=0; i<numreserves; i++){
		 * 
		 * //Si no és soci la reserva estarà activa dos hores a partir de l'hora donada
		 * en la data if(!EsSoci(llistareserves[i].getDNI())){
		 * 
		 * 
		 * //Comprovem si ens trobem en la data i en l'interval de dos hores
		 * if(llistareserves[i].getData().getDay() == new Date().getDay() &&
		 * llistareserves[i].getData().getMonth() == new Date().getMonth() &&
		 * llistareserves[i].getData().getYear() == new Date().getYear() &&
		 * llistareserves[i].getData().getHours() <= new Date().getHours() &&
		 * (llistareserves[i].getData().getHours()+2) >= new Date().getHours()){
		 * 
		 * 
		 * //Quan l'hora sigui dos hores més s'acabarà el termini en els minuts i segons
		 * que toca if ((llistareserves[i].getData().getHours()+2) == new
		 * Date().getHours() && llistareserves[i].getData().getMinutes() <= new
		 * Date().getMinutes() && llistareserves[i].getData().getSeconds() <= new
		 * Date().getSeconds()){
		 * 
		 * llistareserves[i].setActiva(false);
		 * 
		 * } else{
		 * 
		 * llistareserves[i].setActiva(true); }
		 * 
		 * } }
		 * 
		 * //Si és soci la reserva estarà activa tot el dia else{
		 * 
		 * if(llistareserves[i].getData().getDay() == new Date().getDay() &&
		 * llistareserves[i].getData().getMonth() == new Date().getMonth() &&
		 * llistareserves[i].getData().getYear() == new Date().getYear()){
		 * 
		 * llistareserves[i].setActiva(true);
		 * 
		 * } }
		 * 
		 * }
		 */

	}

	/**
	 * Mètode que ens retorna una llista amb les reserves que estan actives en
	 * aquest moment
	 * 
	 * @return llistareservesactives del tipus Reserves[]
	 * @throws IOException
	 */

	public Reserva[] ReservesActives() throws IOException {

		Reserva[] llistareservesactives = new Reserva[numreserves];
		int i, j = 0;

		// Cridem actualizar estat reserves
		EstatReserves();

		// Recorrem totes les reserves
		for (i = 0; i < numreserves; i++) {
			// Si la reserva es troba ara mateix activa l'afegim a la llista de reserves
			// actives
			if (llistareserves[i].getActiva() == true) {

				llistareservesactives[j] = llistareserves[i].Duplicat();
				j++;

			}
		}

		return llistareservesactives;
	}

	/**
	 * Mètode per afegir una reserva (si es pot)
	 * 
	 * @param reserva
	 *            del tipus Reserves que ens donarà la informació de la reserva que
	 *            es vol afegir
	 * @throws LlibreNoDisponible
	 *             excepció que s'activa si el llibre no es troba disponible
	 * @throws ReservesDiaSuperior30
	 *             excepció que s'activa si el número de reserves d'aquell dia ja és
	 *             superior a 30
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */

	public void AfegirReserva(Reserva reserva)
			throws LlibreNoDisponible, ReservesDiaSuperior30, IOException, ClassNotFoundException {

		int i;

		// Controlo l'excepció del cas en el que no podem afegir la reserva perquè
		// el llibre que es vol reservar no es troba disponible
		if (!LlibreDisponible(reserva.getCodillibre(), reserva.getData())) {

			throw new LlibreNoDisponible(reserva.getCodillibre());
		}
		// Controlo l'excepció del cas en el que no podem afegir la reserva perquè
		// el número de reserves d'aquell dia ja és superior a 30
		if (NumRerservesDia(reserva.getData()) >= 30) {

			throw new ReservesDiaSuperior30(reserva.getData());

		}
		// Si no és que el llibre està disponible i aquell dia es pot reservar
		// Per tant anem a fer la reserva
		else {

			// Afegim la reserva
			// Mirem si la llista està plena
			if (numreserves < llistareserves.length) {

				// Si no ho està afegim la reserva directament

				// Afegim la reserva en la posició següent
				llistareserves[numreserves] = reserva.Duplicat();

				// Actualitzem número de reserves
				numreserves++;
			}

			// Si ho està ampliem la llista i afegim la reserva
			else {

				// Creem una llista auxiliar amb una posició més
				Reserva[] aux = new Reserva[numreserves + 1];

				// Copiem tot el contingut de la llista en l'auxiliar
				for (i = 0; i < numreserves; i++) {

					aux[i] = llistareserves[i];
				}
				// Afegim la reserva en la posició que hem afegit
				aux[numreserves] = reserva.Duplicat();

				// Actualitzem número de reserves
				numreserves++;

				// Sobreescrivim la llista al fitxer de reserves
				llistareserves = aux;
			}

			// Ara ordenem la reserva que hem afegit
			// Si només hi ha una reserva la col·loquem la primera de la llista i és
			// correcte
			if (numreserves != 1) {

				OrdenarReserves();
			}

		}
	}

	/**
	 * Mètode per eliminar una reserva a partir d'un DNI
	 * 
	 * @param DNI
	 *            del tipus String que ens dóna el DNI de l'usuari del qual volem
	 *            esborrar les reserves
	 */

	public void EliminarReserva(String DNI) {
		int i, j, k = 0;
		int pos;
		// Recorrem tota la llista
		for (i = 0; i < numreserves; i++) {
			// Si la reserva de la posició de la llista que estem mirant té el mateix DNI
			// l'eliminem
			if (llistareserves[i].getDNI().equals(DNI)) {
				// Guardem la posició
				pos = i;
				// Des d'aquesta posició movem totes les altres un endavant
				for (j = pos; j < numreserves - 1; j++) {

					llistareserves[j] = llistareserves[j + 1];
				}
				// Actualitzem el comptador del número de reserves eliminades
				// Ho fem així perquè pot ser que haguem d'eliminar més d'una reserva d'aquest
				// usuari
				// si no ens carregaríem el for
				k++;
			}
		}
		// Restem les reserves que hem eliminat
		numreserves = numreserves - k;
	}

	/**
	 * Mètode per consultar totes les reserves d'un usuari (que encara no han tingut
	 * lloc o que estan actives ara mateix)
	 * 
	 * @param DNI
	 *            del tipus String que ens dóna el DNI de l'usuari del qual volem
	 *            consultar les reserves
	 * @return
	 * @throws IOException
	 */

	public Reserva[] ConsultarReserves(String DNI) throws IOException {
		int i, j = 0;
		Reserva[] reservesusuari = new Reserva[numreserves];
		Date aux = new Date();
		// Actualitzo estat reserves
		EstatReserves();
		// Recorrem tota la llista
		for (i = 0; i < numreserves; i++) {
			// Si la reserva de la posició de la llista que estem mirant té el mateix DNI
			// i la reserva està activa o encara no ha passat, l'afegim a la llista de
			// reserves de l'usuari
			if (llistareserves[i].getDNI().equals(DNI)
					&& (llistareserves[i].getActiva() == true || llistareserves[i].getData().after(aux))) {

				reservesusuari[j] = llistareserves[i].Duplicat();
				j++;
			}
		}
		// Si no hem posat cap la llista la passem com a nul·la
		if (j == 0) {

			reservesusuari = null;
		}
		return reservesusuari;
	}

	/**
	 * Mètode per comprovar si un llibre està reservat un dia concret
	 * 
	 * @param codillibre
	 *            del tipus String que ens dóna el codi del llibre que volem
	 *            comprovar si està reservat
	 * @param data
	 *            del tipus Date que ens dóna la data del dia que volem comprovar si
	 *            està reservat
	 * @return reservat del tipus booleà que retorna cert si està reservat aquell
	 *         dia i fals si no ho està
	 */

	public boolean ReservaDia(String codillibre, Date data) {

		boolean reservat = false;
		int i = 0;
		// Busquem fins que trobem que està reservat o fins que s'acabi la llista
		while (i < numreserves && !reservat) {

			// Si trobem que la reserva de la posició de la llista que mirem
			// té la mateixa data (en dd/mm/yyyy només) i és del llibre que comprovem (pel
			// codi)
			// sabem que està reservat i parem
			if (llistareserves[i].getCodillibre().equals(codillibre)
					&& llistareserves[i].getData().getDay() == data.getDay()
					&& llistareserves[i].getData().getMonth() == data.getMonth()
					&& llistareserves[i].getData().getYear() == data.getYear()) {

				reservat = true;
			}

			i++;
		}

		return reservat;
	}

	/**
	 * Mètode per sumar un número de dies a la data que volem
	 * 
	 * @param data
	 *            del tipus Date a la que volem sumar-li el període
	 * @param periode
	 *            del tipus int que és el temps que volem sumar-li (en dies)
	 * @return diafinal del tipus Date que ens dóna el dia buscat
	 */
	public Date SumarDies(Date data, int periode) {

		Date diafinal;

		Calendar calendari = Calendar.getInstance();
		calendari.setTime(data); // Configuramos la fecha que se recibe
		calendari.add(Calendar.DAY_OF_YEAR, periode);

		diafinal = calendari.getTime();

		return diafinal;

	}

	/**
	 * Mètode per comprovar si un llibre està reservat en un període
	 * 
	 * @param codillibre
	 *            del tipus String que ens dóna el codi del llibre que volem
	 *            comprovar si està reservat
	 * @param data
	 *            del tipus Date que ens dóna la data a partir de la qual volem
	 *            mirar si està reservat algun dia
	 * @param periode
	 *            del tipus int que ens dóna el número de dies els quals volem mirar
	 *            si està reservat a partir de la data donada
	 * @return reservat del tipus booleà que retorna cert si està reservat aquell
	 *         dia i fals si no ho està
	 */

	public boolean ReservaPeriode(String codillibre, String string_data, int periode) {
		Date data = null;
		boolean reservat = false;
		// Passem el string a tipus data
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try {
			data = format.parse(string_data);
		} catch (ParseException e) {e.printStackTrace();}
		
		Date datafinal = SumarDies(data, periode);
		int i = 0;
		// Busquem fins que trobem que està reservat o fins que s'acabi la llista
		while (i < numreserves && !reservat) {

			// Si trobem que la reserva de la posició de la llista que mirem
			// és del llibre que comprovem (pel codi)
			// i la data de reserva està entre la data donada i la data final que hem
			// calculat
			// sabem que està reservat i parem
			if (llistareserves[i].getCodillibre().equals(codillibre) && llistareserves[i].getData().after(data)
					&& llistareserves[i].getData().before(datafinal)) {

				reservat = true;
			}

			i++;
		}

		return reservat;
	}

	/**
	 * Mètode per comprovar si el llibre està disponible (ni en préstec ni reservat
	 * aquell dia)
	 * 
	 * @param codillibre
	 *            String que ens dóna el codi de llibre del qual hem de comprovar la
	 *            disponibilitat
	 * @param data
	 *            del tipus Date que ens dóna la data del dia que volem comprovar si
	 *            està disponible
	 * @return disponible del tipus booleà que serà cert si el llibre està
	 *         disponible i fals si no ho està
	 */

	public boolean LlibreDisponible(String codillibre, Date data) throws IOException {

		boolean disponible = false;

		// Si el llibre no està reservat ni en préstec aquell dia està disponible
		/*
		 * if(!ReservaDia(codillibre, data) && !PréstecDia(codillibre, data)){
		 * 
		 * disponible=true; }
		 */

		return disponible;
	}

	/**
	 * Mètode per llegir d'un fitxer de reserves
	 * 
	 * @throws IOException
	 * @throws ReservesDiaSuperior30
	 * @throws LlibreNoDisponible
	 * @throws ClassNotFoundException
	 */
	public void LlegirFitxerReserves()
			throws ClassNotFoundException, LlibreNoDisponible, ReservesDiaSuperior30, IOException {

		// Creem les variables on aniran els atributs
		String codillibre;
		String DNI;
		Date data;
		boolean activa;

		Scanner fitxer = new Scanner(new File("Reserves.txt"));

		// Llegim fins * (i tenim cada atribut separat per *)
		fitxer.useDelimiter("\\*");

		// Fins que no arribem al final del fitxer
		while (fitxer.hasNext()) {

			// Anem llegint fins al * i agafant cada atribut
			codillibre = fitxer.next();
			// Afegim aquesta línia perquè no salti de línia la primera vegada
			codillibre = codillibre.replaceAll("[\n\r]", "");
			DNI = fitxer.next();
			data = new Date(fitxer.next());
			activa = fitxer.nextBoolean();

			// Creem una reserva auxiliar on passarem els altributs i l'afegirem a
			// llistareserves
			Reserva aux = new Reserva(codillibre, DNI, data, activa);
			AfegirReserva(aux);

		}

		// Tanquem el fitxer
		fitxer.close();

	}

	/**
	 * Mètode per escriure al fitxer la llista de reserves
	 * 
	 * @throws IOException
	 */
	public void EscriureFitxerReserves() throws IOException {

		// Creem les variables (que són els atributs) que anirem escrivint al fitxer
		String codillibre;
		String DNI;
		String data;
		// Ni que sigui booleà el necessitem com a String al fitxer de text
		String activa;

		BufferedWriter fitxer = new BufferedWriter(new FileWriter("Reserves.txt"));

		// Recorrem totes les reserves per anar posant els atributs en variables
		// I escriure reserva per reserva al fitxer
		for (int i = 0; i < numreserves; i++) {
			// Guardem en variables
			codillibre = llistareserves[i].getCodillibre();
			DNI = llistareserves[i].getDNI();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
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
			// Saltem de línia al fitxer per escriure la nova reserva menys en l'últim cas
			if (i != numreserves - 1) {
				fitxer.newLine();
			}
		}
		// Tanquem el fitxer
		fitxer.close();
	}

}
