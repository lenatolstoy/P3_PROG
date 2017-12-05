package Dades;

import java.util.Date;

public class Soci {
	//variables
	final private int maxNumeroPrestecs = 5;
	protected String DNI;
	protected String nom;
	protected Date data_naixement;
	protected Date data_alta;
	protected int incidencias;
	protected int num_prestec;
	
	//constructor
	public Soci(String DNI, String nom, Date data_naixement) {
		this.DNI = DNI;
		this.nom = nom;
		this.data_naixement = data_naixement;
		data_alta = new Date();
		incidencias = 0;
		num_prestec = 0;
	}
	
	public Soci(String DNI, String nom, Date data_naixement, Date data_alta, int incidencias, int num_prestec) {
		this.DNI = DNI;
		this.nom = nom;
		this.data_naixement = data_naixement;
		this.data_alta = data_alta;
		this.incidencias = incidencias ;
		this.num_prestec = num_prestec;
	}
	
	
	// Getters i Setters
	public String getDNI() {
		return DNI;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getData_naixement() {
		return data_naixement;
	}
	
	public Date getData_alta() {
		return data_alta;
	}

	public void setData_naixement(Date data_naixement) {
		this.data_naixement = data_naixement;
	}

	public int getIncidencias() {
		return incidencias;
	}

	public void setIncidencias(int incidencias) {
		this.incidencias = incidencias;
	}

	public int getNum_prestec() {
		return num_prestec;
	}
	
	public int getNum_max_prestec() {
		return maxNumeroPrestecs;
	}

	
	//Metodes
	public boolean max_prestec(){
		return getNum_prestec() < getNum_max_prestec();
	}
	
	public void prestecUP() {
		num_prestec++;
	}
	
	public void prestecDown() {
		num_prestec--;
	}

	@Override
	public String toString() {
		return "Soci [DNI=" + DNI + ", nom=" + nom + ", data_naixement="
				+ data_naixement + ", data_alta=" + data_alta + ", incidencias=" + incidencias + ", num_prestec="
				+ num_prestec + "]";
	}


	
	
	
	
	
	
	
}//final de clase