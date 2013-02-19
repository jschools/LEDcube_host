package com.schooler.ledcube.painter;

import com.schooler.ledcube.model.CubeFrames;
import com.schooler.ledcube.model.Point3D;
import com.schooler.ledcube.trigger.TranslationTrigger;
import com.schooler.ledcube.trigger.TriggerSet;

public class SinglePointPainter extends Painter {

	private static final int CHANNEL_TRANSLATION = 0;

	private Point3D point;
	private TranslationTrigger transTrigger;
	
	private Point3D temp;

	public SinglePointPainter(CubeFrames cube, TriggerSet triggers) {
		super(cube, triggers);

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
