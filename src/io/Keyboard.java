package io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


public class Keyboard {
	
	public static Key SPACE = new Key();
	
	public static Key UP = new Key();
	public static Key DOWN = new Key();
	public static Key LEFT = new Key();
	public static Key RIGHT = new Key();
	
	public static Key W = new Key();
	public static Key A = new Key();
	public static Key S = new Key();
	public static Key D = new Key();
	
	public static Key U = new Key();
	public static Key H = new Key();
	public static Key J = new Key();
	public static Key K = new Key();
	
	public static Key NP_8 = new Key();
	public static Key NP_4 = new Key();
	public static Key NP_5 = new Key();
	public static Key NP_6 = new Key();
	
	public static void init(JFrame frame) {
		frame.addKeyListener(new KeyListener() {
			private Key getKeyFromCode(KeyEvent ev) {
				switch (ev.getKeyCode()) {
				case KeyEvent.VK_SPACE:	return SPACE;
				
				case KeyEvent.VK_UP:	return UP;
				case KeyEvent.VK_DOWN:	return DOWN;
				case KeyEvent.VK_LEFT:	return LEFT;
				case KeyEvent.VK_RIGHT:	return RIGHT;
				
				case KeyEvent.VK_W:		return W;
				case KeyEvent.VK_A:		return A;
				case KeyEvent.VK_S:		return S;
				case KeyEvent.VK_D:		return D;
				
				case KeyEvent.VK_U:		return U;
				case KeyEvent.VK_H:		return H;
				case KeyEvent.VK_J:		return J;
				case KeyEvent.VK_K:		return K;
				
				case KeyEvent.VK_NUMPAD8:	return NP_8;
				case KeyEvent.VK_NUMPAD4:	return NP_4;
				case KeyEvent.VK_NUMPAD5:	return NP_5;
				case KeyEvent.VK_NUMPAD6:	return NP_6;
				
				default: 				return null;
				}
			}
			
			public void keyTyped(KeyEvent arg0) {
			}
			
			public void keyReleased(KeyEvent ev) {
				Key k = getKeyFromCode(ev);
				if (k != null) {
					k.setDown(false);
				}
			}
			
			public void keyPressed(KeyEvent ev) {
				Key k = getKeyFromCode(ev);
				if (k != null) {
					k.setDown(true);
				}
			}
		});
	}	
}
