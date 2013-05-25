package com.schooler.ledcube.control;

import com.schooler.ledcube.PlaybackController;


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
