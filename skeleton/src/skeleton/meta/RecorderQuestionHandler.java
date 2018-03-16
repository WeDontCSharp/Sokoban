package skeleton.meta;

import java.util.Scanner;

/**
 * @author Peter Lenkefi
 *
 * This answering strategy is used when in developer mode. Every question is asked and answered explicitly.
 * All the answers are recorded and can be used as answers for a new sequence with an automatic question
 * handler. If a question is needed to be answered by the user in automatic mode too, a '#' can be used
 * before the actual answer.
 * 
 * @see AutomaticQuestionHandler
 */
public class RecorderQuestionHandler implements IQuestionHandler {
	/**
	 * The input stream to ask questions from.
	 */
	private Scanner in = new Scanner(System.in);
	/**
	 * The recorded input sequence.
	 */
	private String input = "";
	
	@Override
	public boolean visible() {
		return true;
	}
	
	@Override
	public <T> T chooseOneQuestion(char[] chtbl, T[] answ) {
		char ch = Character.toUpperCase(in.nextLine().trim().charAt(0));
		input += ch;
		if (ch == '#') {
			ch = Character.toUpperCase(in.nextLine().trim().charAt(0));
		}
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
	
	/**
	 * Gets the recorded input sequence.
	 * 
	 * @return The recorded input sequence.
	 */
	public String getInput() {
		return input;
	}
}
