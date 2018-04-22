package skeleton.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import skeleton.model.Direction;
import skeleton.test.commands.LevelCommand;
import skeleton.test.commands.LoadCommand;
import skeleton.test.commands.PlaceCommand;
import skeleton.test.commands.PlayerInfoCommand;
import skeleton.test.commands.SaveCommand;
import skeleton.test.commands.StepCommand;
import skeleton.test.commands.CNTCommand;
import skeleton.test.commands.GiveCommand;
import skeleton.test.commands.HoleInfoCommand;

public abstract class TestReader {
	public static Test fromFile(String path) throws IOException, InputLanguageException {
		ArrayList<Command> clist = new ArrayList<Command>();
		BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
		String line = null;
		while ((line = reader.readLine()) != null) {
			int idx = line.indexOf(';');
			if (idx != -1) {
				line = line.substring(0, idx);
			}
			line = line.trim().toLowerCase();
			if (line.length() == 0) {
				continue;
			}
			
			String[] parts = line.split(" ");
			String cmd = parts[0].trim();
			
			if (cmd.equals("step") || cmd.equals("!step")) {
				boolean sync = cmd.charAt(0) == '!';
				int pid = Integer.parseInt(parts[1].trim()) - 1;
				String dirStr = parts[2].trim();
				
				Direction dir = null;
				switch (dirStr.charAt(0)) {
				case 'l': dir = Direction.Left; break;
				case 'r': dir = Direction.Right; break;
				case 'u': dir = Direction.Up; break;
				case 'd': dir = Direction.Down; break;
				// XXX: Info
				default: throw new InputLanguageException();
				}
				
				clist.add(new StepCommand(sync, pid, dir));
			}
			else if (cmd.equals("level")) {
				String pth = parts[1].trim();
				if (pth.length() < 2 || pth.charAt(0) != '"' || pth.charAt(pth.length() - 1) != '"') {
					throw new InputLanguageException();
				}
				
				clist.add(new LevelCommand(pth.substring(1, pth.length() - 1)));
			}
			else if (cmd.equals("save")) {
				String pth = parts[1].trim();
				if (pth.length() < 2 || pth.charAt(0) != '"' || pth.charAt(pth.length() - 1) != '"') {
					throw new InputLanguageException();
				}
				
				clist.add(new SaveCommand(pth.substring(1, pth.length() - 1)));
			}
			else if (cmd.equals("load")) {
				String pth = parts[1].trim();
				if (pth.length() < 2 || pth.charAt(0) != '"' || pth.charAt(pth.length() - 1) != '"') {
					throw new InputLanguageException();
				}
				
				clist.add(new LoadCommand(pth.substring(1, pth.length() - 1)));
			}
			else if (cmd.equals("playerinfo")) {
				int pid = Integer.parseInt(parts[1].trim()) - 1;
				String typeStr = parts[2].trim();

				clist.add(new PlayerInfoCommand(pid, typeStr));
			}
			else if (cmd.equals("holeinfo")) {
				String xy = parts[1].trim();
				String[] coords = xy.split(",");
				int x = Integer.parseInt(coords[0].trim());
				int y = Integer.parseInt(coords[1].trim());

				clist.add(new HoleInfoCommand(x, y));
			}
			else if (cmd.equals("give")) {
				int pid = Integer.parseInt(parts[1].trim()) - 1;
				String itemStr = parts[2].trim();

				clist.add(new GiveCommand(pid, itemStr));
			}
			else if (cmd.equals("place") || cmd.equals("!place")) {
				int pid = Integer.parseInt(parts[1].trim()) - 1;

				clist.add(new PlaceCommand(pid));
			}
			else if (cmd.equals("cnt")) {
				String arg = parts[1].trim();

				clist.add(new CNTCommand(arg));
			}
			else {
				// XXX: Info like line number
				throw new InputLanguageException();
			}
		}
		reader.close();
		// XXX: Cut tst_ from path
		return new Test(path, clist);
	}
}
