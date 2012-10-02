package com.schooler.ledcube.command;

import com.schooler.ledcube.model.CubeController;

public class WriteAllFramesCommand extends BaseCommand {

	public WriteAllFramesCommand(CubeController controller) {
		super(controller);
	}

	@Override
	public void run() {
		super.run();

		controller.outputEntireCube();
	}

}
