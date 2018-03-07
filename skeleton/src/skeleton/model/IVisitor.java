package skeleton.model;

public interface IVisitor {
	public abstract boolean visit(Worker firstPusher, IVisitable iv);

	boolean pushByWorker(Worker firstPusher, Worker pusher, Direction dir);
}
