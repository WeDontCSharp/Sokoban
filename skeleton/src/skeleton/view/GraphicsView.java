package skeleton.view;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GraphicsView extends JPanel implements IView<StateChangeMessage>{

	private ArrayList<StateChangeMessage> stateChanges;
	
	public GraphicsView(ArrayList<StateChangeMessage> stateChanges) {
		super();
		this.stateChanges = stateChanges;
	}
	
	@Override
	public void sendMessage(StateChangeMessage msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveMessage(StateChangeMessage msg) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO
	}

}
