package skeleton.view.message;

import skeleton.model.Floor;
import skeleton.model.PlaceableItem;

public class PlaceStateChangeMessage extends StateChangeMessage {
	public final Floor floor;			//A padl�, amelyre lerakjuk a t�rgyat.
	public final PlaceableItem item;	//A padl�ra lerakand� t�rgy
	
	/**
	 * Egy t�rgy lerak�s�t anim�lja
	 * 
	 * @param floor	A padl�, amelyre lerakjuk a t�rgyat.
	 * @param item	A padl�ra lerakand� t�rgy
	 */
	public PlaceStateChangeMessage(Floor floor, PlaceableItem item) {
		super(StateChangeMessageType.Place);
		this.floor = floor;
		this.item = item;
	}
	
}
