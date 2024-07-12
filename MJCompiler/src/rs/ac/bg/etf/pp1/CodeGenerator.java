package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.ac.bg.etf.pp1.CounterVisitor.*;

public class CodeGenerator extends VisitorAdaptor{
	private int mainPc;
	private int width = 0;
	

	
	public int getMainPc() {
		return mainPc;
	}
	
	@Override
	public void visit(ProgName node) {
		Obj chr = Tab.find("chr");
		Obj ord = Tab.find("ord");
		Obj len = Tab.find("len");
		// chr()
		chr.setAdr(Code.pc);
		chr.setLevel(0);
		Code.put(Code.return_);
		// ord()
		ord.setAdr(Code.pc);
		ord.setLevel(0);
		Code.put(Code.return_);
		// len()
		len.setAdr(Code.pc);
		ord.setLevel(0);
		Code.put(Code.arraylength);
		Code.put(Code.return_);

	}
	

	public void visit (PrintStmt printStmt){ 
		if (printStmt.getOptionalPrintParam() != null) {}
		if(printStmt.getExpr().struct == Tab.intType || printStmt.getExpr().struct == SemanticAnalyzer.boolType ) {
			Code.loadConst(width);
			Code.put(Code.print);
		}else if (printStmt.getExpr().struct == Tab.charType) {
			Code.loadConst(width);
			Code.put(Code.bprint);
		}else if(printStmt.getExpr().struct.getKind() == Struct.Array) {
			
			Obj local = new Obj(Obj.Var, "loc", Tab.intType);
			Obj address = new Obj(Obj.Var, "adr", Tab.intType);
			Obj index = new Obj(Obj.Var, "ind", Tab.intType);
			address.setAdr(100);
			index.setAdr(101);
			
			Code.put(Code.dup);
		    Code.store(address); 
		    
		    Code.put(Code.arraylength);
		   
		    Code.store(local);
		    
		    Code.put(Code.const_1);
		    Code.put(Code.dup);
		    Code.put(Code.sub);
		    
		    Code.store(index);
		    
			int loopStart = Code.pc;
			
	    	Code.load(address);
	    	Code.load(index);
	    	Code.put(Code.aload);
	    	Code.loadConst(width);
			Code.put(Code.print);
			
			Code.load(index);
	    	Code.put(Code.const_1);
	    	Code.put(Code.add);
	    	Code.store(index);
	        
	        Code.load(index);
	        Code.load(local);
	        
	        Code.put(Code.jcc + Code.ge);
	        int exitJump = Code.pc;
	        Code.put2(0); 
	        
	        Code.put(Code.jmp);
	        Code.put2(loopStart - Code.pc + 1);

	        Code.fixup(exitJump);
			
		}
		
	}
	
	public void visit (PrintParam printParam){
		width = printParam.getN1();
	}
	
	
	
	public void visit(ReadStmt readStmt){
		if(readStmt.getDesignator().obj.getType() == Tab.intType || readStmt.getDesignator().obj.getType() == SemanticAnalyzer.boolType ) {
	        Code.put(Code.read);  
		}else{
			Code.put(Code.bread);
		}
		Code.store(readStmt.getDesignator().obj);	
	}
	
	public void visit (FactNum num){
		Obj con = Tab.insert(Obj.Con, "$", num.struct); 
		con.setLevel(0);
		con.setAdr(num.getN1());
		
		Code.load(con);
	}
	
	public void visit (FactChar ch){
		Obj con = Tab.insert(Obj.Con, "$", ch.struct); 
		con.setLevel(0);
		con.setAdr(ch.getC1());
		
		Code.load(con);
	}
	
	public void visit (FactBool bool){
		Obj con = Tab.insert(Obj.Con, "$", bool.struct); 
		con.setLevel(0);
		if (bool.getB1())
			con.setAdr(1);
		else 
			con.setAdr(0);
		
		Code.load(con);
	}
	
	public void visit (FactEol e){
		Obj con = Tab.find("eol"); 
		con.setAdr('\n');
		Code.load(con);
	}
	
	public void visit (MethodName methodName) {
		if("main".equalsIgnoreCase(methodName.getMethodName())){
			mainPc = Code.pc;
		}
		methodName.obj.setAdr(Code.pc);
		SyntaxNode methodNode = methodName.getParent();
		
		VarCounter varCnt = new VarCounter(); 
		methodNode.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter(); 
		methodNode.traverseTopDown(fpCnt);
	
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
		
	}
	
	public void visit (MethodDecl methodDecl){
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	

	public void visit (DesignatorAssign designatorAssign) {
		Code.store(designatorAssign.getDesignator().obj);
	}
	
	
	public void visit (DesignatorNameSpace designator){
		SyntaxNode parent = designator.getParent();
		if (DesignatorAssign.class != parent.getClass() && FuncCall.class != parent.getClass() 
				&& ReadStmt.class != parent.getClass()){
			Code.load(designator.obj);
		}
	}
	
	public void visit (DesignatorNoNameSpace designator){
		SyntaxNode parent = designator.getParent();
		if (DesignatorAssign.class != parent.getClass() && FuncCall.class != parent.getClass()
				&& ReadStmt.class != parent.getClass()){
			Code.load(designator.obj);
		}
	}
	
	public void visit (DesignatorList designatorList) {
		SyntaxNode parent = designatorList.getParent();
		if (DesignatorAssign.class != parent.getClass() && FuncCall.class != parent.getClass()
				&& ReadStmt.class != parent.getClass()){
			Code.load(designatorList.obj);
		}
	}
	
	public void visit (AddExpr addExpr) {
		if(AddPlus.class == addExpr.getAddop().getClass()) 
			Code.put(Code.add);
		else 
			Code.put(Code.sub);
	}
	
	public void visit (MulExpr mulExpr) {
		if(MulMultiply.class == mulExpr.getMulop().getClass()) 
			Code.put(Code.mul);
		else if(MulDivide.class == mulExpr.getMulop().getClass()) {
			Code.put(Code.div);
		}
		else 
			Code.put(Code.rem);
			
	}
	
	public void visit(Term term) {
		if(ExprMinus.class == term.getParent().getClass())
			Code.put(Code.neg);
	}
	
	public void visit(DesignatorIncrement designatorIncrement) {
		Code.put(Code.const_1);
		Code.put(Code.add);
		Code.store(designatorIncrement.getDesignator().obj);
	}
	
	public void visit(DesignatorDecrement designatorDecrement) {
		Code.put(Code.const_1);
		Code.put(Code.sub);
		Code.store(designatorDecrement.getDesignator().obj);
	}
	
	
	public void visit(FactNTE newTypeExpr) {
		  if(newTypeExpr.getType().struct==Tab.intType || newTypeExpr.getType().struct == SemanticAnalyzer.boolType) {
	            Code.put(Code.newarray);
	            Code.put(1);
	        }
	        else {
	            Code.put(Code.newarray);
	            Code.put(0); 
	        }  
	}
	
	public void visit(RangeCall rangeCall) {
		Obj local = new Obj(Obj.Var, "loc", Tab.intType);
		Obj address = new Obj(Obj.Var, "adr", Tab.intType);
		address.setAdr(100);
		
		Code.put(Code.dup);
	    Code.store(local);
	    
	    Code.put(Code.newarray);
	    Code.put(1);
	    
	    Code.store(address);  
	    
		int loopStart = Code.pc;
		
    	Code.load(address);
    	Code.load(local);
    	Code.put(Code.const_1);
    	Code.put(Code.sub);
    	Code.put(Code.dup);
    	Code.store(local);
    	Code.put(Code.dup);

        Code.put(Code.astore);
        
        Code.load(local);
        Code.put(Code.const_1);
        Code.put(Code.dup);
        Code.put(Code.sub);
        
        Code.put(Code.jcc + Code.le);
        int exitJump = Code.pc;
        Code.put2(0); 
        
        Code.put(Code.jmp);
        Code.put2(loopStart - Code.pc + 1);

        Code.fixup(exitJump);
        
        Code.load(address);

	}
	
	public void visit(FuncCall funcCall) {
		Code.put(Code.call);
		Code.put2(funcCall.getDesignator().obj.getAdr() - Code.pc + 1);	
	}
	
	
	
	
}
