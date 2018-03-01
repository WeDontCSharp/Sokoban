package gfx;

public interface Brush {
	public static final Brush SIMPLE_BRUSH = (int s, int d) -> d;
	public static final Brush IGNORE_BRUSH = (int s, int d) -> (d == 0xffff00ff || d == 0xff800080) ? s : d;
	public static final Brush SUBTRACT_BRUSH = (int s, int d) -> {
		if (d == 0xffff00ff || d == 0xff800080) {
			return s;
		}
		if (d > s) {
			return 0;
		}
		return s - d;
	};
	
	public int plot(int src, int dest);
}
