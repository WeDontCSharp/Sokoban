package skeleton.model;

import java.util.ArrayList;
import java.util.List;

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
	}
    
    /**
	 * Sets the slipperiness of a switch.
	 * @param slipFactor The new value that replaces the current factor.
	 * @return False, because the slipFactor of a switch can never be changed.
	 */
	public boolean placeSlipFactor(double slipFactor) {
		return false;
	}
	
    /* (non-Javadoc)
     * @see skeleton.model.Floor#visitByCrate(skeleton.model.Worker, skeleton.model.Crate)
     */
    @Override
	public boolean visitByCrate(Worker firstPusher, Crate c) {
		super.setEntity(c);
		for (Hole h : holes) {
			h.setOpen(true);
			if (!h.isEmpty()) {
				Entity e = h.getEntity();
				e.visit(firstPusher, h);
			}
		}
		return true;
	}
	
	
    /* (non-Javadoc)
     * @see skeleton.model.Field#unsetEntity()
     */
    @Override
	public void unsetEntity() {
		super.unsetEntity();
		for (Hole h : holes) {
			h.setOpen(false);
		}
	}
    
    public void addHole(Hole h) {
    	this.holes.add(h);
    	h.setOpen(false);
    }
    
    public void closeHoles() {
    	for (Hole h : holes) {
			h.setOpen(false);
		}
    }

}
