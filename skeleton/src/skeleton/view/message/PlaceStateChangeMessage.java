package skeleton.view.message;

import skeleton.model.Floor;
import skeleton.model.PlaceableItem;

public class PlaceStateChangeMessage extends StateChangeMessage {
	public final Floor floor;			//A padló, amelyre lerakjuk a tárgyat.
	public final PlaceableItem item;	//A padlóra lerakandó tárgy
	
	/**
	 * Egy tárgy lerakását animálja
	 * 
	 * @param floor	A padló, amelyre lerakjuk a tárgyat.
	 * @param item	A padlóra lerakandó tárgy
	 */
	public PlaceStateChangeMessage(Floor floor, PlaceableItem item) {
		super(StateChangeMessageType.Place);
		this.floor = floor;
		this.item = item;
	}
	
}
