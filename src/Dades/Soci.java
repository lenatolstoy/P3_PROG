package Dades;

import java.util.Date;

public class Soci {
	// variables
	final private int maxNumeroPrestecs = 5;
	protected String DNI;
	protected String nom;
	protected Date data_naixement;
	protected Date data_alta;
	protected int incidencias;
	protected int num_prestec;

	// constructor

	/**
	 * Primer constructor
	 * 
	 * @param DNI
	 * @param nom
	 * @param data_naixement
	 */
	public Soci(String DNI, String nom, Date data_naixement) {
		this.DNI = DNI;
		this.nom = nom;
		this.data_naixement = data_naixement;
		data_alta = new Date();
		incidencias = 0;
		num_prestec = 0;
	}

	/**
	 * Segon constructor
	 * 
	 * @param DNI
	 * @param nom
	 * @param data_naixement
	 * @param data_alta
	 * @param incidencias
	 * @param num_prestec
	 */
	public Soci(String DNI, String nom, Date data_naixement, Date data_alta, int incidencias, int num_prestec) {
		this.DNI = DNI;
		this.nom = nom;
		this.data_naixement = data_naixement;
		this.data_alta = data_alta;
		this.incidencias = incidencias;
		this.num_prestec = num_prestec;
	}

	// Getters i Setters
	/**
	 * Retorna el DNI
	 * 
	 * @return DNI
	 */
	public String getDNI() {
		return DNI;
	}

	/**
	 * Retorna el nom
	 * 
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Actualitza el nom
	 * 
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Retorna la data de naixement
	 * 
	 * @return data_naixement
	 */
	public Date getData_naixement() {
		return data_naixement;
	}

	/**
	 * retorna la data de alta
	 * 
	 * @return data_alta
	 */
	public Date getData_alta() {
		return data_alta;
	}

	/**
	 * Modifica la data de naixement
	 * 
	 * @param data_naixement
	 */
	public void setData_naixement(Date data_naixement) {
		this.data_naixement = data_naixement;
	}

	/**
	 * Retorna el numero de incidencias
	 * 
	 * @return incidencias
	 */
	public int getIncidencias() {
		return incidencias;
	}

	/**
	 * Modifica el numero de incidencias
	 * 
	 * @param incidencias
	 */
	public void setIncidencias(int incidencias) {
		this.incidencias = incidencias;
	}

	/**
	 * Retorna el numero de prestecs
	 * 
	 * @return num_prestec
	 */
	public int getNum_prestecs() {
		return num_prestec;
	}

	/**
	 * Retorna el numero max de prestecs
	 * 
	 * @return maxNumeroPrestecs
	 */
	public int getNum_max_prestec() {
		return maxNumeroPrestecs;
	}

	// Metodes
	/**
	 * Retorna boolean si prestecs > max
	 * 
	 * @return
	 */
	public boolean max_prestec() {
		return (num_prestec >= maxNumeroPrestecs);
	}

	/**
	 * Modifica num_prestec +1
	 */
	public void prestecUP() {
		num_prestec++;
	}

	/**
	 * Modifica num_prestec -1
	 */
	public void prestecDown() {
		num_prestec--;
	}

	@Override
	/**
	 * Metode to string Return string
	 */
	public String toString() {
		return "DNI: " + DNI + ", nom: " + nom + ", data_naixement: " + data_naixement
				+ ", data d'alta a la biblioteca: " + data_alta + ", incidencies: " + incidencias
				+ ", nombre de llibres que te en prestec: " + num_prestec;
	}

	/**
	 * Modifica Incidencias +1
	 */
	public void incidenciaUP() {
		incidencias++;
	}

}// final de clase