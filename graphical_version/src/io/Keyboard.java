package io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


public class Keyboard {
	
	public static Key UP = new Key();
	public static Key DOWN = new Key();
	public static Key LEFT = new Key();
	public static Key RIGHT = new Key();
	
	public static void init(JFrame frame) {
		frame.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {
			}
			
			public void keyReleased(KeyEvent ev) {
				switch (ev.getKeyCode()) {
				case KeyEvent.VK_UP:
					UP.setDown(false);
					break;
				case KeyEvent.VK_DOWN:
					DOWN.setDown(false);
					break;
				case KeyEvent.VK_LEFT:
					LEFT.setDown(false);
					break;
				case KeyEvent.VK_RIGHT:
					RIGHT.setDown(false);
					break;
				}
			}
			
			public void keyPressed(KeyEvent ev) {
				switch (ev.getKeyCode()) {
				case KeyEvent.VK_UP:
					UP.setDown(true);
					break;
				case KeyEvent.VK_DOWN:
					DOWN.setDown(true);
					break;
				case KeyEvent.VK_LEFT:
					LEFT.setDown(true);
					break;
				case KeyEvent.VK_RIGHT:
					RIGHT.setDown(true);
					break;
				}
			}
		});
	}	
}
