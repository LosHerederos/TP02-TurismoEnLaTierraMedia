package dao;

import java.sql.SQLException;

import tierraMedia.Atraccion;

public interface AtraccionDAO extends GenericDAO<Atraccion> {

	public Atraccion findByNombre(String nombre) throws SQLException;
}
