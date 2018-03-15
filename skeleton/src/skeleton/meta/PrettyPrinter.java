package skeleton.meta;

public abstract class PrettyPrinter {
	private static IQuestionHandler questionHandler;
	private static int indent = 0;
	
	public static void setQuestionHandler(IQuestionHandler qh) {
		questionHandler = qh;
	}
	
	private static void startIndent() {
		++indent;
	}
	
	private static void endIndent() {
		--indent;
	}
	
	private static void indentate() {
		for (int i = 0; i < indent; ++i) {
			System.out.print("    ");
		}
	}
	
	public static void startSequence(String seqNum, String seqName) {
		indentate();
		System.out.println(seqNum + " " + seqName);
		startIndent();
	}
	
	public static void endSequence() {
		endIndent();
	}
	
	public static void printText(String q) {
		indentate();
		System.out.print(q);
	}
	
	public static void startFunction(String className, String sign) {
		indentate();
		System.out.println("-> " + className + "::" + sign + ":");
		startIndent();
	}
	
	public static void endFunction(String className, String sign) {
		endIndent();
		indentate();
		System.out.println("<- " + className + "::" + sign);
	}
	
	public static void endFunction(String className, String sign, String ret) {
		endIndent();
		indentate();
		System.out.println(ret + " <- " + className + "::" + sign);
	}
	
	public static <T> T askQuestion(String q, char[] chtbl, T[] anwtbl) {
		if (questionHandler.visible()) {
			indentate();
			System.out.print(q);
		}
		return questionHandler.chooseOneQuestion(chtbl, anwtbl);
	}
	
	public static <T> T askQuestion(String q, String chtbl, T[] anwtbl) {
		return askQuestion(q, chtbl.toCharArray(), anwtbl);
	}
}
