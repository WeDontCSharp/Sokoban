package skeleton;

import skeleton.meta.PrettyPrinter;
import skeleton.model.Direction;
import skeleton.model.Wall;
import skeleton.model.Warehouse;
import skeleton.model.Worker;

public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("What do you want to do?");
		System.out.println(" 1 - Worker Step");
		System.out.println(" 2 - Idk not implemented yet");
		
		switch (Integer.parseInt(PrettyPrinter.getAnswer())) {
		case 1:
			workerMoves();
			break;
			
		default:
			System.err.println("No such action!");
		}
	}
	
	public static void workerMoves() {
		Warehouse wh = new Warehouse(14, 14, 48, 48);
		
		// Worker steps to the right on an empty field
		Worker w = new Worker(wh, wh.getField(1, 1), Direction.Right);
		wh.addEntity(w);
		
		PrettyPrinter.startSequence("1.", "Worker Moves");
		PrettyPrinter.printText("What direction to move? [L - left, R - right, U - up, D - down] : ");
		Direction dir = null;
		String ans = PrettyPrinter.getAnswer();
		char d = Character.toUpperCase(ans.charAt(0));
		switch (d) {
		case 'L':
			dir = Direction.Left;
			break;
				
		case 'R':
			dir = Direction.Right;
			break;
				
		case 'U':
			dir = Direction.Up;
			break;
				
		case 'D':
			dir = Direction.Down;
			break;
		}
			
		w.move(dir);
		PrettyPrinter.endSequence();
	}
}
