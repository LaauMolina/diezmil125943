package ar.edu.unlu.diezmil.modelo;

public interface IJugador {
	public String getNombre();
	public int getPuntos();
	public void reset();
	public void sumarPuntos(int puntosAct);	
}
