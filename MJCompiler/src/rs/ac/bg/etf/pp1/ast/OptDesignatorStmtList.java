// generated with ast extension for cup
// version 0.8
// 10/6/2024 0:42:53


package rs.ac.bg.etf.pp1.ast;

public class OptDesignatorStmtList extends OptionalDesignatorStmtList {

    private DesignatorStatement DesignatorStatement;
    private MoreDesignatorStmts MoreDesignatorStmts;

    public OptDesignatorStmtList (DesignatorStatement DesignatorStatement, MoreDesignatorStmts MoreDesignatorStmts) {
        this.DesignatorStatement=DesignatorStatement;
        if(DesignatorStatement!=null) DesignatorStatement.setParent(this);
        this.MoreDesignatorStmts=MoreDesignatorStmts;
        if(MoreDesignatorStmts!=null) MoreDesignatorStmts.setParent(this);
    }

    public DesignatorStatement getDesignatorStatement() {
        return DesignatorStatement;
    }

    public void setDesignatorStatement(DesignatorStatement DesignatorStatement) {
        this.DesignatorStatement=DesignatorStatement;
    }

    public MoreDesignatorStmts getMoreDesignatorStmts() {
        return MoreDesignatorStmts;
    }

    public void setMoreDesignatorStmts(MoreDesignatorStmts MoreDesignatorStmts) {
        this.MoreDesignatorStmts=MoreDesignatorStmts;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorStatement!=null) DesignatorStatement.accept(visitor);
        if(MoreDesignatorStmts!=null) MoreDesignatorStmts.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStatement!=null) DesignatorStatement.traverseTopDown(visitor);
        if(MoreDesignatorStmts!=null) MoreDesignatorStmts.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStatement!=null) DesignatorStatement.traverseBottomUp(visitor);
        if(MoreDesignatorStmts!=null) MoreDesignatorStmts.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptDesignatorStmtList(\n");

        if(DesignatorStatement!=null)
            buffer.append(DesignatorStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MoreDesignatorStmts!=null)
            buffer.append(MoreDesignatorStmts.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptDesignatorStmtList]");
        return buffer.toString();
    }
}
