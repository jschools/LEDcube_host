package com.schooler.ledcube.function.manipulator;

import com.schooler.ledcube.function.BooleanFunction;
import com.schooler.ledcube.model.Point3D;

public abstract class FunctionManipulator implements BooleanFunction {

	private BooleanFunction function;

	public FunctionManipulator(BooleanFunction function) {
		this.function = function;
	}

	protected boolean getInnerValue(Point3D point) {
		return function.getValue(point);
	}

}
