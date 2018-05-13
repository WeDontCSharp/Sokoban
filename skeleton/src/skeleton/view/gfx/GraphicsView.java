package skeleton.view.gfx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.util.Pair;
import skeleton.model.Crate;
import skeleton.model.Field;
import skeleton.model.Hole;
import skeleton.view.IView;
import skeleton.view.message.CrateFallStateChangeMessage;
import skeleton.view.message.CrateStepStateChangeMessage;
import skeleton.view.message.HoleStateChangeMessage;
import skeleton.view.message.LifeCrateStepStateChangeMessage;
import skeleton.view.message.StateChangeMessage;
import skeleton.view.message.TileRegisterStateChangeMessage;
import skeleton.view.message.WorkerFallStateChangeMessage;
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
	private HashMap<Hole, HoleShape> holes = new HashMap<Hole, HoleShape>();
	
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

			PlayerShape w = this.workers[ws.playerIndex];
			if (w == null) {
				int idx = ws.playerIndex;
				Color col = idx == 0 ? Color.RED : idx == 1 ? Color.BLUE : idx == 2 ? Color.YELLOW : Color.GREEN;
				w = new PlayerShape(0, 0, col);
				this.workers[idx] = w;
			}
			
			w.direction = ws.direction;
			interpolatePos(w, ws.fieldFrom, ws.fieldTo, ws.percent);
		} break;
		
		case CrateStep: {
			CrateStepStateChangeMessage cs = (CrateStepStateChangeMessage)msg;
			
			CrateShape sh = this.crates.get(cs.crate);
			if (sh == null) {
				sh = new CrateShape(0, 0);
				this.crates.put(cs.crate, sh);
			}
			
			interpolatePos(sh, cs.fieldFrom, cs.fieldTo, cs.percent);
		} break;
		
		case LifeCrateStep: {
			LifeCrateStepStateChangeMessage lc = (LifeCrateStepStateChangeMessage)msg;
			
			CrateShape sh = this.crates.get(lc.lifeCrate);
			if (sh == null) {
				sh = new LifeCrateShape(0, 0);
				this.crates.put(lc.lifeCrate, sh);
			}
			
			interpolatePos(sh, lc.fieldFrom, lc.fieldTo, lc.percent);
		} break;
		
		case CrateFall: {
			CrateFallStateChangeMessage cf = (CrateFallStateChangeMessage)msg;
			
			CrateShape sh = this.crates.get(cf.crate);
			sh.scale = 1.0f - cf.percent * cf.percent;
			interpolatePos(sh, cf.from, cf.to, cf.percent);
		} break;
		
		case WorkerFall: {
			WorkerFallStateChangeMessage wf = (WorkerFallStateChangeMessage)msg;
			
			PlayerShape sh = this.workers[wf.playerIndex];
			sh.scalex = 1.0f - wf.percent * wf.percent;
			sh.scaley = sh.scalex;
			interpolatePos(sh, wf.from, wf.to, wf.percent);
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
				this.holes.put((Hole)tr.obj, new HoleShape(tr.x * UNIT_WIDTH, tr.y * UNIT_WIDTH));
			} break;
			
			case Switch: {
				this.floors.add(new SwitchShape(tr.x * UNIT_WIDTH, tr.y * UNIT_WIDTH));
			} break;
			
			default: {
				System.out.println("<unhandled tile>");
			}
			
			}
		} break;
		
		case Hole: {
			HoleStateChangeMessage hs = (HoleStateChangeMessage)msg;
			
			HoleShape h = this.holes.get(hs.hole);
			h.open = hs.open;
		} break;
		
		default: {
			System.out.println("<unhandled>");
		}
		
		}
	}
	
	private void interpolatePos(Shape sh, Field from, Field to, float percent) {
		int x_diff = (to.getX() - from.getX()) * UNIT_WIDTH;
		int y_diff = (to.getY() - from.getY()) * UNIT_WIDTH;
		
		sh.x = from.getX() * UNIT_WIDTH + (int)(x_diff * percent);
		sh.y = from.getY() * UNIT_WIDTH + (int)(y_diff * percent);
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
		for (HoleShape h : this.holes.values()) {
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
