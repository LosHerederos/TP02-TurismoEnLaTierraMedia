package tierraMedia;

import static org.junit.Assert.*;

import org.junit.Test;

public class AtraccionTest {

	@Test
	public void test() {
		Atraccion atraccion = new Atraccion(0, 25, 2.5, 10, TipoDeAtracciones.AVENTURA, "Una aventura como ninguna");
		assertEquals(25, atraccion.getCosto());
		assertEquals(2.5, atraccion.getTiempo(), 0.001);
		assertEquals(10, atraccion.getCupoPersonas());
		assertEquals(TipoDeAtracciones.AVENTURA, atraccion.getTipoDeAtraccion());
		assertEquals(0, atraccion.getVisitantes());
		atraccion.agregarVisitante();
		assertEquals(1, atraccion.getVisitantes());
	}

}
