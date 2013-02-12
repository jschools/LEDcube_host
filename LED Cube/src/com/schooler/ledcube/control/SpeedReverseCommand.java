package com.schooler.ledcube.control;


public class SpeedReverseCommand extends BaseCommand {

	public SpeedReverseCommand(PlaybackController controller) {
		super(controller);
	}

	@Override
	public void run() {
		super.run();

		controller.setPlaySpeed(controller.getPlaySpeed() - 0.25d);
	}

}
