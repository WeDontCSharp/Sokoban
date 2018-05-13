package skeleton.view.gfx;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Draws a lifecrate.
 */
public class LifeCrateShape extends CrateShape {

	public LifeCrateShape(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void draw(Graphics g, int xoff, int yoff) {
		super.draw(g, xoff, yoff);
		g.setColor(Color.RED);
		
		int eps = (int)(GraphicsView.UNIT_WIDTH * 0.1f);
		int eps2 = (int)(GraphicsView.UNIT_WIDTH * 0.27f);
		
		int ww = 2 * eps;
		int hh = GraphicsView.UNIT_WIDTH - 2 * eps2;
		
		int xx = x + xoff;
		int yy = y + yoff;
		
		int wws = (int)(scale * ww);
		int hhs = (int)(scale * hh);
		
		this.drawRectCent(g, xx + GraphicsView.UNIT_WIDTH / 2, yy + GraphicsView.UNIT_WIDTH / 2, wws, hhs);
		this.drawRectCent(g, xx + GraphicsView.UNIT_WIDTH / 2, yy + GraphicsView.UNIT_WIDTH / 2, hhs, wws);
	}

}
