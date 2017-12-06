package Usuari;

import Dades.*;
import Exceptions.*;
import java.util.Date;

public class BibliotecaUsuari extends Biblioteca{
	
	public BibliotecaUsuari() {
		super();
	}
	
	public boolean accedir(String dni) {
		return (super.socis.existeix(dni));
	}
	
	//Indicar tmb si estan disponibles per a ser consultats ?? 
	public LlistaLlibres consultaLlibresTitol(String titol) {
		
	}
	
	public LlistaLlibres consultaLlibresTema(String tema) {
		
	}
	
	public void ferPrestec (String id_llibre, String dni, String data_ini) throws LlibreNoTrobat{
		if (!super.llibres.existeix(id_llibre)) throw new LlibreNoTrobat();
		else if (!super.socis.existeix(dni)) throw new SociInexistent();
		else {
			if (!socis.retorna(dni).max_prestec()) throw new MaximPrestecs();
			else {
				//Mirar que el llibre no estigui en reserva o prestec 
				prestecsActius.afegirPrestec(id_llibre, dni, data_ini);
			}
		}
	}
	
	public void finalitzarPrestec (String id_llibre, String dni) {
		if (!super.llibres.existeix(id_llibre)) throw new LlibreNoTrobat();
		else {
			prestecsActius.fiPrestec(id_llibre, dni, prestecsInactius);
			//num_prestecs --
			gestionaIncidencies();
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
	 * Accio que calcula la diferencia entre el dia d'inici de la reserva i el dia
	 * de finalitzacio. En el cas de que la diferencia sigui negativa es retorna una
	 * Exception. Si la diferencia es positiva es mira que sigui menor en cas dels
	 * socis o menor al valor especificat en el cas dels No_estudiants
	 * 
	 * @throws DataIncorrecta
	 */
	private void gestionaIncidencies() throws DataIncorrecta {
		int diesReserva = 15;
		long diferencia = getDifferenceDays(data_ini, data_fi);
		// Mirem que la diferencia no sigui negativa
		if (diferencia < 0) {
			data_fi = new Date(0);
			throw new DataIncorrecta();
		}
		// Mirem si el dni es d'un soci
		else if (LlistaSocis.trobaSoci(dni)) {
			Socis soci = LlistaSocis.retornaSoci(dni);
			Llibre llibre = LlistaLlibres.retornaLlibre(id_llibre);
			// Si el llibre es cientific sobreescribim els dies de reserva
			if (llibre instanceof Llibre_Cientific)
				diesReserva = ((Llibre_Cientific) llibre).getDiesReserva();

			if (diferencia > diesReserva)
				soci.afegeixIncidencia(); // Pte Ivan o yops
			// OP2 Llista_Socis.afegeixIncidencia(dni);
			else {
				if (soci instanceof No_estudiant)
					((No_estudiant) soci).afegeixPunts(); // Pte Ivan
			}
		}
	}
}