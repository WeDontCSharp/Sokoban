package skeleton.view.message;

import skeleton.model.Hole;

public class HoleStateChangeMessage extends StateChangeMessage {
	public final Hole hole;		//A lyuk, aminek v�ltozik az �llapota
	public final boolean open;	//A lyuk �j �llapota.
	
	/**
	 * Egy lyuk �llapotv�ltoz�s�t reprezent�lja.
	 * 
	 * @param hole	A lyuk, aminek v�ltozik az �llapota
	 * @param open	A lyuk �j �llapota.
	 */
	public HoleStateChangeMessage(Hole hole, boolean open) {
		super(StateChangeMessageType.Hole);
        this.hole = hole;
        this.open = open;
	}
	
}