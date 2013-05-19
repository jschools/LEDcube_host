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
		bitmap = new ArrayBitmap2D(8 * text.length(), 8);

		loadCharacters(text);
	}

	private void loadCharacters(String text) {
		for (int charIdx = 0; charIdx < text.length(); charIdx++) {
			byte[] charBytes = font.getChar(text.charAt(charIdx));
			
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					boolean value = (charBytes[i] & (1 << (7 - j))) > 0;
					bitmap.set(charIdx * 8, j, value);
				}
			}
		}
	}

	public Bitmap2D getBitmap() {
		return bitmap;
	}

}
