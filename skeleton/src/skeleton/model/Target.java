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
	
    /* (non-Javadoc)
     * @see skeleton.model.Field#unsetEntity()
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
	
    /* (non-Javadoc)
     * @see skeleton.model.Floor#visitByCrate(skeleton.model.Worker, skeleton.model.Crate)
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
