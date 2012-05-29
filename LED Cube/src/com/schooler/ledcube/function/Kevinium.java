package com.schooler.ledcube.function;

public class Kevinium extends Function3D implements TimeFunction {

	private long time;
	private float n;
	
	public Kevinium() {
		time = System.currentTimeMillis();
	}
	
	@Override
	public void setTime(long millis) {
		n = (float) Math.cos((millis - time) / 1000d);
	}
	
	@Override
	public float getY(float x, float z) {
		return (float) Math.sin(x * z) *n * 8;
	}
	
}
