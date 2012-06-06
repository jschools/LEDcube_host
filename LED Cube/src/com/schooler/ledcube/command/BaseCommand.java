package com.schooler.ledcube.command;

import com.schooler.ledcube.CubeMain;

public abstract class BaseCommand implements Runnable {

	protected CubeMain cubeMain;

	public BaseCommand(CubeMain cubeMain) {
		this.cubeMain = cubeMain;
	}

	public int getArgCount() {
		return 0;
	}

	@Override
	public void run() {
		System.out.println(getClass().getSimpleName());
	}
}
