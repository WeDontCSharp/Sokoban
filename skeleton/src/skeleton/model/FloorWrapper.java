package skeleton.model;
import skeleton.view.message.PlaceStateChangeMessage;
import skeleton.view.message.TileRegisterStateChangeMessage;
import skeleton.view.message.TileType;

public class FloorWrapper extends Floor {

	public FloorWrapper(Warehouse level, int x, int y) {
		super(level, x, y);
		this.getLevel().receiveMessage(new TileRegisterStateChangeMessage(x, y, TileType.Floor));
	}
	
	@Override
	public boolean placeSlipFactor(double slipFactor) {
		super.placeSlipFactor(slipFactor);
		PlaceableItem item;
		if (slipFactor > 1.0) {
			item = PlaceableItem.Honey;
		} else {
			item = PlaceableItem.Oil;
		}
		// XXX: Not sure if works like this.
		this.getLevel().receiveMessage(new PlaceStateChangeMessage(this, item));
		return true;
	}

}
