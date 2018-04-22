package skeleton.test.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import skeleton.model.Warehouse;
import skeleton.test.Command;
import skeleton.test.TestEnvironment;
import skeleton.test.TestExecutionException;

public class LoadCommand implements Command {
	private String path;
	
	public LoadCommand(String path) {
		this.path = path;
	}
	
	@Override
	public void exec(TestEnvironment env) throws TestExecutionException {		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
        try {
        	fis = new FileInputStream(path);
        	ois = new ObjectInputStream(fis);
			env.setLevel((Warehouse) ois.readObject());
			
        } catch (FileNotFoundException e) {
			System.out.println("LOAD_FAIL \"" + path + "\"");
		} catch (ClassNotFoundException e) {
			System.out.println("LOAD_FAIL \"" + path + "\"");
		} catch (IOException e) {
			System.out.println("LOAD_FAIL \"" + path + "\"");
		}
        
        System.out.println("LOAD_SUCCESS \"" + path + "\"");
		
	}
}
