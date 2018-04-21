package skeleton;

import java.io.IOException;

import skeleton.test.InputLanguageException;
import skeleton.test.Test;
import skeleton.test.TestReader;

/**
 * The starting-point for the application.
 */
public class Main {
	
	public static void main(String[] args) {		
		try {
			Test t = TestReader.fromFile("test.txt");
			t.run();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InputLanguageException e) {
			e.printStackTrace();
		}
	}
	
}
