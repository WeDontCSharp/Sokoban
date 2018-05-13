package skeleton.view.gfx;

import java.awt.Color;
import java.awt.Graphics;

import skeleton.model.PlaceableItem;

public class FloorShape extends Shape {

	public PlaceableItem item;
	
	public FloorShape(int x, int y) {
		super(x, y);
		this.item = PlaceableItem.Nothing;
	}

	@Override
	public void draw(Graphics g, int xoff, int yoff) {
		int xx = x + xoff;
		int yy = y + yoff;
		
		g.setColor(new Color(200, 200, 200));
		g.fillRect(xx, yy, GraphicsView.UNIT_WIDTH, GraphicsView.UNIT_WIDTH);
		
		int eps = (int)(0.1f * GraphicsView.UNIT_WIDTH);
		
		// XXX: Draw item
		if (this.item == PlaceableItem.Oil || this.item == PlaceableItem.Honey) {
			g.setColor(this.item == PlaceableItem.Oil ? Color.BLACK : new Color(255, 200, 10));
			
			g.fillOval(xx + eps, yy + eps, 5 * eps, 5 * eps);
			g.fillOval(xx + 8 * eps, yy + 5 * eps, 1 * eps, 1 * eps);
			g.fillOval(xx + 5 * eps, yy + 7 * eps, 2 * eps, 2 * eps);
		}
	}

}
