package skeleton.view.gfx;

import java.awt.Color;
import java.awt.Graphics;

import skeleton.model.Direction;

public class PlayerShape extends Shape {
	public Color color;	
	public Direction direction;
	
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
		g.fillOval(xx + eps2, yy + eps2, GraphicsView.UNIT_WIDTH - eps2 * 2, GraphicsView.UNIT_WIDTH - eps2 * 2);
		
		g.setColor(Color.WHITE);
		
		int eps = eps2 * 3;
		
		int xpe = xx + eps;
		int xme = xx + GraphicsView.UNIT_WIDTH - eps;
		int xhf = xx + GraphicsView.UNIT_WIDTH / 2;
		
		int ype = yy + eps;
		int yme = yy + GraphicsView.UNIT_WIDTH - eps;
		int yhf = yy + GraphicsView.UNIT_WIDTH / 2;
		
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
		
		}
	}
}
