package com.schooler.ledcube.test;

import junit.framework.TestCase;

import com.schooler.ledcube.model.CubeFrames;

public class CubeStateTest extends TestCase {

	public void testFrameNavigation() {
		CubeFrames cube = new CubeFrames();

		int frameCount = cube.getFrameCount();
		CubeFrames.State state = cube.getState();
		state.setLoopingEnabled(true);

		for (int i = 0; i < 3 * CubeFrames.DEFAULT_FRAME_COUNT; i++) {
			assertEquals("moveToNextFrame " + i, i % CubeFrames.DEFAULT_FRAME_COUNT, state.getFrame());
			state.moveToNextFrame();
		}

		state.moveToFrame(1);
		assertEquals("moveToFrame 1", 1, state.getFrame());

		state.moveToPrevFrame();
		assertEquals("moveToPrevFrame", 0, state.getFrame());

		state.moveToPrevFrame();
		assertEquals("moveToPrevFrame wrap", CubeFrames.DEFAULT_FRAME_COUNT - 1, state.getFrame());

		state.moveToPrevFrame();
		assertEquals("moveToPrevFrame", CubeFrames.DEFAULT_FRAME_COUNT - 2, state.getFrame());

		state.moveToFrame(16);
		assertEquals("moveToFrame 16", 16, state.getFrame());

		state.moveToFrame(64);
		assertEquals("moveToFrame 64", 0, state.getFrame());
	}

}
