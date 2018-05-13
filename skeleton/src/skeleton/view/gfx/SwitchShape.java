package skeleton.view.gfx;

import java.awt.Color;
import java.awt.Graphics;

public class SwitchShape extends FloorShape {

	public SwitchShape(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics g, int xoff, int yoff) {
		super.draw(g, xoff, yoff);
		
		int eps = (int)(GraphicsView.UNIT_WIDTH * 0.15f);
		
		int xx = x + xoff;
		int yy = y + yoff;
		
		g.setColor(new Color(200, 20, 30));
		g.fillRect(xx + eps, yy + eps, GraphicsView.UNIT_WIDTH - 2 * eps, GraphicsView.UNIT_WIDTH - 2 * eps);
	}
	
}
