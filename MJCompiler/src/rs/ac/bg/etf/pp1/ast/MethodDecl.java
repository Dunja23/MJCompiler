// generated with ast extension for cup
// version 0.8
// 10/6/2024 0:42:53


package rs.ac.bg.etf.pp1.ast;

public class MethodDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private MethodReturnType MethodReturnType;
    private MethodName MethodName;
    private OptionalFormPars OptionalFormPars;
    private VarDeclarList VarDeclarList;
    private MethodStatementList MethodStatementList;

    public MethodDecl (MethodReturnType MethodReturnType, MethodName MethodName, OptionalFormPars OptionalFormPars, VarDeclarList VarDeclarList, MethodStatementList MethodStatementList) {
        this.MethodReturnType=MethodReturnType;
        if(MethodReturnType!=null) MethodReturnType.setParent(this);
        this.MethodName=MethodName;
        if(MethodName!=null) MethodName.setParent(this);
        this.OptionalFormPars=OptionalFormPars;
        if(OptionalFormPars!=null) OptionalFormPars.setParent(this);
        this.VarDeclarList=VarDeclarList;
        if(VarDeclarList!=null) VarDeclarList.setParent(this);
        this.MethodStatementList=MethodStatementList;
        if(MethodStatementList!=null) MethodStatementList.setParent(this);
    }

    public MethodReturnType getMethodReturnType() {
        return MethodReturnType;
    }

    public void setMethodReturnType(MethodReturnType MethodReturnType) {
        this.MethodReturnType=MethodReturnType;
    }

    public MethodName getMethodName() {
        return MethodName;
    }

    public void setMethodName(MethodName MethodName) {
        this.MethodName=MethodName;
    }

    public OptionalFormPars getOptionalFormPars() {
        return OptionalFormPars;
    }

    public void setOptionalFormPars(OptionalFormPars OptionalFormPars) {
        this.OptionalFormPars=OptionalFormPars;
    }

    public VarDeclarList getVarDeclarList() {
        return VarDeclarList;
    }

    public void setVarDeclarList(VarDeclarList VarDeclarList) {
        this.VarDeclarList=VarDeclarList;
    }

    public MethodStatementList getMethodStatementList() {
        return MethodStatementList;
    }

    public void setMethodStatementList(MethodStatementList MethodStatementList) {
        this.MethodStatementList=MethodStatementList;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodReturnType!=null) MethodReturnType.accept(visitor);
        if(MethodName!=null) MethodName.accept(visitor);
        if(OptionalFormPars!=null) OptionalFormPars.accept(visitor);
        if(VarDeclarList!=null) VarDeclarList.accept(visitor);
        if(MethodStatementList!=null) MethodStatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodReturnType!=null) MethodReturnType.traverseTopDown(visitor);
        if(MethodName!=null) MethodName.traverseTopDown(visitor);
        if(OptionalFormPars!=null) OptionalFormPars.traverseTopDown(visitor);
        if(VarDeclarList!=null) VarDeclarList.traverseTopDown(visitor);
        if(MethodStatementList!=null) MethodStatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodReturnType!=null) MethodReturnType.traverseBottomUp(visitor);
        if(MethodName!=null) MethodName.traverseBottomUp(visitor);
        if(OptionalFormPars!=null) OptionalFormPars.traverseBottomUp(visitor);
        if(VarDeclarList!=null) VarDeclarList.traverseBottomUp(visitor);
        if(MethodStatementList!=null) MethodStatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDecl(\n");

        if(MethodReturnType!=null)
            buffer.append(MethodReturnType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodName!=null)
            buffer.append(MethodName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalFormPars!=null)
            buffer.append(OptionalFormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclarList!=null)
            buffer.append(VarDeclarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodStatementList!=null)
            buffer.append(MethodStatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDecl]");
        return buffer.toString();
    }
}
