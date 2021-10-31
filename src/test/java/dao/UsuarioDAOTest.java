package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import jdbc.ConnectionProvider;
import tierraMedia.TipoDeAtracciones;
import tierraMedia.Usuario;


public class UsuarioDAOTest {

	@Test
	public void test() throws SQLException {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		
		System.out.println(usuarioDAO.findAll());
		System.out.println(usuarioDAO.findById(2));
		System.out.println(usuarioDAO.findByNombre("Gandalf"));
		System.out.println(usuarioDAO.countAll());
		
//		usuario pedro = new usuario(0, "Pedro", 150, 10.0, TipoDeusuarios.PAISAJE.ordinal());
//		System.out.println(usuarioDAO.insert(pedro));
//		usuario pedro2 = usuarioDAO.findByNombre("Pedro");
//		System.out.println(pedro2);
//		pedro2.setPresupuesto(99);
//		pedro2.setTiempoDisponible(5.5);
//		System.out.println(usuarioDAO.update(pedro2));
	}
	

	@Test
	public void EncontrarusuarioPorNombre() throws SQLException {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		assertEquals("Gandalf", usuarioDAO.findByNombre("Gandalf").getNombre());
		assertNotEquals(" ", usuarioDAO.findByNombre("Gandalf").getNombre());
	}

	@Test
	public void EncontrarusuarioPorid() throws SQLException {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		assertEquals("Gandalf", usuarioDAO.findById(2).getNombre());
		assertNotEquals(" ", usuarioDAO.findById(2).getNombre());

	}
	
	@Test
	public void TraerTodosLosusuarios() throws SQLException {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		assertArrayEquals(true ,usuarioDAO.findAll().isEmpty());
	}

	@Test
	public void CrearNuevousuario() throws SQLException {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		Usuario MenganoFulano = new Usuario(0,"Mengano Fulano", 280, 85,TipoDeAtracciones.AVENTURA,null);
		usuarioDAO.insert(MenganoFulano);
		
	}
//	@Test
//	public void Actualizarusuario() throws SQLException {
//		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
//		Usuario MenganoFulano = new Usuario(0,"Mengano Fulano", 280, 85,TipoDeAtracciones.AVENTURA,null);
//		usuarioDAO.insert(MenganoFulano);
//		MenganoFulano.set
//		usuarioDAO.update(LaOtra);
//		assertEquals(8, usuarioDAO.findByNombre("La Otra").getCosto());
//		
//	}
//	
	@Test
	public void Borrarusuario() throws SQLException {
		Connection conexion = ConnectionProvider.getConnection();
		PreparedStatement statement = conexion.prepareStatement("DELETE \r\n"
				+ " FROM usuarios WHERE usuarios.nombre like (?); ");
		statement.setString(1, "Mengano Fulano");
		statement.executeUpdate();
	}

 
	private void assertArrayEquals(boolean b, boolean empty) {
		// TODO Auto-generated method stub
		
	}

}
