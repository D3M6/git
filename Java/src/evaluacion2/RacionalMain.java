package evaluacion2;

import java.util.Scanner;

public class RacionalMain {

	public static void main(String[] args) {
		// Clase para probar la clase Racional
		Racional r1 = new Racional(); // Llamada al constructor Racional por defecto
		System.out.println(r1);
		
		Racional r2 = new Racional(1,2); // Llamada al constructor Racional con dos valores enteros
		System.out.println(r2);
		
		Racional r3 = new Racional(3); // Llamada al constructor Racional con un valor entero
		System.out.println(r3);
		
		Racional r4 = new Racional(r2); // Llamada al constructor Racional copiado de otro contructor
		System.out.println(r4);
		
		// Getters
		double n;
		n = r2.getNumerador(); // r = 2
		System.out.println("Numerador: "+ n); // Salida de texto por pantalla
		r4.setNumerador(2);
				
		// Setters
		double d;
		d = r2.getDenominador();
		System.out.println("Denominador: "+ d); // Salida de texto por pantalla
		r4.setDenominador(4); // 2 / 4
		System.out.println(r4); // Salida de texto por pantalla
		
		// Comprobacion facil
		if (r4.equals(r2)) {
			// Si son iguales
			System.out.println("Son iguales");
		}
		else {
			System.out.println("Son diferentes");
		}
		
		// compareTo
		if (r4.compareTo(r2) > 0) {
			System.out.println(r4 + " es MAYOR que "+ r2);
		}
		else if (r4.compareTo(r2) < 0) {
			System.out.println(r4 + " es MENOR que "+ r2);
		}
		else {
			System.out.println(r4 + " es IGUAL que "+ r2);
		}
		
		// leer
		Scanner teclado = new Scanner (System.in);
		
		// leer r1
		r1.leer(teclado);
		System.out.println("Racional leido " + r1);
		
		// cerrar teclado
		teclado.close();		
		
	}

}
