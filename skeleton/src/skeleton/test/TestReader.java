package skeleton.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import skeleton.model.Direction;
import skeleton.test.commands.LevelCommand;
import skeleton.test.commands.StepCommand;

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
				int pid = Integer.parseInt(parts[1].trim());
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
