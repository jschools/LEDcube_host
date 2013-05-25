package com.schooler.ledcube.text;

public final class Strings {

	public static final String WikipediaCube = "In geometry, a cube is a three-dimensional solid object bounded by six square faces, facets or sides, with three meeting at each vertex. The cube can also be called a regular hexahedron and is one of the five Platonic solids. It is a special kind of square prism, of rectangular parallelepiped and of trigonal trapezohedron. The cube is dual to the octahedron. It has cubical symmetry (also called octahedral symmetry). It is special by being a cuboid and a rhombohedron.";
	public static final String FULL_ASCII;
	static {
		StringBuilder builder = new StringBuilder(256);
		for (char c = 0; c < 256; c++) {
			builder.append(c);
		}
		FULL_ASCII = builder.toString();
	}

}
