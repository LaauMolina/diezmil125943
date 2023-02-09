package ar.edu.unlu.diezmil.modelo;

public enum Cara {
	UNO, DOS, TRES, CUATRO, CINCO, SEIS;

	Cara valueOf(int ordinal) {
		Cara caraActual = null;
		switch (ordinal) {
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
