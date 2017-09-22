package org.breder.lng.token;

import java.util.HashMap;
import java.util.Map;

public class BrederWordToken extends Token {

  public String lexeme = "";

  private static final Map<String, BrederWordToken> words =
    new HashMap<String, BrederWordToken>();

  public final static int AND = 256, CONTINUE = 257, BREAK = 258, DO = 259,
    ELSE = 260, EQ = 261, FALSE = 262, GE = 263, ID = 264, IF = 265, LE = 267,
    NE = 269, NUM = 270, OR = 271, TRUE = 274, WHILE = 295, END = 276,
    REPEAT = 277, FOR = 278, STR = 290, THIS = 291, DEC = 292, INC = 293;

  public static final BrederWordToken EQ_TOKEN = new BrederWordToken("==", EQ);

  public static final BrederWordToken NOT_EQUAL_TOKEN = new BrederWordToken(
    "!=", NE);

  public static final BrederWordToken LE_TOKEN = new BrederWordToken("<=", LE);

  public static final BrederWordToken GE_TOKEN = new BrederWordToken(">=", GE);

  public static final BrederWordToken INC_TOKEN =
    new BrederWordToken("++", INC);

  public static final BrederWordToken DEC_TOKEN =
    new BrederWordToken("--", DEC);

  static {
    words.put("--", DEC_TOKEN);
    words.put("++", INC_TOKEN);
    words.put(">=", GE_TOKEN);
    words.put("<=", LE_TOKEN);
    words.put("!=", NOT_EQUAL_TOKEN);
    words.put("==", EQ_TOKEN);
    words.put("and", new BrederWordToken("and", AND));
    words.put("break", new BrederWordToken("break", BREAK));
    words.put("continue", new BrederWordToken("continue", CONTINUE));
    words.put("do", new BrederWordToken("do", DO));
    words.put("else", new BrederWordToken("else", ELSE));
    words.put("end", new BrederWordToken("end", END));
    words.put("false", new BrederWordToken("false", FALSE));
    words.put("for", new BrederWordToken("for", FOR));
    words.put("if", new BrederWordToken("if", IF));
    words.put("or", new BrederWordToken("or", OR));
    words.put("repeat", new BrederWordToken("repeat", REPEAT));
    words.put("this", new BrederWordToken("this", THIS));
    words.put("true", new BrederWordToken("true", TRUE));
    words.put("while", new BrederWordToken("while", WHILE));
  }

  protected BrederWordToken(String s, int tag) {
    super(tag);
    lexeme = s;
  }

  public static BrederWordToken build(String token) {
    return words.get(token);
  }

  public static BrederWordToken build(int tag) {
    for (BrederWordToken token : words.values()) {
      if (token.tag == tag) {
        return token;
      }
    }
    throw new IllegalArgumentException();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((lexeme == null) ? 0 : lexeme.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    BrederWordToken other = (BrederWordToken) obj;
    if (lexeme == null) {
      if (other.lexeme != null) {
        return false;
      }
    }
    else if (!lexeme.equals(other.lexeme)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return lexeme;
  }

}
