import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class IRVar {
    private int startingBlock;
    private int endingBlock;
    private int startingLine;
    private int endingLine;
    private String varName;
    private IRLine lineCreated;
    private List<Integer> liveBlocks = new ArrayList<Integer>();
    

    public IRVar(int lineNumber, int blockNumber, String name) {
        startingLine = lineNumber;
        startingBlock = blockNumber;
        varName = name.trim();
        liveBlocks.add(blockNumber);
    }

    public String getVarName(){
        return varName;
    }

    public int getVarStartLine(){
        return startingLine;
    }

    public int getVarStartBlock(){
        return startingBlock;
    }

    public void setIRLineCreated(IRLine line) {
        this.lineCreated = line;
    }

    public IRLine getIRLineCreated() {
        return this.lineCreated;
    }

    public void addLiveBlock(int blockNum) {
        if (!liveBlocks.contains(blockNum)) {
            liveBlocks.add(blockNum);
        }
    }

    public void setLiveBlocks(List<Integer> blocks) {
        liveBlocks = blocks;
    }

    public void setEnd(int lineNum, int blockNum) {
        this.endingLine = lineNum;
        this.endingBlock = blockNum;
    }

    public int getVarEndBlock() {
        return endingBlock;
    }

    public int getVarEndLine() {
        return endingLine;
    }

    public void setEndIsStart() {
        this.endingLine = this.startingLine;
        this.endingBlock = this.startingBlock;
    }

    public List<Integer> getVarLiveBlocks() {
        return liveBlocks;
    }
}
