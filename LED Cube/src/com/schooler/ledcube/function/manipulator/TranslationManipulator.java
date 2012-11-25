package com.schooler.ledcube.function.manipulator;

import com.schooler.ledcube.function.BooleanFunction;
import com.schooler.ledcube.function.TimeFunction;
import com.schooler.ledcube.model.Point3D;

public class TranslationManipulator extends FunctionManipulator implements TimeFunction {

	private int steps = 0;

	public TranslationManipulator(BooleanFunction function) {
		super(function);
	}

	@Override
	public void setTime(double millis) {
		super.setTime(millis);

		steps = (int) (millis / 33d);
	}

	protected int getSteps() {
		return steps;
	}

	@Override
	public boolean getValue(Point3D point) {
		Point3D copy = Point3D.copy(point);

		translatePoint(copy);
		boolean result = getInnerValue(copy);

		Point3D.reclaim(copy);

		return result;
	}

	protected void translatePoint(Point3D point) {
		// default is no-op
	}

}
