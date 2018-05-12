package skeleton.test.commands;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import skeleton.model.LevelFormatException;
import skeleton.model.Warehouse;
import skeleton.test.Command;
import skeleton.test.TestEnvironment;
import skeleton.test.TestExecutionException;
import skeleton.view.IView;

public class LevelCommand implements Command {
	private String path;
	
	public LevelCommand(String path) {
		this.path = path;
	}
	
	@Override
	public void exec(TestEnvironment env, ArrayList<String> result) throws TestExecutionException {
		try {
			Warehouse wh = Warehouse.fromFile(path, new IView() {
				@Override
				public void sendMessage(Object msg) {
				}

				@Override
				public void receiveMessage(Object msg) {
				}
			});
			env.setLevel(wh);
			result.add("LEVEL_SUCCESS \"" + path + "\"");
		} catch (FileNotFoundException e) {
			result.add("LEVEL_FAIL \"" + path + "\"");
		} catch (LevelFormatException e) {
			throw new TestExecutionException();
		}
	}
}
