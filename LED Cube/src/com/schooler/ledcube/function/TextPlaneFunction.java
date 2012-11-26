package com.schooler.ledcube.function;

import com.schooler.ledcube.model.Point3D;
import com.schooler.ledcube.text.BitmapFont;
import com.schooler.ledcube.text.MonochromeBitmapFont;

public class TextPlaneFunction extends PlaneFunction implements TimeFunction {

	private BitmapFont font = new MonochromeBitmapFont();
	private int stringIdx = 0;
	private String text;

	public TextPlaneFunction(int plane, int row, String text) {
		super(plane, row, true);
		this.text = text;
	}

	@Override
	public void setTime(double millis) {
		stringIdx = (int) (millis / 33) % text.length();
	}

	@Override
	public boolean getValue(Point3D point) {
		boolean inPlane = super.getValue(point);
		if (inPlane) {
			switch (plane) {
			case PLANE_X:
				break;
			case PLANE_Y:
			case PLANE_Z:
			default:
				System.err.println("unsupported plane");
				break;
			}

			byte[] characterBitmap = font.getChar(text.charAt(stringIdx));

			return (characterBitmap[point.k] & (1 << (7 - point.j))) > 0;
		}

		return false;
	}

}
