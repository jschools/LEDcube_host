package com.schooler.ledcube.model;

import com.schooler.ledcube.function.FrameFunction;
import com.schooler.ledcube.manipulator.Evaluator;
import com.schooler.ledcube.output.CubeOutput;

public class CubeController {

	Cube cube;
	Cube.State state;

	double playSpeed = 1.0f;
	boolean paused = true;
	Evaluator evaluator;
	int lastFrame = -1;
	CubeOutput cubeOutput;

	public CubeController() {
		cube = new Cube();
		state = cube.getState();

		// evaluator = new Evaluator(cube, new TextPlaneFunction(PlaneFunction.PLANE_X, 7, "Schooler"));
		evaluator = new Evaluator(cube, new FrameFunction());

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

	public void outputFrame() {
		if (cubeOutput == null) {
			return;
		}

		cubeOutput.writeFrame(cube);
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

				boolean changed = updateState();
				if (changed) {
					outputFrame();
				}
			}
		}

		private boolean updateState() {
			boolean changed = false;

			int frame = state.getFrame();
			if (!paused && playSpeed != 0) {
				if (playSpeed > 0) {
					state.moveToNextFrame();
				} else {
					state.moveToPrevFrame();
				}
				changed = true;
			}

			if (frame != lastFrame) {
				evaluator.evaluate();
				if (paused) {
					System.out.println("frame " + frame);
				}
			}

			lastFrame = frame;

			return true;
		}
	}

}
