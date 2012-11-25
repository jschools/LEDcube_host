package com.schooler.ledcube.function.manipulator;

import com.schooler.ledcube.function.BooleanFunction;
import com.schooler.ledcube.function.TimeFunction;
import com.schooler.ledcube.model.Point3D;

public abstract class FunctionManipulator implements BooleanFunction, TimeFunction {

	private BooleanFunction function;
	private TimeFunction timeFunction;

	public FunctionManipulator(BooleanFunction function) {
		this.function = function;
		if (function instanceof TimeFunction) {
			this.timeFunction = (TimeFunction) function;
		}
	}

	@Override
	public void setTime(double millis) {
		if (timeFunction != null) {
			timeFunction.setTime(millis);
		}
	}

	protected boolean getInnerValue(Point3D point) {
		return function.getValue(point);
	}

}
