package com.schooler.ledcube.bitmap;

public interface Bitmap2D {

	public void set(int x, int y, boolean value);

	public boolean get(int x, int y);

	public boolean[][] toBooleanArray();

	public byte[] toByteArray();

	public int getWidth();

	public int getHeight();
}
