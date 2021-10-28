package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionProvider;
import tierraMedia.Atraccion;
import tierraMedia.TipoDeAtracciones;

public class AtraccionDAOImpl implements AtraccionDAO {
	
	public List<Atraccion> findAll() throws SQLException {
		String sql = "SELECT * FROM Atracciones";
		
		Connection conexion = ConnectionProvider.getConnection();
		PreparedStatement statement = conexion.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();
		
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		
		while(resultados.next()) {
			atracciones.add(toAtraccion(resultados));
		}
		
		return atracciones;
	}
	
	public Atraccion findById(int id) throws SQLException {
		String sql = "SELECT *\n"
			+ "FROM Atracciones\n"
			+ "WHERE idAtraccion = ?";
		
		Connection conexion = ConnectionProvider.getConnection();
		PreparedStatement statement = conexion.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultado = statement.executeQuery();
		
		Atraccion atraccion = null;
		
		if (resultado.next() ) {    
			atraccion = toAtraccion(resultado); 
		} 
		
		return atraccion;
	}
	
	public int countAll() throws SQLException {
		String sql = "SELECT count(*)\n"
				+ "FROM Atracciones\n";
			
		Connection conexion = ConnectionProvider.getConnection();
		PreparedStatement statement = conexion.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		resultado.next();
		int cantidadDeAtracciones = resultado.getInt(1);
		
		return cantidadDeAtracciones;
	}
	
	public int insert(Atraccion atraccion) throws SQLException {
		String sql = "INSERT INTO\n"
				+ "Atracciones (nombre, CostoVisita, tiempoParaRealizarla, cupoPersonas, visitantes, idTipoDeAtraccion)\n"
				+ "VALUES (?, ?, ?, ?, ?, ?)";
			
		Connection conexion = ConnectionProvider.getConnection();
		PreparedStatement statement = conexion.prepareStatement(sql);
		statement.setString(1, atraccion.getNombre());
		statement.setInt(2, atraccion.getCosto());
		statement.setDouble(3, atraccion.getTiempo());
		statement.setInt(4, atraccion.getCupoPersonas());
		statement.setInt(5, atraccion.getVisitantes());
		statement.setInt(5, atraccion.getTipoDeAtraccion().ordinal());
		int filas = statement.executeUpdate();
		
		return filas;
	}
	
	public int update(Atraccion atraccion) throws SQLException {
		String sql = "UPDATE Atracciones\n"
				+ "SET visitantes = ?,\n"
				+ "WHERE idAtraccion = ?;";
		System.out.println(sql);
		System.out.println(atraccion.getVisitantes());
		System.out.println(atraccion.getIdAtraccion());
		Connection conexion = ConnectionProvider.getConnection();
		PreparedStatement statement = conexion.prepareStatement(sql);
		statement.setInt(1, atraccion.getVisitantes());
		statement.setInt(2, atraccion.getIdAtraccion());
		int filas = statement.executeUpdate();
		
		return filas;
	}
	
	public int delete(Atraccion atraccion) throws SQLException {
		return 0;
	}
	
	public Atraccion findByNombre(String nombre) throws SQLException {
		String sql = "SELECT *\n"
				+ "FROM Atracciones\n"
				+ "WHERE nombre = ?";
			
		Connection conexion = ConnectionProvider.getConnection();
		PreparedStatement statement = conexion.prepareStatement(sql);
		statement.setString(1, nombre);
		ResultSet resultado = statement.executeQuery();
		
		Atraccion atraccion = null;
		
		if (resultado.next()) {
			atraccion = toAtraccion(resultado);
		}
		
		return atraccion;
	}
		
	private Atraccion toAtraccion(ResultSet resultado) throws SQLException {
		
		return new Atraccion(
			resultado.getInt("idAtraccion"),
			resultado.getInt("costoVisita"),
			resultado.getDouble("tiempoParaRealizarla"),
			resultado.getInt("cupoPersonas"),
			TipoDeAtracciones.values()[resultado.getInt("idTipoDeAtraccion")-1],
			resultado.getString("nombre")
		);
	}
}
