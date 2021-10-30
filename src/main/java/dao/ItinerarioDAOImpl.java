package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionProvider;
import tierraMedia.Atraccion;
import tierraMedia.Itinerario;
import tierraMedia.Promocion;

public class ItinerarioDAOImpl implements ItinerarioDAO {

	@Override
	public List<Itinerario> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Itinerario findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Itinerario t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Itinerario t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Itinerario t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void findAtracciones(Itinerario itinerario, List<Atraccion> todasLasAtracciones) throws SQLException {
		String sql = "SELECT *\n"
				+ "FROM AtraccionesDeItinerario\n"
				+ "WHERE idItinerario = ?;";
		
		Connection conexion = ConnectionProvider.getConnection();
		PreparedStatement statement = conexion.prepareStatement(sql);
		statement.setInt(1, itinerario.getIdItinerario());
		ResultSet resultados = statement.executeQuery();
		
		List<Integer> idsAtracciones = new ArrayList<Integer>();
		
		while (resultados.next()) {
			Integer idAtraccion = resultados.getInt("idAtraccion");
			idsAtracciones.add(idAtraccion);
		}
		
		for (Atraccion atraccion: todasLasAtracciones) {
			Integer idAtraccion = atraccion.getIdAtraccion();
			if (idsAtracciones.contains(idAtraccion)) {
				itinerario.getAtracciones().add(atraccion);
			}
		}
	}

	@Override
	public void findPromociones(Itinerario itinerario, List<Promocion> todasLasPromociones) throws SQLException {
		String sql = "SELECT *\n"
				+ "FROM PromocionesDeItinerarios\n"
				+ "WHERE idItinerario = ?;";
		
		Connection conexion = ConnectionProvider.getConnection();
		PreparedStatement statement = conexion.prepareStatement(sql);
		statement.setInt(1, itinerario.getIdItinerario());
		ResultSet resultados = statement.executeQuery();
		
		List<Integer> idsPromociones = new ArrayList<Integer>();
		
		while (resultados.next()) {
			Integer idPromocion = resultados.getInt("idPromocion");
			idsPromociones.add(idPromocion);
		}
		
		for (Promocion promocion: todasLasPromociones) {
			Integer idPromocion = promocion.getIdPromocion();
			if (idsPromociones.contains(idPromocion)) {
				itinerario.getPromociones().add(promocion);
			}
		}
	}

	

}
