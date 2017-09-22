/* Generated By:JavaCC: Do not edit this line. GrammarConstants.java */
package breder.processor.parser;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface GrammarConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int SINGLE_LINE_COMMENT = 5;
  /** RegularExpression Id. */
  int JAVA_DOC_COMMENT = 8;
  /** RegularExpression Id. */
  int MULTI_LINE_COMMENT = 9;
  /** RegularExpression Id. */
  int SUM = 11;
  /** RegularExpression Id. */
  int SUB = 12;
  /** RegularExpression Id. */
  int MUL = 13;
  /** RegularExpression Id. */
  int DIV = 14;
  /** RegularExpression Id. */
  int EQUAL = 15;
  /** RegularExpression Id. */
  int SEMICOMMA = 16;
  /** RegularExpression Id. */
  int PRAGMA = 17;
  /** RegularExpression Id. */
  int DOT = 18;
  /** RegularExpression Id. */
  int COMMA = 19;
  /** RegularExpression Id. */
  int DOTDOT = 20;
  /** RegularExpression Id. */
  int LPARAM = 21;
  /** RegularExpression Id. */
  int RPARAM = 22;
  /** RegularExpression Id. */
  int LBLOCK = 23;
  /** RegularExpression Id. */
  int RBLOCK = 24;
  /** RegularExpression Id. */
  int LARRAY = 25;
  /** RegularExpression Id. */
  int RARRAY = 26;
  /** RegularExpression Id. */
  int LOW = 27;
  /** RegularExpression Id. */
  int HIGH = 28;
  /** RegularExpression Id. */
  int NOT = 29;
  /** RegularExpression Id. */
  int DEFINE = 30;
  /** RegularExpression Id. */
  int WHILE = 31;
  /** RegularExpression Id. */
  int IF = 32;
  /** RegularExpression Id. */
  int ELSE = 33;
  /** RegularExpression Id. */
  int OR = 34;
  /** RegularExpression Id. */
  int AND = 35;
  /** RegularExpression Id. */
  int CONSTANT = 36;
  /** RegularExpression Id. */
  int NATURAL = 37;
  /** RegularExpression Id. */
  int ID = 38;
  /** RegularExpression Id. */
  int DIGIT = 39;
  /** RegularExpression Id. */
  int ZDIGIT = 40;
  /** RegularExpression Id. */
  int LETTER = 41;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int IN_JAVA_DOC_COMMENT = 1;
  /** Lexical state. */
  int IN_MULTI_LINE_COMMENT = 2;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\r\"",
    "\"\\t\"",
    "\"\\n\"",
    "<SINGLE_LINE_COMMENT>",
    "<token of kind 6>",
    "\"/*\"",
    "\"*/\"",
    "\"*/\"",
    "<token of kind 10>",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "\"=\"",
    "\";\"",
    "\"#\"",
    "\".\"",
    "\",\"",
    "\":\"",
    "\"(\"",
    "\")\"",
    "\"{\"",
    "\"}\"",
    "\"[\"",
    "\"]\"",
    "\"<\"",
    "\">\"",
    "\"!\"",
    "\"define\"",
    "\"while\"",
    "\"if\"",
    "\"else\"",
    "\"or\"",
    "\"and\"",
    "<CONSTANT>",
    "<NATURAL>",
    "<ID>",
    "<DIGIT>",
    "<ZDIGIT>",
    "<LETTER>",
  };

}
