package skeleton.test;

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
	public void exec(TestEnvironment env) throws TestExecutionException;
}
