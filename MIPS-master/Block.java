import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Block {
    private int blockNumber;
    private int endBlock = 0;
    private String leader;
    private List<IRLine> blockIR = new ArrayList<IRLine>();
    private List<String> cfgLabels = new ArrayList<String>();
    private List<Integer> cfgBlockNext = new ArrayList<Integer>();
    private List<Integer> cfgBlockPrev = new ArrayList<Integer>();


    public Block(List<IRLine> argLine, int blockCount, int isEnd) {
        blockNumber = blockCount;
        blockIR = argLine;
        endBlock = isEnd;
        parseBlock(argLine);
    }

    public int getBlockNumber() {
        return blockNumber;
    }

    public String getBlockLeader() {
        return leader;
    }

    public List<Integer> getCFGBlockNext() {
        return cfgBlockNext;
    }

    public List<Integer> getCFGBlockPrev() {
        return cfgBlockPrev;
    }

    public void setCFGBlockNext(List<Integer> next) {
        cfgBlockNext = next;
    }

    public void addCFGBlockPrev(int prev) {
        if (!cfgBlockPrev.contains(prev)) {
            cfgBlockPrev.add(prev);
        }
    }

    private void parseBlock(List<IRLine> line) {
        leader = line.get(0).toString().replaceAll(":","");
        if(line.get(line.size() - 1).isConditionalOp()) {
            cfgLabels.add(line.get(line.size() - 1).getJumpLabel().trim());
        }
        if (line.get(line.size() - 1).isJump()) {
            cfgLabels.add(line.get(line.size() - 1).getJumpLabelGoto().trim());
        } else if (endBlock != 1) {
            cfgLabels.add("next");
        } else {
            cfgLabels.add("end");
        }
    }

    public List<IRLine> getBlockIR() {
        return blockIR;
    }

    public List<String> getCFGLabels() {
        return cfgLabels;
    }
}
