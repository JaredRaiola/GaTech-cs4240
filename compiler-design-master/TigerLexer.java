// Generated from Tiger.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TigerLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMA=1, COLON=2, SEMI=3, LPAREN=4, RPAREN=5, LBRACK=6, RBRACK=7, LBRACE=8, 
		RBRACE=9, PERIOD=10, PLUS=11, MINUS=12, MULT=13, DIV=14, EQ=15, TYPEEQ=16, 
		NEQ=17, LESSER=18, GREATER=19, LESSEREQ=20, GREATEREQ=21, AND=22, OR=23, 
		ASSIGN=24, POWER=25, MAIN=26, ARRAY=27, RECORD=28, BREAK=29, DO=30, ELSE=31, 
		END=32, FOR=33, FUNC=34, IF=35, IN=36, LET=37, OF=38, THEN=39, TO=40, 
		TYPE=41, VAR=42, WHILE=43, ENDIF=44, BEGIN=45, ENDDO=46, RETURN=47, INT=48, 
		FLOAT=49, NEWLINE=50, SPACE=51, TAB=52, COMMENT=53, FLOATLIT=54, INTLIT=55, 
		ID=56;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"COMMA", "COLON", "SEMI", "LPAREN", "RPAREN", "LBRACK", "RBRACK", "LBRACE", 
		"RBRACE", "PERIOD", "PLUS", "MINUS", "MULT", "DIV", "EQ", "TYPEEQ", "NEQ", 
		"LESSER", "GREATER", "LESSEREQ", "GREATEREQ", "AND", "OR", "ASSIGN", "POWER", 
		"MAIN", "ARRAY", "RECORD", "BREAK", "DO", "ELSE", "END", "FOR", "FUNC", 
		"IF", "IN", "LET", "OF", "THEN", "TO", "TYPE", "VAR", "WHILE", "ENDIF", 
		"BEGIN", "ENDDO", "RETURN", "INT", "FLOAT", "NEWLINE", "SPACE", "TAB", 
		"COMMENT", "FLOATLIT", "INTLIT", "ID", "DIGIT", "NONZERODIGIT", "LETTER", 
		"UNDER", "LETTERORDIGIT", "LETTERORDIGITORUNDER", "NULL"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "':'", "';'", "'('", "')'", "'['", "']'", "'{'", "'}'", "'.'", 
		"'+'", "'-'", "'*'", "'/'", "'=='", "'='", "'!='", "'<'", "'>'", "'<='", 
		"'>='", "'&'", "'|'", "':='", "'**'", "'main'", "'array'", "'record'", 
		"'break'", "'do'", "'else'", "'end'", "'for'", "'function'", "'if'", "'in'", 
		"'let'", "'of'", "'then'", "'to'", "'type'", "'var'", "'while'", "'endif'", 
		"'begin'", "'enddo'", "'return'", "'int'", "'float'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COMMA", "COLON", "SEMI", "LPAREN", "RPAREN", "LBRACK", "RBRACK", 
		"LBRACE", "RBRACE", "PERIOD", "PLUS", "MINUS", "MULT", "DIV", "EQ", "TYPEEQ", 
		"NEQ", "LESSER", "GREATER", "LESSEREQ", "GREATEREQ", "AND", "OR", "ASSIGN", 
		"POWER", "MAIN", "ARRAY", "RECORD", "BREAK", "DO", "ELSE", "END", "FOR", 
		"FUNC", "IF", "IN", "LET", "OF", "THEN", "TO", "TYPE", "VAR", "WHILE", 
		"ENDIF", "BEGIN", "ENDDO", "RETURN", "INT", "FLOAT", "NEWLINE", "SPACE", 
		"TAB", "COMMENT", "FLOATLIT", "INTLIT", "ID"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public TigerLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Tiger.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2:\u0186\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b"+
		"\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20"+
		"\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25"+
		"\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33"+
		"\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3 "+
		"\3 \3 \3!\3!\3!\3!\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3$\3$\3"+
		"$\3%\3%\3%\3&\3&\3&\3&\3\'\3\'\3\'\3(\3(\3(\3(\3(\3)\3)\3)\3*\3*\3*\3"+
		"*\3*\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3"+
		".\3/\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61"+
		"\3\61\3\62\3\62\3\62\3\62\3\62\3\62\3\63\6\63\u0133\n\63\r\63\16\63\u0134"+
		"\3\63\3\63\3\64\6\64\u013a\n\64\r\64\16\64\u013b\3\64\3\64\3\65\6\65\u0141"+
		"\n\65\r\65\16\65\u0142\3\65\3\65\3\66\3\66\3\66\3\66\7\66\u014b\n\66\f"+
		"\66\16\66\u014e\13\66\3\66\3\66\3\66\3\66\3\66\3\67\3\67\7\67\u0157\n"+
		"\67\f\67\16\67\u015a\13\67\3\67\3\67\7\67\u015e\n\67\f\67\16\67\u0161"+
		"\13\67\38\38\78\u0165\n8\f8\168\u0168\138\39\39\79\u016c\n9\f9\169\u016f"+
		"\139\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\5>\u017b\n>\3?\3?\3?\5?\u0180\n?\3"+
		"@\3@\3@\3@\3@\3\u014c\2A\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f"+
		"\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63"+
		"\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62"+
		"c\63e\64g\65i\66k\67m8o9q:s\2u\2w\2y\2{\2}\2\177\2\3\2\7\4\2\f\f\17\17"+
		"\3\2\13\13\3\2\62;\3\2\63;\4\2C\\c|\2\u0189\2\3\3\2\2\2\2\5\3\2\2\2\2"+
		"\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2"+
		"\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2"+
		"\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2"+
		"\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2"+
		"\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2"+
		"\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2"+
		"M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3"+
		"\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2"+
		"\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\3"+
		"\u0081\3\2\2\2\5\u0083\3\2\2\2\7\u0085\3\2\2\2\t\u0087\3\2\2\2\13\u0089"+
		"\3\2\2\2\r\u008b\3\2\2\2\17\u008d\3\2\2\2\21\u008f\3\2\2\2\23\u0091\3"+
		"\2\2\2\25\u0093\3\2\2\2\27\u0095\3\2\2\2\31\u0097\3\2\2\2\33\u0099\3\2"+
		"\2\2\35\u009b\3\2\2\2\37\u009d\3\2\2\2!\u00a0\3\2\2\2#\u00a2\3\2\2\2%"+
		"\u00a5\3\2\2\2\'\u00a7\3\2\2\2)\u00a9\3\2\2\2+\u00ac\3\2\2\2-\u00af\3"+
		"\2\2\2/\u00b1\3\2\2\2\61\u00b3\3\2\2\2\63\u00b6\3\2\2\2\65\u00b9\3\2\2"+
		"\2\67\u00be\3\2\2\29\u00c4\3\2\2\2;\u00cb\3\2\2\2=\u00d1\3\2\2\2?\u00d4"+
		"\3\2\2\2A\u00d9\3\2\2\2C\u00dd\3\2\2\2E\u00e1\3\2\2\2G\u00ea\3\2\2\2I"+
		"\u00ed\3\2\2\2K\u00f0\3\2\2\2M\u00f4\3\2\2\2O\u00f7\3\2\2\2Q\u00fc\3\2"+
		"\2\2S\u00ff\3\2\2\2U\u0104\3\2\2\2W\u0108\3\2\2\2Y\u010e\3\2\2\2[\u0114"+
		"\3\2\2\2]\u011a\3\2\2\2_\u0120\3\2\2\2a\u0127\3\2\2\2c\u012b\3\2\2\2e"+
		"\u0132\3\2\2\2g\u0139\3\2\2\2i\u0140\3\2\2\2k\u0146\3\2\2\2m\u0154\3\2"+
		"\2\2o\u0162\3\2\2\2q\u0169\3\2\2\2s\u0170\3\2\2\2u\u0172\3\2\2\2w\u0174"+
		"\3\2\2\2y\u0176\3\2\2\2{\u017a\3\2\2\2}\u017f\3\2\2\2\177\u0181\3\2\2"+
		"\2\u0081\u0082\7.\2\2\u0082\4\3\2\2\2\u0083\u0084\7<\2\2\u0084\6\3\2\2"+
		"\2\u0085\u0086\7=\2\2\u0086\b\3\2\2\2\u0087\u0088\7*\2\2\u0088\n\3\2\2"+
		"\2\u0089\u008a\7+\2\2\u008a\f\3\2\2\2\u008b\u008c\7]\2\2\u008c\16\3\2"+
		"\2\2\u008d\u008e\7_\2\2\u008e\20\3\2\2\2\u008f\u0090\7}\2\2\u0090\22\3"+
		"\2\2\2\u0091\u0092\7\177\2\2\u0092\24\3\2\2\2\u0093\u0094\7\60\2\2\u0094"+
		"\26\3\2\2\2\u0095\u0096\7-\2\2\u0096\30\3\2\2\2\u0097\u0098\7/\2\2\u0098"+
		"\32\3\2\2\2\u0099\u009a\7,\2\2\u009a\34\3\2\2\2\u009b\u009c\7\61\2\2\u009c"+
		"\36\3\2\2\2\u009d\u009e\7?\2\2\u009e\u009f\7?\2\2\u009f \3\2\2\2\u00a0"+
		"\u00a1\7?\2\2\u00a1\"\3\2\2\2\u00a2\u00a3\7#\2\2\u00a3\u00a4\7?\2\2\u00a4"+
		"$\3\2\2\2\u00a5\u00a6\7>\2\2\u00a6&\3\2\2\2\u00a7\u00a8\7@\2\2\u00a8("+
		"\3\2\2\2\u00a9\u00aa\7>\2\2\u00aa\u00ab\7?\2\2\u00ab*\3\2\2\2\u00ac\u00ad"+
		"\7@\2\2\u00ad\u00ae\7?\2\2\u00ae,\3\2\2\2\u00af\u00b0\7(\2\2\u00b0.\3"+
		"\2\2\2\u00b1\u00b2\7~\2\2\u00b2\60\3\2\2\2\u00b3\u00b4\7<\2\2\u00b4\u00b5"+
		"\7?\2\2\u00b5\62\3\2\2\2\u00b6\u00b7\7,\2\2\u00b7\u00b8\7,\2\2\u00b8\64"+
		"\3\2\2\2\u00b9\u00ba\7o\2\2\u00ba\u00bb\7c\2\2\u00bb\u00bc\7k\2\2\u00bc"+
		"\u00bd\7p\2\2\u00bd\66\3\2\2\2\u00be\u00bf\7c\2\2\u00bf\u00c0\7t\2\2\u00c0"+
		"\u00c1\7t\2\2\u00c1\u00c2\7c\2\2\u00c2\u00c3\7{\2\2\u00c38\3\2\2\2\u00c4"+
		"\u00c5\7t\2\2\u00c5\u00c6\7g\2\2\u00c6\u00c7\7e\2\2\u00c7\u00c8\7q\2\2"+
		"\u00c8\u00c9\7t\2\2\u00c9\u00ca\7f\2\2\u00ca:\3\2\2\2\u00cb\u00cc\7d\2"+
		"\2\u00cc\u00cd\7t\2\2\u00cd\u00ce\7g\2\2\u00ce\u00cf\7c\2\2\u00cf\u00d0"+
		"\7m\2\2\u00d0<\3\2\2\2\u00d1\u00d2\7f\2\2\u00d2\u00d3\7q\2\2\u00d3>\3"+
		"\2\2\2\u00d4\u00d5\7g\2\2\u00d5\u00d6\7n\2\2\u00d6\u00d7\7u\2\2\u00d7"+
		"\u00d8\7g\2\2\u00d8@\3\2\2\2\u00d9\u00da\7g\2\2\u00da\u00db\7p\2\2\u00db"+
		"\u00dc\7f\2\2\u00dcB\3\2\2\2\u00dd\u00de\7h\2\2\u00de\u00df\7q\2\2\u00df"+
		"\u00e0\7t\2\2\u00e0D\3\2\2\2\u00e1\u00e2\7h\2\2\u00e2\u00e3\7w\2\2\u00e3"+
		"\u00e4\7p\2\2\u00e4\u00e5\7e\2\2\u00e5\u00e6\7v\2\2\u00e6\u00e7\7k\2\2"+
		"\u00e7\u00e8\7q\2\2\u00e8\u00e9\7p\2\2\u00e9F\3\2\2\2\u00ea\u00eb\7k\2"+
		"\2\u00eb\u00ec\7h\2\2\u00ecH\3\2\2\2\u00ed\u00ee\7k\2\2\u00ee\u00ef\7"+
		"p\2\2\u00efJ\3\2\2\2\u00f0\u00f1\7n\2\2\u00f1\u00f2\7g\2\2\u00f2\u00f3"+
		"\7v\2\2\u00f3L\3\2\2\2\u00f4\u00f5\7q\2\2\u00f5\u00f6\7h\2\2\u00f6N\3"+
		"\2\2\2\u00f7\u00f8\7v\2\2\u00f8\u00f9\7j\2\2\u00f9\u00fa\7g\2\2\u00fa"+
		"\u00fb\7p\2\2\u00fbP\3\2\2\2\u00fc\u00fd\7v\2\2\u00fd\u00fe\7q\2\2\u00fe"+
		"R\3\2\2\2\u00ff\u0100\7v\2\2\u0100\u0101\7{\2\2\u0101\u0102\7r\2\2\u0102"+
		"\u0103\7g\2\2\u0103T\3\2\2\2\u0104\u0105\7x\2\2\u0105\u0106\7c\2\2\u0106"+
		"\u0107\7t\2\2\u0107V\3\2\2\2\u0108\u0109\7y\2\2\u0109\u010a\7j\2\2\u010a"+
		"\u010b\7k\2\2\u010b\u010c\7n\2\2\u010c\u010d\7g\2\2\u010dX\3\2\2\2\u010e"+
		"\u010f\7g\2\2\u010f\u0110\7p\2\2\u0110\u0111\7f\2\2\u0111\u0112\7k\2\2"+
		"\u0112\u0113\7h\2\2\u0113Z\3\2\2\2\u0114\u0115\7d\2\2\u0115\u0116\7g\2"+
		"\2\u0116\u0117\7i\2\2\u0117\u0118\7k\2\2\u0118\u0119\7p\2\2\u0119\\\3"+
		"\2\2\2\u011a\u011b\7g\2\2\u011b\u011c\7p\2\2\u011c\u011d\7f\2\2\u011d"+
		"\u011e\7f\2\2\u011e\u011f\7q\2\2\u011f^\3\2\2\2\u0120\u0121\7t\2\2\u0121"+
		"\u0122\7g\2\2\u0122\u0123\7v\2\2\u0123\u0124\7w\2\2\u0124\u0125\7t\2\2"+
		"\u0125\u0126\7p\2\2\u0126`\3\2\2\2\u0127\u0128\7k\2\2\u0128\u0129\7p\2"+
		"\2\u0129\u012a\7v\2\2\u012ab\3\2\2\2\u012b\u012c\7h\2\2\u012c\u012d\7"+
		"n\2\2\u012d\u012e\7q\2\2\u012e\u012f\7c\2\2\u012f\u0130\7v\2\2\u0130d"+
		"\3\2\2\2\u0131\u0133\t\2\2\2\u0132\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134"+
		"\u0132\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0137\b\63"+
		"\2\2\u0137f\3\2\2\2\u0138\u013a\7\"\2\2\u0139\u0138\3\2\2\2\u013a\u013b"+
		"\3\2\2\2\u013b\u0139\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013d\3\2\2\2\u013d"+
		"\u013e\b\64\2\2\u013eh\3\2\2\2\u013f\u0141\t\3\2\2\u0140\u013f\3\2\2\2"+
		"\u0141\u0142\3\2\2\2\u0142\u0140\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0144"+
		"\3\2\2\2\u0144\u0145\b\65\2\2\u0145j\3\2\2\2\u0146\u0147\7\61\2\2\u0147"+
		"\u0148\7,\2\2\u0148\u014c\3\2\2\2\u0149\u014b\13\2\2\2\u014a\u0149\3\2"+
		"\2\2\u014b\u014e\3\2\2\2\u014c\u014d\3\2\2\2\u014c\u014a\3\2\2\2\u014d"+
		"\u014f\3\2\2\2\u014e\u014c\3\2\2\2\u014f\u0150\7,\2\2\u0150\u0151\7\61"+
		"\2\2\u0151\u0152\3\2\2\2\u0152\u0153\b\66\2\2\u0153l\3\2\2\2\u0154\u0158"+
		"\5u;\2\u0155\u0157\5s:\2\u0156\u0155\3\2\2\2\u0157\u015a\3\2\2\2\u0158"+
		"\u0156\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u015b\3\2\2\2\u015a\u0158\3\2"+
		"\2\2\u015b\u015f\7\60\2\2\u015c\u015e\5s:\2\u015d\u015c\3\2\2\2\u015e"+
		"\u0161\3\2\2\2\u015f\u015d\3\2\2\2\u015f\u0160\3\2\2\2\u0160n\3\2\2\2"+
		"\u0161\u015f\3\2\2\2\u0162\u0166\5u;\2\u0163\u0165\5s:\2\u0164\u0163\3"+
		"\2\2\2\u0165\u0168\3\2\2\2\u0166\u0164\3\2\2\2\u0166\u0167\3\2\2\2\u0167"+
		"p\3\2\2\2\u0168\u0166\3\2\2\2\u0169\u016d\5w<\2\u016a\u016c\5}?\2\u016b"+
		"\u016a\3\2\2\2\u016c\u016f\3\2\2\2\u016d\u016b\3\2\2\2\u016d\u016e\3\2"+
		"\2\2\u016er\3\2\2\2\u016f\u016d\3\2\2\2\u0170\u0171\t\4\2\2\u0171t\3\2"+
		"\2\2\u0172\u0173\t\5\2\2\u0173v\3\2\2\2\u0174\u0175\t\6\2\2\u0175x\3\2"+
		"\2\2\u0176\u0177\7a\2\2\u0177z\3\2\2\2\u0178\u017b\5w<\2\u0179\u017b\5"+
		"s:\2\u017a\u0178\3\2\2\2\u017a\u0179\3\2\2\2\u017b|\3\2\2\2\u017c\u0180"+
		"\5w<\2\u017d\u0180\5s:\2\u017e\u0180\5y=\2\u017f\u017c\3\2\2\2\u017f\u017d"+
		"\3\2\2\2\u017f\u017e\3\2\2\2\u0180~\3\2\2\2\u0181\u0182\7p\2\2\u0182\u0183"+
		"\7w\2\2\u0183\u0184\7n\2\2\u0184\u0185\7n\2\2\u0185\u0080\3\2\2\2\r\2"+
		"\u0134\u013b\u0142\u014c\u0158\u015f\u0166\u016d\u017a\u017f\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}