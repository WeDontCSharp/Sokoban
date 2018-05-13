package skeleton;

import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

import skeleton.model.LevelFormatException;
import skeleton.model.Warehouse;
import skeleton.view.KeyboardView;
import skeleton.view.gfx.GraphicsView;

/**
 * The starting-point for the application.
 */
public class Main {
	
	public static void main(String[] args) {
		GraphicsView gw = new GraphicsView();
		
		try {
			Warehouse wh = Warehouse.fromFile("graphics_testlevel.json", gw);
			KeyboardView kw0 = new KeyboardView(0, 
					KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE, 
					wh, gw);
			KeyboardView kw1 = new KeyboardView(1, 
					KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_E, 
					wh, gw);
			
			mainGameLoop(wh, gw);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LevelFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void mainGameLoop(Warehouse wh, GraphicsView gw) {
		double goalDelta = 1000000000D / 60D;
		double goalSecDelta = 1000000000D;
		double delta = 0D;
		double secDelta = 0D;
		
		long lastTime = System.nanoTime();
		long currentTime;		
		long diff;
		
		int ups = 0;
		int renders = 0;
		
		while (true) {
			currentTime = System.nanoTime();
			diff = currentTime - lastTime;
			delta += diff;
			secDelta += diff;
			lastTime = currentTime;
			
			while (delta >= goalDelta) {
				delta -= goalDelta;
				wh.update();
				++ups;
			}
			gw.repaint();
			++renders;
			
			if (secDelta >= goalSecDelta) {
				System.out.println("Updates: " + ups + " Renders: " + renders);
				ups = 0;
				renders = 0;
				secDelta %= goalSecDelta;
			}
		}
	}

}

/*
 * FOR TESTING ONLY!
 */

/*public class Main {
	
	private static ArrayList<Test> tests = new ArrayList<Test>();
	
	public static void main(String[] args) {
		addTests();
		displayMenu();
		readInput();
	}
	
	public static void addTests() {
		
		ArrayList<String> src = new ArrayList<String>();
		
		// XXX: Add tests here with their name only!
		src.add("Move worker on empty field");
		src.add("Worker pushes crate to empty field");
		src.add("Worker pushes other worker directly");
		src.add("Worker pushes other worker in chain");
		src.add("Worker smashed by wall");
		src.add("Worker smashed by spawn");
		src.add("Worker falls into hole");
		src.add("Worker pushes lifecrate into hole");
		src.add("Target activation test");
		src.add("Target deactivation test");
		src.add("Switch activation test");
		src.add("Switch deactivation test");
		src.add("Field modificator test - oil");
		src.add("Field modificator test - honey");
		src.add("Locking mechanism of simultaneously pushed conflicting chains");
		src.add("Game ends because there is a crate on all targets");
		src.add("Game ends because there are no moveable crates");
		src.add("Game ends because all the workers died except one");
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
		
	}
	
	public static void readInput() {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		
		try {
			loop: while (true) {
				line = br.readLine();
				
				if (line.trim().equals("Q") || line.trim().equals("q")) {
					System.out.println("So early to quIIT...");
					br.close();
					break loop;
				}
				
				try {
					int num = Integer.parseInt(line.trim());
					
					if (num < 0 || num > tests.size()) {
						System.out.println("It's not that hard to give a number from 0 to " + tests.size() + "... Let's try it again.");
					} else if (num == 0) {	
						int success = 0;
						for (int i = 0; i < tests.size(); ++i) {
							System.out.println("\nRunning test: [" + tests.get(i).getId() + "] " + tests.get(i).getName() + "...");
							if (tests.get(i).run()) {
								success++;
							}
						}
						System.out.println("\n=== SUCCEEDED / ALL ===");
						System.out.println("=== "+ success +" / "+ tests.size() +" ===");
						 
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
	
}*/
