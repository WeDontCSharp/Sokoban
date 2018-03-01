package io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


public class Keyboard {
	
	public static Key UP = new Key();
	public static Key DOWN = new Key();
	public static Key LEFT = new Key();
	public static Key RIGHT = new Key();
	
	public static Key W = new Key();
	public static Key A = new Key();
	public static Key S = new Key();
	public static Key D = new Key();
	
	public static void init(JFrame frame) {
		frame.addKeyListener(new KeyListener() {
			private Key getKeyFromCode(KeyEvent ev) {
				switch (ev.getKeyCode()) {
				case KeyEvent.VK_UP:	return UP;
				case KeyEvent.VK_DOWN:	return DOWN;
				case KeyEvent.VK_LEFT:	return LEFT;
				case KeyEvent.VK_RIGHT:	return RIGHT;
				
				case KeyEvent.VK_W:		return W;
				case KeyEvent.VK_A:		return A;
				case KeyEvent.VK_S:		return S;
				case KeyEvent.VK_D:		return D;
				
				default: 				return null;
				}
			}
			
			public void keyTyped(KeyEvent arg0) {
			}
			
			public void keyReleased(KeyEvent ev) {
				Key k = getKeyFromCode(ev);
				k.setDown(false);
			}
			
			public void keyPressed(KeyEvent ev) {
				Key k = getKeyFromCode(ev);
				k.setDown(true);
			}
		});
	}	
}
