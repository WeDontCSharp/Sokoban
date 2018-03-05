package skeleton;

public class AlternativeStatement implements IStatement {
	private ICondition condition;
	private IStatement thenClause;
	private IStatement elseClause;
	
	public AlternativeStatement(ICondition cnd, IStatement thencl, IStatement elcl) {
		this.condition = cnd;
		this.thenClause = thencl;
		this.elseClause = elcl;
	}
	
	public AlternativeStatement(ICondition cnd, IStatement thencl) {
		this(cnd, thencl, null);
	}

	@Override
	public void execute() {
		if (condition.evaluate()) {
			System.out.println("Then clause cosen!");
			this.thenClause.execute();
		}
		else if (this.elseClause != null) {
			System.out.println("Else clause cosen!");
			this.elseClause.execute();
		}
	}
}
