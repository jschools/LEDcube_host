package com.schooler.ledcube.function.manipulator;

import com.schooler.ledcube.function.BooleanFunction;
import com.schooler.ledcube.model.Point3D;

public class MarqueeManipulator extends TranslationManipulator {

	public MarqueeManipulator(BooleanFunction function) {
		super(function);
	}

	@Override
	protected void translatePoint(Point3D point) {
		// TODO Auto-generated method stub
		super.translatePoint(point);
	}

}
