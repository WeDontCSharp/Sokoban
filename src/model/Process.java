package model;

import gfx.Screen;

public interface Process {
	public void update();
	public void render(Screen screen);
	public boolean isDone();
}
