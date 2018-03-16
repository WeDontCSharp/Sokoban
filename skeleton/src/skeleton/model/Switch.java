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
	public boolean visitByCrate(Worker firstPusher, Crate c) {
		PrettyPrinter.startFunction("Switch", "visitByCrate(firstPusher, c)");
		super.setEntity(c);
		for (Hole h : holes) {
			h.setOpen(true);
			if (!h.isEmpty()) {
				Entity e = h.getEntity().get();
				e.visit(firstPusher, h);
			}
		}
		PrettyPrinter.endFunction("Switch", "visitByCrate(firstPusher, c)", "true");
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
