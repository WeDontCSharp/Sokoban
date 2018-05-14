package skeleton.view.message;

import skeleton.model.Hole;

public class HoleStateChangeMessage extends StateChangeMessage {
	public final Hole hole;		//A lyuk, aminek változik az állapota
	public final boolean open;	//A lyuk új állapota.
	
	/**
	 * Egy lyuk állapotváltozását reprezentálja.
	 * 
	 * @param hole	A lyuk, aminek változik az állapota
	 * @param open	A lyuk új állapota.
	 */
	public HoleStateChangeMessage(Hole hole, boolean open) {
		super(StateChangeMessageType.Hole);
        this.hole = hole;
        this.open = open;
	}
	
}