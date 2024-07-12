// generated with ast extension for cup
// version 0.8
// 10/6/2024 0:42:53


package rs.ac.bg.etf.pp1.ast;

public class VarDeclList extends VarList {

    private VarList VarList;
    private VarName VarName;
    private VarArray VarArray;

    public VarDeclList (VarList VarList, VarName VarName, VarArray VarArray) {
        this.VarList=VarList;
        if(VarList!=null) VarList.setParent(this);
        this.VarName=VarName;
        if(VarName!=null) VarName.setParent(this);
        this.VarArray=VarArray;
        if(VarArray!=null) VarArray.setParent(this);
    }

    public VarList getVarList() {
        return VarList;
    }

    public void setVarList(VarList VarList) {
        this.VarList=VarList;
    }

    public VarName getVarName() {
        return VarName;
    }

    public void setVarName(VarName VarName) {
        this.VarName=VarName;
    }

    public VarArray getVarArray() {
        return VarArray;
    }

    public void setVarArray(VarArray VarArray) {
        this.VarArray=VarArray;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarList!=null) VarList.accept(visitor);
        if(VarName!=null) VarName.accept(visitor);
        if(VarArray!=null) VarArray.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarList!=null) VarList.traverseTopDown(visitor);
        if(VarName!=null) VarName.traverseTopDown(visitor);
        if(VarArray!=null) VarArray.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarList!=null) VarList.traverseBottomUp(visitor);
        if(VarName!=null) VarName.traverseBottomUp(visitor);
        if(VarArray!=null) VarArray.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclList(\n");

        if(VarList!=null)
            buffer.append(VarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarName!=null)
            buffer.append(VarName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarArray!=null)
            buffer.append(VarArray.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclList]");
        return buffer.toString();
    }
}
