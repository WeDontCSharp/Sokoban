package skeleton;

import java.util.Scanner;

import skeleton.meta.AutomaticQuestionHandler;
import skeleton.meta.PrettyPrinter;
import skeleton.meta.RecorderQuestionHandler;
import skeleton.model.Direction;
import skeleton.model.Floor;
import skeleton.model.Warehouse;
import skeleton.model.Worker;

public class Main {
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		//PrettyPrinter.setQuestionHandler(new ManualQuestionHandler());
		RecorderQuestionHandler rqh = new RecorderQuestionHandler();
		PrettyPrinter.setQuestionHandler(rqh);
		
		System.out.println("What do you want to do?");
		System.out.println(" 1 - Move Worker");
		System.out.println(" 2 - Push Crate");
		System.out.println(" 3 - Create Own Sequence (Dev. Mode)");
		
		switch (Integer.parseInt(in.nextLine())) {
		case 1:
			workerMoves();
			break;
			
		case 2:
			cratePush();
			break;
			
		case 3:
			devMode();
			break;
			
		default:
			System.err.println("No such action!");
		}
		
		in.close();
	}
	
	private static String[] pair(String mi, String inp) {
		return new String[] { mi, inp };
	}
	
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
	
	private static void workerMoves() {
		String[] answ = getMenuItem("How do you want the worker to move?",
				
				pair("Move Worker On Empty Floor", 				"WMRFN"),
				pair("Move Worker On Empty Hole", 				"WMRHN#"),
				pair("Move Worker On Empty Target", 			""),
				pair("Move Worker On Empty Switch", 			""),
				pair("Move Worker On Wall", 					""),
				pair("Move Worker On Spawn", 					""),
				pair("Worker Pushes Worker", 					""),
				pair("Worker Pushes Worker In Chain On Wall", 	""),
				pair("Worker Pushes Worker In Chain On Spawn", 	"")
				
		);
		if (answ == null) {
			return;
		}
		
		PrettyPrinter.setQuestionHandler(new AutomaticQuestionHandler(in, answ[2]));
		PrettyPrinter.startSequence(answ[0], answ[1]);
		
		handleSituation();
		
		PrettyPrinter.endSequence();
	}
	
	private static void cratePush() {
		throw new RuntimeException();
	}
	
	private static void devMode() {
		RecorderQuestionHandler rqh = new RecorderQuestionHandler();
		PrettyPrinter.setQuestionHandler(rqh);
		
		handleSituation();
		
		System.out.println("Your input was: " + rqh.getInput());
	}
	
	private static void handleSituation() {
		Warehouse wh = new Warehouse(0, 0);
		
		Floor emptyField = new Floor(wh);
		Character ch = PrettyPrinter.askQuestion(
				"What kind of situation? [W - Worker, C - Crate] : ", 
				"WC", 
				new Character[] { 'W', 'C' }
		);
		if (ch == 'W') {
			Worker w = new Worker(wh, emptyField, Direction.Right);
			wh.addEntity(w);
			
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
