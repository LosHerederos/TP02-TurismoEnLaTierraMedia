package tierraMedia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PromocionAXB extends Promocion {
	private int idPromocionAXB;
	List<Atraccion> atraccionesPagas = new ArrayList<Atraccion>();

	public PromocionAXB(int idPromocion, int idPromocionAXB, String nombre, List<Atraccion> atracciones, List<Atraccion> atraccionesPagas) {
		super(idPromocion, nombre, atracciones);
		this.idPromocionAXB = idPromocionAXB;
		this.setAtraccionesPagas(atraccionesPagas);

	}
	
	public int getIdPromocionAXB() {
		return this.idPromocionAXB;
	}

	public List<Atraccion> getAtraccionesPagas() {
		return this.atraccionesPagas;
	}

	@Override
	public int getCosto() {
		int costoTotal = 0;
		for (Atraccion atraccion : atraccionesPagas) {
			costoTotal += atraccion.getCosto();
		}
		return (costoTotal);
	}

	public void setAtraccionesPagas(List<Atraccion> atraccionesPagas) {
		this.atraccionesPagas.addAll(atraccionesPagas);
	}

	@Override
	public double getTiempo() {
		double tiempoTotal = 0;
		for (Atraccion atraccion : atraccionesPagas) {
			tiempoTotal += atraccion.getTiempo();
		}
		tiempoTotal += super.getTiempo();
		return (tiempoTotal);
	}

	@Override
	public void agregarVisitante() {
		if (!this.esCupoCompleto() && !super.esCupoCompleto()) {
			super.agregarVisitante();
			for (Atraccion atraccion : atraccionesPagas) {
				atraccion.agregarVisitante();
			}

		}
	}

	@Override
	public boolean esCupoCompleto() {
		boolean cupoCompleto = false;
		super.esCupoCompleto();
		Iterator<Atraccion> atraccion = atraccionesPagas.listIterator();
		while (atraccion.hasNext()) {
			if (atraccion.next().esCupoCompleto()) {
				cupoCompleto = true;
			}
		}
		return cupoCompleto;
	}
	@Override
	public String getNombreDeAtraccion() {
		String nombreAtraccion = "";
		for (Atraccion atraccion : atraccionesPagas) {
			nombreAtraccion += "\n" + String.format("%110s", atraccion.getNombre());
		}

		return nombreAtraccion;

	}
	

	@Override
	public String reduccionCostoTotal() {
		return (super.getNombre() + ":" + "Comprando " + this.getAtraccionesPagas() + super.getAtraccion() + "gratis");
	}
	@Override
	public String toString() {
		return (super.toString()+"\n\t\t\t\t\t\t\t\t\t\t\t\t"+ String.format("%4s %4s","|Atraccion gratis|" , super.getNombreDeAtraccion())+"\n");
	}

}
