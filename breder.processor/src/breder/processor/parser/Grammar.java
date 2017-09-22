/* Generated By:JavaCC: Do not edit this line. Grammar.java */
package breder.processor.parser;
import breder.processor.node.*;

public class Grammar implements GrammarConstants {
  BProcessor p = new BProcessor();

  final public BProcessor init() throws ParseException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SUB:
      case PRAGMA:
      case ID:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PRAGMA:
        pragmaDef();
        break;
      case ID:
        callDef();
        jj_consume_token(SEMICOMMA);
        break;
      case SUB:
        declareDef();
        break;
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    jj_consume_token(0);
    {if (true) return p;}
    throw new Error("Missing return statement in function");
  }

  final public void pragmaDef() throws ParseException {
    jj_consume_token(PRAGMA);
    defineDef();
  }

  final public void defineDef() throws ParseException {
    jj_consume_token(DEFINE);
    jj_consume_token(ID);
    exprDef();
  }

  final public void declareDef() throws ParseException {
    jj_consume_token(SUB);
    jj_consume_token(ID);
    jj_consume_token(LPARAM);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      jj_consume_token(ID);
      jj_consume_token(DOTDOT);
      jj_consume_token(CONSTANT);
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[2] = jj_gen;
          break label_2;
        }
        jj_consume_token(COMMA);
        jj_consume_token(ID);
        jj_consume_token(DOTDOT);
        jj_consume_token(CONSTANT);
      }
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
    jj_consume_token(RPARAM);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SEMICOMMA:
      jj_consume_token(SEMICOMMA);
      break;
    case LBLOCK:
      jj_consume_token(LBLOCK);
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SUB:
        case LBLOCK:
        case WHILE:
        case IF:
        case CONSTANT:
        case ID:
          ;
          break;
        default:
          jj_la1[4] = jj_gen;
          break label_3;
        }
        cmdDef();
      }
      jj_consume_token(RBLOCK);
      break;
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void cmdDef() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LBLOCK:
      jj_consume_token(LBLOCK);
      cmdDef();
      jj_consume_token(RBLOCK);
      break;
    default:
      jj_la1[6] = jj_gen;
      if (jj_2_1(2147483647)) {
        assignDef();
        jj_consume_token(SEMICOMMA);
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case WHILE:
          whileDef();
          break;
        case IF:
          ifDef();
          break;
        case SUB:
        case CONSTANT:
        case ID:
          exprDef();
          jj_consume_token(SEMICOMMA);
          break;
        default:
          jj_la1[7] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
  }

  final public void assignDef() throws ParseException {
    lvalueDef();
    jj_consume_token(EQUAL);
    rvalueDef();
  }

  final public void whileDef() throws ParseException {
    jj_consume_token(WHILE);
    jj_consume_token(LPARAM);
    rvalueDef();
    jj_consume_token(RPARAM);
    jj_consume_token(LBLOCK);
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SUB:
      case LBLOCK:
      case WHILE:
      case IF:
      case CONSTANT:
      case ID:
        ;
        break;
      default:
        jj_la1[8] = jj_gen;
        break label_4;
      }
      cmdDef();
    }
    jj_consume_token(RBLOCK);
  }

  final public void ifDef() throws ParseException {
    jj_consume_token(IF);
    jj_consume_token(LPARAM);
    rvalueDef();
    jj_consume_token(RPARAM);
    jj_consume_token(LBLOCK);
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SUB:
      case LBLOCK:
      case WHILE:
      case IF:
      case CONSTANT:
      case ID:
        ;
        break;
      default:
        jj_la1[9] = jj_gen;
        break label_5;
      }
      cmdDef();
    }
    jj_consume_token(RBLOCK);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ELSE:
      jj_consume_token(ELSE);
      cmdDef();
      break;
    default:
      jj_la1[10] = jj_gen;
      ;
    }
  }

  final public BLValue lvalueDef() throws ParseException {
  Token t;
  BRValue v;
    if (jj_2_2(2147483647)) {
      t = jj_consume_token(ID);
      jj_consume_token(LARRAY);
      v = rvalueDef();
      jj_consume_token(RARRAY);
      {if (true) return new BLMemoryIdentify(t, v);}
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ID:
        t = jj_consume_token(ID);
      {if (true) return new BLIdentify(t);}
        break;
      default:
        jj_la1[11] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public BRValue rvalueDef() throws ParseException {
  BRValue v;
    v = exprDef();
    {if (true) return v;}
    throw new Error("Missing return statement in function");
  }

  final public BRValue exprDef() throws ParseException {
  BRValue v;
    v = orDef();
    {if (true) return v;}
    throw new Error("Missing return statement in function");
  }

  final public BRValue orDef() throws ParseException {
  BRValue v;
    v = equalDef();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OR:
      case AND:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_6;
      }
      if (jj_2_3(2147483647)) {
        jj_consume_token(OR);
        equalDef();
      } else if (jj_2_4(2147483647)) {
        jj_consume_token(AND);
        equalDef();
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return v;}
    throw new Error("Missing return statement in function");
  }

  final public BRValue equalDef() throws ParseException {
  BRValue v;
    v = sumDef();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case EQUAL:
      case LOW:
      case HIGH:
      case NOT:
        ;
        break;
      default:
        jj_la1[13] = jj_gen;
        break label_7;
      }
      if (jj_2_5(2147483647)) {
        jj_consume_token(EQUAL);
        jj_consume_token(EQUAL);
        sumDef();
      } else if (jj_2_6(2147483647)) {
        jj_consume_token(NOT);
        jj_consume_token(EQUAL);
        sumDef();
      } else if (jj_2_7(2147483647)) {
        jj_consume_token(LOW);
        jj_consume_token(EQUAL);
        sumDef();
      } else if (jj_2_8(2147483647)) {
        jj_consume_token(HIGH);
        jj_consume_token(EQUAL);
        sumDef();
      } else if (jj_2_9(2147483647)) {
        jj_consume_token(LOW);
        sumDef();
      } else if (jj_2_10(2147483647)) {
        jj_consume_token(HIGH);
        sumDef();
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return v;}
    throw new Error("Missing return statement in function");
  }

  final public BRValue sumDef() throws ParseException {
  BRValue v;
    v = mulDef();
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SUM:
      case SUB:
        ;
        break;
      default:
        jj_la1[14] = jj_gen;
        break label_8;
      }
      if (jj_2_11(2147483647)) {
        jj_consume_token(SUM);
        mulDef();
      } else if (jj_2_12(2147483647)) {
        jj_consume_token(SUB);
        mulDef();
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return v;}
    throw new Error("Missing return statement in function");
  }

  final public BRValue mulDef() throws ParseException {
  BRValue v;
    v = unaryDef();
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MUL:
      case DIV:
        ;
        break;
      default:
        jj_la1[15] = jj_gen;
        break label_9;
      }
      if (jj_2_13(2147483647)) {
        jj_consume_token(MUL);
        unaryDef();
      } else if (jj_2_14(2147483647)) {
        jj_consume_token(DIV);
        unaryDef();
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return v;}
    throw new Error("Missing return statement in function");
  }

  final public BRValue unaryDef() throws ParseException {
  Token t = null;
  BRValue v;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SUB:
      t = jj_consume_token(SUB);
      break;
    default:
      jj_la1[16] = jj_gen;
      ;
    }
    v = literalDef();
    if (t != null)
    {
      {if (true) return v;}
    }
    else
    {
      {if (true) return v;}
    }
    throw new Error("Missing return statement in function");
  }

  final public BRValue literalDef() throws ParseException {
  Token t;
  BRValue v;
    if (jj_2_15(2147483647)) {
      v = callDef();
    {if (true) return v;}
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ID:
        v = idDef();
    {if (true) return v;}
        break;
      case CONSTANT:
        t = jj_consume_token(CONSTANT);
    {if (true) return new BNumber(t);}
        break;
      default:
        jj_la1[17] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public BRValue idDef() throws ParseException {
  Token t = null;
  BRValue v;
    if (jj_2_16(2147483647)) {
      t = jj_consume_token(ID);
      jj_consume_token(LARRAY);
      v = rvalueDef();
      jj_consume_token(RARRAY);
      {if (true) return new BMemoryIdentify(t, v);}
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ID:
        t = jj_consume_token(ID);
      {if (true) return new BIdentify(t);}
        break;
      default:
        jj_la1[18] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public BRValue callDef() throws ParseException {
  Token t;
    t = jj_consume_token(ID);
    jj_consume_token(LPARAM);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SUB:
    case CONSTANT:
    case ID:
      exprDef();
      label_10:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[19] = jj_gen;
          break label_10;
        }
        jj_consume_token(COMMA);
        exprDef();
      }
      break;
    default:
      jj_la1[20] = jj_gen;
      ;
    }
    jj_consume_token(RPARAM);
    {if (true) return new BCall(t);}
    throw new Error("Missing return statement in function");
  }

  private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  private boolean jj_2_7(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  private boolean jj_2_8(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_8(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(7, xla); }
  }

  private boolean jj_2_9(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_9(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(8, xla); }
  }

  private boolean jj_2_10(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_10(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(9, xla); }
  }

  private boolean jj_2_11(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_11(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(10, xla); }
  }

  private boolean jj_2_12(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_12(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(11, xla); }
  }

  private boolean jj_2_13(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_13(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(12, xla); }
  }

  private boolean jj_2_14(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_14(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(13, xla); }
  }

  private boolean jj_2_15(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_15(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(14, xla); }
  }

  private boolean jj_2_16(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_16(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(15, xla); }
  }

  private boolean jj_3R_28() {
    if (jj_scan_token(HIGH)) return true;
    if (jj_scan_token(EQUAL)) return true;
    if (jj_3R_19()) return true;
    return false;
  }

  private boolean jj_3R_44() {
    if (jj_scan_token(ID)) return true;
    if (jj_scan_token(LARRAY)) return true;
    if (jj_3R_14()) return true;
    if (jj_scan_token(RARRAY)) return true;
    return false;
  }

  private boolean jj_3R_27() {
    if (jj_scan_token(LOW)) return true;
    if (jj_scan_token(EQUAL)) return true;
    if (jj_3R_19()) return true;
    return false;
  }

  private boolean jj_3R_26() {
    if (jj_scan_token(NOT)) return true;
    if (jj_scan_token(EQUAL)) return true;
    if (jj_3R_19()) return true;
    return false;
  }

  private boolean jj_3R_42() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_44()) {
    jj_scanpos = xsp;
    if (jj_3R_45()) return true;
    }
    return false;
  }

  private boolean jj_3R_20() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_25()) {
    jj_scanpos = xsp;
    if (jj_3R_26()) {
    jj_scanpos = xsp;
    if (jj_3R_27()) {
    jj_scanpos = xsp;
    if (jj_3R_28()) {
    jj_scanpos = xsp;
    if (jj_3R_29()) {
    jj_scanpos = xsp;
    if (jj_3R_30()) return true;
    }
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3R_25() {
    if (jj_scan_token(EQUAL)) return true;
    if (jj_scan_token(EQUAL)) return true;
    if (jj_3R_19()) return true;
    return false;
  }

  private boolean jj_3_1() {
    if (jj_3R_11()) return true;
    if (jj_scan_token(EQUAL)) return true;
    return false;
  }

  private boolean jj_3R_17() {
    if (jj_3R_19()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_20()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3_4() {
    if (jj_scan_token(AND)) return true;
    return false;
  }

  private boolean jj_3_3() {
    if (jj_scan_token(OR)) return true;
    return false;
  }

  private boolean jj_3_15() {
    if (jj_scan_token(ID)) return true;
    if (jj_scan_token(LPARAM)) return true;
    return false;
  }

  private boolean jj_3R_40() {
    if (jj_scan_token(CONSTANT)) return true;
    return false;
  }

  private boolean jj_3R_39() {
    if (jj_3R_42()) return true;
    return false;
  }

  private boolean jj_3R_22() {
    if (jj_scan_token(AND)) return true;
    if (jj_3R_17()) return true;
    return false;
  }

  private boolean jj_3R_18() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_21()) {
    jj_scanpos = xsp;
    if (jj_3R_22()) return true;
    }
    return false;
  }

  private boolean jj_3R_21() {
    if (jj_scan_token(OR)) return true;
    if (jj_3R_17()) return true;
    return false;
  }

  private boolean jj_3R_38() {
    if (jj_3R_41()) return true;
    return false;
  }

  private boolean jj_3R_35() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_38()) {
    jj_scanpos = xsp;
    if (jj_3R_39()) {
    jj_scanpos = xsp;
    if (jj_3R_40()) return true;
    }
    }
    return false;
  }

  private boolean jj_3R_16() {
    if (jj_3R_17()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_18()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_15() {
    if (jj_3R_16()) return true;
    return false;
  }

  private boolean jj_3R_31() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(12)) jj_scanpos = xsp;
    if (jj_3R_35()) return true;
    return false;
  }

  private boolean jj_3_14() {
    if (jj_scan_token(DIV)) return true;
    return false;
  }

  private boolean jj_3_13() {
    if (jj_scan_token(MUL)) return true;
    return false;
  }

  private boolean jj_3R_14() {
    if (jj_3R_15()) return true;
    return false;
  }

  private boolean jj_3R_37() {
    if (jj_scan_token(DIV)) return true;
    if (jj_3R_31()) return true;
    return false;
  }

  private boolean jj_3_2() {
    if (jj_scan_token(ID)) return true;
    if (jj_scan_token(LARRAY)) return true;
    return false;
  }

  private boolean jj_3R_36() {
    if (jj_scan_token(MUL)) return true;
    if (jj_3R_31()) return true;
    return false;
  }

  private boolean jj_3R_32() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_36()) {
    jj_scanpos = xsp;
    if (jj_3R_37()) return true;
    }
    return false;
  }

  private boolean jj_3R_13() {
    if (jj_scan_token(ID)) return true;
    return false;
  }

  private boolean jj_3R_23() {
    if (jj_3R_31()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_32()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3_12() {
    if (jj_scan_token(SUB)) return true;
    return false;
  }

  private boolean jj_3_11() {
    if (jj_scan_token(SUM)) return true;
    return false;
  }

  private boolean jj_3R_12() {
    if (jj_scan_token(ID)) return true;
    if (jj_scan_token(LARRAY)) return true;
    if (jj_3R_14()) return true;
    if (jj_scan_token(RARRAY)) return true;
    return false;
  }

  private boolean jj_3R_11() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_12()) {
    jj_scanpos = xsp;
    if (jj_3R_13()) return true;
    }
    return false;
  }

  private boolean jj_3R_46() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_15()) return true;
    return false;
  }

  private boolean jj_3R_34() {
    if (jj_scan_token(SUB)) return true;
    if (jj_3R_23()) return true;
    return false;
  }

  private boolean jj_3R_43() {
    if (jj_3R_15()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_46()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_33() {
    if (jj_scan_token(SUM)) return true;
    if (jj_3R_23()) return true;
    return false;
  }

  private boolean jj_3R_24() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_33()) {
    jj_scanpos = xsp;
    if (jj_3R_34()) return true;
    }
    return false;
  }

  private boolean jj_3R_41() {
    if (jj_scan_token(ID)) return true;
    if (jj_scan_token(LPARAM)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_43()) jj_scanpos = xsp;
    if (jj_scan_token(RPARAM)) return true;
    return false;
  }

  private boolean jj_3R_19() {
    if (jj_3R_23()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_24()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3_10() {
    if (jj_scan_token(HIGH)) return true;
    return false;
  }

  private boolean jj_3_9() {
    if (jj_scan_token(LOW)) return true;
    return false;
  }

  private boolean jj_3_8() {
    if (jj_scan_token(HIGH)) return true;
    if (jj_scan_token(EQUAL)) return true;
    return false;
  }

  private boolean jj_3_7() {
    if (jj_scan_token(LOW)) return true;
    if (jj_scan_token(EQUAL)) return true;
    return false;
  }

  private boolean jj_3_16() {
    if (jj_scan_token(ID)) return true;
    if (jj_scan_token(LARRAY)) return true;
    return false;
  }

  private boolean jj_3R_45() {
    if (jj_scan_token(ID)) return true;
    return false;
  }

  private boolean jj_3_6() {
    if (jj_scan_token(NOT)) return true;
    if (jj_scan_token(EQUAL)) return true;
    return false;
  }

  private boolean jj_3R_30() {
    if (jj_scan_token(HIGH)) return true;
    if (jj_3R_19()) return true;
    return false;
  }

  private boolean jj_3_5() {
    if (jj_scan_token(EQUAL)) return true;
    if (jj_scan_token(EQUAL)) return true;
    return false;
  }

  private boolean jj_3R_29() {
    if (jj_scan_token(LOW)) return true;
    if (jj_3R_19()) return true;
    return false;
  }

  /** Generated Token Manager. */
  public GrammarTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[21];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x21000,0x21000,0x80000,0x0,0x80801000,0x810000,0x800000,0x80001000,0x80801000,0x80801000,0x0,0x0,0x0,0x38008000,0x1800,0x6000,0x1000,0x0,0x0,0x80000,0x1000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x40,0x40,0x0,0x40,0x51,0x0,0x0,0x51,0x51,0x51,0x2,0x40,0xc,0x0,0x0,0x0,0x0,0x50,0x40,0x0,0x50,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[16];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public Grammar(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Grammar(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new GrammarTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public Grammar(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new GrammarTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public Grammar(GrammarTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(GrammarTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[42];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 21; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 42; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 16; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
            case 6: jj_3_7(); break;
            case 7: jj_3_8(); break;
            case 8: jj_3_9(); break;
            case 9: jj_3_10(); break;
            case 10: jj_3_11(); break;
            case 11: jj_3_12(); break;
            case 12: jj_3_13(); break;
            case 13: jj_3_14(); break;
            case 14: jj_3_15(); break;
            case 15: jj_3_16(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}