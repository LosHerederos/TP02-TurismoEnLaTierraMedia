package dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

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
		
//		Usuario pedro = new Usuario(0, "Pedro", 150, 10.0, TipoDeAtracciones.PAISAJE.ordinal());
//		System.out.println(usuarioDAO.insert(pedro));
//		Usuario pedro2 = usuarioDAO.findByNombre("Pedro");
//		System.out.println(pedro2);
//		pedro2.setPresupuesto(99);
//		pedro2.setTiempoDisponible(5.5);
//		System.out.println(usuarioDAO.update(pedro2));
	}

}
