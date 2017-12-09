package Personal;

import java.util.Scanner;

public class Personal {

	public static void main(String[] args) {
		Scanner lector = new Scanner(System.in);
		int op;
		BibliotecaPersonal biblioteca = new BibliotecaPersonal();
		biblioteca.llegir();
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

			do {// Excepcion de que solo pueden ser enteros
				try {
					op = Integer.parseInt(lector.next());
				} catch (NumberFormatException e) {
					op = 14;
				}
				if (op < 1 || op > 13)
					System.out.println("Trii una opcio correcta: ");
			} while (op < 1 || op > 13);

			switch (op) {
			case 1:
				System.out.println("\nHa escollit: afegir un llibre");
				afegirLlibre(biblioteca);
				break;
			case 2:
				System.out.println("\nHa escollit: eliminar un llibre");
				eliminarLlibre(biblioteca);
				break;
			case 3:
				System.out.println("\nHa escollit: afegir tematica");
				afegirTematica(biblioteca);
				break;
			case 4:
				System.out.println("\nHa escollit: donar d'alta un soci");
				altaSoci(biblioteca);
				break;
			case 5:
				System.out.println("\nHa escollit: donar de baixa un soci");
				baixaSoci(biblioteca);
				break;
			case 6:
				System.out.println("\nHa escollit: veure els prestecs");
				System.out.println(biblioteca.llibresEnPrestec());
				break;
			case 7:
				System.out.println("\nHa escollit: veure el tema amb mes prestecs actius");
				System.out.println("El tema amb mes prestecs actius es: "+biblioteca.temaAmbMesPrestecs());
				break;
			case 8:
				System.out.println("\nHa escollit: consultar llibres");
				System.out.println(biblioteca.consultaLlibres());
				break;
			case 9:
				System.out.println("\nHa escollit: consultar socis");
				System.out.println(biblioteca.consultaSocis());
				break;
			case 10:
				System.out.println("\nHa escollit: consultar prestecs actius");
				System.out.println(biblioteca.consultaPrestecsActius());
				break;
			case 11:
				System.out.println("\nHa escollit: consultar prestecs inactius");
				System.out.println(biblioteca.consultaPrestecsInactius());
				break;
			case 12:
				System.out.println("\nHa escollit: consultar reserves");
				System.out.println(biblioteca.consultaReserves());
				break;
			case 13:
				System.out.println("\nHa escollit: sortir");
				System.out.println("Vol guardar tots els canvis fets? (Si -> Y | No -> N)");
				char g = 'c';
				do {
					g =lector.next().charAt(0);
					if (g!='N' && g!='Y') System.out.println("Introdueixi Y o N: ");
				}while (g!='N' && g!='Y');
				if (g=='Y') biblioteca.guardar();
				break;
			}
		} while (op != 13);
		lector.close();
	}

	private static void afegirLlibre(BibliotecaPersonal biblioteca) {
		String[] autors = new String[10];
		Scanner lector = new Scanner(System.in);
		int i = 0;
		char op = 'N';
		System.out.println("Introdueixi el titol del llibre: ");
		String titol = lector.next();
		do {
			System.out.println("Introdueixi autor del llibre: ");
			autors[i] = lector.next();
			i++;
			if (i == 10)
				System.out.println("Npmbre maxim d'autors assolit");
			else {
				System.out.println(
						"Vol continuar afegint autors? En cas afirmatiu introdueixi 'Y', si no introdueixi un altre caracter: ");
				op = lector.next().charAt(0);
			}
		} while (op == 'Y' && i < 10);
		System.out.println("Introdueixi el tema del llibre: ");
		String tema = lector.next();
		int num_edicio = 1;
		System.out.println("Introudeixi el numero d'edicio: ");
		try {
			num_edicio = Integer.parseInt(lector.next());
		} catch (NumberFormatException e) {
			System.out.println("No ha introduit un nombre, nombre d'edicio predefinit a 1");
		}
		System.out.println("Introdueixi l'any d'edicio: ");
		int any_edicio = 2017;
		try {
			any_edicio = Integer.parseInt(lector.next());
		} catch (NumberFormatException e) {
			System.out.println("No ha introduit un nombre, nombre d'edicio predefinit a 2017");
		}
		biblioteca.afegirLlibre(titol, autors, tema, num_edicio, any_edicio);
		lector.close();
	}

	private static void eliminarLlibre(BibliotecaPersonal biblioteca) {
		Scanner lector = new Scanner(System.in);
		System.out.println("Introdueixi el codi del llibre a eliminar ");
		biblioteca.eliminaLlibre(lector.next());
		lector.close();
	}

	private static void afegirTematica(BibliotecaPersonal biblioteca) {
		Scanner lector = new Scanner(System.in);
		System.out.println("Introdueixi una tematica nova: ");
		biblioteca.afegirTema(lector.next());
		lector.close();
	}

	private static void altaSoci(BibliotecaPersonal biblioteca) {
		Scanner lector = new Scanner(System.in);
		String dni, nom, data_naixement;
		System.out.println("Afegeixi el DNI del soci: ");
		dni = lector.next();
		System.out.println("Afegeixi el nom del soci: ");
		nom = lector.next();
		System.out.println("Afegeixi la data de naixement (en format dd/MM/yyyy)");
		data_naixement = lector.next();
		biblioteca.afegirSoci(dni, nom, data_naixement);
		lector.close();
	}

	private static void baixaSoci(BibliotecaPersonal biblioteca) {
		Scanner lector = new Scanner(System.in);
		System.out.println("Afegeixi el DNI del soci: ");
		biblioteca.baixaSoci(lector.next());
		lector.close();
	}
}