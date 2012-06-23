package com.schooler.ledcube.function;

public class Paraboloid extends Function3D implements TimeFunction {
	
	private float n;
	
	@Override
	public void setTime(double time) {
		n = (float) Math.cos(time / 3d);
	}
	
	@Override
	public float getY(float x, float z) {
		return x * x * n + z * z * n;
	}
	
}
