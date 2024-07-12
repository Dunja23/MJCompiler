package rs.ac.bg.etf.pp1;
import rs.ac.bg.etf.pp1.ast.FormParamItem; 
import rs.ac.bg.etf.pp1.ast.VarDecl;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class CounterVisitor extends VisitorAdaptor {
	protected int count = 0;
	
	public int getCount(){ 
		return count;
	}
	
	public static class FormParamCounter extends CounterVisitor{
		
		public void visit (FormParamItem formParamDecl){
			count++;
		}
	}
	
	public static class VarCounter extends CounterVisitor{
		public void visit (VarDecl varName){
			count++;
		}
	}
}