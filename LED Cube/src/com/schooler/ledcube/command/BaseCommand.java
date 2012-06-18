package com.schooler.ledcube.command;

import com.schooler.ledcube.CubeApplet;

public abstract class BaseCommand implements Runnable {

	protected CubeApplet cubeMain;

	public BaseCommand(CubeApplet cubeMain) {
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
