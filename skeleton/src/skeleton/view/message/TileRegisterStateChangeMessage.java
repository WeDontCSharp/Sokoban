package skeleton.view.message;

import skeleton.model.Field;

public class TileRegisterStateChangeMessage extends StateChangeMessage {
	public final int x;
	public final int y;
	public final TileType tile;
	public final Field obj;
	
	/**
	 * 
	 * @param x 	X koordin�ta
	 * @param y		Y koordin�ta
	 * @param tile	A csempe t�pusa
	 * @param obj	A padl� elem, amin van
	 */
	public TileRegisterStateChangeMessage(int x, int y, TileType tile, Field obj) {
		super(StateChangeMessageType.TileRegister);
		this.x = x;
		this.y = y;
		this.tile = tile;
		this.obj = obj;
	}

}
