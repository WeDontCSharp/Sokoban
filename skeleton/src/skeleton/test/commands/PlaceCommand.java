package skeleton.test.commands;

import java.util.ArrayList;

import skeleton.model.Worker;
import skeleton.test.Command;
import skeleton.test.TestEnvironment;
import skeleton.test.TestExecutionException;

public class PlaceCommand implements Command {
	private int playerID;

	public PlaceCommand(int pid) {
		this.playerID = pid;
	}

	@Override
	public void exec(TestEnvironment env, ArrayList<String> result) throws TestExecutionException {
		Worker w = env.getLevel().getWorker(this.playerID);
		if (w == null) {
			throw new TestExecutionException();
		}
		if (w.placeItem()) {
			result.add("PLACE_OK " + (playerID + 1));
		} else {
			result.add("PLACE_FAIL " + (playerID + 1));
		}
	}
}
