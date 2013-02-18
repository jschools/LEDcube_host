package com.schooler.ledcube.trigger;

public class KeyboardTriggerSet implements TriggerSet {

	private static final int NUM_TRIGGERS = 10;

	private int[] triggers;

	public KeyboardTriggerSet() {
		triggers = new int[NUM_TRIGGERS];
	}


	@Override
	public int getTriggerValue(int channel) {
		if (channel < 0 || channel >= triggers.length) {
			throw new IllegalArgumentException("No trigger at that channel");
		}

		int value = triggers[channel];

		return value;
	}

	@Override
	public int getNumChannels() {
		return NUM_TRIGGERS;
	}

}
