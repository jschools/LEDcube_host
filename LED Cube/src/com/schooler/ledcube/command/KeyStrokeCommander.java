package com.schooler.ledcube.command;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import com.schooler.ledcube.CubeApplet;

public class KeyStrokeCommander {

	/* package */boolean running;
	private CubeApplet cubeMain;

	/* package */Map<Character, BaseCommand> keyStrokeMap;
	/* package */BaseCommand unknownCommand;

	public KeyStrokeCommander(CubeApplet cubeMain) {
		running = false;
		this.cubeMain = cubeMain;

		initKeyStrokeMap();

		cubeMain.registerKeyEvent(this);
	}

	public void keyEvent(KeyEvent event) {
		if (event.getID() == KeyEvent.KEY_PRESSED) {
			BaseCommand command = keyStrokeMap.get(Character.valueOf(event.getKeyChar()));
			if (command == null) {
				command = unknownCommand;
			}
			command.run();
		}
	}

	private void initKeyStrokeMap() {
		keyStrokeMap = new HashMap<Character, BaseCommand>();
		keyStrokeMap.put(Character.valueOf(KeyStroke.PLAY_PAUSE),    new PlayPauseCommand(cubeMain));
		keyStrokeMap.put(Character.valueOf(KeyStroke.NEXT),          new NextCommand(cubeMain));
		keyStrokeMap.put(Character.valueOf(KeyStroke.PREVIOUS),      new PreviousCommand(cubeMain));
		keyStrokeMap.put(Character.valueOf(KeyStroke.REVERSE),       new ReverseCommand(cubeMain));
		keyStrokeMap.put(Character.valueOf(KeyStroke.FORWARD),       new ForwardCommand(cubeMain));
		keyStrokeMap.put(Character.valueOf(KeyStroke.SPEED_FORWARD), new SpeedForwardCommand(cubeMain));
		keyStrokeMap.put(Character.valueOf(KeyStroke.SPEED_REVERSE), new SpeedReverseCommand(cubeMain));
		unknownCommand = new UnknownCommand(cubeMain);
	}

	private interface KeyStroke {
		public static final char PLAY_PAUSE = ' ';
		public static final char NEXT = 'd';
		public static final char PREVIOUS = 'a';
		public static final char REVERSE = 'z';
		public static final char FORWARD = 'c';
		public static final char SPEED_FORWARD = 'e';
		public static final char SPEED_REVERSE = 'q';
	}

}
