package com.schooler.ledcube.output;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import com.schooler.ledcube.model.Cube;

public class FileOutput implements CubeOutput {

	private static final String DEFAULT_FILE_NAME = "E:\\Schooler\\Desktop\\test.cub";

	private String fileName;
	private ObjectOutputStream os;

	public FileOutput() {
		this(DEFAULT_FILE_NAME);
	}

	public FileOutput(String fileName) {
		this.fileName = fileName;
	}

	private void init() {
		try {
			os = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(fileName, false)));
		} catch (IOException e) {
			e.printStackTrace();
			os = null;
		}
	}

	@Override
	public void writeAllFrames(Cube cube) {
		List<byte[]> frames = cube.getFrames();
		if (os == null) {
			init();
		}

		try {
			os.writeObject(frames);
			System.out.println(String.format("Wrote %d frames to %s", Integer.valueOf(frames.size()), fileName));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				os = null;
			}
		}
	}

	@Override
	public OutputStream getOutputStream() {
		if (os == null) {
			init();
		}

		return os;
	}

}
