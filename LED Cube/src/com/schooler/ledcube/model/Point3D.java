package com.schooler.ledcube.model;

import java.util.LinkedList;
import java.util.Queue;

import com.schooler.ledcube.CubeDebug;

public class Point3D {
	public static final boolean DEBUG = true;

	private static final int POOL_FILL_SIZE = 20;

	private static final Queue<Point3D> pool;
	static {
		pool = new LinkedList<Point3D>();
		fillPool();
	}

	public int x;
	public int y;
	public int z;

	private Point3D() {
		x = y = z = 0;
	}
	
	public Point3D set(int i, int j, int k) {
		this.x = i;
		this.y = j;
		this.z = k;

		return this;
	}

	public void reset() {
		x = y = z = 0;
	}

	public void reclaim() {
		reclaim(this);
	}

	public static Point3D getInstance() {
		return getPointFromPool();
	}
	
	public static Point3D newInstance(int i, int j, int k) {
		return getPointFromPool().set(i, j, k);
	}

	public static Point3D copy(Point3D src) {
		return getPointFromPool().set(src.x, src.y, src.z);
	}

	private static void reclaim(Point3D point) {
		point.x = point.y = point.z = 0;
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
		CubeDebug.println("Point3D fillPool");
		synchronized (pool) {
			for (int i = 0; i < POOL_FILL_SIZE; i++) {
				pool.add(new Point3D());
			}
		}
	}
}
