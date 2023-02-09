package ar.edu.unlu.diezmil.modelo;
import java.io.Serializable;
import java.util.List;

public class Cubilete implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dado miDado; 
	private Cara[] misCaras ;
	private final int maxDados;
	
	public Cubilete() {
		this.maxDados = 5;
		miDado = new Dado(); 
	}		
	
	public void tirar(int cantDados) {
		if (cantDados > 0 && cantDados <= this.maxDados) {
			misCaras = new Cara[cantDados];
			for (int i = 1; i <= cantDados; i++) {
				miDado.lanzar();
				misCaras[i-1] = miDado.getCara();
			}	
		}
	}

	public Cara[] getDados() {
		
		return misCaras;
	}

}