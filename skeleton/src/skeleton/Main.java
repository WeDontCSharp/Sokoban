package skeleton;

import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

import skeleton.model.LevelFormatException;
import skeleton.model.Warehouse;
import skeleton.view.KeyboardView;
import skeleton.view.gfx.GraphicsView;

/**
 * The starting-point for the application.
 */
public class Main {
	
	public static void main(String[] args) {
		GraphicsView gw = new GraphicsView();
		
		try {
			Warehouse wh = Warehouse.fromFile("C:/TMP/SokobanTestlevels/graphics_testlevel.json", gw);
			KeyboardView kw = new KeyboardView(0, 
					KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE, 
					wh, gw);
			
			mainGameLoop(wh, gw);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LevelFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void mainGameLoop(Warehouse wh, GraphicsView gw) {
		double goalDelta = 1000000000D / 60D;
		double goalSecDelta = 1000000000D;
		double delta = 0D;
		double secDelta = 0D;
		
		long lastTime = System.nanoTime();
		long currentTime;		
		long diff;
		
		int ups = 0;
		int renders = 0;
		
		while (true) {
			currentTime = System.nanoTime();
			diff = currentTime - lastTime;
			delta += diff;
			secDelta += diff;
			lastTime = currentTime;
			
			while (delta >= goalDelta) {
				delta -= goalDelta;
				wh.update();
				++ups;
			}
			gw.repaint();
			++renders;
			
			if (secDelta >= goalSecDelta) {
				System.out.println("Updates: " + ups + " Renders: " + renders);
				ups = 0;
				renders = 0;
				secDelta %= goalSecDelta;
			}
		}
	}

}
