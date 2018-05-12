package skeleton.view.gfx;

import java.awt.Color;
import java.awt.Graphics;

public class CrateShape extends Shape {

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
		
		g.setColor(new Color(110, 70, 50));
		g.fillRect(xx + outer, yy + outer, GraphicsView.UNIT_WIDTH - 2 * outer, GraphicsView.UNIT_WIDTH - 2 * outer);
		
		g.setColor(new Color(160, 100, 70));
		g.fillRect(xx + inner, yy + inner, GraphicsView.UNIT_WIDTH - 2 * inner, GraphicsView.UNIT_WIDTH - 2 * inner);
	}

}
