package com.schooler.ledcube.function;

import com.schooler.ledcube.CubeApplet;
import com.schooler.ledcube.model.Point3D;


public abstract class Function3D implements BooleanFunction {
	
	private static final float OFFSET = (CubeApplet.DIM - 1) / 2f;
	private static final float EPSILON = 0.5f;
	
	public abstract float getZ(float x, float z);
	
	@Override
	public boolean getValue(Point3D point) {
		float value = getZ((point.x - OFFSET) / 2f, (point.y - OFFSET) / 2f);
		return (point.z - OFFSET) <= value + EPSILON && (point.z - OFFSET) >= value - EPSILON;
	}
	
}
