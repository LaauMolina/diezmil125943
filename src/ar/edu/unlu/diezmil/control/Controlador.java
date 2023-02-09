package ar.edu.unlu.diezmil.control;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import ar.edu.unlu.diezmil.modelo.Cara;
import ar.edu.unlu.diezmil.modelo.Estado;
import ar.edu.unlu.diezmil.modelo.IJugador;
import ar.edu.unlu.diezmil.modelo.Juego;
import ar.edu.unlu.diezmil.vista.consola.VistaConsola;
import ar.edu.unlu.diezmil.vista.grafica.VistaGrafica;

public class Controlador implements Observer  {
	
	private Juego miJuego;
    private IVista miVista;//para usar la vista
    
    public Controlador() {	
	}
    
	public Controlador(Juego juego, IVista vista) {
		this.miJuego = juego; 
		this.miVista = vista;
		miJuego.addObserver(this); 
		miVista.setControlador(this);		  
	}

	public static void main(String[] args) {
		Juego juego = new Juego();
		//IVista vista  = new  VistaConsola();
		IVista vista  = new  VistaGrafica();
		Controlador c = new Controlador(juego, vista);
		juego.comenzar();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Estado) {
			Estado cambio = (Estado) arg;
			switch (cambio) {
			case CAMBIO_ESTADO:
				Juego.ESTADOS e = miJuego.getEstado();
				if (e == Juego.ESTADOS.JUGANDO) {
					miVista.mostrarJugando();
				} else if (e == Juego.ESTADOS.CONFIGURANDO) {
					miVista.mostrarConfiguracion();
				} else if (e == Juego.ESTADOS.FINALIZADO) {
					miVista.mostrarFinal();
				} 
				break;
			case CAMBIO_JUGADOR:
				miVista.mostrarJugando();
				break;
			case CAMBIO_LISTA_JUGADORES:
				miVista.mostrarJugadores();
				break;		
			case NUEVA_TIRADA:
				miVista.mostrarTirada();
				break;		
			case HAY_GANADOR:
				miVista.mostrarGanador();
				break;		
			case FALTAN_JUGADORES:
				miVista.mostrarFaltanJugadores();
				break;		
			case PERDIO_TURNO:
				miVista.mostarPerdio();
				break;		
			case CONTINUA_TURNO:
				miVista.mostrarContinua();	
				break;				
			}	
		}
	}

	public void finalizarJuego() {
		miJuego.finalizarJuego();		
	}

	public void agregarJugador(String nombre) {
		miJuego.agregarJugador(nombre);	
	}

	public ArrayList<IJugador> getJugadores() {
		return miJuego.getJugadores();
	}

	public String getJugadorActual() {
		return miJuego.getJugadorActual();
	}

	public void IniciarPartida() {
		miJuego.IniciarPartida();
	}

	public void tirar() {
		try {
			miJuego.tirar();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public String getGanador() {
		return miJuego.getGanador();
	}

	public Cara[] getDados() {
		return miJuego.getDados();	
	}

	public int getDadoDisponibles() {
		return miJuego.getDadosDisponibles();
	}

	public int getPuntosParciales() {
		return miJuego.getPuntosParciales();
	}

	public void sumarPuntos() {
		miJuego.sumarPuntos();
	}

	public int getPuntosTirada() {
		// TODO Auto-generated method stub
		return miJuego.getPuntosTirada();
	}

	public void guardar() {
		miJuego.guardar();
		
	}

	public ArrayList<IJugador> getGanadores() {
		return miJuego.getGanadores();
	}
	
	
	

}
