package com.schooler.ledcube.function;

import java.util.HashSet;
import java.util.Set;

import com.schooler.ledcube.graphics.Painter;
import com.schooler.ledcube.model.CubeFrames;
import com.schooler.ledcube.model.Point3D;
import com.schooler.ledcube.util.CubeDebug;

public class Evaluator extends Painter {

	private BooleanFunction function;
	private Set<Integer> cachedFrameIndexes;
	private boolean isTimeFunction;

	public Evaluator(CubeFrames cube, BooleanFunction function) {
		super(cube);

		this.function = function;
		this.isTimeFunction = function instanceof TimeFunction;

		this.cachedFrameIndexes = new HashSet<Integer>();
	}

	@Override
	public void paint() {
		CubeFrames cube = getCube();

		if (isTimeFunction) {
			CubeDebug.println("Time: " + cube.getState().getTime());

			((TimeFunction) function).setTime(cube.getState().getTime());
		}

		int frame = cube.getState().getFrame();
		if (!cachedFrameIndexes.contains(Integer.valueOf(frame))) {
			final int dim = cube.getDim();

			Point3D point = Point3D.getInstance();
			for (int i = 0; i < dim; i++) {
				for (int j = 0; j < dim; j++) {
					for (int k = 0; k < dim; k++) {
						point.x = i;
						point.y = j;
						point.z = k;
						cube.set(point, function.getValue(point));
					}
				}
			}

			point.reclaim();

			cachedFrameIndexes.add(Integer.valueOf(cube.getState().getFrame()));
		}
	}

}
