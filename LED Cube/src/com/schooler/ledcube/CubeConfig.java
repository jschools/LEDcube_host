package com.schooler.ledcube;

import com.schooler.ledcube.model.CubeFrames;
import com.schooler.ledcube.painter.Painter;
import com.schooler.ledcube.painter.Rain;
import com.schooler.ledcube.routine.Routine;



public class CubeConfig {

	// public static Routine ROUTINE = new Routine() {
	// @Override
	// public Painter getPainter(CubeFrames cube) {
	// return new SinglePointPainter(cube, new KeyboardTriggerSet(CubeApplet.getInstance()));
	// }
	// };

	// public static Routine ROUTINE = new SingleCharTextSpinner("Happy Birthday Mary! ");


	public static Routine ROUTINE = new Routine() {
		@Override
		public Painter getPainter(CubeFrames cube) {
			return new Rain(cube);
		}

	};

	
	// new Evaluator(cube, new Paraboloid());
	// new Evaluator(cube, new WaveFunction());

}
