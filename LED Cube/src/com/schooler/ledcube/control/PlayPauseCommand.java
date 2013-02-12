package com.schooler.ledcube.control;


public class PlayPauseCommand extends BaseCommand {

	public PlayPauseCommand(PlaybackController controller) {
		super(controller);
	}

	@Override
	public void run() {
		super.run();
		controller.setPaused(!controller.isPaused());
	}

}
