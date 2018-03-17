package skeleton.model;

import java.util.ArrayList;
import java.util.List;

import skeleton.meta.PrettyPrinter;

/**
 * A class representing a switch. Switches are always
 * reachable by entities. Switches control holes
 * and they are only activated by crates.
 */
public class Switch extends Floor {
	
	/**
     * The holes associated with the switch, which it can control.
     */
    private List<Hole> holes;

	/**
	 * Creates a switch.
	 * @param level The warehouse to the create the switch in.
	 */
    public Switch(Warehouse level) {
		super(level);
		holes = new ArrayList<Hole>();
		
		// XXX: For now we add a hole
		holes.add(new Hole(level));
	}
	
	/**
	 * Tries to step a crate onto the switch.
     * Puts the crate on the switch, opens the assocaited holes and has all entities that stand on them fall down.
	 * @param firstPusher The worker who initiates the push.
     * @param c The crate being pushed onto the switch.
     * @return True, as the crate is put on the swich.
	 */
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
	
	
    /**
	 * When the crate is removed from the switch, all associated holes close.
	 */
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
