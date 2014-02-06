package com.schooler.ledcube.geometry;

import com.schooler.ledcube.model.CubeFrames;
import com.schooler.ledcube.model.Point3D;

public class Lines {

	/**
	Ported from ftp://ftp.isc.org/pub/usenet/comp.sources.unix/volume26/line3d
	*/
	public static void drawLine(CubeFrames cube, Point3D a, Point3D b) {

		final int ddx = Math.abs(b.x - a.x) + 1;
		final int ddy = Math.abs(b.y - a.y) + 1;
		final int ddz = Math.abs(b.z - a.z) + 1;

		final int numPoints = Math.max(Math.max(ddx, ddy), ddz);
		final int[] xPoints = new int[numPoints];
		final int[] yPoints = new int[numPoints];
		final int[] zPoints = new int[numPoints];

		final int x1 = a.x;
		final int y1 = a.y;
		final int z1 = a.z;

		final int x2 = b.x;
		final int y2 = b.y;
		final int z2 = b.z;

		final int dx = x2 - x1;
		final int dy = y2 - y1;
		final int dz = z2 - z1;

		final int ax = Math.abs(dx) * 2;
		final int ay = Math.abs(dy) * 2;
		final int az = Math.abs(dz) * 2;

		final int sx = sign(dx);
		final int sy = sign(dy);
		final int sz = sign(dz);

		int x = x1;
		int y = y1;
		int z = z1;
		int idx = 0;

		double xd;
		double yd;
		double zd;

		if (ax >= Math.max(ay, az)) { // x dominant
			yd = ay - ax / 2;
			zd = az - ax / 2;

			while (true) {
				xPoints[idx] = x;
				yPoints[idx] = y;
				zPoints[idx] = z;
				idx = idx + 1;

				if (x == x2) { // end
					break;
				}

				if (yd >= 0) { // move along y
					y = y + sy;
					yd = yd - ax;
				}

				if (zd >= 0) { // move along z
					z = z + sz;
					zd = zd - ax;
				}

				x = x + sx; // move along x
				yd = yd + ay;
				zd = zd + az;
			}
		}
		else if (ay >= Math.max(ax, az)) { // y dominant
			xd = ax - ay / 2;
			zd = az - ay / 2;

			while (true) {
				xPoints[idx] = x;
				yPoints[idx] = y;
				zPoints[idx] = z;
				idx = idx + 1;

				if (y == y2) { // end
					break;
				}

				if (xd >= 0) { // move along x
					x = x + sx;
					xd = xd - ay;
				}

				if (zd >= 0) { // move along z
					z = z + sz;
					zd = zd - ay;
				}

				y = y + sy; // move along y
				xd = xd + ax;
				zd = zd + az;
			}
		}
		else if (az >= Math.max(ax, ay)) { // z dominant
			xd = ax - az / 2;
			yd = ay - az / 2;

			while (true) {
				xPoints[idx] = x;
				yPoints[idx] = y;
				zPoints[idx] = z;
				idx = idx + 1;

				if (z == z2) { // end
					break;
				}

				if (xd >= 0) { // move along x
					x = x + sx;
					xd = xd - az;
				}

				if (yd >= 0) { // move along y
					y = y + sy;
					yd = yd - az;
				}

				z = z + sz; // move along z
				xd = xd + ax;
				yd = yd + ay;
			}
		}

		Point3D point = Point3D.getInstance();
		for (int i = 0; i < numPoints; i++) {
			point.x = xPoints[i];
			point.y = yPoints[i];
			point.z = zPoints[i];
			point.constrainToCube();
			cube.set(point, true);
		}
		point.recycle();
	}

	private static int sign(float f) {
		if (f >= 0) {
			return 1;
		}

		return -1;
	}

}
