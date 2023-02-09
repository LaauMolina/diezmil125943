package ar.edu.unlu.diezmil.modelo.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unlu.diezmil.modelo.Cubilete;

class TestCombinaciones {

	@BeforeEach
	void setUp() throws Exception {
	}

/*	Estos test ya no se puede ejecutar porque el metodo hayEscalera es privado
 * @Test
	void probarSiHayEscalera_Caso1() {
		// Caso 1: 1,2,3,4,5	
		Cubilete cubi = new Cubilete();
		int[] miCombinacion = {1,1,1,1,1,0};
		assertTrue(cubi.hayEscalera(miCombinacion));
	}
	@Test
	void probarSiHayEscalera_Caso2() {
		// Caso 2: 2,3,4,5,6 	
		Cubilete cubi = new Cubilete();
		int[] miCombinacion = {0,1,1,1,1,1,1};
		assertTrue(cubi.hayEscalera(miCombinacion));
	}
	@Test
	void probarSiHayEscalera_Caso3() {
		// Caso 3: 3,4,5,6,1	
		Cubilete cubi = new Cubilete();
		int[] miCombinacion = {1,0,1,1,1,1};
		assertTrue(cubi.hayEscalera(miCombinacion));
	}@Test
	void probarQueNoHayEscalera() {	
		Cubilete cubi = new Cubilete();
		int[] miCombinacion = {1,1,1,1,0,1};
		assertFalse(cubi.hayEscalera(miCombinacion));
	}
	@Test
	void probarQueNoHayEscalera_Caso2() {	
		Cubilete cubi = new Cubilete();
		int[] miCombinacion = {1,1,0,1,1,1};
		assertFalse(cubi.hayEscalera(miCombinacion));
	}
	@Test
	void probarQueNoHayEscalera_Caso3() {	
		Cubilete cubi = new Cubilete();
		int[] miCombinacion = {1,1,1,2,1,1};
		assertFalse(cubi.hayEscalera(miCombinacion));
	}
*/	
	

}
