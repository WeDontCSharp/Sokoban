package model;

import java.util.List;

public interface IVisitor {
	
	public boolean visit(Field f, List<Entity> ents);

}
