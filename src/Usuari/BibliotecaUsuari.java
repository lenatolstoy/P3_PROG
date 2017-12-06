package Usuari;

import Dades.*;
import Exceptions.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BibliotecaUsuari extends Biblioteca {

	public BibliotecaUsuari() {
		super();
	}

	public boolean accedir(String dni) {
		return (socis.existeix(dni));
	}

	// Indicar tmb si estan disponibles per a ser consultats ??
	public LlistaLlibres consultaLlibresTitol(String titol) {

	}

	public LlistaLlibres consultaLlibresTema(String tema) {

	}

	public void ferPrestec(String id_llibre, String dni, String data_ini)
			throws LlibreNoTrobat, SociInexistent, MaximPrestecs {
		// Comprovem que el llibre es troba a la llista de llibres
		if (!llibres.existeix(id_llibre))
			throw new LlibreNoTrobat();

		// Comprovem que l'usuari sigui soci
		else if (!socis.existeix(dni))
			throw new SociInexistent();

		else {
			Soci soci = socis.retorna(dni);

			// Comprovem que el soci no es trobi en el seu maxim de prestecs
			if (!soci.max_prestec())
				throw new MaximPrestecs();

			else {
				Llibre llibre = llibres.retorna(id_llibre);
				int num_dies = 15;

				// Si el llibre es cientific modifiquem el nombre de dies maxims del prestec
				if (llibre instanceof Llibre_Cientific)
					num_dies = ((Llibre_Cientific) llibre).getDiesPrestec();

				// Mirar que el llibre no estigui en reserva o prestec
				if (!prestecsActius.enPrestec(id_llibre, data_ini, num_dies)
						&& !reserves.enReserva(id_llibre, data_ini)) {
					socis.retorna(dni).prestecUP();
					prestecsActius.afegirPrestec(id_llibre, dni, data_ini);
				}
			}
		}
	}

	public void ferReserva(String id_llibre, String dni, String dia) {

	}

	void finalitzarPrestec(String id_llibre, String dni) throws LlibreNoTrobat, SociInexistent {
		if (!llibres.existeix(id_llibre))
			throw new LlibreNoTrobat();
		else if (!socis.existeix(dni))
			throw new SociInexistent();
		else {
			Prestec prestec = prestecsActius.retorna(id_llibre, dni);
			Soci soci = socis.retorna(dni);
			soci.prestecDown();
			gestionaIncidencies(soci, prestec);
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
	 * Accio que calcula la diferencia entre el dia d'inici de la reserva i el dia
	 * de finalitzacio. En el cas de que la diferencia sigui negativa es retorna una
	 * Exception. Si la diferencia es positiva es mira que sigui menor en cas dels
	 * socis o menor al valor especificat en el cas dels No_estudiants
	 */
	private void gestionaIncidencies(Soci soci, Prestec prestec) {
		int diesPrestec = 15;
		long diferencia = getDifferenceDays(prestec.getData_ini(), prestec.getData_fi());
		
		// Si la diferencia es negativa l'usuari anula el prestec abans de que aquest sigui efectiu
		if (diferencia < 0) 
			diferencia = 0;
		
		Llibre llibre = llibres.retorna(prestec.getId_llibre());
		// Si el llibre es cientific sobreescribim els dies de reserva
		if (llibre instanceof Llibre_Cientific)
				diesPrestec = ((Llibre_Cientific) llibre).getDiesPrestec();

		if (diferencia > diesPrestec)
			soci.incidenciaUP();
		else 
			if (soci instanceof NoEstudiant) ((NoEstudiant) soci).puntsUP();
		}
}