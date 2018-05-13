package skeleton.view.gfx;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Draws a spawn.
 */
public class PlayerSpawnShape extends FloorShape {

	private Color color;
	
	public PlayerSpawnShape(int x, int y, Color col) {
		super(x, y);
		this.color = col;
	}
	
	@Override
	public void draw(Graphics g, int xoff, int yoff) {
		super.draw(g, xoff, yoff);
		
		int xx = x + xoff;
		int yy = y + yoff;
		
		int eps = (int)(GraphicsView.UNIT_WIDTH * 0.4f);
		g.setColor(this.color);
		
		int ypu = yy + GraphicsView.UNIT_WIDTH;
		int xpu = xx + GraphicsView.UNIT_WIDTH;
		
		g.fillPolygon(new int[] { xx, xx + eps, xx }, new int[] { yy, yy, yy + eps }, 3);
		g.fillPolygon(new int[] { xx, xx + eps, xx }, new int[] { ypu, ypu, ypu - eps }, 3);
		g.fillPolygon(new int[] { xpu - eps, xpu, xpu }, new int[] { yy, yy, yy + eps }, 3);
		g.fillPolygon(new int[] { xpu - eps, xpu, xpu }, new int[] { ypu, ypu, ypu - eps }, 3);
	}

}
