package com.schooler.ledcube.cubecom;

import java.util.List;

import processing.core.PApplet;
import processing.serial.Serial;

import com.schooler.ledcube.CubeDebug;
import com.schooler.ledcube.model.Cube;
import com.schooler.ledcube.output.CubeOutput;

public class SerialCubeCom implements CubeOutput {

	private static final String PORT_NAME = "COM3";
	private static final int BAUD_RATE = 115200;
	private static final char PARITY = 'N';
	private static final int DATA_BITS = 8;
	private static final float STOP_BITS = 1.0f;

	protected Serial serial;
	protected boolean serialInitialized;

	public SerialCubeCom(PApplet pApplet) {
		this.serialInitialized = false;

		new Thread(new InitRunnable(pApplet)).start();
	}

	private class InitRunnable implements Runnable {
		private PApplet pApplet;

		public InitRunnable(PApplet pApplet) {
			this.pApplet = pApplet;
		}

		@Override
		public void run() {
			System.out.printf("Connecting to %s at %d\n", PORT_NAME, Integer.valueOf(BAUD_RATE));
			serial = new Serial(pApplet, PORT_NAME, BAUD_RATE, PARITY, DATA_BITS, STOP_BITS);
			serialInitialized = true;
			System.out.println("Serial initialized");
		}
	}

	private boolean isInitialized() {
		return serialInitialized;
	}

	@Override
	public void writeFrame(Cube cube) {
		if (isInitialized()) {
			CubeDebug.println("sending frame " + cube.getState().getFrame());
			sendBytes(cube.getCurrentFrameBytes());
		}
	}

	@Override
	public void writeAllFrames(Cube cube) {
		if (isInitialized()) {
			final int frameCount = cube.getFrameCount();
			List<byte[]> frames = cube.getFrames();

			for (int i = 0; i < frameCount; i++) {
				byte[] frame = frames.get(i);
				CubeDebug.println("sending frame " + i);
				sendBytes(frame);
			}
		}
	}
	
	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	private void sendBytes(byte[] bytes) {
		if (isInitialized()) {
			serial.write(bytes);
		}
	}

}
