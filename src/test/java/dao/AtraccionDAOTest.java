package dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import tierraMedia.Atraccion;
import tierraMedia.TipoDeAtracciones;

public class AtraccionDAOTest {


	@Test
	public void ContarAtracciones() throws SQLException {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		assertEquals(8, atraccionDAO.countAll());
		assertNotEquals(10, atraccionDAO.countAll());

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


 
	private void assertArrayEquals(boolean b, boolean empty) {
		// TODO Auto-generated method stub
		
	}
	

}
