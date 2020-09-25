import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class IRLine {
    private boolean storeSource0 = false;
    private boolean loadSource0 = false;
    private boolean loadSource1 = false;
    private int tRegister0 = -1;
    private int tRegister1 = -1;
    private int tRegister2 = -1;
    private String load0VarName;
    private String load1VarName;
    private String storeVarName;

    public String[] fields;
    private boolean isLabel;
    private boolean isJump;
    private boolean isBinaryOp;
    private boolean isAssign;
    private boolean isConditionalOp;
    private boolean isCallOp;
    private boolean isComment;
    private boolean isFunctionDeclaration;
    private boolean isStore;
    private boolean isLoad;
    private boolean isArrayLoad;
    private boolean isArrayAssign;
    private boolean isReturn;
    private boolean isCallr;
    private String operationType;
    private List<String> binaryOps = Arrays.asList("add","sub","mult","div","and","or");
    private List<String> conditionalOps = Arrays.asList("breq","brneq","brlt","brgt","brgeq","brleq");
    private ArrayList<String> presentVariables = new ArrayList<String>();

    public IRLine(String[] argFields) {
        fields = new String[argFields.length];
        for (int i = 0; i < argFields.length; i++) {
            if (argFields[i] != null) {
                argFields[i] = argFields[i].trim();

            }
        }
        System.arraycopy(argFields,0,fields,0,argFields.length);
        isLabel = false;
        isComment = false;
        isFunctionDeclaration = false;
        isStore = false;
        isLoad = false;
        storeSource0 = false;
        loadSource0 = false;
        loadSource1 = false;

        if (fields[0].contains("#")) {
            isComment = true;
        }

        else if (fields[0].equals("return") && fields.length > 1 && fields[1].length() > 1) {
            operationType = "returnOp";
            isReturn = true;

        }

        else if (fields.length >= 1 && fields[0].contains("):")) {
            isFunctionDeclaration = true;
        }

        else if (fields[0].contains("int-list")) {
            operationType = "intList";

        }

        else if (fields[0].contains("float-list")) {
            operationType = "floatList";
        }

        else if (fields[0].contains("array_load")) {
            operationType = "arrayLoadOp";
            isArrayLoad = true;

        }

        else if (fields.length == 1 && !fields[0].contains("return")) {
            isLabel = true;

        }

        else if (fields[0].equals("goto")) {
            operationType = "gotoOp";
            isJump = true;

        }

        else if (fields.length == 3 && fields[0].equals("assign")) {
            operationType = "assignOp";
            isAssign = true;

        }

        else if (fields.length == 3 && fields[0].equals("call")) {
            operationType = "callOp";
            isCallOp = true;
        }

        else if (fields[0].equals("callr")) {
            operationType = "callrOp";
            isCallr = true;

        }

        else if (fields.length == 4 && fields[0].equals("assign")) {
            operationType = "arrayAssignOp";
            isArrayAssign = true;

        }

        //is binary operation
        else if (fields.length == 4 && binaryOps.contains(fields[0])) {
            operationType = "binaryOp";
            isBinaryOp = true;

        }

        else if (fields.length == 4 && conditionalOps.contains(fields[0])) {
            operationType = "conditionalOp";
            isConditionalOp = true;

        }

        else if (fields[0].equals("store")) {
            isStore = true;
            operationType = "store";
            presentVariables.add(fields[2]);
        }

        else if (fields[0].equals("load")) {
            isLoad = true;
            operationType = "load";
            presentVariables.add(fields[2]);

        }

        else {
            operationType = "otherOp";
        }

    }


    public void setStoreSource0() { this.storeSource0 = true; }

    public boolean getStoreSource0() { return this.storeSource0; }



    public void setLoadSource0() { this.loadSource0 = true; }

    public boolean getLoadSource0() { return this.loadSource0; }

    public void setLoadSource1() { this.loadSource1 = true; }

    public boolean getLoadSource1() { return this.loadSource1; }



    public void setTRegister0(int regi) { this.tRegister0 = regi; }

    public int getTRegister0() { return this.tRegister0; }

    public void setTRegister1(int regi) { this.tRegister1 = regi; }

    public int getTRegister1() { return this.tRegister1; }

    public void setTRegister2(int regi) { this.tRegister2 = regi; }

    public int getTRegister2() { return this.tRegister2; }



    public void setLoad0VarName(String name) { this.load0VarName = name; }

    public String getLoad0VarName() { return this.load0VarName; }

    public void setLoad1VarName(String name) { this.load1VarName = name; }

    public String getLoad1VarName() { return this.load1VarName; }

    public void setStoreVarName(String name) { this.storeVarName = name; }

    public String getStoreVarName() { return this.storeVarName; }


    public boolean isComment() { return isComment; }

    public boolean isLabel() { return isLabel; }

    public String getLabel() { return fields[0]; }

    public boolean isJump() { return isJump; }

    public boolean isReturn() { return isReturn; }

    public boolean isBinaryOp() { return isBinaryOp; }

    public boolean isAssign() { return isAssign; }

    public boolean isArrayAssign() { return isArrayAssign; }

    public boolean isConditionalOp() { return isConditionalOp; }

    public boolean isStoreOp() { return isStore; }

    public boolean isArrayLoad() { return isArrayLoad; }

    public boolean isLoadOp() { return isLoad; }

    public boolean isCallr() { return isCallr; }

    public String getOperationType() { return operationType; }

    public String getOperation() { return fields[0]; }

    public String getDestination() {
        if (operationType.equals("assignOp"))
            return fields[1];

        if (operationType.equals("gotoOp"))
            return fields[1];

        if (operationType.equals("binaryOp"))
            return fields[3];

        if (operationType.equals("arrayLoadOp"))
            return fields[1];

        if (operationType.equals("returnOp")) {
            try {
                return fields[1];
            }
            catch (Exception e){
                return "";
            }

        }

        if (isCallr) return fields[1];

        if (isArrayAssign)
            return fields[1];

        if (isArrayLoad)
            return fields[1];

        return null;
    }

    public String getSource0() {
        if (operationType.equals("assignOp"))
            return fields[2];

        if (operationType.equals("binaryOp"))
            return fields[1];

        if (operationType.equals("load"))
            return fields[1].trim();

        if (operationType.equals("store"))
            return fields[1].trim();

        if (isArrayLoad)
            return fields[2];

        return "";

    }

    public String getSource1() {
        if (operationType.equals("binaryOp"))
            return fields[2];

        if (operationType.equals("load"))
            return fields[2].trim();

        if (operationType.equals("store"))
            return fields[2].trim();

        if (isArrayLoad)
            return fields[3];

        if (operationType.equals("assignOp"))
            return "";

        return "";

    }

    public String getSource2() {
        if (operationType.equals("assignOp"))
            return "";
        if (isArrayAssign) return fields[3];

        return "";
    }

    public String getReturnee() {
        if (isReturn) return fields[1];

        return null;

    }


    public String getComparee0() { return fields[1]; }

    public String getComparee1() { return fields[2]; }

    public String getJumpLabel() { return fields[3]; }

    public String getCallParam0() { return fields[2]; }

    public String getCallrParam0() { return fields[3]; }

    public String getCallFunction() {
        if (operationType.equals("callrOp")) return fields[2];

        return fields[1];

    }

    public String getArrayIndex() {
        return fields[3];
    }

    public String getArray() {
        return fields[2];
    }

    public List<String> getListedVariables() {
        List<String> variables = new ArrayList<>();

        if (operationType.equals("intList") || operationType.equals("floatList")) {
            if (fields.length < 2) {return variables;}
            variables.add(fields[0].split(": ")[1]);
            for (int i = 1; i < fields.length; i++) {
                variables.add(fields[i].trim());
            }
        }


        return variables;

    }

    public ArrayList<String> getPresentVariables() {
        return presentVariables;
    }


    public String getArrayAssignName() { return fields[1]; }

    public String getArrayAssignLength() { return fields[2]; }

    public String getArrayAssignValue()  { return fields[3]; }


    public boolean isFunctionDeclaration() {
        return isFunctionDeclaration;
    }

    public String getFunctionName() {
        return fields[0].split("\\(")[0].split(" ")[1];
    }

    public String toString() {
        String returnString = "";

        for (String field : fields) {

            returnString += field + ", ";

        }

        returnString.trim();

        return returnString.substring(0,returnString.length()-2);

    }

    public String getJumpLabelGoto() {
        return fields[1];
    }


    public boolean isCallOp() { return isCallOp; }

    public String[] getFields() {
        return fields;
    }

    public List<String> getFunctionParam() {
        List<String> tempList = new ArrayList<String>();
        List<String> returnList = new ArrayList<String>();
        if (!fields[0].contains("()")){
            Collections.addAll(tempList,fields[0].split("\\(")[1].replaceAll("\\):","").split(","));
            if (!tempList.isEmpty()) {
                for (String param : tempList) {
                    Collections.addAll(returnList, param.trim().split(" ")[1]);
                }
                return returnList;
            }
        }
        return tempList;
    }

}