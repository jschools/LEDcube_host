package com.schooler.ledcube.model;

import java.util.ArrayList;
import java.util.List;

public class Cube {

	public static final int DEFAULT_DIM = 8;
	public static final int DEFAULT_FRAME_COUNT = 60;

	private final int dim;
	protected List<byte[]> frames;
	private State state;

	private Cube(int dim, int frameCount) {
		this.dim = dim;
		state = new State();
		this.frames = new ArrayList<byte[]>(frameCount);
		for (int i = 0; i < frameCount; i++) {
			frames.add(new byte[dim * dim]);
		}
	}

	public Cube() {
		this(DEFAULT_DIM, DEFAULT_FRAME_COUNT);
	}

	private int getIdx(int x, int y, int z) {
		return z * dim + y;
	}

	public int getDim() {
		return this.dim;
	}

	public int getFrameCount() {
		return frames.size();
	}

	public State getState() {
		return state;
	}

	private int getIdx(int y, int z) {
		return getIdx(0, y, z);
	}

	public List<byte[]> getFrames() {
		return frames;
	}

	public byte[] getCurrentFrameBytes() {
		return getFrameBytes();
	}

	private byte[] getFrameBytes() {
		return frames.get(state.getFrame());
	}

	@SuppressWarnings("unused")
	private void set(int y, int z, byte value) {
		int idx = getIdx(y, z);

		byte[] data = getFrameBytes();
		byte before = data[idx];
		data[idx] = value;
	}

	public void set(Point3D point, boolean on) {
		int idx = point.k * dim + point.j;

		byte[] data = getFrameBytes();

		byte before = data[idx];
		if (on) {
			data[idx] |= 1 << point.i;
		} else {
			data[idx] &= ~(1 << point.i);
		}
	}

	public boolean get(Point3D point) {
		int idx = point.k * dim + point.j;
		byte[] data = getFrameBytes();
		return (data[idx] & (1 << point.i)) > 0;
	}

	public byte getByte(int y, int z) {
		byte[] data = getFrameBytes();
		return data[getIdx(y, z)];
	}

	public class State {
		public static final double DEFAULT_FRAME_INTERVAL_MS = 1000d / 30d;

		private boolean loopingEnabled;
		private double frameIntervalMs;
		private double time;
		private int frame;

		public State() {
			frameIntervalMs = DEFAULT_FRAME_INTERVAL_MS;
			time = 0.0d;
			frame = 0;
			loopingEnabled = true;
		}

		public synchronized void moveToNextFrame() {
			moveToFrame(frame + 1);
		}

		public synchronized void moveToPrevFrame() {
			moveToFrame(frame - 1);
		}

		public synchronized void moveToFrame(int destFrame) {
			int newFrame = getConstrainedFrame(destFrame);

			int frameDelta = newFrame - frame;
			frame += frameDelta;
			time += frameDelta * frameIntervalMs;
		}

		public int getConstrainedFrame(int destFrame) {
			final int frameCount = getFrameCount();

			if (isLoopingEnabled()) {
				int newFrame = destFrame;

				if (newFrame >= frameCount || frame < 0) {
					newFrame %= frameCount;
				}

				if (newFrame < 0) {
					newFrame += frameCount;
				}

				return newFrame;
			}

			return Math.max(Math.min(frameCount - 1, destFrame), 0);
		}

		public boolean isLoopingEnabled() {
			return loopingEnabled;
		}

		public void setLoopingEnabled(boolean loopingEnabled) {
			this.loopingEnabled = loopingEnabled;
		}

		public double getFrameIntervalMs() {
			return frameIntervalMs;
		}

		public void setFrameInterval(double frameInterval) {
			this.frameIntervalMs = frameInterval;
		}

		public int getFrame() {
			return frame;
		}

		public double getTime() {
			return time;
		}
	}
}
