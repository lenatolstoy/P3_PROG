package Dades;
import java.util.Arrays;
import java.util.Date;


public class Llibres {

	protected String titol;
	protected String[] autors;
	protected String tema;
	protected static String[] temes;
	protected int num_edicio;
	protected Date any_edicio;
	protected String codi;
	protected boolean disponible;
	protected static int comptador = 0;
	/**
	 * @param titol (String) Titol del llibre.
	 * @param autors (Vector de Strings) Autor o autors del llibre.
	 * @param tema (String) Tematica del llibre.
	 * @param temes (Vector de Strings) Llista de temes que pot tenir un llibre.
	 * @param num_edicio (int) Quina edició es la del llibre.
	 * @param any_edicio (Date) En quin any es va editar el llibre.
	 * @param codi (String) Codi identificatiu del llibre.
	 * @param disponible (Boolean) Ens indica si el llibre esta disponible o no.
	 * @param comptador (int) s'utilitza per implementar el codi.
	 */
	
	public Llibres(String titol, String[] autors, String tema, int num_edicio, Date any_edicio, boolean disponible) {
		
		this.titol = titol;
		this.autors = autors;
		this.tema = tema;
		this.num_edicio = num_edicio;
		this.any_edicio = any_edicio;
		comptador++;
		this.codi = generarCodi(titol, autors[0], comptador);
		this.disponible = disponible;
	}
	
	/**
	 * Getters
	*/	
	
	/**
	 * Retorna el titol del llibre
	 * @return titol
	 */
	public String getTitol(){
		return (titol);
	}
	
	/**
	 * Retorna els autors del llibre
	 * @return string amb els autors del llibre
	 */
	public String[] getAutors(){
		return (autors);
	}
	
	/**
	 * Retorna el tema del llibre
	 * @return tema
	 */
	public String getTema(){
		return (tema);
	}
	
	/**
	 * Retorna tots els temes que pot tindre un llibre
	 * @return temes
	 */
	public String[] getTemes(){
		return (temes);
	}
	
	/**
	 * Retorna el nomre de edicio del llibre
	 * @return num_edicio
	 */
	public int getNumEdicio(){
		return (num_edicio);
	}
	
	/**
	 * Retorna el any en que es va editar el llibre
	 * @return any_edicio
	 */
	public Date getAnyEdicio(){
		return (any_edicio);
	}
	
	/**
	 * Retorna el codi del llibre
	 * @return codi
	 */
	public String getCodi(){
		return (codi);
	}
	
	/**
	 * Retorna un boolea que indica si el llibre esta disponible
	 * @return disponible
	 */
	public Boolean getDisponible(){
		return (disponible);
	}
	
	/**
	 * Setters
	*/	
	
	public void setNum_edicio(int num_edicio) {
		this.num_edicio = num_edicio;
	}


	public void setAny_edicio(Date any_edicio) {
		this.any_edicio = any_edicio;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public void setAutors(String[] autors) {
		this.autors = autors;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public static void setTemes(String[] temes) {
		Llibres.temes = temes;
	}

	public void setCodi(String codi) {
		this.codi = codi;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	/**
	 * Mètode que duplica la instància del llibre
	 * @return duplicat
	 */
	
	/*Creem un nou objecte perque si nomes copiem la referencia si en modifiquem un
	 * l'altre tambe es modificara perque son el mateix.
	 */
	public Llibres Duplicat(){
		Llibres aux = new Llibres(this.titol, this.autors, this.tema, this.num_edicio, this.any_edicio, this.disponible);
		return(aux);
	}
	
	/**
	 * Metode toString et printa tota la informacio del Llibre
	 * @return String
	 */
	
	public String toString(){
		return ("Titol: "+titol+ "\n" +
				"Autors: "+Arrays.toString(autors)+ "\n" +
				"Tema: "+tema+ "\n" +
				"Numero d'edició: "+num_edicio+ "\n" +
				"Any d'edició: "+any_edicio+ "\n" +
				"Codi: " +codi+ "\n" +
				"Esta disponible: " +disponible+ "\n");
	}
	
	/**
	 * Funcions auxiliars
	*/	

	/**
	 * Retorna el codi del llibre
	 * @return codi
	 */

	private String generarCodi(String titol, String autors, int comptador) {
		String codi = new String("");
		codi = codi + titol.charAt(0) + titol.charAt(1) + titol.charAt(2) + 
				autors.charAt(0) + autors.charAt(1) + autors.charAt(2) + comptador;
		return (codi);
	}
	
	

	
	
}


