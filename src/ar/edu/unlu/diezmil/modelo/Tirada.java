package ar.edu.unlu.diezmil.modelo;

import java.io.Serializable;

public class Tirada implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Cubilete miCubilete = new Cubilete() ;
	private Cara[] misDados;
	private int puntaje;
	private int dadosOcupados;

	public void tirar(int cantDados) {
		// cambiar cubilete para que devuelva las caras al tirar
		miCubilete.tirar(cantDados);
		misDados = miCubilete.getDados();
		calcularPuntos();
	}	
	
	public int getPuntaje() {
		return puntaje;
	}

	public int getDadosOcupados() {
		return dadosOcupados;
	}
	
	public Cara[] getDados() {
		
		return misDados;
	}
	private void calcularPuntos() {
		puntaje = 0;
		dadosOcupados = 0;
		// Calcular frecuencia
		int[] frecuencias = contarDados();
		
		// Si se tiro el cubilete de cinco dados
		if( misDados.length == 5 ) {
			//Se busca combinacion de cinco unos
			if (frecuencias[Cara.UNO.ordinal()] == 5) {
				this.puntaje = 10000;
				this.dadosOcupados = 5;
			}else {
				//Se busca escalera
				if (this.hayEscalera(frecuencias)) {
					this.puntaje = 500;
					this.dadosOcupados = 5;
				}
			}			
		}
		// Si se tiro cubilete menor a cinco dados o
		// no fueron correctas las combinaciones anteriores
		if ( this.puntaje == 0 ) {
			this.puntaje = this.sumarDados(frecuencias);
		}
	}
	
// METODOS AUXILIARES PARA CALCULAR EL PUNTAJE

	// Se cuenta la cantidad de dados por cada cara
	private int[] contarDados() {
		int[] valores = new int[Cara.values().length];
		for (int v: valores)
			v = 0;
		
		for(int i = 1; i <= misDados.length; i++) {
			int pos = misDados[i-1].ordinal();//misDados[i-1].getCara().ordinal();
			valores[pos] ++;
		}
		return valores;
	}

	// Se comprueba si hubo escalera
	private boolean hayEscalera(int[] valores) {
	//Pongo el metodo publico para probarlo en el test
	//public boolean hayEscalera(int[] resultado) {
	//500 puntos con escalera (1,2,3,4,5 o 2,3,4,5,6 o 3,4,5,6,1)
		boolean encontro = false;
		
		if (valores[Cara.TRES.ordinal()]   == 1 &&
			valores[Cara.CUATRO.ordinal()] == 1 &&
			valores[Cara.CINCO.ordinal()]  == 1 ) {
			
			if ( // Caso 1: 1,2,3,4,5
				(valores[Cara.UNO.ordinal()]  == 1 &&
				 valores[Cara.DOS.ordinal()]  == 1 ) ||
				 // Caso 2: 2,3,4,5,6
				(valores[Cara.DOS.ordinal()]  == 1 &&
				 valores[Cara.SEIS.ordinal()] == 1 )||
				 // Caso 3: 3,4,5,6,1
				(valores[Cara.SEIS.ordinal()] == 1 &&
				 valores[Cara.UNO.ordinal()]  == 1 )) {
				// Se comprobo alguna de las combinaciones 
				
				encontro = true;
			}
		}
		return encontro;
	}
	
	// Se suman los puntos segun la cantidad de dados
	private int sumarDados(int[] valores) {
		int puntos = 0;
		int cara = 1;
		// Primero verifico si algun dado se repitio 3 veces
		for (int v: valores) {
			//Sumo s
			if ( v == 3 ) {
				if ( cara == 1 ) {
					puntos += 1000;
				} else {
					puntos += (cara*100);
				}
				this.dadosOcupados +=3;
			}else {
				// Sumo los unos
				if( ( cara == 1 ) && (v > 0) ) {
					puntos += ( 100 * v );
					this.dadosOcupados += v;
				} else {
				// Sumo los cincos	
					if( ( cara == 5 ) && (v > 0) ) {
						puntos += ( 50 * v );
						this.dadosOcupados += v;
					}	
				}
			}
		cara++;			
		}	
		return puntos;
	}
}

