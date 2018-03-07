package skeleton.model;

import java.util.ArrayList;
import java.util.List;

public class Switch extends Floor {
	
	private List<Hole> holes;

	public Switch(Warehouse level) {
		super(level);
		holes = new ArrayList<Hole>();
	}
	
	@Override
	public boolean acceptEntity(Worker firstPusher, Crate c) {
		super.acceptEntity(c);
		for (Hole h : holes) {
			h.setOpen(true);
			if (!h.isEmpty()) {
				Entity e = h.getCurEntity().get();
				e.visit(firstPusher, h);
			}
		}
		return true;
	}
	
	public void unsetEntity() {
		super.unsetEntity();
		for (Hole h : holes) {
			h.setOpen(false);
		}
	}

}
