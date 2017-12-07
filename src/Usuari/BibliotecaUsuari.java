package Usuari;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import Dades.*;
import Exceptions.*;

public class BibliotecaUsuari extends Biblioteca {

	/**
	 * Constructor Incicialitza totes les llistes a mida 20
	 */
	public BibliotecaUsuari() {
		super();
	}

	/**
	 * Ens indica si el DNI es troba a la llista de socis
	 * 
	 * @param dni
	 *            String en el qual es troba el DNI a buscar
	 * @return boolea que indica si s'ha torbat o no el DNI a la llista
	 */
	public boolean accedir(String dni) {
		return (socis.existeix(dni));
	}

	/**
	 * Metode que consulta les dades dels llibres amb un titol o part d'un titol i
	 * ens indica si esta disponible o no
	 * 
	 * @param titol
	 *            String amb el titol o part del titol a buscar
	 * @return String amb les dades dels llibres i si es troben disponibles o no per
	 *         a fer reserva avui
	 */
	public String consultaLlibresPerTitol(String titol) {
		// Obtenim una llista amb els llibres que coincideixen amb part del titol
		LlistaLlibres consulta = llibres.buscaLlibresNom(titol);

		// Inicialitzem el String que retornarem
		String resultat = "";

		// Recorrem els llibres amb els quals coincideix el titol per a veure si estan
		// disponibles o no
		for (int i = 0; i < consulta.getNumllibres(); i++) {

			// Afegim a la String les dades del llibre
			resultat = resultat + i + ". " + consulta.getLlistallibres()[i].toString();

			// Emmagatzemem el codi del llibre i la data d'avui en una String
			String idllibre = consulta.getLlistallibres()[i].getCodi();
			DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String avui = formato.format(new Date());

			// Segons si el llibre es troba disponible o no afegim disponible o no
			// disponible al final de la linea
			if (!prestecsActius.enPrestec(idllibre, avui) && !reserves.ReservaDia(idllibre, new Date()))
				resultat = resultat + "\tDISPONIBLE\n";
			else
				resultat = resultat + "\tNO DISPONIBLE\n";
		}
		return resultat;
	}

	/**
	 * Metode que ens permet saber les dades dels llibres que tenen un tema en
	 * concret
	 * 
	 * @param tema
	 *            String que ens indica el tema del qual volem saber els llibres
	 * @return String amb les dades de tots els llibres
	 */
	public String consultaLlibresPerTema(String tema) {
		return llibres.buscaPerTematica(tema);
	}

	/**
	 * Metode que permet a un soci fer el prestec d'un llibre
	 * 
	 * @param id_llibre
	 *            String que indica el codi del llibre
	 * @param dni
	 *            String que ens indica el DNI del soci
	 * @param data_ini
	 *            String amb la data en que l'usuari vol iniciar el prestec
	 * @throws LlibreNoTrobat
	 *             en el cas de que el llibre no es trobi a la llista de llibres
	 * @throws SociInexistent
	 *             en el cas de que el dni no es trobi a la llista de socis
	 * @throws MaximPrestecs
	 *             si el soci ja te en prestec el nombre maxim de llibres
	 * @throws PrestecJaExisteix
	 *             si l'usuari te un prestec actiu del mateix llibre no pot fer un
	 *             altre prestec
	 */
	public void ferPrestec(String id_llibre, String dni, String data_ini)
			throws LlibreNoTrobat, SociInexistent, MaximPrestecs, PrestecJaExisteix, LlibreNoDisponible {
		Soci soci = socis.retornaSoci(dni);
		Llibre llibre = llibres.retornaLlibre(id_llibre);
		// Comprovem que el llibre es troba a la llista de llibres
		if (llibre == null)
			throw new LlibreNoTrobat();
		else if (!llibre.isActiu())
			throw new LlibreNoDisponible();
		// Comprovem que l'usuari sigui soci
		else if (soci == null)
			throw new SociInexistent();
		// Comprovem que el soci no es trobi en el seu maxim de prestecs
		else if (soci.max_prestec())
			throw new MaximPrestecs();
		else if (prestecsActius.retornaPrestec(id_llibre, dni) != null)
			throw new PrestecJaExisteix();
		else {
			int num_dies = 15;

			// Si el llibre es cientific modifiquem el nombre de dies maxims del prestec
			if (llibre instanceof Llibre_Cientific)
				num_dies = ((Llibre_Cientific) llibre).getDiesPrestec();

			// Mirarem que el llibre no estigui en reserva o prestec
			if (!prestecsActius.enPrestec(id_llibre, data_ini, num_dies)
					&& !reserves.ReservaPeriode(id_llibre, data_ini, num_dies)) {
				// Si es aixi augmentem el numero de llibres en prestec del soci
				soci.prestecUP();
				// Afegim el prestec a la llista
				prestecsActius.afegirPrestec(id_llibre, dni, data_ini);
			}
			else
				throw new LlibreNoDisponible();
		}
	}

	/**
	 * Metode que permet retornar un llibre en prestec
	 * 
	 * @param id_llibre
	 *            String amb el codi del llibre
	 * @param dni
	 *            String amb el dni de l'usuari que havia fet el prestec
	 * @throws LlibreNoTrobat
	 *             en el cas de que el llibre a retornar no existeixi
	 * @throws SociInexistent
	 *             en el cas de que el dni no sigui d'un usuari
	 */
	public void finalitzarPrestec(String id_llibre, String dni)
			throws LlibreNoTrobat, SociInexistent, PrestecInexistent {
		Prestec prestec = prestecsActius.retornaPrestec(id_llibre, dni);
		Soci soci = socis.retornaSoci(dni);

		// Mirem que el llibre es trobi a la llista
		if (llibres.retornaLlibre(id_llibre) == null)
			throw new LlibreNoTrobat();

		// Comprovem que el dni sigui d'un soci
		else if (soci == null)
			throw new SociInexistent();
		else if (prestec == null)
			throw new PrestecInexistent();
		else {
			// Disminuim el nombre de llibres que el soci te en prestec
			soci.prestecDown();

			// Definim la data final de prestec com a avui
			prestec.setData_fi(new Date());

			// Comprovem si s'ha d'afegir incidencia
			gestionaIncidencies(soci, prestec);

			// Movem el prestec a la llista de prestecs Inactius
			prestecsActius.fiPrestec(id_llibre, dni, prestecsInactius);
		}
	}

	/**
	 * Funcio que indica els dies entre dues dates
	 * 
	 * @param d1
	 *            data antiga
	 * @param d2
	 *            data nova
	 * @return diferencia en dies
	 */
	private long getDifferenceDays(Date d1, Date d2) {
		long diff = d2.getTime() - d1.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	/**
	 * Metode que calcula els dies entre la data inicial de la reserva i el dia de
	 * finalitzacio Mirem que la diferencia sigui menor als dies maxims que el soci
	 * podia tenir el llibre en prestec. Si la diferencia es major augmentem les
	 * incidencies del soci. En el cas de que el soci sigui NoEstudiant i el llibre
	 * s'hagi entregat en termini augmentem els punts de fidelitat
	 * 
	 * @param soci
	 *            soci que retorna el llibre
	 * @param prestec
	 *            prestec del qual es fa la finalitzacio
	 */
	private void gestionaIncidencies(Soci soci, Prestec prestec) {
		int diesPrestec = 15;
		long diferencia = getDifferenceDays(prestec.getData_ini(), prestec.getData_fi());

		// Si la diferencia es negativa l'usuari anula el prestec abans de que aquest
		// sigui efectiu
		if (diferencia < 0)
			diferencia = 0;

		Llibre llibre = llibres.retornaLlibre(prestec.getId_llibre());

		// Si el llibre es cientific sobreescribim els dies de reserva
		if (llibre instanceof Llibre_Cientific)
			diesPrestec = ((Llibre_Cientific) llibre).getDiesPrestec();

		if (diferencia > diesPrestec)
			soci.incidenciaUP();
		else if (soci instanceof NoEstudiant)
			((NoEstudiant) soci).puntsUP();
	}

	/**
	 * Metode que li permet a l'usuari fer la reserva d'un llibre
	 * 
	 * @param id_llibre
	 * @param dni
	 * @param dia
	 */
	public void ferReserva(String id_llibre, String dni, String dia) {
		Date data = null;
		// Passem el string a tipus data
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try {
			data = format.parse(dia);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			// Afegim la reserva a la llista
			reserves.AfegirReserva(new Reserva(id_llibre, dni, data));
		} catch (ClassNotFoundException | LlibreNoDisponible | ReservesDiaSuperior30 | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param id_llibre
	 * @param dni
	 */
	public void anularReserva(String id_llibre, String dni) {

	}

	/**
	 * Metode que ens permet veure els prestecs (en cas de que el dni sigui d'un
	 * soci) i les reserves d'un usuari
	 * 
	 * @param dni
	 *            dni de la persona de la qual volem saber les reserves i els
	 *            prestecs
	 * @return String amb totes les dades de les reserves i els prestecs
	 */
	public String consultarPrestecsIReserves(String dni) {
		String resultat = "";
		if (socis.existeix(dni)) {
			resultat = "LLIBRES EN PRESTEC:\n" + prestecsActius.prestecsUsuari(dni).toString();
		}
		resultat = resultat + "\n LLIBRES EN RESERVA:\n" + reserves.reservesUsuari(dni).toString();
		return resultat;
	}
}