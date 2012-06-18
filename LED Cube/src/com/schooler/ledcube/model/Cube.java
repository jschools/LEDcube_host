package com.schooler.ledcube.model;

public class Cube {

	public static final int DEFAULT_DIM = 8;
	public static final int DEFAULT_FRAME_COUNT = 64;

	private final int dim;
	private byte[][] frames;
	private State state;

	private Cube(int dim) {
		this.dim = dim;
		state = new State();
		this.frames = new byte[DEFAULT_FRAME_COUNT][dim * dim];
	}

	public Cube() {
		this(DEFAULT_DIM);
	}

	private int getIdx(int x, int y, int z) {
		return z * dim + y;
	}

	public int getDim() {
		return this.dim;
	}

	public State getState() {
		return state;
	}

	private int getIdx(int y, int z) {
		return getIdx(0, y, z);
	}

	public byte[] getRawCube() {
		return getFrameBytes();
	}

	private byte[] getFrameBytes() {
		return frames[state.getFrame()];
	}

	@SuppressWarnings("unused")
	private void set(int y, int z, byte value) {
		int idx = getIdx(y, z);

		byte[] data = getFrameBytes();
		byte before = data[idx];
		data[idx] = value;
	}

	public void set(int x, int y, int z, boolean on) {
		int idx = z * dim + y;

		byte[] data = getFrameBytes();

		byte before = data[idx];
		if (on) {
			data[idx] |= 1 << x;
		} else {
			data[idx] &= ~(1 << x);
		}
	}

	public boolean get(int x, int y, int z) {
		int idx = z * dim + y;
		byte[] data = getFrameBytes();
		return (data[idx] & (1 << x)) > 0;
	}

	public byte getByte(int y, int z) {
		byte[] data = getFrameBytes();
		return data[getIdx(y, z)];
	}

	public class State {
		public static final float DEFAULT_FRAME_INTERVAL_MS = 1000f / 30f;

		private float frameInterval;
		private float time;
		private int frame;

		public State() {
			frameInterval = DEFAULT_FRAME_INTERVAL_MS;
			time = 0.0f;
			frame = 0;
		}

		public float getFrameInterval() {
			return frameInterval;
		}

		public void setFrameInterval(float frameInterval) {
			this.frameInterval = frameInterval;
		}

		public int getFrame() {
			return frame;
		}

		public void setFrame(int frame) {
			this.frame = frame;
		}

		public float getTime() {
			return time;
		}

		public void setTime(float time) {
			this.time = time;
		}

	}

}
