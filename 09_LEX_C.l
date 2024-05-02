%option noyywrap
%{
#include <stdio.h>
int line_no = 1;
%}

NUMBER      [0-9]+
FLOAT    ({NUMBER}\.)?{NUMBER}

%%

[ \t]+    /* Skip whitespace */

"//".*       /* Skip comments */

("if"|"else"|"while"|"for"|"int"|"float"|"char"|"return"|"printf"|"scan"|"double")   { printf("%-10d %-10s KEYWORD      \t-\n", line_no, yytext); }

[a-zA-Z]([a-zA-Z]|[0-9])*                                                           { printf("%-10d %-10s IDENTIFIER         \t%s\n", line_no, yytext, yytext); }

("+"|"-"|"*"|"/"|"="|"=="|"!="|"<"|"<="|">"|">="|"&&"|"||"|"!")                     { printf("%-10d %-10s OPRATOR      \t-\n", line_no, yytext); }

("("|")"|"{"|"}"|";"|","|"\""|"'")                                                   { printf("%-10d %-10s DELIMITER    \t-\n", line_no, yytext); }


[0-9]+                                { printf("%-10d %-10s NUM        \t%s\n", line_no, yytext, yytext); }

{FLOAT}+             { printf("%-10d %-10s Float-NUM      \t-\n", line_no, yytext); }

.                   { printf("%-10d %-10s INVALID    -\n", line_no, yytext); }

\n                  { line_no++; }

%%

int main() {
    
    yylex();

    return 0;
}