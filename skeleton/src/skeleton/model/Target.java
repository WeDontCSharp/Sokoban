package skeleton.model;

import skeleton.meta.PrettyPrinter;

/**
 * A class representing a target. Targets act like
 * floors but if a crate is pushed onto them, the
 * pushing worker gets a point.
 */
public class Target extends Floor {
	
    /**
	 * The worker who pushed the crate on the target.
	 */
    private Worker whoPushed;

	/**
	 * Creates a target.
	 * @param level The warehouse to the create the target in.
	 */
    public Target(Warehouse level) {
		super(level);
	}
	
	/**
	 * When the crate is removed from the target, the worker who got a point for it loses that point.
	 */
    @Override
	public void unsetEntity() {
		PrettyPrinter.startFunction("Target", "unsetEntity()");
		super.unsetEntity();
		if (whoPushed != null) {
			whoPushed.losePoint();
			whoPushed = null;
		}
		PrettyPrinter.endFunction("Target", "unsetEntity()");
	}
	
	/**
	 * Tries to step a crate onto the target.
     * Puts the crate on the target, stores the pusher and gives them a point.
	 * @param firstPusher The worker who initiates the push.
     * @param c The crate being pushed onto the target.
     * @return True, as the crate is put on the target.
	 */
    @Override
	public boolean visitByCrate(Worker firstPusher, Crate c) {
		PrettyPrinter.startFunction("Target", "visitByCrate(firstPusher, c)");
		super.setEntity(c);
		whoPushed = firstPusher;
		firstPusher.gainPoint();
		PrettyPrinter.endFunction("Target", "visitByCrate(firstPusher, c)", "true");
		return true;
	}

}
