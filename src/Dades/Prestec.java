/** 
 * Practica 3. Classe Prestecs.
 * 
 * 
 * @author Magdalena Tolstoy
 * 
 *
 */

package Dades;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

public class Prestec {
	private String id_llibre;
	private String dni;
	private Date data_ini, data_fi;

	/**
	 * Constructor de la classe Prestecs amb una data d'inici predefinida
	 * 
	 * @param id_llibre:
	 *            numero identificador del llibre en prestec
	 * @param dni:
	 *            dni de qui realitza el prestec
	 * @param data:
	 *            data d'inici del prestec
	 */
	public Prestec(String id_llibre, String dni, String data) {
		this.id_llibre = id_llibre;
		this.dni = dni;
		data_fi = null; // Fem una data final nul.la
		// Passem el string a tipus data
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try {
			data_ini = format.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor de la classe Prestecs que marca el dia actual com a data inicial
	 * del prestec
	 * 
	 * @param id_llibre:
	 *            numero identificador del llibre en prestec
	 * @param dni:
	 *            dni de qui realitza el prestec
	 */
	public Prestec(String id_llibre, String dni) {
		// Mirem que el llibre es trobi a la nostra llista de llibres
		this.id_llibre = id_llibre;
		this.dni = dni;
		data_fi = new Date(0); // Emmagatzemem una data final de prestec "nul.la"
		data_ini = Calendar.getInstance().getTime();

	}

	/**
	 * Constructor de la classe Prestecs amb una data inicial i final predefinides
	 * 
	 * @param id_llibre:
	 *            numero identificador del llibre en prestec
	 * @param dni:
	 *            dni de qui realitza el prestec
	 * @param data:
	 *            data d'inici del prestec
	 */
	public Prestec(String id_llibre, String dni, Date data_ini, Date data_fi) {
		this.id_llibre = id_llibre;
		this.dni = dni;
		this.data_ini = (Date) data_ini.clone();
		this.data_fi = (Date) data_fi.clone();
	}

	/**
	 * Getter
	 * 
	 * @return identificador del llibre del prestec
	 */
	public String getId_llibre() {
		return id_llibre;
	}

	/**
	 * Getter
	 * 
	 * @return DNI de qui fa la reserva
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Getter
	 * 
	 * @return data inici del prestec
	 */
	public Date getData_ini() {
		return data_ini;
	}

	/**
	 * Getter
	 * 
	 * @return data final del prestec
	 */
	public Date getData_fi() {
		return data_fi;
	}

	/**
	 * Setter de la data en que s'acaba el prestec
	 * 
	 * @param data_fi
	 *            data de finalitzacio del prestec
	 */
	public void setData_fi(Date data_fi) {
		this.data_fi = data_fi;
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
	 * Metode que ens indica si el soci ha retornat abans d'arribar al temps maxim
	 * de prestec
	 * 
	 * @param llibre
	 *            tipus Llibre que conte el llibre del qual s'ha fet el prestec
	 * @return true si el prestec es troba dins del termini i false si el soci s'ha
	 *         passat dels dies
	 */
	public boolean enTermini(Llibre llibre, Date data_fi) {
		int diesPrestec = llibre.getDIES_RESERVA();
		long diferencia = getDifferenceDays(data_ini, data_fi);
		// Si la diferencia es negativa l'usuari anula el prestec abans de que aquest
		// sigui efectiu
		if (diferencia < 0)
			diferencia = 0;

		// Si el llibre es cientific sobreescribim els dies de reserva
		if (llibre instanceof Llibre_Cientific)
			diesPrestec = ((Llibre_Cientific) llibre).getDiesPrestec();

		// Si el temps entre la data en que s'ha iniciat el prestec i la data de
		// devolucio es mes petita que els dies maxims de prestec retornem true
		if (diferencia > diesPrestec)
			return false;
		else
			return true;
	}

	/**
	 * Metode que retorna en una String les dades del prestec
	 * 
	 * @return String
	 */
	public String toString() {
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String resultat = "Codi del llibre: " + id_llibre + ", DNI de qui fa el prestec: " + dni
				+ ", data inici del prestec: " + formato.format(data_ini);
		if (data_fi == null)
			resultat = resultat + ", data finalitzacio prestec: " + formato.format(data_fi);
		else
			resultat = resultat + ", prestec no finalitzat";
		return resultat;
	}

	/**
	 * Metode que retorna la copia de l'objecte
	 * 
	 * @return copia del prestec
	 */
	public Prestec copia() {
		return (new Prestec(id_llibre, dni, data_ini, data_fi));
	}
}
