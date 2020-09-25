
import java.util.*;

public class MIPSGenerator {


    private List<IRLine> iRinstructions;


    private List<String> intList;

    private List<String> floatList;

    private Map<String, Integer> intArrays;

    private Map<String, Integer> floatArrays;

    private List<String> assemblyMIPS;

    private List<String> dataMIPS;

    private Map<String, Integer> variablesMap;

    private Map<String, Integer> libFunctions;

    private int stackPosition;

    private Map<String, List<String>> heritage;

    private List<String> globals;

    private List<String> functions;


    public MIPSGenerator(List<IRLine> iRinstructions) {

        this.iRinstructions = iRinstructions;

        /*
        this.intList = intList;
        this.floatList = floatList;
        */
        this.intArrays = new HashMap<>();
        this.floatArrays = new HashMap<>();


        this.assemblyMIPS = new ArrayList<String>();

        this.dataMIPS = new ArrayList<>();

        this.variablesMap = new HashMap<>();

        this.libFunctions = new HashMap<>();

        libFunctions.put("printi", 1);

        this.functions = new ArrayList<>();

        this.stackPosition = 4;

        generateMIPS();
    }


    public void generateMIPS() {
        List<Integer> functionStarts = functionStartPoints();

        dataMIPS.add(".data");
        assemblyMIPS.add("");
        assemblyMIPS.add(".text");
        assemblyMIPS.add("");

        heritage = new HashMap<String, List<String>>();
        globals = new ArrayList<String>();

        for (int i = 0; i < functionStarts.size(); i++) {
            int start;
            int end;
            start = (functionStarts.get(i));
            if((i+1) < functionStarts.size()) {
                end = functionStarts.get(i + 1) - 1;

            }
            else {
                end = iRinstructions.size();

            }

            generateHeritage(start,end);
            for (String global : findFunctionGlobals(start,end)) {
                if (!globals.contains(global)) {
                    globals.add(global);

                }

            }

        }
        for (String global : globals) {
            dataMIPS.add(global + ":  .space 4");

        }

        for (int i = 0; i < functionStarts.size(); i++) {
            int start;
            int end;

            start = (functionStarts.get(i));

            if((i+1) < functionStarts.size()) {
                end = functionStarts.get(i+1)-1;

            }
            else {
                end = iRinstructions.size();

            }

            stackPosition = 4;

            generateFunctionMIPS(start,end);

        }

        assemblyMIPS.addAll(0, dataMIPS);

    }

    public void generateFunctionMIPS(int start, int end) {

        String functionName = iRinstructions.get(start).getFunctionName() + ":";
        assemblyMIPS.add(functionName);
        functions.add(functionName);

        intList = iRinstructions.get(start + 1).getListedVariables();
        floatList = iRinstructions.get(start + 2).getListedVariables();

        generateDefinedMIPSVariablesStack();

        generateInitialCode();

        if (iRinstructions.get(start).getFunctionParam().size() > 0)
            assemblyMIPS.add("sw" + " " + "$a0" + ", " + variablesMap.get(iRinstructions.get(start).getFunctionParam().get(0)) + "($sp)");

        boolean wasMain = generateMIPSCode(start,end);

        if (wasMain) assemblyMIPS.add("jr $ra");


    }


    public List<Integer> functionStartPoints() {
        List<Integer> returnList = new ArrayList<Integer>();
        for (int i = 0; i < iRinstructions.size(); i++) {
            if (iRinstructions.get(i).getOperation().contains("):")) {
                returnList.add(i);
            }

        }
        return returnList;
    }

    public int generateInitialCode() {
        assemblyMIPS.add("sub $sp, $sp, " + stackPosition * 4);
        assemblyMIPS.add("sw $ra, 16($sp)");

        return 0;

    }

    public int generateDefinedMIPSVariablesStack() {
        for (String intVar : intList) {
            if (!intVar.contains("[")) {
                stackPosition++;

                variablesMap.put(intVar.trim(), stackPosition * 4);

            } else {
                int openB = intVar.indexOf("[");
                int closedB = intVar.indexOf("]");

                intArrays.put(intVar.substring(0, openB).trim(), Integer.parseInt(intVar.substring(openB + 1, closedB).trim()));

            }

        }

        return 0;

    }

    public boolean generateMIPSCode(int start, int end) {

        boolean isMain = iRinstructions.get(start).toString().contains("main");

        for (int i = start; i < end; i++) {
            IRLine currentInstruction = iRinstructions.get(i);

            if (currentInstruction.isFunctionDeclaration()) {
                continue;

            } else if (currentInstruction.isLabel() && !currentInstruction.getLabel().equals("main:") && !functions.contains(currentInstruction.getLabel())) {
                assemblyMIPS.add(currentInstruction.getLabel());

            } else if (currentInstruction.isBinaryOp()) {
                String MIPSBinaryOperation = currentInstruction.getOperation();

                String source0 = currentInstruction.getSource0();
                String source1 = currentInstruction.getSource1();

                String destination = currentInstruction.getDestination();

                boolean immediate = false;

                try {
                    Double.parseDouble(source0);
                    Double.parseDouble(source1);

                    immediate = true;

                } catch (NumberFormatException e) {}

                if (MIPSBinaryOperation.equals("mult")) {
                    MIPSBinaryOperation = "mulo";
                }

                if (immediate) MIPSBinaryOperation += "i";


                assemblyMIPS.add(MIPSBinaryOperation + " " + destination + ", " + source0 + ", " + source1);

            } else if (currentInstruction.isAssign()) {
                String source0 = currentInstruction.getSource0();
                String destination = currentInstruction.getDestination();

                String MIPSAssignOperation = "move";

                boolean immediate = false;

                try {
                    Double.parseDouble(source0);

                    immediate = true;

                } catch (NumberFormatException e) {}

                if (immediate) MIPSAssignOperation = "li";

                assemblyMIPS.add(MIPSAssignOperation + " " + destination + ", " + source0);

            } else if (currentInstruction.isArrayAssign()) {
                String arrayName = currentInstruction.getDestination();
                String arrayValue = currentInstruction.getSource2();

                int arrayLength = intArrays.get(arrayName.trim());

                dataMIPS.add(arrayName + ": .word " + arrayValue + ":" + arrayLength);

            } else if (currentInstruction.isConditionalOp()) {
                if (currentInstruction.getOperation().equals("breq"))
                    assemblyMIPS.add("beq" +
                            " " + currentInstruction.getComparee0() +
                            ", " + currentInstruction.getComparee1() +
                            ", " + currentInstruction.getJumpLabel());

                if (currentInstruction.getOperation().equals("brneq"))
                    assemblyMIPS.add("bne" +
                            " " + currentInstruction.getComparee0() +
                            ", " + currentInstruction.getComparee1() +
                            ", " + currentInstruction.getJumpLabel());

                if (currentInstruction.getOperation().equals("brlt"))
                    assemblyMIPS.add("blt" +
                            " " + currentInstruction.getComparee0() +
                            ", " + currentInstruction.getComparee1() +
                            ", " + currentInstruction.getJumpLabel());

                if (currentInstruction.getOperation().equals("brgt"))
                    assemblyMIPS.add("bgt" +
                            " " + currentInstruction.getComparee0() +
                            ", " + currentInstruction.getComparee1() +
                            ", " + currentInstruction.getJumpLabel());

                if (currentInstruction.getOperation().equals("brgeq"))
                    assemblyMIPS.add("bge" +
                            " " + currentInstruction.getComparee0() +
                            ", " + currentInstruction.getComparee1() +
                            ", " + currentInstruction.getJumpLabel());

                if (currentInstruction.getOperation().equals("brleq"))
                    assemblyMIPS.add("ble" +
                            " " + currentInstruction.getComparee0() +
                            ", " + currentInstruction.getComparee1() +
                            ", " + currentInstruction.getJumpLabel());

            } else if (currentInstruction.isJump()) {
                assemblyMIPS.add("j"  + " " + currentInstruction.getDestination());

            } else if (currentInstruction.isStoreOp()) {
                String source0 = currentInstruction.getSource0();
                String source1 = currentInstruction.getSource1();

                if (globals.contains(source1)) {
                    assemblyMIPS.add("sw " + source0 + ", " + source1);
                }
                else {
                    int stackPos = variablesMap.get(source1);

                    assemblyMIPS.add("sw" + " " + source0 + ", " + stackPos + "($sp)");
                }


            } else if (currentInstruction.isLoadOp()) {
                String source0 = currentInstruction.getSource0();
                String source1 = currentInstruction.getSource1();

                //NOTE: Chase added the block below to load immediate of 99
                boolean immediate = false;

                try {
                    Double.parseDouble(source1);

                    immediate = true;

                } catch (NumberFormatException e) {
                }

                if (immediate) {
                    assemblyMIPS.add("li" + " " + source0 + ", " + source1);
                } else {
                    if (globals.contains(source1)) {
                        assemblyMIPS.add("lw " + source0 + ", " + source1);

                    }
                    else {
                        int stackPos = variablesMap.get(source1);

                        assemblyMIPS.add("lw" + " " + source0 + ", " + stackPos + "($sp)");

                    }

                }

            } else if (currentInstruction.isArrayLoad()) {
                String arrayName = currentInstruction.getSource0();
                String arrayLoadIndex = currentInstruction.getSource1();
                String arrayLoadDestination = currentInstruction.getDestination();

                assemblyMIPS.add("mulo" + " " + "$t0" + ", " + arrayLoadIndex + ", " + "4");
                assemblyMIPS.add("la" + " " + "$t1, " + arrayName);
                assemblyMIPS.add("add" + " " + "$t1" + ", " + "$t1" + ", " + "$t0");
                assemblyMIPS.add("lw" + " " + arrayLoadDestination + ", " + "0(" + "$t1" + ")");

            } else if (currentInstruction.isCallOp()) {
                String parameter = currentInstruction.getCallParam0();

                assemblyMIPS.add("lw" + " " + "$a0" + ", " + variablesMap.get(parameter) + "($sp)");
                assemblyMIPS.add("li" + " " + "$v0" + ", " + libFunctions.get(currentInstruction.getCallFunction()));
                assemblyMIPS.add("syscall");

            } else if (currentInstruction.isReturn()) {
                assemblyMIPS.add("move" + " " + "$v0" + ", " + currentInstruction.getReturnee());
                assemblyMIPS.add("lw" + " " + "$ra" + ", " + "16" + "($sp)");
                assemblyMIPS.add("addi $sp, $sp, " + stackPosition * 4);
                assemblyMIPS.add("jr $ra");

            } else if (currentInstruction.isCallr()) {
                if (!isMain) {
                    assemblyMIPS.add("sw $s0, 72($sp)");
                    assemblyMIPS.add("sw $s1, 76($sp)");
                    assemblyMIPS.add("sw $s2, 80($sp)");

                } else {
                    assemblyMIPS.add("sw $s0, 36($sp)");

                }

                assemblyMIPS.add("lw" + " " + "$a0" + ", " + variablesMap.get(currentInstruction.getCallrParam0()) + "($sp)");
                assemblyMIPS.add("jal" + " " + currentInstruction.getCallFunction());

                if (!isMain) {
                    assemblyMIPS.add("lw $s0, 72($sp)");
                    assemblyMIPS.add("lw $s1, 76($sp)");
                    assemblyMIPS.add("lw $s2, 80($sp)");

                } else {
                    assemblyMIPS.add("lw $s0, 36($sp)");

                }

                assemblyMIPS.add("sw" + " " + "$v0" + ", " + variablesMap.get(currentInstruction.getDestination()) + "($sp)");

            }

        }

        if (isMain) {
            assemblyMIPS.add("lw $ra, 16($sp)");
            assemblyMIPS.add("addi $sp, $sp, " + stackPosition * 4);

        }

        return isMain;

    }

    public List<String> getMIPS() {
        return assemblyMIPS;

    }


    public void generateHeritage(int start, int end) {
        List<String> children = new ArrayList<String>();
        String functionName = iRinstructions.get(start).getFunctionName();
        for (int i = start; i < end;i++) {
            if (iRinstructions.get(i).getOperation().equals("callr")) {
                String child = iRinstructions.get(i).getCallFunction();
                children.add(child);
            }
        }
        heritage.put(functionName,children);
    }

    public List<String> findFunctionGlobals(int start, int end) {
        intList = iRinstructions.get(start + 1).getListedVariables();
        floatList = iRinstructions.get(start + 2).getListedVariables();
        ArrayList<String> functionPresentVariables =  new ArrayList<String>();
        List<String> functionGlobals = new ArrayList<String>();
        for (int i = start; i < end;i++) {
            for (String var : iRinstructions.get(i).getPresentVariables()) {
                if (!functionPresentVariables.contains(var)) {
                    functionPresentVariables.add(var);
                }
            }
        }
        for (String presentVar : functionPresentVariables) {
            if (!intList.contains(presentVar.trim()) && !floatList.contains(presentVar.trim())) {
                functionGlobals.add(presentVar);
            }
        }
        return functionGlobals;
    }
}
