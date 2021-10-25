package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionProvider;
import tierraMedia.TipoDeAtracciones;
import tierraMedia.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	public List<Usuario> findAll() throws SQLException {
		String sql = "SELECT * FROM Usuarios";
		
		Connection conexion = ConnectionProvider.getConnection();
		PreparedStatement statement = conexion.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		while(resultados.next()) {
			usuarios.add(toUsuario(resultados));
		}
		return usuarios;
	}

	public Usuario findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Usuario usuario) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(Usuario usuario) {
		
		return 0;
	}

	public int delete(Usuario usuario) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Usuario findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Usuario toUsuario(ResultSet resultado) throws SQLException {
		// nombre: text, presupuesto: integer, tiempoDisponible: real, idTipoDeAtraccion: integer
		//String nombre, int presupuesto, double tiempoDisponible,
		//TipoDeAtracciones tipoFavorito
		return new Usuario(
			resultado.getInt("idUsuario"),
			resultado.getString("nombre"),
			resultado.getInt("presupuesto"),
			resultado.getDouble("tiempoDisponible"),
			TipoDeAtracciones.values()[resultado.getInt("idTipoDeAtraccion")-1]
		);
	}

}
