package skeleton.meta;

import java.util.Scanner;

/**
 * @author Peter Lenkefi
 *
 * This question handling strategy is used when the user chooses the non-developer mode where the
 * sequences are (mostly) automatic. The only exception is when a '#' is encountered in the input sequence,
 * in that case the question needs to be answered manually.
 * By default this question handler is hidden from the user (no output to stdout) but is visible when
 * manual input is needed.
 * 
 * A predefined input sequence is set for this strategy that is fed for the asked questions.
 * 
 * @see IQuestionHandler
 */
public class AutomaticQuestionHandler implements IQuestionHandler {
	/**
	 * The input stream used when manual input is required.
	 */
	private Scanner in;
	/**
	 * The index that shows where we are in the predefined input sequence.
	 */
	private int index;
	/**
	 * The predefined input sequence.
	 */
	private char[] answers;
	
	/**
	 * @param in The input stream for manual input handling.
	 * @param answ The predefined input sequence to use.
	 */
	public AutomaticQuestionHandler(Scanner in, char... answ) {
		this.in = in;
		this.index = 0;
		this.answers = answ;
	}
	
	/**
	 * @param in The input stream for manual input handling.
	 * @param str The predefined input sequence to use in string form. Every character is an answer.
	 */
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
