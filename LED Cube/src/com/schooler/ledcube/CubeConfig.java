package com.schooler.ledcube;

import com.schooler.ledcube.routine.Routine;
import com.schooler.ledcube.routine.SingleCharTextSpinner;

public class CubeConfig {

	public static Routine ROUTINE = new SingleCharTextSpinner("Hi Katharine!");

	// public static Routine ROUTINE = new Routine() {
	// @Override
	// public Painter getPainter(Cube cube) {
	// return new Evaluator(cube, new EdgeFunction());
	// }
	//
	// };
	// new RainRoutine(cube);
	// new Evaluator(cube, new Paraboloid());
	// new Evaluator(cube, new WaveFunction());

}
