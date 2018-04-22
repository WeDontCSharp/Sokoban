package skeleton.test.commands;

import java.util.ArrayList;

import skeleton.model.PlaceableItem;
import skeleton.model.Worker;
import skeleton.test.Command;
import skeleton.test.TestEnvironment;
import skeleton.test.TestExecutionException;

public class GiveCommand implements Command {
	private int playerID;
	private String item;

	public GiveCommand(int pid, String item) {
		this.playerID = pid;
		this.item = item;
	}

	@Override
	public void exec(TestEnvironment env, ArrayList<String> result) throws TestExecutionException {
		Worker w = env.getLevel().getWorker(this.playerID);
		if (w == null) {
			throw new TestExecutionException();
		}
		if (item.equals("h")) {
			w.setItem(PlaceableItem.Honey);
			return;
		}
		if (item.equals("o")) {
			w.setItem(PlaceableItem.Oil);
			return;
		}
		if (item.equals("n")) {
			w.setItem(PlaceableItem.Nothing);
			return;
		}
	}
}
