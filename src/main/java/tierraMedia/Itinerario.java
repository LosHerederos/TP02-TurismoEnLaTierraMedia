package tierraMedia;

import java.util.ArrayList;
import java.util.List;

public class Itinerario {
	private List<Atraccion> atracciones;
	private List<Promocion> promociones;
	
	public Itinerario() {
		this.atracciones = new ArrayList<Atraccion>();
		this.promociones = new ArrayList<Promocion>();
	}
	
	public Itinerario(List<Atraccion> atracciones, List<Promocion> promociones) {
		super();
		this.atracciones = atracciones;
		this.promociones = promociones;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	public void setAtracciones(List<Atraccion> atracciones) {
		this.atracciones = atracciones;
	}

	public List<Promocion> getPromociones() {
		return promociones;
	}

	public void setPromociones(List<Promocion> promociones) {
		this.promociones = promociones;
	}

	public void agregarSugerencia(Sugeribles sugerencia){
		if (sugerencia.getClass().equals(Atraccion.class)) {
			this.atracciones.add((Atraccion) sugerencia);
		} else {
			this.promociones.add((Promocion) sugerencia);
		}
		sugerencia.agregarVisitante();
	}
	
	public double horasNecesarias() {
		double horas = 0;
		for (Promocion promo : promociones) {
			horas += promo.getTiempo();
		}
		for (Atraccion atraccion : atracciones) {
			horas += atraccion.getTiempo();
		}
		return horas;
	}
	
	public int costoTotal() {
		int total = 0;
		for (Promocion promo : promociones) {
			total+=promo.getCosto();
		}
		for (Atraccion atraccion : atracciones) {
			total += atraccion.getCosto();
		}
		return total;
	}

	@Override
	public String toString() {
		String listaDeAtracciones = "";
		String listaDePromociones = "";
		
		for (Atraccion atraccion : atracciones) {
			listaDeAtracciones += atraccion + "\n";
		}
		
		for (Promocion promocion : promociones) {
			listaDePromociones += promocion + "\n";
		}
		return "\nItinerario\n"
				+ "====================================================================================================\n"
				+ "Promociones ****************************************************************************************\n"
				+ "----------------------------------------------------------------------------------------------------\n"
				+ "|Nombre del pack| \t\t |Tipo de atracción| \t |Costo| \t |Tiempo| \t |Atracciones incluidas| \n"
				+ "____________________________________________________________________________________________________\n"
		        + listaDePromociones
		        + "====================================================================================================\n"
				+ "Atracciones ****************************************************************************************\n"
				+ "----------------------------------------------------------------------------------------------------\n"
				+ "|Nombre de atracción| \t\t |Tipo de atracción| \t |Costo| \t |Tiempo| \n"
				+ "____________________________________________________________________________________________________\n"
		        + listaDeAtracciones
		        + "====================================================================================================\n"
		        + "Costo total: " + this.costoTotal() + "\t\t\tTiempo total: " + this.horasNecesarias() + "\n"
				+ "====================================================================================================\n";
	}
	
	
	
}