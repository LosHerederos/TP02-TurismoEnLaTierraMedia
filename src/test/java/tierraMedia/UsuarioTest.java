package tierraMedia;

import static org.junit.Assert.*;

import org.junit.Test;

public class UsuarioTest {

	@Test
	public void aceptarSugerenciatest() {
		Usuario usuario = new Usuario("Pedro", 100, 10.0, TipoDeAtracciones.AVENTURA);
		Atraccion atraccion = new Atraccion(25, 2.5, 10, TipoDeAtracciones.AVENTURA, "Una aventura como ninguna");
		usuario.aceptarSugerencia(atraccion);
		assertEquals(1, atraccion.getVisitantes());
		assertEquals(75, usuario.getPresupuesto());
		assertEquals(7.5, usuario.getTiempoDisponible(), 1);
		assertTrue(usuario.estaEnElItinerario(atraccion));
	}

	@Test
	public void poseeRecursosSuficientesTest() {
		Usuario usuario = new Usuario("Pedro", 10, 3.0, TipoDeAtracciones.AVENTURA);
		assertTrue(usuario.poseeRecursosSuficientes(5, 1.0));
		assertFalse(usuario.poseeRecursosSuficientes(15, 1.0));
		assertFalse(usuario.poseeRecursosSuficientes(5, 4.0));
	}

}
