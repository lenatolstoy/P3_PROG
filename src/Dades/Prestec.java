/** 
 * Practica 3. Classe Prestecs.
 * 
 * 
 * @author Magdalena Tolstoy
 * 
 *
 */

package Dades;

import Exceptions.*;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

public class Prestec {
	private String id_llibre;
	private String dni;
	private Date data_ini, data_fi;

	/**
	 * Constructor de la classe Prestecs amb una data predefinida
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
		data_fi = new Date(0); // Fem una data final de prestec "nul.la"
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
	 * @throws LlibreNoTrobat
	 */
	public Prestec(String id_llibre, String dni) throws LlibreNoTrobat {
		// Mirem que el llibre es trobi a la nostra llista de llibres
			this.id_llibre = id_llibre;
			this.dni = dni;
			data_fi = new Date(0); // Emmagatzemem una data final de prestec "nul.la"
			data_ini = Calendar.getInstance().getTime();
		
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
		this.data_fi=data_fi;
	}

}
