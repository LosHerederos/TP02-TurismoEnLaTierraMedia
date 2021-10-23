package tierraMedia;

import java.util.List;

public class PromocionAbsoluta extends Promocion {
	private int descuento;

	public PromocionAbsoluta(String nombre, List<Atraccion> atracciones, int descuento) {
		super(nombre, atracciones);
		this.setDescuento(descuento);
	}

	@Override
	public int getCosto() {
		return descuento;
	}

	private void setDescuento(int descuento) {
		this.descuento = descuento;

	}

	@Override
	protected String reduccionCostoTotal() {
		return (super.getNombre() + ":" + super.getAtraccion() + " " + "a monedas" + this.getCosto());
	}
}
