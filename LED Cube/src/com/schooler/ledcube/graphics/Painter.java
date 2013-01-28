package com.schooler.ledcube.graphics;

import com.schooler.ledcube.model.Cube;

public abstract class Painter {

	private Cube cube;

	public Painter(Cube cube) {
		this.cube = cube;
	}

	public abstract void paintCube();

	protected Cube getCube() {
		return cube;
	}
	 
}
