package com.schooler.ledcube.model;

import com.schooler.ledcube.function.WaveFunction;
import com.schooler.ledcube.manipulator.Evaluator;

public class CubeController {

	/* package */Cube cube;
	/* package */Cube.State state;

	/* package */double playSpeed = 1.0f;
	/* package */boolean paused = true;
	/* package */Evaluator evaluator;
	/* package */int lastFrame = -1;

	public CubeController() {
		cube = new Cube();
		state = cube.getState();
		evaluator = new Evaluator(cube, new WaveFunction());

		new Thread(new CubeUpdater()).start();
	}

	public Cube getCube() {
		return cube;
	}

	public void setPlaySpeed(double playSpeed) {
		System.out.println("Play Speed: " + playSpeed);
		this.playSpeed = playSpeed;
	}

	public double getPlaySpeed() {
		return playSpeed;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public boolean isPaused() {
		return paused;
	}

	public void nextFrame() {
		state.moveToNextFrame();
	}

	public void prevFrame() {
		state.moveToPrevFrame();
	}

	private class CubeUpdater implements Runnable {
		/* package */CubeUpdater() {
			// prevent synthetic access
		}

		@Override
		public void run() {
			while (true) {
				long sleepDuration = (long) ((state.getFrameIntervalMs() / Math.abs(playSpeed)) * 1000000);
				long wakeTime = System.nanoTime() + sleepDuration;
				// System.out.println("sleepDuration: " + sleepDuration);
				while (System.nanoTime() < wakeTime) {
					Thread.yield();
				}
				updateState();
			}
		}

		private void updateState() {
			int frame = state.getFrame();
			if (!paused && playSpeed != 0) {
				if (playSpeed > 0) {
					state.moveToNextFrame();
				} else {
					state.moveToPrevFrame();
				}
			}

			if (frame != lastFrame) {
				// System.out.println("Evaluating at " + state.getFrame());
				evaluator.evaluate();
			}

			lastFrame = frame;
		}
	}

}
