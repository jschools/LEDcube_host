package com.schooler.ledcube.trigger;

import com.schooler.ledcube.model.Point3D;

public class TranslationTrigger extends Trigger {

	public static final int NONE  = 0x00;
	public static final int POS_X = 0x01;
	public static final int NEG_X = 0x02;
	public static final int POS_Y = 0x04;
	public static final int NEG_Y = 0x08;
	public static final int POS_Z = 0x10;
	public static final int NEG_Z = 0x20;

	public TranslationTrigger(TriggerSet triggerSet, int channel) {
		super(triggerSet, channel);
	}

	public void getVector(Point3D point) {
		int value = getIntValue();

		point.reset();

		if ((value & POS_X) > 0) {
			point.x += 1;
		}
		if ((value & NEG_X) > 0) {
			point.x -= 1;
		}
		if ((value & POS_Y) > 0) {
			point.y += 1;
		}
		if ((value & NEG_Y) > 0) {
			point.y -= 1;
		}
		if ((value & POS_Z) > 0) {
			point.z += 1;
		}
		if ((value & NEG_Z) > 0) {
			point.z -= 1;
		}
	}

}
