package com.schooler.ledcube.painter;

import com.schooler.ledcube.CubeMain;
import com.schooler.ledcube.model.CubeFrames;
import com.schooler.ledcube.model.Point3D;
import com.schooler.ledcube.text.TextPlane;

public class MarqueeTextPainter extends Painter {

	private static final int LEAD_FRAMES = 28;
	private static final int TRAIL_FRAMES = 0;
	private static final int DIM = CubeMain.CUBE_DIM;

	private TextPlane textPlane;
	private int totalFrames;

	public MarqueeTextPainter(CubeFrames cube, String text) {
		super(cube);

		this.textPlane = new TextPlane(text);
		this.totalFrames = LEAD_FRAMES + text.length() * 8 + TRAIL_FRAMES;
	}

	@Override
	public void paint() {
		CubeFrames cube = getCube();

		int frame = cube.getState().getFrame();
		int offset = frame % totalFrames - LEAD_FRAMES;

		int cubeX;
		int cubeY;
		
		// paint the first face
		cubeY = 0;
		for (cubeX = 0; cubeX < DIM; cubeX++) {
			paintColumn(cubeX, cubeY, offset);
		}

		// paint the second face
		cubeX = DIM - 1;
		for (cubeY = 0; cubeY < DIM; cubeY++) {
			paintColumn(cubeX, cubeY, offset);
		}

		// paint the third face
		cubeY = DIM - 1;
		for (cubeX = DIM - 1; cubeX >= 0; cubeX--) {
			paintColumn(cubeX, cubeY, offset);
		}

		// paint the last face
		cubeX = 0;
		for (cubeY = DIM - 1; cubeY >= 0; cubeY--) {
			paintColumn(cubeX, cubeY, offset);
		}

	}

	private void paintColumn(int cubeX, int cubeY, int offset) {
		Point3D point = Point3D.getInstance();

		int wrappingIndex = getWrappingIndex(cubeX, cubeY);
		int bitmapX = wrappingIndex + offset;
		for (int cubeZ = 0; cubeZ < DIM; cubeZ++) {
			point.set(cubeX, cubeY, cubeZ);
			getCube().set(point, textPlane.getBitmap().get(bitmapX, DIM - 1 - cubeZ));
		}

		point.recycle();
	}

	private static int getWrappingIndex(int cubeX, int cubeY) {
		if (cubeY == 0) {
			return 0 * (DIM - 1) + cubeX;
		}

		if (cubeX == DIM - 1) {
			return 1 * (DIM - 1) + cubeY;
		}

		if (cubeY == DIM - 1) {
			return 3 * (DIM - 1) - cubeX;
		}

		if (cubeX == 0) {
			return 4 * (DIM - 1) - cubeY;
		}

		return Integer.MIN_VALUE;
	}

}
