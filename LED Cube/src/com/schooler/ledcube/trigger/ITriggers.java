package com.schooler.ledcube.trigger;

public interface ITriggers {

	public static final float NULL_VALUE = 0.0f;

	public float getTriggerValue(int which, boolean clear);

	public int getNumSupportedTriggers();

}
