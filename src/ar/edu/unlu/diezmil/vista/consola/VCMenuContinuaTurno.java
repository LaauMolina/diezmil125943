package ar.edu.unlu.diezmil.vista.consola;

import java.util.Scanner;

import ar.edu.unlu.diezmil.control.Controlador;
import ar.edu.unlu.diezmil.vista.Menu;

public class VCMenuContinuaTurno extends Menu{
	
	public VCMenuContinuaTurno (Controlador miControlador, VistaConsola vista) {
		this.miControlador = miControlador;
		this.miVista = vista;
	}
	
	@Override
	public void mostrar_menu() {
		int opcion = -1;
		while (opcion < 0 || opcion > 2) {

			System.out.println("------------------------------------------------------------------------------");
			System.out.println(" Puede seguir tirando. Dados disponibles: " +  miControlador.getDadoDisponibles());
			System.out.println(" Puntos acumulados en su turno: " +  miControlador.getPuntosParciales());
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("-- 1 - Volver a Tirar                                                       --");
			System.out.println("-- 2 - Sumar Puntos                                                         --");
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("-- 0 - Salir del Juego                                                      --");
			System.out.println("------------------------------------------------------------------------------");
			Scanner sc = new Scanner(System.in);
			opcion = sc.nextInt();
			
			switch (opcion) {
			case 0: miControlador.finalizarJuego();
					break;
			case 1: miControlador.tirar(); 
					break;
			case 2: miControlador.sumarPuntos();
					break;
			}
		}
	}
}