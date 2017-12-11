package Personal;

import java.util.Scanner;

public class Personal {

	public static void main(String[] args) {
		Scanner lector = new Scanner(System.in);
		int op = 14;
		BibliotecaPersonal biblioteca = new BibliotecaPersonal();
		// biblioteca.llegir();
		do {
			System.out.println("\n---------MENU---------");
			System.out.println("1. Afegir llibre a la biblioteca");
			System.out.println("2. Eliminar llibre de la bilbioteca");
			System.out.println("3. Afegir tematica nova");
			System.out.println("4. Afegir soci nou");
			System.out.println("5. Donar de baixa un soci");
			System.out.println("6. Veure llibres que es troben en prestec i les dades del soci que els te");
			System.out.println("7. Consultar tematica amb mes prestecs actius");
			System.out.println("8. Consultar llibres de la biblioteca");
			System.out.println("9. Consultar socis de la biblioteca");
			System.out.println("10. Consultar prestecs actius de la biblioteca");
			System.out.println("11. Consultar prestecs inactius de la biblioteca");
			System.out.println("12. Consultar reserves de la biblioteca");
			System.out.println("13. Sortir");
			System.out.println("Trii una opcio: ");
			do {// Excepcion de que solo pueden ser enteros
				try {
					op = Integer.parseInt(lector.nextLine());
				} catch (NumberFormatException e) {
					op = 0;
				}
				if (op < 1 || op > 13)
					System.out.println("Trii una opcio correcta: ");
			} while (op < 1 || op > 13);

			switch (op) {
			case 1:
				System.out.println("\nHa escollit: afegir un llibre");
				System.out.println("Quin llibre vols guardar? (Normal -> 1 | Cientific -> 2)");
				char f = 'c';
				do {
					f = lector.nextLine().charAt(0);
					if (f != '1' && f != '2')
						System.out.println("Introdueixi 1 o 2: ");
				} while (f != '1' && f != '2');
				if (f == '1') {
					afegirLlibre(biblioteca, lector);
				}else afegirLlibreCientific(biblioteca, lector);
				
				System.out.println("Llibre afegit");
				break;
			case 2:
				System.out.println("\nHa escollit: eliminar un llibre");
				eliminarLlibre(biblioteca, lector);
				System.out.println("Llibre eliminat");
				break;
			case 3:
				System.out.println("\nHa escollit: afegir tematica");
				if(afegirTematica(biblioteca, lector)) {
					System.out.println("Tema afegit");
				}else {
					System.out.println("Aquest tema ja esta a la llista");

				}
				break;
			case 4:
				System.out.println("\nHa escollit: donar d'alta un soci");
				altaSoci(biblioteca, lector);
				System.out.println("Soci donat d'alta");
				break;
			case 5:
				System.out.println("\nHa escollit: donar de baixa un soci");
				baixaSoci(biblioteca, lector);
				System.out.println("Soci donat de baixa");
				break;
			case 6:
				System.out.println("\nHa escollit: veure els prestecs");
				System.out.println(biblioteca.llibresEnPrestec());
				System.out.println("Fi prestecs");
				break;
			case 7:
				System.out.println("\nHa escollit: veure el tema amb mes prestecs actius");
				System.out.println("El tema amb mes prestecs actius es: " + biblioteca.temaAmbMesPrestecs());
				break;
			case 8:
				System.out.println("\nHa escollit: consultar llibres");
				System.out.println(biblioteca.consultaLlibres());
				System.out.println("Fi llibres");
				break;
			case 9:
				System.out.println("\nHa escollit: consultar socis");
				System.out.println(biblioteca.consultaSocis());
				System.out.println("Fi socis");
				break;
			case 10:
				System.out.println("\nHa escollit: consultar prestecs actius");
				System.out.println(biblioteca.consultaPrestecsActius());
				System.out.println("Fi prestecs actius");
				break;
			case 11:
				System.out.println("\nHa escollit: consultar prestecs inactius");
				System.out.println(biblioteca.consultaPrestecsInactius());
				System.out.println("Fi prestecs inactius");
				break;
			case 12:
				System.out.println("\nHa escollit: consultar reserves");
				System.out.println(biblioteca.consultaReserves());
				System.out.println("Fi reserves");
				break;
			case 13:
				System.out.println("\nHa escollit: sortir");
				System.out.println("Vol guardar tots els canvis fets? (Si -> Y | No -> N)");
				char g = 'c';
				do {
					g = lector.nextLine().charAt(0);
					if (g != 'N' && g != 'Y')
						System.out.println("Introdueixi Y o N: ");
				} while (g != 'N' && g != 'Y');
				if (g == 'Y')
					biblioteca.guardar();
				break;
			}
		} while (op != 13);
		lector.close();
	}

	private static void afegirLlibre(BibliotecaPersonal biblioteca, Scanner lector) {
		String[] autors = new String[10];
		int i = 0;
		char op = 'N';
		System.out.println("Introdueixi el titol del llibre: ");
		String titol = lector.nextLine();
		do {
			System.out.println("Introdueixi autor del llibre: ");
			autors[i] = lector.nextLine();
			i++;
			if (i == 10)
				System.out.println("Nombre maxim d'autors assolit");
			else {
				System.out.println(
						"Vol continuar afegint autors? En cas afirmatiu introdueixi 'Y', si no introdueixi un altre caracter: ");
				op = lector.nextLine().charAt(0);
			}
		} while (op == 'Y' && i < 10);
		System.out.println("Introdueixi el tema del llibre: ");
		String tema = lector.nextLine();
		int num_edicio = 1;
		System.out.println("Introudeixi el numero d'edicio: ");
		try {
			num_edicio = Integer.parseInt(lector.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("No ha introduit un nombre, nombre d'edicio predefinit a 1");
		}
		System.out.println("Introdueixi l'any d'edicio: ");
		int any_edicio = 2017;
		try {
			any_edicio = Integer.parseInt(lector.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("No ha introduit un nombre, nombre d'edicio predefinit a 2017");
		}
		biblioteca.afegirLlibre(titol, autors, tema, num_edicio, any_edicio);
	}
	
	private static void afegirLlibreCientific(BibliotecaPersonal biblioteca, Scanner lector) {
		String[] autors = new String[10];
		int i = 0;
		char op = 'N';
		System.out.println("Introdueixi el titol del llibre: ");
		String titol = lector.nextLine();
		do {
			System.out.println("Introdueixi autor del llibre: ");
			autors[i] = lector.nextLine();
			i++;
			if (i == 10)
				System.out.println("Nombre maxim d'autors assolit");
			else {
				System.out.println(
						"Vol continuar afegint autors? En cas afirmatiu introdueixi 'Y', si no introdueixi un altre caracter: ");
				op = lector.nextLine().charAt(0);
			}
		} while (op == 'Y' && i < 10);
		int num_edicio = 1;
		System.out.println("Introudeixi el numero d'edicio: ");
		try {
			num_edicio = Integer.parseInt(lector.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("No ha introduit un nombre, nombre d'edicio predefinit a 1");
		}
		System.out.println("Introdueixi l'any d'edicio: ");
		int any_edicio = 2017;
		try {
			any_edicio = Integer.parseInt(lector.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("No ha introduit un nombre, nombre d'edicio predefinit a 2017");
		}
		int dies_prestec = 1;
		System.out.println("Introudeixi els dies de prestec que te el llibre: ");
		try {
			dies_prestec = Integer.parseInt(lector.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("No ha introduit un nombre, nombre d'edicio predefinit a 1");
		}
		biblioteca.afegirLlibreCientific(titol, autors, "Cientific", num_edicio, any_edicio, dies_prestec);
	}

	private static void eliminarLlibre(BibliotecaPersonal biblioteca, Scanner lector) {
		System.out.println("Introdueixi el codi del llibre a eliminar ");
		biblioteca.eliminaLlibre(lector.nextLine());
	}

	private static boolean afegirTematica(BibliotecaPersonal biblioteca, Scanner lector) {
		System.out.println("Introdueixi una tematica nova: ");
		return biblioteca.afegirTema(lector.nextLine());
	}

	private static void altaSoci(BibliotecaPersonal biblioteca, Scanner lector) {
		String dni, nom, data_naixement;
		System.out.println("Afegeixi el DNI del soci: ");
		dni = lector.nextLine();
		System.out.println("Afegeixi el nom del soci: ");
		nom = lector.nextLine();
		System.out.println("Afegeixi la data de naixement (en format dd/MM/yyyy)");
		data_naixement = lector.nextLine();
		biblioteca.afegirSoci(dni, nom, data_naixement);
	}

	private static void baixaSoci(BibliotecaPersonal biblioteca, Scanner lector) {
		System.out.println("Afegeixi el DNI del soci: ");
		biblioteca.baixaSoci(lector.nextLine());
	}
}