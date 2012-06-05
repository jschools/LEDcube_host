package com.schooler.ledcube.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.schooler.ledcube.CubeMain;

public class StdinCommander {

	/* package */boolean running;
	private MainThread thread;
	/* package */Scanner scan;
	private CubeMain cubeMain;
	
	private Map<String, BaseCommand> keyStrokeMap;

	public StdinCommander(CubeMain cubeMain) {
		running = false;
		scan = new Scanner(System.in);
		thread = new MainThread();
		this.cubeMain = cubeMain;
		
		initKeyStrokeMap();
	}
	
	private void initKeyStrokeMap() {
		keyStrokeMap = new HashMap<String, BaseCommand>();
		keyStrokeMap.put(KeyStroke.PLAY_PAUSE,    new ResumeCommand(cubeMain));
//		keyStrokeMap.put(KeyStroke.NEXT,          new ResumeCommand(cubeMain));
//		keyStrokeMap.put(KeyStroke.PREVIOUS,      new ResumeCommand(cubeMain));
//		keyStrokeMap.put(KeyStroke.REVERSE,       new ResumeCommand(cubeMain));
//		keyStrokeMap.put(KeyStroke.FORWARD,       new ResumeCommand(cubeMain));
//		keyStrokeMap.put(KeyStroke.SPEED_FORWARD, new ResumeCommand(cubeMain));
//		keyStrokeMap.put(KeyStroke.SPEED_REVERSE, new ResumeCommand(cubeMain));
	}

	public void start() {
		if (!running) {
			running = true;
			new Thread(thread).start();
		}
	}

	private class MainThread implements Runnable {

		public MainThread() {
		}

		@Override
		public void run() {

			String commandInput;

			while (running) {
				System.out.println("Enter a command: ");
				commandInput = scan.nextLine();
			}
		}
	}
	
	
	private interface KeyStroke {
		public static final String PLAY_PAUSE = " ";
		public static final String NEXT = "d";
		public static final String PREVIOUS = "a";
		public static final String REVERSE = "z";
		public static final String FORWARD = "c";
		public static final String SPEED_FORWARD = "e";
		public static final String SPEED_REVERSE = "q";
	}

}
