package skeleton.model;

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
	 * Sets the slipperiness of a target.
	 * @param slipFactor The new value that replaces the current factor.
	 * @return False, because the slipFactor of a target can never be changed.
	 */
	public boolean placeSlipFactor(double slipFactor) {
		return false;
	}
	
    /* (non-Javadoc)
     * @see skeleton.model.Field#unsetEntity()
     */
    @Override
	public void unsetEntity() {
		super.unsetEntity();
		if (whoPushed != null) {
			whoPushed.losePoint();
			whoPushed = null;
		}
	}
	
    /* (non-Javadoc)
     * @see skeleton.model.Floor#visitByCrate(skeleton.model.Worker, skeleton.model.Crate)
     */
    @Override
	public boolean visitByCrate(Worker firstPusher, Crate c) {
		super.setEntity(c);
		whoPushed = firstPusher;
		firstPusher.gainPoint();
		return true;
	}

}
