package skeleton.view.gfx;

import java.awt.Color;
import java.awt.Graphics;

public class FloorShape extends Shape {

	public FloorShape(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics g, int xoff, int yoff) {
		g.setColor(new Color(200, 200, 200));
		g.fillRect(x + xoff, y + yoff, GraphicsView.UNIT_WIDTH, GraphicsView.UNIT_WIDTH);
	}

}
