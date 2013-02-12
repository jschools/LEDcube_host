package com.schooler.ledcube.text;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class MonochromeBitmapFont implements BitmapFont {

	/*
	 * Reads a Bitmap Font file
	 * 
	 * Header:
	 * +----------------------------------+----------------------+------------------------------+
	 * |          11 byte constant        |        1 byte        |           2 bytes            |
	 * | 42 49 54 4D 41 50 20 46 4F 4E 54 |  bytes per character | number of characters in file |
	 * |    "BITMAP FONT" in US-ASCII     |        uint_8        |   uint_16, high bits first   |
	 * +----------------------------------+----------------------+------------------------------+
	 * 
	 */
	
	private static final String FONT_FILENAME = "mono_ascii.bmf";

	private byte[][] characterBitmaps;

	public MonochromeBitmapFont() {
		loadFont(FONT_FILENAME);
	}

	private void loadFont(String fileName) {
		InputStream in = null;

		int bytesPerCharacter = -1;
		int characterCount = -1;

		try {
			in = new BufferedInputStream(new FileInputStream(fileName));

			// read file header
			byte[] infoBytes = new byte[11];
			for (int i = 0; i < 11; i++) {
				infoBytes[i] = (byte) (in.read() & 0xff);
			}
			String info = new String(infoBytes, Charset.forName("US-ASCII"));
			if (!info.equals("BITMAP FONT")) {
				throw new IllegalStateException("Unknown file format: " + info);
			}

			// bytes per character
			bytesPerCharacter = in.read();

			// character count
			int characterCountUpper = in.read();
			int characterCountLower = in.read();
			characterCount = characterCountUpper << 8 | characterCountLower;

			// init array
			characterBitmaps = new byte[characterCount][bytesPerCharacter];

			// read font
			int charIdx = 0;
			int lastByte = in.read();
			while (lastByte != -1) {
				for (int i = 0; i < bytesPerCharacter; i++) {
					// flip vertically
					characterBitmaps[charIdx][bytesPerCharacter - i - 1] = (byte) lastByte;
					lastByte = in.read();
				}
				charIdx++;
			}

		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		catch (ArrayIndexOutOfBoundsException ioobe) {
			String format = "Error reading %s:\n  bytesPerChar:%d\n  characterCount:%d";
			String message = String.format(format, FONT_FILENAME, bytesPerCharacter, characterCount);
			System.err.println(message);
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
	public byte[] getChar(char c) {
		return characterBitmaps[c];
	}

}
