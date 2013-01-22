package com.schooler.ledcube;

import java.util.Random;

import processing.core.PApplet;

import com.schooler.ledcube.command.KeyStrokeCommander;
import com.schooler.ledcube.cubecom.SerialCubeCom;
import com.schooler.ledcube.model.Cube;
import com.schooler.ledcube.model.CubeController;
import com.schooler.ledcube.model.Point3D;

public class CubeApplet extends PApplet implements CubeMain {

	public static final String GRAPHICS_ENGINE = P3D;
	// public static final String GRAPHICS_ENGINE = OPENGL;

	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 800;

	public static final int DIM = 8;
	public static final int LED_SIZE = 6;
	public static final int LED_SPACING = 15;

	public static final int LED_ON_ARGB = 0x804444ff;
	public static final int LED_OFF_ARGB = 0x04000000;
	public static final boolean DRAW_OFF_LEDS = true;

	public static final Random rand = new Random();

	private CubeController cubeController;
	private float xRot = PI;
	private float yRot = -3 * PI / 2;
	private float zRot = PI / 2;
	private float zoom = 500f;

	@Override
	public void setup() {
		size(WINDOW_WIDTH, WINDOW_HEIGHT, GRAPHICS_ENGINE);
		smooth();

		// create cube controller
		cubeController = new CubeController();

		// create output
		cubeController.setCubeOutput(new SerialCubeCom(this));

		// create commander
		KeyStrokeCommander commander = new KeyStrokeCommander(this, cubeController);
	}

	@Override
	public void mouseDragged() {
		if (mouseButton == LEFT) {
			float dx = mouseX - pmouseX;
			float dy = mouseY - pmouseY;

			xRot += -dx * 0.01f;
			yRot += -dy * 0.01f;
		}
		else if (mouseButton == RIGHT) {
			float dz = mouseY - pmouseY;
			zoom += dz * 10;
		}

		CubeDebug.println(String.format("xRot:%6.2f yRot:%6.2f zoom:%6.2f", Float.valueOf(xRot), Float.valueOf(yRot), Float.valueOf(zoom)));
	}

	@Override
	public void draw() {
		drawCube(cubeController.getCube());
	}

	public void drawCube(Cube cube) {
		// convert system to right-handed
		scale(1f, 1f, -1f);

		background(0xffffffff);

		// center in window
		translate(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2, -zoom);

		// rotate
		rotateZ(zRot);

		rotateX(xRot);
		rotateY(-yRot);

		// start at corner
		translate(-DIM * LED_SPACING / 2, -DIM * LED_SPACING / 2, -DIM * LED_SPACING / 2);

		// axis
		drawAxis();

		// draw the cube
		Point3D point = Point3D.newInstance();
		synchronized (cube) {
			for (int k = 0; k < DIM; k++) {
				pushMatrix();
				for (int j = 0; j < DIM; j++) {
					pushMatrix();
					for (int i = 0; i < DIM; i++) {
						drawLed(cube.get(point.set(i, j, k)));
						translate(LED_SPACING, 0, 0);
					}
					popMatrix();
					translate(0, LED_SPACING, 0);
				}
				popMatrix();
				translate(0, 0, LED_SPACING);
			}
		}
		point.reclaim();
	}

	private void drawLed(boolean on) {
		if (on || DRAW_OFF_LEDS) {
			fill(on ? LED_ON_ARGB : LED_OFF_ARGB);
			noStroke();
			box(LED_SIZE);
		}
	}

	private void drawAxis() {
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
		PApplet.main(new String[] { CubeApplet.class.getName() });
	}

}
