package com.schooler.ledcube.function;

import com.schooler.ledcube.model.Point3D;

public class StrobeFunction implements BooleanFunction, TimeFunction {

	private boolean on = true;

	@Override
	public void setTime(double millis) {
		int frame = (int) (millis / 33.33333333d);
		on = frame % 2 == 0;
	}

	@Override
	public boolean getValue(Point3D point) {
		return on;
	}

}
