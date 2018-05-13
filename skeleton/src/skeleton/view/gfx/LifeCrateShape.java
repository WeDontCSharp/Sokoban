package skeleton.view.gfx;

import java.awt.Color;
import java.awt.Graphics;

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
		int xx = x + xoff;
		int yy = y + yoff;
		int uw2 = GraphicsView.UNIT_WIDTH / 2;
		int hx = xx + uw2;
		int hy = yy + uw2;
		
		g.fillRect(hx - eps, yy + eps2, 2 * eps, GraphicsView.UNIT_WIDTH - 2 * eps2);
		g.fillRect(xx + eps2, hy - eps, GraphicsView.UNIT_WIDTH - 2 * eps2, 2 * eps);
	}

}
