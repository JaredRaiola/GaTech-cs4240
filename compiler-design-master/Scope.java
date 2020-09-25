
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.lang.Integer;
import java.lang.Float;

public class Scope {


    private Scope parentScope;

    private String name;

    private String returnType;

    private HashMap<String, String> definedTypes;

    private HashMap<String, String> vars;

    private ArrayList<Scope> declaredFunctions;

    private ArrayList<String> parameters;

    private ArrayList<String> assigns;

    private ArrayList<String> operations;

    private Stack<String> tempIRRegisters;

    private int loopCount;

    private int whileCount;

    private int ifCount;

    private int tempIRRegisterCount;


    public Scope(String name, String returnType, Scope parentScope) {

        this.name = name;

        this.returnType = returnType;
        this.parentScope = parentScope;

        this.definedTypes = new HashMap<String, String>();
        this.vars = new HashMap<String, String>();
        this.declaredFunctions = new ArrayList<Scope>();
        this.parameters = new ArrayList<String>();
        this.assigns = new ArrayList<String>();
        this.operations = new ArrayList<String>();
        this.tempIRRegisters = new Stack<String>();

        this.loopCount = 0;
        this.whileCount = 0;
        this.ifCount = 0;

        this.tempIRRegisterCount = 0;

        if (name.equals(Config.MAIN_KEYWORD)) {

            Scope printsFunction = new Scope(Config.PRINTS_FUNCTION_NAME, Config.VOID, null);
            Scope printiFunction = new Scope(Config.PRINTI_FUNCTION_NAME, Config.VOID, null);
            Scope flushFunction = new Scope(Config.FLUSH_FUNCTION_NAME, Config.VOID, null);
            Scope getcharFunction = new Scope(Config.GETCHAR_FUNCTION_NAME, Config.STRING_KEYWORD, null);

            declaredFunctions.add(printsFunction);
            declaredFunctions.add(printiFunction);
            declaredFunctions.add(flushFunction);
            declaredFunctions.add(getcharFunction);

        }


    }


    public String toString(int space, int scopeNumber) {

        String s = "";

        for (int i = 0; i < space - 1; i++)
            s += Config.NEW_TAB;

        s += Config.SCOPE + Config.SPACE + scopeNumber + Config.COLON + Config.NEW_LINE;

        for (HashMap.Entry<String, String> definedType : definedTypes.entrySet()) {
            for (int i = 0; i < space; i++)
                s += Config.NEW_TAB;

            s +=      definedType.getKey() + Config.COMMA + Config.SPACE +
                       Config.TYPE_KEYWORD + Config.COMMA + Config.SPACE +
                    definedType.getValue() + Config.NEW_LINE;

        }

        for (HashMap.Entry<String, String> var : vars.entrySet()) {
            for (int i = 0; i < space; i++)
                s += Config.NEW_TAB;

            s +=              var.getKey() + Config.COMMA + Config.SPACE +
                        Config.VAR_KEYWORD + Config.COMMA + Config.SPACE +
                            var.getValue() + Config.NEW_LINE;

        }

        for (Scope functionScope : declaredFunctions) {
            for (int i = 0; i < space; i++)
                s += Config.NEW_TAB;

            s +=          functionScope.getName() + Config.COMMA + Config.SPACE +
                              Config.FUNC_KEYWORD + Config.COMMA + Config.SPACE +
                    functionScope.getReturnType() + Config.NEW_LINE;

            if (    !functionScope.name.equals(Config.PRINTS_FUNCTION_NAME) &&
                    !functionScope.name.equals(Config.PRINTI_FUNCTION_NAME) &&
                     !functionScope.name.equals(Config.FLUSH_FUNCTION_NAME) &&
                    !functionScope.name.equals(Config.GETCHAR_FUNCTION_NAME)) {
                TigerIRGeneratorListener.scope_number++;

                s += functionScope.toString(space + 1, TigerIRGeneratorListener.scope_number);

            }

        }

        return s;

    }

    public int addDefinedType(String typeName, String typeAssign, String arrayTypeLength) {
        if (arrayTypeLength.equals("-1")) definedTypes.put(typeName, typeAssign);
        else {
            definedTypes.put(typeName, typeAssign + Config.OPEN_BRACKET + arrayTypeLength + Config.CLOSED_BRACKET);
            typeAssign = typeAssign + Config.OPEN_BRACKET + arrayTypeLength + Config.CLOSED_BRACKET;

        }

        System.out.println(typeName + ", " + Config.TYPE_KEYWORD + ", " + typeAssign);

        return 0;

    }

    public int addDeclaredFunction(Scope functionDeclarationScope) {
        declaredFunctions.add(functionDeclarationScope);

        return 0;

    }

    public int addVar(String varName, String varType) {
        vars.put(varName, varType);

        // if varType is like A but A is not definedType then error cause that's not good

        System.out.println(varName + ", " + Config.VAR_KEYWORD + ", " + varType);

        return 0;

    }

    public boolean hasVar(String varName) {
        Scope currentScope = this;

        while (currentScope != null) {
            if (currentScope.vars.containsKey(varName)) return true;

            currentScope = currentScope.parentScope;

        }

        return false;

    }

    public boolean hasFunction(String functionName) {
        Scope currentScope = this;

        if (currentScope.name.equals(functionName)) return true;

        while (currentScope != null) {
            for (Scope function : currentScope.declaredFunctions)
                if (function.name.equals(functionName)) return true;

            currentScope = currentScope.parentScope;

        }

        return false;

    }

    public int addAssign(String variable, String value) {

        String variableType = checkExist(variable);

        String assignment;

        if (variableType.contains(Config.OPEN_BRACKET))
            assignment = "assign " +
                          variable +
                              ", " +
                       variableType
                                 .substring(variableType.indexOf(Config.OPEN_BRACKET) + 1,
                                       variableType.indexOf(Config.CLOSED_BRACKET)) + ", " + value;
        else if (definedTypes.get(variableType) != null && definedTypes.get(variableType).contains(Config.OPEN_BRACKET))
            assignment = "assign " +
                          variable +
                              ", " +
                          definedTypes.get(variableType)
                                  .substring(definedTypes.get(variableType).indexOf(Config.OPEN_BRACKET) + 1,
                                          definedTypes.get(variableType).indexOf(Config.CLOSED_BRACKET)) + ", " + value;
        else assignment = "assign " + variable + ", " + value;

        System.out.println(assignment);

        assigns.add(assignment);

        return 0;

    }

    public int addParameter(String parameterName, String parameterType) {

        this.addVar(parameterName, parameterType);

        parameters.add(parameterName);

        return 0;

    }

    public int addOperation3(String operation, String arg1, String arg2, String arg3) {
//        checkExist(arg1);
//        checkExist(arg2);
//        checkExist(arg3);


        operations.add(operation + " " + arg1 + ", " + arg2 + ", " + arg3);

        System.out.println(operation + " " + arg1 + ", " + arg2 + ", " + arg3);

        return 0;

    }

    public int addOperation2(String operation, String arg1, String arg2) {
        operations.add(operation + " " + arg1 + ", " + arg2);

        System.out.println(operation + " " + arg1 + ", " + arg2);

        return 0;

    }

    public int addOperation1(String operation, String arg1) {
//        checkExist(arg1);

        operations.add(operation + " " + arg1);

        System.out.println(operation + " " + arg1);

        return 0;

    }

    public int addOperation0(String operation) {
        operations.add(operation);

        System.out.println(operation);

        return 0;

    }

    public String insertWhile() {
        String whileGotoLabel = Config.WHILE_LABEL + whileCount;

        whileCount++;

        addOperation0(whileGotoLabel + Config.OP_BRANCH_GOTO_LABEL_COLON);

        return whileGotoLabel;

    }

    public String getLastInnerWhileLabel() {
        return Config.WHILE_LABEL + (whileCount - 1);

    }

    public String insertLoop() {
        String loopGotoLabel = Config.LOOP_LABEL + loopCount;

        loopCount++;

        addOperation0(loopGotoLabel + Config.OP_BRANCH_GOTO_LABEL_COLON);

        return loopGotoLabel;

    }

    public String getBreakAfterLoopLabel() {
        return Config.LOOP_LABEL + loopCount;

    }

    public String getLastInnerLoopLabel() {
        return Config.LOOP_LABEL + (loopCount - 1);

    }

    public String insertIF() {
        String loopGotoLabel = Config.IF_LABEL + (ifCount - 1);

        addOperation0(loopGotoLabel + Config.OP_BRANCH_GOTO_LABEL_COLON);

        return loopGotoLabel;

    }

    public String createIFLabel() {
        String ifLabel = Config.IF_LABEL + ifCount;

        ifCount++;

        return ifLabel;

    }

    public String getNewTempIRRegister(String tempRegisterType) {
        String tempRegister = Config.REGISTER_NAME + tempIRRegisterCount;

        vars.put(tempRegister, tempRegisterType);

        tempIRRegisters.push(tempRegister);

        tempIRRegisterCount++;

        return tempRegister;

    }

    public String checkExist(String variable) {
        Scope currentScope = this;

        String variableType = definedTypes.get(variable);

//        try {
//            Integer.parseInt(variable);
//            return Config.INT;
//        } catch(NumberFormatException e) {
//        }
//
//        try {
//            Float.parseFloat(variable);
//            return Config.FLOAT;
//        } catch(NumberFormatException e) {
//        }

        while (currentScope != null && variableType == null) {
            variableType = currentScope.vars.get(variable);

            currentScope = currentScope.parentScope;

        }

        if (variableType == null) throw new RuntimeException(variable + Config.SPACE + Config.VARIABLE_NOT_DEFINED_ERROR + Config.DOT);

        return variableType;

    }


    public String getLastTempIRRegister() {
        return (this.noTempRegisters()) ? this.getNewTempIRRegister(Config.INT) : tempIRRegisters.peek();

    }

    public boolean noTempRegisters() {
        return tempIRRegisters.empty();

    }

    public int emptyTempIRRegisters() {
        tempIRRegisters.clear();

        tempIRRegisterCount = 0;

        return 0;

    }

    public String getName() { return name; }

    public String getReturnType() { return returnType; }



}
