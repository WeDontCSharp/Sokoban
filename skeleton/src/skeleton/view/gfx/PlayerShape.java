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
		
		int eps = eps2 * 3;
		
		int xpe = xx + eps;
		int xme = xx + GraphicsView.UNIT_WIDTH - eps;
		int xhf = xx + GraphicsView.UNIT_WIDTH / 2;
		
		int ype = yy + eps;
		int yme = yy + GraphicsView.UNIT_WIDTH - eps;
		int yhf = yy + GraphicsView.UNIT_WIDTH / 2;
		
		/*
		switch (this.direction) {
		
		case Left: {
			g.fillPolygon(new int[] { xme, xpe, xme }, new int[] { ype, yhf, yme }, 3);
		} break;
		
		case Right: {
			g.fillPolygon(new int[] { xpe, xme, xpe }, new int[] { ype, yhf, yme }, 3);
		} break;
		
		case Up: {
			g.fillPolygon(new int[] { xpe, xhf, xme }, new int[] { yme, ype, yme }, 3);
		} break;
		
		case Down: {
			g.fillPolygon(new int[] { xpe, xhf, xme }, new int[] { ype, yme, ype }, 3);
		} break;
		
		}*/
	}
	
	private void drawCircleCent(Graphics g, int cx, int cy, int rx, int ry) {
		g.fillOval(cx - rx, cy - ry, 2 * rx, 2 * ry);
	}
}
