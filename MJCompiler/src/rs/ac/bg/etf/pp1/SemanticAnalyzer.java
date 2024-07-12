package rs.ac.bg.etf.pp1;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*; 
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {
	

	public static final Struct boolType = new Struct(Struct.Bool);
	int varDeclCount = 0;
	public int nVars = 0;
	ArrayList<String> nameSpaces = new ArrayList<>();
	Obj currentMethod = null;
	Type currentType = null;
	Type currentMethodType = null;
	String currentNameSpace = "";
	String currentVarName = "";
	Boolean rangFunc = false;
	boolean errorDetected = false;
	Obj constNode = null;
	Struct temp = null;
	
	Logger log = Logger.getLogger(getClass());
	
	public void report_error(String message, SyntaxNode info) { 
		errorDetected = true;
		StringBuilder msg = new StringBuilder (message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line); 
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) { 
		StringBuilder msg = new StringBuilder (message);
		int line = (info == null)? 0: info.getLine();
		if (line != 0)
		msg.append(" na liniji ").append(line); 
		log.info(msg.toString());
	}
	
	public void visit(VarName varName) {
		varDeclCount++;
		currentVarName = varName.getVarName();
	}
	
	public void visit(NoVariableArray noVariableArray) {
		if (currentType != null) {
			if (Tab.find(currentVarName) == Tab.noObj) {
				if(currentNameSpace == "")
					Tab.insert(Obj.Var, currentVarName, currentType.struct);
				else {
					Tab.insert(Obj.Var, currentNameSpace + "::" + currentVarName, currentType.struct);
					if(!nameSpaces.contains(currentNameSpace)) {
						nameSpaces.add(currentNameSpace);
					}
				}
			}
			else {
				report_error("Greska na " + noVariableArray.getLine() + ", promenljiva "+ currentVarName +" je vec deklarisana!", null);	
			}
		}
	}
	
	
	public void visit(VariableArray variableArray) {
		if (currentType != null) {
			Struct type = new Struct(Struct.Array, currentType.struct);
			if (Tab.find(currentVarName) == Tab.noObj) {
				if(currentNameSpace == "")
					Tab.insert(Obj.Var, currentVarName, type);
				else {
					Tab.insert(Obj.Var, currentNameSpace + "::" + currentVarName, type);
					if(!nameSpaces.contains(currentNameSpace)) {
						nameSpaces.add(currentNameSpace);
					}
				}
			}
			else {
				report_error("Greska na " + variableArray.getLine() + ", promenljiva "+ currentVarName +" je vec deklarisana!", null);	
			}
		}
		
	}
	 
	public void visit(ConstName constName) {
		if (currentType != null) {
			String name = "";
			if (Tab.find(constName.getConstName()) == Tab.noObj) {
				if(currentNameSpace == "") 
					name = constName.getConstName();
				else {
					name = currentNameSpace + "::" + constName.getConstName();
					if(!nameSpaces.contains(currentNameSpace)) {
						nameSpaces.add(currentNameSpace);
					}	
				}
				constNode = Tab.insert(Obj.Con, name, currentType.struct);		
			}
			else {
				report_error("Greska na " + constName.getLine() + ", konstanta "+ name +" je vec deklarisana!", null);	
			}
		}
	}
	
	public void visit(ConstDecl constDecl) {
		Struct te = constDecl.getType().struct;
		Struct t = constDecl.getConstant().struct;
		if(!te.equals(t))
			report_error("Greska na " + constDecl.getLine() + ": nekompatibilni tipovi prilikom dodele konstante", null);
		
	}
	
	public void visit(ReadStmt readStmt) {
		if(readStmt.getDesignator().obj != null)
			if (readStmt.getDesignator().obj.getType() != Tab.intType && readStmt.getDesignator().obj.getType()  != Tab.charType
					&& readStmt.getDesignator().obj.getType()  != boolType) {
				report_error("Greska na " + readStmt.getDesignator().getLine() + " nepravilan tip u read-u! ", null); 
			}
	}

	public void visit(PrintStmt print) {
		if(print.getExpr().struct != null)
			if (print.getExpr().struct != Tab.intType && print.getExpr().struct != Tab.charType
					&& print.getExpr().struct != boolType && print.getExpr().struct.getKind()!= Struct.Array) {
				report_error("Greska na " + print.getExpr().getLine() + " nepravilan tip u print-u! ", null); 
			}
	}
	
	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType); 
		Tab.openScope();
	}
	
	public void visit(Program program) {
		nVars = Tab.currentScope().getnVars();
		Tab.chainLocalSymbols(program.getProgName().obj); 
		Tab.closeScope();
	}
	
	public void visit (Type type) {
		Obj typeNode = Tab.find(type.getTypeName());
		currentType = type;
		if(typeNode == Tab.noObj) {
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", type); 
			type.struct = Tab.noType;
		}
		else{
			if (Obj.Type == typeNode.getKind()){
				type.struct = typeNode.getType();
			}
			else{
				report_error("Greska na " + type.getLine() + ", "+ type.getTypeName() + "ne predstavlja tip!", type);
				type.struct = Tab.noType;
			}
		}
		
	}
	
	public void visit(DesignatorNameSpace designatorNameSpace) {
		Obj obj = Tab.find(designatorNameSpace.getNsName() + "::" + designatorNameSpace.getDesignatorName().getName());
		if (obj == Tab.noObj) {
			if(nameSpaces.contains(designatorNameSpace.getNsName()))
				report_error("Greska na " + designatorNameSpace.getLine()+", ime "+designatorNameSpace.getDesignatorName().getName()+" nije deklarisano u prostoru imena " + designatorNameSpace.getNsName() + "!", null);
			else		
				report_error("Greska na " + designatorNameSpace.getLine()+", prostor imena "+designatorNameSpace.getNsName()+" ne postoji! ", null);
		}
		else {
			designatorNameSpace.obj = obj;
			DumpSymbolTableExd dstv = new DumpSymbolTableExd();
			dstv.visitObjNode(obj);
			System.out.println("Pretraga na  " + designatorNameSpace.getLine() 
			+ " (" + designatorNameSpace.getDesignatorName().getName() + "), nadjeno " + dstv.getOutput());	

		}
		
	}

	public void visit(DesignatorNoNameSpace designatorNoNameSpace) {
		Obj obj = Tab.find(designatorNoNameSpace.getDesignatorName().getName());
		if (obj == Tab.noObj) {
			report_error("Greska na " + designatorNoNameSpace.getLine()+" ime "+designatorNoNameSpace.getDesignatorName().getName()+" nije deklarisano! ", null);
		}
		else {
			designatorNoNameSpace.obj = obj;
			DumpSymbolTableExd dstv = new DumpSymbolTableExd();
			dstv.visitObjNode(obj);
			System.out.println("Pretraga na  " + designatorNoNameSpace.getLine() 
			+ " (" + designatorNoNameSpace.getDesignatorName().getName() + "), nadjeno " + dstv.getOutput());
		}

	}
	
	public void visit(DesignatorList designatorList) {
		designatorList.obj = new Obj(Obj.Elem, "$", designatorList.getDesignatorRestList().struct);
	}

	public void visit(Num num) {
		num.struct = Tab.intType;
		constNode.setAdr(num.getN1());
	}
	
	public void visit(Ch ch) {
		ch.struct = Tab.charType;
		constNode.setAdr((int)ch.getC1());
	}
	
	public void visit(Bool bool) {
		bool.struct = boolType;
		if(bool.getB1())
			constNode.setAdr(1);
		else 
			constNode.setAdr(0);
	}
	
	public void visit(NamespName namespName) {
		currentNameSpace = namespName.getNsName();
	}
	
	public void visit(Namespace namespace) {
		currentNameSpace = "";
	}
	public void visit(MethodType methodType) {
		currentMethodType = methodType.getType();
	}
	public void visit(MethodVoid methodVoid) {
		currentMethodType = null;
	}
	
	public void visit(MethodName methodName) {
		if (currentMethodType != null )
			currentMethod = Tab.insert(Obj.Meth, methodName.getMethodName(), currentMethodType.struct); 
		else 
			currentMethod = Tab.insert(Obj.Meth, methodName.getMethodName(), Tab.noType);
		methodName.obj = currentMethod;
		Tab.openScope();
	}
	
	public void visit(MethodDecl methodDecl) {
		Tab.chainLocalSymbols(currentMethod); 
		Tab.closeScope();
		currentMethod = null;
	}
	
	public void visit(FactNum factNum) {
		factNum.struct = Tab.intType;
		temp = Tab.intType;
	}
	
	public void visit(FactChar factChar) {
		factChar.struct = Tab.charType;
		temp = Tab.charType;
	}
	
	public void visit(FactBool factBool) {
		factBool.struct = boolType;
		temp = boolType;
	}
	
	public void visit(FactEol factEol) {
		factEol.struct = Tab.charType;
		temp = Tab.charType;
	}
	
	public void visit(MulExpr mulExpr) {
		Struct te = mulExpr.getMoreFactors().struct; 
		Struct t = mulExpr.getFactor().struct;
		if (te != null && t != null) {
			if(te.equals(t) && te == Tab.intType)
				mulExpr.struct = te;
			else 
				report_error("Greska na "+ mulExpr.getFactor().getLine()+": nekompatibilni tipovi u izrazu za mnozenje!", null);	
		}else if (te == null){
			if (t != Tab.intType)
				report_error("Greska na "+ mulExpr.getFactor().getLine() +": tip mora biti int u izrazu za mnozenje!", null);	
			else 
				mulExpr.struct = t;
		}
		else {
			if (te != Tab.intType)
				report_error("Greska na "+ mulExpr.getFactor().getLine()+": tip mora biti int!", null);	
			else 
				mulExpr.struct = te;
		}
	}
	
	public void visit(Term term) {
		Struct te = term.getMoreFactors().struct; 
		Struct t = term.getFactor().struct;
		if (te != null && t != null) {
			if(te.equals(t))
				term.struct = te;
			else 
				report_error("Greska na "+ term.getLine()+": nekompatibilni tipovi u izrazu!", null);	
		}
		else term.struct = t;
	}
	
	public void visit(FactExpr factExpr) {
		factExpr.struct = factExpr.getExpr().struct;
		temp = factExpr.struct;
	}
	
	public void visit(FactNTE newTypeExpr) {
		if (newTypeExpr.getExpr().struct != Tab.intType) {
			report_error("Greska na "+ newTypeExpr.getLine()+" tip unutar '[]' mora biti int!", null);	
		}
		newTypeExpr.struct = new Struct(Struct.Array, newTypeExpr.getType().struct);
	}
	
	public void visit(RangeCall rangCall) {
	    rangFunc = true;
		if (rangCall.getExpr().struct != Tab.intType) {
			report_error("Greska na "+ rangCall.getLine()+" parametar u funkciji range mora biti int!", null);	
		}
	}
	
	
	public void visit(FactDesign factDesign) {
		if(factDesign.getDesignator().obj != null) {
			factDesign.struct = factDesign.getDesignator().obj.getType();	
			temp = factDesign.struct;
		}
		else temp = null;
	}
	
	public void visit(ExprMinus exprMinus) {
		Struct te = exprMinus.getMoreTerms().struct; 
		Struct t = exprMinus.getTerm().struct;
		if (t != Tab.intType)
			report_error("Greska na " + exprMinus.getLine()+": nepravilni tip ispred minusa", null);
		else if (te != null) {
			if(te.equals(t) && te == Tab.intType)
				exprMinus.struct = te;
			else 
				report_error("Greska na "+ exprMinus.getTerm().getLine()+": nekompatibilni tipovi u izrazu za sabiranje.", null);	
		}
		else exprMinus.struct = t;
	
	}
	
	public void visit(ExprNoMinus exprNoMinus) {
		Struct te = exprNoMinus.getMoreTerms().struct; 
		Struct t = exprNoMinus.getTerm().struct;
		if (te != null) {
			if(!te.equals(t))
				report_error("Greska na "+ exprNoMinus.getTerm().getLine()+": nekompatibilni tipovi u izrazu!", null);
			else 
				exprNoMinus.struct = te;	
		}
		else
			exprNoMinus.struct = t;
		
	}

	public void visit(AddExpr addExpr){ 
		Struct te = addExpr.getMoreTerms().struct; 
		Struct t = addExpr.getTerm().struct;
		if (te == null) {
			if (t == Tab.intType)
				addExpr.struct = t;
			else
				report_error("Greska na "+ addExpr.getTerm().getLine()+": tip mora biti int za sabiranje!", null);			
		}else {
			if(te.equals(t) && te == Tab.intType) {
				addExpr.struct = te;
			}
			else 
				report_error("Greska na "+ addExpr.getTerm().getLine()+": nekompatibilni tipovi u izrazu za sabiranje!", null);
		}
	}
	
	public void visit(DesignatorRestArray designatorRestArray) {
		if(designatorRestArray.getDesignator().obj.getType().getKind() != Struct.Array)
			report_error("Greska na "+ designatorRestArray.getExpr().getLine()+ ": "+ designatorRestArray.getDesignator().obj.getName()+ " nije tipa niz! ", null);
		else if(designatorRestArray.getExpr().struct != Tab.intType)
			report_error("Greska na "+ designatorRestArray.getExpr().getLine() +": tip unutar '[]' mora biti int!", null);	
		designatorRestArray.struct = designatorRestArray.getDesignator().obj.getType().getElemType(); 
		
	}
	
	public void visit(DesignatorAssign designatorAssign) {
		Struct te = designatorAssign.getDesignator().obj.getType();
		Struct t = designatorAssign.getExpr().struct;
		if (te != null && t != null) {
			if (te.getKind() != t.getKind())
					report_error("Greska na "+ designatorAssign.getLine()+ ": nekompatibilni tipovi pri operaciji dodele!", null);
			else if(te.getKind() == Struct.Array) {
				if(!te.getElemType().equals(t.getElemType())) 
					report_error("Greska na "+ designatorAssign.getLine()+ ": nekompatibilni tipovi pri operaciji dodele!", null);

			}
		}
		else if(te != null && rangFunc) {
			rangFunc = false;
			if(!te.getElemType().equals(Tab.intType))
				report_error("Greska na "+ designatorAssign.getLine()+ ": nekompatibilni tipovi, funkcija range() vraca niz tipa int!", null);
		}
	}
	
	public void visit(DesignatorIncrement designatorIncrement) {
		if (designatorIncrement.getDesignator().obj.getType() != Tab.intType) {
			report_error("Greska na " + designatorIncrement.getDesignator().getLine() + ": moze da se inkrementira samo int tip!", null); 
		}
	}
	public void visit(DesignatorDecrement designatorDecrement) {
		if (designatorDecrement.getDesignator().obj.getType() != Tab.intType) {
			report_error("Greska na " + designatorDecrement.getDesignator().getLine() + ": moze da se derementira samo int tip!", null); 
		}
	}
	
	public void visit(FuncCall funcCall) {
		Obj method = funcCall.getDesignator().obj;
		if (method.getKind() != Obj.Meth) {
			report_error("Greska na " + funcCall.getDesignator().getLine() + ": nije metoda!", null); 
			funcCall.struct = Tab.noType;
			return;
		}	
		String name = method.getName();
		switch(name) {
			case "len":
				if(temp.getKind()!= Struct.Array)
					report_error("Greska na " + funcCall.getDesignator().getLine() + ": neispravan tip parametra!", null);
				break;
			case "chr":
				if(temp != Tab.intType)
					report_error("Greska na " + funcCall.getDesignator().getLine() + ": neispravan tip parametra!", null);
				break;
			case "ord":
				if(temp != Tab.charType)
					report_error("Greska na " + funcCall.getDesignator().getLine() + ": neispravan tip parametra!", null);
				break;
			default:
				break;
		
		}
		funcCall.struct = method.getType();
		temp = null;
		
	}
	
	public boolean passed() {
		return !errorDetected;
	}
	

	
}
