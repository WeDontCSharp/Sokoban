package skeleton.test.commands;

import java.util.ArrayList;

import skeleton.model.PlaceableItem;
import skeleton.model.Worker;
import skeleton.test.Command;
import skeleton.test.TestEnvironment;
import skeleton.test.TestExecutionException;

public class PlayerInfoCommand implements Command {
	private int playerID;
	private String type;

	public PlayerInfoCommand(int pid, String type) {
		this.playerID = pid;
		this.type = type;
	}

	@Override
	public void exec(TestEnvironment env, ArrayList<String> result) throws TestExecutionException {
		Worker w = env.getLevel().getWorker(this.playerID);
		if (w == null) {
			throw new TestExecutionException();
		}
		if (type.equals("hp")) {
			//System.out.println("PLAYER_HP " + (playerID + 1) + " " + w.getHealth());
			result.add("PLAYER_HP " + (playerID + 1) + " " + w.getHealth());
			return;
		}
		if (type.equals("pts")) {
			//System.out.println("PLAYER_PTS " + (playerID + 1) + " " + w.getPoints());
			result.add("PLAYER_PTS " + (playerID + 1) + " " + w.getPoints());
			return;
		}
		if (type.equals("item")) {
			//System.out.println("PLAYER_ITEM " + (playerID + 1) + " " + PlaceableItem.toChar(w.getItem()));
			result.add("PLAYER_ITEM " + (playerID + 1) + " " + PlaceableItem.toChar(w.getItem()));
			return;
		}
	}
}
