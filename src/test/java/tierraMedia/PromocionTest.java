package tierraMedia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PromocionTest {
	
	List<Atraccion> atracciones;
	
	
	@Before
	public void setup() {
		atracciones = new ArrayList<Atraccion>();
		/*
		Moria 10 2 6 Aventura
		Minas Tirith 5 2.5 25 Paisaje
		La Comarca 3 6.5 150 Degustación
		Mordor 25 3 4 Aventura
		Abismo de Helm 5 2 15 Paisaje
		Lothlórien 35 1 30 Degustación
		Erebor 12 3 32 Paisaje
		Bosque Negro 3 4 12 Aventura
		*/
	}

	@Test
	public void promocionPorcentualTest() {
		//Pack aventura: Bosque Negro y Mordor con un 20% de descuento si se compran ambas.
		atracciones.add(new Atraccion(0, 3, 4.0, 12, TipoDeAtracciones.AVENTURA, "Bosque Negro", 0));
		atracciones.add(new Atraccion(0, 25, 3.0, 4, TipoDeAtracciones.AVENTURA, "Mordor", 0));
		PromocionPorcentual promoPorcentual = new PromocionPorcentual(0, 0, "Pack aventura", atracciones, 0.2);
		assertEquals(22, promoPorcentual.getCosto());
		assertEquals(7.0, promoPorcentual.getTiempo(), 0.001);
		assertEquals(TipoDeAtracciones.AVENTURA, promoPorcentual.getTipoDeAtraccion());
		List<Atraccion> atraccionesDeLaPromo = promoPorcentual.getAtraccion();
		for (Atraccion atraccion : atraccionesDeLaPromo) {
			assertEquals(0, atraccion.getVisitantes());
		}
		promoPorcentual.agregarVisitante();
		for (Atraccion atraccion : atraccionesDeLaPromo) {
			assertEquals(1, atraccion.getVisitantes());
		}
		
	}
	
	@Test
	public void promocionAbsolutaTest() {
		//Pack degustación: Lothlórien y La Comarca a 36 monedas.
		atracciones.add(new Atraccion(0, 35, 1.0, 30, TipoDeAtracciones.DEGUSTACION, "Lothlórien", 0));
		atracciones.add(new Atraccion(0, 3, 6.5, 150, TipoDeAtracciones.DEGUSTACION, "La Comarca", 0));
		PromocionAbsoluta promoAbsoluta = new PromocionAbsoluta(0, 0, "Pack degustación", atracciones, 36);
		assertEquals(36, promoAbsoluta.getCosto());
		assertEquals(7.5, promoAbsoluta.getTiempo(), 0.001);
		assertEquals(TipoDeAtracciones.DEGUSTACION, promoAbsoluta.getTipoDeAtraccion());
		List<Atraccion> atraccionesDeLaPromo = promoAbsoluta.getAtraccion();
		for (Atraccion atraccion : atraccionesDeLaPromo) {
			assertEquals(0, atraccion.getVisitantes());
		}
		promoAbsoluta.agregarVisitante();
		for (Atraccion atraccion : atraccionesDeLaPromo) {
			assertEquals(1, atraccion.getVisitantes());
		}
	}
	
	@Test
	public void promocionAxBTest() {
		//Pack paisajes: Comprando Minas Tirith y el Abismo de Helm, Erebor es gratis.
		List<Atraccion> otrasAtracciones = new ArrayList<Atraccion>();
		otrasAtracciones.add(new Atraccion(0, 5, 2.5, 25, TipoDeAtracciones.PAISAJE, "Minas Tirith", 0));
		otrasAtracciones.add(new Atraccion(0, 5, 2.0, 15, TipoDeAtracciones.PAISAJE, "Abismo de Helm", 0));
		atracciones.add(new Atraccion(0, 12, 3.0, 32, TipoDeAtracciones.PAISAJE, "Erebor", 0));
		PromocionAXB promoAxB = new PromocionAXB(0, 0, "Mi Promo AxB", atracciones, otrasAtracciones);
		assertEquals(10, promoAxB.getCosto());
		assertEquals(7.5, promoAxB.getTiempo(), 0.001);
		assertEquals(TipoDeAtracciones.PAISAJE, promoAxB.getTipoDeAtraccion());
		List<Atraccion> atraccionesDeLaPromo = new ArrayList<Atraccion>();
		atraccionesDeLaPromo.addAll(promoAxB.getAtraccion());
		atraccionesDeLaPromo.addAll(promoAxB.getAtraccionesPagas()); 
		for (Atraccion atraccion : atraccionesDeLaPromo) {
			assertEquals(0, atraccion.getVisitantes());
		}
		promoAxB.agregarVisitante();
		for (Atraccion atraccion : atraccionesDeLaPromo) {
			assertEquals(1, atraccion.getVisitantes());
		}
	}

}
