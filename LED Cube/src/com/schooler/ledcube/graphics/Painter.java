package com.schooler.ledcube.graphics;

import com.schooler.ledcube.model.Cube;
import com.schooler.ledcube.routine.Routine;

public abstract class Painter implements Routine {

	private Cube cube;

	public Painter(Cube cube) {
		this.cube = cube;
	}

	@Override
	public Painter getPainter(Cube cube) {
		return this;
	}

	public abstract void paintCube();

	protected Cube getCube() {
		return cube;
	}
	 
}
