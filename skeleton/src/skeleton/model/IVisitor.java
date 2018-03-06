package skeleton.model;

public interface IVisitor {
	public abstract boolean canVisit(Worker firstPusher, IVisitable iv);
	public abstract void visit(Worker firstPusher, IVisitable iv);
}
