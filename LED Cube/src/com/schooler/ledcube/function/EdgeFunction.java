package com.schooler.ledcube.function;

import com.schooler.ledcube.model.Point3D;

public class EdgeFunction implements BooleanFunction, TimeFunction {

	private int dim = 4;

	@Override
	public void setTime(double millis) {
		dim = (int) (Math.sin(millis / 1000) * 8) + 1;
	}

	@Override
	public boolean getValue(Point3D point) {
		int edge = dim - 1;
		
		boolean result = false;
		result |= point.x == 0 && point.z == 0;
		result |= point.x == 0 && point.z == edge;
		result |= point.x == edge && point.z == 0;
		result |= point.x == edge && point.z == edge;
		result &= point.y < dim;

		result |= point.y == 0 && point.z == 0;
		result |= point.y == 0 && point.z == edge;
		result |= point.y == edge && point.z == 0;
		result |= point.y == edge && point.z == edge;
		result &= point.x < dim;

		result |= point.x == 0 && point.y == 0;
		result |= point.x == 0 && point.y == edge;
		result |= point.x == edge && point.y == 0;
		result |= point.x == edge && point.y == edge;
		result &= point.z < dim;

		return result;
	}

}
