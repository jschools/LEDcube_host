package com.schooler.ledcube.control;

import com.schooler.ledcube.PlaybackController;


public class NextCommand extends BaseCommand {

	public NextCommand(PlaybackController controller) {
		super(controller);
	}

	@Override
	public void run() {
		super.run();
		
		controller.setPaused(true);
		controller.nextFrame();
	}
}
