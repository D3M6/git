package evaluacion2;

import java.util.Scanner;

public class Fecha {
	private int dia;
	private int mes;
	private int a�o;
	
	// Metodos
	// Creacion de un constructor publico por defecto
	Fecha(){
		this.dia = 10;
		this.mes = 11;
		this.a�o = 1999;
	}
	
	// Creacion de un constructor publico personalizado
	public Fecha(int d, int m, int a){
		this.dia = d;
		this.mes = m;
		this.a�o = a;
	}
	
	// Creacion de un constructor publico copiado
	public Fecha(Fecha f){
		this.dia = f.dia;
		this.mes = f.mes;
		this.a�o = f.a�o;
	}
	
	// ToString
	@Override
	public String toString() {
		// Salida de texto
		return (this.dia+"/"+this.mes+"/"+this.a�o);
	}

	// Setter and Getter
	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getA�o() {
		return a�o;
	}

	public void setA�o(int a�o) {
		this.a�o = a�o;
	}

	public void leerf(Scanner teclado) {
		// lee por teclado las propiedades
		// leo dia
		System.out.println("Dia :");
		this.dia = teclado.nextInt();
		// leo mes
		System.out.println("Mes :");
		this.mes = teclado.nextInt();
		// leo a�o
		System.out.println("A�o :");
		this.a�o = teclado.nextInt();
	}

	
}
