package skeleton.view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;

import skeleton.model.Direction;
import skeleton.model.Warehouse;
import skeleton.view.message.ControlMessage;
import skeleton.view.message.PlaceControlMessage;
import skeleton.view.message.StepControlMessage;

/**
 * A View that listens to the keypresses.
 */
public class KeyboardView extends KeyAdapter implements IView<ControlMessage>{

	private int playerIndex;
	private int upKey;
	private int downKey;
	private int leftKey;
	private int rightKey;
	private int placeKey;	
	private Warehouse warehouse;
	
	public KeyboardView(int playerIndex, int upKey, int downKey, int leftKey, int rightKey, int placeKey, Warehouse warehouse, JComponent cmp) {
		super();
		
		this.playerIndex = playerIndex;
		
		this.upKey = upKey;
		this.downKey = downKey;
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		this.placeKey = placeKey;
		this.warehouse = warehouse;
		
		cmp.addKeyListener(this);
	}
	
	@Override
	public void sendMessage(ControlMessage msg) {
		this.warehouse.receiveMessage(msg);
	}

	@Override
	public void receiveMessage(ControlMessage msg) {
		// XXX: Not requied
		
	}
	
	@Override
	public void keyPressed(KeyEvent ev) {
		int kc = ev.getKeyCode();
		
		if (kc == this.rightKey) {
			this.sendMessage(new StepControlMessage(this.playerIndex, Direction.Right));
		}
		else if (kc == this.leftKey) {
			this.sendMessage(new StepControlMessage(this.playerIndex, Direction.Left));
		}
		else if (kc == this.upKey) {
			this.sendMessage(new StepControlMessage(this.playerIndex, Direction.Up));
		}
		else if (kc == this.downKey) {
			this.sendMessage(new StepControlMessage(this.playerIndex, Direction.Down));
		}
		else if (kc == this.placeKey) {
			this.sendMessage(new PlaceControlMessage(this.playerIndex));
		}
	}
	
}
