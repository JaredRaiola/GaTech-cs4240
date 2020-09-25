
import java.util.Stack;

public class TigerIRGeneratorListener extends TigerBaseListener {


    private TigerSymbolTable programSymbolTable;

    private String programTigerGeneratedIRCode;

    private Stack<Scope> scopes;

    public static int scope_number = 0;

    public String tiger_program_input_filename;


    public TigerIRGeneratorListener(TigerSymbolTable programSymbolTable, String parentScopeName, String parentScopeReturnType) {

        this.programSymbolTable = programSymbolTable;
        this.scopes = new Stack<Scope>();
        scopes.push(new Scope(parentScopeName, parentScopeReturnType, new Scope(Config.ROOT, Config.VOID, null)));

        this.programTigerGeneratedIRCode = Config.EMPTY_STRING;
        this.tiger_program_input_filename = Config.EMPTY_STRING;

    }

    public String printSymbolTableScopes() {

        String symbol_table_representation = "";
        try {
            for (int i = 0; i < scopes.size(); i++) {
                TigerIRGeneratorListener.scope_number++;
                symbol_table_representation += scopes.get(i).toString(i + 1, TigerIRGeneratorListener.scope_number);

            }

        } catch (Exception e) {
            System.out.println(Config.NEW_LINE + Config.SYMBOL_TABLE_GENERATION_ERROR_MESSAGE + tiger_program_input_filename);

        }

        return symbol_table_representation;

    }

    @Override
    public void exitType_declaration(TigerParser.Type_declarationContext ctx) {
        super.enterType_declaration(ctx);

        try {
            String typeName = ctx.getChild(1).getText();
            String typeAssign = ctx.getChild(3).getText();
            String arrayTypeLength = "-1";

            if (typeAssign.startsWith(Config.ARRAY_KEYWORD)) {
                typeAssign = ctx.getChild(3).getChild(5).getText();
                arrayTypeLength = ctx.getChild(3).getChild(2).getText();

            }

            scopes.peek().addDefinedType(typeName, typeAssign, arrayTypeLength);

        } catch (Exception e) {
            throw new RuntimeException(Config.TIGER_RUNTIME_EXCEPTION + Config.COLON + Config.SPACE +
                                        "Tiger type declaration" + Config.IN_LINE + ctx.start.getLine() +
                    Config.OF_PROGRAM + tiger_program_input_filename);

        }

    }

    @Override
    public void enterVar_declaration(TigerParser.Var_declarationContext ctx) {
        super.enterVar_declaration(ctx);

        try {
            String variables[] = ctx.getChild(1).getText().split(",");
            String variableType = ctx.getChild(3).getText();
            String arrayTypeLength = "-1";

            if (variableType.startsWith(Config.ARRAY_KEYWORD)) {
                variableType = ctx.getChild(3).getChild(5).getText();
                arrayTypeLength = ctx.getChild(3).getChild(2).getText();

                variableType = variableType + Config.OPEN_BRACKET + arrayTypeLength + Config.CLOSED_BRACKET;

            }

            boolean optionalInit = ctx.getChild(4).getChild(0) != null && ctx.getChild(4).getChild(0).getText().equals(Config.ASSIGN);

            for (int i = 0; i < variables.length; i++) {
                scopes.peek().addVar(variables[i], variableType);

                if (optionalInit) scopes.peek().addAssign(variables[i], ctx.getChild(4).getChild(1).getText());

            }

        } catch (Exception e) {
            throw new RuntimeException(Config.TIGER_RUNTIME_EXCEPTION + Config.COLON + Config.SPACE +
                    "Tiger variable declaration" + Config.IN_LINE + ctx.start.getLine() +
                    Config.OF_PROGRAM + tiger_program_input_filename);

        }

    }

    @Override
    public void enterFunction_declaration(TigerParser.Function_declarationContext ctx) {
        super.enterFunction_declaration(ctx);

        try {
            String functionName = ctx.getChild(1).getText();
            String functionReturnType = (ctx.getChild(5).getChild(0) != null) ? ctx.getChild(5).getChild(1).getText() : "void";

            Scope functionDec = new Scope(functionName, functionReturnType, scopes.peek());

            scopes.peek().addDeclaredFunction(functionDec);

            scopes.push(functionDec);

        } catch (Exception e) {
            throw new RuntimeException(Config.TIGER_RUNTIME_EXCEPTION + Config.COLON + Config.SPACE +
                    "Tiger function declaration" + Config.IN_LINE + ctx.start.getLine() +
                    Config.OF_PROGRAM + tiger_program_input_filename);

        }

    }

    @Override
    public void exitFunction_declaration(TigerParser.Function_declarationContext ctx) {
        super.exitFunction_declaration(ctx);

        try {
            scopes.pop();

        } catch (Exception e) {
            throw new RuntimeException(Config.TIGER_RUNTIME_EXCEPTION + Config.COLON + Config.SPACE +
                    "Tiger function declaration" + Config.IN_LINE + ctx.start.getLine() +
                    Config.OF_PROGRAM + tiger_program_input_filename);

        }

    }

    @Override
    public void enterParam(TigerParser.ParamContext ctx) {
        super.enterParam(ctx);

        try {
            this.scopes.peek().addParameter(ctx.getChild(0).getText(), ctx.getChild(2).getText());

        } catch (Exception e) {
            throw new RuntimeException(Config.TIGER_RUNTIME_EXCEPTION + Config.COLON + Config.SPACE +
                    "Tiger function parameter declaration" + Config.IN_LINE + ctx.start.getLine() +
                    Config.OF_PROGRAM + tiger_program_input_filename);

        }

    }

    @Override
    public void enterStat(TigerParser.StatContext ctx) {
        super.enterStat(ctx);

        boolean variableNotDefinedSemanticErrorCheck = false;

        try {
            if (ctx.getChild(0).getText().equals(Config.FOR_KEYWORD)) {

                scopes.peek().addVar(ctx.getChild(1).getText(), Config.INT);

                // value of the loop counter expression
                // value of the loop end expression

                scopes.peek().addAssign(ctx.getChild(1).getText(), ctx.getChild(3).getText());

                String loop_label = scopes.peek().insertLoop();

                int loopNumber = Integer.parseInt(loop_label.substring(Config.LOOP_LABEL.length()));

                String afterLoop_label = Config.LOOP_LABEL + (loopNumber + 1);

                scopes.peek().addOperation3(Config.OP_BRANCH_brgt, ctx.getChild(1).getText(), ctx.getChild(5).getText(), afterLoop_label);

                scopes.peek().addOperation3(Config.OP_ADD, Config.ONE, ctx.getChild(1).getText(), ctx.getChild(1).getText());

            } else if (scopes.peek().hasVar(ctx.getChild(0).getText())) {
                scopes.peek().addAssign(ctx.getChild(0).getText(), scopes.peek().getLastTempIRRegister());

            } else if (ctx.getChild(0).getText().equals(Config.BREAK_KEYWORD)) {
                scopes.peek().addOperation1(Config.OP_GOTO, scopes.peek().getBreakAfterLoopLabel());

            } else if (ctx.getChild(0).getText().equals(Config.RETURN_KEYWORD)) {
                scopes.peek().addOperation1(Config.OP_RETURN, ctx.getChild(1).getText());

            } else if (ctx.getChild(0).getText().equals(Config.LET_KEYWORD)) {
                Scope newLetScope = new Scope(Config.LET_KEYWORD, Config.VOID, scopes.peek());

                scopes.push(newLetScope);

            } else if (ctx.getChild(0).getText().equals(Config.IF_KEYWORD)) {
                String operation = "";

                if (ctx.getChild(1).getText().contains(Config.SIGN_EQ))
                    operation = Config.OP_BRANCH_brneq;
                if (ctx.getChild(1).getText().contains(Config.SIGN_NEQ))
                    operation = Config.OP_BRANCH_breq;
                if (ctx.getChild(1).getText().contains(Config.SIGN_GREATER))
                    operation = Config.OP_BRANCH_brleq;
                if (ctx.getChild(1).getText().contains(Config.SIGN_LESSER))
                    operation = Config.OP_BRANCH_brgeq;
                if (ctx.getChild(1).getText().contains(Config.SIGN_GREATEREQ))
                    operation = Config.OP_BRANCH_brlt;
                if (ctx.getChild(1).getText().contains(Config.SIGN_LESSEREQ))
                    operation = Config.OP_BRANCH_brgt;

                String ifLoopLabel = scopes.peek().createIFLabel();

                scopes.peek().addOperation3(operation, ctx.getChild(1).getChild(0).getChild(0).getText(), ctx.getChild(1).getChild(0).getChild(2).getText(), ifLoopLabel);

            } else if (scopes.peek().hasFunction(ctx.getChild(0).getText())) {
                scopes.peek().addOperation2(Config.OP_CALL, ctx.getChild(0).getText(), ctx.getChild(1).getChild(1).getText());

            } else if (ctx.getChild(0).getText().equals(Config.WHILE_KEYWORD)) {
                String while_label = scopes.peek().insertWhile();

                int whileNumber = Integer.parseInt(while_label.substring(Config.WHILE_LABEL.length()));

                String afterWhile_label = Config.WHILE_LABEL + (whileNumber + 1);

                String whileConOperation = "";

                if (ctx.getChild(1).getText().contains(Config.SIGN_EQ))
                    whileConOperation = Config.OP_BRANCH_brneq;
                if (ctx.getChild(1).getText().contains(Config.SIGN_NEQ))
                    whileConOperation = Config.OP_BRANCH_breq;
                if (ctx.getChild(1).getText().contains(Config.SIGN_GREATER))
                    whileConOperation = Config.OP_BRANCH_brleq;
                if (ctx.getChild(1).getText().contains(Config.SIGN_LESSER))
                    whileConOperation = Config.OP_BRANCH_brgeq;
                if (ctx.getChild(1).getText().contains(Config.SIGN_GREATEREQ))
                    whileConOperation = Config.OP_BRANCH_brlt;
                if (ctx.getChild(1).getText().contains(Config.SIGN_LESSEREQ))
                    whileConOperation = Config.OP_BRANCH_brgt;

                scopes.peek().addOperation3(whileConOperation,
                                            ctx.getChild(1).getChild(0).getChild(0).getText(),
                                            ctx.getChild(1).getChild(0).getChild(2).getText(),
                                            afterWhile_label);

            } else {
                variableNotDefinedSemanticErrorCheck = true;

            }

        } catch (Exception e) {
            throw new RuntimeException(Config.TIGER_RUNTIME_EXCEPTION + Config.COLON + Config.SPACE +
                    "Tiger sequential statement" + Config.IN_LINE + ctx.start.getLine() +
                    Config.OF_PROGRAM + tiger_program_input_filename);

        }

        if (variableNotDefinedSemanticErrorCheck) throw new RuntimeException(
                                "'" + ctx.getChild(0).getText() + "'" +
                                Config.SPACE +
                                Config.VARIABLE_NOT_DEFINED_ERROR +
                                ", during Tiger sequential statement" +
                                Config.IN_LINE + ctx.start.getLine() +
                                Config.OF_PROGRAM + tiger_program_input_filename +
                                Config.DOT + Config.SPACE +
                                Config.TIGER_COMPILE_UNSUCCESSFUL + Config.DOT);

    }

    @Override
    public void exitStat(TigerParser.StatContext ctx) {
        super.exitStat(ctx);

        try {
            if (ctx.getChild(0).getText().equals(Config.FOR_KEYWORD)) {
                scopes.peek().addOperation1(Config.OP_GOTO, scopes.peek().getLastInnerLoopLabel());
                scopes.peek().insertLoop();

            } else if (ctx.getChild(0).getText().equals(Config.LET_KEYWORD)) {
                scopes.pop();

            } else if (ctx.getChild(0).getText().equals(Config.IF_KEYWORD)) {
                scopes.peek().insertIF();

            } else if (ctx.getChild(0).getText().equals(Config.WHILE_KEYWORD)) {
                scopes.peek().addOperation1(Config.OP_GOTO, scopes.peek().getLastInnerWhileLabel());
                scopes.peek().insertWhile();

            }

        } catch (Exception e) {
            throw new RuntimeException(Config.TIGER_RUNTIME_EXCEPTION + Config.COLON + Config.SPACE +
                    "Tiger sequential statement" + Config.IN_LINE + ctx.start.getLine() +
                    Config.OF_PROGRAM + tiger_program_input_filename);

        }

    }

    @Override
    public void enterExpr(TigerParser.ExprContext ctx) {
        super.enterExpr(ctx);

        try {
            String operation;
            String secondSourceTempRegisterVariable;
            String destTempRegisterVariable;

            if (ctx.getChildCount() > 1) {
                for (int i = 1; i < ctx.getChildCount(); i += 2) {

                    operation = (ctx.getChild(i).getText().equals(Config.SIGN_OR)) ? Config.OP_OR : Config.OP_AND;

                    if (scopes.peek().noTempRegisters()) {
                        destTempRegisterVariable = scopes.peek().getNewTempIRRegister(Config.INT);

                        scopes.peek().addOperation3(operation,
                                ctx.getChild(i - 1).getText(),
                                ctx.getChild(i + 1).getText(), destTempRegisterVariable);

                    } else {
                        secondSourceTempRegisterVariable = scopes.peek().getLastTempIRRegister();

                        destTempRegisterVariable = scopes.peek().getNewTempIRRegister(Config.INT);

                        scopes.peek().addOperation3(operation,
                                secondSourceTempRegisterVariable,
                                ctx.getChild(i + 1).getText(), destTempRegisterVariable);

                    }

                }

            }

        } catch (Exception e) {
            throw new RuntimeException(Config.TIGER_RUNTIME_EXCEPTION + Config.COLON + Config.SPACE +
                    "Tiger logical expression statement" + Config.IN_LINE + ctx.start.getLine() +
                    Config.OF_PROGRAM + tiger_program_input_filename);

        }

    }

    @Override
    public void exitExpr(TigerParser.ExprContext ctx) {
        super.exitExpr(ctx);

        try {
//            scopes.peek().emptyTempIRRegisters();

        } catch (Exception e) {
            throw new RuntimeException(Config.TIGER_RUNTIME_EXCEPTION + Config.COLON + Config.SPACE +
                    "Tiger logical expression statement" + Config.IN_LINE + ctx.start.getLine() +
                    Config.OF_PROGRAM + tiger_program_input_filename);

        }

    }

    @Override
    public void enterE_expr(TigerParser.E_exprContext ctx) {
        super.enterE_expr(ctx);

        try {
            String operation;
            String secondSourceTempRegisterVariable;
            String destTempRegisterVariable;

            if (ctx.getChildCount() > 1) {
                for (int i = 1; i < ctx.getChildCount(); i += 2) {

                    operation = (ctx.getChild(i).getText().equals(Config.SIGN_ADD)) ? Config.OP_ADD : Config.OP_SUB;

                    if (scopes.peek().noTempRegisters()) {
                        destTempRegisterVariable = scopes.peek().getNewTempIRRegister(Config.INT);

                        scopes.peek().addOperation3(operation,
                                ctx.getChild(i - 1).getText(),
                                ctx.getChild(i + 1).getText(), destTempRegisterVariable);

                    } else {
                        secondSourceTempRegisterVariable = scopes.peek().getLastTempIRRegister();

                        destTempRegisterVariable = scopes.peek().getNewTempIRRegister(Config.INT);

                        scopes.peek().addOperation3(operation,
                                secondSourceTempRegisterVariable,
                                ctx.getChild(i + 1).getText(), destTempRegisterVariable);

                    }

                }

            }

        } catch (Exception e) {
            throw new RuntimeException(Config.TIGER_RUNTIME_EXCEPTION + Config.COLON + Config.SPACE +
                    "Tiger arithmetic expression statement" + Config.IN_LINE + ctx.start.getLine() +
                    Config.OF_PROGRAM + tiger_program_input_filename);

        }

    }

    @Override
    public void enterA_expr(TigerParser.A_exprContext ctx) {
        super.enterA_expr(ctx);

        try {
            String operation;
            String secondSourceTempRegisterVariable;
            String destTempRegisterVariable;

            if (ctx.getChildCount() > 1) {
                for (int i = 1; i < ctx.getChildCount(); i += 2) {

                    operation = (ctx.getChild(i).getText().equals(Config.SIGN_MULTIPLICATION)) ? Config.OP_MULTIPLICATION : Config.OP_DIVISION;

                    if (scopes.peek().noTempRegisters()) {
                        destTempRegisterVariable = scopes.peek().getNewTempIRRegister(Config.INT);

                        scopes.peek().addOperation3(operation,
                                ctx.getChild(i - 1).getText(),
                                ctx.getChild(i + 1).getText(), destTempRegisterVariable);

                    } else {
                        secondSourceTempRegisterVariable = scopes.peek().getLastTempIRRegister();

                        destTempRegisterVariable = scopes.peek().getNewTempIRRegister(Config.INT);

                        scopes.peek().addOperation3(operation,
                                secondSourceTempRegisterVariable,
                                ctx.getChild(i + 1).getText(), destTempRegisterVariable);

                    }

                }

            }

        } catch (Exception e) {
            throw new RuntimeException(Config.TIGER_RUNTIME_EXCEPTION + Config.COLON + Config.SPACE +
                    "Tiger multiplication/division expression statement" + Config.IN_LINE + ctx.start.getLine() +
                    Config.OF_PROGRAM + tiger_program_input_filename);

        }

    }

    @Override
    public void enterI_expr(TigerParser.I_exprContext ctx) {
        super.enterI_expr(ctx);

        try {
            String operation;
            String secondSourceTempRegisterVariable;
            String destTempRegisterVariable;

            if (ctx.getChildCount() > 1) {
                for (int i = 1; i < ctx.getChildCount(); i += 2) {

                    operation = Config.OP_POWER;

                    if (scopes.peek().noTempRegisters()) {
                        destTempRegisterVariable = scopes.peek().getNewTempIRRegister(Config.INT);

                        scopes.peek().addOperation3(operation,
                                ctx.getChild(i - 1).getText(),
                                ctx.getChild(i + 1).getText(), destTempRegisterVariable);

                    } else {
                        secondSourceTempRegisterVariable = scopes.peek().getLastTempIRRegister();

                        destTempRegisterVariable = scopes.peek().getNewTempIRRegister(Config.INT);

                        scopes.peek().addOperation3(operation,
                                secondSourceTempRegisterVariable,
                                ctx.getChild(i + 1).getText(), destTempRegisterVariable);

                    }

                }

            }

        } catch (Exception e) {
            throw new RuntimeException(Config.TIGER_RUNTIME_EXCEPTION + Config.COLON + Config.SPACE +
                    "Tiger power expression statement" + Config.IN_LINE + ctx.start.getLine() +
                    Config.OF_PROGRAM + tiger_program_input_filename);

        }

    }



}
