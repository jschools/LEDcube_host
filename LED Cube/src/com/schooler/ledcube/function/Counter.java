package com.schooler.ledcube.function;

import com.schooler.ledcube.manipulator.VoxelManipulator;
import com.schooler.ledcube.model.Cube;

public class Counter extends VoxelManipulator implements Runnable {

	private static final int DELAY_INTERVAL_MS = 20;

	private boolean running = true;

	private int position = 0;

	public Counter(Cube cube) {
		super(cube);
	}

	public void stop() {
		running = false;
	}

	@Override
	public void run() {
		while (running) {
			set(position, false);

			position++;
			if (position == 512) {
				reset(false);
				position = 0;
			}

			set(position, true);

			try {
				Thread.sleep(DELAY_INTERVAL_MS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void set(int position, boolean on) {
		cube.set(getX(position), getY(position), getZ(position), on);
	}

	private int getX(int position) {
		return position % cube.getDim();
	}

	private int getY(int position) {
		return getX(position / cube.getDim());
	}

	private int getZ(int position) {
		return getY(position / cube.getDim());
	}

}
