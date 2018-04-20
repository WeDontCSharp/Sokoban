package skeleton.test;

import java.util.ArrayList;

public class Test {
	private String name;
	private TestEnvironment environment;
	private ArrayList<Command> commands;
	
	public Test(String name, ArrayList<Command> cmds) {
		this.name = name;
		this.environment = new TestEnvironment();
		this.commands = cmds;
	}
	
	public void run() {
		try {
			for (Command cmd : commands) {
				cmd.exec(this.environment);
			}
			/*
			while (true) {
				this.environment.getLevel().update();
			}
			*/
		}
		catch (TestExecutionException ex) {
			ex.printStackTrace();
		}
	}
}
