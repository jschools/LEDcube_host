package com.schooler.ledcube.function;

public class WaveFunction extends Function3D implements TimeFunction {
	
	private double n;
	
	@Override
	public void setTime(double time) {
		n = time / 318.47d;
	}
	
	@Override
	public float getY(float x, float z) {
		return (float) (4 * Math.sin(x + n));
	}
	
}
