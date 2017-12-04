/** 
 * Practica 3. Classe LlistaCobertures.
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
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

public class Prestecs {
	private String id_llibre;
	private String dni;
	private Date data_ini, data_fi;
	
	
	/**
	 * Constructor de la classe Prestecs amb una data predefinida
	 * @param id_llibre: numero identificador del llibre en prestec
	 * @param dni: dni de qui realitza el prestec
	 * @param data: data d'inici del prestec
	 * @throws LlibreNoTrobatException
	 */
	public Prestecs(String id_llibre, String dni, String data) throws LlibreNoTrobatException{
		//Mirem que el llibre es troba a la nostra llista de llibres
		if (!Llista_Llibres.trobaLlibre(id_llibre)) { //Pte saber como se llama -> Cris 
			throw new LlibreNoTrobatException(); 
		}
		else {
			this.id_llibre=id_llibre;
			this.dni=dni;
			data_fi = new Date(0);	//Fem una data final de prestec "nul.la"
			//Passem el string a tipus data
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			try {
				data_ini = format.parse(data);
			} catch (ParseException e) {e.printStackTrace();}
		}
	}
	
	
	/**
	 * Constructor de la classe Prestecs que marca el dia actual com a data
	 * inicial del prestec
	 * @param id_llibre: numero identificador del llibre en prestec
	 * @param dni: dni de qui realitza el prestec
	 * @throws LlibreNoTrobatException
	 */
	public Prestecs(String id_llibre, String dni) throws LlibreNoTrobatException{
		//Mirem que el llibre es trobi a la nostra llista de llibres
		if (!Llista_Llibres.trobaLlibre(id_llibre)) { //Pte saber como se llama -> Cris 
			throw new LlibreNoTrobatException(); 
		}
		else {
			this.id_llibre=id_llibre;
			this.dni=dni;
			data_fi = new Date(0);	//Emmagatzemem una data final de prestec "nul.la"
			data_ini = Calendar.getInstance().getTime();
		}
	}
	
	
	/**
	 * Getter
	 * @return identificador del llibre del prestec
	 */
	public String getId_llibre() {
		return id_llibre;
	}
	
	
	/**
	 * Getter
	 * @return DNI de qui fa la reserva
	 */
	public String getDni() {
		return dni;
	}
	
	
	/**
	 * Getter
	 * @return data inici del prestec
	 */
	public Date getData_ini() {
		return data_ini;
	}
	
	
	/**
	 * Getter
	 * @return data final del prestec
	 */
	public Date getData_fi() {
		return data_fi;
	}
	
	
	/**
	 * Setter de la data en que s'acaba el prestec
	 * @param data_fi data de finalitzacio del prestec
	 */
	public void setData_fi(String data_fi) {
		//Passem el string a un tipus data
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try {
				this.data_fi = format.parse(data_fi);
		} catch (ParseException e) {e.printStackTrace();}
		try {
			gestionaIncidencies();
		} catch (DataIncorrecta e) {e.printStackTrace();}
	}
	
	/**
	 * Funcio que indica els dies entre dues dates
	 * @param d1 data antiga
	 * @param d2 data nova
	 * @return diferencia en dies
	 */
	private long getDifferenceDays(Date d1, Date d2) {
	    long diff = d2.getTime() - d1.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * Accio que calcula la diferencia entre el dia d'inici de la reserva
	 * i el dia de finalitzacio. En el cas de que la diferencia sigui negativa
	 * es retorna una Exception. 
	 * Si la diferencia es positiva es mira que sigui menor en cas dels socis
	 * o menor al valor especificat en el cas dels No_estudiants
	 * @throws DataIncorrecta
	 */
	private void gestionaIncidencies() throws DataIncorrecta{
		int diesReserva = 15;
		long diferencia = getDifferenceDays(data_ini, data_fi);
		//Mirem que la diferencia no sigui negativa
		if (diferencia<0) {
			data_fi = new Date(0);
			throw new DataIncorrecta();
		}
		//Mirem si el dni es d'un soci
		else if (Llista_Socis.trobaSoci(dni)) {
			Socis soci = Llista_Socis.retornaSoci(dni);
			Llibres llibre = Llista_Llibres.retornaLlibre(id_llibre);
			//Si el llibre es cientific sobreescribim els dies de reserva
			if (llibre instanceof Llibre_Cientific) diesReserva = ((Llibre_Cientific) llibre).getDiesReserva();
			
			if (diferencia>diesReserva) soci.afegeixIncidencia(); //Pte Ivan o yops
			//OP2 Llista_Socis.afegeixIncidencia(dni);
			else {
				if (soci instanceof No_estudiant) ((No_estudiant) soci).afegeixPunts(); //Pte Ivan
			}
		}
	}
}
