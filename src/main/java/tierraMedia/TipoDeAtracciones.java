package tierraMedia;

public enum TipoDeAtracciones {
	AVENTURA("Atracción de aventuras"),
	PAISAJE("Atracción de paisaje"),
	DEGUSTACION("Atracción de degustacion");
	
	private final String descripcion;

    private TipoDeAtracciones(String descripcion){
        this.descripcion = descripcion;
    }

	public String toString() {
		return descripcion;
	}
}
