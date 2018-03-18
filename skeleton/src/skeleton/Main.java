package skeleton;

import java.util.Scanner;

import skeleton.meta.AutomaticQuestionHandler;
import skeleton.meta.PrettyPrinter;
import skeleton.meta.RecorderQuestionHandler;
import skeleton.model.Direction;
import skeleton.model.Floor;
import skeleton.model.Warehouse;
import skeleton.model.Worker;

/**
 * The starting-point for the application.
 */
public class Main {
	/**
	 * The input stream used for user input.
	 */
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {		
		int selection;
		
		do {	
			printMainMenu();
			selection = Integer.parseInt(in.nextLine());
			switch (selection) {
			case 1:
				workerMoves();
				break;
				
			case 2:
				cratePush();
				break;
				
			case 3:
				devMode();
				break;
				
			case 0:
				System.out.println("This is the end, my beautiful friend.");
				break;
				
			default:
				System.err.println("No such action!");
			}
						
		} while (selection != 0);
		
		in.close();
	}
	
	private static void printMainMenu() {
		System.out.println("\nWhat do you want to do? [Choose from 1 to 3 play, or 0 to exit]");
		System.out.println(" 1 - Move Worker");
		System.out.println(" 2 - Push Crate");
		System.out.println(" 3 - Create Own Sequence (Dev. Mode)");
		System.out.println(" ---");
		System.out.println(" 0 - Exit");
	}
	
	/**
	 * A syntax-sugar for string pairs.
	 * 
	 * @param mi First string.
	 * @param inp Second string.
	 * @return The string pair as an array.
	 */
	private static String[] pair(String mi, String inp) {
		return new String[] { mi, inp };
	}
	
	/**
	 * Asks for a submenu item.
	 * First it prints the provided question, then asks for a number for the answer list.
	 * It returns the corresponding argument from the string arrays. 
	 * 
	 * @param q The asked question.
	 * @param answ The answer string arrays.
	 * @return The corresponding answer string array.
	 * 
	 * @see workerMoves()
	 * @see cratePush()
	 */
	private static String[] getMenuItem(String q, String[]... answ) {
		System.out.println(" " + q + " [Choose from 1 to " + answ.length + "]");
		int n = 1;
		for (String[] p : answ) {
			System.out.println("  " + (n++) + " - " + p[0]);
		}
		int c = Integer.parseInt(in.nextLine()) - 1;
		if (c < 0 || c >= answ.length) {
			System.err.println("No such action!");
			return null;
		}
		return new String[] { (c + 1) + ".", answ[c][0], answ[c][1] };
	}
	
	/**
	 * Sets up a basic situation and asks for a specific worker moving sequence.
	 */
	private static void workerMoves() {
		String[] answ = getMenuItem("How do you want the worker to move?",
				
				pair("Move Worker On Empty Floor", 				"WMRFN"),
				pair("Move Worker On Empty Hole", 				"WMRHN#"),
				pair("Move Worker On Empty Target", 			"WMRTN"),
				pair("Move Worker On Empty Switch", 			"WMRSN"),
				pair("Move Worker On Wall", 					"WMRWN"),
				pair("Move Worker On Spawn", 					"WMRP#"),
				pair("Worker Pushes Worker", 					"WMRFYW"),
				pair("Worker Pushes Worker In Chain On Wall", 	"WMRFYCFYWWN"),
				pair("Worker Pushes Worker In Chain On Spawn", 	"WMRFYCFYWP#")
				
		);
		if (answ == null) {
			return;
		}
		
		PrettyPrinter.setQuestionHandler(new AutomaticQuestionHandler(in, answ[2]));
		PrettyPrinter.startSequence(answ[0], answ[1]);
		
		handleSituation();
		
		PrettyPrinter.endSequence();
	}
	
	/**
	 * Sets up a basic situation and asks for a specific crate pushing sequence.
	 */
	private static void cratePush() {
		String[] answ = getMenuItem("How do you want to push the crate?",
				
				pair("Worker Pushes Crate On Empty Floor", 		"WMRFYCFN"),
				pair("Worker Pushes Crate On Empty Hole", 		"WMRFYCHN#"),
				pair("Worker Pushes LifeCrate On Empty Hole", 	"WMRFYLHN#"),
				pair("Worker Pushes Crate On Empty Target", 	"WMRFYCTN"),
				pair("Worker Pushes Crate On Empty Switch", 	"WMRFYCSNN"),
				pair("Worker Pushes Crate On Wall", 			"WMRFYCWN"),
				pair("Worker Pushes Crate On Spawn", 			"WMRFYCP"),
				pair("Worker Pushes Crate From Switch", 		"WMRSYCFN"),
				pair("Worker Pushes Crate From Target", 		"WMRTYCFN")
				
		);
		if (answ == null) {
			return;
		}
		
		PrettyPrinter.setQuestionHandler(new AutomaticQuestionHandler(in, answ[2]));
		PrettyPrinter.startSequence(answ[0], answ[1]);
		
		handleSituation();
		
		PrettyPrinter.endSequence();
	}
	
	/**
	 * Runs a generic situation in developer mode, so the user has to provide every
	 * element of the situation manually.
	 */
	private static void devMode() {
		RecorderQuestionHandler rqh = new RecorderQuestionHandler();
		PrettyPrinter.setQuestionHandler(rqh);
		
		handleSituation();
		
		System.out.println("Your input was: " + rqh.getInput());
	}
	
	/**
	 * Sets up the generic situation that is used by every sequence.
	 */
	private static void handleSituation() {
		Warehouse wh = new Warehouse(0, 0);
		
		Floor emptyField = new Floor(wh);
		Character ch = PrettyPrinter.askQuestion(
				"What kind of situation? [W - Worker] : ", 
				"W", 
				new Character[] { 'W' }
		);
		if (ch == 'W') {
			Worker w = new Worker(wh, emptyField, Direction.Right);
			wh.addEntity(w, 0, 0);
			
			ch = PrettyPrinter.askQuestion(
					"What method to call? [M - move()] : ", 
					"M", 
					new Character[] { 'M' }
			);
			if (ch == 'M') {
				Direction dir = PrettyPrinter.askQuestion(
						"What direction to move? [L - left, R - right, U - up, D - down] : ", 
						"LRUD", 
						new Direction[] { Direction.Left, Direction.Right, Direction.Up, Direction.Down }
				);
				w.move(dir);
			}
			else {
				throw new RuntimeException("Unimplemented!");
			}
		}
		else {
			throw new RuntimeException("Unimplemented!");
		}
	}
}
