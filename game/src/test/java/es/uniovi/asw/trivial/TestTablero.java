package es.uniovi.asw.trivial;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import es.uniovi.asw.game.model.Tablero;

public class TestTablero {

	@Test
	public void test() {
		Tablero t = new Tablero(6,6,6,5);
		
		assertFalse(t.posibleQuesito(0));
		assertFalse(t.posibleQuesito(42));
		assertFalse(t.posibleQuesito(2));
		assertTrue(t.posibleQuesito(1));
		assertTrue(t.posibleQuesito(8));
		assertTrue(t.posibleQuesito(15));
		assertTrue(t.posibleQuesito(22));
		assertTrue(t.posibleQuesito(29));
		assertTrue(t.posibleQuesito(36));
		
		assertFalse(t.posibleFin(0));
		assertFalse(t.posibleFin(1));
		assertTrue(t.posibleFin(47));
		assertTrue(t.posibleFin(52));
		assertTrue(t.posibleFin(57));
		assertTrue(t.posibleFin(62));
		assertTrue(t.posibleFin(67));
		assertTrue(t.posibleFin(72));

		assertEquals(43, t.calculaPosicionQuesito(1));
		assertEquals(48, t.calculaPosicionQuesito(8));
		assertEquals(53, t.calculaPosicionQuesito(15));
		assertEquals(58, t.calculaPosicionQuesito(22));
		assertEquals(63, t.calculaPosicionQuesito(29));
		assertEquals(68, t.calculaPosicionQuesito(36));


		assertArrayEquals(new int[]{2,42,43}, t.calculaSiguienteCasilla(1));
		assertArrayEquals(new int[]{9,7,48}, t.calculaSiguienteCasilla(8));
		assertArrayEquals(new int[]{16,14,53}, t.calculaSiguienteCasilla(15));
		assertArrayEquals(new int[]{23,21,58}, t.calculaSiguienteCasilla(22));
		assertArrayEquals(new int[]{30,28,63}, t.calculaSiguienteCasilla(29));
		assertArrayEquals(new int[]{37,35,68}, t.calculaSiguienteCasilla(36));
		
		assertArrayEquals(new int[]{3,1}, t.calculaSiguienteCasilla(2));
		assertArrayEquals(new int[]{71,69}, t.calculaSiguienteCasilla(70));
		assertArrayEquals(null, t.calculaSiguienteCasilla(78));

		// test sobre posiciones faltaria el método de probar...
		int[] r = t.mover(6,12);
		for (int i = 0; i<r.length; i++)
		{
			System.out.println("-->"+r[i]);
		}
	}

}
