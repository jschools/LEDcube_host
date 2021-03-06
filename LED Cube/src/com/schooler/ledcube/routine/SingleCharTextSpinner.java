package com.schooler.ledcube.routine;


import com.schooler.ledcube.function.Evaluator;
import com.schooler.ledcube.function.PlaneFunction;
import com.schooler.ledcube.function.TextPlaneFunction;
import com.schooler.ledcube.function.manipulator.MarqueeManipulator;
import com.schooler.ledcube.model.CubeFrames;
import com.schooler.ledcube.painter.Painter;

public class SingleCharTextSpinner implements Routine {

	private final String string;

	public SingleCharTextSpinner(String string) {
		this.string = string;
	}

	@Override
	public Painter getPainter(CubeFrames cube) {
		return new Evaluator(cube, new MarqueeManipulator(new TextPlaneFunction(PlaneFunction.PLANE_X, 7, this.string,
				MarqueeManipulator.FRAMES_PER_CYCLE)));
	}

}
