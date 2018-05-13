package skeleton.model;
import skeleton.view.message.LifeCrateFallStateChangeMessage;
import skeleton.view.message.LifeCrateStepStateChangeMessage;

/**
 * Overrides the methods which affect the display
 * and sends messages so that the View could handle the changes.
 */
public class LifeCrateWrapper extends LifeCrate {
	
	/**
	 * Sends a message because a lifecrate has been created.
	 * @param g The warehouse to lifecreate the crate in.
	 * @param f The field to place the lifecrate onto.
	 */
	public LifeCrateWrapper(Warehouse g, Field f) {
		super(g, f);
		level.receiveMessage(new LifeCrateStepStateChangeMessage(this, f, f, 1.0f));
	}

}
