package skeleton.model;
import skeleton.view.message.PlaceStateChangeMessage;

public class FloorWrapper extends Floor {

	public FloorWrapper(Warehouse level, int x, int y) {
		super(level, x, y);
		// TODO Auto-generated constructor stub
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
