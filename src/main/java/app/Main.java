package app;

import java.util.LinkedList;
import java.util.List;

import javax.swing.plaf.multi.MultiPopupMenuUI;

import tierraMedia.Atraccion;
import tierraMedia.Promocion;
import tierraMedia.PromocionAbsoluta;
import tierraMedia.PromocionPorcentual;
import tierraMedia.SistemaDeSugerencias;
import tierraMedia.TipoDeAtracciones;

public class Main {

	public static void main(String[] args) {

		System.out.println(" Bienvenidos!");
		System.out.println(
				"****************************************************************************************************\n");
		 SistemaDeSugerencias sistema = new SistemaDeSugerencias();
		 sistema.iniciarMenu();
	}

}
