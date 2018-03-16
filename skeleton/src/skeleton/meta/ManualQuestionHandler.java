package skeleton.meta;

import java.util.Scanner;

public class ManualQuestionHandler implements IQuestionHandler {
	private Scanner in;
	
	public ManualQuestionHandler(Scanner in) {
		this.in = in;
	}
	
	@Override
	public boolean visible() {
		return true;
	}
	
	@Override
	public <T> T chooseOneQuestion(char[] chtbl, T[] answ) {
		char ch = Character.toUpperCase(in.nextLine().trim().charAt(0));
		for (int i =  0; i < chtbl.length; i++) {
			if (chtbl[i] == ch) {
				return answ[i];
			}
		}
		return null;
	}

}
