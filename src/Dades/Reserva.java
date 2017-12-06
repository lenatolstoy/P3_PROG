package Dades;
import java.util.Date;

/**
 * 
 * @author Cristina
 *
 */

public class Reserva {

	//Atrributs
	private String codillibre;
	private String DNI;
	//El tipus Date inclou també l'hora
	private Date data;
	private boolean activa;
	
	/**
	 * Constructor
	 * @param nomllibre String on guardem el nom del llibre que es vol reservar
	 * @param DNI String on guardem el DNI de l'usuari que vol reservar el llibre
	 * @param data del tipus Date on guardem la data en la qual es vol fer la reserva
	 */
	public Reserva(String codillibre, String DNI, Date data){
		
		this.codillibre = codillibre;
		this.DNI = DNI;
		this.data = data;
		activa = false;
		
	}
	
	
	/**
	 * Constructor per poder utilitzar per a LlegirFitxerReserves
	 * @param nomllibre String on guardem el nom del llibre que es vol reservar
	 * @param DNI String on guardem el DNI de l'usuari que vol reservar el llibre
	 * @param data del tipus Date on guardem la data en la qual es vol fer la reserva
	 * @param activa del tipus booleà ens indica si la reserva està activa o no (si ja s'ha acabat el termini en el que tens el llibre reservat)
	 */	
public Reserva(String codillibre, String DNI, Date data, boolean activa){
		
		this.codillibre = codillibre;
		this.DNI = DNI;
		this.data = data;
		this.activa = activa;
	}
	
	//Getters i setters

	/**
	 * Retorna el codi del llibre que es vol reservar
	 * @return codillibre
	 */
	public String getCodillibre() {
		return codillibre;
	}
	/**
	 * Modifica el codi del llibre que es vol reservar
	 * @param codillibre
	 */
	public void setCodillibre(String codillibre) {
		this.codillibre = codillibre;
	}
	
	/**
	 * Retorna el DNI de l'usuari que vol reservar el llibre
	 * @return DNI
	 */
	public String getDNI() {
		return DNI;
	}
	/**
	 * Modifica el DNI de l'usuari que vol reservar el llibre
	 * @param DNI
	 */
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	
	/**
	 * Retorna la data en la qual es vol reservar el llibre
	 * @return data
	 */
	public Date getData() {
		return data;
	}
	/**
	 * Modifica la data en la qual es vol reservar el llibre
	 * @param data
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * Retorna l'estat en el que es troba la reserva
	 * @return activa
	 */
	public boolean getActiva() {
		return activa;
	}
	/**
	 * Modifica l'estat en el que es troba la reserva
	 * @param activa
	 */
	public void setActiva(boolean activa) {
		this.activa = activa;
	}
	
	
	//toString
	
	/**
	 * Mètode toString
	 * @return String
	 */
	public String toString() {
		return "Reserva: [Codi del llibre: " + codillibre + "    DNI: " + DNI + "    Data reserva: " + data + "    Reserva activa: " +activa+ "]";
	}
	
	
	/**
	 * Mètode per duplicar una reserva
	 * @return reserva
	 */
	
	public Reserva Duplicat (){
		
		Reserva aux= new Reserva(this.getCodillibre(), this.getDNI(), this.getData());
		aux.activa = this.activa;
		
		return aux;	
	}

	
	
}
