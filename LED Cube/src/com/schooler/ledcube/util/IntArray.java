package com.schooler.ledcube.util;

public class IntArray {

	private static final int DEFAULT_SIZE = 16;
	public static final int NULL = Integer.MIN_VALUE;

	private int[] array;
	private boolean[] notNull;

	public IntArray(int size) {
		this.array = new int[size];
		this.notNull = new boolean[size];
	}

	public IntArray() {
		this(DEFAULT_SIZE);
	}

	public synchronized void set(int idx, int value) {
		rangeCheck(idx);
		array[idx] = value;
		notNull[idx] = true;
	}

	public synchronized int get(int idx) {
		if (idx >= 0 && idx < array.length && notNull[idx]) {
			return array[idx];
		}
		
		return NULL;
	}

	private void rangeCheck(int idx) {
		if (array.length <= idx) {
			int[] arrayCopy = new int[idx + 1];
			boolean[] notNullCopy = new boolean[idx + 1];
			System.arraycopy(array, 0, arrayCopy, 0, array.length);
			System.arraycopy(notNull, 0, notNullCopy, 0, notNull.length);

			array = arrayCopy;
			notNull = notNullCopy;
		}
	}

}
