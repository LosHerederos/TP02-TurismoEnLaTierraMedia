package app;

import tierraMedia.SistemaDeSugerencias;


public class Main {

	public static void main(String[] args) {

		System.out.println(" Bienvenidos!");
		System.out.println(
				"****************************************************************************************************\n");
		 SistemaDeSugerencias sistema = new SistemaDeSugerencias();
		  try {
			sistema.iniciarMenu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
