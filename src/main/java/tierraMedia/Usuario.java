package tierraMedia;
import java.util.Objects;

public class Usuario {
	
	private int idUsuario;
	private String nombre;
	private int presupuesto;
	private Itinerario itinerario;
	private double tiempoDisponible;
	private TipoDeAtracciones tipoFavorito;
	
	public Usuario() {
		this.nombre = "";
		this.presupuesto = 0;
		this.itinerario = null;
		this.tiempoDisponible = 0;
		this.tipoFavorito = null;
	}
	
	public Usuario(
		int idUsuario,
		String nombre, 
		int presupuesto,
		double tiempoDisponible,
		TipoDeAtracciones tipoFavorito
	) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		setPresupuesto(presupuesto);
		this.itinerario = new Itinerario();
		setTiempoDisponible(tiempoDisponible);
		this.tipoFavorito = tipoFavorito;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(int presupuesto) {
		if (presupuesto < 0) {
			Error presupuestonegativo = new Error("El presupuesto tiene que ser mayor a 0 ");
			throw presupuestonegativo;
		}
		this.presupuesto = presupuesto;
	}

	public Itinerario getItinerario() {
		return itinerario;
	}

	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	public void setTiempoDisponible(double tiempoDisponible) {
		if (tiempoDisponible < 0 ) {
			Error tiemponegativo = new Error("El tiempo disponible tiene que ser mayor a 0 ");
			throw tiemponegativo;
		}
		this.tiempoDisponible = tiempoDisponible;
	}

	public TipoDeAtracciones getTipoFavorito() {
		return tipoFavorito;
	}

	public void setTipoFavorito(TipoDeAtracciones tipoFavorito) {
		this.tipoFavorito = tipoFavorito;
	}
	
	public void aceptarSugerencia(Sugeribles aceptada) {
		this.itinerario.agregarSugerencia(aceptada);
		setPresupuesto(this.presupuesto - aceptada.getCosto());
		setTiempoDisponible(this.tiempoDisponible - aceptada.getTiempo());
	}

	public boolean poseeRecursosSuficientes(int precio, double tiempo){
		return this.tiempoDisponible >= tiempo && this.presupuesto >= precio;
	}

	public Boolean estaEnElItinerario(Sugeribles sugerencia){
		return this.itinerario.getAtracciones().contains(sugerencia) || this.itinerario.getPromociones().contains(sugerencia);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, tipoFavorito);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(nombre, other.nombre) && tipoFavorito == other.tipoFavorito;
	}

	@Override
	public String toString() {
		return "Nombre: " + this.nombre + "\t\tTipo de favorito: " + this.tipoFavorito + "\n"
				+ this.itinerario;
	}
	
	

}