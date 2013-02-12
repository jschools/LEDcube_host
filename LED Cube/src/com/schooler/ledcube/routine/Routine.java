package com.schooler.ledcube.routine;

import com.schooler.ledcube.graphics.Painter;
import com.schooler.ledcube.model.CubeFrames;

public interface Routine {
	public Painter getPainter(CubeFrames cube);
}
