package skeleton.test.commands;

import skeleton.model.Warehouse;

import java.util.ArrayList;

import skeleton.model.Hole;
import skeleton.test.Command;
import skeleton.test.TestEnvironment;
import skeleton.test.TestExecutionException;

public class HoleInfoCommand implements Command {
	private int posX;
	private int posY;

	public HoleInfoCommand(int x, int y) {
		this.posX = x;
		this.posY = y;
	}

	@Override
	public void exec(TestEnvironment env, ArrayList<String> result) throws TestExecutionException {
		Hole h = (Hole) env.getLevel().getField(this.posX, this.posY);
		if (h == null) {
			throw new TestExecutionException();
		}
		if (h.isOpen()){
			result.add("OPEN " + posX + "," + posY);
			return;
		} else {
			result.add("CLOSED " + posX + "," + posY);
			return;
		}
	}
}
