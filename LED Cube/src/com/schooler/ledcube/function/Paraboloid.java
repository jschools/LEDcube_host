package com.schooler.ledcube.function;

public class Paraboloid extends Function3D implements TimeFunction {
	
	private float t;
	
	@Override
	public void setTime(double time) {
		t = (float) Math.sin(time / 318.47d);
	}
	
	@Override
	public float getY(float x, float z) {
		return x * x * t + z * z * t;
	}
	
}
