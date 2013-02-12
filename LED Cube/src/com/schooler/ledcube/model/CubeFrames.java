package com.schooler.ledcube.model;

import java.util.LinkedList;
import java.util.List;

public class CubeFrames {

	public static final int DEFAULT_DIM = 8;
	public static final int DEFAULT_FRAME_COUNT = 60;

	protected List<byte[]> frames;
	private final int dim;
	private final int frameLength;

	private State state;

	public CubeFrames() {
		this(DEFAULT_DIM, DEFAULT_FRAME_COUNT);
	}

	private CubeFrames(int dim, int initialFrameCount) {
		this.dim = dim;
		this.frameLength = dim * dim;

		this.state = new State();

		this.frames = new LinkedList<byte[]>();

		appendEmptyFrames(initialFrameCount);
	}

	public boolean get(Point3D point) {
		int idx = point.z * dim + point.y;
		byte[] data = getFrameBytes();
		return (data[idx] & (1 << point.x)) > 0;
	}

	public byte getByte(int y, int z) {
		byte[] data = getFrameBytes();
		return data[getIdx(y, z)];
	}

	public byte[] getCurrentFrameBytes() {
		return getFrameBytes();
	}

	public int getDim() {
		return this.dim;
	}

	public int getFrameCount() {
		return frames.size();
	}

	public List<byte[]> getFrames() {
		return frames;
	}

	public State getState() {
		return state;
	}

	public void set(Point3D point, boolean on) {
		int idx = point.z * dim + point.y;

		byte[] data = getFrameBytes();

		if (on) {
			data[idx] |= 1 << point.x;
		}
		else {
			data[idx] &= ~(1 << point.x);
		}
	}

	protected void ensureFrameExists(int frameIdx) {
		if (frameIdx < 0) {
			return;
		}

		while (frameIdx >= getFrameCount()) {
			appendEmptyFrames(DEFAULT_FRAME_COUNT);
		}
	}

	private void appendEmptyFrames(final int count) {
		for (int i = 0; i < count; i++) {
			frames.add(new byte[frameLength]);
		}
	}

	private byte[] getFrameBytes() {
		return frames.get(state.getFrame());
	}

	private int getIdx(int y, int z) {
		return getIdx(0, y, z);
	}

	private int getIdx(int x, int y, int z) {
		return z * dim + y;
	}

	public class State {
		public static final double DEFAULT_FRAME_INTERVAL_MS = 1000d / 30d;

		private int frame;
		private double frameIntervalMs;
		private boolean loopingEnabled;
		private double time;

		public State() {
			frameIntervalMs = DEFAULT_FRAME_INTERVAL_MS;
			time = 0.0d;
			frame = 0;
			loopingEnabled = false;
		}

		public int getFrame() {
			return frame;
		}

		public double getFrameIntervalMs() {
			return frameIntervalMs;
		}

		public double getTime() {
			return time;
		}

		public boolean isLoopingEnabled() {
			return loopingEnabled;
		}

		public synchronized void moveToFrame(int destFrame) {
			int newFrame = getInBoundsFrame(destFrame);

			frame = newFrame;
			time = newFrame * frameIntervalMs;
		}

		public synchronized void moveToNextFrame() {
			moveToFrame(frame + 1);
		}

		public synchronized void moveToPrevFrame() {
			moveToFrame(frame - 1);
		}

		public void setFrameInterval(double frameInterval) {
			this.frameIntervalMs = frameInterval;
		}

		public void setLoopingEnabled(boolean loopingEnabled) {
			this.loopingEnabled = loopingEnabled;
		}

		private int getInBoundsFrame(int destFrame) {
			// if looping, compute the next frame index
			if (isLoopingEnabled()) {
				final int frameCount = getFrameCount();

				int newFrame = destFrame;

				if (newFrame >= frameCount || frame < 0) {
					newFrame %= frameCount;
				}

				if (newFrame < 0) {
					newFrame += frameCount;
				}

				return newFrame;
			}

			// if not looping, add more frames if needed
			ensureFrameExists(destFrame);

			return Math.max(destFrame, 0);
		}
	}
}
