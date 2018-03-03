package model;

import java.util.ArrayList;

import gfx.Bitmap;
import gfx.Brush;

public class BloodLayer extends RenderLayer {

	private Bitmap staticBuffer;
	private ArrayList<BloodSplatter> splatters;
	
	public BloodLayer(int w, int h, Brush b, Brush blitBrush, int d) {
		super(w, h, b, blitBrush, d);
		this.staticBuffer = new Bitmap(w, h, b);
		this.staticBuffer.clear(0xffff00ff);
		this.splatters = new ArrayList<BloodSplatter>();
	}
	
	public void update() {
		for (int i = 0; i < splatters.size(); i++) {
			BloodSplatter sp = splatters.get(i);
			sp.update();
			if (!sp.active()) {
				splatters.remove(i);
				i--;
				
				sp.render(staticBuffer);
			}
		}
	}
	
	public void render(Bitmap bmp, int xoff, int yoff) {
		Bitmap mbmp = this.getBitmap();
		mbmp.blit(0, 0, 0, 0, mbmp.getWidth(), mbmp.getHeight(), staticBuffer);
		for (BloodSplatter s : splatters) {
			s.render(mbmp);
		}
		
		super.render(bmp, xoff, yoff);
	}
	
	public void add(BloodSplatter sp) {
		this.splatters.add(sp);
	}

}
