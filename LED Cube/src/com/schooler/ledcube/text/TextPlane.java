package com.schooler.ledcube.text;

import com.schooler.ledcube.bitmap.ArrayBitmap2D;
import com.schooler.ledcube.bitmap.Bitmap2D;

public class TextPlane {

	private String text;
	private Bitmap2D bitmap;
	private BitmapFont font;

	public TextPlane(String text) {
		this.font = new MonochromeBitmapFont();

		setTextInternal(text);
	}

	public void setText(String text) {
		setTextInternal(text);
	}

	private void setTextInternal(String text) {
		this.text = text;

		int width = 0;
		for (int i = 0; i < text.length(); i++) {
			width += font.getWidth(text.charAt(i));
		}

		bitmap = new ArrayBitmap2D(width, 8);

		renderText(text);
	}

	private void renderText(String text) {
		int left = 0;

		for (int charIdx = 0; charIdx < text.length(); charIdx++) {
			char c = text.charAt(charIdx);
			byte[] charBytes = font.getChar(c);
			int charWidth = font.getWidth(c);
			
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					boolean value = (charBytes[7 - j] & (1 << i)) > 0;
					bitmap.set(left + (7 - i), j, value);
				}
			}

			left += charWidth;
		}
	}

	public Bitmap2D getBitmap() {
		return bitmap;
	}

}
