package ar.edu.unlu.diezmil.vista.consola;

import java.util.ArrayList;
import java.util.Scanner;

import ar.edu.unlu.diezmil.control.Controlador;
import ar.edu.unlu.diezmil.control.IVista;
import ar.edu.unlu.diezmil.modelo.Cara;
import ar.edu.unlu.diezmil.modelo.IJugador;
import ar.edu.unlu.diezmil.modelo.Jugador;
import ar.edu.unlu.diezmil.vista.Menu;

public class VistaConsola implements IVista {
	private Menu miMenu;
	private Controlador miControlador;
	
	public VistaConsola() {//constructor de la clase
	}

	public void agregarJugador() {
		String nombre = "";
		while (nombre.equals("")) {
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("--                           Agregando Jugador                              --");
			System.out.println("------------------------------------------------------------------------------");
			System.out.print("-- Nombre : ");
			Scanner sc = new Scanner(System.in);
			nombre = sc.nextLine();
			miControlador.agregarJugador(nombre);
		}			
	}

	@Override
	public void mostrarTirada() {
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("--                             Ultimo lanzamiento                           --");
		System.out.println("------------------------------------------------------------------------------");
		Cara[] tirada = miControlador.getDados();
		for(Cara c: tirada) {
			System.out.print(c.toString()+ " - ");	
		System.out.println("");
		}
		System.out.println("Punto:"+ miControlador.getPuntosTirada());
	}

	@Override
	public void setControlador(Controlador controlador) {
		this.miControlador = controlador;
	}

	@Override
	public void mostrarJugadores() {
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("--                            Lista de Jugadores                            --");
		System.out.println("------------------------------------------------------------------------------");
		ArrayList<IJugador> misJugadores = miControlador.getJugadores();
		for(IJugador j : misJugadores) 
			System.out.printf("Nombre: %s  \t - Puntos: %s \n",j.getNombre(), j.getPuntos() );
		System.out.println("------------------------------------------------------------------------------");
		esperarEnter();
	}

	private void esperarEnter() {
		System.out.println("Presione ENTER para continuar...");
		Scanner sc = new Scanner(System.in);
		String pausa = sc.nextLine();
	}

	@Override
	public void mostrarJugando() {
		miMenu = new VCMenuJugando(miControlador, this);
		miMenu.mostrar_menu();		
	}

	@Override
	public void mostrarConfiguracion() {
		miMenu = new VCMenuConfig(miControlador, this);
		miMenu.mostrar_menu();
	}

	@Override
	public void mostrarFinal() {
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("--                            TERMINO EL JUEGO                              --");
		System.out.println("------------------------------------------------------------------------------");
		Scanner sc = new Scanner(System.in);
		String pausa = sc.nextLine();
	}

	@Override
	public void mostrarGanador() {
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("--                            TERMINO EL JUEGO                              --");
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("El ganador es: "+ miControlador.getGanador());
		esperarEnter();
		miMenu = new VCMenuConfig(miControlador, this);
		miMenu.mostrar_menu();				
	}

	@Override
	public void mostrarFaltanJugadores() {
		System.out.println("Debe ingresar al menos dos jugadores antes de comenzar el Juego");
		esperarEnter();
		miMenu = new VCMenuConfig(miControlador, this);
		miMenu.mostrar_menu();
	}
	
	@Override
	public void mostarPerdio() {
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("Perdio turno");
		esperarEnter();	
	}

	@Override
	public void mostrarContinua() {
		miMenu = new VCMenuContinuaTurno(miControlador, this);
		miMenu.mostrar_menu();		
	}
}
