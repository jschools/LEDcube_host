package com.schooler.ledcube.model;

import com.schooler.ledcube.function.Plane;
import com.schooler.ledcube.function.TextPlaneFunction;
import com.schooler.ledcube.manipulator.Evaluator;
import com.schooler.ledcube.output.CubeOutput;

public class CubeController {

	/* package */Cube cube;
	/* package */Cube.State state;

	/* package */double playSpeed = 1.0f;
	/* package */boolean paused = true;
	/* package */Evaluator evaluator;
	/* package */int lastFrame = -1;
	/* package */CubeOutput cubeOutput;

	public CubeController() {
		cube = new Cube();
		state = cube.getState();
		evaluator = new Evaluator(cube, new TextPlaneFunction(Plane.PLANE_X, 0, "Welcome to überMIND  "));

		new Thread(new CubeUpdater()).start();
	}

	public Cube getCube() {
		return cube;
	}

	public CubeOutput getCubeOutput() {
		return cubeOutput;
	}

	public void setCubeOutput(CubeOutput cubeOutput) {
		this.cubeOutput = cubeOutput;
	}

	public void setPlaySpeed(double playSpeed) {
		System.out.println("Speed: " + playSpeed);
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

	public void outputEntireCube() {
		cubeOutput.writeAllFrames(cube);
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
				evaluator.evaluate();
				if (paused) {
					System.out.println("frame " + frame);
				}
			}

			lastFrame = frame;
		}
	}

}
