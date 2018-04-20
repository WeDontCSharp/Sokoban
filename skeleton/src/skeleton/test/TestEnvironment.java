package skeleton.test;

import skeleton.model.Warehouse;

/**
 * This is a container class that shares information between commands, like what level is loaded, ect.
 */
public class TestEnvironment {
	private Warehouse level;
	
	public void setLevel(Warehouse wh) {
		this.level = wh;
	}
}
