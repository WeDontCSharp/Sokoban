package skeleton.meta;

/**
 * This is the common interface for the question handling and answer input strategies we define.
 * The pretty printer uses this to ask questions from the user. Mainly used to separate developer and user mode.
 * 
 * @see PrettyPrinter
 */
public interface IQuestionHandler {
	/**
	 * Asks if the question should be printed to stdout.
	 * 
	 * @return True, if the question should be visible to the user.
	 */
	public boolean visible();
	
	
	/**
	 * This asks the user a choose one question and decides which one he has chosen.
	 * The function provides a generic interface to immediately get back an object as an answer 
	 * instead of a character-based answer.
	 * The actual question printing is not done by this method, it's done by the PrettyPrinter.
	 * 
	 * If the user chose the i-th character in the chtbl, then the returned object is the i-th one
	 * from the answ array.
	 * 
	 * @param chtbl The choice character table. These are the choices the user has.
	 * @param answ The generic answer table that contains the answer objects that the user chooses from.
	 * @return The corresponding answer object to the chosen answer character.
	 * 
	 * @see PrettyPrinter
	 */
	public <T> T chooseOneQuestion(char[] chtbl, T[] answ);
}
