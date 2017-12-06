package Dades;

public abstract class Biblioteca {
	protected LlistaLlibres llibres;
	protected LlistaSocis socis;
	protected LlistaPrestecs prestecsActius;
	protected LlistaPrestecs prestecsInactius;
	protected LlistaReserves reservesActives;
	protected LlistaReserves reservesInactives;

	public Biblioteca( ) {
			llibres = new LlistaLlibres(20);
			socis = new LlistaSocis(20);
			prestecsActius = new LlistaPrestecs();
			prestecsInactius = new LlistaPrestecs();
			reservesActives = new LlistaReserves();
			reservesInactives = new LlistaReserves();
		}

	/**
	 * Getter de la llista de llibres
	 * 
	 * @return LlistaLlibres
	 */
	public LlistaLlibres getLlibres() {
		return llibres;
	}

	/**
	 * Getter de la llista de socis
	 * 
	 * @return LlistaSocis
	 */
	public LlistaSocis getSocis() {
		return socis;
	}

	/**
	 * Getter de la llista de prestecs que estiguin actius
	 * 
	 * @return LlistaPrestecs amb els prestecs actius
	 */
	public LlistaPrestecs getPrestecsActius() {
		return prestecsActius;
	}

	/**
	 * Getter de la llista de prestecs que ja no estiguin actius
	 * 
	 * @return LlistaPrestecs amb els prestecs inactius
	 */
	public LlistaPrestecs getPrestecsInactius() {
		return prestecsInactius;
	}

	/**
	 * Getter de les reserves actives
	 * 
	 * @return LlistaReserves amb les reserves actives
	 */
	public LlistaReserves getReservesActives() {
		return reservesActives;
	}

	/** 
	 * Getter de les reseves que jo no estan actives
	 * 
	 * @return LlistaReserves amb les reserves inactives
	 */
	public LlistaReserves getReservesInactives() {
		return reservesInactives;
	}
}
