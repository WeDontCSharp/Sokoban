package skeleton.view;

import skeleton.model.Hole;

public class HoleStateChangeMessage extends StateChangeMessage {
	public final Hole hole;
	public final boolean open;
	
	public HoleStateChangeMessage(Hole hole, boolean open) {
		super(StateChangeMessageType.Hole);
        this.hole = hole;
        this.open = open;
	}
	
}