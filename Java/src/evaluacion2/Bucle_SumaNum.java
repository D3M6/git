package evaluacion2;

import java.util.Scanner;

public class Bucle_SumaNum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Pedir n�meros hasta que se teclee un 0, 
		// mostrar la suma de todos los n�meros introducidos.
		
		// Necesidades previas
		int num,suma;
		suma=0;
		
		// Crear variable
		Scanner teclado = new Scanner(System.in);
		
		do {
			// Leer n�mero
			System.out.print("Introduzca un n�mero: ");
			num=teclado.nextInt();
			suma=suma+num;
		}
		while(num!=0);
		
		// Cerrar teclado
		teclado.close();
		
		// Sacar texto o soluci�n)
		System.out.println("La suma de todos los n�meros es: "+suma);
	}

}
