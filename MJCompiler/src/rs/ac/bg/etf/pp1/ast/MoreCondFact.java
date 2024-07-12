// generated with ast extension for cup
// version 0.8
// 10/6/2024 0:42:53


package rs.ac.bg.etf.pp1.ast;

public class MoreCondFact extends MoreCondFacts {

    private MoreCondFacts MoreCondFacts;
    private CondFact CondFact;

    public MoreCondFact (MoreCondFacts MoreCondFacts, CondFact CondFact) {
        this.MoreCondFacts=MoreCondFacts;
        if(MoreCondFacts!=null) MoreCondFacts.setParent(this);
        this.CondFact=CondFact;
        if(CondFact!=null) CondFact.setParent(this);
    }

    public MoreCondFacts getMoreCondFacts() {
        return MoreCondFacts;
    }

    public void setMoreCondFacts(MoreCondFacts MoreCondFacts) {
        this.MoreCondFacts=MoreCondFacts;
    }

    public CondFact getCondFact() {
        return CondFact;
    }

    public void setCondFact(CondFact CondFact) {
        this.CondFact=CondFact;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MoreCondFacts!=null) MoreCondFacts.accept(visitor);
        if(CondFact!=null) CondFact.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MoreCondFacts!=null) MoreCondFacts.traverseTopDown(visitor);
        if(CondFact!=null) CondFact.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MoreCondFacts!=null) MoreCondFacts.traverseBottomUp(visitor);
        if(CondFact!=null) CondFact.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreCondFact(\n");

        if(MoreCondFacts!=null)
            buffer.append(MoreCondFacts.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondFact!=null)
            buffer.append(CondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreCondFact]");
        return buffer.toString();
    }
}
