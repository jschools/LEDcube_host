package com.schooler.ledcube.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cube {

	public static final int DEFAULT_DIM = 8;

	private int dim;
	private byte[] data;
	private List<Integer> dirtyList;

	private Cube(int dim) {
		this.dim = dim;
		data = new byte[dim * dim];
		dirtyList = new ArrayList(dim * dim);
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

	private int getIdx(int y, int z) {
		return getIdx(0, y, z);
	}

	public byte[] getRawCube() {
		return data;
	}

	private void set(int y, int z, byte value) {
		int idx = getIdx(y, z);

		byte before = data[idx];
		data[idx] = value;

		if (before != value) {
			setDirty(idx);
		}
	}

	public void set(int x, int y, int z, boolean on) {
		int idx = z * dim + y;

		byte before = data[idx];
		if (on) {
			data[idx] |= 1 << x;
		} else {
			data[idx] &= ~(1 << x);
		}

		if (before != data[idx]) {
			setDirty(idx);
		}
	}

	public boolean get(int x, int y, int z) {
		int idx = z * dim + y;
		return (data[idx] & (1 << x)) > 0;
	}

	public byte getByte(int y, int z) {
		return data[getIdx(y, z)];
	}

	private void setDirty(int idx) {
		dirtyList.add(idx);
	}

	public List<Integer> getDirtyList() {
		return Collections.unmodifiableList(dirtyList);
	}

	private void clearDirtyList() {
		dirtyList.clear();
	}

	public boolean isClean() {
		return dirtyList.isEmpty();
	}

}
