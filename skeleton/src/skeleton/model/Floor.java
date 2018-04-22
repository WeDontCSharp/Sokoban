package skeleton.model;

/**
 * A class representing a floor. A floor is always
 * reachable by the entities.
 */
public class Floor extends Field {

	/**
	 * Creates a floor.
	 * @param level The warehouse to the create the floor in.
	 */
	public Floor(Warehouse level) {
		super(level);
	}
	
	/**
	 * Sets the slipperiness of a floor.
	 * @param slipFactor The new value that replaces the current factor.
	 * @return True, because the slipFactor of a floor can always be changed.
	 */
	public boolean placeSlipFactor(double slipFactor) {
		setSlipFactor(slipFactor);
		return true;
	}

    /* (non-Javadoc)
     * @see skeleton.model.IVisitable#visitByWorker(skeleton.model.Worker, skeleton.model.Worker)
     */
    @Override
	public boolean visitByWorker(Worker firstPusher, Worker w) {
		super.setEntity(w);
		return true;
	}

    /* (non-Javadoc)
     * @see skeleton.model.IVisitable#visitByCrate(skeleton.model.Worker, skeleton.model.Crate)
     */
    @Override
	public boolean visitByCrate(Worker firstPusher, Crate c) {
		super.setEntity(c);
		super.getLevel().updateBlocking(this, true);
		return true;
	}

}
