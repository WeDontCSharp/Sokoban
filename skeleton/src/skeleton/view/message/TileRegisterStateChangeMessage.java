package skeleton.view.message;

public class TileRegisterStateChangeMessage extends StateChangeMessage {
	public final int x;
	public final int y;
	public final TileType tile;
	
	public TileRegisterStateChangeMessage(int x, int y, TileType tile) {
		super(StateChangeMessageType.TileRegister);
		this.x = x;
		this.y = y;
		this.tile = tile;
	}

}
