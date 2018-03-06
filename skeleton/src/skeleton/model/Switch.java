package skeleton.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Switch extends Floor {
	
	List<Hole> holes;

	public Switch(Warehouse level, int x, int y) {
		super(level, x, y);
		holes = new ArrayList<Hole>();
	}
	
	@Override
	public void acceptEntity(Worker firstPusher, Crate c) {
		super.acceptEntity(c);
		for (Hole h : holes) {
			h.setOpen(true);
			if (!h.isEmpty()) {
				Entity e = h.getCurEntity().get();
				e.fallDown(firstPusher);
			}
		}
	}
	
	public void unsetEntity() {
		super.unsetEntity();
		for (Hole h : holes) {
			h.setOpen(false);
		}
	}

}
