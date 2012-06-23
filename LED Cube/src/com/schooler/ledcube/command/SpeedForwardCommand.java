package com.schooler.ledcube.command;

import com.schooler.ledcube.model.CubeController;

public class SpeedForwardCommand extends BaseCommand {

	public SpeedForwardCommand(CubeController controller) {
		super(controller);
	}

	@Override
	public void run() {
		super.run();

		controller.setPlaySpeed(controller.getPlaySpeed() + 0.25d);
	}

}
