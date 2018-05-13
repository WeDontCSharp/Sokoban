package skeleton.model;

import skeleton.view.message.TileRegisterStateChangeMessage;
import skeleton.view.message.TileType;

public class SwitchWrapper extends Switch {

	public SwitchWrapper(Warehouse level, int x, int y) {
		super(level, x, y);
		level.receiveMessage(new TileRegisterStateChangeMessage(x, y, TileType.Switch));
	}

}
