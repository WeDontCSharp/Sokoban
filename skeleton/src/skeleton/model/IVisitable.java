package skeleton.model;

public interface IVisitable {
	public abstract boolean canVisitBy(Worker firstPusher, Worker w);
	public abstract boolean canVisitBy(Worker firstPusher, Crate c);
	public abstract void visitBy(Worker firstPusher, Worker w);
	public abstract void visitBy(Worker firstPusher, Crate c);
	public abstract void visitBy(Worker firstPusher, LifeCrate lc);
}
