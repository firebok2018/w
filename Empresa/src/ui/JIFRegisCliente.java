package ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ConexionDatabase.ConexionCliente;
import model.Cliente;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JIFRegisCliente extends JInternalFrame implements ActionListener {
	private JLabel lblNCodigo;
	private JTextField txtCodigoCliente;
	private JLabel lblNombre;
	private JTextField txtNombreCliente;
	private JLabel lblDni;
	private JTextField txtDniCliente;
	private JLabel lblApellido;
	private JTextField txtApellidoCliente;
	private JLabel lblTelefono;
	private JTextField txtTelefono;
	private JButton btnBuscar;
	private JButton btnAceptar;
	private JButton btnVolver;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	
	private DefaultTableModel modelo;

	private int tipOperacion;
	
	public final static int ADICIONAR = 0;
	public final static int MODIFICAR = 1;
	public final static int ELIMINAR= 2;
	public final static int VOLVER=3;
	public final static int BUSCAR=4;
	public final static int ACEPTAR=5;

	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnCerrar;
	
	ConexionCliente conCli= new ConexionCliente();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIFRegisCliente frame = new JIFRegisCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JIFRegisCliente() {
		setTitle("Registro de Clientes");
		setBorder(null);
		setBounds(100, 100, 860, 539);
		getContentPane().setLayout(null);
		
		lblNCodigo = new JLabel("N\u00B0 Codigo:");
		lblNCodigo.setBounds(26, 24, 68, 14);
		getContentPane().add(lblNCodigo);
		
		txtCodigoCliente = new JTextField();
		txtCodigoCliente.setEnabled(false);
		txtCodigoCliente.setBounds(99, 21, 86, 20);
		getContentPane().add(txtCodigoCliente);
		txtCodigoCliente.setColumns(10);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(26, 60, 68, 14);
		getContentPane().add(lblNombre);
		
		txtNombreCliente = new JTextField();
		txtNombreCliente.setEnabled(false);
		txtNombreCliente.setBounds(99, 57, 287, 20);
		getContentPane().add(txtNombreCliente);
		txtNombreCliente.setColumns(10);
		
		lblDni = new JLabel("DNI:");
		lblDni.setBounds(219, 24, 46, 14);
		getContentPane().add(lblDni);
		
		txtDniCliente = new JTextField();
		txtDniCliente.setBounds(261, 21, 125, 20);
		getContentPane().add(txtDniCliente);
		txtDniCliente.setColumns(10);
		
		lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(26, 98, 46, 14);
		getContentPane().add(lblApellido);
		
		txtApellidoCliente = new JTextField();
		txtApellidoCliente.setEnabled(false);
		txtApellidoCliente.setBounds(99, 95, 287, 20);
		getContentPane().add(txtApellidoCliente);
		txtApellidoCliente.setColumns(10);
		
		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(26, 136, 46, 14);
		getContentPane().add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setEnabled(false);
		txtTelefono.setBounds(99, 133, 125, 20);
		getContentPane().add(txtTelefono);
		txtTelefono.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setVisible(false);
		btnBuscar.setIcon(new ImageIcon(JIFRegisCliente.class.getResource("/imagen/search.png")));
		btnBuscar.setBounds(445, 20, 116, 33);
		getContentPane().add(btnBuscar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setVisible(false);
		btnAceptar.setIcon(new ImageIcon(JIFRegisCliente.class.getResource("/imagen/ok.png")));
		btnAceptar.setBounds(445, 60, 116, 33);
		getContentPane().add(btnAceptar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setVisible(false);
		btnVolver.addActionListener(this);
		btnVolver.setIcon(new ImageIcon(JIFRegisCliente.class.getResource("/imagen/previous.png")));
		btnVolver.setBounds(445, 103, 116, 33);
		getContentPane().add(btnVolver);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setIcon(new ImageIcon(JIFRegisCliente.class.getResource("/imagen/plus.png")));
		btnAdicionar.setBounds(590, 20, 116, 33);
		getContentPane().add(btnAdicionar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setIcon(new ImageIcon(JIFRegisCliente.class.getResource("/imagen/engineering.png")));
		btnModificar.setBounds(590, 60, 116, 33);
		getContentPane().add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setIcon(new ImageIcon(JIFRegisCliente.class.getResource("/imagen/delete_database.png")));
		btnEliminar.setBounds(590, 103, 116, 33);
		getContentPane().add(btnEliminar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 170, 802, 311);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setIcon(new ImageIcon(JIFRegisCliente.class.getResource("/imagen/cerrar.png")));
		btnCerrar.addActionListener(this);
		btnCerrar.setBounds(726, 60, 111, 33);
		getContentPane().add(btnCerrar);

	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnAceptar) {
			actionPerformedBtnAceptar(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnCerrar) {
			actionPerformedBtnCerrar(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificar(arg0);
		}
		if (arg0.getSource() == btnAdicionar) {
			actionPerformedBtnAdicionar(arg0);
		}
		if (arg0.getSource() == btnVolver) {
			actionPerformedBtnVolver(arg0);
		}
	}
	protected void actionPerformedBtnVolver(ActionEvent arg0) {
		tipOperacion=VOLVER;
		habilitarBoton(true);
		habilitarEntrada(true);
	}
	void habilitarBoton(Boolean not){
		if(tipOperacion==ADICIONAR){
			btnAceptar.setVisible(!not);
			
			
			btnAdicionar.setEnabled(not);
			btnModificar.setEnabled(not);
			btnEliminar.setEnabled(not);
			btnVolver.setVisible(!not);
		}
		if(tipOperacion==VOLVER){
			btnAceptar.setVisible(!not);
			btnVolver.setVisible(!not);
			btnBuscar.setVisible(!not);
			btnAdicionar.setEnabled(not);
			btnModificar.setEnabled(not);
			btnEliminar.setEnabled(not);
			
		}if(tipOperacion==MODIFICAR){
			btnModificar.setEnabled(not);
			btnEliminar.setEnabled(not);
			btnAdicionar.setEnabled(not);
			btnAceptar.setVisible(!not);
			btnVolver.setVisible(!not);
			btnBuscar.setVisible(!not);
		}if(tipOperacion==ELIMINAR){
			btnEliminar.setEnabled(!not);
			btnModificar.setEnabled(!not);
			btnAdicionar.setEnabled(!not);
			btnVolver.setVisible(not);
			btnBuscar.setVisible(not);
			btnAceptar.setVisible(not);
		}
	}
	void habilitarEntrada(Boolean not){
		if(tipOperacion==ADICIONAR){
			txtNombreCliente.setEnabled(!not);
			txtApellidoCliente.setEnabled(!not);
			
			txtDniCliente.setEnabled(!not);
			txtTelefono.setEnabled(!not);
			
			
		}
		if(tipOperacion==MODIFICAR){
			txtNombreCliente.setEnabled(!not);
			txtApellidoCliente.setEnabled(!not);
			
			txtDniCliente.setEnabled(!not);
			txtTelefono.setEnabled(!not);
		}
		if(tipOperacion==VOLVER){
			txtNombreCliente.setEnabled(!not);
			txtApellidoCliente.setEnabled(!not);
			txtCodigoCliente.setEnabled(!not);
			txtDniCliente.setEnabled(!not);
			txtTelefono.setEnabled(!not);
		}
		if(tipOperacion==ELIMINAR){
			txtCodigoCliente.setEnabled(not);
		}
	}
	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		tipOperacion=ADICIONAR;
		habilitarBoton(false);
		habilitarEntrada(false);
	}
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		tipOperacion=MODIFICAR;
		habilitarBoton(false);
		habilitarEntrada(false);
	}
	protected void actionPerformedBtnCerrar(ActionEvent arg0) {
		dispose();
	}
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		tipOperacion=ELIMINAR;
		habilitarBoton(true);
		habilitarEntrada(true);
	}
	protected void actionPerformedBtnAceptar(ActionEvent arg0) {
		Cliente newCli= new Cliente(0,codigo(), dni(), nombre(), apellido(), telefono());
		int valor=conCli.addCliente(newCli);
		if(valor==1){
			System.out.println("Cliente Registrado");
		}else{
			System.out.println("no esta registrado");
		}
		
	}
	String codigo(){
		return txtCodigoCliente.getText();
	}
	int dni(){
		return Integer.parseInt(txtDniCliente.getText());
	}
	String nombre(){
		return txtNombreCliente.getText();
	}
	String apellido(){
		return txtApellidoCliente.getText();
	}
	int telefono(){
		return Integer.parseInt(txtTelefono.getText());
	}
	
}
