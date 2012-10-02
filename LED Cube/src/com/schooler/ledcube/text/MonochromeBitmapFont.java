package com.schooler.ledcube.text;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MonochromeBitmapFont implements BitmapFont {

	private static final String FONT_FILENAME = "mono_ascii.bmf";
	private static final int CHARACTER_SIZE_BYTES = 8;
	private static final int CHARACTER_SET_SIZE = 256;

	private byte[][] characterBitmaps;

	public MonochromeBitmapFont() {
		characterBitmaps = new byte[CHARACTER_SET_SIZE][CHARACTER_SIZE_BYTES];
		loadFont(FONT_FILENAME);
	}

	private void loadFont(String fileName) {
		InputStream in = null;

		try {
			in = new BufferedInputStream(new FileInputStream(fileName));

			int charIdx = 0;
			int lastByte = in.read();
			while (lastByte != -1) {
				for (int i = 0; i < CHARACTER_SIZE_BYTES; i++) {
					// flip vertically
					characterBitmaps[charIdx][7 - i] = (byte) lastByte;
					lastByte = in.read();
				}
				charIdx++;
			}

		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public int getBytesPerCharacter() {
		return CHARACTER_SIZE_BYTES;
	}

	@Override
	public byte[] getChar(char c) {
		return characterBitmaps[c];
	}

}
