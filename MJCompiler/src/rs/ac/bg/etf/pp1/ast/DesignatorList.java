// generated with ast extension for cup
// version 0.8
// 10/6/2024 0:42:53


package rs.ac.bg.etf.pp1.ast;

public class DesignatorList extends Designator {

    private DesignatorRestList DesignatorRestList;

    public DesignatorList (DesignatorRestList DesignatorRestList) {
        this.DesignatorRestList=DesignatorRestList;
        if(DesignatorRestList!=null) DesignatorRestList.setParent(this);
    }

    public DesignatorRestList getDesignatorRestList() {
        return DesignatorRestList;
    }

    public void setDesignatorRestList(DesignatorRestList DesignatorRestList) {
        this.DesignatorRestList=DesignatorRestList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorRestList!=null) DesignatorRestList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorRestList!=null) DesignatorRestList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorRestList!=null) DesignatorRestList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorList(\n");

        if(DesignatorRestList!=null)
            buffer.append(DesignatorRestList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorList]");
        return buffer.toString();
    }
}
