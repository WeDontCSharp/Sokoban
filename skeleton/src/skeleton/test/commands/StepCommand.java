package skeleton.test.commands;

import skeleton.model.Direction;
import skeleton.model.Worker;
import skeleton.model.Process;
import skeleton.test.Command;
import skeleton.test.TestEnvironment;
import skeleton.test.TestExecutionException;

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
	public void exec(TestEnvironment env) throws TestExecutionException {
		Worker w = env.getLevel().getWorker(this.playerID);
		if (w == null) {
			throw new TestExecutionException();
		}
		w.move(this.direction);
		Process proc = w.getCurrentProcess();
		if (proc == null) {
			System.out.println("STEP_FAIL " + (playerID + 1) + " " + Direction.toChar(this.direction));
			return;
		}
		System.out.println("STEP_OK " + (playerID + 1) + " " + Direction.toChar(this.direction));
		if (sync) {
			while (!proc.isOver()) {
				env.getLevel().update();
			}
		}
	}
}
