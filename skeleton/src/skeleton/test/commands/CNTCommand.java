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
	private ArrayList<Crate> crates;

	public CNTCommand(String args) {
		this.param = args;
		this.crates = new ArrayList<Crate>();
	}

	public void CrateFinder(Warehouse wh){
		crates.clear();
		crates = wh.getCrates();
	}

	@Override
	public void exec(TestEnvironment env, ArrayList<String> result) throws TestExecutionException {
		Warehouse w = env.getLevel();
		if (w == null) {
			throw new TestExecutionException();
		}
		if (param.equals("wa")) {
			int cnt = 0;
			for (int i = 0; i < 4; i++){
				if (w.getWorker(i) != null)
					cnt++;
			}
			result.add("CNT_WA " + cnt);
			return;
		}
		if (param.equals("wd")) {
			int cnt = 0;
			for (int i = 0; i < 4; i++){
				if (w.getWorker(i) == null)
					cnt++;
			}
			result.add("CNT_WD " + cnt);
			return;
		}
		if (param.equals("c")) {
			CrateFinder(w);
			result.add("CNT_C " + crates.size());
			return;
		}
		if (param.equals("cr")) {
			int cnt = 0;
			CrateFinder(w);
			for (Crate c : crates){
				if(c.isOnTarget())
					cnt++;
			}
			result.add("CNT_CR " + cnt);
			return;
		}
		if (param.equals("cw")) {
			int cnt = 0;
			CrateFinder(w);
			for (Crate c : crates){
				if(!(c.isOnTarget()))
					cnt++;
			}
			result.add("CNT_CW " + cnt);
			return;
		}
		if (param.equals("cs")) {
			int cnt = 0;
			CrateFinder(w);
			for (Crate c : crates){
				if(c.isStuck())
					cnt++;
			}
			result.add("CNT_CS " + cnt);
			return;
		}
	}
}
