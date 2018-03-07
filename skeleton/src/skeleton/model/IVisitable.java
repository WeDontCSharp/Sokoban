package skeleton.model;

public interface IVisitable {
	public abstract boolean visitByWorker(Worker firstPusher, Worker w);
	public abstract boolean visitByCrate(Worker firstPusher, Crate c);
	public abstract boolean visitByLifeCrate(Worker firstPusher, LifeCrate lc);
}
