package com.schooler.ledcube.painter;

import java.util.Random;

import com.schooler.ledcube.geometry.Lines;
import com.schooler.ledcube.model.CubeFrames;
import com.schooler.ledcube.model.Point3D;

public class WanderingLine extends Painter {

	private static final Random rand = new Random();
	private static final int FRAMES_PER_JUMP = 30;

	private Point3D a = Point3D.getInstance();
	private Point3D b = Point3D.getInstance();
	private Point3D aStart = Point3D.getInstance();
	private Point3D bStart = Point3D.getInstance();
	private Point3D aDest = Point3D.getInstance();
	private Point3D bDest = Point3D.getInstance();

	private int lastFrame = -1;

	public WanderingLine(CubeFrames cube) {
		super(cube);
	}

	@Override
	public void paint() {
		CubeFrames cube = getCube();
		int frame = cube.getState().getFrame();
		if (frame != lastFrame && frame % FRAMES_PER_JUMP == 0) {
			randomize();
		}
		lastFrame = frame;

		int step = frame % FRAMES_PER_JUMP;
		float fraction = (float) step / FRAMES_PER_JUMP;
		blend(a, aStart, aDest, fraction);
		blend(b, bStart, bDest, fraction);

		Lines.drawLine(cube, a, b);
	}

	private static void blend(Point3D point, Point3D start, Point3D end, float fraction) {
		float oneMinus = 1 - fraction;
		point.x = (int) (start.x * oneMinus + end.x * fraction);
		point.y = (int) (start.y * oneMinus + end.y * fraction);
		point.z = (int) (start.z * oneMinus + end.z * fraction);
	}

	private void randomize() {
		aStart.x = a.x;
		aStart.y = a.y;
		aStart.z = a.z;

		bStart.x = b.x;
		bStart.y = b.y;
		bStart.z = b.z;

		aDest.x = randPosition();
		aDest.y = randPosition();
		aDest.z = randPosition();

		bDest.x = randPosition();
		bDest.y = randPosition();
		bDest.z = randPosition();
	}

	private static int randPosition() {
		if (rand.nextBoolean()) {
			return rand.nextInt(8);
		}

		int value = rand.nextInt(2);
		if (rand.nextBoolean()) {
			value += 6;
		}

		return value;
	}

}
