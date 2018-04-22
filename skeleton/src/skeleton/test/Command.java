package skeleton.test;

import java.util.ArrayList;

/**
 * Represents a single instruction in the input language.
 */
public interface Command {
	/**
	 * Called when the command for the test is executed.
	 * 
	 * @param env The test environment the command is currently in.
	 * @throws TestExecutionException Thrown when a runtime error happens.
	 */
	public void exec(TestEnvironment env, ArrayList<String> result) throws TestExecutionException;
}
