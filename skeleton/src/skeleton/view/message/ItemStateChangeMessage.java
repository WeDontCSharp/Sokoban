package skeleton.view.message;

import skeleton.model.PlaceableItem;

public class ItemStateChangeMessage extends StateChangeMessage {
	public final int playerIndex;
	public final PlaceableItem item;
	
	public ItemStateChangeMessage(int playerIndex, PlaceableItem item) {
		super(StateChangeMessageType.Item);
		this.playerIndex = playerIndex;
		this.item = item;
	}
	
}