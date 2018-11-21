package ConexionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.Cliente;
import utils.Conexion;

public class ConexionCliente {
	public int addCliente(Cliente cli){
		int valor=-1;
		Connection con=null;
		PreparedStatement pstm=null;
		try {
			con= new Conexion().getConexion();
			String sql= "insert into Clientes values (null,?,?,?,?,?,?)";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, cli.getCodigo());
			pstm.setInt(2,cli.getDNI());
			pstm.setString(3,cli.getNombre() );
			pstm.setString(4, cli.getApellido());
			pstm.setInt(5, cli.getTelefono());
	
			valor =pstm.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No REGISTRADO cliente");
			
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return valor;
	}

}
