
grammar Tiger;

// Punctuation
COMMA       :	    ',';
COLON       :		':';
SEMI        :		';';
LPAREN      :		'(';
RPAREN      :		')';
LBRACK      :		'[';
RBRACK      :		']';
LBRACE      :		'{';
RBRACE      :		'}';
PERIOD      :		'.';
PLUS        :		'+';
MINUS       :		'-';
MULT        :		'*';
DIV         :		'/';
EQ          :	    '==';
TYPEEQ      :       '=';
NEQ         :		'!=';
LESSER      :		'<';
GREATER     :	    '>';
LESSEREQ    :	    '<=';
GREATEREQ   :	    '>=';
AND         :		'&';
OR          :		'|';
ASSIGN      :		':=';
POWER       :		'**';

// Keywords
MAIN        : 		'main';
ARRAY       :		'array';
RECORD      :		'record';
BREAK       :		'break';
DO          :		'do';
ELSE        :		'else';
END         :		'end';
FOR         :		'for';
FUNC        :		'function';
IF          :		'if';
IN          :		'in';
LET         :		'let';
OF          :		'of';
THEN        :		'then';
TO          :		'to';
TYPE        :		'type';
VAR         :		'var';
WHILE       :		'while';
ENDIF       :		'endif';
BEGIN       :		'begin';
ENDDO       :		'enddo';
RETURN      :       'return';
INT         :       'int';
FLOAT       :       'float';

// User-defined identifiers
NEWLINE     :       [\r\n]+             -> skip;
SPACE       :       ' '+                -> skip;
TAB         :       [\t]+               -> skip;
COMMENT     :       '/*' .*? '*/'       -> skip;

FLOATLIT    :       DIGIT+ '.' DIGIT* ;
INTLIT      :       DIGIT+ ;
ID          :       LETTER LETTERORDIGITORUNDER*;

fragment DIGIT  : [0-9];
fragment NONZERODIGIT   : [1-9];
fragment LETTER : [a-zA-Z] ;
fragment UNDER  : '_' ;
fragment LETTERORDIGIT  : LETTER | DIGIT ;
fragment LETTERORDIGITORUNDER   : LETTER | DIGIT | UNDER ;
fragment NULL   : 'null' ;


/****************************************
 ****************************************
                 Grammar

  The Grammar is in LL(1) format because
  The ambiguity was removed by:
  1) Enforcing operator precedence,
  2) left and right associativity for
     different operators,
  3) Removing left recursion
  4) performing left factoring on the
     garammars obtained above.

  This grammar that is LL(1) compatible
  now can be used for LL(1) parsing by
  the compiler.
  Also now that it is in correct form, it
  can be put into antrl to generate the
  Java parser code, which we will test
  by feeding it Tiger programs (*.tiger).
 ****************************************
*****************************************/

tiger_program               :   MAIN LET declaration_segment IN BEGIN stat_seq END ;

declaration_segment         :   type_declaration_list var_declaration_list function_declaration_list ;

type_declaration_list       :   type_declaration type_declaration_list
                            |   ;

var_declaration_list        :   var_declaration var_declaration_list
                            |   ;

function_declaration_list   :   function_declaration function_declaration_list
                            |   ;


type_declaration            :   TYPE ID TYPEEQ type SEMI ;

type                        :   type_id
                            |   ARRAY LBRACK INTLIT RBRACK OF type_id
                            |   ID ;

type_id                     :   INT
                            |   FLOAT ;


var_declaration             :   VAR id_list COLON type optional_init SEMI ;

id_list                     :   ID id_list_tail ;

id_list_tail                :   COMMA ID id_list_tail
                            |   ;

optional_init               :   ASSIGN constant
                            |   ;


function_declaration        :   FUNC ID LPAREN param_list RPAREN ret_type BEGIN stat_seq END SEMI ;

param_list                  :   param param_list_tail
                            |   ;

param_list_tail             :   COMMA param param_list_tail
                            |   ;

ret_type                    :   COLON type
                            |   ;

param                       :   ID COLON type ;


stat_seq                    :   stat stat_seq_tail ;

stat_seq_tail               :   stat stat_seq_tail
                            |   ;

stat                        :   ID stat_assign_first_tail
                            |   IF expr THEN stat_seq if_stat_tail
                            |   WHILE expr DO stat_seq ENDDO SEMI
                            |   FOR ID ASSIGN expr TO expr DO stat_seq ENDDO SEMI
                            |   BREAK SEMI
                            |   RETURN expr SEMI
                            |   LET declaration_segment IN stat_seq END SEMI ;

if_stat_tail                :   ENDIF SEMI
                            |   ELSE stat_seq ENDIF SEMI ;

stat_assign_first_tail      :   lvalue_tail stat_assign_tail
                            |   LPAREN expr_list RPAREN SEMI ;

stat_assign_tail            :   ASSIGN stat_assign_second0_tail ;

stat_assign_second0_tail    :   ID stat_assign_second_tail
//                            |   constant expr_tail SEMI
                            |   expr SEMI
                            |   LPAREN expr RPAREN expr_tail SEMI ;

stat_assign_second_tail     :   lvalue_tail stat_assign_second1_tail
                            |   LPAREN expr_list RPAREN SEMI ;

stat_assign_second1_tail    :   l_tail ASSIGN expr SEMI
                            |   expr_tail SEMI ;

l_tail                      :   ASSIGN ID lvalue_tail l_tail
                            |   ;

expr_tail                   :   logical_binary_operator expr
                            |   equality_binary_operator expr
                            |   arithmetic_binary_operator expr
                            |   ion_binary_operator expr
                            |   power_binary_operator expr
                            |   ;

expr                        :   l_expr (logical_binary_operator l_expr)* ;

l_expr                      :   e_expr (equality_binary_operator e_expr)? ;

e_expr                      :   a_expr (arithmetic_binary_operator a_expr)* ;

a_expr                      :   i_expr (ion_binary_operator i_expr)* ;

i_expr                      :   expr_0 (power_binary_operator expr_0)* ;

expr_0                      :   constant
                            |   ID lvalue_tail
                            |   LPAREN expr RPAREN ;

constant                    :   INTLIT
                            |   FLOATLIT ;

logical_binary_operator     :   AND
                            |   OR ;

equality_binary_operator    :   EQ
                            |   NEQ
                            |   LESSER
                            |   GREATER
                            |   LESSEREQ
                            |   GREATEREQ ;

arithmetic_binary_operator  :   PLUS
                            |   MINUS ;

ion_binary_operator         :   MULT
                            |   DIV ;

power_binary_operator       :   POWER ;


expr_list                   :   expr expr_list_tail
                            |   ;

expr_list_tail              :   COMMA expr expr_list_tail
                            |   ;

lvalue_tail                 :   LBRACK expr RBRACK
                            |   ;
