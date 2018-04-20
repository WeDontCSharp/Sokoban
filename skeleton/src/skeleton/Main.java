package skeleton;

import java.io.IOException;

import skeleton.test.TestReader;

/**
 * The starting-point for the application.
 */
public class Main {
	
	public static void main(String[] args) {		
		try {
			TestReader.fromFile("C:/TMP/sokoban_tests.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
