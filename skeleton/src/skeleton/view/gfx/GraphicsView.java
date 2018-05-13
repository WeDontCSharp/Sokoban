package skeleton.view.gfx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import skeleton.model.Crate;
import skeleton.model.Field;
import skeleton.model.Hole;
import skeleton.view.IView;
import skeleton.view.message.CrateStepStateChangeMessage;
import skeleton.view.message.StateChangeMessage;
import skeleton.view.message.TileRegisterStateChangeMessage;
import skeleton.view.message.WorkerStepStateChangeMessage;


@SuppressWarnings("serial")
public class GraphicsView extends JPanel implements IView<StateChangeMessage>{
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final int UNIT_WIDTH = 32;
	
	private JFrame frame;
	
	private int xOffset;
	private int yOffset;
	
	private PlayerShape[] workers = new PlayerShape[4];
	private HashMap<Crate, CrateShape> crates = new HashMap<Crate, CrateShape>();
	private ArrayList<FloorShape> floors = new ArrayList<FloorShape>();
	private ArrayList<WallShape> walls = new ArrayList<WallShape>();
	private ArrayList<HoleShape> holes = new ArrayList<HoleShape>();
	
	public GraphicsView() {
		super();
		
		this.frame = new JFrame("Sokoban");
		
		Dimension siz = new Dimension(WIDTH, HEIGHT);
		this.setMinimumSize(siz);
		this.setMaximumSize(siz);
		this.setPreferredSize(siz);
		this.setSize(siz);
		
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
		this.frame.add(this);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
		
		this.setFocusable(true);
		this.requestFocus();
		
		this.xOffset = 16;
		this.yOffset = 8;
	}
	
	@Override
	public void sendMessage(StateChangeMessage msg) {
		// XXX: Stub
	}

	@Override
	public void receiveMessage(StateChangeMessage msg) {
		switch (msg.type) {
		
		case WorkerStep: {
			WorkerStepStateChangeMessage ws = (WorkerStepStateChangeMessage)msg;
			
			Field from = ws.fieldFrom;
			Field to = ws.fieldTo;
			PlayerShape w = this.workers[ws.playerIndex];
			
			if (w == null) {
				int idx = ws.playerIndex;
				Color col = idx == 0 ? Color.RED : idx == 1 ? Color.BLUE : idx == 2 ? Color.YELLOW : Color.GREEN;
				w = new PlayerShape(0, 0, col);
				this.workers[idx] = w;
			}
			
			w.direction = ws.direction;
			
			int x_diff = (to.getX() - from.getX()) * UNIT_WIDTH;
			int y_diff = (to.getY() - from.getY()) * UNIT_WIDTH;
			
			w.x = from.getX() * UNIT_WIDTH + (int)(x_diff * ws.percent);
			w.y = from.getY() * UNIT_WIDTH + (int)(y_diff * ws.percent);
		} break;
		
		case CrateStep: {
			CrateStepStateChangeMessage cs = (CrateStepStateChangeMessage)msg;
			
			Field from = cs.fieldFrom;
			Field to = cs.fieldTo;
			
			CrateShape sh = this.crates.get(cs.crate);
			if (sh == null) {
				sh = new CrateShape(0, 0);
				this.crates.put(cs.crate, sh);
			}
			
			int x_diff = (to.getX() - from.getX()) * UNIT_WIDTH;
			int y_diff = (to.getY() - from.getY()) * UNIT_WIDTH;
			
			sh.x = from.getX() * UNIT_WIDTH + (int)(x_diff * cs.percent);
			sh.y = from.getY() * UNIT_WIDTH + (int)(y_diff * cs.percent);
		} break;
		
		case TileRegister: {
			TileRegisterStateChangeMessage tr = (TileRegisterStateChangeMessage)msg;
			
			switch (tr.tile) {
			
			case Floor: {
				this.floors.add(new FloorShape(tr.x * UNIT_WIDTH, tr.y * UNIT_WIDTH));
			} break;
			
			case Wall: {
				this.walls.add(new WallShape(tr.x * UNIT_WIDTH, tr.y * UNIT_WIDTH));
			} break;
			
			case Goal: {
				this.floors.add(new GoalShape(tr.x * UNIT_WIDTH, tr.y * UNIT_WIDTH));
			} break;
			
			case Player1Spawn: {
				this.floors.add(new PlayerSpawnShape(tr.x * UNIT_WIDTH, tr.y * UNIT_WIDTH, Color.RED));
			} break;
			
			case Player2Spawn: {
				this.floors.add(new PlayerSpawnShape(tr.x * UNIT_WIDTH, tr.y * UNIT_WIDTH, Color.BLUE));
			} break;
			
			case Player3Spawn: {
				this.floors.add(new PlayerSpawnShape(tr.x * UNIT_WIDTH, tr.y * UNIT_WIDTH, Color.YELLOW));
			} break;
			
			case Player4Spawn: {
				this.floors.add(new PlayerSpawnShape(tr.x * UNIT_WIDTH, tr.y * UNIT_WIDTH, Color.GREEN));
			} break;
			
			case Hole: {
				this.holes.add(new HoleShape(tr.x * UNIT_WIDTH, tr.y * UNIT_WIDTH));
			} break;
			
			case Switch: {
				this.floors.add(new SwitchShape(tr.x * UNIT_WIDTH, tr.y * UNIT_WIDTH));
			} break;
			
			default: {
				System.out.println("<unhandled tile>");
			}
			
			}
		} break;
		
		default: {
			System.out.println("<unhandled>");
		}
		
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (FloorShape f : this.floors) {
			f.draw(g, this.xOffset, this.yOffset);
		}
		for (WallShape w : this.walls) {
			w.draw(g, this.xOffset, this.yOffset);
		}
		for (HoleShape h : this.holes) {
			h.draw(g, this.xOffset, this.yOffset);
		}
		
		g.setColor(Color.BLACK);
		for (int i = UNIT_WIDTH; i < HEIGHT; i += UNIT_WIDTH) {
			g.drawLine(0, i + this.yOffset, WIDTH, i + this.yOffset);
		}
		for (int i = UNIT_WIDTH; i < WIDTH; i += UNIT_WIDTH) {
			g.drawLine(i + this.xOffset, 0, i + this.xOffset, HEIGHT);
		}
		
		for (PlayerShape c : this.workers) {
			if (c == null) {
				continue;
			}
			c.draw(g, this.xOffset, this.yOffset);
		}
		
		for (CrateShape c : this.crates.values()) {
			c.draw(g, this.xOffset, this.yOffset);
		}
	}

}
