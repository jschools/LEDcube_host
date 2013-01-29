package com.schooler.ledcube.graphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.schooler.ledcube.model.Cube;
import com.schooler.ledcube.model.Point3D;

public class RainGenerator extends Painter {

	private static final int DROP_COUNT = 6;
	protected static final Random rand = new Random();
	
	private List<RainDrop> rainDrops;
	private int currentFrame;
	private double frameIntervalSec;

	public RainGenerator(Cube cube) {
		super(cube);

		currentFrame = 0;
		frameIntervalSec = (float) cube.getState().getFrameIntervalMs() / 1000;

		rainDrops = new ArrayList<RainDrop>(DROP_COUNT);
		for (int i = 0; i < DROP_COUNT; i++) {
			RainDrop drop = new RainDrop();
			drop.reset();
			rainDrops.add(drop);
		}
	}

	@Override
	public void paintCube() {
		Cube cube = getCube();
		Cube.State state = cube.getState();
		int frame = state.getFrame();
		double time = state.getTime();

		updateThroughFrame(frame, frameIntervalSec);

		for (RainDrop drop : rainDrops) {
			drop.draw(cube);
		}
	}

	private void updateThroughFrame(int frame, double timeStep) {
		while (currentFrame <= frame) {
			currentFrame++;
			for (RainDrop drop : rainDrops) {
				drop.fall(timeStep);
				if (drop.isExpired()) {
					drop.reset();
				}
			}
		}
	}
	
	private class RainDrop {
		private static final double STEP_SPACING_M = 0.022225; // meters
		private static final double FALL_HEIGHT_M = STEP_SPACING_M * 7; // meters
		private static final double GRAVITY = -0.015; // m/s/s
		private static final double EPSILON = STEP_SPACING_M / 2;

		private int x;
		private int y;
		private double height; // meters
		private double zVelocity; // m/s

		public RainDrop() {
			this.x = 0;
			this.y = 0;
			this.height = FALL_HEIGHT_M;
			this.zVelocity = 0;
		}

		public void fall(double time) {
			if (!isExpired()) {
				zVelocity += GRAVITY * time;
				height += zVelocity;
			}
		}

		public void draw(Cube cube) {
			if (isExpired()) {
				return;
			}

			Point3D point = Point3D.getInstance();
			point.reset();
			point.x = x;
			point.y = y;

			final int cubeDim = cube.getDim();
			for (int i = 0; i < cubeDim; i++) {
				double step = i * STEP_SPACING_M;
				double low = step - EPSILON;
				double high = step + EPSILON;
				if (height >= low && height <= high) {
					point.z = i;
					cube.set(point, true);
				}
			}

			point.reclaim();
		}

		public boolean isExpired() {
			return height < -FALL_HEIGHT_M;
		}

		public void reset() {
			Cube cube = getCube();
			int cubeDim = cube.getDim();

			x = rand.nextInt(cubeDim);
			y = rand.nextInt(cubeDim);
			height = rand.nextFloat() * (FALL_HEIGHT_M + STEP_SPACING_M);
			zVelocity = 0;
		}
	}

}
