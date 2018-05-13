package skeleton.model;
import skeleton.view.message.HoleStateChangeMessage;
import skeleton.view.message.TileRegisterStateChangeMessage;
import skeleton.view.message.TileType;

/**
 * Overrides the methods which affect the display
 * and sends messages so that the View could handle the changes.
 */
public class HoleWrapper extends Hole {

	/**
	 * Sends a message because a hole has been created.
	 * @param level The warehouse to create the hole in.
	 * @param x The X coordinate of the field.
	 * @param y The Y coordinate of the field.
	 */
	public HoleWrapper(Warehouse level, int x, int y) {
		super(level, x, y);
		level.receiveMessage(new TileRegisterStateChangeMessage(x, y, TileType.Hole, this));
	}
	
	@Override
	public void setOpen(boolean open) {
		super.setOpen(open);
		// XXX: Not sure if works like this.
		this.getLevel().receiveMessage(new HoleStateChangeMessage(this, open));
	}

}
