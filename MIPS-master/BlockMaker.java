import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class BlockMaker {

    private List<Block> blocks = new ArrayList<Block>();
    private ArrayList<String> blockLeader = new ArrayList<String>();
    private List<IRLine> blockLine = new ArrayList<IRLine>();
    private List<IRVar> allVars = new ArrayList<IRVar>();
    private List<String> allVarsNames = new ArrayList<String>();
    
    public BlockMaker(List<IRLine> argLine) {
        setBlockLeaders(argLine);
        setBlocks(argLine);
        cfgLabels();
        genVariables();
        for (int i = 0; i < allVars.size() - 1; i++) {
            pathfinder(i);
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public List<IRVar> getVars() {
        return allVars;
    }

    public List<String> getVarsNames() {
        return allVarsNames;
    }

    private void setBlocks(List<IRLine> lines) {
        int branchAfterLine = 0;
        int isEnd = 0;
        int endCounter = 0;
        int nBlock = 0;
        int blockCount = 1;
        int lineCount = 1;
        blockLine = new ArrayList<IRLine>();

        for (IRLine e : lines) {

            if (branchAfterLine == 1) {

                branchAfterLine = 0;

                if (!blockLine.isEmpty() && blockLeader.contains(e.toString().replaceAll(":","").trim())) { nBlock = 1; }

            }
            if (e.isJump()) {

                branchAfterLine = 1;

            }
            if (e.isLabel()) {
                if (!blockLine.isEmpty() && blockLeader.contains(e.toString().replaceAll(":","").trim())) { nBlock = 1; }
            }
            if (e.isConditionalOp()) {

                branchAfterLine = 1;

            }
            if (e.isFunctionDeclaration() && lineCount != 1) {
                if (!blockLine.isEmpty() && blockLeader.contains(e.getFunctionName().replaceAll(":","").trim())) { nBlock = 1; }
            }

            if (e.getFields()[0] == "return") {
                isEnd = 1;
                endCounter++;
            }
            
            if (nBlock == 1) {
                nBlock = 0;
                blocks.add(new Block(blockLine, blockCount, isEnd));
                blockLine = new ArrayList<IRLine>();
                blockCount++;
            }
            blockLine.add(e);
            lineCount++;
        }
        if (endCounter == 0) {
            isEnd = 1;
        }
        blocks.add(new Block(blockLine, blockCount, isEnd));
        //return blocks;
    }

    public ArrayList<String> getBlockLeaders() {
        return blockLeader;
    }

    //things todo, recognize separate functions as new programs aka has own blocks!!!!

    private void setBlockLeaders(List<IRLine> lines) {
        int branchAfterLine = 0;
        int firstLine = 0;

        for (IRLine e : lines) {

            if (branchAfterLine == 1) {
                if (!blockLeader.contains(e.toString().replaceAll(":",""))){
                    blockLeader.add(e.toString().replaceAll(":", "").trim());
                }
                branchAfterLine = 0;

            }

            if (e.isJump()) {
                if (!blockLeader.contains(e.getJumpLabelGoto())) {
                    blockLeader.add(e.getJumpLabelGoto().trim());
                }
                branchAfterLine = 1;

            } 
            if (e.isLabel() && firstLine == 0) {

                if (!blockLeader.contains(e.getLabel())) {
                    blockLeader.add(e.getLabel().trim());
                }
                firstLine = 1;

            } 
            if (e.isConditionalOp()) {
                if (!blockLeader.contains(e.getJumpLabel())) {
                    blockLeader.add(e.getJumpLabel().trim());
                }
                branchAfterLine = 1;

            }
            if (e.isFunctionDeclaration() && firstLine != 0) {
                if (!blockLeader.contains(e.getFunctionName())) {
                    blockLeader.add(e.getFunctionName().trim());
                }
            }
        }
    }

    private void cfgLabels() {
        List<Integer> cfgBlockNext = new ArrayList<Integer>();
        for (Block b : blocks) {
            for(String label : b.getCFGLabels()) {
                for (Block b2 : blocks) {
                    if (label == "end") {
                        cfgBlockNext.add(-1);
                        break;
                    } else if (label == "next") {
                        cfgBlockNext.add(b.getBlockNumber() + 1);
                        blocks.get(b.getBlockNumber()).addCFGBlockPrev(b.getBlockNumber());
                        break;
                    } else if (label.equals(b2.getBlockLeader())) {
                        cfgBlockNext.add(b2.getBlockNumber());
                        b2.addCFGBlockPrev(b.getBlockNumber());
                    }
                }
            }
            b.setCFGBlockNext(cfgBlockNext);
            cfgBlockNext = new ArrayList<Integer>();
        }
    }


    private void genVariables() {
        for (Block b : blocks) {
            int countLine = 1;
            for (IRLine ir : b.getBlockIR()) {
                if (ir.isBinaryOp()) {
                    if (!allVarsNames.contains(ir.getDestination())){
                        allVars.add(new IRVar(countLine, b.getBlockNumber(), ir.getDestination()));
                        allVarsNames.add(ir.getDestination());
                        allVars.get(allVars.size()-1).setIRLineCreated(ir);
                    } else {
                        if (allVarsNames.contains(ir.getDestination())){
                            int ind = allVarsNames.indexOf(ir.getDestination());
                            allVars.get(ind).setEnd(countLine, b.getBlockNumber());
                            allVars.get(ind).addLiveBlock(b.getBlockNumber());
                        }
                        if (allVarsNames.contains(ir.getSource0())){
                            int ind = allVarsNames.indexOf(ir.getSource0());
                            allVars.get(ind).setEnd(countLine, b.getBlockNumber());
                            allVars.get(ind).addLiveBlock(b.getBlockNumber());
                        }
                        if (allVarsNames.contains(ir.getSource1())){
                            int ind = allVarsNames.indexOf(ir.getSource1());
                            allVars.get(ind).setEnd(countLine, b.getBlockNumber());
                            allVars.get(ind).addLiveBlock(b.getBlockNumber());
                        }
                    }
                } else if (ir.isAssign()) {
                    if (!allVarsNames.contains(ir.getDestination())){
                        allVars.add(new IRVar(countLine, b.getBlockNumber(), ir.getDestination()));
                        allVarsNames.add(ir.getDestination());
                        allVars.get(allVars.size()-1).setIRLineCreated(ir);
                    } else {
                        int ind = allVarsNames.indexOf(ir.getDestination());
                        allVars.get(ind).setEnd(countLine, b.getBlockNumber());
                        allVars.get(ind).addLiveBlock(b.getBlockNumber());
                    }
                } else if (ir.isLoadOp()) {
                    if (!allVarsNames.contains(ir.getSource0())){
                        allVars.add(new IRVar(countLine, b.getBlockNumber(), ir.getSource0()));
                        allVarsNames.add(ir.getSource0());
                        allVars.get(allVars.size()-1).setIRLineCreated(ir);
                    } else {
                        if (allVarsNames.contains(ir.getSource0())){
                            int ind = allVarsNames.indexOf(ir.getSource0());
                            allVars.get(ind).setEnd(countLine, b.getBlockNumber());
                            allVars.get(ind).addLiveBlock(b.getBlockNumber());
                        }
                        if (allVarsNames.contains(ir.getSource1())){
                            int ind = allVarsNames.indexOf(ir.getSource1());
                            allVars.get(ind).setEnd(countLine, b.getBlockNumber());
                            allVars.get(ind).addLiveBlock(b.getBlockNumber());
                        }
                    }
                } else if (ir.isStoreOp()) {
                    if (allVarsNames.contains(ir.getSource0())){
                        int ind = allVarsNames.indexOf(ir.getSource0());
                        allVars.get(ind).setEnd(countLine, b.getBlockNumber());
                        allVars.get(ind).addLiveBlock(b.getBlockNumber());
                    }
                    if (allVarsNames.contains(ir.getSource1())){
                        int ind = allVarsNames.indexOf(ir.getSource1());
                        allVars.get(ind).setEnd(countLine, b.getBlockNumber());
                        allVars.get(ind).addLiveBlock(b.getBlockNumber());
                    }
                } else if (ir.isArrayAssign()) {
                    // System.out.println(ir.getArrayAssignName());
                    // System.out.println(ir.getArrayAssignValue());
                    // System.out.println("Array");
                } else if (ir.isCallOp()) {
                    if (allVarsNames.contains(ir.getCallParam0())) {
                        int ind = allVarsNames.indexOf(ir.getCallParam0());
                        allVars.get(ind).setEnd(countLine, b.getBlockNumber());
                        allVars.get(ind).addLiveBlock(b.getBlockNumber());
                    }
                } else if (ir.isConditionalOp()) {
                    if (allVarsNames.contains(ir.getComparee0())) {
                        int ind = allVarsNames.indexOf(ir.getComparee0());
                        allVars.get(ind).setEnd(countLine, b.getBlockNumber());
                        allVars.get(ind).addLiveBlock(b.getBlockNumber());
                    }
                    if (allVarsNames.contains(ir.getComparee1())) {
                        int ind2 = allVarsNames.indexOf(ir.getComparee1());
                        allVars.get(ind2).setEnd(countLine, b.getBlockNumber());
                        allVars.get(ind2).addLiveBlock(b.getBlockNumber());
                    }
                } else if (ir.isFunctionDeclaration()) {
                    for (String param : ir.getFunctionParam()) {
                        if (!allVarsNames.contains(param.trim())) {
                            allVars.add(new IRVar(countLine, b.getBlockNumber(), param.trim()));
                            allVarsNames.add(param.trim());
                            allVars.get(allVars.size()-1).setEnd(blocks.get(blocks.size()-1).getBlockIR().size(), blocks.get(blocks.size()-1).getBlockNumber());
                        }

                    }
                }
                countLine++;
            }
        }
    }

    //index of variable in vars list
    private void pathfinder(int index) {
        int start = allVars.get(index).getVarStartBlock();
        if (allVars.get(index).getVarEndBlock() == 0) {
            allVars.get(index).setEndIsStart();
        }
        int end = allVars.get(index).getVarEndBlock();

        List<Integer> flagList = new ArrayList<Integer>();

        boolean chase = true;
        while(chase) {
            for (Block b : blocks) {
                if (flagList.contains(start)) {
                    chase = false;
                } else if (b.getBlockNumber() == end && !flagList.contains(end)) {
                    //we want this block and it's preds in flag list
                    flagList.add(b.getBlockNumber());
                    if (start != end) {
                        for (int i : b.getCFGBlockPrev()) {
                            if (!flagList.contains(i)) {
                                flagList.add(i);
                            }
                        }
                    }
                } else if (flagList.contains(b.getBlockNumber())) {
                    for (int i : b.getCFGBlockPrev()) {
                        if (!flagList.contains(i)) {
                            flagList.add(i);
                        }
                    }
                }
            }
        }
        allVars.get(index).setLiveBlocks(flagList);
        // System.out.println(flagList);
    }
}

//I now see the flaw in my code. We're tracking by block which would
//loop infinitely when we should be tracking by variable and follow
//each var down the rabbithole
