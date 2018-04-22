package skeleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import skeleton.test.InputLanguageException;
import skeleton.test.Test;
import skeleton.test.TestReader;

/**
 * The starting-point for the application.
 */
public class Main {
	
	private static ArrayList<Test> tests = new ArrayList<Test>();
	
	public static void main(String[] args) {
		addTests();
		displayMenu();
		
		/*try {
			Test t = TestReader.fromFile("test.txt");
			t.run();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InputLanguageException e) {
			e.printStackTrace();
		}*/
	}
	
	public static void addTests() {
		
		ArrayList<String> src = new ArrayList<String>();
		
		// XXX: Add tests here with their name only!
		//MENNEK:
		src.add("Move worker on empty field");
		src.add("Worker pushes crate to empty field");
		src.add("Worker pushes other worker directly");
		src.add("Worker pushes other worker in chain");
		src.add("Worker smashed by wall");
		src.add("Worker smashed by spawn");
		src.add("Worker falls into hole");
		src.add("Worker pushes lifecrate into hole");
		
		//NEM MENNEK:  (CNT PROBLEMS)
		src.add("Target activation test");
		src.add("Target deactivation test");
		
		//MENNEK:
		src.add("Switch activation test");
		src.add("Switch deactivation test");
		src.add("Field modificator test - oil");
		src.add("Field modificator test - honey");
		src.add("Locking mechanism of simultaneously pushed conflicting chains");
		
		//NEM MENNEK: (ENDING PROBLEMS)
		src.add("Game ends because there is a crate on all targets");
		src.add("Game ends because there are no moveable crates");
		src.add("Game ends because all the workers died except one");
		
		//MENNEK:
		src.add("Player gains an item");
		src.add("Worker puts down item");
		
		try {
			Test t = null;
			for (int i = 0; i < src.size(); ++i) {
				t = TestReader.fromFile("tst_in_"+ src.get(i) +".txt");
				t.setId(i+1);
				t.setName(src.get(i));
				tests.add(t);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InputLanguageException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void displayMenu() {
		System.out.println("\n-----");
		System.out.println("Choose from the tests below [0-"+ tests.size() +"], or hit Q to quit!\n");
		
		System.out.println("[0] RUN ALL TESTS");
		
		for (Test t : tests) {
			System.out.println("["+ t.getId() +"] " + t.getName());
		}
		
		System.out.println("\n[Q] Quit");
		
		readInput();
	}
	
	public static void readInput() {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		
		try {
			loop: while (true) {
				line = br.readLine();
				
				if (line.trim().equals("Q") || line.trim().equals("q")) {
					System.out.println("So early to quIIT...");
					break loop;
				}
				
				try {
					int num = Integer.parseInt(line.trim());
					
					if (num < 0 || num > tests.size()) {
						System.out.println("It's not that hard to give a number from 0 to " + tests.size() + "... Let's try it again.");
					} else if (num == 0) {	
						
						for (int i = 0; i < tests.size(); ++i) {
							System.out.println("\nRunning test: [" + tests.get(i).getId() + "] " + tests.get(i).getName() + "...");
							tests.get(i).run();
						}
						
					} else {
						System.out.println("\nRunning test: [" + tests.get(num - 1).getId() + "] " + tests.get(num - 1).getName() + "...");
						tests.get(num - 1).run();
					}
				
				} catch(NumberFormatException e) {
					System.out.println("It's not that hard to give a number from 0 to " + tests.size() + "... Let's try it again.");
					
				}
				
				displayMenu();
			}
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
