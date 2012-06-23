package com.schooler.ledcube.command;

import com.schooler.ledcube.model.CubeController;

public class PreviousCommand extends BaseCommand {

	public PreviousCommand(CubeController controller) {
		super(controller);
	}

	@Override
	public void run() {
		super.run();

		controller.setPaused(true);
		controller.prevFrame();
	}

}
