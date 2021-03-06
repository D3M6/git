package basesdedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BDUpdateAlumno {

	public static void main(String[] args) {
		
		try {
			
			// Conectar a la base de datos
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bdalumnos", "root", "");
			
			// Salida por consola
			System.out.println("Conexion correcta.");
			
			// Creo un statement
			Statement st = conexion.createStatement();
			// abro una nueva instancia del Statement
			st = conexion.createStatement();
			// actualizo el valor del grupo del alumno '11111111A' a '1DW3'
			st.executeUpdate("UPDATE alumnos SET grupo='1DW3' WHERE dni='11111111A'");
			// cierro el Statement despues de realizar la consulta
			st.close();
			
			// Creo un statement
			Statement st2 = conexion.createStatement();
			// Preparo una consulta
			String consulta = "SELECT * FROM alumnos";
			// Ejecuto la consulta
			ResultSet rs = st2.executeQuery(consulta);
			// RECORRO LOS REGISTROS DEVUELTOS
			while (rs.next()){
				System.out.println("DNI: " + rs.getObject("dni") + ", nombre: " + rs.getObject("nombre") + ", Apellidos: " + rs.getObject("apellidos") + ", Grupo: " + rs.getObject("grupo"));
			}
			// cierro el Statement despues de realizar la consulta
			st2.close();
			// cierro el ResultSet
			rs.close();
			// Cierro conexion
			conexion.close();
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}

}
