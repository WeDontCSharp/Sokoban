package skeleton.model;

import skeleton.meta.PrettyPrinter;

public abstract class Entity implements IVisitor {
	protected Warehouse level;
	private Field curField;
	
	public Entity(Warehouse level, Field f) {
		this.level = level;
		this.curField = f;
		f.setEntity(this);
	}
	
	public abstract boolean push(Worker firstPusher, Entity pushed, Direction dir);
	public abstract boolean pushByWorker(Worker firstPusher, Worker pusher, Direction dir);
	public abstract boolean pushByCrate(Worker firstPusher, Crate pusher, Direction dir);
	
	public boolean step(Worker firstPusher, Direction dir) {
		PrettyPrinter.startFunction("Entity", "step(firstPusher, dir)");
		Field neighbour = curField.getNeighbourField(dir);
		if (neighbour.isEmpty()) {
			if (visit(firstPusher, neighbour)) {
				curField.unsetEntity();
				setCurField(neighbour);
				PrettyPrinter.endFunction("Entity", "step(firstPusher, dir)", "true");
				return true;
			}
		} else {
			Entity nextEntity = neighbour.getEntity().get();
			if (push(firstPusher, nextEntity, dir)) {
				if (visit(firstPusher, neighbour)) {
					curField.unsetEntity();
					setCurField(neighbour);
					PrettyPrinter.endFunction("Entity", "step(firstPusher, dir)", "true");
					return true;
				}
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
		PrettyPrinter.startFunction("Entity", "getCurField()");
		PrettyPrinter.endFunction("Entity", "getCurField()", "curField");
		return this.curField;
	}
	
	public Warehouse getLevel() {
		PrettyPrinter.startFunction("Entity", "getLevel()");
		PrettyPrinter.endFunction("Entity", "getLevel()", "level");
		return level;
	}
	
}
