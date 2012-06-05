package com.schooler.ledcube.output;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.schooler.ledcube.model.Cube;

public class FileOutput implements CubeOutput {

	private static final String DEFAULT_FILE_NAME = "E:\\Schooler\\Desktop\\test.cub";

	private String fileName;
	private FileOutputStream fos;

	public FileOutput() {
		this(DEFAULT_FILE_NAME);
	}

	public FileOutput(String fileName) {
		this.fileName = fileName;
	}

	private void init() {
		try {
			fos = new FileOutputStream(fileName, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeCube(Cube cube) {
		byte[] bytes = cube.getRawCube();
		if (fos == null) {
			init();
		}

		try {
			fos.write(bytes);
			fos.close();
			fos = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OutputStream getOutputStream() {
		if (fos == null) {
			init();
		}

		return fos;
	}

}
