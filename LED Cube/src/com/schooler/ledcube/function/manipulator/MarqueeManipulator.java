package com.schooler.ledcube.function.manipulator;

import com.schooler.ledcube.function.BooleanFunction;
import com.schooler.ledcube.model.Cube;
import com.schooler.ledcube.model.Point3D;

public class MarqueeManipulator extends TranslationManipulator {

	private static final int DIM = Cube.DEFAULT_DIM;
	private static final int SIDE_LEN = DIM - 1;
	private static final int PATTERN_STEPS = SIDE_LEN * 4;

	public MarqueeManipulator(BooleanFunction function) {
		super(function);
	}

	@Override
	protected void translatePoint(Point3D point) {
		int steps = getSteps() % PATTERN_STEPS;

		boolean plusI = inPlusI(point);
		boolean negI = plusI ? false : inNegI(point);
		boolean plusJ = plusI || negI ? false : inPlusJ(point);
		boolean negJ = plusI || negI || plusJ ? false : inNegJ(point);

		int distFromAxis = plusI ? 4 - point.j :
                           negI  ? 4 - (SIDE_LEN - point.j) :
                           plusJ ? 4 - (SIDE_LEN - point.i) :
						   negJ  ? 4 - point.i :
						   0;

		double percentOfLoop = (double) steps / (double) PATTERN_STEPS;
		int perimeterSideLength = 2 * distFromAxis - 1;

		int adjustedSteps = (int) Math.round(percentOfLoop * 4 * perimeterSideLength);

		for (int i = 0; i < adjustedSteps; i++) {
			if (inPlusI(point)) {
				point.i++;
			}
			else if (inPlusJ(point)) {
				point.j++;
			}
			else if (inNegI(point)) {
				point.i--;
			}
			else if (inNegJ(point)) {
				point.j--;
			}
		}
	}

	private static boolean inPlusI(Point3D point) {
		return point.i >= point.j && point.i + point.j < SIDE_LEN;
	}

	private static boolean inPlusJ(Point3D point) {
		return point.i > point.j && point.i + point.j >= SIDE_LEN;
	}

	private static boolean inNegI(Point3D point) {
		return point.i <= point.j && point.i + point.j > SIDE_LEN;
	}

	private static boolean inNegJ(Point3D point) {
		return point.i < point.j && point.i + point.j <= SIDE_LEN;
	}

}
