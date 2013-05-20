package com.schooler.ledcube.test;

import junit.framework.TestCase;

import com.schooler.ledcube.bitmap.ArrayBitmap2D;

public class ArrayBitmap2DTest extends TestCase {

	public void testSimpleSetAndGets() {
		checkSingleSetAndGet(0, 0);
		checkSingleSetAndGet(1, 0);
		checkSingleSetAndGet(7, 5);
		checkSingleSetAndGet(7, 7);
		checkSingleSetAndGet(8, 0);
		checkSingleSetAndGet(20, 300);
	}

	public void testResize() {
		ArrayBitmap2D bitmap = new ArrayBitmap2D(8, 8);
		bitmap.set(2, 0, true);
		bitmap.set(6, 3, true);

		assertTrue(bitmap.get(2, 0));
		assertTrue(bitmap.get(6, 3));

		assertFalse(bitmap.get(10, 2));
		assertFalse(bitmap.get(3, 4));

		bitmap.set(10, 2, true);
		bitmap.set(3, 4, true);

		assertTrue(bitmap.get(2, 0));
		assertTrue(bitmap.get(6, 3));
		assertTrue(bitmap.get(10, 2));
		assertTrue(bitmap.get(3, 4));

	}

	private static void checkSingleSetAndGet(int x, int y) {
		ArrayBitmap2D bitmap = new ArrayBitmap2D(8, 8);
		bitmap.set(x, y, true);

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				boolean actual = bitmap.get(i, j);
				boolean expected = i == x && j == y;
				assertEquals(expected, actual);
			}
		}
	}
}
