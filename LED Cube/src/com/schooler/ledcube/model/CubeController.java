package com.schooler.ledcube.model;

import com.schooler.ledcube.function.WaveFunction;
import com.schooler.ledcube.manipulator.Evaluator;

public class CubeController {

	/*package*/ Cube cube;
	/*package*/ Cube.State state;

	/*package*/ float playSpeed = 1.0f;
	/*package*/ boolean paused = true;
	/*package*/ Evaluator evaluator;
	/*package*/ int lastFrame = -1;

	public CubeController() {
		cube = new Cube();
		state = cube.getState();
		evaluator = new Evaluator(cube, new WaveFunction());

		new Thread(new CubeUpdater()).start();
	}

	public Cube getCube() {
		return cube;
	}

	public void setPlaySpeed(float playSpeed) {
		this.playSpeed = playSpeed;
	}

	public void setPaused(boolean paused) {
		System.out.println("setPaused: " + paused);
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
		/*package*/ CubeUpdater() {
			// prevent synthetic access
		}

		@Override
		public void run() {
			while (true) {
				try {
					long sleepTime = (long) (state.getFrameIntervalMs() * playSpeed);
					// System.out.println("sleeping for " + sleepTime);
					Thread.sleep(sleepTime);
					updateState();
				} catch (InterruptedException e) {
					// don't care
				}
			}
		}
		
		private void updateState() {
			synchronized (cube) {
				int frame = state.getFrame();
				if (!paused && playSpeed != 0) {
					if (playSpeed > 0) {
						state.moveToNextFrame();
					} else {
						state.moveToPrevFrame();
					}
				}

				if (frame != lastFrame) {
					System.out.println("Evaluating at " + state.getFrame());
					evaluator.evaluate();
				}

				lastFrame = frame;
			}
		}
	}

}
