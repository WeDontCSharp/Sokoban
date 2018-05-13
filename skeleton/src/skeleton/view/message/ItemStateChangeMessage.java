package skeleton.view.message;

import skeleton.model.PlaceableItem;

public class ItemStateChangeMessage extends StateChangeMessage {
	public final int playerIndex;		//A tárgyat kapó vagy lerakó játékos indexe.
	public final PlaceableItem item;	//A játékos dolgozójánál lévõ tárgy.
	
	/**
	 * Egy dolgozónál lévõ tárgyat változtatja a kijelzõ felületén.
	 * 
	 * @param playerIndex	A tárgyat kapó vagy lerakó játékos indexe.
	 * @param item			A játékos dolgozójánál lévõ tárgy.
	 */
	public ItemStateChangeMessage(int playerIndex, PlaceableItem item) {
		super(StateChangeMessageType.Item);
		this.playerIndex = playerIndex;
		this.item = item;
	}
	
}