package skeleton;

import java.util.Scanner;

import skeleton.meta.AutomaticQuestionHandler;
import skeleton.meta.PrettyPrinter;
import skeleton.meta.RecorderQuestionHandler;
import skeleton.model.Direction;
import skeleton.model.Warehouse;
import skeleton.model.Worker;

public class Main {
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		//PrettyPrinter.setQuestionHandler(new ManualQuestionHandler());
		RecorderQuestionHandler rqh = new RecorderQuestionHandler();
		PrettyPrinter.setQuestionHandler(rqh);
		
		System.out.println("What do you want to do?");
		System.out.println(" 1 - Worker Move");
		System.out.println(" 2 - Worker Dies");
		System.out.println(" 3 - Crate Moves");
		
		switch (Integer.parseInt(in.nextLine())) {
		case 1:
			workerMoves();
			break;
			
		default:
			System.err.println("No such action!");
		}
		
		System.out.println("Your input was: " + rqh.getInput());
		
		in.close();
	}
	
	public static void workerMoves() {
		System.out.println(" More specifically?");
		System.out.println("  1 - Worker Move On Empty Floor");
		System.out.println("  2 - Worker Move On Wall");
		System.out.println("  3 - Worker Pushes Crate On Empty Floor");
		System.out.println("  4 - Worker Pushes Worker");
		
		String answers = "";
		switch (Integer.parseInt(in.nextLine())) {
		case 1:
			answers = "RFN";
			break;
			
		case 2:
			answers = "RWN";
			break;
			
		case 3:
			answers = "RFYCFN";
			break;
			
		case 4:
			answers = "RFYW";
			break;
			
		default:
			System.err.println("No such action!");
			return;
		}
		//PrettyPrinter.setQuestionHandler(new AutomaticQuestionHandler(answers));
		
		Warehouse wh = new Warehouse(14, 14, 48, 48);
		
		// Worker steps to the right on an empty field
		Worker w = new Worker(wh, wh.getField(1, 1), Direction.Right);
		wh.addEntity(w);
		
		PrettyPrinter.startSequence("1.", "Worker Moves");
		Direction dir = PrettyPrinter.askQuestion(
				"What direction to move? [L - left, R - right, U - up, D - down] : ", 
				"LRUD", 
				new Direction[] { Direction.Left, Direction.Right, Direction.Up, Direction.Down }
		);
		
		w.move(dir);
		PrettyPrinter.endSequence();
	}
}
