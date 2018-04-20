package skeleton.test.commands;

import skeleton.model.Direction;
import skeleton.test.Command;
import skeleton.test.TestEnvironment;

public class StepCommand implements Command {
	private boolean sync;
	private int playerID;
	private Direction direction;

	public StepCommand(boolean sync, int pid, Direction d) {
		this.sync = sync;
		this.playerID = pid;
		this.direction = d;
	}

	@Override
	public void exec(TestEnvironment env) {
		// XXX
	}
}
