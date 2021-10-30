package tierraMedia;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


public class BaseDeDatosTest {

	@Test
	public void test() {
		List<Atraccion> atracciones = BaseDeDatos.cargarAtracciones();
		List<Promocion> promociones = BaseDeDatos.cargarPromociones(atracciones);
		List<Usuario> usuarios = BaseDeDatos.cargarUsuarios(atracciones, promociones);
		
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
	}

}
