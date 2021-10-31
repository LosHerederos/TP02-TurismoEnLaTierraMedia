package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import jdbc.ConnectionProvider;
import tierraMedia.Atraccion;
import tierraMedia.TipoDeAtracciones;

public class AtraccionDAOTest {


	@Test
	public void ContarAtracciones() throws SQLException {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		assertEquals(DAOFactory.getAtraccionDAO().countAll(), atraccionDAO.countAll());
		assertNotEquals(DAOFactory.getAtraccionDAO().countAll() + 1, atraccionDAO.countAll());

		/*
		 * assertEquals(2.5, atraccion.getTiempo(), 0.001); assertEquals(10,
		 * atraccion.getCupoPersonas()); assertEquals(TipoDeAtracciones.AVENTURA,
		 * atraccion.getTipoDeAtraccion()); assertEquals(0, atraccion.getVisitantes());
		 * atraccion.agregarVisitante(); assertEquals(1, atraccion.getVisitantes());
		 */
	}

	@Test
	public void EncontrarAtraccionPorNombre() throws SQLException {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		assertEquals("Minas Tirith", atraccionDAO.findByNombre("Minas Tirith").getNombre());
		assertNotEquals(" ", atraccionDAO.findByNombre("Minas Tirith").getNombre());
	}

	@Test
	public void EncontrarAtraccionPorid() throws SQLException {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		assertEquals("Minas Tirith", atraccionDAO.findById(2).getNombre());
		assertNotEquals(" ", atraccionDAO.findById(2).getNombre());

	}
	
	@Test
	public void TraerTodasLasAtracciones() throws SQLException {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		assertArrayEquals(true ,atraccionDAO.findAll().isEmpty());
	}

	@Test
	public void CrearNuevaAtraccion() throws SQLException {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Atraccion LaNueva = new Atraccion(0,20,5,48,TipoDeAtracciones.DEGUSTACION,"La Nueva");
		atraccionDAO.insert(LaNueva);
		assertEquals("La Nueva", atraccionDAO.findByNombre("La Nueva").getNombre());
		
	}
	@Test
	public void ActualizarAtraccion() throws SQLException {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Atraccion LaOtra = new Atraccion(0,20,5,48,TipoDeAtracciones.DEGUSTACION,"La Otra");
		atraccionDAO.insert(LaOtra);
		LaOtra.setCostoVisita(48);
		atraccionDAO.update(LaOtra);
		assertEquals(48, atraccionDAO.findByNombre("La Otra").getCosto());
		
	}
	
	@Test
	public void BorrarAtraccion() throws SQLException {
		Connection conexion = ConnectionProvider.getConnection();
		PreparedStatement statement = conexion.prepareStatement("DELETE \r\n"
				+ " FROM Atracciones WHERE Atracciones.nombre like (?); ");
		statement.setString(1, "La Nueva");
		statement.executeUpdate();
	}
	
	
 
	private void assertArrayEquals(boolean b, boolean empty) {
		// TODO Auto-generated method stub
		
	}
	

}
