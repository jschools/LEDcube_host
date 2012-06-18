package com.schooler.ledcube.function;

import com.schooler.ledcube.CubeApplet;


public abstract class Function3D implements BooleanFunction {
	
	private static final float OFFSET = (CubeApplet.DIM - 1) / 2f;
	private static final float EPSILON = 0.5f;
	
	public abstract float getY(float x, float z);
	
	@Override
	public boolean getValue(int i, int j, int k) {
		float value = getY((i - OFFSET) / 2f, (k - OFFSET) / 2f);
		return  (j - OFFSET) <= value + EPSILON && (j - OFFSET) >= value - EPSILON;
	}
	
}
