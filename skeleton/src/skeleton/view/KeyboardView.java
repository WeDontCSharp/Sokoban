package skeleton.view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;

import skeleton.model.Direction;
import skeleton.model.Warehouse;
import skeleton.view.message.ControlMessage;
import skeleton.view.message.StepControlMessage;

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
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent ev) {
		int kc = ev.getKeyCode();
		
		if (kc == this.rightKey) {
			this.sendMessage(new StepControlMessage(this.playerIndex, Direction.Right));
		}
	}
	
}
