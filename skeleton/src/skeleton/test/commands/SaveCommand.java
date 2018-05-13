package skeleton.test.commands;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import skeleton.model.Hole;
import skeleton.model.LevelFormatException;
import skeleton.model.Warehouse;
import skeleton.test.Command;
import skeleton.test.TestEnvironment;
import skeleton.test.TestExecutionException;

public class SaveCommand implements Command {
	private String path;
	
	public SaveCommand(String path) {
		this.path = path;
	}
	
	@Override
	public void exec(TestEnvironment env, ArrayList<String> result) throws TestExecutionException {
		Warehouse w = env.getLevel();
		if (w == null) {
			throw new TestExecutionException();
		}
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		
		try {
			fout = new FileOutputStream(path);
		} catch (FileNotFoundException e) {
			result.add("SAVE_FAIL \"" + path + "\"");
			return;
		}
		
		try {
			oos = new ObjectOutputStream(fout);
			oos.writeObject(w);
			fout.close();
			oos.close();
		} catch (IOException e) {
			result.add("SAVE_FAIL \"" + path + "\"");
			return;
		}
		
		result.add("SAVE_SUCCESS \"" + path + "\"");
	}
}
