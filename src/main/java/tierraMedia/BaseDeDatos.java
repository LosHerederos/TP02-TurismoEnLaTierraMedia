package tierraMedia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DAOFactory;
import dao.UsuarioDAO;

public class BaseDeDatos {

	public static List<Usuario> cargarUsuarios() throws SQLException {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		
		List<Usuario> usuarios = usuarioDAO.findAll();
		
		return usuarios;
	}
	
	public static List<Atraccion> cargarAtracciones() {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		
		return atracciones;
	}
	
	public static List<Promocion> cargarPromociones(List<Atraccion> todasLasAtracciones) {
		List<Promocion> promociones = new ArrayList<Promocion>();
		
		return promociones;
	}
	
	public static void guardarDatos(List<Usuario> usuarios) {
		
	}
}
