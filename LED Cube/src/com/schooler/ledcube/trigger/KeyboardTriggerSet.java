package com.schooler.ledcube.trigger;

import java.awt.event.KeyEvent;

import processing.core.PApplet;

import com.schooler.ledcube.model.Point3D;

public class KeyboardTriggerSet implements TriggerSet {

	private static final int TRIGGER_TRANSLATION = 0;
	private static final int NUM_TRIGGERS = 1;


	private int[] triggers;
	private Point3D temp;

	public KeyboardTriggerSet(PApplet applet) {
		triggers = new int[NUM_TRIGGERS];

		temp = Point3D.getInstance();

		applet.registerKeyEvent(this);
	}

	public void keyEvent(KeyEvent event) {
		if (!isTranslationKey(event.getKeyCode())) {
			return;
		}

		int eventId = event.getID();

		if (eventId == KeyEvent.KEY_RELEASED) {
			temp.reset();

			switch (event.getKeyCode()) {
			case KeyEvent.VK_NUMPAD4:
				temp.x += 1;
				break;
			case KeyEvent.VK_NUMPAD6:
				temp.x -= 1;
				break;
			case KeyEvent.VK_NUMPAD8:
				temp.y -= 1;
				break;
			case KeyEvent.VK_NUMPAD5:
				temp.y += 1;
				break;
			case KeyEvent.VK_NUMPAD0:
				temp.z -= 1;
				break;
			case KeyEvent.VK_NUMPAD1:
				temp.z += 1;
				break;
			default:
				break;
			}

			triggers[TRIGGER_TRANSLATION] = TranslationTrigger.convertVector(temp);
		}
	}

	private static boolean isTranslationKey(int keyCode) {
		return keyCode == KeyEvent.VK_NUMPAD4 || keyCode == KeyEvent.VK_NUMPAD6 || keyCode == KeyEvent.VK_NUMPAD8
				|| keyCode == KeyEvent.VK_NUMPAD5 || keyCode == KeyEvent.VK_NUMPAD0 || keyCode == KeyEvent.VK_NUMPAD1;
	}

	@Override
	public int getTriggerValue(int channel) {
		int value = 0;

		if (channel < 0 || channel >= triggers.length) {
			throw new IllegalArgumentException("No trigger connected to channel " + channel);
		}

		value = triggers[channel];

		triggers[channel] = 0;

		return value;
	}

	@Override
	public int getNumChannels() {
		return NUM_TRIGGERS;
	}

}
