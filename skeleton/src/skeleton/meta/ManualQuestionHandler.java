package skeleton.meta;

import java.util.Scanner;

public class ManualQuestionHandler implements IQuestionHandler {
	private Scanner in = new Scanner(System.in);
	
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
	
	@Override
	public void finalize() {
		in.close();
	}

}
