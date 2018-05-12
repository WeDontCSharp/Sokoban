package skeleton.view.gfx;

import java.awt.Color;
import java.awt.Graphics;

public class CrateShape extends Shape {

	public CrateShape(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics g, int xoff, int yoff) {
		g.setColor(new Color(110, 70, 50));
		
		int eps = (int)(GraphicsView.UNIT_WIDTH * 0.05f);
		g.fillRect(x + xoff + eps, y + yoff + eps, GraphicsView.UNIT_WIDTH - 2 * eps, GraphicsView.UNIT_WIDTH - 2 * eps);
		
		g.setColor(new Color(160, 100, 70));
		g.fillRect(x + xoff + eps * 6, y + yoff + eps * 6, GraphicsView.UNIT_WIDTH - 12 * eps, GraphicsView.UNIT_WIDTH - 12 * eps);
	}

}
