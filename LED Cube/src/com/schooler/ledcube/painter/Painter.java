package com.schooler.ledcube.painter;

import com.schooler.ledcube.model.CubeFrames;
import com.schooler.ledcube.routine.Routine;
import com.schooler.ledcube.trigger.TriggerSet;

public abstract class Painter implements Routine {

	private CubeFrames cube;
	private TriggerSet triggers;

	public Painter(CubeFrames cube) {
		this.cube = cube;
	}

	@Override
	public Painter getPainter(CubeFrames cube) {
		return this;
	}

	public abstract void paint();

	protected CubeFrames getCube() {
		return cube;
	}

	protected TriggerSet getTriggers() {
		return triggers;
	}
	 
}
