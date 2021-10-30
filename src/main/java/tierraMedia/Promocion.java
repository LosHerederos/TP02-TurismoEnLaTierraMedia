package tierraMedia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Promocion implements Sugeribles {
	private int idPromocion;
	protected String nombre;
	private List<Atraccion> atracciones = new ArrayList<Atraccion>();
	private TipoDeAtracciones tipoDeAtraccion;

	public Promocion(int idPromocion, String nombre, List<Atraccion> atracciones) {
		this.idPromocion = idPromocion;
		this.setNombre(nombre);
		this.setAtraccion(atracciones);
		if (!atracciones.isEmpty()) {
			this.setTipodeAtraccion();
		}
		
	}

	public Promocion() {
		this.idPromocion = 0;
		this.setNombre(null);
		if (!this.atracciones.isEmpty()) {
			this.setTipodeAtraccion();
		}
	}
	
	public int getIdPromocion() {
		return this.idPromocion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setAtraccion(List<Atraccion> atracciones) {
		this.atracciones.addAll(atracciones);
	}

	public List<Atraccion> getAtraccion() {
		return (atracciones);
	}

	public int getCosto() {
		int costoTotal = 0;
		for (Atraccion atraccion : atracciones) {

			costoTotal += atraccion.getCosto();

		}

		return (costoTotal);

	}

	public double getTiempo() {

		double tiempoTotal = 0;
		for (Atraccion atraccion : atracciones) {

			tiempoTotal += atraccion.getTiempo();

		}

		return (tiempoTotal);

	}

	public void agregarVisitante() {
		if (!this.esCupoCompleto()) {
			for (Atraccion atraccion : atracciones) {
				atraccion.agregarVisitante();
			}

		}
	}

	public boolean esCupoCompleto() {
		boolean cupoCompleto = false;

		Iterator<Atraccion> atraccion = atracciones.listIterator();
		while (atraccion.hasNext()) {
			if (atraccion.next().esCupoCompleto()) {
				cupoCompleto = true;
			}
		}
		return cupoCompleto;

	}

	public String getNombreDeAtraccion() {
		String nombreAtraccion = "";
		for (Atraccion atraccion : atracciones) {
			nombreAtraccion += "\n" + String.format("%110s", atraccion.getNombre());
		}

		return nombreAtraccion;

	}

	public void setTipodeAtraccion() {
		this.tipoDeAtraccion = atracciones.get(0).getTipoDeAtraccion();
	}

	public TipoDeAtracciones getTipoDeAtraccion() {
		return (this.tipoDeAtraccion);
	}

	abstract String reduccionCostoTotal();

	@Override
	public String toString() {
		return String.format("%8s %38s %8s %14s %1s", this.getNombre(), this.getTipoDeAtraccion(), this.getCosto(),
				this.getTiempo(), this.getNombreDeAtraccion());

	}
}