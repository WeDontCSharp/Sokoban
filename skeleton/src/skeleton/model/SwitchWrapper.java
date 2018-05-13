package skeleton.model;

import skeleton.view.message.TileRegisterStateChangeMessage;
import skeleton.view.message.TileType;

/**
 * Overrides the methods which affect the display
 * and sends messages so that the View could handle the changes.
 */
public class SwitchWrapper extends Switch {

	/**
	 * Sends a message because a switch has been created.
	 * @param level The warehouse to create the switch in.
	 * @param x The X coordinate of the field.
	 * @param y The Y coordinate of the field.
	 */
	public SwitchWrapper(Warehouse level, int x, int y) {
		super(level, x, y);
		level.receiveMessage(new TileRegisterStateChangeMessage(x, y, TileType.Switch, this));
	}

}
