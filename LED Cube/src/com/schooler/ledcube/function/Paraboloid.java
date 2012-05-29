package com.schooler.ledcube.function;

public class Paraboloid extends Function3D implements TimeFunction {
	
	private long time;
	private float n;
	
	public Paraboloid() {
		time = System.currentTimeMillis();
	}
	
	@Override
	public void setTime(long millis) {
		n = (float) Math.cos((millis - time) / 3000d);
	}
	
	@Override
	public float getY(float x, float z) {
		return x * x * n + z * z * n;
	}
	
}
