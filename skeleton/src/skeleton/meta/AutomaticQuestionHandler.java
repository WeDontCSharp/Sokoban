package skeleton.meta;

public class AutomaticQuestionHandler implements IQuestionHandler {
	private int index;
	private char[] answers;
	
	public AutomaticQuestionHandler(char... answ) {
		this.index = 0;
		this.answers = answ;
	}
	
	public AutomaticQuestionHandler(String str) {
		this.index = 0;
		this.answers = str.toCharArray();
	}
	
	@Override
	public boolean visible() {
		return false;
	}

	@Override
	public <T> T chooseOneQuestion(char[] chtbl, T[] answ) {
		char ch = answers[index++];
		for (int i =  0; i < chtbl.length; i++) {
			if (chtbl[i] == ch) {
				return answ[i];
			}
		}
		assert(false);
		return null;
	}
	
}
