package skeleton.model;

public interface IVisitor {
	public boolean visit(Worker firstPusher, IVisitable iv);
}
