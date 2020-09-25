
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class TigerCompilerTimeErrorListener extends BaseErrorListener {

    private boolean compileError;

    public TigerCompilerTimeErrorListener() {

        this.compileError = false;

    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);

        this.compileErrorHappened();

    }

    private void compileErrorHappened() {
        compileError = true;

    }

    public boolean didCompile() { return !compileError; }

}
