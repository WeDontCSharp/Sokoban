package skeleton.model;

public class WorkerRespawnProcess implements Process {
	public static final float SPEED = 0.08f;
	
	private float percent;
	private Worker w;
	private Field at;
	private Field spawn;
	
	public WorkerRespawnProcess(Worker w, Field at) {
		this.percent = 0.0f;
		this.w = w;
		this.at = at;
		this.spawn = w.getSpawnField();
	}
	
	@Override
	public void start() {
		this.at.lock();
		this.spawn.lock();
	}

	@Override
	public void update() {
		percent += SPEED;
		if (percent >= 1.0f) {
			percent = 1.0f;
		}
	}

	@Override
	public boolean isOver() {
		return percent >= 1.0f;
	}

	@Override
	public void end() {
		this.at.unlock();
		this.spawn.unlock();
		
		if (at.getEntity().get() == this.w) {
			at.unsetEntity();
		}
		
		spawn.setEntity(this.w);
		this.w.setCurField(this.spawn);
	}
	
}
