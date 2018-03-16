package skeleton.meta;

import java.util.Scanner;

public class AutomaticQuestionHandler implements IQuestionHandler {
	private Scanner in;
	private int index;
	private char[] answers;
	
	public AutomaticQuestionHandler(Scanner in, char... answ) {
		this.in = in;
		this.index = 0;
		this.answers = answ;
	}
	
	public AutomaticQuestionHandler(Scanner in, String str) {
		this.in = in;
		this.index = 0;
		this.answers = str.toCharArray();
	}
	
	@Override
	public boolean visible() {
		if (index >= answers.length) {
			return false;			
		}
		return answers[index] == '#';
	}

	@Override
	public <T> T chooseOneQuestion(char[] chtbl, T[] answ) {
		char ch;
		if (visible()) {
			ch = Character.toUpperCase(in.nextLine().trim().charAt(0));			
		}
		else {
			ch = answers[index++];
		}
		
		for (int i =  0; i < chtbl.length; i++) {
			if (chtbl[i] == ch) {
				return answ[i];
			}
		}
		assert(false);
		return null;
	}
	
}
