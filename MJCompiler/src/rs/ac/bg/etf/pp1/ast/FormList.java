// generated with ast extension for cup
// version 0.8
// 10/6/2024 0:42:53


package rs.ac.bg.etf.pp1.ast;

public class FormList extends FormParamList {

    private FormParamList FormParamList;
    private FormParamItem FormParamItem;

    public FormList (FormParamList FormParamList, FormParamItem FormParamItem) {
        this.FormParamList=FormParamList;
        if(FormParamList!=null) FormParamList.setParent(this);
        this.FormParamItem=FormParamItem;
        if(FormParamItem!=null) FormParamItem.setParent(this);
    }

    public FormParamList getFormParamList() {
        return FormParamList;
    }

    public void setFormParamList(FormParamList FormParamList) {
        this.FormParamList=FormParamList;
    }

    public FormParamItem getFormParamItem() {
        return FormParamItem;
    }

    public void setFormParamItem(FormParamItem FormParamItem) {
        this.FormParamItem=FormParamItem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormParamList!=null) FormParamList.accept(visitor);
        if(FormParamItem!=null) FormParamItem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParamList!=null) FormParamList.traverseTopDown(visitor);
        if(FormParamItem!=null) FormParamItem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParamList!=null) FormParamList.traverseBottomUp(visitor);
        if(FormParamItem!=null) FormParamItem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormList(\n");

        if(FormParamList!=null)
            buffer.append(FormParamList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParamItem!=null)
            buffer.append(FormParamItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormList]");
        return buffer.toString();
    }
}
