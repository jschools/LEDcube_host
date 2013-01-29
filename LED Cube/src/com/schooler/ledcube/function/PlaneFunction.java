package com.schooler.ledcube.function;

import com.schooler.ledcube.model.Point3D;


public class PlaneFunction implements BooleanFunction {

	public static final int PLANE_X = 0;
	public static final int PLANE_Y = 1;
	public static final int PLANE_Z = 2;

	protected int plane;
	private boolean on;
	private int row;

	public PlaneFunction(int plane, int row, boolean on) {
		this.plane = plane;
		this.row = row;
		this.on = on;
	}

	@Override
	public boolean getValue(Point3D point) {
		switch (plane) {
		case PLANE_X:
			return (point.x == row) == on;
		case PLANE_Y:
			return (point.y == row) == on;
		case PLANE_Z:
		default:
			return (point.z == row) == on;
		}
	}

}
