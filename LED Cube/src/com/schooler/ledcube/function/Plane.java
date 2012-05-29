package com.schooler.ledcube.function;


public class Plane implements BooleanFunction {

	public static final int PLANE_X = 0;
	public static final int PLANE_Y = 1;
	public static final int PLANE_Z = 2;

	private int plane;
	private boolean on;
	private int row;

	public Plane(int plane, int row, boolean on) {
		this.plane = plane;
		this.row = row;
		this.on = on;
	}

	@Override
	public boolean getValue(int i, int j, int k) {
		switch (plane) {
		case PLANE_X:
			return (i == row) == on;
		case PLANE_Y:
			return (j == row) == on;
		case PLANE_Z:
		default:
			return (k == row) == on;
		}
	}

}
