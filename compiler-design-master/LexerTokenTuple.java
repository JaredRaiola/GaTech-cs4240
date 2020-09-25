
import org.antlr.v4.runtime.*;

public class LexerTokenTuple {

    private Token lexerToken;
    private String type;
    private String value;

    public LexerTokenTuple(Token lexerToken, TigerLexer tigerLexer) {

        this.type = tigerLexer.getVocabulary().getSymbolicName(lexerToken.getType());
        this.value = lexerToken.getText();

    }

    public Token getLexerToken() {
        return lexerToken;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

}
