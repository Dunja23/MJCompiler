// generated with ast extension for cup
// version 0.8
// 10/6/2024 0:42:53


package rs.ac.bg.etf.pp1.ast;

public class MoreE extends MoreExprs {

    private MoreExprs MoreExprs;
    private Expr Expr;

    public MoreE (MoreExprs MoreExprs, Expr Expr) {
        this.MoreExprs=MoreExprs;
        if(MoreExprs!=null) MoreExprs.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public MoreExprs getMoreExprs() {
        return MoreExprs;
    }

    public void setMoreExprs(MoreExprs MoreExprs) {
        this.MoreExprs=MoreExprs;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MoreExprs!=null) MoreExprs.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MoreExprs!=null) MoreExprs.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MoreExprs!=null) MoreExprs.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreE(\n");

        if(MoreExprs!=null)
            buffer.append(MoreExprs.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreE]");
        return buffer.toString();
    }
}
