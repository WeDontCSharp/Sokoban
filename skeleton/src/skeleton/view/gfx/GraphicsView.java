package skeleton.view.gfx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import skeleton.model.Field;
import skeleton.view.IView;
import skeleton.view.message.StateChangeMessage;
import skeleton.view.message.WorkerStepStateChangeMessage;


@SuppressWarnings("serial")
public class GraphicsView extends JPanel implements IView<StateChangeMessage>{
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final int UNIT_WIDTH = 32;
	
	private JFrame frame;
	
	private int xOffset;
	private int yOffset;
	
	private ArrayList<Circle> workers = new ArrayList<Circle>();
	
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
		
		this.workers.add(new Circle(0, 0, UNIT_WIDTH, Color.RED));
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
			
			// XXX: Missing stuff Dani??????????????????
			
			Field to = ws.fieldTo;
			Circle w = this.workers.get(ws.playerIndex);
			w.x = to.getX() * UNIT_WIDTH;
			w.y = to.getY() * UNIT_WIDTH;
		} break;
		
		default: {
			System.out.println("<unhandled>");
		}
		
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (Circle c : this.workers) {
			c.draw(g, this.xOffset, this.yOffset);
		}
	}

}
