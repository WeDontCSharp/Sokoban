package skeleton.model;

import java.util.Optional;

import skeleton.meta.PrettyPrinter;

public abstract class Field implements IVisitable {
	private Warehouse level;
	private Field[] neighbours;
	private Optional<Entity> curEntity;
	
	public Field(Warehouse level) {
		this.level = level;
		this.neighbours = new Field[Direction.values().length];
	}
	
	// XXX: Shouldn't it be set entity? Elnézést, hogy csak most változtattam meg a nevét.
	public void setEntity(Entity e) {
		PrettyPrinter.startFunction("Field", "acceptEntity(e)");
		setCurEntity(e);
		PrettyPrinter.endFunction("Field", "acceptEntity(e)");
	}
	
	public abstract boolean acceptEntity(Worker firstPusher, Worker w);
	public abstract boolean acceptEntity(Worker firstPusher, Crate c);
	
	public boolean acceptEntity(Worker firstPusher, LifeCrate lc) {
		return acceptEntity(firstPusher, (Crate)lc);
	}
	
	@Override
	public boolean visitByWorker(Worker firstPusher, Worker w) {
		PrettyPrinter.startFunction("Field", "visitByWorker(firstPusher, w)");
		boolean ret = acceptEntity(firstPusher, w);
		PrettyPrinter.endFunction("Field", "visitByWorker(firstPusher, w)", ret ? "true" : "false");
		return ret;
	}

	@Override
	public boolean visitByCrate(Worker firstPusher, Crate c) {
		return acceptEntity(firstPusher, c);		
	}

	@Override
	public boolean visitByLifeCrate(Worker firstPusher, LifeCrate lc) {
		return acceptEntity(firstPusher, lc);		
	}
	
	public Optional<Entity> getCurEntity() {
		return curEntity;
	}
	
	public void setCurEntity(Entity e) {
		curEntity = Optional.of(e);
	}
	
	public void unsetEntity() {
		PrettyPrinter.startFunction("Field", "unsetEntity()");
		curEntity = Optional.empty();
		PrettyPrinter.endFunction("Field", "unsetEntity()");
	}
	
	public boolean isEmpty() {
		PrettyPrinter.startFunction("Field", "isEmpty()");
		if (this.curEntity == null) {
			// We haven't handled this field! We need to ask the user what he wants here
			PrettyPrinter.printText("Is an entity already on this field? [Y - yes, N - no] : ");
			char ans = Character.toUpperCase(PrettyPrinter.getAnswer().charAt(0));
			if (ans == 'Y') {
				// XXX
			}
			else {
				this.curEntity = Optional.empty();
			}
		}
		PrettyPrinter.endFunction("Field", "isEmpty()", this.curEntity.isPresent() ? "false" : "true");
		return !this.curEntity.isPresent();
		
	}
	
	public void setNeighbourField(Direction dir, Field f) {
		neighbours[dir.ordinal()] = f;
	}
	
	public Field getNeighbourField(Direction dir) {
		PrettyPrinter.startFunction("Field", "getNeighbourField(dir)");
		if (neighbours[dir.ordinal()] == null) {
			// We haven't handled this field! We need to ask the user what he wants here
			Field n = null;
			
			// XXX: more types?
			PrettyPrinter.printText("What kind of field is the neighbor? [F - floor, W - wall] : ");
			char ans = Character.toUpperCase(PrettyPrinter.getAnswer().charAt(0));
			if (ans == 'F') {
				n = new Floor(this.level);
			}
			else {
				n = new Wall(this.level);
			}
			
			neighbours[dir.ordinal()] = n;
		}
		PrettyPrinter.endFunction("Field", "getNeighbourField(dir)", "neighbor");
		return neighbours[dir.ordinal()];
	}
	
	public Warehouse getLevel() {
		return level;
	}
}
