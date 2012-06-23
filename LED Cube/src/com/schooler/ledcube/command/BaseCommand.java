package com.schooler.ledcube.command;

import com.schooler.ledcube.model.CubeController;

public abstract class BaseCommand implements Runnable {

	protected CubeController controller;

	public BaseCommand(CubeController controller) {
		this.controller = controller;
	}

	public int getArgCount() {
		return 0;
	}

	@Override
	public void run() {
		System.out.println(getClass().getSimpleName());
	}
}
