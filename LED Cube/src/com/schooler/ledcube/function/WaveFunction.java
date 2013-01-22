package com.schooler.ledcube.function;

public class WaveFunction extends Function3D implements TimeFunction {
	
	private double t;
	
	@Override
	public void setTime(double time) {
		t = time / 318.47d;
	}
	
	@Override
	public float getZ(float x, float y) {
		return (float) (4 * Math.sin(x + t));
	}
	
}
