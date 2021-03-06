/** 
 * Practica 3. Classe Llibre.
 * 
 * 
 * @author Ivan Grima
 * @author Cristina Llort
 * @author Magdalena Tolstoy
 * @author Antonio Torres
 *
 */

package Dades;

import Exceptions.ErrorGenerarCodi;

public class Llibre {

	private final int DIES_RESERVA = 15;

	protected String titol;
	protected String[] autors;
	protected String tema;
	protected static String[] temes;
	protected int num_edicio;
	protected int any_edicio;
	protected String codi;
	protected static int comptador = 1;
	protected boolean actiu;

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
	 *            (int) Quina edici� es la del llibre.
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
		try {
			this.codi = generarCodi(titol, autors[0]);
		} catch (ErrorGenerarCodi e) {
			e.printStackTrace();
		}
		// Quan s'elimina un llibre simplement s'inactiva (o es posa com a no disponible
		// -> tenerlo en cuenta a la hora de hacer reservas -> CRIS)
		actiu = true;
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
	 *            (int) Quina edici� es la del llibre.
	 * @param any_edicio
	 *            (int) En quin any es va editar el llibre.
	 * @param codi
	 *            (String) Codi identificatiu del llibre.
	 * @param disponible
	 *            (Boolean) Ens indica si el llibre esta disponible o no.
	 * @param comptador
	 *            (int) s'utilitza per implementar el codi.
	 * @param actiu
	 *            (boolean) et diu si el llibre esta aciu o no
	 */
	public Llibre(String titol, String[] autors, String tema, int num_edicio, int any_edicio, String codi,
			boolean actiu) {

		this.titol = titol;
		this.autors = autors;
		this.tema = tema;
		this.num_edicio = num_edicio;
		this.any_edicio = any_edicio;
		this.codi = codi;

		if (actiu) {
			this.actiu = true;
		} else {
			this.actiu = false;
		}

		this.actiu = actiu;

		// Quan s'elimina un llibre simplement s'inactiva (o es posa com a no disponible
		// -> tenerlo en cuenta a la hora de hacer reservas -> CRIS)
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

	public int getDIES_RESERVA() {
		return (DIES_RESERVA);
	}

	public int getComptador() {
		return (comptador);
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
			if (temes == null) {
				String[] aux = new String[1];
				aux[0] = tema;
				temes = aux;
			} else {
				String[] aux = new String[temes.length + 1];

				// Copiem tot el contingut de la llista en l'auxiliar
				for (int i = 0; i < temes.length; i++) {
					aux[i] = temes[i];
				}
				aux[aux.length - 1] = tema;
				temes = aux;
			}
		}
	}

	public void setActiu(boolean actiu) {
		this.actiu = actiu;
	}

	public static void setTemes(String[] temes) {
		Llibre.temes = temes;
	}

	public void setCodi(String codi) {
		this.codi = codi;
	}

	public void setNouTema(String tema) {

		afegirTematica(tema);
	}

	public static void setComptador(int comptadoh) {

		comptador = comptadoh;
	}

	/**
	 * M�tode que duplica la inst�ncia del llibre
	 * 
	 * @return duplicat
	 */

	/*
	 * Creem un nou objecte perque si nomes copiem la referencia si en modifiquem un
	 * l'altre tambe es modificara perque son el mateix.
	 */
	public Llibre Duplicat() {
		Llibre aux = new Llibre(this.titol, this.autors, this.tema, this.num_edicio, this.any_edicio, this.codi,
				this.actiu);
		return (aux);
	}

	/**
	 * Metode toString et printa tota la informacio del Llibre
	 * 
	 * @return String
	 */

	public String toString() {
		int z = 0;
		String aux = "";
		while (autors[z] != (null) && z < 10) {
			aux = aux + autors[z] + ", ";
			z++;
		}

		aux = aux.substring(0, aux.length() - 2);

		return ("Titol: " + titol + "\n" + "Autors: " + aux + "\n" + "Tema: " + tema + "\n" + "Numero d'edici�: "
				+ num_edicio + "\n" + "Any d'edici�: " + any_edicio + "\n" + "Codi: " + codi + "\n");
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

	private String generarCodi(String titol, String autors) throws ErrorGenerarCodi {
		String codi = new String("");

		if (titol.charAt(0) == ' ' || titol.charAt(1) == ' ' || titol.charAt(2) == ' ' || autors.charAt(0) == ' '
				|| autors.charAt(1) == ' ' || autors.charAt(2) == ' ') {

			/*
			 * String aux2 = new String (autors); String aux1 = new String (titol); aux1 =
			 * aux1.replaceAll(" ", ""); aux2 = aux2.replaceAll(" ", ""); codi = codi +
			 * aux1.charAt(0) + aux1.charAt(1) + aux1.charAt(2) + aux2.charAt(0) +
			 * aux2.charAt(1) + aux2.charAt(2) + comptador;
			 */
			throw new ErrorGenerarCodi();
		} else {
			codi = codi + titol.charAt(0) + titol.charAt(1) + titol.charAt(2) + autors.charAt(0) + autors.charAt(1)
					+ autors.charAt(2) + comptador;
		}

		comptador++;
		return (codi);
	}

	/**
	 * 
	 * @param tema
	 * @return un boolea que ens indica si el tema ya existeix en la llista de temes
	 */

	private static boolean comprovarTema(String tema) {

		boolean hiEs = false;

		if (temes != null) {
			for (int i = 0; (i < temes.length) && (hiEs == false); i++) {
				hiEs = (temes[i].equals(tema));
			}
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

	/**
	 * Afegeix un tema a la llista de temas, si esta repetit no l'afegeix.
	 * 
	 * @param tema
	 */

	public static boolean afegirTematica(String tema) {

		boolean todo_bien = false;

		todo_bien = !(comprovarTema(tema));

		if (!comprovarTema(tema)) {
			if (temes == null) {
				String[] aux = new String[1];
				aux[0] = tema;
				temes = aux;
			} else {
				String[] aux = new String[temes.length + 1];

				// Copiem tot el contingut de la llista en l'auxiliar
				for (int i = 0; i < temes.length; i++) {
					aux[i] = temes[i];
				}
				aux[aux.length - 1] = tema;
				temes = aux;

			}
		}
		return todo_bien;

	}
}
