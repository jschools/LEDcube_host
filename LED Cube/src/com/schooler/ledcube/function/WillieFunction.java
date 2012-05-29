package com.schooler.ledcube.function;

public class WillieFunction extends Function3D implements TimeFunction {
	
	private long time;
	private float n;
	
	public WillieFunction() {
		time = System.currentTimeMillis();
	}
	
	@Override
	public void setTime(long millis) {
		n = (float) (millis - time) / 250f;
	}
	
	@Override
	public float getY(float x, float z) {
		return (float) (4 * Math.sin((x + n)));
	}
	
}
