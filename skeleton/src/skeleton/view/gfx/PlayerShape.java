package skeleton.view.gfx;

import java.awt.Color;
import java.awt.Graphics;

import skeleton.model.Direction;

public class PlayerShape extends Shape {
	public Color color;	
	public Direction direction;
	public float scalex = 1.0f;
	public float scaley = 1.0f;
	
	public PlayerShape(int x, int y, Color color) {
		super(x, y);
		this.color = color;
		this.direction = Direction.Right;
	}

	@Override
	public void draw(Graphics g, int xoff, int yoff) {
		int xx = x + xoff;
		int yy = y + yoff;
		
		int eps2 = (int)(GraphicsView.UNIT_WIDTH * 0.1f);
		
		g.setColor(this.color);
		int r = (GraphicsView.UNIT_WIDTH - 2 * eps2) / 2;
		this.drawCircleCent(g, xx + GraphicsView.UNIT_WIDTH / 2, yy + GraphicsView.UNIT_WIDTH / 2, (int)(scalex * r), (int)(scaley * r));
		
		g.setColor(Color.WHITE);
		
		int eps = (int)(eps2 * 2.5f);
		
		drawTriCent(g, xx + GraphicsView.UNIT_WIDTH / 2, yy + GraphicsView.UNIT_WIDTH / 2, (int)(eps * scalex), (int)(eps * scaley));
	}
	
	private void drawCircleCent(Graphics g, int cx, int cy, int rx, int ry) {
		g.fillOval(cx - rx, cy - ry, 2 * rx, 2 * ry);
	}
	
	private void drawTriCent(Graphics g, int cx, int cy, int szx, int szy) {		
		switch (this.direction) {
		case Up:
			g.fillPolygon(new int[] { cx, cx + szx, cx - szx }, new int[] { cy - szy, cy + szy, cy + szy }, 3);
			break;
			
		case Down:
			g.fillPolygon(new int[] { cx, cx + szx, cx - szx }, new int[] { cy + szy, cy - szy, cy - szy }, 3);
			break;
			
		case Left:
			g.fillPolygon(new int[] { cx - szx, cx + szx, cx + szx }, new int[] { cy, cy - szy, cy + szy }, 3);
			break;
			
		case Right:
			g.fillPolygon(new int[] { cx + szx, cx - szx, cx - szx }, new int[] { cy, cy - szy, cy + szy }, 3);
			break;
		}
	}
}
