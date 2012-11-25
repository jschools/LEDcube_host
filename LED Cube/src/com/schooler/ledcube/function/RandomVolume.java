package com.schooler.ledcube.function;

import com.schooler.ledcube.model.Point3D;

public class RandomVolume implements BooleanFunction {

	@Override
	public boolean getValue(Point3D point) {
		return Math.random() > 0.5f;
	}

}
