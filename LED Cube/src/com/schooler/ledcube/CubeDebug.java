package com.schooler.ledcube;

import java.io.PrintStream;

public class CubeDebug {

	public static boolean DEBUG = false;

	private static final PrintStream out = System.out;

	public static void print(boolean b) {
		if (DEBUG) {
			out.print(b);
		}
	}

	public static void print(char c) {
		if (DEBUG) {
			out.print(c);
		}
	}

	public static void print(int i) {
		if (DEBUG) {
			out.print(i);
		}
	}

	public static void print(long l) {
		if (DEBUG) {
			out.print(l);
		}
	}

	public static void print(float f) {
		if (DEBUG) {
			out.print(f);
		}
	}

	public static void print(double d) {
		if (DEBUG) {
			out.print(d);
		}
	}

	public static void print(char[] s) {
		if (DEBUG) {
			out.print(s);
		}
	}

	public static void print(String s) {
		if (DEBUG) {
			out.print(s);
		}
	}

	public static void print(Object obj) {
		if (DEBUG) {
			out.print(obj);
		}
	}

	public static void println() {
		if (DEBUG) {
			out.println();
		}
	}

	public static void println(boolean x) {
		if (DEBUG) {
			out.println(x);
		}
	}

	public static void println(char x) {
		if (DEBUG) {
			out.println(x);
		}
	}

	public static void println(int x) {
		if (DEBUG) {
			out.println(x);
		}
	}

	public static void println(long x) {
		if (DEBUG) {
			out.println(x);
		}
	}

	public static void println(float x) {
		if (DEBUG) {
			out.println(x);
		}
	}

	public static void println(double x) {
		if (DEBUG) {
			out.println(x);
		}
	}

	public static void println(char[] x) {
		if (DEBUG) {
			out.println(x);
		}
	}

	public static void println(String x) {
		if (DEBUG) {
			out.println(x);
		}
	}

	public static void println(Object x) {
		if (DEBUG) {
			out.println(x);
		}
	}

}
