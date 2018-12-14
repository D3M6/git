package evaluacion2;

import java.util.Scanner;

public class Bucle_NegaMedia {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Necesidades previas
		int num, suma, elementos;
		float media; // la media puede tener decimales
		// num: guardar� los n�meros introducidos por el usuario
		// suma: almacenar� la suma de todos los n�meros introducidos
		// elementos: ser� un contador que indicar� el n�meros de n�meros 8o elementos) introducidos.
		
		// Crear variable
		Scanner teclado = new Scanner(System.in);
		
		System.out.print("Introduzca un n�mero: ");
		num=teclado.nextInt();
		
		// Inicializar variables
		suma= 0;
		elementos= 0;
		
		// Solo interesan los numeros positivos y el cero
		while(num>=0) {
			suma+=num;
			elementos++;
			System.out.print("Introduzca otro n�mero: ");
			num=teclado.nextInt();
		}
		
		// Cerrar teclado
		teclado.close();
		
		// Dar� un error de divisi�n por cero
		if (elementos == 0) {
			System.out.println("Imposible hacer la media.");
		}
		
		else {
		media= (float)suma/elementos;
		System.out.println("La media es de: " + media);
		}
		
	}

}
