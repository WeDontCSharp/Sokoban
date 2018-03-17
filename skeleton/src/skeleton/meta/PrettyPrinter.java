package skeleton.meta;

/**
 * A sort-of singleton for pretty-printing sequences and asking choose-one questions from
 * question handling strategies.
 * 
 * @see IQuestionHandler
 */
public abstract class PrettyPrinter {
	/**
	 * The current question handling strategy.
	 */
	private static IQuestionHandler questionHandler;
	/**
	 * The current indentation.
	 */
	private static int indent = 0;
	/**
	 * A flag that tells if we should not print any sequence-related text.
	 * Used for initialization when we don't want to litter stdout with init sequences.
	 */
	private static boolean silent = true;
	
	/**
	 * Sets the current question handling strategy.
	 * 
	 * @param qh The question handling strategy to use.
	 */
	public static void setQuestionHandler(IQuestionHandler qh) {
		questionHandler = qh;
	}
	
	/**
	 * Increases the indentation by a unit.
	 */
	private static void startIndent() {
		++indent;
	}
	
	/**
	 * Decreases the indentation by a unit.
	 */
	private static void endIndent() {
		--indent;
	}
	
	/**
	 * Prints spaces according to the current indentation.
	 * This needs to be called before every indentated line.
	 */
	private static void indentate() {
		for (int i = 0; i < indent; ++i) {
			System.out.print("    ");
		}
	}
	
	/**
	 * Starts a sequence with a given number and name.
	 * Indents everything afterwards.
	 * Needs to be called before every call in the sequence.
	 * 
	 * Example:
	 *  startSequence("1", "DoStuff");
	 *  // Do the sequence
	 *  endSequence();
	 * 
	 * @param seqNum The number of the sequence, just aesthetics.
	 * @param seqName The name of the sequence.
	 * 
	 * @see endSequence()
	 */
	public static void startSequence(String seqNum, String seqName) {
		silent = false;
		indentate();
		System.out.println(seqNum + " " + seqName);
		startIndent();
	}
	
	/**
	 * Ends a sequence, undoes the indentation. Needs to be called at the end of the
	 * sequence when no more calls are printed from that sequence.
	 * 
	 * Example:
	 *  startSequence("1", "DoStuff");
	 *  // Do the sequence
	 *  endSequence();
	 *  
	 *  @see startSequence()
	 */
	public static void endSequence() {
		endIndent();
		silent = true;
	}
	
	/**
	 * Prints a piece of text indentated if the silent flag is not set.
	 * No newline is appended.
	 * 
	 * @param q The text to print
	 */
	public static void printText(String q) {
		if (silent) {
			return;
		}
		indentate();
		System.out.print(q);
	}
	
	/**
	 * Prints the start of the function of a given class with a given signature
	 * if the silent flag is not set.
	 * Needs to be called before every call in the function.
	 * 
	 * Example:
	 *  startFunction("Foo", "bar(x, y)");
	 *  // Things the function does
	 *  endFunction("Foo", "bar(x, y)" [, "returnValue"]);
	 * 
	 * @param className The class name the function is called from. 
	 * @param sign The signature of the called function.
	 * 
	 * @see endFunction()
	 */
	public static void startFunction(String className, String sign) {
		if (silent) {
			return;
		}
		indentate();
		System.out.println("-> " + className + "::" + sign + ":");
		startIndent();
	}
	
	/**
	 * Prints the end of the function of a given class with a given signature
	 * if the silent flag is not set.
	 * Needs to be called after every call in the function.
	 * 
	 * Example:
	 *  startFunction("Foo", "bar(x, y)");
	 *  // Things the function does
	 *  endFunction("Foo", "bar(x, y)");
	 * 
	 * @param className The class name the function is called from. 
	 * @param sign The signature of the called function.
	 * 
	 * @see startFunction()
	 */
	public static void endFunction(String className, String sign) {
		if (silent) {
			return;
		}
		endIndent();
		indentate();
		System.out.println("<- " + className + "::" + sign);
	}
	
	/**
	 * Prints the end of the function of a given class with a given signature
	 * and return value if the silent flag is not set.
	 * Needs to be called after every call in the function.
	 * 
	 * Example:
	 *  startFunction("Foo", "bar(x, y)");
	 *  // Things the function does
	 *  endFunction("Foo", "bar(x, y)", "retValue");
	 * 
	 * @param className The class name the function is called from. 
	 * @param sign The signature of the called function.
	 * @param ret The return value of the function.
	 * 
	 * @see startFunction()
	 */
	public static void endFunction(String className, String sign, String ret) {
		if (silent) {
			return;
		}
		endIndent();
		indentate();
		System.out.println(ret + " <- " + className + "::" + sign);
	}
	
	/**
	 * Asks a choose-one question based on the current question-handling strategy.
	 * Only prints the question if the user needs to answer it manually.
	 * 
	 * If the answer in chtbl is the i-th character, then the returned answer object is
	 * the i-th object in the anwtbl.
	 * 
	 * @param q The asked question.
	 * @param chtbl The answer character table.
	 * @param anwtbl The answer object list.
	 * @return The corresponding answer object to the answer character. 
	 */
	public static <T> T askQuestion(String q, char[] chtbl, T[] anwtbl) {
		if (questionHandler.visible()) {
			indentate();
			System.out.print(q);
		}
		return questionHandler.chooseOneQuestion(chtbl, anwtbl);
	}
	
	/**
	 * Asks a choose-one question based on the current question-handling strategy.
	 * Only prints the question if the user needs to answer it manually.
	 * 
	 * If the answer in chtbl is the i-th character, then the returned answer object is
	 * the i-th object in the anwtbl.
	 * 
	 * @param q The asked question.
	 * @param chtbl The answer character table as a string. Every character is an answer to a question.
	 * @param anwtbl The answer object list.
	 * @return The corresponding answer object to the answer character. 
	 */
	public static <T> T askQuestion(String q, String chtbl, T[] anwtbl) {
		return askQuestion(q, chtbl.toCharArray(), anwtbl);
	}
}
