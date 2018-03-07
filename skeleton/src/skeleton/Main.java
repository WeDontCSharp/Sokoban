package skeleton;

import java.util.Scanner;

import skeleton.meta.AutomaticQuestionHandler;
import skeleton.meta.ManualQuestionHandler;
import skeleton.meta.PrettyPrinter;
import skeleton.model.Direction;
import skeleton.model.Warehouse;
import skeleton.model.Worker;

public class Main {
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		PrettyPrinter.setQuestionHandler(new ManualQuestionHandler());
		
		System.out.println("What do you want to do?");
		System.out.println(" 1 - Worker Move");
		System.out.println(" 2 - Idk not implemented yet");
		
		switch (Integer.parseInt(in.nextLine())) {
		case 1:
			workerMoves();
			break;
			
		default:
			System.err.println("No such action!");
		}
		
		in.close();
	}
	
	public static void workerMoves() {
		System.out.println(" More specifically?");
		System.out.println("  1 - Worker Move On Empty Floor");
		System.out.println("  2 - Worker Move On Wall");
		
		String answers = "";
		switch (Integer.parseInt(in.nextLine())) {
		case 1:
			answers = "RFN";
			break;
			
		case 2:
			answers = "RWN";
			break;
			
		default:
			System.err.println("No such action!");
			return;
		}
		
		PrettyPrinter.setQuestionHandler(new AutomaticQuestionHandler(answers));
		
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
