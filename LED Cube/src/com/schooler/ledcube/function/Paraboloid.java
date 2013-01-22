package com.schooler.ledcube.function;

public class Paraboloid extends Function3D implements TimeFunction {
	
	private float t;
	
	@Override
	public void setTime(double time) {
		t = (float) Math.sin(time / 318.47d);
	}
	
	@Override
	public float getZ(float x, float y) {
		return x * x * t + y * y * t;
	}
	
}
