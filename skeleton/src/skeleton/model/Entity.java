package skeleton.model;

import skeleton.meta.PrettyPrinter;

public abstract class Entity implements IVisitor {
	protected Warehouse level;
	private Field curField;
	
	public Entity(Warehouse level, Field f) {
		this.level = level;
		this.curField = f;
		f.setCurEntity(this);
	}
	
	public abstract boolean push(Worker firstPusher, Entity pushed, Direction dir);
	public abstract boolean pushByWorker(Worker firstPusher, Worker pusher, Direction dir);
	public abstract boolean pushByCrate(Worker firstPusher, Crate pusher, Direction dir);
	
	public boolean step(Worker firstPusher, Direction dir) {
		PrettyPrinter.startFunction("Entity", "step(firstPusher, dir)");
		Field neighbor = curField.getNeighbourField(dir);
		if (neighbor.isEmpty()) {
			if (visit(firstPusher, neighbor)) {
				curField.unsetEntity();
				setCurField(neighbor);
				PrettyPrinter.endFunction("Entity", "step(firstPusher, dir)", "true");
				return true;
			}
		} else {
			Entity nextEntity = neighbor.getCurEntity().get();
			if (push(firstPusher, nextEntity, dir)) {
				getCurField().unsetEntity();
				setCurField(neighbor);
				System.out.println("< TRUE [:Entity].step(firstPusher, dir)");
				return true;
			}
		}
		PrettyPrinter.endFunction("Entity", "step(firstPusher, dir)", "false");
		return false;
	}
	
	public void setCurField(Field f) {
		PrettyPrinter.startFunction("Entity", "setCurField(f)");
		this.curField = f;
		PrettyPrinter.endFunction("Entity", "setCurField(f)");
	}
	
	public Field getCurField() {
		return this.curField;
	}
	
	public Warehouse getLevel() {
		return level;
	}
	
}
