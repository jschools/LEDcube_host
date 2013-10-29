package com.schooler.ledcube;

import com.schooler.ledcube.model.CubeFrames;
import com.schooler.ledcube.painter.MarqueeBitmapPainter;
import com.schooler.ledcube.painter.Painter;
import com.schooler.ledcube.routine.Routine;



public class CubeConfig {

	public static Routine ROUTINE = new Routine() {
		@Override
		public Painter getPainter(CubeFrames cube) {
			return new MarqueeBitmapPainter(cube, "LED Cube!");
		}

	};

}
