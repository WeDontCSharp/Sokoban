package skeleton.meta;

public interface IQuestionHandler {
	public boolean visible();
	public <T> T chooseOneQuestion(char[] chtbl, T[] answ);
}
