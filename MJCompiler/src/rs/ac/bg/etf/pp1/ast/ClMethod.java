// generated with ast extension for cup
// version 0.8
// 10/6/2024 0:42:53


package rs.ac.bg.etf.pp1.ast;

public class ClMethod extends ClassDecl {

    private String I1;
    private StaticVarDeclList StaticVarDeclList;
    private StaticInitializerList StaticInitializerList;
    private VarDeclarList VarDeclarList;
    private MethodDeclList MethodDeclList;

    public ClMethod (String I1, StaticVarDeclList StaticVarDeclList, StaticInitializerList StaticInitializerList, VarDeclarList VarDeclarList, MethodDeclList MethodDeclList) {
        this.I1=I1;
        this.StaticVarDeclList=StaticVarDeclList;
        if(StaticVarDeclList!=null) StaticVarDeclList.setParent(this);
        this.StaticInitializerList=StaticInitializerList;
        if(StaticInitializerList!=null) StaticInitializerList.setParent(this);
        this.VarDeclarList=VarDeclarList;
        if(VarDeclarList!=null) VarDeclarList.setParent(this);
        this.MethodDeclList=MethodDeclList;
        if(MethodDeclList!=null) MethodDeclList.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public StaticVarDeclList getStaticVarDeclList() {
        return StaticVarDeclList;
    }

    public void setStaticVarDeclList(StaticVarDeclList StaticVarDeclList) {
        this.StaticVarDeclList=StaticVarDeclList;
    }

    public StaticInitializerList getStaticInitializerList() {
        return StaticInitializerList;
    }

    public void setStaticInitializerList(StaticInitializerList StaticInitializerList) {
        this.StaticInitializerList=StaticInitializerList;
    }

    public VarDeclarList getVarDeclarList() {
        return VarDeclarList;
    }

    public void setVarDeclarList(VarDeclarList VarDeclarList) {
        this.VarDeclarList=VarDeclarList;
    }

    public MethodDeclList getMethodDeclList() {
        return MethodDeclList;
    }

    public void setMethodDeclList(MethodDeclList MethodDeclList) {
        this.MethodDeclList=MethodDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StaticVarDeclList!=null) StaticVarDeclList.accept(visitor);
        if(StaticInitializerList!=null) StaticInitializerList.accept(visitor);
        if(VarDeclarList!=null) VarDeclarList.accept(visitor);
        if(MethodDeclList!=null) MethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StaticVarDeclList!=null) StaticVarDeclList.traverseTopDown(visitor);
        if(StaticInitializerList!=null) StaticInitializerList.traverseTopDown(visitor);
        if(VarDeclarList!=null) VarDeclarList.traverseTopDown(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StaticVarDeclList!=null) StaticVarDeclList.traverseBottomUp(visitor);
        if(StaticInitializerList!=null) StaticInitializerList.traverseBottomUp(visitor);
        if(VarDeclarList!=null) VarDeclarList.traverseBottomUp(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClMethod(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(StaticVarDeclList!=null)
            buffer.append(StaticVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StaticInitializerList!=null)
            buffer.append(StaticInitializerList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclarList!=null)
            buffer.append(VarDeclarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclList!=null)
            buffer.append(MethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClMethod]");
        return buffer.toString();
    }
}
