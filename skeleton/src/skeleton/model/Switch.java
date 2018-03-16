package skeleton.model;

import java.util.ArrayList;
import java.util.List;

import skeleton.meta.PrettyPrinter;

public class Switch extends Floor {
	
	private List<Hole> holes;

	public Switch(Warehouse level) {
		super(level);
		holes = new ArrayList<Hole>();
	}
	
	@Override
	public boolean acceptEntity(Worker firstPusher, Crate c) {
		PrettyPrinter.startFunction("Switch", "acceptEntity(firstPusher, c)");
		super.setEntity(c);
		for (Hole h : holes) {
			h.setOpen(true);
			if (!h.isEmpty()) {
				Entity e = h.getCurEntity().get();
				e.visit(firstPusher, h);
			}
		}
		PrettyPrinter.endFunction("Switch", "acceptEntity(firstPusher, c)", "true");
		return true;
	}
	
	@Override
	public void unsetEntity() {
		PrettyPrinter.startFunction("Switch", "unsetEntity()");
		super.unsetEntity();
		for (Hole h : holes) {
			h.setOpen(false);
		}
		PrettyPrinter.endFunction("Switch", "unsetEntity()");
	}

}
