package evaluacion1;

import java.util.Scanner;

public class XIV_Fecha {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int dia,mes,a�o;
		// para que una fecha sea correcta se tiene que cumplir
		// d�a en el rango 1..30
		// mes en el rango 1..12
		// a�o cualquiera distinto del 0
		Scanner teclado = new Scanner(System.in);
		
		System.out.print("Introduzca d�a: ");
		dia=teclado.nextInt();
		
		System.out.print("Introduzca mes: ");
		mes=teclado.nextInt();
		
		System.out.print("Introduzca a�o: ");
		a�o=teclado.nextInt();
		
		if (dia >= 1 && dia <=30) {}
		else {
			System.out.println("D�a incorrecto");
		}
		
		if (mes >= 1 && mes <= 12) {}
		else {
			System.out.println("Mes incorrecto");
		}
		
		if (a�o != 0) {
			System.out.println ("Fecha correcta");
		}
		else {
			System.out.println ("A�o incorrecto");
		}
		
		teclado.close();
			
	}

}
