// generated with ast extension for cup
// version 0.8
// 10/6/2024 0:42:53


package rs.ac.bg.etf.pp1.ast;

public class Type implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private String typeName;
    private TypeNamespName TypeNamespName;

    public Type (String typeName, TypeNamespName TypeNamespName) {
        this.typeName=typeName;
        this.TypeNamespName=TypeNamespName;
        if(TypeNamespName!=null) TypeNamespName.setParent(this);
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName=typeName;
    }

    public TypeNamespName getTypeNamespName() {
        return TypeNamespName;
    }

    public void setTypeNamespName(TypeNamespName TypeNamespName) {
        this.TypeNamespName=TypeNamespName;
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
        if(TypeNamespName!=null) TypeNamespName.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TypeNamespName!=null) TypeNamespName.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TypeNamespName!=null) TypeNamespName.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Type(\n");

        buffer.append(" "+tab+typeName);
        buffer.append("\n");

        if(TypeNamespName!=null)
            buffer.append(TypeNamespName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Type]");
        return buffer.toString();
    }
}
