package com.schooler.ledcube.graphics;

import com.schooler.ledcube.model.CubeFrames;
import com.schooler.ledcube.routine.Routine;

public abstract class Painter implements Routine {

	private CubeFrames cube;

	public Painter(CubeFrames cube) {
		this.cube = cube;
	}

	@Override
	public Painter getPainter(CubeFrames cube) {
		return this;
	}

	public abstract void paintCube();

	protected CubeFrames getCube() {
		return cube;
	}
	 
}
