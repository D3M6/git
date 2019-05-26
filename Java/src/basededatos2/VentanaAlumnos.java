package basededatos2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAlumnos extends JFrame {

	// Necesidades previas
	private static final long serialVersionUID = -6750350292260968293L;
	private JPanel contenedor;
	private JPanel pnlBotones;
	private JPanel pnlTabla;
	private JScrollPane scpAlumnos;
	private JTable tblAlumnos;
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtGrupo;
	private JLabel lblTitulo;
	private JLabel lblDni;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblGrupo;
	private JButton bntGuardar;
	private JButton btnEliminar;
	private JButton bntModificar;
	private DefaultTableModel dtmTabla;

	// Main-Inicializador de la ventana
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAlumnos frame = new VentanaAlumnos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public VentanaAlumnos() {
		setResizable(false);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 591, 350);
		contenedor = new JPanel();
		contenedor.setBackground(Color.WHITE);
		contenedor.setBorder(null);
		contenedor.setLayout(new BorderLayout(0, 0));
		setContentPane(contenedor);
		
		lblTitulo = new JLabel("Datos de los alumnos");
		lblTitulo.setBackground(Color.WHITE);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		contenedor.add(lblTitulo, BorderLayout.NORTH);
		
		pnlBotones = new JPanel();
		pnlBotones.setBackground(Color.WHITE);
		pnlBotones.setBorder(null);
		contenedor.add(pnlBotones, BorderLayout.SOUTH);
		pnlBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		bntGuardar = new JButton("Guardar");
		bntGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnInsertarAlumnos();
			}
		});
		bntGuardar.setBackground(Color.WHITE);
		pnlBotones.add(bntGuardar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEliminarAlumnos();
			}
		});
		btnEliminar.setBackground(Color.WHITE);
		pnlBotones.add(btnEliminar);
		
		bntModificar = new JButton("Modificar");
		bntModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnModificarAlumnos();
			}
		});
		bntModificar.setBackground(Color.WHITE);
		pnlBotones.add(bntModificar);
		
		pnlTabla = new JPanel();
		pnlTabla.setBorder(null);
		pnlTabla.setBackground(Color.WHITE);
		contenedor.add(pnlTabla, BorderLayout.CENTER);
		pnlTabla.setLayout(null);
		
		scpAlumnos = new JScrollPane();
		scpAlumnos.setBounds(61, 5, 453, 185);
		pnlTabla.add(scpAlumnos);
		
		tblAlumnos = new JTable();
		tblAlumnos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JTableToJTextField();
			}
		});
		tblAlumnos.setBackground(Color.WHITE);
		scpAlumnos.setViewportView(tblAlumnos);
		
		txtDni = new JTextField();
		txtDni.setBounds(61, 222, 114, 20);
		pnlTabla.add(txtDni);
		txtDni.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(192, 222, 114, 20);
		pnlTabla.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setBounds(324, 222, 114, 20);
		pnlTabla.add(txtApellidos);
		txtApellidos.setColumns(10);
		
		txtGrupo = new JTextField();
		txtGrupo.setBounds(454, 222, 60, 20);
		pnlTabla.add(txtGrupo);
		txtGrupo.setColumns(10);
		
		lblDni = new JLabel("DNI");
		lblDni.setHorizontalAlignment(SwingConstants.CENTER);
		lblDni.setBounds(90, 202, 55, 16);
		pnlTabla.add(lblDni);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(224, 202, 55, 16);
		pnlTabla.add(lblNombre);
		
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellidos.setBounds(352, 202, 55, 16);
		pnlTabla.add(lblApellidos);
		
		lblGrupo = new JLabel("Grupo");
		lblGrupo.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupo.setBounds(456, 202, 55, 16);
		pnlTabla.add(lblGrupo);
		
		
		// Try- Catch para mostrar la tabla
		// Abro la concexion conectarse a MySQL
		try {
			// Creo la conexion
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bdalumnos", "root", "");
			// Si se ha conectado correctamente 
			// System.out.println("Conexi�n Correcta.");
			
			// Creo un Statement st = conexion.createStatement();
			Statement st = conexion.createStatement();
			// Preparo la cosulta
			String Consulta = "SELECT * FROM alumnos";
			// Ejecuto la consulta 
			ResultSet rs = st.executeQuery(Consulta);
			// Cabeceras de la tabla columnas
			Vector <String> columnas = new Vector<String>();
			columnas.add("DNI");
			columnas.add("Nombre");
			columnas.add("Apellidos");
			columnas.add("Grupo");
			
			// Creo el vector para los datos de la tabla
			Vector<Vector<String>> datosTabla = new Vector<Vector<String>>();
			
			// Agrego uno a uno los alumnos al vector de datos
			while (rs.next()) {
				Vector<String> fila = new Vector<String>();
				fila.add(rs.getString("dni"));
				fila.add(rs.getString("nombre"));
				fila.add(rs.getString("apellidos"));
				fila.add(rs.getString("grupo"));
				fila.add("\n\n\n\n\n\n\n");
				datosTabla.add(fila);
			}
			
			// Cierro el ResultSet
			rs.close();
			// Cierro el Statement despues de realizar la consulta
			st.close();
			// Cierro la conexion
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
			tblAlumnos.setModel(dtmTabla);
			// Podemos ordenar el contenido de la tabla de varias formas
			tblAlumnos.setAutoCreateRowSorter(true);
			tblAlumnos.getTableHeader().setReorderingAllowed(false);
		  				
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Se ha producido un error");
		}
		
	}
	
	// Metodo para recoger los datos de la JTable y pasarlos a JTextFields
	private void JTableToJTextField() {
		// Necesidades previas
		int rec = this.tblAlumnos.getSelectedRow();
		// Llenar los campos
		this.txtDni.setText(tblAlumnos.getValueAt(rec, 0).toString());
		this.txtNombre.setText(tblAlumnos.getValueAt(rec, 1).toString());
		this.txtApellidos.setText(tblAlumnos.getValueAt(rec, 2).toString());
		this.txtGrupo.setText(tblAlumnos.getValueAt(rec, 3).toString());
	}
	
	// Metodo para actualizar la tabla con los datos
	private void JTableActualizar() {
		try {
			// Creo la conexion
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bdalumnos", "root", "");
			// Si se ha conectado correctamente 
			// System.out.println("Conexi�n Correcta.");
			
			// Creo un Statement st = conexion.createStatement();
			Statement st = conexion.createStatement();
			// Preparo la cosulta
			String Consulta = "SELECT * FROM alumnos";
			// Ejecuto la consulta 
			ResultSet rs = st.executeQuery(Consulta);
			// Cabeceras de la tabla columnas
			Vector <String> columnas = new Vector<String>();
			columnas.add("DNI");
			columnas.add("Nombre");
			columnas.add("Apellidos");
			columnas.add("Grupo");
			
			// Creo el vector para los datos de la tabla
			Vector<Vector<String>> datosTabla = new Vector<Vector<String>>();
			
			// Agrego uno a uno los alumnos al vector de datos
			while (rs.next()) {
				Vector<String> fila = new Vector<String>();
				fila.add(rs.getString("dni"));
				fila.add(rs.getString("nombre"));
				fila.add(rs.getString("apellidos"));
				fila.add(rs.getString("grupo"));
				fila.add("\n\n\n\n\n\n\n");
				datosTabla.add(fila);
			}
			
			// Cierro el ResultSet
			rs.close();
			// Cierro el Statement despues de realizar la consulta
			st.close();
			// Cierro la conexion
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
			tblAlumnos.setModel(dtmTabla);
			// Podemos ordenar el contenido de la tabla de varias formas
			tblAlumnos.setAutoCreateRowSorter(true);
			tblAlumnos.getTableHeader().setReorderingAllowed(false);
		  				
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Se ha producido un error");
		}
	}
	
	// Metodo para limpiar los campos de texto
	private void ClearFields() {
		// Necesidades previas
		this.txtDni.setText("");
		this.txtNombre.setText("");
		this.txtApellidos.setText("");
		this.txtGrupo.setText("");
	}
	
	// Metodo insertar alumnos en la basededatos y en la tabla
	private void btnInsertarAlumnos() {
		// Conectar a la base de datos
		try {
			// Creo la conexion
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bdalumnos", "root", "");
			// Creo un Statement st = conexion.createStatement();
			Statement st = conexion.createStatement();
			// Creo la consulta
			String ConsultaInsert = "INSERT INTO alumnos VALUES ('"+this.txtDni.getText()+"','"+this.txtNombre.getText()+"','"+this.txtApellidos.getText()+"','"+this.txtGrupo.getText()+"')";
			// Ejecuto la consulta
			st.executeUpdate(ConsultaInsert);
			// cierro el Statement despues de realizar la consulta
			st.close();
			// cierro la conexion
			conexion.close();
			// Actualizo la tabla
			JTableActualizar();
			// Limpio los campos de texto
			ClearFields();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Se ha producido un error");
		}
		
	}
	
	// Metodo eliminar alumnos en la basededatos y en la tabla
	private void btnEliminarAlumnos() {
		// Conectar a la base de datos
		try {
			// Creo la conexion
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bdalumnos", "root", "");
			// Creo un Statement st = conexion.createStatement();
			Statement st = conexion.createStatement();
			// Creo la consulta
			String ConsultaDelete = "DELETE FROM alumnos WHERE dni = '"+this.txtDni.getText()+"'";
			// Ejecuto la consulta
			st.executeUpdate(ConsultaDelete);
			// cierro el Statement despues de realizar la consulta
			st.close();
			// cierro la conexion
			conexion.close();
			// Actualizo la tabla
			JTableActualizar();
			// Limpio los campos de texto
			ClearFields();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Se ha producido un error");
		}
	}
	
	// Metodo modificar alumnos en la basededatos y en la tabla
	private void btnModificarAlumnos() {
		// Conectar a la base de datos
		try {
			// Creo la conexion
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bdalumnos", "root", "");
			// Creo un Statement st = conexion.createStatement();
			Statement st = conexion.createStatement();
			// Creo la consulta
			String ConsultaUpdate = "UPDATE alumnos SET dni = '"+this.txtDni.getText()+"', nombre = '"+this.txtNombre.getText()+"', apellidos = '"+this.txtApellidos.getText()+"', grupo = '"+this.txtGrupo.getText()+"' WHERE dni = '"+this.txtDni.getText()+"'";
			// Ejecuto la consulta
			st.executeUpdate(ConsultaUpdate);
			// cierro el Statement despues de realizar la consulta
			st.close();
			// cierro la conexion
			conexion.close();
			// Actualizo la tabla
			JTableActualizar();
			// Limpio los campos de texto
			ClearFields();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Se ha producido un error");
		}
	}
	
}
