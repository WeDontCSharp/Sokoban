package skeleton.model;

import skeleton.view.message.TileRegisterStateChangeMessage;
import skeleton.view.message.TileType;

/**
 * Overrides the methods which affect the display
 * and sends messages so that the View could handle the changes.
 */
public class SpawnWrapper extends Spawn {

	public SpawnWrapper(Warehouse level, int x, int y) {
		super(level, x, y);
	}

	@Override
	public void setOwner(Worker w) {
		super.setOwner(w);
		int idx = w.getPlayerIndex();
		TileType tt = idx == 0 ? TileType.Player1Spawn : idx == 1 ? TileType.Player2Spawn : idx == 2 ? TileType.Player3Spawn : TileType.Player4Spawn;
		this.getLevel().receiveMessage(new TileRegisterStateChangeMessage(this.getX(), this.getY(), tt, this));
	}
	
}
