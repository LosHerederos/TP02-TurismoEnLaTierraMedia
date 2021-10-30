package tierraMedia;

import java.util.List;

public class PromocionPorcentual extends Promocion {
	private int idPromocionPorcentual;
	private double porcentaje;

	public PromocionPorcentual(int idPromocion, int idPromocionPorcentual, String nombre, List<Atraccion> atracciones, double porcentaje) {
		super(idPromocion, nombre, atracciones);
		this.idPromocionPorcentual = idPromocionPorcentual;
		this.setPorcentaje(porcentaje);
	}
	
	public int getIdPromocionPorcentual() {
		return this.idPromocionPorcentual;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	private double getPorcentaje() {
		return (this.porcentaje);
	}

	@Override
	public String reduccionCostoTotal() {
		return (super.getNombre() + ":" + "Comprando" + " " + super.getAtraccion() + " " + " "
				+ this.getPorcentaje() * 100 + " % de descuento");
	}

	private double calculoPromocion(Double procentaje) {
		double total = 0;
		total += super.getCosto();
		return total - (total * this.getPorcentaje());
	}

	public double descuento(Double descuento) {
		return (this.calculoPromocion(descuento));
	}
	@Override
	public int getCosto() {
		return ((int)this.descuento(this.getPorcentaje()));
		
	}

}
