package tierraMedia;

import java.util.List;

public class PromocionAbsoluta extends Promocion {
	private int idPromocionAbsoluta;
	private int descuento;

	public PromocionAbsoluta(int idPromocion, int idPromocionAbsoluta, String nombre, List<Atraccion> atracciones, int descuento) {
		super(idPromocion, nombre, atracciones);
		this.idPromocionAbsoluta = idPromocionAbsoluta;
		this.setDescuento(descuento);
	}
	
	public int getIdPromocionAbsoluta() {
		return this.idPromocionAbsoluta;
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
