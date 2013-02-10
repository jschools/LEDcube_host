package com.schooler.ledcube.function;

import com.schooler.ledcube.model.Point3D;
import com.schooler.ledcube.text.BitmapFont;
import com.schooler.ledcube.text.MonochromeBitmapFont;

public class TextPlaneFunction extends PlaneFunction implements TimeFunction {

	private BitmapFont font = new MonochromeBitmapFont();
	private int stringIdx = 0;
	private String text;
	private final double timePerCharacter;

	public TextPlaneFunction(int plane, int row, String text, double framesPerChar) {
		super(plane, row, true);
		this.text = text;
		this.timePerCharacter = 33 * framesPerChar;
	}

	@Override
	public void setTime(double millis) {
		stringIdx = (int) (millis / timePerCharacter) % text.length();
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

			return (characterBitmap[point.z] & (1 << (7 - point.y))) > 0;
		}

		return false;
	}

}
