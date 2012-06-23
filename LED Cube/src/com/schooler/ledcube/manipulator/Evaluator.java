package com.schooler.ledcube.manipulator;

import java.util.Arrays;

import com.schooler.ledcube.function.BooleanFunction;
import com.schooler.ledcube.function.TimeFunction;
import com.schooler.ledcube.model.Cube;

public class Evaluator {

	private Cube cube;
	private BooleanFunction function;
	private boolean[] frameCached;

	public Evaluator(Cube cube, BooleanFunction function) {
		this.cube = cube;
		this.function = function;

		frameCached = new boolean[cube.getFrameCount()];
		Arrays.fill(frameCached, false);
	}

	public void evaluate() {
		if (function instanceof TimeFunction) {
			// System.out.println("Time: " + cube.getState().getTime());
			((TimeFunction) function).setTime(cube.getState().getTime());
		}

		int frame = cube.getState().getFrame();
		if (!frameCached[frame]) {
			final int dim = cube.getDim();
			for (int i = 0; i < dim; i++) {
				for (int j = 0; j < dim; j++) {
					for (int k = 0; k < dim; k++) {
						cube.set(i, j, k, function.getValue(i, j, k));
					}
				}
			}

			frameCached[cube.getState().getFrame()] = true;
		}
	}

}
