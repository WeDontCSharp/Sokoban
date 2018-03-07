package skeleton;

import java.util.Scanner;

import skeleton.model.Crate;
import skeleton.model.Direction;
import skeleton.model.Field;
import skeleton.model.Floor;
import skeleton.model.Hole;
import skeleton.model.Spawn;
import skeleton.model.Switch;
import skeleton.model.Wall;
import skeleton.model.Warehouse;
import skeleton.model.Worker;

public class Main {
	
	public static void main(String[] args) {
		Main m = new Main();
		m.WorkerSteps();
	}
	
	public void WorkerSteps() {
		Warehouse wh = new Warehouse(14, 14, 48, 48);
		
		// Worker steps to the right on an empty field
		Worker w = new Worker(wh, wh.getField(1, 1), Direction.Right);
		wh.setField(2, 2, new Wall(wh, 5 * Warehouse.TILE_WIDTH, 5 * Warehouse.TILE_HEIGHT));
		wh.addEntity(w);
		
		w.move(Direction.Right);
		
	}
}
