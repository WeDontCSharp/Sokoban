package skeleton.view.message;

import skeleton.model.Field;

public class TileRegisterStateChangeMessage extends StateChangeMessage {
	public final int x;
	public final int y;
	public final TileType tile;
	public final Field obj;
	
	/**
	 * 
	 * @param x 	X koordináta
	 * @param y		Y koordináta
	 * @param tile	A csempe típusa
	 * @param obj	A padló elem, amin van
	 */
	public TileRegisterStateChangeMessage(int x, int y, TileType tile, Field obj) {
		super(StateChangeMessageType.TileRegister);
		this.x = x;
		this.y = y;
		this.tile = tile;
		this.obj = obj;
	}

}
