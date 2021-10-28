package tierraMedia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Archivos {

	final static String SEPARADOR_DE_DATOS = ", ";
	final static String SEPARADOR_DE_ARREGLO = " : ";
	static String rutaDeArchivos = "archivos/";
	static String archivoDeUsuarios = "usuarios.in";
	static String archivoDeAtracciones = "atracciones.in";
	static String archivoDePromociones = "promociones.in";
	static String prefijoDeArchivoDeSalida = "%s.out";

	public static List<Usuario> cargarUsuarios() {
		/*
		 * 0: Nombre : String
		 * 1: Presupuesto : int
		 * 2: Tiempo : double
		 * 3: Tipo de Atracci�n : enum
		 */
		String nombre;
		int presupuesto;
		double tiempoDisponible;
		TipoDeAtracciones tipoFavorito;
		
		String rutaDeArchivoDeUsuarios = rutaDeArchivos + "input/" + archivoDeUsuarios;
		String[][] datosDeUsuarios = separarDatos(leerArchivo(rutaDeArchivoDeUsuarios));
		List<Usuario> usuarios = new ArrayList<Usuario>();

		for (int i = 0; i < datosDeUsuarios.length; i++) {
			nombre = datosDeUsuarios[i][0];
			presupuesto = Integer.parseInt(datosDeUsuarios[i][1]);
			tiempoDisponible = Double.parseDouble(datosDeUsuarios[i][2]);
			tipoFavorito = TipoDeAtracciones.valueOf(datosDeUsuarios[i][3]);
			
			usuarios.add(new Usuario(0, nombre, presupuesto, tiempoDisponible, tipoFavorito, null));
		}
		
		return usuarios;
	}

	public static List<Atraccion> cargarAtracciones() {
		/* 
		 * 0: Nombre : String
		 * 1: Costo : int
		 * 2: Tiempo : double
		 * 3: Cupo de Personas : int
		 * 4: Tipo de Atracci�n : enum
		 */
		String nombre;
		int costoVisita;
		double tiempoParaRealizarla;
		int cupoPersonas;
		TipoDeAtracciones tipoDeAtraccion;
		
		String rutaDeArchivoDeAtracciones = rutaDeArchivos + "input/" + archivoDeAtracciones;
		String[][] datosDeAtracciones = separarDatos(leerArchivo(rutaDeArchivoDeAtracciones));
		List<Atraccion> atracciones = new ArrayList<Atraccion>();

		for (int i = 0; i < datosDeAtracciones.length; i++) {
			nombre = datosDeAtracciones[i][0];
			costoVisita = Integer.parseInt(datosDeAtracciones[i][1]);
			tiempoParaRealizarla = Double.parseDouble(datosDeAtracciones[i][2]);
			cupoPersonas = Integer.parseInt(datosDeAtracciones[i][3]);
			tipoDeAtraccion = TipoDeAtracciones.valueOf(datosDeAtracciones[i][4]);
			
			atracciones.add(new Atraccion(0,costoVisita, tiempoParaRealizarla, cupoPersonas, tipoDeAtraccion, nombre));
		}
		
		return atracciones;
	}

	public static List<Promocion> cargarPromociones(List<Atraccion> todasLasAtracciones) {
		/*"
		 * 0: Tipo de Promoci�n : String
		 * 1: Nombre : String
		 * 2: Atracciones : Atraccion[]
		 * 3: Otros:  
		 */
		String tipoDePromocion;
		String nombre;
		List<Atraccion> atracciones;
		
		String rutaDeArchivoDePromociones = rutaDeArchivos + "input/" + archivoDePromociones;
		String[][] datosDePromociones = separarDatos(leerArchivo(rutaDeArchivoDePromociones));
		List<Promocion> promociones = new ArrayList<Promocion>();
		
		for (int i = 0; i < datosDePromociones.length; i++) {
			tipoDePromocion = datosDePromociones[i][0];
			nombre = datosDePromociones[i][1];
			String[] nombresDeAtracciones = separarArregloEnString(datosDePromociones[i][2]);
			atracciones = buscarAtracciones(todasLasAtracciones, nombresDeAtracciones);
			
			if (tipoDePromocion.equals("PromocionPorcentual")) {
				double porcentajeDeDescuento = Double.parseDouble(datosDePromociones[i][3]);
				promociones.add(new PromocionPorcentual(nombre, atracciones, porcentajeDeDescuento));
			} else if (tipoDePromocion.equals("PromocionAbsoluta")) {
				int costoTotal =  Integer.parseInt(datosDePromociones[i][3]);
				promociones.add(new PromocionAbsoluta(nombre, atracciones, costoTotal));
			} else if (tipoDePromocion.equals("PromocionAXB")) {
				String[] nombresDeOtrasAtracciones = separarArregloEnString(datosDePromociones[i][3]);
				List<Atraccion> otrasAtracciones = buscarAtracciones(todasLasAtracciones, nombresDeOtrasAtracciones);
				promociones.add(new PromocionAXB(nombre, atracciones, otrasAtracciones));
			}
		}
		
		return promociones;
	}
	
	public static void generarArchivosDeSalida(List<Usuario> usuarios) throws IOException {
		PrintWriter salida;
		
		for (Usuario usuario : usuarios) {
			salida = new PrintWriter(new FileWriter(rutaDeArchivos + "output/" + String.format(prefijoDeArchivoDeSalida, usuario.getNombre())));
			salida.println(usuario);
			salida.close();
		}
	}
	
	private static String[] leerArchivo(String rutaDeArchivo) {
		File archivoDeEntrada = new File(rutaDeArchivo);
		Scanner sc;
		String archivo = "";
		try {
			sc = new Scanner(archivoDeEntrada);
			String linea = sc.nextLine();
			while (sc.hasNextLine()) {
				linea = sc.nextLine();
				if (!linea.startsWith("#"))
					archivo += linea + ";";
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return archivo.split(";");
	}
	
	private static String[][] separarDatos(String[] arregloDeDatosUnidos) {
		String[][] arregloDeDatosSeparados = new String[arregloDeDatosUnidos.length][];
		for (int i = 0; i < arregloDeDatosUnidos.length; i++) {
			arregloDeDatosSeparados[i] = arregloDeDatosUnidos[i].split(SEPARADOR_DE_DATOS);
			for (int j = 0; j < arregloDeDatosSeparados[i].length; j++) {
				arregloDeDatosSeparados[i][j] = arregloDeDatosSeparados[i][j].trim();
			}
		}
		
		return arregloDeDatosSeparados;
	}
	
	private static String[] separarArregloEnString(String arregloEnString) {
		String[] arreglo;
		arregloEnString = arregloEnString.replaceAll("[()]", "");
		arreglo = arregloEnString.split(SEPARADOR_DE_ARREGLO);
		
		return arreglo;
	}
	
	private static List<Atraccion> buscarAtracciones(List<Atraccion> todasLasAtracciones, String[] nombresDeAtracciones) {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		int encontradas = 0;
		
		while (encontradas < nombresDeAtracciones.length) {
			for (Atraccion atraccion : todasLasAtracciones) {
				if (atraccion.getNombre().equals((nombresDeAtracciones[encontradas]))) {
					atracciones.add(atraccion);
				}
			}
			encontradas++;
		}
		
		return atracciones;
	}
}
