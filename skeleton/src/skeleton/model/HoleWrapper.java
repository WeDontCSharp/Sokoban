package skeleton.model;
import skeleton.view.message.HoleStateChangeMessage;
import skeleton.view.message.TileRegisterStateChangeMessage;
import skeleton.view.message.TileType;

public class HoleWrapper extends Hole {

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
