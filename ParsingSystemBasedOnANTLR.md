## Introduction ##

ANTLR (Another Tool for Language Recognition) is a syntaxical and lexical analyser generator. ANTLR is able to generate a "parsing system" designed specifically for a development language. In order to generate this "specific parsing system", ANTLR is based upon a grammar file which describes the specification of a development language.

The output of ANTLR is Java generated classes able to parse a source file according to the grammar file provided initialy.


## What does ANTLR generate? ##

The "generated parsing system"  is subdivided in three subcomponents :

  * lexer : A lexer (often called a scanner) breaks up an input stream of characters into vocabulary symbols for a parser, which applies a grammatical structure to that symbol stream

  * token stream parser : A parser takes a stream of tokens from a lexer and groups them into higher order constructions called productions.

  * tree-parsers (tree-walkers) : A more powerful parsing strategy is to build an intermediate representation that holds all or most of the input symbols and has encoded the relationship between those tokens, in the structure of the data.


## Usage in codesquale ##

ANTLR will be a real important part of codesquale because we'll based our parsing system on it. The parsing system will provide us all the data we need to build the software metrics.

The first language to be implemented in code squale will be Java. So, we will explain what ANTLR generates with a Java grammar file.

ANTLR generates all the needed java files to provides an efficient parsing system :

  * JavaLexer : Defines the lexer, a Token.java inherited class
  * JavaRecognizer : Wich is the implementation for the parser
  * JavaTokenTypes : An interface wich defines all the Java language Tokens