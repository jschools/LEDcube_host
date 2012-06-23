package com.schooler.ledcube.model;

import com.schooler.ledcube.function.WaveFunction;
import com.schooler.ledcube.manipulator.Evaluator;

public class CubeController {

	private Cube cube;

	public CubeController() {
		cube = new Cube();
		Evaluator eval = new Evaluator(cube);
		eval.startEvaluating(new WaveFunction());
	}

	public Cube getCube() {
		return cube;
	}

}
