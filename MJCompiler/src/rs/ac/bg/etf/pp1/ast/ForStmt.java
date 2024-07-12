// generated with ast extension for cup
// version 0.8
// 10/6/2024 0:42:53


package rs.ac.bg.etf.pp1.ast;

public class ForStmt extends Statement {

    private OptionalDesignatorStmtList OptionalDesignatorStmtList;
    private OptionalCondFact OptionalCondFact;
    private OptionalDesignatorStmtList OptionalDesignatorStmtList1;
    private Statement Statement;

    public ForStmt (OptionalDesignatorStmtList OptionalDesignatorStmtList, OptionalCondFact OptionalCondFact, OptionalDesignatorStmtList OptionalDesignatorStmtList1, Statement Statement) {
        this.OptionalDesignatorStmtList=OptionalDesignatorStmtList;
        if(OptionalDesignatorStmtList!=null) OptionalDesignatorStmtList.setParent(this);
        this.OptionalCondFact=OptionalCondFact;
        if(OptionalCondFact!=null) OptionalCondFact.setParent(this);
        this.OptionalDesignatorStmtList1=OptionalDesignatorStmtList1;
        if(OptionalDesignatorStmtList1!=null) OptionalDesignatorStmtList1.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public OptionalDesignatorStmtList getOptionalDesignatorStmtList() {
        return OptionalDesignatorStmtList;
    }

    public void setOptionalDesignatorStmtList(OptionalDesignatorStmtList OptionalDesignatorStmtList) {
        this.OptionalDesignatorStmtList=OptionalDesignatorStmtList;
    }

    public OptionalCondFact getOptionalCondFact() {
        return OptionalCondFact;
    }

    public void setOptionalCondFact(OptionalCondFact OptionalCondFact) {
        this.OptionalCondFact=OptionalCondFact;
    }

    public OptionalDesignatorStmtList getOptionalDesignatorStmtList1() {
        return OptionalDesignatorStmtList1;
    }

    public void setOptionalDesignatorStmtList1(OptionalDesignatorStmtList OptionalDesignatorStmtList1) {
        this.OptionalDesignatorStmtList1=OptionalDesignatorStmtList1;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalDesignatorStmtList!=null) OptionalDesignatorStmtList.accept(visitor);
        if(OptionalCondFact!=null) OptionalCondFact.accept(visitor);
        if(OptionalDesignatorStmtList1!=null) OptionalDesignatorStmtList1.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalDesignatorStmtList!=null) OptionalDesignatorStmtList.traverseTopDown(visitor);
        if(OptionalCondFact!=null) OptionalCondFact.traverseTopDown(visitor);
        if(OptionalDesignatorStmtList1!=null) OptionalDesignatorStmtList1.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalDesignatorStmtList!=null) OptionalDesignatorStmtList.traverseBottomUp(visitor);
        if(OptionalCondFact!=null) OptionalCondFact.traverseBottomUp(visitor);
        if(OptionalDesignatorStmtList1!=null) OptionalDesignatorStmtList1.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForStmt(\n");

        if(OptionalDesignatorStmtList!=null)
            buffer.append(OptionalDesignatorStmtList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalCondFact!=null)
            buffer.append(OptionalCondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalDesignatorStmtList1!=null)
            buffer.append(OptionalDesignatorStmtList1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForStmt]");
        return buffer.toString();
    }
}
