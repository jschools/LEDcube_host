package com.schooler.ledcube;

import com.schooler.ledcube.model.CubeFrames;
import com.schooler.ledcube.painter.MarqueeTextPainter;
import com.schooler.ledcube.painter.Painter;
import com.schooler.ledcube.routine.Routine;
import com.schooler.ledcube.text.Strings;



public class CubeConfig {


	// public static Routine ROUTINE = new Routine() {
	// @Override
	// public Painter getPainter(CubeFrames cube) {
	// return new SinglePointPainter(cube, new KeyboardTriggerSet(CubeApplet.getInstance()));
	// }
	// };

	// public static Routine ROUTINE = new SingleCharTextSpinner("Yo momma so fat! ");

	public static Routine ROUTINE = new Routine() {
		@Override
		public Painter getPainter(CubeFrames cube) {
			return new MarqueeTextPainter(cube, Strings.WikipediaCube);
		}

	};

	
	// new Evaluator(cube, new Paraboloid());
	// new Evaluator(cube, new WaveFunction());

}
