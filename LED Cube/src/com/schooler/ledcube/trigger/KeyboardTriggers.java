package com.schooler.ledcube.trigger;

public class KeyboardTriggers implements ITriggers {

	private static final int NUM_TRIGGERS = 10;

	private float[] triggers;

	public KeyboardTriggers() {
		triggers = new float[NUM_TRIGGERS];
	}

	@Override
	public float getTriggerValue(int which, boolean clear) {
		if (which < 0 || which >= triggers.length) {
			return NULL_VALUE;
		}

		float value = triggers[which];

		if (clear) {
			triggers[which] = NULL_VALUE;
		}

		return value;
	}

	@Override
	public int getNumSupportedTriggers() {
		return NUM_TRIGGERS;
	}

}
