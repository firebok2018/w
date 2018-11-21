package ConexionDatabase;

import java.awt.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Mesa;
import model.Previlegio;
import model.TipoMesa;
import utils.Conexion;

public class ConexionMesa {
	public ArrayList<Mesa> listarMesas(){
		ArrayList<Mesa> numer= new ArrayList<Mesa>();
		Connection con = null;
		PreparedStatement pst= null;
		ResultSet rs= null;
		
		try {
			con = new Conexion().getConexion();
			String sql="select*from NumMesa";
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			Mesa m = null;
			
			while (rs.next()) {
				m= new Mesa();
				m.setIdMesa(rs.getInt("ID"));
				m.setNumeromesa(rs.getInt("Numero de Mesa"));
				m.setNumerosillas(rs.getInt("Numero de Sillas"));
				numer.add(m);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				con.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return numer;
	}
	
	
	
	public void add_mesa(Mesa m,int mesa,int silla){
			
		Connection con= null;
		CallableStatement cst= null;
		try {
			con=new Conexion().getConexion();
			cst = con.prepareCall("CALL add_mesa (?,?)");
			cst.setInt(1, mesa);
			cst.setInt(2, silla);
			cst.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error");
			
		}finally {
			try {
				con.close();
				//rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	public void numeroMesa(JComboBox m){
		DefaultComboBoxModel<Mesa> model;
		Statement st= null;
		Connection cn = null;
		ResultSet rs = null;
		try {
			cn = new Conexion().getConexion();
			st= cn.createStatement();
			rs= st.executeQuery("select*from NumMesa");
			model = new DefaultComboBoxModel<>();
			m.setModel(model);
			
			while (rs.next()) {
				model.addElement(new Mesa(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.setNextException(e);
			System.out.println("eeor de listado");
		}
		finally {
			try {
				cn.close();
				st.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void TipoMesa(JComboBox t){
		DefaultComboBoxModel<TipoMesa> model;
		Statement st= null;
		Connection cn = null;
		ResultSet rs = null;
		try {
			cn = new Conexion().getConexion();
			st= cn.createStatement();
			rs= st.executeQuery("select*from TipoMesa;");
			model = new DefaultComboBoxModel<>();
			t.setModel(model);
			
			while (rs.next()) {
				model.addElement(new TipoMesa(rs.getInt(1), rs.getString(2)));
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.setNextException(e);
			System.out.println("eeor de listado");
		}
		finally {
			try {
				cn.close();
				st.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	public ArrayList<Mesa> getByNumeroMesa(String num){
		Connection con= null;
		CallableStatement call= null;
		ResultSet rs=null;
		int z=Integer.parseInt(num);
		ArrayList<Mesa> mesas= new ArrayList<>();
		try {
			con= new Conexion().getConexion();
			String sql_sp ="{CALL NUMERO_MESA (?)}";
			call= con.prepareCall(sql_sp);
			call.setInt(1, z);
			rs=call.executeQuery();
			Mesa x;
			
			if (rs.next()) {
				x= new Mesa();//crear codigo de mesa no ona my numero
				x.setIdMesa(rs.getInt("ID"));
				x.setNumeromesa(rs.getInt("Numero de Mesa"));
				x.setNumerosillas(rs.getInt("Numero de Sillas"));
				mesas.add(x);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("No se pudo mostar el info de la mesa");
		}finally{
			try {
				if (rs!=null) rs.close();
				if (call!=null) call.close();
				if (con!=null) con.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		
		return mesas;
	}
	/*
	public void loadDato(DefaultTableModel modelo){
		Connection con  = null;
		Statement stm= null;
		ResultSet rs= null;
		ResultSetMetaData rsmd=null;
		try {
			con= new Conexion().getConexion();
			stm=con.createStatement();
			String sql="select*from NumMesa";
			rsmd=rs.getMetaData();
			int columnas=rsmd.getColumnCount();
			for (int i = 0; i <=columnas; i++) {
				modelo.addColumn(rsmd.getColumnLabel(i));
			}
			while (rs.next()) {
				Object[] fila = new Object[columnas];
				for (int i = 0; i < columnas; i++) {
					fila[i]=rs.getInt(i+1);
					modelo.addRow(fila);
				}
				
			}
			
			rs=stm.executeQuery(sql);
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error de cargar datos");
		}
	}*/
}