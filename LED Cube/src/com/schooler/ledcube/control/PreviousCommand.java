package com.schooler.ledcube.control;

import com.schooler.ledcube.PlaybackController;


public class PreviousCommand extends BaseCommand {

	public PreviousCommand(PlaybackController controller) {
		super(controller);
	}

	@Override
	public void run() {
		super.run();

		controller.setPaused(true);
		controller.prevFrame();
	}

}
