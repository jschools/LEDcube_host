package com.schooler.ledcube.model;

public class Vector3D {

	public float x;
	public float y;
	public float z;

	public Vector3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3D() {
		this(0f, 0f, 0f);
	}

	public void normalize() {
		float magnitude = x * x + y * y + z * z;

		x /= magnitude;
		y /= magnitude;
		z /= magnitude;
	}

}
