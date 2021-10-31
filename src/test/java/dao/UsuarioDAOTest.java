package dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import tierraMedia.TipoDeUsuarios;
import tierraMedia.Usuario;

public class UsuarioDAOTest {

	@Test
	public void test() throws SQLException {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		
		System.out.println(usuarioDAO.findAll());
		System.out.println(usuarioDAO.findById(2));
		System.out.println(usuarioDAO.findByNombre("Gandalf"));
		System.out.println(usuarioDAO.countAll());
		
//		Usuario pedro = new Usuario(0, "Pedro", 150, 10.0, TipoDeUsuarios.PAISAJE.ordinal());
//		System.out.println(usuarioDAO.insert(pedro));
//		Usuario pedro2 = usuarioDAO.findByNombre("Pedro");
//		System.out.println(pedro2);
//		pedro2.setPresupuesto(99);
//		pedro2.setTiempoDisponible(5.5);
//		System.out.println(usuarioDAO.update(pedro2));
	}
	@Test
	public void ContarUsuarios() throws SQLException {
		UsuarioDAO UsuarioDAO = DAOFactory.getUsuarioDAO();
		assertEquals(4, UsuarioDAO.countAll());
		assertNotEquals(48, UsuarioDAO.countAll());

		/*
		 * assertEquals(2.5, Usuario.getTiempo(), 0.001); assertEquals(10,
		 * Usuario.getCupoPersonas()); assertEquals(TipoDeUsuarios.AVENTURA,
		 * Usuario.getTipoDeUsuario()); assertEquals(0, Usuario.getVisitantes());
		 * Usuario.agregarVisitante(); assertEquals(1, Usuario.getVisitantes());
		 */
	}

	@Test
	public void EncontrarUsuarioPorNombre() throws SQLException {
		UsuarioDAO UsuarioDAO = DAOFactory.getUsuarioDAO();
		assertEquals("Gandalf", UsuarioDAO.findByNombre("Gandalf").getNombre());
		assertNotEquals(" ", UsuarioDAO.findByNombre("Gandalf").getNombre());
	}

	@Test
	public void EncontrarUsuarioPorid() throws SQLException {
		UsuarioDAO UsuarioDAO = DAOFactory.getUsuarioDAO();
		assertEquals("Gandalf", UsuarioDAO.findById(2).getNombre());
		assertNotEquals(" ", UsuarioDAO.findById(2).getNombre());

	}
	
	@Test
	public void TraerTodasLasUsuarios() throws SQLException {
		UsuarioDAO UsuarioDAO = DAOFactory.getUsuarioDAO();
		assertArrayEquals(true ,UsuarioDAO.findAll().isEmpty());
	}

	@Test
	public void CrearNuevaUsuario() throws SQLException {
		UsuarioDAO UsuarioDAO = DAOFactory.getUsuarioDAO();
		UsuarioDAO.insert("La nueva", 20, 2.0, 28, 0, 2);
	}

 
	private void assertArrayEquals(boolean b, boolean empty) {
		// TODO Auto-generated method stub
		
	}

}
