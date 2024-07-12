package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class Compiler {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args) throws Exception {
		Logger log = Logger.getLogger(Compiler.class);
		Reader br = null;
		
		try {
		
			File sourceCode = new File("test/program.mj");
			log.info("Compiling source file: " + sourceCode.getAbsolutePath());
			
			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			
			MJParser p = new MJParser(lexer);
	        Symbol s = p.parse();  //pocetak parsiranja
	        
	        Program prog = (Program)(s.value);
	        Tab.init();
	        Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", SemanticAnalyzer.boolType));
	        
	        log.info(prog.toString(""));
	        System.out.println("==================SEMANTICKA OBRADA==========================");
		        
	        SemanticAnalyzer semanticCheck = new SemanticAnalyzer();
			prog.traverseBottomUp(semanticCheck);
			
			//log.info(" Print count calls = " + semanticCheck.printCallCount);
			System.out.println("====================SINTAKSNA ANALIZA========================");
			System.out.println(semanticCheck.varDeclCount + " deklarisanih promenljivih");
	        
	        DumpSymbolTableExd dst = new DumpSymbolTableExd();
	        Tab.dump(dst);
	        
			
			if(!p.errorDetected && semanticCheck.passed()){
				File objFile = new File("test/program.obj"); 
				if (objFile.exists()) objFile.delete();
				
				CodeGenerator codeGenerator = new CodeGenerator(); 
				prog.traverseBottomUp(codeGenerator); 
				Code.dataSize = semanticCheck.nVars;
				Code.mainPc = codeGenerator.getMainPc();
				Code.write(new FileOutputStream(objFile)); 
				log.info("Parsiranje uspesno završeno!");
			}else{
				log.error("Parsiranje NIJE uspesno zavrseno!");
			}
		        
		} finally {
			if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1);}
		}

	}
}
