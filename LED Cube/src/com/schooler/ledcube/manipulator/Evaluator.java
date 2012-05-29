package com.schooler.ledcube.manipulator;

import com.schooler.ledcube.function.BooleanFunction;
import com.schooler.ledcube.function.TimeFunction;
import com.schooler.ledcube.model.Cube;

public class Evaluator implements Runnable {

	private Cube cube;
	BooleanFunction function;

	public Evaluator(Cube cube) {
		this.cube = cube;
	}

	public void evaluate(BooleanFunction function) {
		this.function = function;
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (true) {
			synchronized (cube) {
				fill(function);
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void fill(BooleanFunction function) {
		if (function instanceof TimeFunction) {
			((TimeFunction) function).setTime(System.currentTimeMillis());
		}

		int dim = cube.getDim();

		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				for (int k = 0; k < dim; k++) {
					cube.set(i, j, k, function.getValue(i, j, k));
				}
			}
		}
	}

}
