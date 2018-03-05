package skeleton;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// An example for simple decision-making
		/*
		if (<something is true>) {
			print "Hooray!";
		}
		else {
			print "Boo!";
		}
		*/
		
		Scanner scanner = new Scanner(System.in);
		
		class InputCondition implements ICondition {
			@Override
			public boolean evaluate() {
				System.out.println("Do you want the expression to be true? (y/n)");
				String answer = scanner.nextLine().trim();
				if (answer.length() > 0) {
					return Character.toLowerCase(answer.charAt(0)) == 'y';
				}
				return false;
			}
		}
		
		class PrintStatement implements IStatement {
			private String text;
			
			public PrintStatement(String text) {
				this.text = text;
			}
			
			@Override
			public void execute() {
				System.out.println(text);
			}
		}
		
		new AlternativeStatement(
				new InputCondition(),
				new PrintStatement("Hooray!"),
				new PrintStatement("Boo!")
		).execute();
		
		scanner.close();
	}
}
