package skeleton.view.message;

import skeleton.model.PlaceableItem;

public class ItemStateChangeMessage extends StateChangeMessage {
	public final int playerIndex;		//A t�rgyat kap� vagy lerak� j�t�kos indexe.
	public final PlaceableItem item;	//A j�t�kos dolgoz�j�n�l l�v� t�rgy.
	
	/**
	 * Egy dolgoz�n�l l�v� t�rgyat v�ltoztatja a kijelz� fel�let�n.
	 * 
	 * @param playerIndex	A t�rgyat kap� vagy lerak� j�t�kos indexe.
	 * @param item			A j�t�kos dolgoz�j�n�l l�v� t�rgy.
	 */
	public ItemStateChangeMessage(int playerIndex, PlaceableItem item) {
		super(StateChangeMessageType.Item);
		this.playerIndex = playerIndex;
		this.item = item;
	}
	
}