package evaluacion1;

import java.util.Scanner;

public class XII_Bisiesto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int anio;
		
		// Para que la consola lea lo que introducimos por teclado
		// Creamos la clase Scanner
		Scanner teclado = new Scanner(System.in);
		
		// Pedimos el numero real A
		System.out.println("Introduce el a�o que quieres comprobar: ");
		anio = teclado.nextInt();
		
		// Cerramos teclado
		teclado.close();
		
		// C�mo saber si un a�o es bisiesto o no, con esta linea de c�digo
		if ((anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0))){
			System.out.println("El a�o es bisiesto.");
		}
			
		else {
			System.out.println("El a�o no es bisiesto.");
		}
		
		/**
		// Java nos ofrece una forma todav�a m�s sencilla de resolver si un a�o es bisiesto. 
		// Y es que nos proporciona la clase GregorianCalendar y en concreto el m�todo .isLeapYear(anio).
		// Este m�todo devolver� true o false, seg�n corresponda.
		//
		//GregorianCalendar calendar = new GregorianCalendar();
		// 
		//if (calendar.isLeapYear(anio))
		//	System.out.println("El a�o es bisiesto");
		//else
		//	System.out.println("El a�o no es bisiesto");
		*/			
	}

}
