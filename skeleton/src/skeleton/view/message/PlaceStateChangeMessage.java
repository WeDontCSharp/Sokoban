package skeleton.view.message;

import skeleton.model.Floor;
import skeleton.model.PlaceableItem;

public class PlaceStateChangeMessage extends StateChangeMessage {
	public final Floor floor;
	public final PlaceableItem item;
	
	public PlaceStateChangeMessage(Floor floor, PlaceableItem item) {
		super(StateChangeMessageType.Place);
		this.floor = floor;
		this.item = item;
	}
	
}
