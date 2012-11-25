package com.schooler.ledcube.model;

import java.util.LinkedList;
import java.util.Queue;

public class Point3D {

	private static final int POOL_FILL_SIZE = 20;

	private static final Queue<Point3D> pool;
	static {
		pool = new LinkedList<Point3D>();
		fillPool();
	}

	public int i;
	public int j;
	public int k;

	private Point3D() {
		i = j = k = 0;
	}
	
	private Point3D(Point3D src) {
		this.i = src.i;
		this.j = src.j;
		this.k = src.k;
	}

	public static synchronized Point3D newInstance() {
		return getPointFromPool();
	}

	public static synchronized Point3D copy(Point3D src) {
		Point3D result = getPointFromPool();
		result.i = src.i;
		result.j = src.j;
		result.k = src.k;

		return result;
	}

	public static synchronized void reclaim(Point3D point) {
		point.i = point.j = point.k = 0;
		pool.add(point);
	}

	private static synchronized Point3D getPointFromPool() {
		if (pool.isEmpty()) {
			fillPool();
		}
		return pool.remove();
	}

	private static synchronized void fillPool() {
		System.out.println("fill pool");
		for (int i = 0; i < POOL_FILL_SIZE; i++) {
			pool.add(new Point3D());
		}
	}
}
