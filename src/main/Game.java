package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import gfx.Bitmap;
import gfx.Brush;
import io.Keyboard;
import model.Grid;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable {

	public static final String TITLE = "Sokoban!";
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	public static final int TILE_WIDTH = 16;
	public static final int TILE_HEIGHT = 11;
	
	private JFrame frame;
	
	private BufferedImage image;
	private int[] pixels;
	
	private boolean running;
	
	private Bitmap screen;
	private Grid level;
	
	public Game() {
		frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		this.setMinimumSize(size);
		this.setMaximumSize(size);
		this.setPreferredSize(size);
		this.setSize(size);
		
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void init() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		running = true;
		screen = new Bitmap(WIDTH, HEIGHT, Brush.IGNORE_BRUSH);
		Keyboard.init(frame);
		
		level = Grid.fromFile("src/level1.lvl", 48, 48);
	}
	
	public synchronized void start() {
		init();
		new Thread(this).start();
	}
	
	public synchronized void stop() {
		running = false;
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long lastTick = System.currentTimeMillis();
		double delta = 0.0;
		double goal = 1000D * 1000D * 1000D / 60D;
		int ticks = 0;
		int frames = 0;
		
		while (running) {
			long currentTime = System.nanoTime();
			long currentTick = System.currentTimeMillis();
			long diff = currentTime - lastTime;
			lastTime = currentTime;
			delta += diff;
			if (delta >= goal) {
				delta -= goal;
				ticks++;
				tick();
			}
			frames++;
			render();
			if (currentTick - lastTick >= 1000) {
				System.out.println("ticks: " + ticks + ", frames: " + frames);
				ticks = 0;
				frames = 0;
				lastTick = currentTick;
			}
		}
	}
	
	private void tick() {
		level.update();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		/////////////////////////////////
		
		level.render(screen);
		
		/////////////////////////////////
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		new Game().start();
	}

}
