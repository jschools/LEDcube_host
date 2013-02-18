package com.schooler.ledcube.routine;

import com.schooler.ledcube.model.CubeFrames;
import com.schooler.ledcube.painter.Painter;

public interface Routine {
	public Painter getPainter(CubeFrames cube);
}
