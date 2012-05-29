package com.schooler.ledcube.manipulator;

import com.schooler.ledcube.model.Cube;

public abstract class VoxelManipulator implements Runnable {

	protected Cube cube;

	public VoxelManipulator(Cube cube) {
		this.cube = cube;
	}

	protected void reset(boolean on) {
		final int dim = cube.getDim();
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				for (int k = 0; k < dim; k++) {
					cube.set(i, j, k, on);
				}
			}
		}
	}

	public final void start() {
		new Thread(this).start();
	}

}
