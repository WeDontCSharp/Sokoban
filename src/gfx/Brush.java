package gfx;

public interface Brush {
	public class RecolorBrush implements Brush {
		private int[] newColors;
		
		public RecolorBrush(int[] ncols) {
			this.newColors = ncols;
		}
		
		@Override
		public int plot(int src, int dest) {
			if (dest == 0xffff00ff || dest == 0xff800080) {
				return src;
			}
			switch (dest) {
			case 0xffff00ff:
			case 0xff800080: return src;
			
			case 0xff1B2F66: return newColors[0];
			case 0xff2C3454: return newColors[1];
			case 0xff3648BC: return newColors[2];
			case 0xff00137F: return newColors[3];
			case 0xff3838FF: return newColors[4];
			case 0xff596FFF: return newColors[5];
			case 0xff2E4FAA: return newColors[6];
			case 0xff2037BC: return newColors[7];
			}
			return dest;
		}
		
	}
	
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
