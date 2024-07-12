// generated with ast extension for cup
// version 0.8
// 10/6/2024 0:42:53


package rs.ac.bg.etf.pp1.ast;

public class ExprMinus extends Expr {

    private Term Term;
    private MoreTerms MoreTerms;

    public ExprMinus (Term Term, MoreTerms MoreTerms) {
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
        this.MoreTerms=MoreTerms;
        if(MoreTerms!=null) MoreTerms.setParent(this);
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public MoreTerms getMoreTerms() {
        return MoreTerms;
    }

    public void setMoreTerms(MoreTerms MoreTerms) {
        this.MoreTerms=MoreTerms;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Term!=null) Term.accept(visitor);
        if(MoreTerms!=null) MoreTerms.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
        if(MoreTerms!=null) MoreTerms.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Term!=null) Term.traverseBottomUp(visitor);
        if(MoreTerms!=null) MoreTerms.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprMinus(\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MoreTerms!=null)
            buffer.append(MoreTerms.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprMinus]");
        return buffer.toString();
    }
}
