package com.schooler.ledcube.function;

public class Kevinium extends Function3D implements TimeFunction {

	private float n;
	
	@Override
	public void setTime(double time) {
		n = (float) Math.cos(time);
	}
	
	@Override
	public float getY(float x, float z) {
		return (float) Math.sin(x * z) * n * 8;
	}
	
}
