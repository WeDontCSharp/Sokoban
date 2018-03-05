package skeleton;

public class AlternativeStatement implements IStatement {
	private Pair<ICondition, IStatement>[] conditionalClauses;
	private IStatement elseClause;
	
	public AlternativeStatement(Pair<ICondition, IStatement>[] conds, IStatement els) {
		this.conditionalClauses = conds;
		this.elseClause = els;
	}
	
	public AlternativeStatement(Pair<ICondition, IStatement>[] conds) {
		this(conds, null);
	}

	@Override
	public void execute() {
		for (int i = 0; i < this.conditionalClauses.length; i++) {
			Pair<ICondition, IStatement> cnd = this.conditionalClauses[i];
			if (cnd.getFirst().evaluate()) {
				System.out.println("Executing " + (i + 1) + ". alternative clause!");
				cnd.getSecond().execute();
				return;
			}
		}
		if (elseClause != null) {
			System.out.println("Executing else (last) alternative clause!");
			elseClause.execute();
		}
	}
}
