package skeleton.test;

import skeleton.model.Direction;

/**
 * Represents a single instruction in the input language.
 */
public interface Command {
	/**
	 * Called when the command for the test is executed.
	 * 
	 * @param env The test environment the command is currently in.
	 */
	public void exec(TestEnvironment env);
	
	
	public static class Step implements Command {
		private boolean sync;
		private int playerID;
		private Direction direction;
		
		public Step(boolean sync, int pid, Direction d) {
			this.sync = sync;
			this.playerID = pid;
			this.direction = d;
		}

		@Override
		public void exec(TestEnvironment env) {
			
		}
	}
}
