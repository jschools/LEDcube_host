package com.schooler.ledcube.painter;

import com.schooler.ledcube.geometry.Lines;
import com.schooler.ledcube.model.CubeFrames;
import com.schooler.ledcube.model.Point3D;

public class LineExample1 extends Painter {

	private static final Point3D a = Point3D.getInstance(0, 0, 0);
	private static final Point3D b = Point3D.getInstance(0, 0, 0);

	public LineExample1(CubeFrames cube) {
		super(cube);
	}

	@Override
	public void paint() {
		CubeFrames cube = getCube();

		int frame = cube.getState().getFrame() % 42;
		if (frame <= 7) {
			b.x = 7;
			b.y = 0;
			b.z = 7 - frame;
		}
		else if (frame <= 14) {
			b.x = 7;
			b.y = frame - 7;
			b.z = 0;
		}
		else if (frame <= 21) {
			b.x = 21 - frame;
			b.y = 7;
			b.z = 0;
		}
		else if (frame <= 28) {
			b.x = 0;
			b.y = 7;
			b.z = frame - 21;
		}
		else if (frame <= 35) {
			b.x = frame - 28;
			b.y = 7;
			b.z = 7;
		}
		else if (frame <= 42) {
			b.x = 7;
			b.y = 42 - frame;
			b.z = 7;
		}

		Lines.drawLine(cube, a, b);
	}

}
