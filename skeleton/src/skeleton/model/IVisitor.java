package skeleton.model;

public interface IVisitor {
	public abstract boolean canVisit(Worker firstPusher, IVisitable iv);
}
