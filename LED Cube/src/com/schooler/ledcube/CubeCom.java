package com.schooler.ledcube;

import processing.core.PApplet;
import processing.serial.Serial;

public class CubeCom {

	private static final String PORT_NAME = "COM3";
	private static final int BAUD_RATE = 115200;
	private static final char PARITY = 'N';
	private static final int DATA_BITS = 8;
	private static final float STOP_BITS = 1.0f;

	private Serial serial;

	public CubeCom(PApplet pApplet) {
		serial = new Serial(pApplet, PORT_NAME, BAUD_RATE, PARITY, DATA_BITS, STOP_BITS);
	}

}
