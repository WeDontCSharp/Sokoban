package skeleton.test.commands;

import java.util.ArrayList;

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
	public void exec(TestEnvironment env, ArrayList<String> result) throws TestExecutionException {
		Worker w = env.getLevel().getWorker(this.playerID);
		if (w == null) {
			throw new TestExecutionException();
		}
		w.move(this.direction);
		Process proc = w.getCurrentProcess();
		if (proc == null) {
			result.add("STEP_FAIL " + (playerID + 1) + " " + Direction.toChar(this.direction));
			return;
		}
		result.add("STEP_OK " + (playerID + 1) + " " + Direction.toChar(this.direction));
		if (sync) {
			while (!proc.isOver()) {
				env.getLevel().update();
			}
			env.getLevel().update();
		}
	}
}
