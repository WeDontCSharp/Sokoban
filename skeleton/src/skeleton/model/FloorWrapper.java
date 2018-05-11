package skeleton.model;
import skeleton.view.PlaceStateChangeMessage;;

public class FloorWrapper extends Floor {

	public FloorWrapper(Warehouse level) {
		super(level);
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
		PlaceStateChangeMessage msg = new PlaceStateChangeMessage(this, item);
		this.getLevel().receiveMessage(msg);
		return true;
	}

}
