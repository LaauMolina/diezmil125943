package ar.edu.unlu.diezmil.modelo;

import java.io.Serializable;

public class Turno implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Tirada miTirada = new Tirada() ;
	private Cara[] caras;
	private int puntosAnteriores;
	private int puntosTurno;
	private int puntosTirada;
	private int dadosUsados;
	private int dadosDisponibles;
	private boolean gano;
	private boolean perdio;
	private final int puntosGanar = 1000;
	
	public Turno(int puntos) {
		puntosAnteriores = puntos;
		puntosTurno  = 0;
		puntosTirada = 0;
		dadosUsados  = 0;
		dadosDisponibles = 5;
		gano   = false;
		perdio = false;
	}	

	public Cara[] getCaras() {
		return caras;
	}

	public int getPuntosTurno() {
		return puntosTurno;
	}

	public int getPuntosTirada() {
		return puntosTirada;
	}

	public int getDadosUsados() {
		return dadosUsados;
	}

	public int getDadosDisponibles() {
		return dadosDisponibles;
	}

	public boolean gano() {
		return gano;
	}

	public boolean perdio() {
		return perdio;
	}

	public void tirar() {
		// Tira el cubilete con la cantidad de dados disponibles
		miTirada.tirar(dadosDisponibles);
		puntosTirada = miTirada.getPuntaje();
		caras = miTirada.getDados();
		// Si no obtuvo puntos, perdio el turno
		if ( puntosTirada > 0 ) {
			puntosTurno += puntosTirada;
			//Se consulta si gano el partido
			if (( puntosAnteriores + puntosTurno ) < puntosGanar) {
				//Si no gano ni perdio modifico la cantidad de dados 
				if ( dadosDisponibles == miTirada.getDadosOcupados() ) {
					dadosUsados = 0;
					dadosDisponibles = 5;
				} else {
					dadosUsados += miTirada.getDadosOcupados();
					dadosDisponibles = 5 - dadosUsados;
				}
			} else {
				gano = true;
			}
			
		} else {
			perdio = true;
			puntosTurno = 0;
		}		
	}
	
	

}
