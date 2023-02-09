package ar.edu.unlu.diezmil.modelo;

import java.io.Serializable;

public class Jugador implements IJugador, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int puntos;
	private boolean gano;

	public Jugador(String newNombre) {
		nombre = newNombre;
		reset();
	}

	@Override
	public String getNombre() {
		return nombre;
	}
	
	@Override
	public int getPuntos() {
		return puntos;
	}

	@Override
	public void reset() {
		puntos = 0;
		gano = false;		
	}

	@Override
	public void sumarPuntos(int puntosAct) {
		puntos +=  puntosAct;
		
	}

}
