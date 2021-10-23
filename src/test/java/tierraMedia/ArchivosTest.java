package tierraMedia;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ArchivosTest {
	
	@Before
	public void setup() {
		
		Archivos.rutaDeArchivos = "archivos/test/";
		Archivos.archivoDeUsuarios = "usuariosTest.in";
		Archivos.archivoDeAtracciones = "atraccionesTest.in";
		Archivos.archivoDePromociones = "promocionesTest.in";
		Archivos.prefijoDeArchivoDeSalida = "%sTest.out";
		
	}
	
	@Test
	public void cargarUsuariosTest() {

		List<Usuario> usuarios = Archivos.cargarUsuarios();
		
		assertEquals("Pedro", usuarios.get(0).getNombre());
		assertEquals(75, usuarios.get(0).getPresupuesto());
		assertEquals(6.0, usuarios.get(0).getTiempoDisponible(), 0.001);
		assertEquals(TipoDeAtracciones.AVENTURA, usuarios.get(0).getTipoFavorito());
		
		assertEquals("Gabriel", usuarios.get(1).getNombre());
		assertEquals(120, usuarios.get(1).getPresupuesto());
		assertEquals(3.6, usuarios.get(1).getTiempoDisponible(), 0.001);
		assertEquals(TipoDeAtracciones.PAISAJE, usuarios.get(1).getTipoFavorito());
		
	}
	
	@Test
	public void cargarAtraccionesTest() {
		
		List<Atraccion> atracciones = Archivos.cargarAtracciones();
		
		assertEquals("Moria", atracciones.get(0).getNombre());
		assertEquals(10, atracciones.get(0).getCosto());
		assertEquals(2.0, atracciones.get(0).getTiempo(), 0.001);
		assertEquals(6, atracciones.get(0).getCupoPersonas());
		assertEquals(TipoDeAtracciones.AVENTURA, atracciones.get(0).getTipoDeAtraccion());
		
		assertEquals("Minas Tirith", atracciones.get(1).getNombre());
		assertEquals(5, atracciones.get(1).getCosto());
		assertEquals(2.5, atracciones.get(1).getTiempo(), 0.001);
		assertEquals(25, atracciones.get(1).getCupoPersonas());
		assertEquals(TipoDeAtracciones.AVENTURA, atracciones.get(1).getTipoDeAtraccion());
		
		assertEquals("La Comarca", atracciones.get(2).getNombre());
		assertEquals(3, atracciones.get(2).getCosto());
		assertEquals(6.5, atracciones.get(2).getTiempo(), 0.001);
		assertEquals(150, atracciones.get(2).getCupoPersonas());
		assertEquals(TipoDeAtracciones.DEGUSTACION, atracciones.get(2).getTipoDeAtraccion());
			
	}
	
	
	@Test
	public void cargarPromocionesTest() {
		
		List<Atraccion> atracciones = Archivos.cargarAtracciones();
		List<Promocion> promociones = Archivos.cargarPromociones(atracciones);
		
		assertEquals("PromocionPorcentual", promociones.get(0).getClass().getSimpleName());
		assertSame(atracciones.get(0), promociones.get(0).getAtraccion().get(0));
		assertSame(atracciones.get(1), promociones.get(0).getAtraccion().get(1));
		
		assertEquals("PromocionAbsoluta", promociones.get(1).getClass().getSimpleName());
		assertSame(atracciones.get(0), promociones.get(1).getAtraccion().get(0));
		assertSame(atracciones.get(1), promociones.get(1).getAtraccion().get(1));
		
		assertEquals("PromocionAXB", promociones.get(2).getClass().getSimpleName());
		assertSame(atracciones.get(0), promociones.get(2).getAtraccion().get(0));
		assertSame(atracciones.get(1), promociones.get(2).getAtraccion().get(1));
		
	}
	
	
	@Test
	public void generarArchivosDeSalidaTest() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(new Usuario("Pedro", 100, 10.0, TipoDeAtracciones.AVENTURA));
		usuarios.add(new Usuario("Jose", 70, 7.5, TipoDeAtracciones.DEGUSTACION));
//		String nombre, int presupuesto, double tiempoDisponible,TipoDeAtracciones tipoFavorito
		Atraccion atraccion1 = new Atraccion(25, 2.5, 10, TipoDeAtracciones.AVENTURA, "Una aventura como ninguna1");
		Atraccion atraccion2 = new Atraccion(25, 2.5, 10, TipoDeAtracciones.AVENTURA, "Una aventura como ninguna2");
		
		usuarios.get(0).aceptarSugerencia(atraccion1);
		usuarios.get(0).aceptarSugerencia(atraccion2);
		usuarios.get(1).aceptarSugerencia(atraccion2);
		
		//Promocion promo1 = new Promocion 
		try {
			Archivos.generarArchivosDeSalida(usuarios);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
