package ar.edu.unlu.diezmil.vista.grafica;

import javax.swing.SwingUtilities;

public class Prueba {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VistaGrafica vista = new VistaGrafica();
			}	
		});

	}

}
