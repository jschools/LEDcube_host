package com.schooler.ledcube.manipulator;

import com.schooler.ledcube.model.Cube;
import com.schooler.ledcube.model.Point3D;

public class PlaneShifter extends VoxelManipulator implements Runnable {

	public static final int PLANE_X = 0;
	public static final int PLANE_Y = 1;
	public static final int PLANE_Z = 2;

	private boolean[][] tempPlane;

	private boolean running = true;

	public PlaneShifter(Cube cube) {
		super(cube);

		int dim = cube.getDim();

		tempPlane = new boolean[dim][dim];
	}

	public void stop() {
		running = false;
	}

	@Override
	public void run() {
		while (running) {
			shift();

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void shift() {
		final int dim = cube.getDim();
		for (int row = -1; row < dim; row++) {
			for (int u = 0; u < dim; u++) {
				for (int v = 0; v < dim; v++) {
					if (row == -1) {
						Point3D point = Point3D.newInstance(0, u, v);
						tempPlane[u][v] = cube.get(point);
						point.reclaim();
					} else if (row == dim - 1) {
						cube.set(null, tempPlane[u][v]);
					} else {
						Point3D point = Point3D.newInstance(row + 1, u, v);
						cube.set(null, cube.get(point));
						point.reclaim();
					}
				}
			}
		}

	}

}
