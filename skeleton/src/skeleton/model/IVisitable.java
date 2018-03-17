package skeleton.model;

/**
 * An interface representing the things that can be visited by things
 * implementing the {@link IVisitor IVisitor} interface. 
 * According to the current design it's representing the fields that can 
 * be visited by the entities.
 */
public interface IVisitable {
	/**
	 * A visitable thing is visited by a worker, here a worker is trying
	 * to the step on a field.
	 * @param firstPusher The worker who is pushing the chain. If there's
	 * no chain, it's same as the stepping worker.
	 * @param w The worker who is stepping on the field.
	 * @return True, if the worker successfully stepped on the field, false otherwise.
	 */
	public abstract boolean visitByWorker(Worker firstPusher, Worker w);
	/**
	 * A visitable thing is visited by a crate, here a crate is trying
	 * to the step on a field.
	 * @param firstPusher The worker who is pushing the chain.
	 * @param c The crate that is stepping on the field.
	 * @return True, if the crate successfully stepped on the field, false otherwise.
	 */
	public abstract boolean visitByCrate(Worker firstPusher, Crate c);
	/**
	 * A visitable thing is visited by a worker, here a lifecrate is trying
	 * to the step on a field.
	 * @param firstPusher The worker who is pushing the chain.
	 * @param lc The lifecrate that is stepping on the field.
	 * @return True, if the lifecrate successfully stepped on the field, false otherwise.
	 */
	public abstract boolean visitByLifeCrate(Worker firstPusher, LifeCrate lc);
}
