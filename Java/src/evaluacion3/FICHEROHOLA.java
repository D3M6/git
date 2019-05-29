package evaluacion3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FICHEROHOLA {

	public static void main(String[] args) {

		FileWriter fichero = null;
		PrintWriter pw = null;
		BufferedWriter bw = null;
		
		try {
			fichero = new FileWriter("Prueba.txt");
			pw = new PrintWriter(fichero);
			bw = new BufferedWriter(pw);
			//escribo una lineas
			bw.write("Hola mundo.");
			bw.newLine();
			bw.write("Qu� tal?");
			bw.write("Bien y t�?");
			bw.newLine();
			bw.write("Bien y t�?");
			//cierro los recursos utilizados
			bw.close();
			pw.close();
			fichero.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		//leo datos desde el fichero
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			archivo = new File ("Prueba.txt");
			fr = new FileReader (archivo);
			br = new BufferedReader (fr);
			String linea;
			while((linea=br.readLine())!=null) {
				System.out.println(linea);
			}
			//cierro los recursos utilizados
			br.close();
			fr.close();
		}
		catch (IOException e) {
			// TODO Auto-generated method stub
			e.printStackTrace();
		}

	}

}
