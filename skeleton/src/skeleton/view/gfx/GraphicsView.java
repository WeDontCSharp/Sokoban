package skeleton.view.gfx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import skeleton.model.Crate;
import skeleton.model.Direction;
import skeleton.model.Field;
import skeleton.model.Hole;
import skeleton.model.PlaceableItem;
import skeleton.view.IView;
import skeleton.view.message.CrateFallStateChangeMessage;
import skeleton.view.message.CrateStepStateChangeMessage;
import skeleton.view.message.HealthStateChangeMessage;
import skeleton.view.message.HoleStateChangeMessage;
import skeleton.view.message.ItemStateChangeMessage;
import skeleton.view.message.LifeCrateFallStateChangeMessage;
import skeleton.view.message.LifeCrateStepStateChangeMessage;
import skeleton.view.message.PlaceStateChangeMessage;
import skeleton.view.message.ScoreStateChangeMessage;
import skeleton.view.message.StateChangeMessage;
import skeleton.view.message.TileRegisterStateChangeMessage;
import skeleton.view.message.WorkerFallStateChangeMessage;
import skeleton.view.message.WorkerSquashStateChangeMessage;
import skeleton.view.message.WorkerStepStateChangeMessage;


@SuppressWarnings("serial")
public class GraphicsView extends JPanel implements IView<StateChangeMessage>{
	static class PlayerDescriptor {
		public PlayerShape shape;
		public String name;
		public int lives = 3;
		public int score = 0;
		public PlaceableItem item = PlaceableItem.Nothing;
		
		public PlayerDescriptor(PlayerShape sh, int idx) {
			this.shape = sh;
			this.name = "Player" + (idx + 1);
		}
	}
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final int UNIT_WIDTH = 32;
	
	private JFrame frame;
	
	private int mapWidth;
	private int mapHeight;
	
	private int xOffset;
	private int yOffset;
	
	private PlayerDescriptor[] workers = new PlayerDescriptor[4];
	private HashMap<Crate, CrateShape> crates = new HashMap<Crate, CrateShape>();
	private HashMap<Field, FloorShape> floors = new HashMap<Field, FloorShape>();
	private ArrayList<WallShape> walls = new ArrayList<WallShape>();
	private HashMap<Hole, HoleShape> holes = new HashMap<Hole, HoleShape>();
	
	private Font headFont = new Font("Arial", Font.BOLD, 24);
	private Font normFont = new Font("Arial", Font.PLAIN, 20);
	
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

			PlayerDescriptor w = this.workers[ws.playerIndex];
			if (w == null) {
				int idx = ws.playerIndex;
				Color col = idx == 0 ? Color.RED : idx == 1 ? Color.BLUE : idx == 2 ? Color.YELLOW : Color.GREEN;
				w = new PlayerDescriptor(new PlayerShape(0, 0, col), ws.playerIndex);
				this.workers[idx] = w;
			}
			
			w.shape.direction = ws.direction;
			interpolatePos(w.shape, ws.fieldFrom, ws.fieldTo, ws.percent);
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
		
		case LifeCrateFall: {
			LifeCrateFallStateChangeMessage cf = (LifeCrateFallStateChangeMessage)msg;
			
			CrateShape sh = this.crates.get(cf.lifeCrate);
			sh.scale = 1.0f - cf.percent * cf.percent;
			interpolatePos(sh, cf.from, cf.to, cf.percent);
		} break;
		
		case WorkerFall: {
			WorkerFallStateChangeMessage wf = (WorkerFallStateChangeMessage)msg;
			
			PlayerShape sh = this.workers[wf.playerIndex].shape;
			sh.scalex = 1.0f - wf.percent * wf.percent;
			sh.scaley = sh.scalex;
			interpolatePos(sh, wf.from, wf.to, wf.percent);
		} break;
		
		case WorkerSquash: {
			WorkerSquashStateChangeMessage ws = (WorkerSquashStateChangeMessage)msg;
			
			PlayerShape sh = this.workers[ws.playerIndex].shape;
			if (ws.direction == Direction.Left || ws.direction == Direction.Right) {
				sh.scalex = 1.0f - ws.percent;
				sh.offToX = (ws.direction == Direction.Right ? 1 : -1);
			}
			else {
				sh.scaley = 1.0f - ws.percent;
				sh.offToY = (ws.direction == Direction.Down ? 1 : -1);
			}
		} break;
		
		case TileRegister: {
			TileRegisterStateChangeMessage tr = (TileRegisterStateChangeMessage)msg;
			
			if (tr.x > this.mapWidth) {
				this.mapWidth = tr.x;
				this.xOffset = (WIDTH - UNIT_WIDTH * this.mapWidth) / 2;
			}
			if (tr.y > this.mapHeight) {
				this.mapHeight = tr.y;
			}
			
			switch (tr.tile) {
			
			case Floor: {
				this.floors.put(tr.obj, new FloorShape(tr.x * UNIT_WIDTH, tr.y * UNIT_WIDTH));
			} break;
			
			case Wall: {
				this.walls.add(new WallShape(tr.x * UNIT_WIDTH, tr.y * UNIT_WIDTH));
			} break;
			
			case Goal: {
				this.floors.put(tr.obj, new GoalShape(tr.x * UNIT_WIDTH, tr.y * UNIT_WIDTH));
			} break;
			
			case Player1Spawn: {
				this.floors.put(tr.obj, new PlayerSpawnShape(tr.x * UNIT_WIDTH, tr.y * UNIT_WIDTH, Color.RED));
			} break;
			
			case Player2Spawn: {
				this.floors.put(tr.obj, new PlayerSpawnShape(tr.x * UNIT_WIDTH, tr.y * UNIT_WIDTH, Color.BLUE));
			} break;
			
			case Player3Spawn: {
				this.floors.put(tr.obj, new PlayerSpawnShape(tr.x * UNIT_WIDTH, tr.y * UNIT_WIDTH, Color.YELLOW));
			} break;
			
			case Player4Spawn: {
				this.floors.put(tr.obj, new PlayerSpawnShape(tr.x * UNIT_WIDTH, tr.y * UNIT_WIDTH, Color.GREEN));
			} break;
			
			case Hole: {
				this.holes.put((Hole)tr.obj, new HoleShape(tr.x * UNIT_WIDTH, tr.y * UNIT_WIDTH));
			} break;
			
			case Switch: {
				this.floors.put(tr.obj, new SwitchShape(tr.x * UNIT_WIDTH, tr.y * UNIT_WIDTH));
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
		
		case Place: {
			PlaceStateChangeMessage ps = (PlaceStateChangeMessage)msg;
			
			FloorShape sh = this.floors.get(ps.floor);
			sh.item = ps.item;
		} break;
		
		case Health: {
			HealthStateChangeMessage hs = (HealthStateChangeMessage)msg;
			this.workers[hs.playerIndex].lives = hs.newHP;
		} break;
		
		case Item: {
			ItemStateChangeMessage is = (ItemStateChangeMessage)msg;
			this.workers[is.playerIndex].item = is.item;
		} break;
		
		case Score: {
			ScoreStateChangeMessage ss = (ScoreStateChangeMessage)msg;
			this.workers[ss.playerIndex].score = ss.newScore;
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
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for (FloorShape f : this.floors.values()) {
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
		
		int xoff = 16;
		Graphics2D g2 = (Graphics2D)g;
		for (PlayerDescriptor c : this.workers) {
			if (c == null) {
				continue;
			}
			c.shape.draw(g, this.xOffset, this.yOffset);
			
			g2.setColor(c.shape.color);
			g2.setFont(this.headFont);
			
			int ypos = this.mapHeight * UNIT_WIDTH + this.yOffset + 64;
			g2.drawString(c.name, xoff, ypos);
			ypos += 22;
			
			g2.setFont(this.normFont);
			g2.drawString("Lives: " + c.lives, xoff, ypos);
			ypos += 22;
			g2.drawString("Score: " + c.score, xoff, ypos);
			ypos += 22;
			g2.drawString("Item: " + (c.item == PlaceableItem.Nothing ? "-" : c.item == PlaceableItem.Oil ? "Oil" : "Honey"), 
					xoff, ypos);
			
			xoff += (WIDTH - 16) / 4;
		}
		
		for (CrateShape c : this.crates.values()) {
			c.draw(g, this.xOffset, this.yOffset);
		}
	}

}
