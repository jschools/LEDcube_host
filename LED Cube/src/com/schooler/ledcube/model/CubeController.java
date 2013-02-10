package com.schooler.ledcube.model;

import com.schooler.ledcube.CubeConfig;
import com.schooler.ledcube.graphics.Painter;
import com.schooler.ledcube.output.CubeOutput;

public class CubeController {

	Cube cube;
	Cube.State state;

	double playSpeed = 1.0f;
	boolean paused = true;
	Painter painter;
	int lastFrame = -1;
	CubeOutput cubeOutput;

	public CubeController() {
		cube = new Cube();
		state = cube.getState();

		painter = CubeConfig.ROUTINE.getPainter(cube);

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

		public CubeUpdater() {
			// prevent synthetic warning
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
				painter.paintCube();
				if (paused) {
					System.out.println("frame " + frame);
				}
			}

			lastFrame = frame;

			return true;
		}
	}

}
