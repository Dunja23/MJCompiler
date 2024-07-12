// generated with ast extension for cup
// version 0.8
// 10/6/2024 0:42:53


package rs.ac.bg.etf.pp1.ast;

public class NoOptDesignaStmttorList extends OptionalDesignatorStmtList {

    public NoOptDesignaStmttorList () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NoOptDesignaStmttorList(\n");

        buffer.append(tab);
        buffer.append(") [NoOptDesignaStmttorList]");
        return buffer.toString();
    }
}
