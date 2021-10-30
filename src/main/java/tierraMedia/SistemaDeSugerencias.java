package tierraMedia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SistemaDeSugerencias {
	private List<Atraccion> atracciones = new ArrayList<Atraccion>();
	private List<Promocion> promociones = new ArrayList<Promocion>();
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	public SistemaDeSugerencias() {
		setAtracciones(BaseDeDatos.cargarAtracciones());
		setPromociones(BaseDeDatos.cargarPromociones(this.atracciones));
		setUsuarios(BaseDeDatos.cargarUsuarios(this.atracciones, this.promociones));
	}


	public List<Atraccion> getAtracciones() {
		return atracciones;
	}


	public void setAtracciones(List<Atraccion> atracciones) {
		for (Atraccion atraccion : atracciones) {
			if (!this.atracciones.contains(atraccion) && atracionValida(atraccion)){
				this.atracciones.add(atraccion);
			}
		}
	}


	public List<Promocion> getPromociones() {
		return promociones;
	}


	public void setPromociones(List<Promocion> promociones) {
		for (Promocion promo : promociones) {
			if (!this.promociones.contains(promo) && promocionesValidas(promo)) {
				this.promociones.add(promo);
			}
		}
	}


	public List<Usuario> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(List<Usuario> usuarios) {
		for (Usuario usuario : usuarios) {
			if (!this.usuarios.contains(usuario) && usuarioValido(usuario)){
				this.usuarios.add(usuario);
			}
		}
	}
	
	public void iniciarMenu() throws IOException {
		ordenarPorPrecioYTiempo();
		Scanner respuesta = new Scanner(System.in);
		System.out.println("Oprimir ENTER para comenzar");
		System.in.read();
		System.out.println("Seleccionar un id de usuario o 0 para finalizar");
		mostrarUsuarios();
		int id = respuesta.nextInt();
		while (id !=0) {
			System.out.println(System.lineSeparator().repeat(100));
			menuSugerencias(usuarioDeId(id));
			System.out.println("Oprimir ENTER para finalizar");
			System.in.read();
			System.out.println("Seleccionar un id de usuario o 0 para finalizar");
			mostrarUsuarios();
			id = respuesta.nextInt();
		}
	}
	
	public void menuSugerencias(Usuario usuario){
		System.out.println("____________________________________________________________________________________________________");
		System.out.println("Bienvenido " + usuario.getNombre() + "!");
		System.out.println("Basandonos en sus preferencias, tenemos las siguientes promociones y atraciones para ofrecerle:");
		mostrarOpciones(enlistarSugerencias(usuario, true), usuario);
		if (usuario.poseeRecursosSuficientes(0, 0)) {
			//System.out.println("Ademas tenemos las siguientes promociones y atraciones vigentes");
			mostrarOpciones(enlistarSugerencias(usuario, false), usuario);
		}
		System.out.println("\nSu itinerario quedo de la siguiente forma:");
		System.out.println(usuario.getItinerario().toString());
		BaseDeDatos.guardarDatos(usuario);
	}
	
	public void mostrarUsuarios() {
		System.out.println("Usuarios del sistema =");
		for (int i = 0; i < usuarios.size(); i++) {
			System.out.println(i + "\t \t" + usuarios.get(i).getNombre());
		}
	}

	private LinkedList<Sugeribles> enlistarSugerencias(Usuario usuario, Boolean preferencias){
		LinkedList<Sugeribles> sugerencias = new LinkedList<Sugeribles>();
		sugerencias.addAll(this.promociones);
		sugerencias.addAll(this.atracciones);
		sugerencias.removeIf(sugerencia -> !cumplePreferencias(sugerencia, usuario).equals(preferencias));
		return sugerencias;
	}


	private void mostrarOpciones(LinkedList<Sugeribles> sugerencias, Usuario usuario){
		int respuesta;
		while (usuario.poseeRecursosSuficientes(0, 0) && !sugerencias.isEmpty()) {
			if (!usuario.estaEnElItinerario(sugerencias.getFirst()) && sugerenciaDisponible(sugerencias.getFirst(), usuario)) {
				mostrarSugeribles(sugerencias.getFirst());
				System.out.print("\n¿Acepta nuestra sugerencia? (0: Rechazarla / 1: Aceptarla): ");
				respuesta = new Scanner(System.in).nextInt();
				if (respuesta == 1) {
					usuario.aceptarSugerencia(sugerencias.getFirst());
				}
			}
			sugerencias.removeFirst();
		}
	}
	
	private void mostrarSugeribles(Sugeribles sugerible) {
		if (sugerible instanceof Atraccion) {
			System.out.println("\nAtracción:");
			System.out.println("|Nombre de atracción| \t\t |Tipo de atracción| \t |Costo| \t |Tiempo|");
		}
		else if (sugerible instanceof Promocion) {
			System.out.println("\nPromoción:");
			System.out.println("|Nombre de Pack| \t\t |tipo de atraccion| \t |Costo| \t |tiempo| \t |Atracciones incluidas|");
		}
		System.out.println(sugerible.toString());
	}

	public void ordenarPorPrecioYTiempo() {
		Collections.sort(this.promociones, new OrdenadorPorPrecioYTiempo());
		Collections.sort(this.atracciones, new OrdenadorPorPrecioYTiempo());
	}

	private Boolean atracionValida(Atraccion atraccion){
		return (!atraccion.getNombre().equals("") && atraccion.getTipoDeAtraccion() != null);
	}

	private Boolean usuarioValido(Usuario usuario){
		return (!usuario.equals(new Usuario()) && usuario.getItinerario() != null &&
		!usuario.getNombre().equals("") && usuario.getTipoFavorito() != null &&
		 usuario.getPresupuesto() != 0 && usuario.getTiempoDisponible() != 0);
	}

	private Boolean promocionesValidas(Promocion promo){
		return (!promo.getAtraccion().isEmpty() && !promo.getNombre().equals("")
		 && promo.getTipoDeAtraccion() != null);
	}

	private Boolean cumplePreferencias(Sugeribles sugerencia, Usuario usuario){
		return (sugerencia.getTipoDeAtraccion().equals(usuario.getTipoFavorito()));
	}

	private Boolean sugerenciaDisponible(Sugeribles opc, Usuario usuario){
		return !opc.esCupoCompleto() && usuario.poseeRecursosSuficientes(opc.getCosto(), opc.getTiempo());
	}
	
	private Usuario usuarioDeId(int id) {
		return usuarios.get(usuarios.indexOf(new Usuario(id,"",0,0,null,null)));
	}

	@Override
	public String toString() {
		return "Atracciones=" + atracciones + 
		"\n Promociones=" + promociones +
		 "\n Usuarios="
				+ usuarios + "]";
	}
}
