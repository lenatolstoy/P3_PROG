package Dades;

import java.util.Arrays;

public class Llibre {

	private final int DIES_RESERVA = 15;

	protected String titol;
	protected String[] autors;
	protected String tema;
	protected static String[] temes;
	protected int num_edicio;
	protected int any_edicio;
	protected String codi;
	protected static int comptador = 0;
	protected boolean actiu;

	public int getDIES_RESERVA() {
		return DIES_RESERVA;
	}

	/**
	 * @param DIES_RESERVA
	 *            (int) Constant que ens indica els dies de reserva d'un llibre.
	 * @param titol
	 *            (String) Titol del llibre.
	 * @param autors
	 *            (Vector de Strings) Autor o autors del llibre.
	 * @param tema
	 *            (String) Tematica del llibre.
	 * @param temes
	 *            (Vector de Strings) Llista de temes que pot tenir un llibre.
	 * @param num_edicio
	 *            (int) Quina edició es la del llibre.
	 * @param any_edicio
	 *            (int) En quin any es va editar el llibre.
	 * @param codi
	 *            (String) Codi identificatiu del llibre.
	 * @param disponible
	 *            (Boolean) Ens indica si el llibre esta disponible o no.
	 * @param comptador
	 *            (int) s'utilitza per implementar el codi.
	 */
	public Llibre(String titol, String[] autors, String tema, int num_edicio, int any_edicio) {

		this.titol = titol;
		this.autors = autors;
		this.tema = tema;
		this.num_edicio = num_edicio;
		this.any_edicio = any_edicio;
		comptador++;
		this.codi = generarCodi(titol, autors[0], comptador);

		// Quan s'elimina un llibre simplement s'inactiva (o es posa com a no disponible
		// -> tenerlo en cuenta a la hora de hacer reservas -> CRIS)
		actiu = true;
	}

	public void setActiu(boolean actiu) {
		this.actiu = actiu;
	}

	/**
	 * Getters
	 */

	public boolean isActiu() {
		return actiu;
	}

	/**
	 * Retorna el titol del llibre
	 * 
	 * @return titol
	 */
	public String getTitol() {
		return (titol);
	}

	/**
	 * Retorna els autors del llibre
	 * 
	 * @return string amb els autors del llibre
	 */
	public String[] getAutors() {
		return (autors);
	}

	/**
	 * Retorna el tema del llibre
	 * 
	 * @return tema
	 */
	public String getTema() {
		return (tema);
	}

	/**
	 * Retorna tots els temes que pot tindre un llibre
	 * 
	 * @return temes
	 */
	public String[] getTemes() {
		return (temes);
	}

	/**
	 * Retorna el nomre de edicio del llibre
	 * 
	 * @return num_edicio
	 */
	public int getNumEdicio() {
		return (num_edicio);
	}

	/**
	 * Retorna el any en que es va editar el llibre
	 * 
	 * @return any_edicio
	 */
	public int getAnyEdicio() {
		return (any_edicio);
	}

	/**
	 * Retorna el codi del llibre
	 * 
	 * @return codi
	 */
	public String getCodi() {
		return (codi);
	}

	/**
	 * Setters
	 */

	public void setNum_edicio(int num_edicio) {
		this.num_edicio = num_edicio;
	}

	public void setAnyEdicio(int any_edicio) {
		this.any_edicio = any_edicio;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public void setAutor(String autor) {
		String[] autors = new String[this.autors.length + 1];

		for (int i = 0; i < this.autors.length; i++) {
			autors[i] = this.autors[i];
		}
		autors[this.autors.length + 1] = autor;
		this.autors = autors;
	}

	public void setAutors(String[] autors) {
		this.autors = autors;
	}

	public void setTema(String tema) {

		this.tema = tema;

		if (!comprovarTema(tema)) {
			String[] aux = new String[temes.length + 1];

			// Copiem tot el contingut de la llista en l'auxiliar
			for (int i = 0; i < temes.length; i++) {
				aux[i] = temes[i];
			}
			aux[aux.length - 1] = tema;
		}
	}

	public static void setTemes(String[] temes) {
		Llibre.temes = temes;
	}

	public void setCodi(String codi) {
		this.codi = codi;
	}

	public void setNouTema(String tema) {

		// Exception comrpova tema
	}

	/**
	 * Mètode que duplica la instància del llibre
	 * 
	 * @return duplicat
	 */

	/*
	 * Creem un nou objecte perque si nomes copiem la referencia si en modifiquem un
	 * l'altre tambe es modificara perque son el mateix.
	 */
	public Llibre Duplicat() {
		Llibre aux = new Llibre(this.titol, this.autors, this.tema, this.num_edicio, this.any_edicio);
		return (aux);
	}

	/**
	 * Metode toString et printa tota la informacio del Llibre
	 * 
	 * @return String
	 */

	public String toString() {
		return ("Titol: " + titol + "\n" + "Autors: " + Arrays.toString(autors) + "\n" + "Tema: " + tema + "\n"
				+ "Numero d'edició: " + num_edicio + "\n" + "Any d'edició: " + any_edicio + "\n" + "Codi: " + codi);
	}

	/**
	 * Funcions auxiliars
	 */

	/**
	 * @param titol
	 *            (String) El titol del llibre
	 * @param autors
	 *            (String) El primer autor del llibre
	 * @param comptador
	 *            (int) El nombre del llibre ingresat Retorna el codi del llibre
	 * @return codi
	 */

	private String generarCodi(String titol, String autors, int comptador) {
		String codi = new String("");
		codi = codi + titol.charAt(0) + titol.charAt(1) + titol.charAt(2) + autors.charAt(0) + autors.charAt(1)
				+ autors.charAt(2) + comptador;
		return (codi);
	}

	/**
	 * 
	 * @param tema
	 * @return un boolea que ens indica si el tema ya existeix en la llista de temes
	 */

	private boolean comprovarTema(String tema) {

		boolean hiEs = false;

		for (int i = 0; (i < temes.length) && (hiEs == false); i++) {

			hiEs = (temes[i] == tema);
		}
		return hiEs;
	}

	/**
	 * Mira si el codi passat per parametre es un codi hauria d'anar ABANS del codi
	 * del objecte
	 * 
	 * @param codi
	 * @return un boolea que ens indica si el codi del llibre hauria d'anar despres
	 *         del que li passem per parametre alfabeticament
	 */

	public boolean comprovaAlfabetic(String codi) {
		boolean trobat = false, surt_bucle = false;
		char comp1, comp2;

		if (codi.length() > this.codi.length()) {
			trobat = true;
		} else if (codi.length() < this.codi.length()) {
			trobat = false;
		} else {
			for (int i = 0; i < codi.length() && trobat == false && surt_bucle == false; i++) {
				comp1 = codi.charAt(i);
				comp2 = this.codi.charAt(i);

				comp1 = Character.toUpperCase(comp1); /* ho paso a majuscules per comparar-ho be */
				comp2 = Character.toUpperCase(comp2);

				trobat = (comp1 < comp2); /*
											 * Si this.codi te una B i codi una A vol dir que el codi anira abans de
											 * this.codi (codi < this.codi)
											 */
				surt_bucle = !(comp1 > comp2); /* Si la lletra de this.codi es A i la de codi es B) */
			}
		}
		return trobat;
	}

	public static void afegirTematica(String tema) {

	}
}
