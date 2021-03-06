import java.util.List;
import java.util.ArrayList;

public class NaiveAllocator {

    private List<IRLine> allocatedLines;

    private List<IRLine> unAllocatedLines;

    private String intStoreRegister = "$s0";
    private String intLoad0Register = "$s1";
    private String intLoad1Register = "$s2";

    private String floatStoreRegister = "$ft2";
    private String floatLoad0Register = "$ft1";
    private String floatLoad1Register = "$ft0";

    List<String> intVariables;
    List<String> floatVariables;

    public NaiveAllocator(List<IRLine> IRLines) {
        allocatedLines = new ArrayList<IRLine>();
        unAllocatedLines = IRLines;

        intVariables = null;
        floatVariables = null;

        int index = -1;

        for (IRLine currentLine : IRLines) {
            index++;

            //The line is a comment
            if (currentLine.isComment()) {continue;}


            //TODO PROBALY NEEDS TO BE CHANGE TO BE MORE THAN JUST ADDING THE LINE
            if (currentLine.isFunctionDeclaration()) {
                allocatedLines.add(currentLine);

                continue;
            }

            //TODO PROBALY NEEDS TO BE CHANGE TO BE MORE THAN JUST ADDING THE LINE
            if(currentLine.isLabel()) {
                allocatedLines.add(currentLine);

                continue;
            }



            String operationType = currentLine.getOperationType();

            if (operationType.equals("intList")) {
                intVariables = currentLine.getListedVariables();
                allocatedLines.add(currentLine);

            }
            else if (operationType.equals("floatList")) {
                floatVariables = currentLine.getListedVariables();
                allocatedLines.add(currentLine);
            }

            else if (operationType.equals("returnOp")) {
                String operation = currentLine.getOperation();
                String dest = currentLine.getDestination();
                String load0Register = intLoad0Register;

                if (floatVariables.contains(dest)) {
                    load0Register = floatLoad0Register;
                }

                if (dest.trim().length() == 0) {
                    allocatedLines.add(currentLine);
                    continue;
                }
                else {

                    if (!isNumeric(dest)) {
                        IRLine newLoad = generateLoadLine(load0Register,dest,index, currentLine);
                        if (newLoad != null) {
                            allocatedLines.add(newLoad);
                        }
                        dest = load0Register;
                    }


                    allocatedLines.add(generateLine(operation,dest,"", currentLine));
                }



            }

            else if (operationType.equals("assignOp")) {
                String operation = currentLine.getOperation();
                String dest = currentLine.getDestination();
                String source0 = currentLine.getSource0();
                String load0Register = intLoad0Register;
                String storeRegister = intStoreRegister;

                if (floatVariables.contains(dest)) {
                    load0Register = floatLoad0Register;
                    storeRegister = floatStoreRegister;
                }

                if (!isNumeric(source0)) {
                    IRLine newLoad = generateLoadLine(load0Register,source0,index, currentLine);
                    if (newLoad != null) {
                        allocatedLines.add(newLoad);
                    }
                    source0 = load0Register;
                    IRLine temp = generateStoreLine(source0,dest, currentLine);
                    if (temp != null) {
                        allocatedLines.add(temp);
                    }

                }
                else {
                    allocatedLines.add(generateLine(operation,storeRegister,source0, currentLine));
                    IRLine temp = generateStoreLine(storeRegister,dest,currentLine);
                    if (temp != null) {
                        allocatedLines.add(temp);
                    }
                }

            }

            else if (operationType.equals("binaryOp")) {
                String dest = currentLine.getDestination();
                String source0 = currentLine.getSource0();
                String source1 = currentLine.getSource1();
                String operation = currentLine.getOperation();
                String load0Register = intLoad0Register;
                String load1Register = intLoad1Register;
                String storeRegister = intStoreRegister;

                if (floatVariables.contains(dest)) {
                    load0Register = floatLoad0Register;
                    storeRegister = floatStoreRegister;

                }
                if (!isNumeric(source0) || true) {
                    IRLine newLoad = generateLoadLine(load0Register,source0,index, currentLine);
                    if (newLoad != null) {
                        allocatedLines.add(newLoad);

                    }

                    source0 = load0Register;

                }

                if (!isNumeric(source1) || true) {
                    IRLine newLoad = generateLoadLine(load1Register,source1,index, currentLine);
                    if (newLoad != null) {
                        allocatedLines.add(newLoad);
                    }
                    source1 = load1Register;

                }

                allocatedLines.add(generateLine(operation,source0,source1,storeRegister, currentLine));
                IRLine temp = generateStoreLine(storeRegister,dest, currentLine);
                if (temp != null) {
                    allocatedLines.add(temp);
                }

            }

            else if (operationType.equals("conditionalOp")) {
                String operation = currentLine.getOperation();
                String comparee0 = currentLine.getComparee0();
                String comparee1 = currentLine.getComparee1();
                String jumpLabel = currentLine.getJumpLabel();

                if (!isNumeric(comparee0)) {
                    IRLine temp = generateLoadLine(intLoad0Register,comparee0,index, currentLine);
                    if (temp != null) {
                        allocatedLines.add(temp);
                    }
                    comparee0 = intLoad0Register;

                }

                //NOTE: Chase added or true to fix the conditional load immediate issue.
                if (!isNumeric(comparee1) || true) {
                    IRLine newLoad = generateLoadLine(intLoad1Register,comparee1,index,currentLine);

                    if (newLoad != null) {
                        allocatedLines.add(newLoad);

                    }
                    comparee1 = intLoad1Register;

                }
                else {

                }

                allocatedLines.add(generateLine(operation,comparee0,comparee1,jumpLabel, currentLine));

            }

            else if (operationType.equals("callOp")) {
                String operation = currentLine.getOperation();
                String callParam0 = currentLine.getCallParam0();
                String callFunction = currentLine.getCallFunction();

//                if (!isNumeric(callParam0)) {
//                    IRLine newLoad = generateLoadLine(intLoad0Register,callParam0,index);
//                    if (newLoad != null) {
//                        allocatedLines.add(newLoad);
//                    }
//                    callParam0 = intLoad0Register;
//                }

                allocatedLines.add(generateLine(operation,callFunction,callParam0, currentLine));

            }

            else if (operationType.equals("arrayAssignOp")) {
                String operation = currentLine.getOperation();
                String arrayName = currentLine.getArrayAssignName();
                String arrayLength = currentLine.getArrayAssignLength();
                String arrayInitialVal = currentLine.getArrayAssignValue();

                allocatedLines.add(currentLine);

            }

            else if (operationType.equals("arrayLoadOp")) {
                String operation = currentLine.getOperation();
                String arrayIndex = currentLine.getArrayIndex();
                String array = currentLine.getArray();
                String dest = currentLine.getDestination();
                String load0Register = intLoad0Register;
                String storeRegister = intStoreRegister;


                if (!isNumeric(arrayIndex)) {
                    IRLine newLoad = generateLoadLine(intLoad1Register,arrayIndex,index,currentLine);
                    if (newLoad != null) {
                        allocatedLines.add(newLoad);
                    }
                    arrayIndex = intLoad1Register;
                }

                allocatedLines.add(generateLine(operation,storeRegister,array,arrayIndex, currentLine));
                IRLine temp = generateStoreLine(storeRegister,dest, currentLine);
                if (temp != null) {
                    allocatedLines.add(temp);
                }

            }



            else {
                allocatedLines.add(currentLine);

            }

        }

//        for (IRLine currentLine : allocatedLines) System.out.println(currentLine.toString());

    }

    public List<IRLine> getAllocatedLines() { return allocatedLines; }

    private IRLine generateLoadLine(String destination, String source, int index, IRLine currentLine) {
        if (!(currentLine.getLoadSource0() && source.equals(currentLine.getLoad0VarName())) && !(currentLine.getLoadSource1() && source.equals(currentLine.getLoad1VarName()))) {
            return null;
        }

        if (currentLine.getLoadSource0() && source.equals(currentLine.getLoad0VarName())) {
            source = "$s" + (3 + currentLine.getTRegister0());
        }

        if (currentLine.getLoadSource1() && source.equals(currentLine.getLoad1VarName())) {
            source = "$s" + (3 + currentLine.getTRegister1());
        }

        String operation = "load";
        IRLine before1 = unAllocatedLines.get(index-1);
        IRLine before2 = unAllocatedLines.get(index-2);
        if (before1.getOperation().equals("array_load")) {
            if (destination.equals(before1.getDestination())) {
                return null;
            }
        }
        if (before2.getOperation().equals("array_load")) {
            if (destination.equals(before2.getDestination())) {
                return null;
            }
        }
        if (isNumeric(source)) {
            operation = "assign";
        }
        return new IRLine(new String[]{operation, destination, source});
    }

    private IRLine generateStoreLine(String source, String destination, IRLine currentLine) {
        if (!(currentLine.getStoreSource0())) {
            return null;
        }

        if (currentLine.getStoreSource0()) {
            source = "$s" + (3 + currentLine.getTRegister2());
        }
        return new IRLine(new String[]{"store", source, destination});
    }

    private IRLine generateLine(String operation, String destination, String source0, IRLine currentLine) {
        if (currentLine.getTRegister0() > -1)
            source0 = "$s" + (3 + currentLine.getTRegister0());
        if (currentLine.getTRegister2() > -1)
            destination = "$s" + (3 + currentLine.getTRegister2());
        return new IRLine(new String[]{operation,destination,source0});
    }

    private IRLine generateLine(String operation, String destination, String source0, String source1, IRLine currentLine) {
        if (currentLine.getTRegister0() > -1)
            source0 = "$s" + (3 + currentLine.getTRegister0());
        if (currentLine.getTRegister1() > -1)
            source1 = "$s" + (3 + currentLine.getTRegister1());
        if (currentLine.getTRegister2() > -1)
            destination = "$s" + (3 + currentLine.getTRegister2());
        return new IRLine(new String[]{operation,destination,source0,source1});
    }

    public List<String> getIntVariables() {
        return intVariables;

    }

    public List<String> getFloatVariables() {
        return floatVariables;

    }


    //source https://www.baeldung.com/java-check-string-number
    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);

        } catch (NumberFormatException | NullPointerException nfe) {
            return false;

        }
        return true;

    }

}


