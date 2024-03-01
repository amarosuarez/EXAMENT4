package examen;

import java.util.Random;

/**
 * Clase que contiene los métodos del juego del Criptograma
 * 
 * @author Amaro
 * @version 1.0
 */
public class Criptograma {

	/**
	 * Atributo estático de tipo array bidimensional 
	 * de char que almacena las letras del abecedario
	 */
	static char[][] abecedario = {
			{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'},
			{'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q'},
			{'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'}
	};
	
	/**
	 * Atributo estático de tipo array unidimensional
	 * de String que almacena las frases a usar
	 */
	static String[] frases = {
			"las guerras seguiran mientras el color de la piel siga siendo mas importante que el de los ojos",
			"aprende a vivir y sabras morir bien",
			"cada dia sabemos mas y entendemos menos",
			"el dinero no puede comprar la vida",
			"la verdadera sabiduria esta en reconocer la propia ignorancia"
	};
	
	/**
	 * Atributo estático de tipo String que almacena
	 * la frase con la que se va a jugar
	 */
	static String fraseReal = "";
	
	/**
	 * Atributo estático de tipo String que almacena
	 * la codificación de la frase real
	 */
	static String fraseCodificada = "";
	
	/**
	 * Constructor vacío
	 */
	public Criptograma() {}
	
	/**
	 * Función que desordena el array 'abecedario'
	 */
	static void desordenaAbecedario() {
		// Variable que nos servirá como auxiliar a la hora de desordenar el array
		char aux;
		
		// Variable donde se almacenará la fila aleatoria
		int fila;
		
		// Variable donde se almacenará la columna aleatoria
		int columna;
		
		// Creamos el objeto Random
		Random rand = new Random();
		
		// Bucles para recorrer las filas del array
		for (int i = 0; i < abecedario.length; i++) {
			// Bucle para recorrer las columnas del array
			for (int j = 0; j < abecedario[i].length; j++) {
				// Obtenemos la letra actual
				aux = abecedario[i][j];
				
				// Obtenemos una fila aleatoria
				fila = rand.nextInt(0, abecedario.length);
				
				// Obtenemos una columna aleatoria
				columna = rand.nextInt(0, abecedario[i].length);
				
				// Almacenamos la letra aleatoria en la posición actual
				abecedario[i][j] = abecedario[fila][columna];
				
				// Almacenamos la letra actual, guardada en auxiliar en la posición en aleatoria
				abecedario[fila][columna] = aux;
			}
		}
	}
	
	/**
	 * Función que selecciona una frase al azar del array 'frases' y
	 * se la asigna a 'fraseReal'
	 */
	static void eligeFrase() {
		// Variable donde se almacenará el número generado aleatoriamente
		int num;
		
		// Creamos el objeto Random
		Random rand = new Random();
		
		// Generamos el número aleatorio
		num = rand.nextInt(0, frases.length);
		
		// Le asignamos a fraseReal la frase seleccionada aleatoriamente
		fraseReal = frases[num];
	}
	
	/**
	 * Función que obtiene la letra actual, su código en el abecedario
	 * y lo añade a la frase codificada
	 */
	static void codificaFrase() {
		// Variable donde se almacenará la letra
		char letra;
		
		// Variable donde se almacenará la posición
		String posicion = "";
		
		// Bucle para recorrer cada posición de fraseReal
		for (int i = 0; i < fraseReal.length(); i++) {
			// Obtenemos el caracter de la posición actual
			letra = fraseReal.charAt(i);
			
			// Comprobamos si el caracter es un espacio o no
			if (letra != ' ') {
				/* Si es diferente al espacio, llamamos a la función auxiliar que busca en el array
				 abecedario y nos devuelve la posición (x, y) como String */
				posicion = busca(letra);
				
				// Concatenamos el codigo seguido de un espacio en la frase codificada
				fraseCodificada += posicion + " ";
			} else {
				// Si es un espacio, lo concatenamos en la frase codificada
				fraseCodificada += letra;
			}
		}
		
	}
	
	/**
	 * Función que obtiene el código y la letra,
	 * comprueba si el código se corresponde a la letra, consultando el array 'abecedario' y
	 * en caso afirmativo, modifica fraseCodificada
	 * 
	 * @param codigo Código de la letra en el abecedario
	 * @param letra Letra a comprobar
	 * @return Devuelve si la letra se corresponde al código en el abecedario o no
	 */
	static boolean compruebaCodigo(int codigo, char letra) {
		// Variable donde se almacenará si se corresponde con la letra o no
		boolean seCorresponde = false;
		
		// Variable donde se almacenará la parte filas del código
		int fila;
		
		// Variable donde se almacenará la parte columnas del código
		int columna;
		
		// Variable auxiliar donde se almacenará la frase con el reemplazo
		String fraseReemplazo = "";
		
		// Variable auxiliar donde se almacena el código en tipo String
		String codigoString = "";
		
		// Variable auxiliar donde se almacena la letra en tipo String
		String letraString = "";
		
		// Obtenemos la fila
		fila = codigo / 10;
		
		// Obtenemos la columna
		columna = codigo % 10;
		
		// Comprobamos si la letra se corresponde a la del abecedario desordenado
		if (letra == abecedario[fila][columna]) {
			// En caso afirmativo, cambiamos el valor del booleano a true
			seCorresponde = true;
						
			// Obtenemos el código en tipo String
			codigoString = fila+""+columna;
			
			// Obtenemos la letra en tipo String
			letraString = String.valueOf(letra);
			
			// Obtenemos el reemplazo de la frase
			fraseReemplazo = fraseCodificada.replace(codigoString, letraString);
			
			// Colocamos el reemplazo de la frase en fraseCodificada
			fraseCodificada = fraseReemplazo;
		}
		
		// Devolvemos el booleano
		return seCorresponde;
	}
	
	/**
	 * Función que comprueba si 'fraseCodificada' y 'fraseReal' son iguales
	 * 
	 * @return Devuelve si la 'fraseCodificada' y la 'fraseReal' son iguales
	 */
	static boolean sonIguales() {
		// Variable donde se almacena si las frases son iguales o no
		boolean equals = false;
		
		// Variable donde se almacenará una copia de fraseReal pero con espacios
		String fraseConEspacios = "";
		
		// Variable donde se almacenará la letra actual
		char letraActual;
		
		// Bucle para recorrer cada posición de fraseReal
		for (int i = 0; i < fraseReal.length(); i++) {
			// Obtenemos la letra actual
			letraActual = fraseReal.charAt(i);
			
			/* Concatenamos a fraseConEspacios la letra actual y un espacio siempre que detrás de esta
				no haya un espacio */
			fraseConEspacios += letraActual;
			
			/* Esta condición comprueba que la posición actual más uno no sea mayor que la longitud 
			 y que en esa posición no haya un espacio */
			if ((i+1)<fraseReal.length() && fraseReal.charAt(i+1) != ' ') {
				fraseConEspacios += " ";
			}
		}
		
		// Comprobamos si las frases son iguales, eliminando los espacios iniciales y finales en ambas frases
		if (fraseConEspacios.trim().equals(fraseCodificada.trim())) {
			// En caso afirmativo, cambiamos el valor de equals a true
			equals = true;
		}
		
		// Devolvemos si son iguales o no
		return equals;
	}
	
	
	/**
	 * Función que busca la letra en el array 'abecedario' y devuelve su código (x, y) del array 
	 * en tipo String
	 * 
	 * @param letra Caracter a buscar en el array
	 * @return Devuelve el código del array (x, y)
	 */
	static String busca(char letra) {
		// Variable que actúa como índice de las filas
		int filas = 0;
		
		// Variable que actúa como índice de las columnas
		int columnas = 0;
		
		// Variable que actua como bandera para determinar si se ha encontrado o no la letra
		boolean encontrado = false;
		
		// Variable donde se almacenará el código
		String codigo = "";
		
		// Bucle que se ejecuta mientras las filas no lleguen al final del array y no se haya encontrado la letra
		while (filas < abecedario.length && !encontrado) {
			// Inicializamos las columnas a 0
			columnas = 0;
			
			// Bucle que se ejecuta mientras las columnas no lleguen al final del array y no se haya encontrado la letra
			while (columnas < abecedario[filas].length && !encontrado) {
				// Comprobamos si la letra es igual a la de la posición actual
				if (letra == abecedario[filas][columnas]) {
					// En caso afirmativo, cambiamos el valor de encontrado a true
					encontrado = true;
					
					// Actualizamos el código
					codigo = filas + "" + columnas;
				}
				
				// Aumentamos las columnas
				columnas++;
			}
			
			// Aumentamos las filas
			filas++;
		}
		
		// Devolvemos la posición
		return codigo;
	}
}
