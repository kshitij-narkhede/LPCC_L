%option noyywrap
%{
#include <stdio.h>
%}


%%
The|the|a|an                               { printf("ARTICLE: %s\n", yytext); }
I|you|he|she|it|we|they                    { printf("PRONOUN: %s\n", yytext); }
and|but|or                                 { printf("CONJUNCTION: %s\n", yytext); }
of|in|on|at|by|with|about|for|over|from    { printf("PREPOSITION: %s\n", yytext); }
quick|brown|lazy|same                           { printf("ADJECTIVE: %s\n", yytext); }
quickly|slowly|carefully                   { printf("ADVERB: %s\n", yytext); }
fox|dog|cat|Destiny                               { printf("NOUN: %s\n", yytext); }
jumps|runs|sleeps|is|Dread|arrives                       { printf("VERB: %s\n", yytext); }
[a-zA-Z]+                                  { printf("WORD: %s\n", yytext);}
.             { /* Ignore any other characters */ }
%%


int main(int argc, char* argv[]) {
    
    yylex();

    return 0;
}