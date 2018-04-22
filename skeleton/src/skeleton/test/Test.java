package skeleton.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Test {
	
	int id;
	
	public int getId() {
		return id;
	}

	private String name;
	
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	private TestEnvironment environment;
	private ArrayList<Command> commands;
	
	private ArrayList<String> result = new ArrayList<String>();
	private ArrayList<String> expected = new ArrayList<String>();
	
	public Test(String name, ArrayList<Command> cmds) {
		this.name = name;
		this.environment = new TestEnvironment();
		this.commands = cmds;
	}
	
	public boolean run() {
		try {
			for (Command cmd : commands) {
				cmd.exec(this.environment, this.result);
			}
			
			// XXX: Reading the excepted results here.			
			try {
				Path path = Paths.get("tst_out_" + name + ".txt");
				Stream<String> lines = Files.lines(path);
	            lines.forEach(s -> expected.add(s));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// XXX: Comparing the result and the expected result and writing some shit to the user here.
			if (result.size() != expected.size()) {
				// XXX: Such a big error that we should handle differently...
				System.out.println("TEST FAILED: The amount of output lines differ from the expected result...");
				for (String s : result) {
					System.out.println(s);
				}
				System.out.println("--");
				for (String s : expected) {
					System.out.println(s);
				}
				return false;
			}
			
			boolean failed = false;
			
			for (int i = 0; i < result.size(); ++i) {
				if (!result.get(i).equals(expected.get(i))){
					failed = true;
				}
			}
			
			if (!failed) {
				System.out.println("TEST SUCCESSFULLY FINISHED!");
				return true;
			} else {
				for (int i = 0; i < result.size(); ++i) {
					if (!result.get(i).equals(expected.get(i))){
						System.out.println(expected.get(i) + " != " + result.get(i));
					} else {
						System.out.println(expected.get(i) + " == " + result.get(i));
					}
				}
			}			
			
		}
		catch (TestExecutionException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
}
