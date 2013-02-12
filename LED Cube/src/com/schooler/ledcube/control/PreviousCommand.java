package com.schooler.ledcube.control;


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
