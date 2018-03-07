package skeleton.meta;

import java.util.Scanner;

public abstract class PrettyPrinter {
	private static Scanner in = new Scanner(System.in);
	private static int indent = 0;
	
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
		System.out.println("<- " + className + "::" + sign + ":");
	}
	
	public static void endFunction(String className, String sign, String ret) {
		endIndent();
		indentate();
		System.out.println(ret + " <- " + className + "::" + sign + ":");
	}
	
	public static String getAnswer() {
		return in.nextLine().trim();
	}
}
