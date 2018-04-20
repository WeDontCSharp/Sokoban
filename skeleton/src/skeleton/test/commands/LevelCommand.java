package skeleton.test.commands;

import java.io.FileNotFoundException;

import skeleton.model.LevelFormatException;
import skeleton.model.Warehouse;
import skeleton.test.Command;
import skeleton.test.TestEnvironment;
import skeleton.test.TestExecutionException;

public class LevelCommand implements Command {
	private String path;
	
	public LevelCommand(String path) {
		this.path = path;
	}
	
	@Override
	public void exec(TestEnvironment env) throws TestExecutionException {
		try {
			Warehouse wh = Warehouse.fromFile(path);
			env.setLevel(wh);
			System.out.println("LEVEL_SUCCESS \"" + path + "\"");
		} catch (FileNotFoundException e) {
			System.out.println("LEVEL_FAIL \"" + path + "\"");
		} catch (LevelFormatException e) {
			throw new TestExecutionException();
		}
	}
}
