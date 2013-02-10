package com.schooler.ledcube.routine;

import com.schooler.ledcube.graphics.Painter;
import com.schooler.ledcube.model.Cube;

public interface Routine {
	public Painter getPainter(Cube cube);
}
