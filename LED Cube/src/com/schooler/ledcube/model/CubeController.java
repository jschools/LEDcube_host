package com.schooler.ledcube.model;

import com.schooler.ledcube.function.WaveFunction;
import com.schooler.ledcube.manipulator.Evaluator;

public class CubeController {

	public static final CubeController instance = new CubeController();

	private Cube cube;

	private CubeController() {
		cube = new Cube();
		Evaluator eval = new Evaluator(cube);
		eval.startEvaluating(new WaveFunction());
	}

	public Cube getCube() {
		return cube;
	}

}
