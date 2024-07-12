// generated with ast extension for cup
// version 0.8
// 10/6/2024 0:42:53


package rs.ac.bg.etf.pp1.ast;

public class MoreCondTerm extends MoreCondTerms {

    private MoreCondTerms MoreCondTerms;
    private CondTerm CondTerm;

    public MoreCondTerm (MoreCondTerms MoreCondTerms, CondTerm CondTerm) {
        this.MoreCondTerms=MoreCondTerms;
        if(MoreCondTerms!=null) MoreCondTerms.setParent(this);
        this.CondTerm=CondTerm;
        if(CondTerm!=null) CondTerm.setParent(this);
    }

    public MoreCondTerms getMoreCondTerms() {
        return MoreCondTerms;
    }

    public void setMoreCondTerms(MoreCondTerms MoreCondTerms) {
        this.MoreCondTerms=MoreCondTerms;
    }

    public CondTerm getCondTerm() {
        return CondTerm;
    }

    public void setCondTerm(CondTerm CondTerm) {
        this.CondTerm=CondTerm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MoreCondTerms!=null) MoreCondTerms.accept(visitor);
        if(CondTerm!=null) CondTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MoreCondTerms!=null) MoreCondTerms.traverseTopDown(visitor);
        if(CondTerm!=null) CondTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MoreCondTerms!=null) MoreCondTerms.traverseBottomUp(visitor);
        if(CondTerm!=null) CondTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreCondTerm(\n");

        if(MoreCondTerms!=null)
            buffer.append(MoreCondTerms.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondTerm!=null)
            buffer.append(CondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreCondTerm]");
        return buffer.toString();
    }
}
