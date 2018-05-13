package skeleton.model;
import skeleton.view.message.CrateFallStateChangeMessage;
import skeleton.view.message.CrateStepStateChangeMessage;

/**
 * Overrides the methods which affect the display
 * and sends messages so that the View could handle the changes.
 */
public class CrateWrapper extends Crate {
	
	/**
	 * Sends a message because a crate has been created.
	 * @param g The warehouse to create the crate in.
	 * @param f The field to place the crate onto.
	 */
	public CrateWrapper(Warehouse g, Field f) {
		super(g, f);
		this.getLevel().receiveMessage(new CrateStepStateChangeMessage(this, this.getCurField(), this.getCurField(), 1.0f));
	}

}
