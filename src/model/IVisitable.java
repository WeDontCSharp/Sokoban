package model;

import java.util.List;

public interface IVisitable {
	
	public boolean visitBy(Worker w, List<Entity> ents);
	public boolean visitBy(Crate c, List<Entity> ents);

}
