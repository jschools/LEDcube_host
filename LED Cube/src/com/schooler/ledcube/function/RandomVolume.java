package com.schooler.ledcube.function;

public class RandomVolume implements BooleanFunction {

	@Override
	public boolean getValue(int i, int j, int k) {
		return Math.random() > 0.5f;
	}

}
