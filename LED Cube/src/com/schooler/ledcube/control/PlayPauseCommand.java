package com.schooler.ledcube.control;

import com.schooler.ledcube.PlaybackController;


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
