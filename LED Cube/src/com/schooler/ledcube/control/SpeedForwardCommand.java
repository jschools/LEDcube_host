package com.schooler.ledcube.control;

import com.schooler.ledcube.PlaybackController;


public class SpeedForwardCommand extends BaseCommand {

	public SpeedForwardCommand(PlaybackController controller) {
		super(controller);
	}

	@Override
	public void run() {
		super.run();

		controller.setPlaySpeed(controller.getPlaySpeed() + 0.25d);
	}

}
