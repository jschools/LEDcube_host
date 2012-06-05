package com.schooler.ledcube.command;

import com.schooler.ledcube.CubeMain;

public class ResumeCommand extends BaseCommand {

	public ResumeCommand(CubeMain cubeMain) {
		super(cubeMain);
	}

	@Override
	public void run() {
		System.out.println("Resuming!");
	}

}
