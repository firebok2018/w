package ConexionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.Usuarios;
import utils.Conexion;

public class ConexionSign_Up extends Conexion {
	
	public int Sign_Up(Usuarios user){		
		int valor=-1;
		Connection con=null;
		PreparedStatement pstm=null;
		try {
			con= new Conexion().getConexion();
			String sql= "insert into Usuarios values (null,?,?,?,?,?,?,?,?)";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user.getUsuario());
			pstm.setString(2,user.getPassword());
			pstm.setString(3, user.getCorreo());
			pstm.setString(4, user.getNomUser());
			pstm.setString(5, user.getApeUser());
			pstm.setString(6, user.getLastSigIn());
			pstm.setString(7, user.getTimeSignIn());
			pstm.setInt(8, user.getTipo());
			
			valor =pstm.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No REGISTRADO");
			
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
		//https://gist.github.com/jujogracu/3069429

	}
	/*public int Sign_In(){
		
	}*/

}
