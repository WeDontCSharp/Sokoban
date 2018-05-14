package skeleton.view.message;

import skeleton.model.Direction;

public class StepControlMessage extends ControlMessage {		//L�p�sre utas�tja az egyik munk�st.
	public final int playerIndex;			//A l�p�sre utas�tott munk�s indexe.
	public final Direction direction;		//Az ir�ny, melybe a munk�st l�p�sre utas�tja.
	
	/**
	 * L�p�sre utas�tja az egyik munk�st.
	 * 
	 * @param playerIndex	A l�p�sre utas�tott munk�s indexe.
	 * @param direction		Az ir�ny, melybe a munk�st l�p�sre utas�tja.
	 */
	public StepControlMessage(int playerIndex, Direction direction) {
		super(ControlMessageType.Step);
		this.playerIndex = playerIndex;
		this.direction = direction;
	}
	
}