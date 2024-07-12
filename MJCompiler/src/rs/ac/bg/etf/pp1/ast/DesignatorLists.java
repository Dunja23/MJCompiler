// generated with ast extension for cup
// version 0.8
// 10/6/2024 0:42:53


package rs.ac.bg.etf.pp1.ast;

public class DesignatorLists extends OptDesignatorList {

    private OptinalDesignator OptinalDesignator;
    private OptDesignatorList OptDesignatorList;

    public DesignatorLists (OptinalDesignator OptinalDesignator, OptDesignatorList OptDesignatorList) {
        this.OptinalDesignator=OptinalDesignator;
        if(OptinalDesignator!=null) OptinalDesignator.setParent(this);
        this.OptDesignatorList=OptDesignatorList;
        if(OptDesignatorList!=null) OptDesignatorList.setParent(this);
    }

    public OptinalDesignator getOptinalDesignator() {
        return OptinalDesignator;
    }

    public void setOptinalDesignator(OptinalDesignator OptinalDesignator) {
        this.OptinalDesignator=OptinalDesignator;
    }

    public OptDesignatorList getOptDesignatorList() {
        return OptDesignatorList;
    }

    public void setOptDesignatorList(OptDesignatorList OptDesignatorList) {
        this.OptDesignatorList=OptDesignatorList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptinalDesignator!=null) OptinalDesignator.accept(visitor);
        if(OptDesignatorList!=null) OptDesignatorList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptinalDesignator!=null) OptinalDesignator.traverseTopDown(visitor);
        if(OptDesignatorList!=null) OptDesignatorList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptinalDesignator!=null) OptinalDesignator.traverseBottomUp(visitor);
        if(OptDesignatorList!=null) OptDesignatorList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorLists(\n");

        if(OptinalDesignator!=null)
            buffer.append(OptinalDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptDesignatorList!=null)
            buffer.append(OptDesignatorList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorLists]");
        return buffer.toString();
    }
}
