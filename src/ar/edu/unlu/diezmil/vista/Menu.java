package ar.edu.unlu.diezmil.vista;

import ar.edu.unlu.diezmil.control.Controlador;
import ar.edu.unlu.diezmil.vista.consola.VistaConsola;

public abstract class Menu {
	protected Controlador miControlador;
	protected VistaConsola miVista;
	public abstract void mostrar_menu();

}
