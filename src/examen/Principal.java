package examen;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		// Variable donde se almacenará el código
		int codigo = 0;

		// Variable donde se almacenará la letra
		char letra = 0;
		
		// Variable auxiliar que almacena si el código introducido es un valor correcto o no
		boolean valorCorrecto;

		// Creamos el objeto Scanner
		Scanner sc = new Scanner(System.in);

		// Desordenamos el abecedario
		Criptograma.desordenaAbecedario();

		// Seleccionamos una frase con la que jugar
		Criptograma.eligeFrase();
		
		// Codificamos la frase seleccionada
		Criptograma.codificaFrase();

		// Bucle que se ejecuta mientras el usuario no adivine la frase
		do {
			// Asignamos el valor del auxiliar a false
			valorCorrecto = false;
			
			// Imprimimos la frase codificada
			System.out.println("La frase codificada es:\n" + Criptograma.fraseCodificada);

			// Bucle que pide el código al usuario mientras introduzca un valor erróneo
			do {
				try {
					// Le pedimos al usuario código y lo almacenamos
					System.out.println("Introduzca el código:");
					codigo = sc.nextInt();

					// Cambiamos el valor a true del booleano para salir del bucle
					valorCorrecto = true;
				} catch (InputMismatchException e) {
					// Mostramso el mensaje de valor incorrecto
					System.out.println("Valor incorrecto");
				} finally {
					// Limpiamos el Scanner
					sc.nextLine();
				}
			} while (!valorCorrecto);

			// Le pedimos la letra al usuario
			System.out.println("Introduzca la letra");

			// Leemos la letra, pasándola antes a minúscula
			letra = sc.next().toLowerCase().charAt(0);
			
			// Comprobamos si el usuario ha acertado y mostramose el mensaje
			if (Criptograma.compruebaCodigo(codigo, letra)) {
				System.out.println("¡Has acertado!");
			} else {
				System.out.println("Vaya... no has acertado");
			}

		} while (!Criptograma.sonIguales());

		// Mostramos el mensaje de enhorabuena y la frase real
		System.out.println("¡Enhorabuena, acertaste la frase!\n" + "La frase es:\n" + Criptograma.fraseReal);
		
		// Cerramos el Scanner
		sc.close();
	}
}
