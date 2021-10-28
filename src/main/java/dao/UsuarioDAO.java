package dao;

import java.sql.SQLException;
import java.util.List;

import tierraMedia.Usuario;
import tierraMedia.Atraccion;
import tierraMedia.Promocion;

public interface UsuarioDAO extends GenericDAO<Usuario> {
	
	public List<Usuario> findAll(List<Atraccion> todasLasAtracciones, List<Promocion> todasLasPromociones) throws SQLException;
	public Usuario findByNombre(String nombre) throws SQLException;
}
