package skeleton.test.commands;

import java.util.ArrayList;
import java.util.List;

import skeleton.model.Entity;
import skeleton.model.Warehouse;
import skeleton.model.Worker;
import skeleton.test.Command;
import skeleton.test.TestEnvironment;
import skeleton.test.TestExecutionException;

public class LevelEndCommand implements Command {
	@Override
	public void exec(TestEnvironment env, ArrayList<String> result) throws TestExecutionException {
		Warehouse wh = env.getLevel();
		if (wh == null) {
			throw new TestExecutionException();
		}
		
		if(wh.getEnd() != Warehouse.EndType.Nothing) {
			System.out.println("LEVEL_END "+Warehouse.EndType.toString(wh.getEnd()));
		}
	}

}
