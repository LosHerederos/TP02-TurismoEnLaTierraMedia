package tierraMedia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ItinerarioTest {

	@Test
	public void test() {
		Atraccion atraccion = new Atraccion(0, 3, 4.0, 12, TipoDeAtracciones.AVENTURA, "Bosque Negro");
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(new Atraccion(0, 35, 1.0, 30, TipoDeAtracciones.DEGUSTACION, "Lothlórien"));
		atracciones.add(new Atraccion(0, 3, 6.5, 150, TipoDeAtracciones.DEGUSTACION, "La Comarca"));
		PromocionAbsoluta promoAbsoluta = new PromocionAbsoluta("Pack degustación", atracciones, 36);
		Itinerario itinerario = new Itinerario();
		itinerario.agregarSugerencia(atraccion);
		itinerario.agregarSugerencia(promoAbsoluta);
		
		assertEquals(11.5, itinerario.horasNecesarias(), 0.001);
		assertEquals(39, itinerario.costoTotal());
	}

}
