package com.schooler.ledcube;

import java.util.Random;

import processing.core.PApplet;
import processing.serial.Serial;

import com.schooler.ledcube.function.WillieFunction;
import com.schooler.ledcube.manipulator.Evaluator;
import com.schooler.ledcube.model.Cube;

public class Viz extends PApplet {

	public static final String GRAPHICS_ENGINE = P3D;
	// public static final String GRAPHICS_ENGINE = OPENGL;

	public static final int DIM = 8;
	public static final int LED_SIZE = 5;
	public static final int LED_SPACING = 15;

	public static final int LED_ON_ARGB = 0x804444ff;
	public static final int LED_OFF_ARGB = 0x08000000;
	public static final boolean DRAW_OFF_LEDS = false;

	public static final Random rand = new Random();

	private Cube cube;
	private float xRot = -3.6f;
	private float yRot = -3.92f;
	private float zoom = 500f;

	private Serial serial;

	public void setup() {
		size(800, 800, GRAPHICS_ENGINE);
		smooth();

		cube = new Cube();

		Evaluator evaluator = new Evaluator(cube);
		evaluator.evaluate(new WillieFunction());
	}

	@Override
	public void mouseDragged() {
		if (mouseButton == LEFT) {
			float dx = mouseX - pmouseX;
			float dy = mouseY - pmouseY;

			xRot += -dy * 0.01f;
			yRot += -dx * 0.01f;
		} else if (mouseButton == RIGHT) {
			float dz = mouseY - pmouseY;
			zoom += dz * 10;
		}

		// System.out.println(String.format("xRot:%6.2f yRot:%6.2f zoom:%6.2f",
		// xRot, yRot, zoom));
	}

	public void draw() {
		background(0xffffffff);

		// center
		translate(width / 2, height / 2, zoom);

		// rotate
		rotateX(xRot);
		rotateY(yRot);

		// start at corner
		translate(-DIM * LED_SPACING / 2, -DIM * LED_SPACING / 2, -DIM * LED_SPACING / 2);

		// axis
		drawAxis();

		pushMatrix();

		synchronized (cube) {
			for (int i = 0; i < DIM; i++) {
				pushMatrix();
				for (int j = 0; j < DIM; j++) {
					pushMatrix();
					for (int k = 0; k < DIM; k++) {
						drawLed(cube.get(i, j, k));
						translate(0, 0, LED_SPACING);
					}
					popMatrix();
					translate(0, LED_SPACING, 0);
				}
				popMatrix();
				translate(LED_SPACING, 0, 0);
			}
		}

		popMatrix();
	}

	public void drawLed(boolean on) {
		if (on || DRAW_OFF_LEDS) {
			fill(on ? LED_ON_ARGB : LED_OFF_ARGB);
			noStroke();
			box(LED_SIZE);
		}
	}

	public void drawAxis() {
		pushMatrix();

		noStroke();

		float offset = 10;

		translate(-offset, -offset, -offset);
		strokeWeight(10);
		float length = (DIM - 1) * LED_SPACING;

		fill(0xffff0000);
		pushMatrix();
		translate(length / 2 + offset, 0, 0);
		box(length, offset, offset);
		popMatrix();

		fill(0xff00ff00);
		pushMatrix();
		translate(0, length / 2 + offset, 0);
		box(offset, length, offset);
		popMatrix();

		fill(0xff0000ff);
		pushMatrix();
		translate(0, 0, length / 2 + offset);
		box(offset, offset, length);
		popMatrix();

		popMatrix();
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "com.schooler.ledcube.Viz" });
	}

}
