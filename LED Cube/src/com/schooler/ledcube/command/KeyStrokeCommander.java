package com.schooler.ledcube.command;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import com.schooler.ledcube.CubeApplet;
import com.schooler.ledcube.model.CubeController;

public class KeyStrokeCommander {

	/* package */boolean running;
	private CubeController controller;

	/* package */Map<Character, BaseCommand> keyStrokeMap;
	/* package */BaseCommand unknownCommand;

	public KeyStrokeCommander(CubeApplet cubeMain, CubeController controller) {
		running = false;
		this.controller = controller;

		initKeyStrokeMap();

		cubeMain.registerKeyEvent(this);
	}

	public void keyEvent(KeyEvent event) {
		if (event.getID() == KeyEvent.KEY_PRESSED) {
			BaseCommand command = keyStrokeMap.get(Character.valueOf(event.getKeyChar()));
			if (command == null) {
				command = unknownCommand;
			}
			new Thread(command).start();
		}
	}

	private void initKeyStrokeMap() {
		keyStrokeMap = new HashMap<Character, BaseCommand>();
		keyStrokeMap.put(Character.valueOf(KeyStroke.PLAY_PAUSE),       new PlayPauseCommand(controller));
		keyStrokeMap.put(Character.valueOf(KeyStroke.NEXT),             new NextCommand(controller));
		keyStrokeMap.put(Character.valueOf(KeyStroke.PREVIOUS),         new PreviousCommand(controller));
		keyStrokeMap.put(Character.valueOf(KeyStroke.REVERSE),          new ReverseCommand(controller));
		keyStrokeMap.put(Character.valueOf(KeyStroke.FORWARD),          new ForwardCommand(controller));
		keyStrokeMap.put(Character.valueOf(KeyStroke.SPEED_FORWARD),    new SpeedForwardCommand(controller));
		keyStrokeMap.put(Character.valueOf(KeyStroke.SPEED_REVERSE),    new SpeedReverseCommand(controller));
		keyStrokeMap.put(Character.valueOf(KeyStroke.WRITE_ALL_FRAMES), new WriteAllFramesCommand(controller));
		unknownCommand = new UnknownCommand(controller);
	}

	private interface KeyStroke {
		public static final char PLAY_PAUSE = ' ';
		public static final char NEXT = 'd';
		public static final char PREVIOUS = 'a';
		public static final char REVERSE = 'z';
		public static final char FORWARD = 'c';
		public static final char SPEED_FORWARD = 'e';
		public static final char SPEED_REVERSE = 'q';
		public static final char WRITE_ALL_FRAMES = 'o';
	}

}
