package ar.edu.unlu.diezmil.modelo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;
import ar.edu.unlu.diezmil.Serializacion.Serializador;
import ar.edu.unlu.diezmil.control.Controlador;

public class Juego extends Observable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static enum ESTADOS {CONFIGURANDO, JUGANDO, FINALIZADO};
	private ESTADOS estado = ESTADOS.CONFIGURANDO;
	private ArrayList<Jugador> misJugadores;
	private int jugadorActual;
	private boolean jugando;
	private Turno turno; 
	private IJugador ganador = new Jugador("x") ;
	private Serializador serializadorGanador = new Serializador("ganadores.txt");

	public void comenzar() {
		jugadorActual = 0;
		misJugadores = new ArrayList<>();
		ganador.reset();
		avisar(Estado.CAMBIO_ESTADO);		
	}
	private void avisar(Estado cambio) {
		setChanged();
		notifyObservers(cambio);
	}
	
	public ESTADOS getEstado() {
		return estado;
	}
	public void finalizarJuego() {
		jugando = false;
		estado = ESTADOS.CONFIGURANDO;
		avisar(Estado.CAMBIO_ESTADO);		
	}
	public void agregarJugador(String nombre) {
		Jugador j = new Jugador(nombre);
		misJugadores.add(j);
		avisar(Estado.CAMBIO_LISTA_JUGADORES);
		avisar(Estado.CAMBIO_ESTADO);
	}

	public ArrayList<IJugador> getJugadores(){
		ArrayList<IJugador> mjug = new ArrayList<>();
		for (IJugador j : misJugadores) {
			mjug.add(j);
		}
		return mjug;		
	}
	public String getJugadorActual() {
		return misJugadores.get(jugadorActual).getNombre();
	}
	
	public String getGanador() {
		return ganador.getNombre();
	}
	
	public Cara[] getDados() {
		return turno.getCaras();		
	}
	
	public void IniciarPartida() {
		if (misJugadores.size() < 2) {
			avisar(Estado.FALTAN_JUGADORES);	
		}  else {
			jugando = true;
			estado = ESTADOS.JUGANDO;
			turno = new Turno(misJugadores.get(jugadorActual).getPuntos());
			avisar(Estado.CAMBIO_ESTADO);
		}		
	}
	public void tirar() throws RemoteException {
		if (turno.getDadosDisponibles() > 0 ) {	
			turno.tirar();
			avisar(Estado.NUEVA_TIRADA);
			if (turno.gano()) {
				ganador = misJugadores.get(jugadorActual);
				try {
					guardarGanador(misJugadores.get(jugadorActual));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				for (IJugador j : misJugadores) {
					j.reset();
				}
				avisar(Estado.HAY_GANADOR);
				avisar(Estado.CAMBIO_ESTADO);
				avisar(Estado.CAMBIO_LISTA_JUGADORES);
				finalizarJuego();
			} else if (turno.perdio()) {
				avisar(Estado.PERDIO_TURNO);
				cambiarJugador();
			} else {
				avisar(Estado.CONTINUA_TURNO);
			}
		}
	}
	private void guardarGanador(Jugador ganador) throws RemoteException
	{	
		serializadorGanador.guardar(ganador);
	}
	public int getDadosDisponibles() {
		return turno.getDadosDisponibles();
	}

	public int getPuntosParciales() {
		return turno.getPuntosTurno();		
	}
	public void sumarPuntos() {
		misJugadores.get(jugadorActual).sumarPuntos(turno.getPuntosTurno());
		cambiarJugador();
		avisar(Estado.CAMBIO_LISTA_JUGADORES);
		IniciarPartida();
	}
	private void cambiarJugador() {
		jugadorActual ++;
		if (jugadorActual == misJugadores.size()) {
			jugadorActual = 0;
		}
		IniciarPartida();
	}
	public int getPuntosTirada() {
		return turno.getPuntosTirada();
	}
	public void guardar() {
		
		
	}
	public ArrayList<IJugador> getGanadores() {
		Object[] ganadores = serializadorGanador.readObjectsTopCinco();
		ArrayList<IJugador> mjug = new ArrayList<>();
		for(int x = 0; x < ganadores.length; x ++) {
			mjug.add((IJugador) ganadores[x]);
		}
		return mjug;
	}
}
