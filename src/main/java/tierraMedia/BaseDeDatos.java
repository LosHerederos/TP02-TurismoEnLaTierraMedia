package tierraMedia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.AtraccionDAO;
import dao.DAOFactory;
import dao.PromocionDAO;
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
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();

		List<Promocion> promociones = null;
		
		try {
			promociones = promocionDAO.findAll(todasLasAtracciones);
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
	
	public static void guardarDatos(List<Usuario> todosLosUsuarios, List<Atraccion> todasLasAtracciones) {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		
		for (Usuario usuario : todosLosUsuarios) {
			try {
				usuarioDAO.update(usuario);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		for (Atraccion atraccion : todasLasAtracciones) {
			try {
				atraccionDAO.update(atraccion);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
