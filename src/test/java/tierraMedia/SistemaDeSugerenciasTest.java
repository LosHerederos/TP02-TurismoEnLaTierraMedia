package tierraMedia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SistemaDeSugerenciasTest {
    private SistemaDeSugerencias sistema;

    @Before
    public void setup() {
        
        Archivos.rutaDeArchivos = "archivos/test/";
        Archivos.archivoDeUsuarios = "usuariosTest2.in";
        Archivos.archivoDeAtracciones = "atraccionesTest2.in";
        Archivos.archivoDePromociones = "promocionesTest2.in";
        Archivos.prefijoDeArchivoDeSalida = "%sTest2.out";
    }

    @Test
    public void ordenadorPorPrecioYTiempoAtraccionesTest(){
		List<Atraccion> ordenadas = new ArrayList<>();
		List<Atraccion> desordenadas = new ArrayList<>();
		Atraccion a1 = new Atraccion(55, 3.5, 3, TipoDeAtracciones.AVENTURA, "Mi Aventura");
		Atraccion a2 = new Atraccion(45, 4.5, 4, TipoDeAtracciones.AVENTURA, "Extrema");
		Atraccion a3 = new Atraccion(45, 2.5, 2, TipoDeAtracciones.AVENTURA, "Otra Aventura");
		desordenadas.add(a3);
		desordenadas.add(a1);
		desordenadas.add(a2);

        ordenadas.add(a1);
        ordenadas.add(a2);
        ordenadas.add(a3);

		Collections.sort(desordenadas, new OrdenadorPorPrecioYTiempo());
        for (int i = 0; i < ordenadas.size()-1; i++) {
            assertEquals(ordenadas.get(i), desordenadas.get(i));
        }
    }

    @Test
    public void ordenadorPorPrecioYTiempoPromocionesTest(){
        List<Promocion> ordenadas = new ArrayList<>();
		List<Promocion> desordenadas = new ArrayList<>();
        List<Atraccion> atracciones = new ArrayList<>();
        atracciones.add(new Atraccion(55, 3.5, 3, TipoDeAtracciones.AVENTURA, "Mi Aventura"));
        atracciones.add(new Atraccion(45, 2.5, 2, TipoDeAtracciones.AVENTURA, "Otra Aventura"));
        atracciones.add(new Atraccion(10, 2.0, 6, TipoDeAtracciones.AVENTURA, "Moria"));
        atracciones.add(new Atraccion(5, 2.5, 25, TipoDeAtracciones.AVENTURA, "Minas Tirith"));
        atracciones.add(new Atraccion(20, 2.0, 7, TipoDeAtracciones.AVENTURA, "Otra que es Gratis"));
		
		PromocionAbsoluta p1 = new PromocionAbsoluta("Pack Aventura 1", atracciones.subList(0, 1), 50);
		PromocionAXB p2 = new PromocionAXB("Pack aventura3", atracciones.subList(2, 3), atracciones.subList(4, 4));
		PromocionPorcentual p3 = new PromocionPorcentual("Pack Aventura", atracciones, 20.0);

		desordenadas.add(p3);
		desordenadas.add(p1);
		desordenadas.add(p2);

        ordenadas.add(p1);
        ordenadas.add(p2);
        ordenadas.add(p3);

		Collections.sort(desordenadas, new OrdenadorPorPrecioYTiempo());
        for (int i = 0; i < ordenadas.size()-1; i++) {
            assertEquals(ordenadas.get(i), desordenadas.get(i));
        }
    }
}
