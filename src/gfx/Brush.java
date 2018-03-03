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
	
	public class CombineBrush implements Brush {
		private Brush brush1;
		private Brush brush2;
		
		public CombineBrush(Brush b1, Brush b2) {
			this.brush1 = b1;
			this.brush2 = b2;
		}
		
		public int plot(int src, int dest) {
			return brush1.plot(src, brush2.plot(src, dest));
		}
	}
	
	public static final Brush SIMPLE_BRUSH = (int s, int d) -> d;
	public static final Brush IGNORE_BRUSH = (int s, int d) -> (d == 0xffff00ff || d == 0xff800080) ? s : d;
	public static final Brush SUBTRACT_BRUSH = (int s, int d) -> {
		if (d == 0xffff00ff || d == 0xff800080) {
			return s;
		}
		int r1 = (s >> 16) & 0xff;
		int g1 = (s >> 8) & 0xff;
		int b1 = s & 0xff;
		
		int r2 = (d >> 16) & 0xff;
		int g2 = (d >> 8) & 0xff;
		int b2 = d & 0xff;
		
		int r3 = Math.max(0, r1 - r2);
		int g3 = Math.max(0, g1 - g2);
		int b3 = Math.max(0, b1 - b2);
		
		return 0xff000000 | (r3 << 16) | (g3 << 8) | b3;
	};
	public static final Brush BLEND_BRUSH = (int s, int d) -> {
		if (d == 0xffff00ff || d == 0xff800080 || s == 0xffff00ff) {
			return s;
		}
		
		int r1 = (s >> 16) & 0xff;
		int g1 = (s >> 8) & 0xff;
		int b1 = s & 0xff;
		
		int r2 = (d >> 16) & 0xff;
		int g2 = (d >> 8) & 0xff;
		int b2 = d & 0xff;
		
		return 0xff000000 | (((r1 + r2 + r2) / 3) << 16) | (((g1 + g2 + g2) / 3) << 8) | ((b1 + b2 + b2) / 3);
	};
	
	public int plot(int src, int dest);
}
