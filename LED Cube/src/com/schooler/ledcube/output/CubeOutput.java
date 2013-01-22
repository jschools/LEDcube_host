package com.schooler.ledcube.output;

import com.schooler.ledcube.model.Cube;

public interface CubeOutput {

	public void writeAllFrames(Cube cube);

	public void writeFrame(Cube cube);

	public void flush();

}
