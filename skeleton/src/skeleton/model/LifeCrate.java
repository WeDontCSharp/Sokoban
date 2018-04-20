package skeleton.model;

/**
 * A class representing a crate with a heart symbol on it.
 * This crate gives a health to the worker who pushes it in an open hole.
 */
public class LifeCrate extends Crate {
	
	/**
	 * LifeCrate's construstor.
	 * 
	 * @param g Warehouse, where the entities are listed and specifies the level.
	 * @param f	The field, where the LifeCreate appears.
	 */
	public LifeCrate(Warehouse g, Field f) {
		super(g, f);			
	}
	
	/**
	 * Removes the lifeCrate from the game, and the worker, 
	 * who pushed it in the hole gains one healthpoint.
	 */
	public void remove(Worker firstPusher) {
		super.remove();
		firstPusher.gainHealth();
	}
	
	/* (non-Javadoc)
	 * @see skeleton.model.Crate#visit(skeleton.model.Worker, skeleton.model.IVisitable)
	 */
	@Override
	public boolean visit(Worker firstPusher, IVisitable iv) {
		return iv.visitByLifeCrate(firstPusher, this);
	}
	
}
