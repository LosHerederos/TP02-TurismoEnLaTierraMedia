package tierraMedia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.AtraccionDAO;
import dao.DAOFactory;
import dao.UsuarioDAO;

public class BaseDeDatos {

	public static List<Atraccion> cargarAtracciones() {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		
		List<Atraccion> atracciones = null;
		
		try {
			atracciones = atraccionDAO.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return atracciones;
	}
	
	public static List<Promocion> cargarPromociones(List<Atraccion> todasLasAtracciones) {
		List<Promocion> promociones = new ArrayList<Promocion>();
		
		return promociones;
	}
	
	public static List<Usuario> cargarUsuarios(List<Atraccion> todasLasAtracciones, List<Promocion> todasLasPromociones) {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		
		List<Usuario> usuarios = null;

		try {
			usuarios = usuarioDAO.findAll(todasLasAtracciones, todasLasPromociones);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}
	
	public static void guardarDatos(List<Usuario> usuarios) {
		
	}

}
