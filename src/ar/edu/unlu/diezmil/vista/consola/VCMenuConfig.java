package ar.edu.unlu.diezmil.vista.consola;

import java.util.Scanner;

import ar.edu.unlu.diezmil.control.Controlador;
import ar.edu.unlu.diezmil.vista.Menu;

public class VCMenuConfig extends Menu{
	
	public VCMenuConfig (Controlador miControlador, VistaConsola vista) {
		this.miControlador = miControlador;
		this.miVista = vista;
	}

	@Override
	public void mostrar_menu() {
		int opcion = -1;
		while (opcion < 0 || opcion > 3) {
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("--                                 DIEZMIL                                  --");
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("");
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("--                          Menú de Configuración                           --");
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("--                                Opciones                                  --");
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("-- 1 - Agregar Jugador                                                      --");
			System.out.println("-- 2 - Mostrar Lista de Jugadores                                           --");
			System.out.println("-- 3 - Comenzar Partida                                                     --");
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("-- 0 - Salir del Juego                                                      --");
			System.out.println("------------------------------------------------------------------------------");
			Scanner sc = new Scanner(System.in);
			opcion = sc.nextInt();
			
			switch (opcion) {
			case 0:miControlador.finalizarJuego();
					break;
			case 1: miVista.agregarJugador(); 
					break;
			case 2: miVista.mostrarJugadores();
					mostrar_menu();
					break;
			case 3: miControlador.IniciarPartida();
			        break;
						
		}
			
			
			
		}
		
		
	}


}
