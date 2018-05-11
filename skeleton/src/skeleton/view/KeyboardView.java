package skeleton.view;

import java.awt.event.KeyAdapter;

import skeleton.model.Warehouse;

public class KeyboardView extends KeyAdapter implements IView<ControlMessage>{

	public KeyboardView(int playerIndex, int upKey, int downKey, int leftKey, int rightKey, int placeKey, Warehouse warehouse) {
		super();
		this.playerIndex = playerIndex;
		this.upKey = upKey;
		this.downKey = downKey;
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		this.placeKey = placeKey;
		this.warehouse = warehouse;
	}

	private int playerIndex;
	private int upKey;
	private int downKey;
	private int leftKey;
	private int rightKey;
	private int placeKey;	
	private Warehouse warehouse;
	
	@Override
	public void sendMessage(ControlMessage msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveMessage(ControlMessage msg) {
		// TODO Auto-generated method stub
		
	}
	
}
