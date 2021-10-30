package dao;

import java.sql.SQLException;
import java.util.List;

import tierraMedia.Atraccion;
import tierraMedia.Promocion;

public interface PromocionDAO extends GenericDAO<Promocion> {
	public List<Promocion> findAll(List<Atraccion> todasLasAtracciones) throws SQLException;
	public Promocion findByNombre(String nombre) throws SQLException;
}
