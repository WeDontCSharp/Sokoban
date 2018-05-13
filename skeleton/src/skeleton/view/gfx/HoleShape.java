package skeleton.view.gfx;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Draws a hole.
 */
public class HoleShape extends FloorShape {

	/**
	 * The state of the hole.
	 */
	public boolean open;
	
	public HoleShape(int x, int y) {
		super(x, y);
		this.open = true;
	}
	
	@Override
	public void draw(Graphics g, int xoff, int yoff) {
		super.draw(g, xoff, yoff);
		
		int xx = x + xoff;
		int yy = y + yoff;
		
		int eps = (int)(GraphicsView.UNIT_WIDTH * 0.1f);
		g.setColor(open ? Color.BLACK : new Color(160, 160, 160));
		g.fillRect(xx + eps, yy + eps, GraphicsView.UNIT_WIDTH - 2 * eps, GraphicsView.UNIT_WIDTH - 2 * eps);
	}

}
