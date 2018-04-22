package skeleton.test.commands;

import skeleton.model.Warehouse;
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
	public void exec(TestEnvironment env) throws TestExecutionException {
		Hole h = env.getLevel().getField(this.posX, this.posY);
		if (h == null) {
			throw new TestExecutionException();
		}
		if (h.IsOpen()){
			System.out.println("OPEN " + posX + "," + posY);
			return;
		} else {
			System.out.println("CLOSED " + posX + "," + posY);
			return;
		}
	}
}
