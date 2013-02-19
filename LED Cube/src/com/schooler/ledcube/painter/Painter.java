package com.schooler.ledcube.painter;


import com.schooler.ledcube.model.CubeFrames;
import com.schooler.ledcube.routine.Routine;
import com.schooler.ledcube.trigger.TriggerSet;

public abstract class Painter implements Routine {

	private CubeFrames cube;
	private TriggerSet triggers;

	public Painter(CubeFrames cube, TriggerSet triggers) {
		this.cube = cube;
		this.triggers = triggers;
	}

	public Painter(CubeFrames cube) {
		this(cube, null);
	}

	@Override
	public Painter getPainter(CubeFrames cube) {
		return this;
	}

	public abstract void paint();

	protected CubeFrames getCube() {
		return cube;
	}

	public TriggerSet getTriggers() {
		return triggers;
	}

	public void setTriggers(TriggerSet triggers) {
		this.triggers = triggers;
	}
	 
}
