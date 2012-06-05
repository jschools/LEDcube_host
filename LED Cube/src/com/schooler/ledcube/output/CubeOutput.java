package com.schooler.ledcube.output;

import java.io.OutputStream;

import com.schooler.ledcube.model.Cube;

public interface CubeOutput {

	public void writeCube(Cube cube);

	public OutputStream getOutputStream();

}
