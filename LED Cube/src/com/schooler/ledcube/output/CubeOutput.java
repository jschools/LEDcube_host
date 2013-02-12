package com.schooler.ledcube.output;

import com.schooler.ledcube.model.CubeFrames;

public interface CubeOutput {

	public void writeAllFrames(CubeFrames cube);

	public void writeFrame(CubeFrames cube);

	public void flush();

	public void closeConnection();

}
