package ar.edu.unlu.diezmil.modelo;

import java.io.Serializable;
import java.util.Random;

public class Dado implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int valor;
	private final int cant_caras = 6;
	private Random suerte ;
	public Dado() {
		this.suerte = new Random();
	}	
	public void lanzar() { // Se tira el dado y se guarda el valor obtenido
		this.valor = suerte.nextInt(cant_caras)+1;
	}
	
	// Ver cual de los 2 mantengo	
	public int getValorCara() {
		return this.valor;
	}
	
	public Cara getCara() {
		Cara caraActual = null;
		switch (this.valor) {
		case 1:
			caraActual = Cara.UNO;
			break;
		case 2:
			caraActual = Cara.DOS;
			break;	
		case 3:
			caraActual = Cara.TRES;
			break;
		case 4:
			caraActual = Cara.CUATRO;
			break;
		case 5:
			caraActual = Cara.CINCO;
			break;
		case 6:
			caraActual = Cara.SEIS;
			break;
		}
		return caraActual; 
	}
}
