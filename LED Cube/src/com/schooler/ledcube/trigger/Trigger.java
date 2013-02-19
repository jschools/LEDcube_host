package com.schooler.ledcube.trigger;


public abstract class Trigger {

	private TriggerSet triggerSet;
	private int channel;

	public Trigger(TriggerSet triggerSet, int channel) {
		this.triggerSet = triggerSet;
		this.channel = channel;
	}

	public abstract int getNullValue();

	public int getIntValue() {
		return triggerSet.getTriggerValue(channel);
	}

	public float getFloatValue() {
		return Float.intBitsToFloat(getIntValue());
	}

	public boolean getBooleanValue() {
		return getIntValue() != 0;
	}

}
