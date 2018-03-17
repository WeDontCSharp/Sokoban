package skeleton.model;

/**
 * An interface representing the things that can visit things
 * implementing the {@link IVisitable IVisitable} interface. 
 * According to the current design it's representing the entities
 * that can visit the fields.
 */
public interface IVisitor {
	/**
	 * A visitor is visiting a visitable thing, here an entity
	 * is trying to the step on a field.
	 * @param firstPusher The worker who is pushing the chain. If there's
	 * no chain, it's same as the stepping worker.
	 * @param iv The field to be visited.
	 * @return True, if the entity successfully stepped on the field, false otherwise.
	 */
	public boolean visit(Worker firstPusher, IVisitable iv);
}
