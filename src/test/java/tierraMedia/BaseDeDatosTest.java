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
		
		Atraccion abismo = atracciones.get(4);
		Usuario galadriel = usuarios.get(3);
//		galadriel.aceptarSugerencia(abismo);
//		System.out.println(abismo);
//		System.out.println(galadriel);
		
//		for (Atraccion atraccion : atracciones) {
//			System.out.println(atraccion);
//		}
//		for (Promocion promocion : promociones) {
//			System.out.println(promocion);
//		
//		}
//		for (Usuario usuario : usuarios) {
//			System.out.println(usuario);
//		}
		
//		BaseDeDatos.guardarDatos(usuarios, atracciones);
	}

}
