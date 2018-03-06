package skeleton.meta;

public class BlockStatement implements IStatement {
	private IStatement[] statements;
	
	public BlockStatement(IStatement[] stmts) {
		this.statements = stmts;
	}

	@Override
	public void execute() {
		for (int i = 0; i < statements.length; i++) {
			System.out.println("Executing " + (i + 1) + ". statement!");
			statements[i].execute();
		}
	}
	
}
