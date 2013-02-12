package com.schooler.ledcube.control;


public abstract class BaseCommand implements Runnable {

	protected PlaybackController controller;

	public BaseCommand(PlaybackController controller) {
		this.controller = controller;
	}

	public int getArgCount() {
		return 0;
	}

	@Override
	public void run() {
		// System.out.println(getClass().getSimpleName());
	}
}
