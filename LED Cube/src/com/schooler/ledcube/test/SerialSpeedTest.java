package com.schooler.ledcube.test;

import processing.core.PApplet;
import processing.serial.Serial;

public class SerialSpeedTest extends PApplet {

	private static final String PORT_NAME = "COM3";
	private static final int BAUD_RATE = 115200;
	private static final char PARITY = 'N';
	private static final int DATA_BITS = 8;
	private static final float STOP_BITS = 1.0f;

	private Serial serial;
	private byte value;
	private boolean started = false;

	@Override
	public void setup() {
		serial = new Serial(this, PORT_NAME, BAUD_RATE, PARITY, DATA_BITS, STOP_BITS);
		value = 0x00;
	}

	@Override
	public void draw() {
		if (!started) {
			started = true;
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						serial.write(value);
						value++;
					}
				}
			}).start();
		}
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "com.schooler.ledcube.test.SerialSpeedTest" });
	}
}
