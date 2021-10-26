package dao;

import java.sql.SQLException;

import tierraMedia.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {
	
	public Usuario findByNombre(String nombre) throws SQLException;
}
