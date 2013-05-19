package com.schooler.ledcube.bitmap;

public class ArrayBitmap2D implements Bitmap2D {

	public static final int DEFAULT_WIDTH = 8;
	public static final int DEFAULT_HEIGHT = 8;

	private boolean[][] data;
	private int width;
	private int height;

	public ArrayBitmap2D(int width, int height) {
		init(width, height);
	}

	public ArrayBitmap2D() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	private void init(int width, int height) {
		this.width = width;
		this.height = height;
		this.data = new boolean[width][height];
	}

	@Override
	public void set(int x, int y, boolean value) {
		ensureSize(x, y);
		data[x][y] = value;
	}

	@Override
	public boolean get(int x, int y) {
		return data[x][y];
	}

	@Override
	public boolean[][] toBooleanArray() {
		return data;
	}

	@Override
	public byte[] toByteArray() {
		return null;
	}
	
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	private void ensureSize(int x, int y) {
		if (!withinBounds(x, y)) {
			int prevWidth = this.width;
			int prevHeight = this.height;
			boolean[][] prevData = this.data;

			init(Math.max(x, prevWidth), Math.max(y, prevHeight));

			// copy old data into new array
			for (int i = 0; i < prevWidth; i++) {
				for (int j = 0; j < prevHeight; j++) {
					set(i, j, prevData[i][j]);
				}
			}
		}
	}

	private boolean withinBounds(int x, int y) {
		return x < width && y < height;
	}

}
