package com.schooler.ledcube;

import com.schooler.ledcube.model.CubeFrames;
import com.schooler.ledcube.painter.Painter;
import com.schooler.ledcube.painter.SinglePointPainter;
import com.schooler.ledcube.routine.Routine;
import com.schooler.ledcube.trigger.KeyboardTriggerSet;

public class CubeConfig {

	public static Routine ROUTINE = new Routine() {
		@Override
		public Painter getPainter(CubeFrames cube) {
			return new SinglePointPainter(cube, new KeyboardTriggerSet(CubeApplet.getInstance()));
		}
	};

	// public static Routine ROUTINE = new SingleCharTextSpinner("Hi Katharine!");

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
