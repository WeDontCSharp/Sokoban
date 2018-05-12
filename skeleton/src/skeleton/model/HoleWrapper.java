package skeleton.model;
import skeleton.view.HoleStateChangeMessage;

public class HoleWrapper extends Hole {

	public HoleWrapper(Warehouse level) {
		super(level);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setOpen(boolean open) {
		super.setOpen(open);
		// XXX: Not sure if works like this.
		this.getLevel().receiveMessage(new HoleStateChangeMessage(this, open));
	}

}
