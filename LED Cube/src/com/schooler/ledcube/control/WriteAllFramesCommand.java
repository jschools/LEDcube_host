package com.schooler.ledcube.control;


public class WriteAllFramesCommand extends BaseCommand {

	public WriteAllFramesCommand(PlaybackController controller) {
		super(controller);
	}

	@Override
	public void run() {
		super.run();

		controller.outputEntireCube();
	}

}
