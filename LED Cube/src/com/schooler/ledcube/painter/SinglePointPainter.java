package com.schooler.ledcube.painter;

import com.schooler.ledcube.model.CubeFrames;
import com.schooler.ledcube.model.Point3D;
import com.schooler.ledcube.trigger.TranslationTrigger;

public class SinglePointPainter extends Painter {

	private static final int CHANNEL_TRANSLATION = 1;

	private Point3D point;
	private TranslationTrigger transTrigger;
	
	private Point3D temp;

	public SinglePointPainter(CubeFrames cube) {
		super(cube);

		this.point = Point3D.getInstance();
		this.transTrigger = new TranslationTrigger(getTriggers(), CHANNEL_TRANSLATION);
		this.temp = Point3D.getInstance();
	}

	@Override
	public void paint() {
		transTrigger.getVector(temp);

		point.add(temp);
		point.constrainToCube();

		getCube().set(point, true);
	}

}
