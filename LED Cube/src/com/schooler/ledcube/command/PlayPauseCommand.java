package com.schooler.ledcube.command;

import com.schooler.ledcube.model.CubeController;

public class PlayPauseCommand extends BaseCommand {

	public PlayPauseCommand(CubeController controller) {
		super(controller);
	}

	@Override
	public void run() {
		super.run();
		controller.setPaused(!controller.isPaused());
	}

}
