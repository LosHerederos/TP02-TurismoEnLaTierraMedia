package dao;

import java.sql.SQLException;

public interface AtraccionDAO extends GenericDAO<tierraMedia.Atraccion> {

	public tierraMedia.Atraccion findByNombre(String nombre) throws SQLException;
}
