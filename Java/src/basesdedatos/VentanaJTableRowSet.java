package basesdedatos;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaJTableRowSet extends JFrame {

	private static final long serialVersionUID = -9077118071788019311L;
	private static JPanel Contenedor;
	private JLabel lblTexto;
	private JButton btnSalir;
	private DefaultTableModel dtmTabla;
	private JTable tabla;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJTableRowSet frame = new VentanaJTableRowSet();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaJTableRowSet() {
		setTitle("VentanaJTableRowSet");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		Contenedor = new JPanel();
		Contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		Contenedor.setLayout(new BorderLayout(0, 0));
		setContentPane(Contenedor);
		
		lblTexto = new JLabel("Datos de los Alumnos");
		lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
		Contenedor.add(lblTexto, BorderLayout.NORTH);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Exit();
			}
		});
		Contenedor.add(btnSalir, BorderLayout.SOUTH);
	
		//abro la concexion conectarse a MySQL
		try {
			// me conecto usando una conexion
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bdalumnos", "root", "");
			// desactivo la actualizacion automatica de datos
			conexion.setAutoCommit(false);
			// creo el CachedRowSet
			CachedRowSet crs;
			RowSetFactory myRowSetFactory = null;
			myRowSetFactory = RowSetProvider.newFactory();
			crs = myRowSetFactory.createCachedRowSet();
			// selecciono todos los alumnos
			// usando la conexion anterior
			crs.setCommand("SELECT * FROM alumnos");
			crs.execute(conexion);
			
			
			// cabeceras de la tabla columnas
			Vector <String> columnas = new Vector<String>();
			columnas.add("DNI");
			columnas.add("Nombre");
			columnas.add("Apellidos");
			columnas.add("Grupo");
			
			// creo el vector para los datos de la tabla
			Vector<Vector<String>> datosTabla = new Vector<Vector<String>>();
			
			// a�ado uno a uno los alumnos al vector de datos
			while (crs.next()) {
				Vector<String> fila = new Vector<String>();
				fila.add(crs.getString("dni"));
				fila.add(crs.getString("nombre"));
				fila.add(crs.getString("apellidos"));
				fila.add(crs.getString("grupo"));
				fila.add("\n\n\n\n\n\n\n");
				datosTabla.add(fila);
			}	
			
			// cierro la conexion con la base de datos
			conexion.close();		
			// creo la JTable
			dtmTabla = new DefaultTableModel(datosTabla, columnas){
				private static final long serialVersionUID = -5029586722185735524L;
				@Override
				public boolean isCellEditable(int row, int column) {
				// hago que todas las celdas de la tabla NO sean editables
				return false;
				}
			};
			tabla = new JTable(dtmTabla);
			
			  
			// creo el Scroll panel y le a�ado la tabla
			JScrollPane scrollPane = new JScrollPane(tabla);
			// a�ado el Scroll panel al panel principal
			Contenedor.add(scrollPane, BorderLayout.CENTER);
			
			// Podemos ordenar el contenido de la tabla de varias formas
			tabla.setAutoCreateRowSorter(true);
		  				
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Exit() {
		// Salir de la aplicacion
		// VentanaJTableRowSet vl = new VentanaJTableRowSet();
		// vl.setVisible(false);
		dispose();
		
	}
	
}

