package skeleton.view.gfx;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Draws a target.
 */
public class GoalShape extends FloorShape {

	public GoalShape(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics g, int xoff, int yoff) {
		super.draw(g, xoff, yoff);
		
		int xx = x + xoff;
		int yy = y + yoff;
		
		int eps = (int)(GraphicsView.UNIT_WIDTH * 0.15f);
		g.setColor(new Color(255, 50, 120));
		g.fillRect(xx, yy, GraphicsView.UNIT_WIDTH, eps);
		g.fillRect(xx, yy + GraphicsView.UNIT_WIDTH - eps, GraphicsView.UNIT_WIDTH, eps);
		g.fillRect(xx, yy, eps, GraphicsView.UNIT_WIDTH);
		g.fillRect(xx + GraphicsView.UNIT_WIDTH - eps, yy, eps, GraphicsView.UNIT_WIDTH);
	}
	
}
