package com.schooler.ledcube.control;


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
