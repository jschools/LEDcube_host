package com.schooler.ledcube.model;

public class Cube {

	public static final int DEFAULT_DIM = 8;
	public static final int DEFAULT_FRAME_COUNT = 64;

	private final int dim;
	private byte[][] frames;
	private int frameIdx;

	private Cube(int dim) {
		this.dim = dim;
		this.frameIdx = 0;
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

	public int getFrameIndex() {
		return frameIdx;
	}

	public void setFrameIndex(int frame) {
		this.frameIdx = frame;
	}

	private int getIdx(int y, int z) {
		return getIdx(0, y, z);
	}

	public byte[] getRawCube() {
		return frames[frameIdx];
	}

	private byte[] getFrameBytes() {
		return frames[frameIdx];
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
}
