package skeleton.test.commands;

import skeleton.model.Warehouse;
import skeleton.model.Worker;
import skeleton.model.Crate;
import skeleton.model.Entity;

import java.util.ArrayList;
import java.util.List;

import skeleton.test.Command;
import skeleton.test.TestEnvironment;
import skeleton.test.TestExecutionException;

public class CNTCommand implements Command {
	private String param;
	private ArrayList<Entity> crates;

	public CNTCommand(String args) {
		this.param = args;
		this.crates = new ArrayList<Entity>();
	}

	public void CrateFinder(Warehouse wh){
		crates.clear();
		for (int i = 0; i <= wh.getHeight(); ++i){
			for (int j = 0; j <= wh.getWidth(); ++i){
				if (!wh.getField(i, j).isEmpty());
					crates.add((wh.getField(i, j).getEntity().get()));
			}
		}
		for (int i = 0; i < 4; i++){
			crates.remove(wh.getWorker(i));
		}
	}

	@Override
	public void exec(TestEnvironment env) throws TestExecutionException {
		Warehouse w = env.getLevel();
		if (w == null) {
			throw new TestExecutionException();
		}
		if (param.equals("WA")) {
			int cnt = 0;
			for (int i = 0; i < 4; i++){
				if (w.getWorker(i).getHealth() > 0)
					cnt++;
			}
			System.out.println("CNT_WA " + cnt);
			return;
		}
		if (param.equals("WD")) {
			int cnt = 0;
			for (int i = 0; i < 4; i++){
				if (w.getWorker(i).getHealth() == 0)
					cnt++;
			}
			System.out.println("CNT_WD " + cnt);
			return;
		}
		if (param.equals("C")) {
			CrateFinder(w);
			System.out.println("CNT_C " + crates.size());
			return;
		}
		if (param.equals("CR")) {
			int cnt = 0;
			CrateFinder(w);
			for (Entity c : crates){
				if(((Crate)c).isOnTarget())
					cnt++;
			}
			System.out.println("CNT_CR " + cnt);
			return;
		}
		if (param.equals("CW")) {
			int cnt = 0;
			CrateFinder(w);
			for (Entity c : crates){
				if(!((Crate)c).isOnTarget())
					cnt++;
			}
			System.out.println("CNT_CW " + cnt);
			return;
		}
		if (param.equals("CS")) {
			int cnt = 0;
			CrateFinder(w);
			for (Entity c : crates){
				if(((Crate)c).isStuck())
					cnt++;
			}
			System.out.println("CNT_CS " + cnt);
			return;
		}
	}
}
