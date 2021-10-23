package tierraMedia;

public enum TipoDeAtracciones {
	PAISAJE("Atracción de paisaje"),
	AVENTURA("Atracción de aventuras"),
	DEGUSTACION("Atracción de degustacion");
	
	private final String descripcion;

    private TipoDeAtracciones(String descripcion){
        this.descripcion = descripcion;
    }

	public String toString() {
		return descripcion;
	}
}
