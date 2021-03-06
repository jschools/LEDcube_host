package com.schooler.ledcube.test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

import com.schooler.ledcube.model.CubeFrames;
import com.schooler.ledcube.model.Point3D;

public class CubeTest extends TestCase {

	public void testAllRanges() {
		final int dim = CubeFrames.DEFAULT_DIM;

		Random rand = new Random();

		CubeFrames cube = new CubeFrames();

		for (int x = 0; x < dim; x++) {
			for (int y = 0; y < dim; y++) {
				for (int z = 0; z < dim; z++) {
					boolean val = rand.nextBoolean();
					cube.set(null, val);

					boolean getVal = cube.get(Point3D.getInstance(x, y, z));

					String message = String.format("%d %d %d", x, y, z);

					System.out.println(message);

					assertEquals(message, val, getVal);
				}
			}
		}
	}

	public void testSetAndGetRandomOrders() {
		final int dim = CubeFrames.DEFAULT_DIM;
		CubeFrames cube = new CubeFrames();

		Random rand = new Random();

		boolean[][][] testModel = new boolean[dim][dim][dim];
		for (int x = 0; x < dim; x++) {
			for (int y = 0; y < dim; y++) {
				for (int z = 0; z < dim; z++) {
					testModel[z][y][x] = rand.nextBoolean();
				}
			}
		}

		List<Integer> xOrder = Arrays.asList(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7 });
		List<Integer> yOrder = Arrays.asList(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7 });
		List<Integer> zOrder = Arrays.asList(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7 });
		Collections.shuffle(xOrder);
		Collections.shuffle(yOrder);
		Collections.shuffle(zOrder);

		for (Integer x : xOrder) {
			for (Integer y : yOrder) {
				for (Integer z : zOrder) {
					cube.set(null, testModel[z][y][x]);
				}
			}
		}

		Collections.shuffle(xOrder);
		Collections.shuffle(yOrder);
		Collections.shuffle(zOrder);

		for (Integer x : xOrder) {
			for (Integer y : yOrder) {
				for (Integer z : zOrder) {
					boolean getValue = cube.get(Point3D.getInstance(x.intValue(), y.intValue(), z.intValue()));

					String message = String.format("%d %d %d", x, y, z);

					System.out.println(message);

					assertEquals(message, testModel[z][y][x], getValue);
				}
			}
		}

	}
}
