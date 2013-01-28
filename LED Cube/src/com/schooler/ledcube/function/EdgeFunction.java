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
		result |= point.i == 0 && point.k == 0;
		result |= point.i == 0 && point.k == edge;
		result |= point.i == edge && point.k == 0;
		result |= point.i == edge && point.k == edge;
		result &= point.j < dim;

		result |= point.j == 0 && point.k == 0;
		result |= point.j == 0 && point.k == edge;
		result |= point.j == edge && point.k == 0;
		result |= point.j == edge && point.k == edge;
		result &= point.i < dim;

		result |= point.i == 0 && point.j == 0;
		result |= point.i == 0 && point.j == edge;
		result |= point.i == edge && point.j == 0;
		result |= point.i == edge && point.j == edge;
		result &= point.k < dim;

		return result;
	}

}
