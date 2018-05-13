package skeleton.view.gfx;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Draws a shape on the display.
 */
public class CrateShape extends Shape {

	public float scale = 1.0f;
	
	public CrateShape(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics g, int xoff, int yoff) {
		int eps = (int)(GraphicsView.UNIT_WIDTH * 0.05f);
		
		int xx = x + xoff;
		int yy = y + yoff;
		
		int outer = eps * 2;
		int inner = eps * 8;
		
		int width = (GraphicsView.UNIT_WIDTH - 2 * outer);
		int width_in = (GraphicsView.UNIT_WIDTH - 2 * inner);
		
		g.setColor(new Color(110, 70, 50));
		drawRectCent(g, xx + outer + width / 2, yy + outer + width / 2, (int)(width * scale), (int)(width * scale));
		
		g.setColor(new Color(160, 100, 70));
		drawRectCent(g, xx + inner + width_in / 2, yy + inner + width_in / 2, (int)(width_in * scale), (int)(width_in * scale));
	}
	
	/**
	 * Draws a rectangle.
	 */
	protected void drawRectCent(Graphics g, int cx, int cy, int w, int h) {
		int w2 = w / 2;
		int h2 = h / 2;
		g.fillRect(cx - w2, cy - h2, w, h);
	}

}
