package tierraMedia;
import java.util.Comparator;

public class OrdenadorPorPrecioYTiempo implements Comparator<Sugeribles> {

	public int compare(Sugeribles sugerencia, Sugeribles otrasugerencia) {
		if (Integer.compare(sugerencia.getCosto(),otrasugerencia.getCosto()) == 0){
			return Double.compare(sugerencia.getTiempo(), otrasugerencia.getTiempo()) * -1;
		}
		return Integer.compare(sugerencia.getCosto(), 
				otrasugerencia.getCosto()) * -1;
	}

}
