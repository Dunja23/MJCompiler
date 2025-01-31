package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROG, yytext()); }
"break"   { return new_symbol(sym.BREAK, yytext()); }
"class"   { return new_symbol(sym.CLASS, yytext()); }
"else"   { return new_symbol(sym.ELSE, yytext()); }
"const"   { return new_symbol(sym.CONST, yytext()); }
"if"   { return new_symbol(sym.IF, yytext()); }
"new"   { return new_symbol(sym.NEW, yytext()); }
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"read" 	{ return new_symbol(sym.READ, yytext()); }
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"extends" 	{ return new_symbol(sym.EXT, yytext()); }
"continue"  { return new_symbol(sym.CONTINUE, yytext()); }
"for"   { return new_symbol(sym.FOR, yytext()); }
"static"   { return new_symbol(sym.STATIC, yytext()); }
"namespace"   { return new_symbol(sym.NAMESP, yytext()); }
"range"   { return new_symbol(sym.RANG, yytext()); }
<YYINITIAL> "+" 		{ return new_symbol(sym.PLUS, yytext()); }
"-" 		{ return new_symbol(sym.MINUS, yytext()); }
"*" 		{ return new_symbol(sym.MUL, yytext()); }
"/" 		{ return new_symbol(sym.DIV, yytext()); }
"%" 		{ return new_symbol(sym.MOD, yytext()); }
"==" 		{ return new_symbol(sym.EQ, yytext()); }
"!=" 		{ return new_symbol(sym.NEQ, yytext()); }
">" 		{ return new_symbol(sym.GREATER, yytext()); }
">=" 		{ return new_symbol(sym.GREQ, yytext()); }
"<" 		{ return new_symbol(sym.LESS, yytext()); }
"<=" 		{ return new_symbol(sym.LEQ, yytext()); }
"&&" 		{ return new_symbol(sym.AND, yytext()); }
"||" 		{ return new_symbol(sym.OR, yytext()); }
"=" 		{ return new_symbol(sym.EQUAL, yytext()); }
"++" 		{ return new_symbol(sym.INC, yytext()); }
"--" 		{ return new_symbol(sym.DEC, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
":" 		{ return new_symbol(sym.COLON, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"." 		{ return new_symbol(sym.PERIOD, yytext()); }
"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"[" 		{ return new_symbol(sym.LSQBRACK, yytext()); }
"]" 		{ return new_symbol(sym.RSQBRACK, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }
"=>"		{ return new_symbol(sym.FOLLOW, yytext()); }
"eol"		{ return new_symbol(sym.EOL, yytext()); }

<YYINITIAL> "//" 		     { yybegin(COMMENT); }
<COMMENT> .      { yybegin(COMMENT); }
<COMMENT> "\r\n" { yybegin(YYINITIAL); }



"true"|"false" { return new_symbol(sym.BOOL, Boolean.valueOf(yytext().equals("true"))); }
[0-9]+  { return new_symbol(sym.NUMBER, Integer.parseInt(yytext())); }
"'"[^\']"'" {return new_symbol(sym.CHAR, yytext().charAt(1)); }
([a-z]|[A-Z])[a-z|A-Z|0-9|_]*	{return new_symbol(sym.IDENT, yytext()); }

. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)+" u koloni "+(yycolumn+1)); }






