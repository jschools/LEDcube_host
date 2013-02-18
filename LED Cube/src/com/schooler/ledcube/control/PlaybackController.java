package com.schooler.ledcube.control;

import com.schooler.ledcube.CubeConfig;
import com.schooler.ledcube.model.CubeFrames;
import com.schooler.ledcube.output.CubeOutput;
import com.schooler.ledcube.painter.Painter;

public class PlaybackController {

	protected CubeFrames cube;
	protected CubeFrames.State state;

	protected double playSpeed = 1.0f;
	protected boolean paused = true;
	protected Painter painter;
	protected int lastFrame = Integer.MIN_VALUE;
	protected CubeOutput cubeOutput;

	public PlaybackController() {
		cube = new CubeFrames();
		state = cube.getState();

		painter = CubeConfig.ROUTINE.getPainter(cube);

		new Thread(new Updater()).start();
	}

	public CubeFrames getCube() {
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

	private class Updater implements Runnable {

		public Updater() {
			// prevent synthetic warning
		}

		@Override
		public void run() {
			while (true) {
				long sleepDuration = (long) ((state.getFrameIntervalMs() / Math.abs(playSpeed)) * 1000000);
				long wakeTime = System.nanoTime() + sleepDuration;

				// wait until it's time for the next update
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

			if (!paused && playSpeed != 0) {
				if (playSpeed > 0) {
					state.moveToNextFrame();
				}
				else {
					state.moveToPrevFrame();
				}
				changed = true;
			}

			int frame = state.getFrame();

			if (frame != lastFrame) {
				painter.paint();
				if (paused) {
					System.out.println("frame " + frame);
				}

				changed = true;
			}

			lastFrame = frame;

			return changed;
		}
	}

}
