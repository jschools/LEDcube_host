package com.schooler.ledcube;

import com.schooler.ledcube.function.EdgeFunction;
import com.schooler.ledcube.function.Evaluator;
import com.schooler.ledcube.graphics.Painter;
import com.schooler.ledcube.model.Cube;
import com.schooler.ledcube.routine.Routine;

public class CubeConfig {

	// public static Routine ROUTINE = new SingleCharTextSpinner(Strings.WikipediaCube);

	public static Routine ROUTINE = new Routine() {
		@Override
		public Painter getPainter(Cube cube) {
			return new Evaluator(cube, new EdgeFunction());
		}
		
	};
	// new RainRoutine(cube);
	// new Evaluator(cube, new Paraboloid());
	// new Evaluator(cube, new WaveFunction());

}
