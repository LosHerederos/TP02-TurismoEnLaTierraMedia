package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionProvider;
import tierraMedia.Atraccion;
import tierraMedia.Promocion;
import tierraMedia.PromocionAXB;
import tierraMedia.PromocionAbsoluta;
import tierraMedia.PromocionPorcentual;

public class PromocionDAOImpl implements PromocionDAO {

	@Override
	public List<Promocion> findAll() throws SQLException {
		String sql = "SELECT *\n"
				+ "FROM Promociones\n"
				+ "LEFT JOIN PromocionAbsoluta ON (PromocionAbsoluta.idPromocion = Promociones.idPromocion)\n"
				+ "LEFT JOIN PromocionAXB ON (PromocionAXB.idPromocion = Promociones.idPromocion)\n"
				+ "LEFT JOIN PromocionPorcentual ON (PromocionPorcentual.idPromocion = Promociones.idPromocion)";

		Connection conexion = ConnectionProvider.getConnection();
		PreparedStatement statement = conexion.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		List<Promocion> promociones = new ArrayList<Promocion>();

		while (resultados.next()) {
			promociones.add(toPromocion(resultados));
		}

		return promociones;
	}

	@Override
	public Promocion findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Promocion t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Promocion t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Promocion t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public List<Promocion> findAll(List<Atraccion> todasLasAtracciones) throws SQLException {
		String sql = "SELECT\n"
				+ "	Promociones.*,\n"
				+ "	PromocionAbsoluta.idPromocionAbsoluta,\n"
				+ "	PromocionAbsoluta.descuento,\n"
				+ "	PromocionAXB.idPromocionAXB,\n"
				+ "	PromocionPorcentual.idPromocionPorcentual,\n"
				+ "	PromocionPorcentual.porcentuajeDeDescuento\n"
				+ "FROM Promociones\n"
				+ "LEFT JOIN PromocionAbsoluta ON (PromocionAbsoluta.idPromocion = Promociones.idPromocion)\n"
				+ "LEFT JOIN PromocionAXB ON (PromocionAXB.idPromocion = Promociones.idPromocion)\n"
				+ "LEFT JOIN PromocionPorcentual ON (PromocionPorcentual.idPromocion = Promociones.idPromocion)";

		Connection conexion = ConnectionProvider.getConnection();
		PreparedStatement statement = conexion.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		List<Promocion> promociones = new ArrayList<Promocion>();

		while (resultados.next()) {
			promociones.add(toPromocion(resultados));
		}
		
		for (Promocion promocion: promociones) {
			findAtracciones(promocion, todasLasAtracciones);
		}

		return promociones;
	}

	@Override
	public Promocion findByNombre(String nombre) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Promocion toPromocion(ResultSet resultado) throws SQLException {
		Promocion promocion = null;
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		
		int idPromocion = resultado.getInt("idPromocion");
		String nombre = resultado.getString("nombre");
		String tipoDePromocion = resultado.getString("tipoDePromocion");
		
		if (tipoDePromocion.equals("PromocionAbsoluta")) {
			int idPromocionAbsoluta = resultado.getInt("idPromocionAbsoluta");
			int descuento = resultado.getInt("descuento");
			
			promocion = new PromocionAbsoluta(
				idPromocion,
				idPromocionAbsoluta,
				nombre,
				atracciones,
				descuento
			);
		} else if (tipoDePromocion.equals("PromocionAXB")) {
			int idPromocionAXB = resultado.getInt("idPromocionAXB");
			List<Atraccion> atraccionesPagas = new ArrayList<Atraccion>();
//			atraccionesPagas
			
			promocion = new PromocionAXB(
				idPromocion,
				idPromocionAXB,
				nombre,
				atracciones,
				atraccionesPagas
			);
		} else if (tipoDePromocion.equals("PromocionPorcentual")) {
			int idPromocionPorcentual = resultado.getInt("idPromocionPorcentual");
			double porcentaje = resultado.getDouble("porcentuajeDeDescuento");
			
			promocion = new PromocionPorcentual(
				idPromocion,
				idPromocionPorcentual,
				nombre,
				atracciones,
				porcentaje
			);
		}
		
		return promocion;
	}
	
	private void findAtracciones(Promocion promocion, List<Atraccion> todasLasAtracciones) throws SQLException {
		String sql = "SELECT *\n"
				+ "FROM AtraccionesDePromociones\n"
				+ "WHERE idPromocion = ? AND NOT promocionNoGeneral;";
		
		Connection conexion = ConnectionProvider.getConnection();
		PreparedStatement statement = conexion.prepareStatement(sql);
		statement.setInt(1, promocion.getIdPromocion());
		ResultSet resultados = statement.executeQuery();
		
		List<Integer> idsAtracciones = new ArrayList<Integer>();
		
		while (resultados.next()) {
			Integer idAtraccion = resultados.getInt("idAtraccion");
			idsAtracciones.add(idAtraccion);
		}
		
		for (Atraccion atraccion: todasLasAtracciones) {
			Integer idAtraccion = atraccion.getIdAtraccion();
			if (idsAtracciones.contains(idAtraccion)) {
				promocion.getAtraccion().add(atraccion);
			}
		}
		
		if (!promocion.getAtraccion().isEmpty()) {
			promocion.setTipodeAtraccion();
		}
		
		if (promocion.getClass().getSimpleName().equals("PromocionAXB")) {
			sql = "SELECT *\n"
				+ "FROM AtraccionesDePromociones\n"
				+ "WHERE idPromocion = ? AND promocionNoGeneral;";
			
			PromocionAXB promocionAXB = (PromocionAXB) promocion;
			
			statement = conexion.prepareStatement(sql);
			statement.setInt(1, promocionAXB.getIdPromocionAXB());
			resultados = statement.executeQuery();
			
			idsAtracciones = new ArrayList<Integer>();
			
			while (resultados.next()) {
				Integer idAtraccion = resultados.getInt("idAtraccion");
				idsAtracciones.add(idAtraccion);
			}
			
			for (Atraccion atraccion: todasLasAtracciones) {
				Integer idAtraccion = atraccion.getIdAtraccion();
				if (idsAtracciones.contains(idAtraccion)) {
					promocionAXB.getAtraccionesPagas().add(atraccion);
				}
			}
		}

	}
}
