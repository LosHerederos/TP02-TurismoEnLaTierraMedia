package dao;

import java.sql.SQLException;
import java.util.List;

import tierraMedia.Atraccion;
import tierraMedia.Itinerario;
import tierraMedia.Promocion;

public interface ItinerarioDAO extends GenericDAO<Itinerario> {
	public boolean existeAtraccionDeItinerario(int idItinerario, int idAtraccion) throws SQLException;
	public boolean existePromocionDeItinerario(int idItinerario, int idPromocion) throws SQLException;
	public void findAtracciones(Itinerario itinerario, List<Atraccion> todasLasAtracciones) throws SQLException;
	public void findPromociones(Itinerario itinerario, List<Promocion> todasLasPromociones) throws SQLException;
}
