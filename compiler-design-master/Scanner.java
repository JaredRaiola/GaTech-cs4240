
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.PrintStream;
import java.io.FileOutputStream;  


public class Scanner {

	public static void main(String[] args) throws Exception {

		PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
//		System.setOut(out);
	
		String sequenceTokenTypesTigerParserOutput = Config.TIGER_PARSER_SEQUENCE_TOKEN_TYPES_OUTPUT_MESSAGE +
																								Config.COLON +
																							 Config.NEW_LINE +
																							 Config.NEW_LINE;

		TigerCompilerTimeErrorListener tigerCompilerTimeErrorListener = new TigerCompilerTimeErrorListener();

		TigerLexer tigerLexer = buildTigerLexer(args[0], tigerCompilerTimeErrorListener);
		TigerParser tigerParser = buildTigerParser(tigerLexer, tigerCompilerTimeErrorListener);

		Token lexerToken = tigerLexer.nextToken();
		LexerTokenTuple lexerTokenTuple = new LexerTokenTuple(lexerToken, tigerLexer);

		System.out.println(Config.NEW_LINE + Config.PARSER_SEQUENCE_TOKEN_TYPES_MESSAGE + Config.COLON + Config.NEW_LINE);

		while(lexerToken.getType() != TigerLexer.EOF) {
			System.out.println(Config.NEW_TAB +
							   Config.OPEN_TUPLE +
							   lexerTokenTuple.getType() +
					           (Config.COMMA + Config.SPACE) +
							   lexerTokenTuple.getValue() + Config.CLOSED_TUPLE);

			sequenceTokenTypesTigerParserOutput += Config.SPACE +
												   tigerParser.
												   getVocabulary().
												   getSymbolicName(lexerToken.getType());

			lexerToken = tigerLexer.nextToken();
			lexerTokenTuple = new LexerTokenTuple(lexerToken, tigerLexer);

	  	}

		System.out.println();


		tigerLexer = buildTigerLexer(args[0], tigerCompilerTimeErrorListener);

		tigerParser = buildTigerParser(tigerLexer, tigerCompilerTimeErrorListener);


		System.out.println(sequenceTokenTypesTigerParserOutput);

		System.out.println();


		ParseTree tigerParseTree = tigerParser.tiger_program();


		boolean compiled = tigerCompilerTimeErrorListener.didCompile();

		if (compiled) System.out.println(Config.NEW_TAB + Config.NEW_TAB + Config.NEW_TAB +
										 Config.SUCCESSFUL_PARSE_MESSAGE + Config.NEW_LINE);
		else {
			System.out.println(Config.NEW_TAB + Config.NEW_TAB + Config.UN_SUCCESSFUL_PARSE_MESSAGE);

			return;

		}


		System.out.println(Config.NEW_LINE + Config.TIGER_PARSE_TREE_OUTPUT_MESSAGE + Config.COLON + Config.NEW_LINE);
		System.out.println(Config.SPACE + tigerParseTree.toStringTree(tigerParser));

		System.out.println();

		ParseTreeWalker tigerParseTreeWalker = new ParseTreeWalker();

		System.out.println(Config.NEW_LINE + Config.TIGER_PROGRAM_GENERATED_IR_CODE_MESSAGE + Config.COLON + Config.NEW_LINE);


		TigerIRGeneratorListener tigerIRListener = new TigerIRGeneratorListener(new TigerSymbolTable(), "main", "void");

		tigerIRListener.tiger_program_input_filename = args[0];


		tigerParseTreeWalker.walk(tigerIRListener, tigerParseTree);


		System.out.println(Config.NEW_LINE +
						   Config.NEW_TAB + Config.NEW_TAB + Config.NEW_TAB +
						   Config.SUCCESSFUL_COMPILE_MESSAGE + Config.NEW_LINE);

		System.out.println();

		System.out.println("\"" + args[0] + "\"" + Config.SPACE + Config.SYMBOL_TABLE_REPRESENTATION + Config.COLON + Config.NEW_LINE);

		System.out.println(tigerIRListener.printSymbolTableScopes());


	}


	private static TigerLexer buildTigerLexer(String tigerProgramInputFileName,
											  TigerCompilerTimeErrorListener tigerCompilerTimeErrorListener)
											  throws Exception {
		TigerLexer tl = new TigerLexer(CharStreams.fromFileName(tigerProgramInputFileName));
		tl.addErrorListener(tigerCompilerTimeErrorListener);

		return tl;

	}

	private static TigerParser buildTigerParser(TigerLexer tigerLexer,
												TigerCompilerTimeErrorListener tigerCompilerTimeErrorListener) {
		TigerParser tp = new TigerParser(new CommonTokenStream(tigerLexer));
		tp.addErrorListener(tigerCompilerTimeErrorListener);

		return tp;

	}


}


