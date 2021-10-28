package tierraMedia;

import java.util.Objects;

public class Atraccion implements Sugeribles {

	private int idAtraccion;
	private int costoVisita;
	private double tiempoParaRealizarla;
	private int cupoPersonas;
	private TipoDeAtracciones tipoDeAtracciones;
	private int visitantes;
	private String nombre;

	// constructor
	public Atraccion(int idAtraccion, int costo, double tiempo, int cupo, TipoDeAtracciones tipoAtraccion, String nombre) {
		this.setIdAtraccion(idAtraccion);
		this.setCostoVisita(costo);
		this.setTiempoParaRealizarla(tiempo);
		this.setCupoPersonas(cupo);
		this.setTipoDeAtraccion(tipoAtraccion);
		this.visitantes = 0;
		this.nombre = nombre;
	}

	public boolean esCupoCompleto() {
		return this.visitantes == this.cupoPersonas;
	}

	public void agregarVisitante() {
		this.visitantes += 1;
	}

	// getters y setters
	public int getCosto() {
		return this.costoVisita;
	}
	
	public void setIdAtraccion(int idAtraccion) {
		this.idAtraccion = idAtraccion;
	}

	public int getIdAtraccion() {
		return idAtraccion;
	}
	
	public void setCostoVisita(int costoVisita) {
		this.costoVisita = costoVisita;
	}

	public double getTiempo() {
		return this.tiempoParaRealizarla;
	}

	public void setTiempoParaRealizarla(double tiempoParaRealizarla) {
		this.tiempoParaRealizarla = tiempoParaRealizarla;
	}

	public int getCupoPersonas() {
		return this.cupoPersonas;
	}

	public void setCupoPersonas(int cupoPersonas) {
		this.cupoPersonas = cupoPersonas;
	}

	public TipoDeAtracciones getTipoDeAtraccion() {
		return (this.tipoDeAtracciones);
	}
	
	public void setTipoDeAtraccion(TipoDeAtracciones tipoDeAtraccion) {
		this.tipoDeAtracciones = tipoDeAtraccion;
	}

	public int getVisitantes() {
		return this.visitantes;
	}

	public void setVisitantes(int visitantes) {
		this.visitantes = visitantes;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
		
	@Override
	public int hashCode() {
		return Objects.hash(costoVisita, cupoPersonas, nombre, tiempoParaRealizarla, tipoDeAtracciones, visitantes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return costoVisita == other.costoVisita && cupoPersonas == other.cupoPersonas
				&& Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(tiempoParaRealizarla) == Double.doubleToLongBits(other.tiempoParaRealizarla)
				&& tipoDeAtracciones == other.tipoDeAtracciones && visitantes == other.visitantes;
	}

	@Override
	public String toString() {
		return this.nombre
				+ "\t\t\t" + this.tipoDeAtracciones
				+ "\t    " + this.costoVisita
				+ "\t\t    " + this.tiempoParaRealizarla;
	}

}
