// Generated from Tiger.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TigerParser}.
 */
public interface TigerListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TigerParser#tiger_program}.
	 * @param ctx the parse tree
	 */
	void enterTiger_program(TigerParser.Tiger_programContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#tiger_program}.
	 * @param ctx the parse tree
	 */
	void exitTiger_program(TigerParser.Tiger_programContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#declaration_segment}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration_segment(TigerParser.Declaration_segmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#declaration_segment}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration_segment(TigerParser.Declaration_segmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#type_declaration_list}.
	 * @param ctx the parse tree
	 */
	void enterType_declaration_list(TigerParser.Type_declaration_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#type_declaration_list}.
	 * @param ctx the parse tree
	 */
	void exitType_declaration_list(TigerParser.Type_declaration_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#var_declaration_list}.
	 * @param ctx the parse tree
	 */
	void enterVar_declaration_list(TigerParser.Var_declaration_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#var_declaration_list}.
	 * @param ctx the parse tree
	 */
	void exitVar_declaration_list(TigerParser.Var_declaration_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#function_declaration_list}.
	 * @param ctx the parse tree
	 */
	void enterFunction_declaration_list(TigerParser.Function_declaration_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#function_declaration_list}.
	 * @param ctx the parse tree
	 */
	void exitFunction_declaration_list(TigerParser.Function_declaration_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#type_declaration}.
	 * @param ctx the parse tree
	 */
	void enterType_declaration(TigerParser.Type_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#type_declaration}.
	 * @param ctx the parse tree
	 */
	void exitType_declaration(TigerParser.Type_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(TigerParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(TigerParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#type_id}.
	 * @param ctx the parse tree
	 */
	void enterType_id(TigerParser.Type_idContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#type_id}.
	 * @param ctx the parse tree
	 */
	void exitType_id(TigerParser.Type_idContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#var_declaration}.
	 * @param ctx the parse tree
	 */
	void enterVar_declaration(TigerParser.Var_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#var_declaration}.
	 * @param ctx the parse tree
	 */
	void exitVar_declaration(TigerParser.Var_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#id_list}.
	 * @param ctx the parse tree
	 */
	void enterId_list(TigerParser.Id_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#id_list}.
	 * @param ctx the parse tree
	 */
	void exitId_list(TigerParser.Id_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#id_list_tail}.
	 * @param ctx the parse tree
	 */
	void enterId_list_tail(TigerParser.Id_list_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#id_list_tail}.
	 * @param ctx the parse tree
	 */
	void exitId_list_tail(TigerParser.Id_list_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#optional_init}.
	 * @param ctx the parse tree
	 */
	void enterOptional_init(TigerParser.Optional_initContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#optional_init}.
	 * @param ctx the parse tree
	 */
	void exitOptional_init(TigerParser.Optional_initContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#function_declaration}.
	 * @param ctx the parse tree
	 */
	void enterFunction_declaration(TigerParser.Function_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#function_declaration}.
	 * @param ctx the parse tree
	 */
	void exitFunction_declaration(TigerParser.Function_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#param_list}.
	 * @param ctx the parse tree
	 */
	void enterParam_list(TigerParser.Param_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#param_list}.
	 * @param ctx the parse tree
	 */
	void exitParam_list(TigerParser.Param_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#param_list_tail}.
	 * @param ctx the parse tree
	 */
	void enterParam_list_tail(TigerParser.Param_list_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#param_list_tail}.
	 * @param ctx the parse tree
	 */
	void exitParam_list_tail(TigerParser.Param_list_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#ret_type}.
	 * @param ctx the parse tree
	 */
	void enterRet_type(TigerParser.Ret_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#ret_type}.
	 * @param ctx the parse tree
	 */
	void exitRet_type(TigerParser.Ret_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(TigerParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(TigerParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#stat_seq}.
	 * @param ctx the parse tree
	 */
	void enterStat_seq(TigerParser.Stat_seqContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#stat_seq}.
	 * @param ctx the parse tree
	 */
	void exitStat_seq(TigerParser.Stat_seqContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#stat_seq_tail}.
	 * @param ctx the parse tree
	 */
	void enterStat_seq_tail(TigerParser.Stat_seq_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#stat_seq_tail}.
	 * @param ctx the parse tree
	 */
	void exitStat_seq_tail(TigerParser.Stat_seq_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(TigerParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(TigerParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#if_stat_tail}.
	 * @param ctx the parse tree
	 */
	void enterIf_stat_tail(TigerParser.If_stat_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#if_stat_tail}.
	 * @param ctx the parse tree
	 */
	void exitIf_stat_tail(TigerParser.If_stat_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#stat_assign_first_tail}.
	 * @param ctx the parse tree
	 */
	void enterStat_assign_first_tail(TigerParser.Stat_assign_first_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#stat_assign_first_tail}.
	 * @param ctx the parse tree
	 */
	void exitStat_assign_first_tail(TigerParser.Stat_assign_first_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#stat_assign_tail}.
	 * @param ctx the parse tree
	 */
	void enterStat_assign_tail(TigerParser.Stat_assign_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#stat_assign_tail}.
	 * @param ctx the parse tree
	 */
	void exitStat_assign_tail(TigerParser.Stat_assign_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#stat_assign_second0_tail}.
	 * @param ctx the parse tree
	 */
	void enterStat_assign_second0_tail(TigerParser.Stat_assign_second0_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#stat_assign_second0_tail}.
	 * @param ctx the parse tree
	 */
	void exitStat_assign_second0_tail(TigerParser.Stat_assign_second0_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#stat_assign_second_tail}.
	 * @param ctx the parse tree
	 */
	void enterStat_assign_second_tail(TigerParser.Stat_assign_second_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#stat_assign_second_tail}.
	 * @param ctx the parse tree
	 */
	void exitStat_assign_second_tail(TigerParser.Stat_assign_second_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#stat_assign_second1_tail}.
	 * @param ctx the parse tree
	 */
	void enterStat_assign_second1_tail(TigerParser.Stat_assign_second1_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#stat_assign_second1_tail}.
	 * @param ctx the parse tree
	 */
	void exitStat_assign_second1_tail(TigerParser.Stat_assign_second1_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#l_tail}.
	 * @param ctx the parse tree
	 */
	void enterL_tail(TigerParser.L_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#l_tail}.
	 * @param ctx the parse tree
	 */
	void exitL_tail(TigerParser.L_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#expr_tail}.
	 * @param ctx the parse tree
	 */
	void enterExpr_tail(TigerParser.Expr_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#expr_tail}.
	 * @param ctx the parse tree
	 */
	void exitExpr_tail(TigerParser.Expr_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(TigerParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(TigerParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#l_expr}.
	 * @param ctx the parse tree
	 */
	void enterL_expr(TigerParser.L_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#l_expr}.
	 * @param ctx the parse tree
	 */
	void exitL_expr(TigerParser.L_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#e_expr}.
	 * @param ctx the parse tree
	 */
	void enterE_expr(TigerParser.E_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#e_expr}.
	 * @param ctx the parse tree
	 */
	void exitE_expr(TigerParser.E_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#a_expr}.
	 * @param ctx the parse tree
	 */
	void enterA_expr(TigerParser.A_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#a_expr}.
	 * @param ctx the parse tree
	 */
	void exitA_expr(TigerParser.A_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#i_expr}.
	 * @param ctx the parse tree
	 */
	void enterI_expr(TigerParser.I_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#i_expr}.
	 * @param ctx the parse tree
	 */
	void exitI_expr(TigerParser.I_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#expr_0}.
	 * @param ctx the parse tree
	 */
	void enterExpr_0(TigerParser.Expr_0Context ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#expr_0}.
	 * @param ctx the parse tree
	 */
	void exitExpr_0(TigerParser.Expr_0Context ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(TigerParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(TigerParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#logical_binary_operator}.
	 * @param ctx the parse tree
	 */
	void enterLogical_binary_operator(TigerParser.Logical_binary_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#logical_binary_operator}.
	 * @param ctx the parse tree
	 */
	void exitLogical_binary_operator(TigerParser.Logical_binary_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#equality_binary_operator}.
	 * @param ctx the parse tree
	 */
	void enterEquality_binary_operator(TigerParser.Equality_binary_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#equality_binary_operator}.
	 * @param ctx the parse tree
	 */
	void exitEquality_binary_operator(TigerParser.Equality_binary_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#arithmetic_binary_operator}.
	 * @param ctx the parse tree
	 */
	void enterArithmetic_binary_operator(TigerParser.Arithmetic_binary_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#arithmetic_binary_operator}.
	 * @param ctx the parse tree
	 */
	void exitArithmetic_binary_operator(TigerParser.Arithmetic_binary_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#ion_binary_operator}.
	 * @param ctx the parse tree
	 */
	void enterIon_binary_operator(TigerParser.Ion_binary_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#ion_binary_operator}.
	 * @param ctx the parse tree
	 */
	void exitIon_binary_operator(TigerParser.Ion_binary_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#power_binary_operator}.
	 * @param ctx the parse tree
	 */
	void enterPower_binary_operator(TigerParser.Power_binary_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#power_binary_operator}.
	 * @param ctx the parse tree
	 */
	void exitPower_binary_operator(TigerParser.Power_binary_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list(TigerParser.Expr_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list(TigerParser.Expr_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#expr_list_tail}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list_tail(TigerParser.Expr_list_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#expr_list_tail}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list_tail(TigerParser.Expr_list_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#lvalue_tail}.
	 * @param ctx the parse tree
	 */
	void enterLvalue_tail(TigerParser.Lvalue_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#lvalue_tail}.
	 * @param ctx the parse tree
	 */
	void exitLvalue_tail(TigerParser.Lvalue_tailContext ctx);
}