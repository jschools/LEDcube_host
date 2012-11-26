package com.schooler.ledcube.model;

import java.util.LinkedList;
import java.util.Queue;

public class Point3D {
	public static final boolean DEBUG = true;

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
	
	public Point3D set(int i, int j, int k) {
		this.i = i;
		this.j = j;
		this.k = k;

		return this;
	}

	public void reclaim() {
		reclaim(this);
	}

	public static Point3D newInstance() {
		return getPointFromPool();
	}
	
	public static Point3D newInstance(int i, int j, int k) {
		return getPointFromPool().set(i, j, k);
	}

	public static Point3D copy(Point3D src) {
		return getPointFromPool().set(src.i, src.j, src.k);
	}

	private static void reclaim(Point3D point) {
		point.i = point.j = point.k = 0;
		synchronized (pool) {
			pool.add(point);
		}
	}

	private static Point3D getPointFromPool() {
		synchronized (pool) {
			if (pool.isEmpty()) {
				fillPool();
			}
			return pool.remove();
		}
	}

	private static void fillPool() {
		if (DEBUG) {
			System.out.println("Point3D fillPool");
		}
		synchronized (pool) {
			for (int i = 0; i < POOL_FILL_SIZE; i++) {
				pool.add(new Point3D());
			}
		}
	}
}
