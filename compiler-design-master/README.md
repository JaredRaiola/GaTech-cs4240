# compiler-design


Setup lexer (Windows):
Install antlr4 complete jar;
and put grun.bat and antlr4.bat in same folder;
put jar in CLASSPATH;



With Parser
edit Tiger.g4;
command "antlr4 Tiger.g4";
command "javac Tiger*.java;
command "grun Tiger prog -gui" or run "grun Tiger prog -tokens" if you want to see tokens;
type in text to parse then hit enter;
type ctrl+z then hit enter;
you should see the tree of your input string;

Without parser
edit Tiger.g4;
command "antlr4 Tiger.g4";
command "javac Tiger*.java;
command "grun Tiger tokens -tokens";
type in text to parse then hit enter;
type ctrl+z then hit enter;
you should see the tokens of what you put in
