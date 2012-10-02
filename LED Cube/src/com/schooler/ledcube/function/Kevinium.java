package com.schooler.ledcube.function;

public class Kevinium extends Function3D implements TimeFunction {

	private float t;
	
	@Override
	public void setTime(double time) {
		t = (float) (time / 318.47d);
	}
	
	@Override
	public float getY(float x, float z) {
		return (float) Math.sin(x * z) * t;
	}
	
}
