�� sr breder.test.model.TestModel        L testst Ljava/util/List;xr breder.util.util.AbstractModel         xpsr java.util.ArrayListx����a� I sizexp  w  �sr breder.test.model.AbstractTest        	Z enabledZ endWithJ timerL expectedq ~ L 	mainClasst Ljava/lang/String;L memoryt Ljava/lang/Long;L nameq ~ L okt Ljava/lang/Boolean;L sourcesq ~ xp       5sq ~    w   
t 1xpsr java.lang.Long;��̏#� J valuexr java.lang.Number������  xp       ~t abstractsr java.lang.Boolean� r�՜�� Z valuexpsq ~    w   
sr breder.test.model.Source        L codeq ~ L nameq ~ xpt �package breder;
public class Test extends Test1 {
	public static void main(notnull IList<String> args) {
		Console.println(1);
	}
}t breder.Testsq ~ t /package breder;
public abstract class Test1 {
}t breder.Test1xsq ~        8sq ~    w   
t 1xpsq ~        yt abstractq ~ sq ~    w   
sq ~ t �package breder;
public abstract class Test extends Test1 {
	public static void main(notnull IList<String> args) {
		Console.println(1);
	}
}t breder.Testsq ~ t /package breder;
public abstract class Test1 {
}t breder.Test1xsq ~         sq ~    w   
t H['breder.Test','4','21','Test'] : can not create a instance of this typexppt abstractq ~ sq ~    w   
sq ~ t �package breder;
public abstract class Test extends Test1 {
	public static void main(notnull IList<String> args) {
		new Test();
		Console.println(1);
	}
}t breder.Testsq ~ t /package breder;
public abstract class Test1 {
}t breder.Test1xsq ~        Bsq ~    w   
t 1xpq ~ t abstractq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public static void main(notnull IList<String> args) {
		new Test();
		Console.println(1);
	}
}t breder.Testsq ~ t /package breder;
public abstract class Test1 {
}t breder.Test1xsq ~         sq ~    w   
t P['breder.Test','2','14','Test'] : the class not implemented the method '() a ()'xppt abstract.methodq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public static void main(notnull IList<String> args) {
		new Test();
		Console.println(1);
	}
}t breder.Testsq ~ t Jpackage breder;
public abstract class Test1 {
	public abstract void a();
}t breder.Test1xsq ~        Asq ~    w   
t 1xpsq ~        zt abstract.methodq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public void a() {
		Console.println(1);
	}
	public static void main(notnull IList<String> args) {
		new Test().a();
	}
}t breder.Testsq ~ t Jpackage breder;
public abstract class Test1 {
	public abstract void a();
}t breder.Test1xsq ~        <sr java.util.Arrays$ArrayList٤<�͈� [ at [Ljava/lang/Object;xpur [Ljava.lang.String;��V��{G  xp   t 1t breder.Testq ~ Kt abstract.methodq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public void a(Number i) {
		Console.println(1);
	}
	public static void main(notnull IList<String> args) {
		new Test().a(1);
	}
}t breder.Testsq ~ t cpackage breder;
public abstract class Test1 {
	public void a(Number i) {
		Console.println(2);
	}
}t breder.Test1xsq ~        �sq ~ Uuq ~ X   t 1q ~ [q ~ Kt abstract.methodq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public void a(Integer i) {
		Console.println(1);
	}
	public static void main(notnull IList<String> args) {
		new Test().a(1);
	}
}t breder.Testsq ~ t cpackage breder;
public abstract class Test1 {
	public void a(Number i) {
		Console.println(2);
	}
}t breder.Test1xsq ~        �sq ~ Uuq ~ X   t 2q ~ [q ~ Kt abstract.methodq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public void a(Integer i) {
		Console.println(1);
	}
	public static void main(notnull IList<String> args) {
		((Test1)new Test()).a(1.0);
	}
}t breder.Testsq ~ t cpackage breder;
public abstract class Test1 {
	public void a(Number i) {
		Console.println(2);
	}
}t breder.Test1xsq ~        Psq ~ Uuq ~ X   t 2q ~ [q ~ Kt abstract.methodq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public void a(Integer i) {
		Console.println(1);
	}
	public static void main(notnull IList<String> args) {
		((Test1)new Test()).a(1);
	}
}t breder.Testsq ~ t cpackage breder;
public abstract class Test1 {
	public void a(Number i) {
		Console.println(2);
	}
}t breder.Test1xsq ~        Jsq ~ Uuq ~ X   t 1q ~ [q ~ Kt abstract.methodq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public void a(Integer i) {
		Console.println(1);
	}
	public static void main(notnull IList<String> args) {
		new Test().a(1);
	}
}t breder.Testsq ~ t cpackage breder;
public abstract class Test1 {
	public void a(Number i) {
		Console.println(2);
	}
}t breder.Test1xsq ~        Dsq ~    w   
t 5xpsq ~        xt access.fieldq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static Number i;
	public static void main(notnull IList<String> args) {
		Test.i = 5;
		Console.println(Test.i);
	}
}t breder.Testxsq ~        9sq ~    w   
t 5xpq ~ �t access.fieldq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	protected static Number i;
	public static void main(notnull IList<String> args) {
		Test.i = 5;
		Console.println(Test.i);
	}
}t breder.Testxsq ~        :sq ~    w   
t 5xpq ~ �t access.fieldq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	private static Number i;
	public static void main(notnull IList<String> args) {
		Test.i = 5;
		Console.println(Test.i);
	}
}t breder.Testxsq ~         sq ~    w   
t J['breder.Test','3','23','i'] : a public field only with static declarationxppt access.fieldq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public Number i;
	public Test() {
		this.i = 1;
		Console.println(this.i);
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testxsq ~         sq ~    w   
t M['breder.Test','3','26','i'] : a protected field only with static declarationxppt access.fieldq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	protected Number i;
	public Test() {
		this.i = 1;
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		Console.println(t.i);
	}
}t breder.Testxsq ~         sq ~    w   
t 7['breder.Test','9','35','i'] : can not access the fieldxppt access.fieldq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	private Number i;
	public Test() {
		this.i = 1;
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		Console.println(t.i);
	}
}t breder.Testxsq ~        >sq ~    w   
t 1xpq ~ �t access.fieldq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	private Number i;
	public Test() {
		this.i = 1;
		Console.println(this.i);
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testxsq ~         sq ~    w   
t M['breder.Test','3','26','i'] : a protected field only with static declarationxppt access.fieldq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	protected Number i;
	public Test() {
		this.i = 1;
		Console.println(this.i);
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testxsq ~        2sq ~    w   
t 1xpq ~ �t access.methodq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public void a () {
		Console.println(1);
	}
	public Test() {
		this.a();
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testxsq ~        @sq ~    w   
t 1xpq ~ �t access.methodq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	protected void a () {
		Console.println(1);
	}
	public Test() {
		this.a();
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testxsq ~        �sq ~    w   
t 1xpq ~ �t access.methodq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	private void a () {
		Console.println(1);
	}
	public Test() {
		this.a();
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testxsq ~        -sq ~    w   
t 1xpq ~ �t access.methodq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public void a () {
		Console.println(1);
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t.a();
	}
}t breder.Testxsq ~         sq ~    w   
t 8['breder.Test','8','19','a'] : can not access the methodxppt access.methodq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	protected void a () {
		Console.println(1);
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t.a();
	}
}t breder.Testxsq ~         sq ~    w   
t 8['breder.Test','8','19','a'] : can not access the methodxppt access.methodq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	private void a () {
		Console.println(1);
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t.a();
	}
}t breder.Testxsq ~        Jsq ~    w   
t 1xpq ~ �t access.method.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void a () {
		Console.println(1);
	}
	public static void main(notnull IList<String> args) {
		Test.a();
	}
}t breder.Testxsq ~        4sq ~    w   
t 1xpq ~ �t access.method.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	protected static void a () {
		Console.println(1);
	}
	public static void main(notnull IList<String> args) {
		Test.a();
	}
}t breder.Testxsq ~        Tsq ~    w   
t 1xpq ~ �t access.method.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	private static void a () {
		Console.println(1);
	}
	public static void main(notnull IList<String> args) {
		Test.a();
	}
}t breder.Testxsq ~        7sq ~    w   
t 1xpq ~ �t access.method.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void a () {
		Console.println(1);
	}
	public Test() {
		Test.a();
	}
	public static void main(notnull IList<String> args) {
		new Test();
	}
}t breder.Testxsq ~        ,sq ~    w   
t 1xpq ~ �t access.method.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	protected static void a () {
		Console.println(1);
	}
	public Test() {
		Test.a();
	}
	public static void main(notnull IList<String> args) {
		new Test();
	}
}t breder.Testxsq ~        =sq ~    w   
t 1xpq ~ �t access.method.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	private static void a () {
		Console.println(1);
	}
	public Test() {
		Test.a();
	}
	public static void main(notnull IList<String> args) {
		new Test();
	}
}t breder.Testxsq ~         sq ~    w   
t K['breder.Test1','3','23','a'] : a public field only with static declarationxppt access.poly.fieldq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public Test() {
		this.a = 1;
		Console.println(this.a);
	}
	public static void main(notnull IList<String> args) {
		new Test();
	}
}t breder.Testsq ~ t 8package breder;
public class Test1 {
	public Number a;
}t breder.Test1xsq ~         sq ~    w   
t N['breder.Test1','3','26','a'] : a protected field only with static declarationxppt access.poly.fieldq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public Test() {
		this.a = 1;
		Console.println(this.a);
	}
	public static void main(notnull IList<String> args) {
		new Test();
	}
}t breder.Testsq ~ t ;package breder;
public class Test1 {
	protected Number a;
}t breder.Test1xsq ~         sq ~    w   
t 7['breder.Test','5','38','a'] : can not access the fieldxppt access.poly.fieldq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public Test() {
		this.a = 1;
		Console.println(this.a);
	}
	public static void main(notnull IList<String> args) {
		new Test();
	}
}t breder.Testsq ~ t 9package breder;
public class Test1 {
	private Number a;
}t breder.Test1xsq ~        Csq ~    w   
t 1xpq ~ t access.poly.field.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public static void main(notnull IList<String> args) {
		Test1.a = 1;
		Console.println(Test1.a);
	}
}t breder.Testsq ~ t ?package breder;
public class Test1 {
	public static Number a;
}t breder.Test1xsq ~        Usq ~    w   
t 1xpq ~ t access.poly.field.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public static void main(notnull IList<String> args) {
		Test1.a = 1;
		Console.println(Test1.a);
	}
}t breder.Testsq ~ t Bpackage breder;
public class Test1 {
	protected static Number a;
}t breder.Test1xsq ~         sq ~    w   
t 7['breder.Test','5','39','a'] : can not access the fieldxppt access.poly.field.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public static void main(notnull IList<String> args) {
		Test1.a = 1;
		Console.println(Test1.a);
	}
}t breder.Testsq ~ t @package breder;
public class Test1 {
	private static Number a;
}t breder.Test1xsq ~        1sq ~    w   
t 1xpq ~ t access.poly.methodq ~ sq ~    w   
sq ~ t zpackage breder;
public class Test extends Test1 {
	public static void main(notnull IList<String> args) {
		Test1.a();
	}
}t breder.Testsq ~ t Ypackage breder;
public class Test1 {
	public static void a() {
		Console.println(1);
	}
}t breder.Test1xsq ~        Gsq ~    w   
t 1xpq ~ t access.poly.methodq ~ sq ~    w   
sq ~ t zpackage breder;
public class Test extends Test1 {
	public static void main(notnull IList<String> args) {
		Test1.a();
	}
}t breder.Testsq ~ t \package breder;
public class Test1 {
	protected static void a() {
		Console.println(1);
	}
}t breder.Test1xsq ~         sq ~    w   
t 8['breder.Test','4','23','a'] : can not access the methodxppt access.poly.methodq ~ sq ~    w   
sq ~ t zpackage breder;
public class Test extends Test1 {
	public static void main(notnull IList<String> args) {
		Test1.a();
	}
}t breder.Testsq ~ t Zpackage breder;
public class Test1 {
	private static void a() {
		Console.println(1);
	}
}t breder.Test1xsq ~        0sq ~    w   
t 1xpq ~ t access.poly.methodq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public Test() {
		Test1.a();
	}
	public static void main(notnull IList<String> args) {
		new Test();
	}
}t breder.Testsq ~ t Ypackage breder;
public class Test1 {
	public static void a() {
		Console.println(1);
	}
}t breder.Test1xsq ~        Dsq ~    w   
t 1xpq ~ t access.poly.methodq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public Test() {
		Test1.a();
	}
	public static void main(notnull IList<String> args) {
		new Test();
	}
}t breder.Testsq ~ t \package breder;
public class Test1 {
	protected static void a() {
		Console.println(1);
	}
}t breder.Test1xsq ~         sq ~    w   
t 8['breder.Test','4','23','a'] : can not access the methodxppt access.poly.methodq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public Test() {
		Test1.a();
	}
	public static void main(notnull IList<String> args) {
		new Test();
	}
}t breder.Testsq ~ t Zpackage breder;
public class Test1 {
	private static void a() {
		Console.println(1);
	}
}t breder.Test1xsq ~        7sq ~    w   
t 1xpq ~ t access.source.method.staticq ~ sq ~    w   
sq ~ t lpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test1.a();
	}
}t breder.Testsq ~ t Ypackage breder;
public class Test1 {
	public static void a() {
		Console.println(1);
	}
}t breder.Test1xsq ~         sq ~    w   
t 8['breder.Test','4','23','a'] : can not access the methodxppt access.source.method.staticq ~ sq ~    w   
sq ~ t lpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test1.a();
	}
}t breder.Testsq ~ t Zpackage breder;
public class Test1 {
	private static void a() {
		Console.println(1);
	}
}t breder.Test1xsq ~         sq ~    w   
t 8['breder.Test','4','23','a'] : can not access the methodxppt access.source.method.staticq ~ sq ~    w   
sq ~ t lpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test1.a();
	}
}t breder.Testsq ~ t \package breder;
public class Test1 {
	protected static void a() {
		Console.println(1);
	}
}t breder.Test1xsq ~        /sq ~    w   
t 1xpq ~ t access.source.method.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public Test() {
		Test1.a();
	}
	public static void main(notnull IList<String> args) {
		new Test();
	}
}t breder.Testsq ~ t Ypackage breder;
public class Test1 {
	public static void a() {
		Console.println(1);
	}
}t breder.Test1xsq ~         sq ~    w   
t 8['breder.Test','4','23','a'] : can not access the methodxppt access.source.method.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public Test() {
		Test1.a();
	}
	public static void main(notnull IList<String> args) {
		new Test();
	}
}t breder.Testsq ~ t \package breder;
public class Test1 {
	protected static void a() {
		Console.println(1);
	}
}t breder.Test1xsq ~         sq ~    w   
t 8['breder.Test','4','23','a'] : can not access the methodxppt access.source.method.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public Test() {
		Test1.a();
	}
	public static void main(notnull IList<String> args) {
		new Test();
	}
}t breder.Testsq ~ t Zpackage breder;
public class Test1 {
	private static void a() {
		Console.println(1);
	}
}t breder.Test1xsq ~         sq ~ Uuq ~ X   t 0['breder.Test','4','25','println'] : ambiguidadet breder.Testpt ambiguidadeq ~ sq ~    w   
sq ~ t xpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(null);
	}
}t breder.Testxsq ~          sq ~ Uuq ~ X   t  t breder.Testpt api.breder.compiler.BGrammerpsq ~    w   
sq ~ t �package breder;
import breder.compiler.standard.*;
import breder.compiler.*;
public class Test {
	public static void main(notnull IList<String> args) throws Exception {
		new BGrammer(new File("breder/Test1.breder")).start();
	}
}t breder.Testsq ~ t public class Main {}t breder.Test1xsq ~          sq ~ Uuq ~ X   t  t breder.Testpt api.breder.compiler.BGrammerpsq ~    w   
sq ~ tpackage breder;
import breder.compiler.standard.*;
import breder.compiler.*;
public class Test {
	public static void main(notnull IList<String> args) throws Exception {
		for (Index i = 1 ; i < 100000 ; i += 1) {
			new BGrammer(new File("breder/Test1.breder"));
		}
	}
}t breder.Testsq ~ t public class Main {}t breder.Test1xsq ~         Tsq ~ Uuq ~ X   t 	t breder.Testpt api.breder.compiler.Parserpsq ~    w   
sq ~ tpackage breder;
import breder.compiler.standard.*;
import breder.compiler.*;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws Exception {
		Parser p = new Parser(new LocalFile("breder/Test.breder"));
	}
}t breder.Testxsq ~         sq ~ Uuq ~ X   t  q ~#pt api.breder.compiler.Parserpsq ~    w   
sq ~ tpackage breder;
import breder.compiler.standard.*;
import breder.compiler.*;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws Exception {
		Parser p = new Parser(new LocalFile("breder/Test.breder"));
		p.next();
	}
}t breder.Testxsq ~         %sq ~ Uuq ~ X   Ut packaget bredert ;t importt bredert .t compilert .t standardt .t *t ;t importt bredert .t compilert .t *t ;t importt bredert .t iot .t localt .t standardt .t *t ;t publict classt Testt {t publict statict voidt maint (t notnullt IListt <t Stringt >t argst )t throwst 	Exceptiont {t Parsert pt =t newt Parsert (t newt 	LocalFilet (t breder/Test.bredert )t )t ;t whilet (t pt .t hasTokent (t )t )t {t Consolet .t printlnt (t pt .t nextt (t )t )t ;t }t }t }q ~#sq ~        �t api.breder.compiler.Parserpsq ~    w   
sq ~ tIpackage breder;
import breder.compiler.standard.*;
import breder.compiler.*;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws Exception {
		Parser p = new Parser(new LocalFile("breder/Test.breder"));
		while (p.hasToken()) {
			Console.println(p.next());
		}
	}
}t breder.Testxsq ~         6sq ~ Uuq ~ X   t throw breder.io.IOException : t #	method () Parser (breder.io.IFile)t @	method () main (breder.util.IList<breder.lang.standard.String>)q ~#sq ~        �t api.breder.compiler.Parserpsq ~    w   
sq ~ tpackage breder;
import breder.compiler.standard.*;
import breder.compiler.*;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws Exception {
		Parser p = new Parser(new LocalFile("breder/Test"));
	}
}t breder.Testxsq ~         =sq ~ Uuq ~ X   t 'throw breder.compiler.ParseException : t K	method (breder.lang.standard.String) doToken (breder.lang.standard.String)t @	method () main (breder.util.IList<breder.lang.standard.String>)q ~#sq ~        �t api.breder.compiler.Parserpsq ~    w   
sq ~ t#package breder;
import breder.compiler.standard.*;
import breder.compiler.*;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws Exception {
		Parser p = new Parser(new LocalFile("breder/Test.breder"));
		p.doToken("test");
	}
}t breder.Testxsq ~         7sq ~ Uuq ~ X   t  q ~#pt api.breder.compiler.Parserpsq ~    w   
sq ~ t	package breder;
import breder.compiler.standard.*;
import breder.compiler.*;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws Exception {
		Parser p = new Parser(new LocalFile("breder/Test.breder"));
		p.doToken("package");
		p.doToken("breder");
		p.doToken(";");
		p.doToken("import");
		p.doToken("breder");
		p.doToken(".");
		p.doToken("compiler");
		p.doToken(".");
		p.doToken("standard");
		p.doToken(".");
		p.doToken("*");
		p.doToken(";");
	}
}t breder.Testxsq ~        1sq ~ Uuq ~ X   t Brt  t et der Languaget nullt nullt breder.Testpt api.breder.io.StringInputStreamq ~ sq ~    w   
sq ~ t�package breder;
public class Test {
	public static void main(notnull IList<String> args) throws IOException {
		StringInputStream input = new StringInputStream("Breder Language");
		Console.println(input.read(2));
		Console.println(input.read(0));
		Console.println(input.read(1));
		Console.println(input.read(1024));
		Console.println(input.read(1024));
		input.close();
		input = new StringInputStream("Breder Language");
		input.close();
		Console.println(input.read(1));
	}
}t breder.Testxsq ~        6sq ~ Uuq ~ X   t  q ~#pt api.breder.io.local.Fileq ~ sq ~    w   
sq ~ t �package breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) {
		LocalFile file = new LocalFile("breder/Test.breder");
	}
}t breder.Testxsq ~        _sq ~ Uuq ~ X   t  q ~#pt api.breder.io.local.Fileq ~ sq ~    w   
sq ~ t �package breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) {
		IFile file = new LocalFile("breder/Test.breder");
	}
}t breder.Testxsq ~        /sq ~ Uuq ~ X   t trueq ~#sq ~        �t api.breder.io.local.Fileq ~ sq ~    w   
sq ~ t �package breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) {
		IFile file = new LocalFile("breder/Test.breder");
		Console.println(file.exist());
	}
}t breder.Testxsq ~        ?sq ~ Uuq ~ X   t falseq ~#sq ~        �t api.breder.io.local.Fileq ~ sq ~    w   
sq ~ t �package breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) {
		IFile file = new LocalFile("breder/Test");
		Console.println(file.exist());
	}
}t breder.Testxsq ~        .sq ~ Uuq ~ X   t trueq ~#sq ~        �t api.breder.io.local.Fileq ~ sq ~    w   
sq ~ t �package breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) {
		IFile file = new LocalFile("breder");
		Console.println(file.exist());
	}
}t breder.Testxsq ~        'sq ~ Uuq ~ X   t falseq ~#sq ~        �t api.breder.io.local.Fileq ~ sq ~    w   
sq ~ t �package breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) {
		IFile file = new LocalFile("brede");
		Console.println(file.exist());
	}
}t breder.Testxsq ~        <sq ~ Uuq ~ X   t Test.brederq ~#sq ~        �t api.breder.io.local.Fileq ~ sq ~    w   
sq ~ t �package breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) {
		IFile file = new LocalFile("breder/Test.breder");
		Console.println(file.getName());
	}
}t breder.Testxsq ~        <sq ~ Uuq ~ X   t breder/Test.brederq ~#sq ~        �t api.breder.io.local.Fileq ~ sq ~    w   
sq ~ t �package breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) {
		IFile file = new LocalFile("breder/Test.breder");
		Console.println(file.getAbsoluteName());
	}
}t breder.Testxsq ~        &sq ~ Uuq ~ X   t j['breder.Test','5','42','LocalFileInputStream'] : the method has exception of type 'breder.io.IOException't breder.Testpt #api.breder.io.local.FileInputStreamq ~ sq ~    w   
sq ~ t �package breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) {
		IInputStream input = new LocalFileInputStream(new LocalFile("breder/Test.breder"));
	}
}t breder.Testxsq ~        0sq ~ Uuq ~ X   t throw breder.io.IOException : t D	method () LocalFileInputStream (breder.io.local.standard.LocalFile)t @	method () main (breder.util.IList<breder.lang.standard.String>)q ~sq ~        �t #api.breder.io.local.FileInputStreamq ~ sq ~    w   
sq ~ t �package breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws IOException {
		IInputStream input = new LocalFileInputStream(new LocalFile("breder/Test"));
	}
}t breder.Testxsq ~        0sq ~ Uuq ~ X   t  q ~pt #api.breder.io.local.FileInputStreamq ~ sq ~    w   
sq ~ t �package breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws IOException {
		IInputStream input = new LocalFileInputStream(new LocalFile("breder/Test.breder"));
		input.close();
	}
}t breder.Testxsq ~        .sq ~ Uuq ~ X   	t package breder;t "import breder.io.local.standard.*;t public class Test {t I	public static void main(notnull IList<String> args) throws IOException {t U		IInputStream input = new LocalFileInputStream(new LocalFile("breder/Test.breder"));t $		Console.println(input.read(1024));t 		input.close();t 	}t }q ~sq ~        �t #api.breder.io.local.FileInputStreamq ~ sq ~    w   
sq ~ t!package breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws IOException {
		IInputStream input = new LocalFileInputStream(new LocalFile("breder/Test.breder"));
		Console.println(input.read(1024));
		input.close();
	}
}t breder.Testxsq ~        6sq ~ Uuq ~ X   t package breder;t "import breder.io.local.standard.*;t public class Test {t 9	public static void main(notnull IList<String> args) throq ~sq ~        �t #api.breder.io.local.FileInputStreamq ~ sq ~    w   
sq ~ t package breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws IOException {
		IInputStream input = new LocalFileInputStream(new LocalFile("breder/Test.breder"));
		Console.println(input.read(128));
		input.close();
	}
}t breder.Testxsq ~        Xsq ~ Uuq ~ X   t package breder;t "import breder.io.local.standard.*;t public class Test {t 9	public static void main(notnull IList<String> args) throt ws IOException {t U		IInputStream input = new LocalFileInputStream(new LocalFile("breder/Test.breder"));t 		Console.println(input.rq ~sq ~        �t #api.breder.io.local.FileInputStreamq ~ sq ~    w   
sq ~ tDpackage breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws IOException {
		IInputStream input = new LocalFileInputStream(new LocalFile("breder/Test.breder"));
		Console.println(input.read(128));
		Console.println(input.read(128));
		input.close();
	}
}t breder.Testxsq ~        Fsq ~ Uuq ~ X   t package breder;t "import breder.io.local.standard.*;t public class Test {t 9	public static void main(notnull IList<String> args) throt ws IOException {t U		IInputStream input = new LocalFileInputStream(new LocalFile("breder/Test.breder"));t 		Console.println(input.rt 
ead(128));t #		Console.println(input.read(128));t #		Console.println(input.read(128));t 		input.close();t 	}t }q ~sq ~        �t #api.breder.io.local.FileInputStreamq ~ sq ~    w   
sq ~ thpackage breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws IOException {
		IInputStream input = new LocalFileInputStream(new LocalFile("breder/Test.breder"));
		Console.println(input.read(128));
		Console.println(input.read(128));
		Console.println(input.read(128));
		input.close();
	}
}t breder.Testxsq ~        3sq ~ Uuq ~ X   t  q ~pt $api.breder.io.local.FileOutputStreamq ~ sq ~    w   
sq ~ tpackage breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws IOException {
		IOutputStream output = new LocalFileOutputStream(new LocalFile("breder/Test1.breder"));
		output.close();
	}
}t breder.Testxsq ~        1sq ~ Uuq ~ X   t  q ~pt $api.breder.io.local.FileOutputStreamq ~ sq ~    w   
sq ~ tpackage breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws IOException {
		IOutputStream output = new LocalFileOutputStream(new LocalFile("breder/Test1.breder"));
		output.write("test");
		output.close();
	}
}t breder.Testxsq ~        1sq ~ Uuq ~ X   t testq ~sq ~        �t $api.breder.io.local.FileOutputStreamq ~ sq ~    w   
sq ~ t�package breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws IOException {
		IOutputStream output = new LocalFileOutputStream(new LocalFile("breder/Test1.breder"));
		output.write("test");
		output.close();
		IInputStream input = new LocalFileInputStream(new LocalFile("breder/Test1.breder"));
		Console.println(input.read(1024));
		input.close();
	}
}t breder.Testxsq ~        4sq ~ Uuq ~ X   t tt et st tq ~sq ~        �t $api.breder.io.local.FileOutputStreamq ~ sq ~    w   
sq ~ t+package breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws IOException {
		IOutputStream output = new LocalFileOutputStream(new LocalFile("breder/Test1.breder"));
		output.write("test");
		output.close();
		IInputStream input = new LocalFileInputStream(new LocalFile("breder/Test1.breder"));
		Console.println(input.read(1));
		Console.println(input.read(1));
		Console.println(input.read(1));
		Console.println(input.read(1));
		Console.println(input.read(1));
		input.close();
	}
}t breder.Testxsq ~        (sq ~ Uuq ~ X    q ~sq ~        t $api.breder.io.local.FileOutputStreamq ~ sq ~    w   
sq ~ t�package breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws IOException {
		IOutputStream output = new LocalFileOutputStream(new LocalFile("breder/Test1.breder"));
		output.write("test");
		IInputStream input = new LocalFileInputStream(new LocalFile("breder/Test1.breder"));
		Console.println(input.read(1024));
		input.close();
	}
}t breder.Testxsq ~        Asq ~ Uuq ~ X   t testq ~sq ~        �t $api.breder.io.local.FileOutputStreamq ~ sq ~    w   
sq ~ t�package breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws IOException {
		IOutputStream output = new LocalFileOutputStream(new LocalFile("breder/Test1.breder"));
		output.write("test");
		output.flush();
		IInputStream input = new LocalFileInputStream(new LocalFile("breder/Test1.breder"));
		Console.println(input.read(1024));
		input.close();
	}
}t breder.Testxsq ~        Esq ~ Uuq ~ X   t testt trueq ~sq ~        �t $api.breder.io.local.FileOutputStreamq ~ sq ~    w   
sq ~ t�package breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws IOException {
		IOutputStream output = new LocalFileOutputStream(new LocalFile("breder/Test1.breder"));
		output.write("test");
		output.flush();
		IInputStream input = new LocalFileInputStream(new LocalFile("breder/Test1.breder"));
		Console.println(input.read(1024));
		input.close();
		Console.println(new LocalFile("breder/Test1.breder").exist());
	}
}t breder.Testxsq ~        Fsq ~ Uuq ~ X   t  t trueq ~sq ~        �t $api.breder.io.local.FileOutputStreamq ~ sq ~    w   
sq ~ t�package breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws IOException {
		IOutputStream output = new LocalFileOutputStream(new LocalFile("breder/Test1.breder"));
		output.write("test");
		IInputStream input = new LocalFileInputStream(new LocalFile("breder/Test1.breder"));
		Console.println(input.read(1024));
		input.close();
		Console.println(new LocalFile("breder/Test1.breder").exist());
	}
}t breder.Testxsq ~        Qsq ~ Uuq ~ X   t testq ~sq ~        �t $api.breder.io.local.FileOutputStreamq ~ sq ~    w   
sq ~ t�package breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws IOException {
		IOutputStream output = new LocalFile("breder/Test1.breder").getOutputStream();
		output.write("test");
		output.close();
		IInputStream input = new LocalFileInputStream(new LocalFile("breder/Test1.breder"));
		Console.println(input.read(1024));
		input.close();
	}
}t breder.Testxsq ~        Isq ~ Uuq ~ X   t testq ~sq ~        �t $api.breder.io.local.FileOutputStreamq ~ sq ~    w   
sq ~ t�package breder;
import breder.io.local.standard.*;
public class Test {
	public static void main(notnull IList<String> args) throws IOException {
		IOutputStream output = new LocalFile("breder/Test1.breder").getOutputStream();
		output.write("test");
		output.close();
		IInputStream input = new LocalFile("breder/Test1.breder").getInputStream();
		Console.println(input.read(1024));
		input.close();
	}
}t breder.Testxsq ~        4sq ~ Uuq ~ X   t 1t breder.Testq ~ �t api.breder.lang.Consoleq ~ sq ~ Uur [Lbreder.test.model.Source;��a���  xp   sq ~ t upackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
	}
}q ~�sq ~        Asq ~ Uuq ~ X   t 2q ~�sq ~        �q ~�q ~ sq ~ Uuq ~�   sq ~ t wpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1+1);
	}
}q ~�sq ~        4sq ~ Uuq ~ X   t aq ~�q ~ �q ~�q ~ sq ~ Uuq ~�   sq ~ t wpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println("a");
	}
}q ~�sq ~        5sq ~ Uuq ~ X   t trueq ~�q ~ �q ~�q ~ sq ~ Uuq ~�   sq ~ t xpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(true);
	}
}q ~�sq ~        Vsq ~ Uuq ~ X   t falset breder.Testq ~ �q ~�q ~ sq ~ Uuq ~�   sq ~ t ypackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(false);
	}
}q ~ sq ~        ;sq ~ Uuq ~ X   t 1.500000q ~ q ~ �q ~�q ~ sq ~ Uuq ~�   sq ~ t wpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1.5);
	}
}q ~ sq ~        1sq ~ Uuq ~ X   t 3q ~ sq ~        �q ~�q ~ sq ~ Uuq ~�   sq ~ t {package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1.5+1.5);
	}
}q ~ sq ~        1sq ~ Uuq ~ X   t 3.100000q ~ q ~ �q ~�q ~ sq ~ Uuq ~�   sq ~ t {package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1.5+1.6);
	}
}q ~ sq ~        2sq ~ Uuq ~ X   t 3.100000t breder.Testq ~ �q ~�q ~ sq ~ Uuq ~�   sq ~ t ypackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.print(1.5+1.6);
	}
}q ~"sq ~        !sq ~ Uuq ~ X   t 3q ~"sq ~        �q ~�q ~ sq ~ Uuq ~�   sq ~ t ypackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.print(1.5+1.5);
	}
}q ~"sq ~        sq ~ Uuq ~ X   t 1.500000q ~"q ~ �q ~�q ~ sq ~ Uuq ~�   sq ~ t upackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.print(1.5);
	}
}q ~"sq ~        @sq ~ Uuq ~ X   t falseq ~"q ~ �q ~�q ~ sq ~ Uuq ~�   sq ~ t wpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.print(false);
	}
}q ~"sq ~        4sq ~ Uuq ~ X   t aq ~"q ~ �q ~�q ~ sq ~ Uuq ~�   sq ~ t upackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.print("a");
	}
}q ~"sq ~        $sq ~ Uuq ~ X   t 2q ~"sq ~        �q ~�q ~ sq ~ Uuq ~�   sq ~ t upackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.print(1+1);
	}
}q ~"sq ~        �sq ~ Uuq ~ X   t 1q ~"q ~ �q ~�q ~ sq ~ Uuq ~�   sq ~ t spackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.print(1);
	}
}q ~"sq ~        Hsq ~ Uuq ~ X   t 1t 2t 3t 4t 5t 6t 7t 8t 9t 10t 11t 12t 13t 14t 15t 16t 17t 18t 19t 20t 21t 22t 23t 24t 25t 26t 27t 28t 29t 30t 31t 32t 33t 34t 35t 36t 37t 38t 39t 40t 41t 42t 43t 44t 45t 46t 47t 48t 49t 50t 51t 52t 53t 54t 55t 56t 57t 58t 59t 60t 61t 62t 63t 64t 65t 66t 67t 68t 69t 70t 71t 72t 73t 74t 75t 76t 77t 78t 79t 80t 81t 82t 83t 84t 85t 86t 87t 88t 89t 90t 91t 92t 93t 94t 95t 96t 97t 98t 99t 100t 101t 102t 103t 104t 105t 106t 107t 108t 109t 110t 111t 112t 113t 114t 115t 116t 117t 118t 119t 120t 121t 122t 123t 124t 125t 126t 127t 128t 129t 130t 131t 132t 133t 134t 135t 136t 137t 138t 139t 140t 141t 142t 143t 144t 145t 146t 147t 148t 149t 150t 151t 152t 153t 154t 155t 156t 157t 158t 159t 160t 161t 162t 163t 164t 165t 166t 167t 168t 169t 170t 171t 172t 173t 174t 175t 176t 177t 178t 179t 180t 181t 182t 183t 184t 185t 186t 187t 188t 189t 190t 191t 192t 193t 194t 195t 196t 197t 198t 199t 200t 201t 202t 203t 204t 205t 206t 207t 208t 209t 210t 211t 212t 213t 214t 215t 216t 217t 218t 219t 220t 221t 222t 223t 224t 225t 226t 227t 228t 229t 230t 231t 232t 233t 234t 235t 236t 237t 238t 239t 240t 241t 242t 243t 244t 245t 246t 247t 248t 249t 250t 251t 252t 253t 254t 255t 256t 257t 258t 259t 260t 261t 262t 263t 264t 265t 266t 267t 268t 269t 270t 271t 272t 273t 274t 275t 276t 277t 278t 279t 280t 281t 282t 283t 284t 285t 286t 287t 288t 289t 290t 291t 292t 293t 294t 295t 296t 297t 298t 299t 300t 301t 302t 303t 304t 305t 306t 307t 308t 309t 310t 311t 312t 313t 314t 315t 316t 317t 318t 319t 320t 321t 322t 323t 324t 325t 326t 327t 328t 329t 330t 331t 332t 333t 334t 335t 336t 337t 338t 339t 340t 341t 342t 343t 344t 345t 346t 347t 348t 349t 350t 351t 352t 353t 354t 355t 356t 357t 358t 359t 360t 361t 362t 363t 364t 365t 366t 367t 368t 369t 370t 371t 372t 373t 374t 375t 376t 377t 378t 379t 380t 381t 382t 383t 384t 385t 386t 387t 388t 389t 390t 391t 392t 393t 394t 395t 396t 397t 398t 399t 400t 401t 402t 403t 404t 405t 406t 407t 408t 409t 410t 411t 412t 413t 414t 415t 416t 417t 418t 419t 420t 421t 422t 423t 424t 425t 426t 427t 428t 429t 430t 431t 432t 433t 434t 435t 436t 437t 438t 439t 440t 441t 442t 443t 444t 445t 446t 447t 448t 449t 450t 451t 452t 453t 454t 455t 456t 457t 458t 459t 460t 461t 462t 463t 464t 465t 466t 467t 468t 469t 470t 471t 472t 473t 474t 475t 476t 477t 478t 479t 480t 481t 482t 483t 484t 485t 486t 487t 488t 489t 490t 491t 492t 493t 494t 495t 496t 497t 498t 499t 500t 501t 502t 503t 504t 505t 506t 507t 508t 509t 510t 511t 512t 513t 514t 515t 516t 517t 518t 519t 520t 521t 522t 523t 524t 525t 526t 527t 528t 529t 530t 531t 532t 533t 534t 535t 536t 537t 538t 539t 540t 541t 542t 543t 544t 545t 546t 547t 548t 549t 550t 551t 552t 553t 554t 555t 556t 557t 558t 559t 560t 561t 562t 563t 564t 565t 566t 567t 568t 569t 570t 571t 572t 573t 574t 575t 576t 577t 578t 579t 580t 581t 582t 583t 584t 585t 586t 587t 588t 589t 590t 591t 592t 593t 594t 595t 596t 597t 598t 599t 600t 601t 602t 603t 604t 605t 606t 607t 608t 609t 610t 611t 612t 613t 614t 615t 616t 617t 618t 619t 620t 621t 622t 623t 624t 625t 626t 627t 628t 629t 630t 631t 632t 633t 634t 635t 636t 637t 638t 639t 640t 641t 642t 643t 644t 645t 646t 647t 648t 649t 650t 651t 652t 653t 654t 655t 656t 657t 658t 659t 660t 661t 662t 663t 664t 665t 666t 667t 668t 669t 670t 671t 672t 673t 674t 675t 676t 677t 678t 679t 680t 681t 682t 683t 684t 685t 686t 687t 688t 689t 690t 691t 692t 693t 694t 695t 696t 697t 698t 699t 700t 701t 702t 703t 704t 705t 706t 707t 708t 709t 710t 711t 712t 713t 714t 715t 716t 717t 718t 719t 720t 721t 722t 723t 724t 725t 726t 727t 728t 729t 730t 731t 732t 733t 734t 735t 736t 737t 738t 739t 740t 741t 742t 743t 744t 745t 746t 747t 748t 749t 750t 751t 752t 753t 754t 755t 756t 757t 758t 759t 760t 761t 762t 763t 764t 765t 766t 767t 768t 769t 770t 771t 772t 773t 774t 775t 776t 777t 778t 779t 780t 781t 782t 783t 784t 785t 786t 787t 788t 789t 790t 791t 792t 793t 794t 795t 796t 797t 798t 799t 800t 801t 802t 803t 804t 805t 806t 807t 808t 809t 810t 811t 812t 813t 814t 815t 816t 817t 818t 819t 820t 821t 822t 823t 824t 825t 826t 827t 828t 829t 830t 831t 832t 833t 834t 835t 836t 837t 838t 839t 840t 841t 842t 843t 844t 845t 846t 847t 848t 849t 850t 851t 852t 853t 854t 855t 856t 857t 858t 859t 860t 861t 862t 863t 864t 865t 866t 867t 868t 869t 870t 871t 872t 873t 874t 875t 876t 877t 878t 879t 880t 881t 882t 883t 884t 885t 886t 887t 888t 889t 890t 891t 892t 893t 894t 895t 896t 897t 898t 899t 900t 901t 902t 903t 904t 905t 906t 907t 908t 909t 910t 911t 912t 913t 914t 915t 916t 917t 918t 919t 920t 921t 922t 923t 924t 925t 926t 927t 928t 929t 930t 931t 932t 933t 934t 935t 936t 937t 938t 939t 940t 941t 942t 943t 944t 945t 946t 947t 948t 949t 950t 951t 952t 953t 954t 955t 956t 957t 958t 959t 960t 961t 962t 963t 964t 965t 966t 967t 968t 969t 970t 971t 972t 973t 974t 975t 976t 977t 978t 979t 980t 981t 982t 983t 984t 985t 986t 987t 988t 989t 990t 991t 992t 993t 994t 995t 996t 997t 998t 999t 1000t 1001t 1002t 1003t 1004t 1005t 1006t 1007t 1008t 1009t 1010t 1011t 1012t 1013t 1014t 1015t 1016t 1017t 1018t 1019t 1020t 1021t 1022t 1023t 1024t breder.Testsq ~        �t api.breder.lang.Consoleq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for (Index i = 1 ; i <= 1024 ; i += 1) {
			Console.println(i);
		}
	}
}q ~\sq ~        Psq ~ Uuq ~ X   
t 1t 2t 3t 4t 5t 6t 7t 8t 9t 10t breder.Testsq ~        �t api.breder.lang.Consoleq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for (Index i = 1 ; i <= 10 ; i += 1) {
			Console.println(i);
		}
	}
}t breder.Testxsq ~        =sq ~ Uuq ~ X   t 1t breder.Testq ~ �t api.breder.lang.Consoleq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for (Index i = 1 ; i <= 1 ; i += 1) {
			Console.println(i);
		}
	}
}t breder.Testxsq ~        Asq ~ Uuq ~ X   9t nullt 1t 2t 3t nullt 2t 1t nullt nullt  t nullt nullt 2t 3t nullt 2t nullt nullt nullt  t nullt nullt nullt 3t nullt nullt nullt nullt nullt  t >throw breder.util.standard.IndexOutOfBoundsRuntimeException : t e	method (breder.lang.standard.Index) indexOf (breder.lang.standard.String,breder.lang.standard.Index)t @	method () main (breder.util.IList<breder.lang.standard.String>)t >throw breder.util.standard.IndexOutOfBoundsRuntimeException : t e	method (breder.lang.standard.Index) indexOf (breder.lang.standard.String,breder.lang.standard.Index)t @	method () main (breder.util.IList<breder.lang.standard.String>)t >throw breder.util.standard.IndexOutOfBoundsRuntimeException : t e	method (breder.lang.standard.Index) indexOf (breder.lang.standard.String,breder.lang.standard.Index)t @	method () main (breder.util.IList<breder.lang.standard.String>)t >throw breder.util.standard.IndexOutOfBoundsRuntimeException : t e	method (breder.lang.standard.Index) indexOf (breder.lang.standard.String,breder.lang.standard.Index)t @	method () main (breder.util.IList<breder.lang.standard.String>)t >throw breder.util.standard.IndexOutOfBoundsRuntimeException : t e	method (breder.lang.standard.Index) indexOf (breder.lang.standard.String,breder.lang.standard.Index)t @	method () main (breder.util.IList<breder.lang.standard.String>)t >throw breder.util.standard.IndexOutOfBoundsRuntimeException : t e	method (breder.lang.standard.Index) indexOf (breder.lang.standard.String,breder.lang.standard.Index)t @	method () main (breder.util.IList<breder.lang.standard.String>)t >throw breder.util.standard.IndexOutOfBoundsRuntimeException : t e	method (breder.lang.standard.Index) indexOf (breder.lang.standard.String,breder.lang.standard.Index)t @	method () main (breder.util.IList<breder.lang.standard.String>)t >throw breder.util.standard.IndexOutOfBoundsRuntimeException : t e	method (breder.lang.standard.Index) indexOf (breder.lang.standard.String,breder.lang.standard.Index)t @	method () main (breder.util.IList<breder.lang.standard.String>)t >throw breder.util.standard.IndexOutOfBoundsRuntimeException : t e	method (breder.lang.standard.Index) indexOf (breder.lang.standard.String,breder.lang.standard.Index)t @	method () main (breder.util.IList<breder.lang.standard.String>)t breder.Testpt api.breder.lang.String.indexOfq ~ sq ~    w   
sq ~ t}package breder;
import breder.util.*;
import breder.math.*;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println("tes".indexOf("",1));
		Console.println("tes".indexOf("t",1));
		Console.println("tes".indexOf("e",1));
		Console.println("tes".indexOf("s",1));
		Console.println("tes".indexOf("_",1));
		Console.println("tes".indexOf("es",1));
		Console.println("tes".indexOf("te",1));
		Console.println("tes".indexOf("st",1));
		Console.println("tes".indexOf("ta",1));
		Console.println("");
		Console.println("tes".indexOf("",2));
		Console.println("tes".indexOf("t",2));
		Console.println("tes".indexOf("e",2));
		Console.println("tes".indexOf("s",2));
		Console.println("tes".indexOf("_",2));
		Console.println("tes".indexOf("es",2));
		Console.println("tes".indexOf("te",2));
		Console.println("tes".indexOf("st",2));
		Console.println("tes".indexOf("ta",2));
		Console.println("");
		Console.println("tes".indexOf("",3));
		Console.println("tes".indexOf("t",3));
		Console.println("tes".indexOf("e",3));
		Console.println("tes".indexOf("s",3));
		Console.println("tes".indexOf("_",3));
		Console.println("tes".indexOf("es",3));
		Console.println("tes".indexOf("te",3));
		Console.println("tes".indexOf("st",3));
		Console.println("tes".indexOf("ta",3));
		Console.println("");
		try { Console.println("tes".indexOf("",4)); } catch (Exception e) { e.printStackTrace(); }
		try { Console.println("tes".indexOf("t",4)); } catch (Exception e) { e.printStackTrace(); }
		try { Console.println("tes".indexOf("e",4)); } catch (Exception e) { e.printStackTrace(); }
		try { Console.println("tes".indexOf("s",4)); } catch (Exception e) { e.printStackTrace(); }
		try { Console.println("tes".indexOf("_",4)); } catch (Exception e) { e.printStackTrace(); }
		try { Console.println("tes".indexOf("es",4)); } catch (Exception e) { e.printStackTrace(); }
		try { Console.println("tes".indexOf("te",4)); } catch (Exception e) { e.printStackTrace(); }
		try { Console.println("tes".indexOf("st",4)); } catch (Exception e) { e.printStackTrace(); }
		try { Console.println("tes".indexOf("ta",4)); } catch (Exception e) { e.printStackTrace(); }
	}
}t breder.Testxsq ~        *sq ~ Uuq ~ X   	t nullt 1t 2t 3t nullt 2t 1t 3t nullq ~�pt api.breder.lang.String.indexOfq ~ sq ~    w   
sq ~ t�package breder;
import breder.util.*;
import breder.math.*;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println("tes".indexOf(""));
		Console.println("tes".indexOf("t"));
		Console.println("tes".indexOf("e"));
		Console.println("tes".indexOf("s"));
		Console.println("test".indexOf("_"));
		Console.println("test".indexOf("es"));
		Console.println("test".indexOf("te"));
		Console.println("test".indexOf("st"));
		Console.println("test".indexOf("ta"));
	}
}t breder.Testxsq ~        6sq ~ Uuq ~ X   	t nullt 1t 2t 3t nullt 2t 1t 3t nullq ~�pt "api.breder.lang.String.lastIndexOfq ~ sq ~    w   
sq ~ tpackage breder;
import breder.util.*;
import breder.math.*;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println("tes".lastIndexOf(""));
		Console.println("tes".lastIndexOf("t"));
		Console.println("tes".lastIndexOf("e"));
		Console.println("tes".lastIndexOf("s"));
		Console.println("test".lastIndexOf("_"));
		Console.println("test".lastIndexOf("es"));
		Console.println("test".lastIndexOf("te"));
		Console.println("test".lastIndexOf("st"));
		Console.println("test".lastIndexOf("ta"));
	}
}t breder.Testxsq ~        0sq ~ Uuq ~ X   1t nullt 1t nullt nullt nullt nullt nullt nullt nullt  t nullt 1t 2t nullt nullt nullt 1t nullt nullt  t nullt 1t 2t 3t nullt 2t 1t nullt nullt  t >throw breder.util.standard.IndexOutOfBoundsRuntimeException : t i	method (breder.lang.standard.Index) lastIndexOf (breder.lang.standard.String,breder.lang.standard.Index)t @	method () main (breder.util.IList<breder.lang.standard.String>)t >throw breder.util.standard.IndexOutOfBoundsRuntimeException : t i	method (breder.lang.standard.Index) lastIndexOf (breder.lang.standard.String,breder.lang.standard.Index)t @	method () main (breder.util.IList<breder.lang.standard.String>)t >throw breder.util.standard.IndexOutOfBoundsRuntimeException : t i	method (breder.lang.standard.Index) lastIndexOf (breder.lang.standard.String,breder.lang.standard.Index)t @	method () main (breder.util.IList<breder.lang.standard.String>)t >throw breder.util.standard.IndexOutOfBoundsRuntimeException : t i	method (breder.lang.standard.Index) lastIndexOf (breder.lang.standard.String,breder.lang.standard.Index)t @	method () main (breder.util.IList<breder.lang.standard.String>)t >throw breder.util.standard.IndexOutOfBoundsRuntimeException : t i	method (breder.lang.standard.Index) lastIndexOf (breder.lang.standard.String,breder.lang.standard.Index)t @	method () main (breder.util.IList<breder.lang.standard.String>)t 2t 1t 3t nullq ~�pt "api.breder.lang.String.lastIndexOfq ~ sq ~    w   
sq ~ t	package breder;
import breder.util.*;
import breder.math.*;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println("tes".lastIndexOf("",1));
		Console.println("tes".lastIndexOf("t",1));
		Console.println("tes".lastIndexOf("e",1));
		Console.println("tes".lastIndexOf("s",1));
		Console.println("tes".lastIndexOf("_",1));
		Console.println("test".lastIndexOf("es",1));
		Console.println("test".lastIndexOf("te",1));
		Console.println("test".lastIndexOf("st",1));
		Console.println("test".lastIndexOf("ta",1));
		Console.println("");
		Console.println("tes".lastIndexOf("",2));
		Console.println("tes".lastIndexOf("t",2));
		Console.println("tes".lastIndexOf("e",2));
		Console.println("tes".lastIndexOf("s",2));
		Console.println("tes".lastIndexOf("_",2));
		Console.println("test".lastIndexOf("es",2));
		Console.println("test".lastIndexOf("te",2));
		Console.println("test".lastIndexOf("st",2));
		Console.println("test".lastIndexOf("ta",2));
		Console.println("");
		Console.println("tes".lastIndexOf("",3));
		Console.println("tes".lastIndexOf("t",3));
		Console.println("tes".lastIndexOf("e",3));
		Console.println("tes".lastIndexOf("s",3));
		Console.println("tes".lastIndexOf("_",3));
		Console.println("test".lastIndexOf("es",3));
		Console.println("test".lastIndexOf("te",3));
		Console.println("test".lastIndexOf("st",3));
		Console.println("test".lastIndexOf("ta",3));
		Console.println("");
		try { Console.println("tes".lastIndexOf("",4)); } catch (Exception e) { e.printStackTrace(); }
		try { Console.println("tes".lastIndexOf("t",4)); } catch (Exception e) { e.printStackTrace(); }
		try { Console.println("tes".lastIndexOf("e",4)); } catch (Exception e) { e.printStackTrace(); }
		try { Console.println("tes".lastIndexOf("s",4)); } catch (Exception e) { e.printStackTrace(); }
		try { Console.println("tes".lastIndexOf("_",4)); } catch (Exception e) { e.printStackTrace(); }
		try { Console.println("test".lastIndexOf("es",4)); } catch (Exception e) { e.printStackTrace(); }
		try { Console.println("test".lastIndexOf("te",4)); } catch (Exception e) { e.printStackTrace(); }
		try { Console.println("test".lastIndexOf("st",4)); } catch (Exception e) { e.printStackTrace(); }
		try { Console.println("test".lastIndexOf("ta",4)); } catch (Exception e) { e.printStackTrace(); }
	}
}t breder.Testxsq ~        >sq ~ Uuq ~ X   t Breder Languaget 	 Languaget Languaget >throw breder.util.standard.IndexOutOfBoundsRuntimeException : t L	method (breder.lang.standard.String) substring (breder.lang.standard.Index)t @	method () main (breder.util.IList<breder.lang.standard.String>)q ~�pt  api.breder.lang.String.substringq ~ sq ~    w   
sq ~ tXpackage breder;
import breder.util.*;
import breder.math.*;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println("Breder Language".substring(1));
		Console.println("Breder Language".substring(7));
		Console.println("Breder Language".substring(8));
		Console.println("Breder Language".substring(99));
	}
}t breder.Testxsq ~        "sq ~ Uuq ~ X   t Bredert  t Lt  Lat Languaget >throw breder.util.standard.IndexOutOfBoundsRuntimeException : t g	method (breder.lang.standard.String) substring (breder.lang.standard.Index,breder.lang.standard.Index)t @	method () main (breder.util.IList<breder.lang.standard.String>)t >throw breder.util.standard.IndexOutOfBoundsRuntimeException : t g	method (breder.lang.standard.String) substring (breder.lang.standard.Index,breder.lang.standard.Index)t @	method () main (breder.util.IList<breder.lang.standard.String>)q ~�pt  api.breder.lang.String.substringq ~ sq ~    w   
sq ~ tipackage breder;
import breder.util.*;
import breder.math.*;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println("Breder Language".substring(1,6));
		Console.println("Breder Language".substring(7,7));
		Console.println("Breder Language".substring(8,8));
		Console.println("Breder Language".substring(7,9));
		Console.println("Breder Language".substring(8,15));
		try{ Console.println("Breder Language".substring(8, 7)); } catch (Exception e) { e.printStackTrace(); }
		try{ Console.println("Breder Language".substring(8,16)); } catch (Exception e) { e.printStackTrace(); }
	}
}t breder.Testxsq ~        4sq ~ Uuq ~ X   t 1t 2t 3t 4t 5.500000t 6t breder.Testsq ~        �t api.breder.math.Matrixq ~ sq ~ Uuq ~�   sq ~ tupackage breder;
import breder.util.*;
import breder.math.*;
public class Test {
	public static void main(notnull IList<String> args) {
		Matrix m = new Matrix("1,2,3:" + "4,5.5,6:");
		Console.println(m.get(1,1));
		Console.println(m.get(1,2));
		Console.println(m.get(1,3));
		Console.println(m.get(2,1));
		Console.println(m.get(2,2));
		Console.println(m.get(2,3));
	}
}q ~	Hsq ~        3sq ~ Uuq ~ X   t 1t 2t 3t 4t 5t 6t breder.Testsq ~        �q ~	Jq ~ sq ~ Uuq ~�   sq ~ tspackage breder;
import breder.util.*;
import breder.math.*;
public class Test {
	public static void main(notnull IList<String> args) {
		Matrix m = new Matrix("1,2,3:" + "4,5,6:");
		Console.println(m.get(1,1));
		Console.println(m.get(1,2));
		Console.println(m.get(1,3));
		Console.println(m.get(2,1));
		Console.println(m.get(2,2));
		Console.println(m.get(2,3));
	}
}q ~	Xsq ~        )sq ~ Uuq ~ X   	t 1t 2t 3t 4t 5t 6t truet truet trueq ~	Xsq ~        �q ~	Jq ~ sq ~ Uuq ~�   sq ~ t-package breder;
import breder.util.*;
import breder.math.*;
public class Test {
	public static void main(notnull IList<String> args) {
		Matrix m = new Matrix("1,2,3:4,5,6:");
		Console.println(m.get(1,1));
		Console.println(m.get(1,2));
		Console.println(m.get(1,3));
		Console.println(m.get(2,1));
		Console.println(m.get(2,2));
		Console.println(m.get(2,3));
		try {
			Console.println(m.get(0,0));
			Console.println(false);
		} catch ( IndexOutOfBoundsRuntimeException e ) {
			Console.println(true);
		}
		try {
			Console.println(m.get(null,null));
			Console.println(false);
		} catch ( NullPointerRuntimeException e2 ) {
			Console.println(true);
		}
		try {
			Console.println(m.get(3,3));
			Console.println(false);
		} catch ( IndexOutOfBoundsRuntimeException e1 ) {
			Console.println(true);
		}
	}
}q ~	Xsq ~        5sq ~    w   
t Vector2D[x=2, y=3]t 3.605551xpsq ~        �t api.breder.math.Vector2Dq ~ sq ~    w   
sq ~ t �package breder;
import breder.gui.*;
import breder.math.*;
public class Test {
	public static void main(notnull IList<String> args) {
		Vector2D v = new Vector2D(2, 3);
		Console.println(v);
		Console.println(v.length());
	}
}t breder.Testxsq ~        <sq ~    w   
t Vector3D[x=2, y=3, z=4]t 5.385165t ,Vector3D[x=0.371391, y=0.557086, z=0.742781]t Vector3D[x=5, y=7, z=9]t Vector3D[x=-1, y=-1, z=-1]t Vector3D[x=6, y=12, z=20]t Vector3D[x=4, y=6, z=8]t Vector3D[x=-1, y=2, z=-1]xpsq ~        �t api.breder.math.Vector3Dq ~ sq ~    w   
sq ~ t�package breder;
import breder.gui.*;
import breder.math.*;
public class Test {
	public static void main(notnull IList<String> args) {
		Vector3D v1 = new Vector3D(2, 3, 4);
		Vector3D v2 = new Vector3D(3, 4, 5);
		Console.println(v1);
		Console.println(v1.length());
		Console.println(v1.normalize());
		Console.println(v1 + v2);
		Console.println(v1 - v2);
		Console.println(v1 * v2);
		Console.println(v1 * 2);
		Console.println(v1.crossProduct(v2));
	}
}t breder.Testxsq ~        4sq ~ Uuq ~ X   t 5t 10t falset truet 3t 1t 2t 4t 5t 6t 3t falset truet truet truet falset 1t 2t 3t nullt 1t nullt truet breder.Testsq ~        �t api.breder.util.ArrayListq ~ sq ~    w   
sq ~ t�package breder;
import breder.util.*;
public class Test {
	public static void main(notnull IList<String> args) {
		IList<Number> list = new ArrayList<Number>();
		list.add(5).add(10);
		Console.println(list.get(1));
		Console.println(list.get(2));
		Console.println(list.isEmpty());
		list.clear();
		Console.println(list.isEmpty());
		list.add(1);
		list.add(2);
		list.add(3);
		Console.println(list.size());
		Console.println(list.get(1));
		Console.println(list.get(2));
		list.set(1, 4);
		list.set(2, 5);
		list.set(3, 6);
		Console.println(list.get(1));
		Console.println(list.get(2));
		Console.println(list.get(3));
		Console.println(list.size());
		Console.println(list.isEmpty());
		Console.println(list.contain(4));
		Console.println(list.contain(5));
		Console.println(list.contain(6));
		Console.println(list.contain(1));
		Console.println(list.indexOf(4));
		Console.println(list.indexOf(5));
		Console.println(list.indexOf(6));
		Console.println(list.indexOf(7));
		Console.println(list.indexOf(4,1));
		Console.println(list.indexOf(4,2));
		try {
			list.get(10);
			Console.println(false);
		} catch ( IndexOutOfBoundsRuntimeException e2 ) {
			Console.println(true);
		}
	}
}t breder.Testxsq ~       ��sq ~ Uuq ~ X   t  t breder.Testpt  api.breder.util.ArrayList.stressq ~ sq ~ Uuq ~�   sq ~ t�package breder;
import breder.util.*;
public class Test {
	public static void main(notnull IList<String> args) {
		for(Index ii = 1 ; ii < 1024 ; ii += 1) {
			for(Index uu = 1 ; uu < 1024 ; uu += 1) {
				IList<Number> list = new ArrayList<Number>();
				list.add(5);
				list.add(10);
				list.get(1);
				list.get(2);
				list.isEmpty();
				list.clear();
				list.isEmpty();
				list.add(1);
				list.add(2);
				list.add(3);
				list.size();
				list.get(1);
				list.get(2);
				list.set(1, 4);
				list.set(2, 5);
				list.set(3, 6);
				list.get(1);
				list.get(2);
				list.get(3);
				list.size();
				list.isEmpty();
				list.contain(4);
				list.contain(5);
				list.contain(6);
				list.contain(1);
				list.indexOf(4);
				list.indexOf(5);
				list.indexOf(6);
				list.indexOf(7);
				list.indexOf(4,1);
				list.indexOf(4,2);
				try {
					list.get(10);
					false;
				} catch ( IndexOutOfBoundsRuntimeException e2 ) {
					true;
				}
			}
		}
	}
}q ~	�sq ~       ZEsq ~ Uuq ~ X   t  q ~	�pt  api.breder.util.ArrayList.stressq ~ sq ~ Uuq ~�   sq ~ tXpackage breder;
import breder.util.*;
public class Test {
	public static void main(notnull IList<String> args) {
		for(Index ii = 1 ; ii < 1024 ; ii += 1) {
			for(Index uu = 1 ; uu < 1024 ; uu += 1) {
				IList<Number> list = new ArrayList<Number>();
				list.add(5);
				list.add(10);
				list.get(1);
				list.get(2);
				list.isEmpty();
				list.clear();
				list.isEmpty();
				list.add(1);
				list.add(2);
				list.add(3);
				list.size();
				list.get(1);
				list.get(2);
				list.set(1, 4);
				list.set(2, 5);
				list.set(3, 6);
				list.get(1);
				list.get(2);
				list.get(3);
				list.size();
				list.isEmpty();
				list.contain(4);
				list.contain(5);
				list.contain(6);
				list.contain(1);
				list.indexOf(4);
				list.indexOf(5);
				list.indexOf(6);
				list.indexOf(7);
				list.indexOf(4,1);
				list.indexOf(4,2);
			}
		}
	}
}q ~	�sq ~        "sq ~ Uuq ~ X   t 3t 2t 4t 6t nullt nullt 3t 1.5t 3.5t 5.5t nullt nullt 0q ~	�sq ~        �t api.breder.util.HashMapq ~ sq ~    w   
sq ~ t�package breder;
import breder.util.*;
public class Test {
	public static void main(notnull IList<String> args) {
		IMap<String,String> map = new HashMap<String,String>();
		map.set("1","2");
		map.set("3","4");
		map.set("5","6");
		Console.println(map.size());
		Console.println(map.get("1"));
		Console.println(map.get("3"));
		Console.println(map.get("5"));
		Console.println(map.get("7"));
		Console.println(map.get("9"));
		map.set("1","1.5");
		map.set("3","3.5");
		map.set("5","5.5");
		Console.println(map.size());
		Console.println(map.get("1"));
		Console.println(map.get("3"));
		Console.println(map.get("5"));
		Console.println(map.get("7"));
		Console.println(map.get("9"));
		map.remove("1").remove("3").remove("5");
		Console.println(map.size());
	}
}t breder.Testxsq ~       Щsq ~ Uuq ~ X   t  q ~	�pt api.breder.util.HashMap.stressq ~ sq ~ Uuq ~�   sq ~ t�package breder;
import breder.util.*;
public class Test {
	public static void main(notnull IList<String> args) {
		for (Index i = 1 ; i < 1024 ; i += 1) {
			for (Index u = 1 ; u < 1024 ; u += 1) {
				IMap<String,String> map = new HashMap<String,String>();
				map.set("1","2");
				map.set("3","4");
				map.set("5","6");
				map.size();
				map.get("1");
				map.get("3");
				map.get("5");
				map.get("7");
				map.get("9");
				map.set("1","1.5");
				map.set("3","3.5");
				map.set("5","5.5");
				map.size();
				map.get("1");
				map.get("3");
				map.get("5");
				map.get("7");
				map.get("9");
				map.remove("1");
				map.remove("3");
				map.remove("5");
				map.size();
			}
		}
	}
}q ~	�sq ~        2sq ~ Uuq ~ X   t BernardoBrederBCBt At truet At  t truet truet breder.Testq ~ �t api.breder.util.StringBuilderq ~ sq ~    w   
sq ~ t�package breder;
import breder.util.*;
public class Test {
	public static void main(notnull IList<String> args) {
		StringBuilder sb = new StringBuilder();
		sb.append("Bernardo");
		sb.append("Breder");
		sb.append("ABC", 2);
		sb.append("ABC", 2, 1);
		Console.println(sb.toString());
		sb.clear();
		sb.append("A", 1);
		Console.println(sb.toString());
		try {
			sb.append("A", 2);
			Console.println(false);
		} catch ( IndexOutOfBoundsRuntimeException e ) {
			Console.println(true);
		}
		sb.clear();
		sb.append("A", 1, 1);
		Console.println(sb.toString());
		sb.clear();
		sb.append("A", 1, 0);
		Console.println(sb.toString());
		try {
			sb.append("A", 1, 2);
			Console.println(false);
		} catch ( IndexOutOfBoundsRuntimeException e ) {
			Console.println(true);
		}
		try {
			sb.append("A", 2, 2);
			Console.println(false);
		} catch ( IndexOutOfBoundsRuntimeException e ) {
			Console.println(true);
		}
	}
}t breder.Testxsq ~        ?sq ~ Uuq ~ X   t BernardoBrederBCBq ~	�pt api.breder.util.StringBuilderq ~ sq ~    w   
sq ~ t"package breder;
import breder.util.*;
public class Test {
	public static void main(notnull IList<String> args) {
		StringBuilder sb = new StringBuilder();
		sb.append("Bernardo");
		sb.append("Breder");
		sb.append("ABC", 2);
		sb.append("ABC", 2, 1);
		Console.println(sb.toString());
	}
}t breder.Testxsq ~         *sq ~ Uuq ~ X   t 2q ~�pt arithmetic.subpsq ~    w   
sq ~ t rpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Natural a = 5-4;
	}
}t breder.Testxsq ~        1sq ~ Uuq ~ X   t  t breder.Testpt assignq ~ sq ~    w   
sq ~ t }package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i, Index u = 1, 2, 3;
	}
}t breder.Testxsq ~        2sq ~ Uuq ~ X   t 1t 2t breder.Testq ~ �t assignq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i, Index u = 1, 2, 3;
		Console.println(i);
		Console.println(u);
	}
}t breder.Testxsq ~        )sq ~ Uuq ~ X   t 10t breder.Testpt assignq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void test (notnull Natural length) {
		length = 10;
		Console.println(length);
	}
	public static void main(notnull IList<String> args) {
		Test.test(5);
	}
}t breder.Testxsq ~        /sq ~ Uuq ~ X   t 10t 20t 30t breder.Testpt assignq ~ sq ~    w   
sq ~ t8package breder;
public class Test {
	public static void test (notnull Natural length, notnull Index a, notnull Index b) {
		length = 10;
		a = 20;
		b = 30;
		Console.println(length);
		Console.println(a);
		Console.println(b);
	}
	public static void main(notnull IList<String> args) {
		Test.test(5,10,15);
	}
}t breder.Testxsq ~        3sq ~ Uuq ~ X   t 5t 10t 15q ~
#pt assignq ~ sq ~    w   
sq ~ tpackage breder;
public class Test {
	public static void test (notnull Natural length, notnull Index a, notnull Index b) {
		Console.println(length);
		Console.println(a);
		Console.println(b);
	}
	public static void main(notnull IList<String> args) {
		Test.test(5,10,15);
	}
}t breder.Testxsq ~        0sq ~ Uuq ~ X   t 10t 20t 30t 40q ~
#pt assignq ~ sq ~    w   
sq ~ thpackage breder;
public class Test {
	public static void test (notnull Natural length, notnull Index a, notnull Index b) {
		Index c = 20;
		length = 10;
		a = 20;
		b = 30;
		c = 40;
		Console.println(length);
		Console.println(a);
		Console.println(b);
		Console.println(c);
	}
	public static void main(notnull IList<String> args) {
		Test.test(5,10,15);
	}
}t breder.Testxsq ~        5sq ~ Uuq ~ X   t 40q ~
#pt assignq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void test (notnull Natural length, notnull Index a, notnull Index b) {
		Index c = 20;
		c = 40;
		Console.println(c);
	}
	public static void main(notnull IList<String> args) {
		Test.test(5,10,15);
	}
}t breder.Testxsq ~        .sq ~ Uuq ~ X   t 40t 50q ~
#pt assignq ~ sq ~    w   
sq ~ t.package breder;
public class Test {
	public static void test (notnull Natural length, notnull Index a, notnull Index b) {
		Index c = 20;
		Index d = 30;
		c = 40;
		d = 50;
		Console.println(c);
		Console.println(d);
	}
	public static void main(notnull IList<String> args) {
		Test.test(5,10,15);
	}
}t breder.Testxsq ~        3sq ~ Uuq ~ X   t truet 1t breder.Testq ~ �t assign.doubleq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Boolean flag, Index i = Test.get();
		Console.println(flag);
		Console.println(i);
	}
	public static notnull Boolean, notnull Index get() { return true, 1; }
}t breder.Testxsq ~        (sq ~ Uuq ~ X   t truet 1t testq ~
Xq ~ �t assign.doubleq ~ sq ~    w   
sq ~ t4package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Boolean flag, Index i, String u = Test.get();
		Console.println(flag);
		Console.println(i);
		Console.println(u);
	}
	public static notnull Boolean, notnull Index, notnull String get() { return true, 1, "test"; }
}t breder.Testxsq ~        2sq ~ Uuq ~ X   t truet 1t testq ~
Xq ~ �t assign.doubleq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Boolean flag, Index i, String u = true, 1, "test";
		Console.println(flag);
		Console.println(i);
		Console.println(u);
	}
}t breder.Testxsq ~        /sq ~ Uuq ~ X   t truet 1t testq ~
Xq ~ t assign.doubleq ~ sq ~    w   
sq ~ t�package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Boolean flag, Index i, String u = Test.get();
		Console.println(flag);
		Console.println(i);
		Console.println(u);
	}
	public static notnull Boolean, notnull Index, notnull String get() { return true, Test.get2(), "test"; }
	public static notnull Index, notnull String get2() { return 1, "test"; }
}t breder.Testxsq ~        ,sq ~ Uuq ~ X   t truet falset breder.Testq ~ �t assign.doubleq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Boolean i, Boolean u = true, false;
		Console.println(i);
		Console.println(u);
	}
}t breder.Testxsq ~        *sq ~ Uuq ~ X   t truet falseq ~
�q ~ �t assign.doubleq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Boolean flag, Boolean i = Test.get();
		Console.println(flag);
		Console.println(i);
	}
	public static Boolean, Boolean get() { return true, false; }
}t breder.Testxsq ~        6sq ~ Uuq ~ X   t truet falset breder.Testq ~ �t assign.doubleq ~ sq ~    w   
sq ~ tpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Boolean flag, Boolean i = Test.get(true, false);
		Console.println(flag);
		Console.println(i);
	}
	public static Boolean, Boolean get(Boolean a, Boolean b) { return true, false; }
}t breder.Testxsq ~        'sq ~ Uuq ~ X   t truet falseq ~
�q ~ �t assign.doubleq ~ sq ~    w   
sq ~ tpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Boolean flag, Boolean i = Test.get(true, false);
		Console.println(flag);
		Console.println(i);
	}
	public static Boolean, Boolean get(Boolean a, Boolean b) { return a, b; }
}t breder.Testxsq ~        6sq ~ Uuq ~ X   t truet falseq ~
�q ~ �t assign.doubleq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Boolean flag, Boolean i = Test.get(true);
		Console.println(flag);
		Console.println(i);
	}
	public static Boolean, Boolean get(Boolean a) { return a, false; }
}t breder.Testxsq ~        )sq ~ Uuq ~ X   t truet falseq ~
�q ~ �t assign.doubleq ~ sq ~    w   
sq ~ t package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Boolean flag, Boolean i = Test.get(true, true, true);
		Console.println(flag);
		Console.println(i);
	}
	public static Boolean, Boolean get(Boolean a, Boolean b, Boolean c) { return a, false; }
}t breder.Testxsq ~        )sq ~ Uuq ~ X   t truet falseq ~
�q ~ �t assign.doubleq ~ sq ~    w   
sq ~ tpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Boolean flag, Boolean i = Test.get(true, true, false);
		Console.println(flag);
		Console.println(i);
	}
	public static Boolean, Boolean get(Boolean a, Boolean b, Boolean c) { return a, c; }
}t breder.Testxsq ~        Lsq ~ Uuq ~ X   t truet falseq ~
�q ~ �t assign.doubleq ~ sq ~    w   
sq ~ t:package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Boolean flag, Boolean i = Test.get(true, true, false);
		Console.println(flag);
		Console.println(i);
	}
	public static Boolean, Boolean get(Boolean a, Boolean b, Boolean c) {
		Boolean flag = true;
		return flag, c;
	}
}t breder.Testxsq ~        6sq ~ Uuq ~ X   t truet falset falset breder.Testq ~ �t assign.doubleq ~ sq ~    w   
sq ~ t/package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Boolean flag, Boolean i, Boolean u = Test.get(true);
		Console.println(flag);
		Console.println(i);
		Console.println(u);
	}
	public static Boolean, Boolean, Boolean get(Boolean a) { return a, false, false; }
}t breder.Testxsq ~        9sq ~ Uuq ~ X   t 1q ~�q ~ �t assign.expressionq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i;
		Console.println(i = 1);
	}
}t breder.Testxsq ~        +sq ~ Uuq ~ X   t 1t 1q ~�q ~ �t assign.expressionq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i, Index u;
		Console.println(i = u = 1);
		Console.println(u);
	}
}t breder.Testxsq ~        6sq ~ Uuq ~ X   t 1t 2q ~�q ~ �t assign.expressionq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i, Index u;
		Console.println(i = u = 1);
		Console.println(u = 2);
	}
}t breder.Testxsq ~        7sq ~ Uuq ~ X   t 1t 2q ~�q ~ �t assign.expressionq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i, Index u;
		Console.println(i = 1);
		Console.println(u = 2);
	}
}t breder.Testxsq ~        1sq ~ Uuq ~ X   t 1t breder.Testq ~ �t assign.expressionq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i;
		Console.println(Test.test(i = 1));
	}
	public static Index test (Index i) {
		return i;
	}
}t breder.Testxsq ~        0sq ~ Uuq ~ X   t 1q ~q ~ �t assign.expressionq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i;
		Console.println(Test.test(i));
	}
	public static Index test (Index i) {
		return i = 1;
	}
}t breder.Testxsq ~        .sq ~ Uuq ~ X   t trueq ~q ~ �t assign.expressionq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Boolean flag;
		if (flag = true) {
			Console.println(true);
		}
	}
}t breder.Testxsq ~        3sq ~ Uuq ~ X   t 1t breder.Testq ~ �t assign.expressionq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(Test.test(5));
	}
	public static Index test (Index i) {
		return i = 1;
	}
}t breder.Testxsq ~        7sq ~ Uuq ~ X   t 1t breder.Testq ~ �t assign.expressionq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i;
		Console.println(i = 1);
	}
	public static Index test (Index i) {
		return i = 1;
	}
}t breder.Testxsq ~        Dsq ~ Uuq ~ X   t trueq ~
Xq ~ �t assign.singleq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Boolean flag = true;
		if (flag = true) {
			Console.println(true);
		}
	}
}t breder.Testxsq ~        Hsq ~ Uuq ~ X   t trueq ~
Xq ~ �t assign.singleq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Boolean flag = Test.get();
		if (flag = true) {
			Console.println(true);
		}
	}
	public static Boolean get() { return true; }
}t breder.Testxsq ~        $sq ~ Uuq ~ X   t trueq ~
Xq ~ �t assign.singleq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Boolean flag = Test.get();
		if (flag = true) {
			Console.println(true);
		}
	}
	public static notnull Boolean get() { return true; }
}t breder.Testxsq ~        4sq ~ Uuq ~ X   t trueq ~
Xq ~ �t assign.singleq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Boolean flag = Test.get();
		Console.println(flag);
	}
	public static notnull Boolean get() { return true; }
}t breder.Testxsq ~        Gsq ~ Uuq ~ X   t truet breder.Testq ~ �t breakq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for(;;) {
			break;
		}
		Console.println(true);
	}
}q ~\sq ~        ,sq ~ Uuq ~ X   t truet truet breder.Testq ~ �t breakq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for(;;) {
			Console.println(true);
			break;
		}
		Console.println(true);
	}
}q ~gsq ~        Hsq ~ Uuq ~ X   t truet trueq ~gq ~ �t breakq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for(;;) {
			Console.println(true);
			break;
			Console.println(true);
		}
		Console.println(true);
	}
}q ~gsq ~        8sq ~ Uuq ~ X   t truet truet trueq ~gq ~ �t breakq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(true);
		for(;;) {
			Console.println(true);
			break;
			Console.println(true);
		}
		Console.println(true);
	}
}q ~gsq ~        7sq ~ Uuq ~ X   t truet truet trueq ~gq ~ �t breakq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(true);
		for(Index i = 1;;) {
			Console.println(true);
			break;
			Console.println(true);
		}
		Console.println(true);
	}
}q ~gsq ~        %sq ~ Uuq ~ X   t truet truet trueq ~gq ~ �t breakq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(true);
		for(Index i = 1;i<10;) {
			Console.println(true);
			break;
			Console.println(true);
		}
		Console.println(true);
	}
}q ~gsq ~        Gsq ~ Uuq ~ X   t truet truet trueq ~gq ~ �t breakq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(true);
		for(Index i = 1;i<10;i+=1) {
			Console.println(true);
			break;
			Console.println(true);
		}
		Console.println(true);
	}
}q ~gsq ~        4sq ~ Uuq ~ X   t truet truet truet trueq ~gq ~ �t breakq ~ sq ~ Uuq ~�   sq ~ tpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(true);
		for(Index i = 1;i<10;i+=1) {
			Console.println(true);
			Console.println(true);
			break;
			Console.println(true);
		}
		Console.println(true);
	}
}q ~gsq ~        7sq ~ Uuq ~ X   t 1t 2t 3t 5t breder.Testq ~ �t breakq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		while(true) {
			Console.println(2);
			Console.println(3);
			break;
			Console.println(4);
		}
		Console.println(5);
	}
}q ~�sq ~        6sq ~ Uuq ~ X   t 1t 2t 3t 5q ~�q ~ �t breakq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		while(1==1) {
			Console.println(2);
			Console.println(3);
			break;
			Console.println(4);
		}
		Console.println(5);
	}
}q ~�sq ~        Qsq ~ Uuq ~ X   t 1t 2t 3t 5q ~�q ~ �t breakq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		repeat {
			Console.println(2);
			Console.println(3);
			break;
			Console.println(4);
		} while(1==1);
		Console.println(5);
	}
}q ~�sq ~        =sq ~ Uuq ~ X   t 1t 2t 3t 5q ~�q ~ �t breakq ~ sq ~ Uuq ~�   sq ~ tpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		repeat {
			Console.println(2);
			Console.println(3);
			if (1==1) { break; }
			Console.println(4);
		} while(1==1);
		Console.println(5);
	}
}q ~�sq ~         sq ~ Uuq ~ X   t G['breder.Test','4','17','break'] : break without generic loop statementt breder.Testpt breakq ~ sq ~    w   
sq ~ t hpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		break;
	}
}t breder.Testxsq ~         sq ~ Uuq ~ X   t G['breder.Test','4','31','break'] : break without generic loop statementq ~�pt breakq ~ sq ~    w   
sq ~ t xpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		if (1 == 1) { break; }
	}
}t breder.Testxsq ~         sq ~ Uuq ~ X   t 1t 2t 3t 6q ~gq ~ �t break.doubleq ~ sq ~ Uuq ~�   sq ~ tBpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		for(Index i=1;i<10;i+=1) {
			Console.println(2);
			for(Index u=1;u<10;u+=1) {
				Console.println(3);
				break;
				Console.println(4);
			}
			break;
			Console.println(5);
		}
		Console.println(6);
	}
}q ~gsq ~        -sq ~ Uuq ~ X   t 1t 2t 3t 6q ~gq ~ �t break.doubleq ~ sq ~ Uuq ~�   sq ~ t>package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		for(Index i=1;i<10;i+=1) {
			Console.println(2);
			for(Index u=1;u<10;) {
				Console.println(3);
				break;
				Console.println(4);
			}
			break;
			Console.println(5);
		}
		Console.println(6);
	}
}q ~gsq ~        3sq ~ Uuq ~ X   t 1t 2t 3t 6q ~gq ~ �t break.doubleq ~ sq ~ Uuq ~�   sq ~ t:package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		for(Index i=1;i<10;i+=1) {
			Console.println(2);
			for(Index u=1;;) {
				Console.println(3);
				break;
				Console.println(4);
			}
			break;
			Console.println(5);
		}
		Console.println(6);
	}
}q ~gsq ~        4sq ~ Uuq ~ X   t 1t 2t 3t 6q ~gq ~ �t break.doubleq ~ sq ~ Uuq ~�   sq ~ t1package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		for(Index i=1;i<10;i+=1) {
			Console.println(2);
			for(;;) {
				Console.println(3);
				break;
				Console.println(4);
			}
			break;
			Console.println(5);
		}
		Console.println(6);
	}
}q ~gsq ~        8sq ~ Uuq ~ X   t 1t 2t 3t 6q ~gq ~ �t break.doubleq ~ sq ~ Uuq ~�   sq ~ t-package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		for(Index i=1;i<10;) {
			Console.println(2);
			for(;;) {
				Console.println(3);
				break;
				Console.println(4);
			}
			break;
			Console.println(5);
		}
		Console.println(6);
	}
}q ~gsq ~        Bsq ~ Uuq ~ X   t 1t 2t 3t 6q ~gq ~ �t break.doubleq ~ sq ~ Uuq ~�   sq ~ t)package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		for(Index i=1;;) {
			Console.println(2);
			for(;;) {
				Console.println(3);
				break;
				Console.println(4);
			}
			break;
			Console.println(5);
		}
		Console.println(6);
	}
}q ~gsq ~        )sq ~ Uuq ~ X   t 1t 2t 3t 6q ~gq ~ �t break.doubleq ~ sq ~ Uuq ~�   sq ~ t package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		for(;;) {
			Console.println(2);
			for(;;) {
				Console.println(3);
				break;
				Console.println(4);
			}
			break;
			Console.println(5);
		}
		Console.println(6);
	}
}q ~gsq ~        /sq ~ Uuq ~ X   t 1t 2t 3t 6q ~�q ~ �t break.doubleq ~ sq ~ Uuq ~�   sq ~ t$package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		while(true) {
			Console.println(2);
			for(;;) {
				Console.println(3);
				break;
				Console.println(4);
			}
			break;
			Console.println(5);
		}
		Console.println(6);
	}
}q ~�sq ~        =sq ~ Uuq ~ X   t 1t 2t 3t 6q ~�q ~ �t break.doubleq ~ sq ~ Uuq ~�   sq ~ t$package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		while(1==1) {
			Console.println(2);
			for(;;) {
				Console.println(3);
				break;
				Console.println(4);
			}
			break;
			Console.println(5);
		}
		Console.println(6);
	}
}q ~�sq ~        :sq ~ Uuq ~ X   t 1t 2t 3t 6q ~�q ~ �t break.doubleq ~ sq ~ Uuq ~�   sq ~ t?package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		while(1==1) {
			Console.println(2);
			if (1==1) {
				for(;;) {
					Console.println(3);
					break;
					Console.println(4);
				}
				break;
				Console.println(5);
			}
		}
		Console.println(6);
	}
}q ~�sq ~        9sq ~ Uuq ~ X   t 1t 2t 3t 6q ~�q ~ �t break.doubleq ~ sq ~ Uuq ~�   sq ~ tHpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		repeat {
			Console.println(2);
			if (1==1) {
				for(;;) {
					Console.println(3);
					break;
					Console.println(4);
				}
				break;
				Console.println(5);
			}
		} while (1==1);
		Console.println(6);
	}
}q ~�sq ~          sq ~ Uuq ~ X   t  t breder.Testpt breturnpsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i;
		i = {
			breturn 1;
		}
	}
}q ~{sq ~          sq ~ Uuq ~ X   t  q ~{pt breturnpsq ~ Uuq ~�   sq ~ t package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = {
			breturn 1;
		}
	}
}q ~{sq ~          sq ~ Uuq ~ X   t  q ~{pt breturnpsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i, String str = {
			breturn 1, "test";
		}
	}
}q ~{sq ~          sq ~ Uuq ~ X   t  q ~{pt breturnpsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i, String str = {
			if (true) {
				breturn 1, "test";
			} else {
				breturn null, null;
			}
		}
	}
}q ~{sq ~        .sq ~ Uuq ~ X   t d['breder.Test','4','17','test'] : not found the variable or type 'test' in the imports and classpatht breder.Testpt 
class.nameq ~ sq ~    w   
sq ~ t ipackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		test t;
	}
}t breder.Testxsq ~        (sq ~ Uuq ~ X   t  t breder.Testpt continueq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for(Number i = 0 ; i < 2 ; i += 1) {
			if (i == 0) { continue; }
		}
	}
}q ~�sq ~        (sq ~ Uuq ~ X   t 1t 1t 2q ~�sq ~        �t continueq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for(Number i = 0 ; i < 2 ; i += 1) {
			Console.println(1);
			if (i == 0) { continue; }
			Console.println(2);
		}
	}
}q ~�sq ~        )sq ~ Uuq ~ X   t 1t 1q ~�sq ~        �t continueq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for(Number i = 0 ; i < 2 ;i+=1) {
			Console.println(1);
			continue;
			Console.println(2);
		}
	}
}q ~�sq ~        *sq ~ Uuq ~ X   t 1t 2t 1t 2t 1q ~�sq ~        �t continueq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for(Number i = 0 ; i <= 2 ; i += 1) {
			Console.println(1);
			if (i == 2) { break; }
			Console.println(2);
			continue;
			Console.println(3);
		}
	}
}q ~�sq ~        5sq ~ Uuq ~ X   t 1t 2t 1t 2t 1q ~�sq ~        �t continueq ~ sq ~ Uuq ~�   sq ~ tpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Number u = 0;
		for(Number i = 0 ; i <= 2 ; u += 1) {
			Console.println(1);
			if (u == 2) { break; }
			Console.println(2);
			continue;
			Console.println(3);
		}
	}
}q ~�sq ~        3sq ~ Uuq ~ X   t 1t 2t 1t 2t 1q ~�sq ~        �t continueq ~ sq ~ Uuq ~�   sq ~ t	package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Number u = 0;
		for(Number i = 0 ; i <= 2 ;) {
			Console.println(1);
			if (u == 2) { break; }
			u+=1;
			Console.println(2);
			continue;
			Console.println(3);
		}
	}
}q ~�sq ~        *sq ~ Uuq ~ X   t 1t 2t 1t 2t 1q ~�sq ~        �t continueq ~ sq ~ Uuq ~�   sq ~ tpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Number u = 0;
		for(Number i = 0 ;;) {
			Console.println(1);
			if (u == 2) { break; }
			u+=1;
			Console.println(2);
			continue;
			Console.println(3);
		}
	}
}q ~�sq ~        ;sq ~ Uuq ~ X   t 1t 2t 1t 2t 1q ~�sq ~        �t continueq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Number u = 0;
		for(;;) {
			Console.println(1);
			if (u == 2) { break; }
			u+=1;
			Console.println(2);
			continue;
			Console.println(3);
		}
	}
}q ~�sq ~        .sq ~ Uuq ~ X   t 1t 2t 1t 2t 1q ~�sq ~        �t continueq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Number u = 0;
		while(true) {
			Console.println(1);
			if (u == 2) { break; }
			u+=1;
			Console.println(2);
			continue;
			Console.println(3);
		}
	}
}q ~�sq ~        +sq ~ Uuq ~ X   t 1t 2t 1t 2t 1q ~�sq ~        �t continueq ~ sq ~ Uuq ~�   sq ~ t package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Number u = 0;
		repeat {
			Console.println(1);
			if (u == 2) { break; }
			u+=1;
			Console.println(2);
			continue;
			Console.println(3);
		} while(true);
	}
}q ~�sq ~         sq ~ Uuq ~ X   t M['breder.Test','4','31','continue'] : continue without generic loop statementq ~�pt continueq ~ sq ~    w   
sq ~ t {package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		if (0 == 0) { continue; }
	}
}t breder.Testxsq ~         sq ~ Uuq ~ X   t M['breder.Test','4','17','continue'] : continue without generic loop statementq ~�pt continueq ~ sq ~    w   
sq ~ t kpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		continue;
	}
}t breder.Testxsq ~        :sq ~ Uuq ~ X   t 1t 2t 1t 2t 1q ~�sq ~        �t continue.doubleq ~ sq ~ Uuq ~�   sq ~ tpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Number u = 0;
		while(true) {
			while(true) {
				Console.println(1);
				if (u == 2) { break; }
				u+=1;
				Console.println(2);
				continue;
				Console.println(3);
			}
			break;
		}
	}
}q ~�sq ~        *sq ~ Uuq ~ X   t 1t 2q ~�q ~ �t declare.doubleq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i, Index u = 1 , 2;
		Console.println(i);
		Console.println(u);
	}
}t breder.Testxsq ~        sq ~ Uuq ~ X   t 1t 2q ~
q ~ �t declare.doublesq ~  sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i, Index u, Index z = 1 , 2;
		Console.println(i);
		Console.println(u);
	}
}t breder.Testxsq ~        5sq ~ Uuq ~ X   t 1t 2t nullq ~�q ~ �t declare.doubleq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i, Index u, Index z = 1, 2, null;
		Console.println(i);
		Console.println(u);
		Console.println(z);
	}
}t breder.Testxsq ~        Csq ~ Uuq ~ X   t 1t 2t nullq ~
q ~ �t declare.doubleq ~Ysq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i, Index u, Index z = 1 , 2;
		Console.println(i);
		Console.println(u);
		Console.println(z);
	}
}t breder.Testxsq ~          sq ~ Uuq ~ X   t  t breder.Testpt dynamic.method.declarepsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Method<><> method = Test.test<><>;
	}
	public static void test () {}
}q ~xsq ~          sq ~ Uuq ~ X   t  q ~xpq ~ypsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Method<><Index> method = Test.test<><Index>;
	}
	public static void test (Index i) {}
}q ~xsq ~          sq ~ Uuq ~ X   t  q ~xpq ~ypsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Method<><notnull Index> method = Test.test<><Index>;
	}
	public static void test (notnull Index i) {}
}q ~xsq ~          sq ~ Uuq ~ X   t  q ~xpq ~ypsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Method<Index><notnull Index> method = Test.test<Index><Index>;
	}
	public static Index test (notnull Index i) {}
}q ~xsq ~          sq ~ Uuq ~ X   t  q ~xpq ~ypsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Method<notnull Index><notnull Index> method = Test.test<Index><Index>;
	}
	public static notnull Index test (notnull Index i) {}
}q ~xsq ~          sq ~ Uuq ~ X   t  q ~xpq ~ypsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Method<notnull Index><notnull Index, String> method = Test.test<Index><Index, String>;
	}
	public static notnull Index test (notnull Index i, String str) {}
}q ~xsq ~          sq ~ Uuq ~ X   t  q ~xpt dynamic.method.replacepsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test.test = Test.test<Index><Index, String>;
	}
	public static notnull Index test (notnull Index i, String str) {}
}q ~xsq ~          sq ~ Uuq ~ X   t  q ~xpt dynamic.method.replacepsq ~ Uuq ~�   sq ~ tFpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test.test = Test.test2<Index><Index, String>;
	}
	public static notnull Index test (notnull Index i, String str) {
		Console.println(1);
	}
	public static notnull Index test2 (notnull Index i, String str) {
		Console.println(2);
	}
}q ~xsq ~          sq ~ Uuq ~ X   t 1q ~xpt dynamic.method.replacepsq ~ Uuq ~�   sq ~ tUpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test.test();
		Test.test = Test.test2<Index><Index, String>;
	}
	public static notnull Index test (notnull Index i, String str) {
		Console.println(1);
	}
	public static notnull Index test2 (notnull Index i, String str) {
		Console.println(2);
	}
}q ~xsq ~          sq ~ Uuq ~ X   t 1t 2q ~xpt dynamic.method.replacepsq ~ Uuq ~�   sq ~ tdpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test.test();
		Test.test = Test.test2<Index><Index, String>;
		Test.test();
	}
	public static notnull Index test (notnull Index i, String str) {
		Console.println(1);
	}
	public static notnull Index test2 (notnull Index i, String str) {
		Console.println(2);
	}
}q ~xsq ~          sq ~ Uuq ~ X   t  t breder.Testpt dynamic.structpsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		<Index i, String u> struct = Test.test();
	}
	public static Index, String test () {
		return 1, "test";
	}
}q ~�sq ~          sq ~ Uuq ~ X   t  q ~�pt dynamic.structpsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		<notnull Index i, String u> struct = Test.test();
	}
	public static notnull Index, String test () {
		return 1, "test";
	}
}q ~�sq ~          sq ~ Uuq ~ X   t  q ~�pt dynamic.structpsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		<notnull Index i, notnull String u> struct = Test.test();
	}
	public static notnull Index, notnull String test () {
		return 1, "test";
	}
}q ~�sq ~          sq ~ Uuq ~ X   t  q ~�pt dynamic.structpsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		<Index i, String u> struct = Test.test();
	}
	public static Index, String test () {
		return 1, "test";
	}
}q ~�sq ~          sq ~ Uuq ~ X   t  q ~�pt dynamic.structpsq ~ Uuq ~�   sq ~ tpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		<Index i, String u> struct = Test.test();
		Console.println(struct.i);
		Console.println(struct.u);
	}
	public static Index, String test () {
		return 1, "test";
	}
}q ~�sq ~         sq ~ Uuq ~ X   t >['breder.Test2','2','28','Test'] : a class with circle extendst breder.Testpt extend.cicleq ~ sq ~    w   
sq ~ t 3package breder;
public class Test extends Test1 {
}t breder.Testsq ~ t 4package breder;
public class Test1 extends Test2 {
}t breder.Test1sq ~ t 3package breder;
public class Test2 extends Test {
}t breder.Test2xsq ~         sq ~ Uuq ~ X   t ?['breder.Test2','2','32','Test1'] : a class with circle extendsq ~�pt extend.cicleq ~ sq ~    w   
sq ~ t 6package breder;
public class Test implements Test1 {
}t breder.Testsq ~ t 8package breder;
public interface Test1 extends Test2 {
}t breder.Test1sq ~ t 8package breder;
public interface Test2 extends Test1 {
}t breder.Test2xsq ~        0sq ~    w   
t 3xpq ~ �t fieldq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	private Number i;
	public Test () {
		this.i = 3;
	}
	public Number getI() { return this.i; }
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		Console.println(t.getI());
	}
}t breder.Testxsq ~        <sq ~    w   
t 1t 2t 3t 4xpq ~ t fieldq ~ sq ~    w   
sq ~ t�package breder;
public class Test {
	private Number i;
	private Number u;
	public Test (Number i, Number u) {
		this.i = i;
		this.u = u;
	}
	public Number getI() { return this.i; }
	public Number getU() { return this.u; }
	public static void main(notnull IList<String> args) {
		Test t1 = new Test(1,2);
		Test t2 = new Test(3,4);
		Console.println(t1.getI());
		Console.println(t1.getU());
		Console.println(t2.getI());
		Console.println(t2.getU());
	}
}t breder.Testxsq ~        0sq ~    w   
t 3t 2xpsq ~        kt fieldq ~ sq ~    w   
sq ~ tHpackage breder;
public class Test {
	private Number i;
	public Test () {
		this.i = 3;
	}
	public Number getI() { return this.i; }
	public void setI(Number i) { this.i = i; }
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		Console.println(t.getI());
		t.setI(2);
		Console.println(t.getI());
	}
}t breder.Testxsq ~        :sq ~    w   
t 3t 2xppt fieldq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test1 t = new Test1();
		Console.println(t.getI());
		t.setI(2);
		Console.println(t.getI());
	}
}t breder.Testsq ~ t �package breder;
public class Test1 {
	private Number i;
	public Test1 () {
		this.i = 3;
	}
	public Number getI() { return this.i; }
	public void setI(Number i) { this.i = i; }
}t breder.Test1xsq ~        #sq ~    w   
t 3t 2xppt fieldq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public static void main(notnull IList<String> args) {
		Test1 t = new Test();
		Console.println(t.getI());
		t.setI(2);
		Console.println(t.getI());
	}
}t breder.Testsq ~ t �package breder;
public class Test1 {
	public Test1 () {
		this.i = 3;
	}
	private Number i;
	public Number getI() { return this.i; }
	public void setI(Number i) { this.i = i; }
}t breder.Test1xsq ~        (sq ~    w   
t 2t 3xppt fieldq ~ sq ~    w   
sq ~ t@package breder;
public class Test extends Test1 {
	private Number u;
	public Number getU() { return this.u; }
	public void setU(Number u) { this.u = u; }
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t.setI(2);
		Console.println(t.getI());
		t.setU(3);
		Console.println(t.getU());
	}
}t breder.Testsq ~ t �package breder;
public class Test1 {
	public Test1 () {
		this.i = 3;
	}
	private Number i;
	public Number getI() { return this.i; }
	public void setI(Number i) { this.i = i; }
}t breder.Test1xsq ~        9sq ~    w   
t 2t 3t 4xppt fieldq ~ sq ~    w   
sq ~ trpackage breder;
public class Test extends Test1 , Test2 {
	private Number u;
	public Number getU() { return this.u; }
	public void setU(Number u) { this.u = u; }
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t.setI(2);
		Console.println(t.getI());
		t.setU(3);
		Console.println(t.getU());
		t.setX(4);
		Console.println(t.getX());
	}
}t breder.Testsq ~ t �package breder;
public class Test1 {
	public Test1 () {
		this.i = 3;
	}
	private Number i;
	public Number getI() { return this.i; }
	public void setI(Number i) { this.i = i; }
}t breder.Test1sq ~ t �package breder;
public class Test2 {
	public Test2 () {
		this.x = 3;
	}
	private Number x;
	public Number getX() { return this.x; }
	public void setX(Number x) { this.x = x; }
}t breder.Test2xsq ~        "sq ~    w   
t 5xppt fieldq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test2 {
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t.setX(1);
		t.setY(5);
		Console.println(t.getY());
	}
}t breder.Testsq ~ t 4package breder;
public class Test2 extends Test3 {
}t breder.Test2sq ~ t �package breder;
public class Test3 {
	private Number y;
	public void setX(Number x) {}
	public void setY(Number y) { this.y = y; }
	public Number getY() { return this.y; }
}t breder.Test3xsq ~        ?sq ~    w   
t 5xppt fieldq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test2 {
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t.setX(1);
		t.setY(5);
		Console.println(t.getY());
	}
}t breder.Testsq ~ t 4package breder;
public class Test2 extends Test3 {
}t breder.Test2sq ~ t �package breder;
public class Test3 {
	private Number y;
	public void setX(Number x) {}
	public void setY(Number y) { this.y = y; }
	public Number getY() { return this.y; }
}t breder.Test3xsq ~        ,sq ~    w   
t 5t 6xppt fieldq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test2 {
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t.setX(1);
		t.setY(5);
		t.setZ(6);
		Console.println(t.getY());
		Console.println(t.getZ());
	}
}t breder.Testsq ~ t �package breder;
public class Test2 extends Test3 {
	private Number z;
	public void setZ(Number z) { this.z = z; }
	public Number getZ() { return this.z; }
}t breder.Test2sq ~ t �package breder;
public class Test3 {
	private Number y;
	public void setX(Number x) {}
	public void setY(Number y) { this.y = y; }
	public Number getY() { return this.y; }
}t breder.Test3xsq ~        1sq ~ Uuq ~ X   t falset breder.Testpt 
field.initq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	private Number id;
	public Test() {
		Console.println(this.id == 1);
	}
	public static void main(notnull IList<String> args) {
		new Test();
	}
}t breder.Testxsq ~        2sq ~ Uuq ~ X   t falseq ~�pt 
field.initq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	private Number id;
	public Test() {
		Console.println(1 == this.id);
	}
	public static void main(notnull IList<String> args) {
		new Test();
	}
}t breder.Testxsq ~        )sq ~ Uuq ~ X   t falseq ~�pt 
field.initq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	private Number id;
	public Test() {
		if (this.id == 0) {}
		Console.println(false);
	}
	public static void main(notnull IList<String> args) {
		new Test();
	}
}t breder.Testxsq ~        7sq ~ Uuq ~ X   t nullq ~�pt 
field.initq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	private Number id;
	public Test() {
		Console.println(this.id);
	}
	public static void main(notnull IList<String> args) {
		new Test();
	}
}t breder.Testxsq ~        5sq ~ Uuq ~ X   t falseq ~�pt 
field.initq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	private Number id;
	public Test() {
		while (this.id == 0) {}
		Console.println(false);
	}
	public static void main(notnull IList<String> args) {
		new Test();
	}
}t breder.Testxsq ~        #sq ~ Uuq ~ X   t 9throw breder.lang.standard.NullPointerRuntimeException : t 	method () Test ()t @	method () main (breder.util.IList<breder.lang.standard.String>)q ~�pt 
field.initq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	private Number id;
	public Test() {
		Console.println(this.id <= 0);
	}
	public static void main(notnull IList<String> args) {
		new Test();
	}
}t breder.Testxsq ~        2sq ~     w   
xppt field.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static Number i;
	public static void main(notnull IList<String> args) {
		Test.i = 1;
	}
}t breder.Testxsq ~        %sq ~    w   
t 1xppt field.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static Number i;
	public static void main(notnull IList<String> args) {
		Test.i = 1;
		Console.println(Test.i);
	}
}t breder.Testxsq ~        $sq ~    w   
t nullxppt field.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static Number i;
	public static void main(notnull IList<String> args) {
		Test.i = null;
		Console.println(Test.i);
	}
}t breder.Testxsq ~         sq ~    w   
t nullxppt field.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static Number i;
	public static void main(notnull IList<String> args) {
		Console.println(Test.i);
	}
}t breder.Testxsq ~          sq ~ Uuq ~ X   t 1t 2t 3q ~�pt finally.blockpsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		{
			Console.println(2);
		} finally {
			Console.println(3);
		}
	}
}q ~�sq ~          sq ~ Uuq ~ X   t 1t 2t 3q ~�pt finally.block.doublepsq ~ Uuq ~�   sq ~ tpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		{
			Console.println(2);
		} finally {
			{
				Console.println(3);
			} finally {
				Console.println(4);
			}
			Console.println(5);
		}
	}
}q ~�sq ~          sq ~ Uuq ~ X   t 1t 2t 4q ~�pt finally.forpsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		for(;;) {
			Console.println(2);
			break;
			Console.println(3);
		} finally {
			Console.println(4);
		}
	}
}q ~�sq ~          sq ~ Uuq ~ X   t 1t 2t 3q ~�pt 
finally.ifpsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		if (true) {
			Console.println(2);
		} finally {
			Console.println(3);
		}
	}
}q ~�sq ~          sq ~ Uuq ~ X   t 1t 2t 3t 4q ~�pt 
finally.ifpsq ~ Uuq ~�   sq ~ t package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		try {
			Console.println(1);
			if (true) {
				throw new Exception();
			} finally {
				Console.println(2);
			}
		} catch (Exception e ) {
			Console.println(3);
		}
		Console.println(4);
	}
}q ~�sq ~          sq ~ Uuq ~ X   t 1t 2t 3t 4t 5q ~�pt 
finally.ifpsq ~ Uuq ~�   sq ~ tEpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		try {
			Console.println(1);
			if (true) {
				throw new Exception();
			} finally {
				Console.println(2);
			}
		} catch (Exception e ) {
			Console.println(3);
		} finally {
			Console.println(4);
		}
		Console.println(5);
	}
}q ~�sq ~          sq ~ Uuq ~ X   t 1t 2t 3t 4q ~�pt finally.methodpsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		Test.test();
		Console.println(4);
	}
	public static void test () {
		Console.println(2);
	} finally {
		Console.println(3);
	}
}q ~�sq ~          sq ~ Uuq ~ X   t 1t 2t 3t 4t 5t 6t 7q ~�pt finally.methodpsq ~ Uuq ~�   sq ~ tupackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		Test.test();
		Console.println(7);
	}
	public static void test () {
		Console.println(2);
		Test.test2();
		Console.println(5);
	} finally {
		Console.println(6);
	}
	public static void test2 () {
		Console.println(3);
	} finally {
		Console.println(4);
	}
}q ~�sq ~          sq ~ Uuq ~ X   t 1t 2t 4q ~�pt finally.repeatpsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		repeat {
			Console.println(2);
			break;
			Console.println(3);
		} while(true) finally {
			Console.println(4);
		}
	}
}q ~�sq ~          sq ~ Uuq ~ X   t 1t 2t 4q ~�pt finally.whilepsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		while(true) {
			Console.println(2);
			break;
			Console.println(3);
		} finally {
			Console.println(4);
		}
	}
}q ~�sq ~        6sq ~    w   
t 0xppt forq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for(Number i = 0 ; i < 1 ; i += 1) {
			Console.println(i);
		}
	}
}t breder.Testxsq ~        0sq ~    w   
t 0t 1xppt forq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for(Number i = 0 ; i <= 1 ; i += 1) {
			Console.println(i);
		}
	}
}t breder.Testxsq ~        7sq ~    w   
t 0t 0.500000t 1xppt forq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for(Number i = 0 ; i <= 1 ; i += 0.5) {
			Console.println(i);
		}
	}
}t breder.Testxsq ~        (sq ~    w   
t l=1t c=1t c=2t l=2t c=1t c=2xppt forq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for(Index l = 1 ; l <= 2 ; l += 1) {
			Console.println("l="+l);
			for(Index c = 1 ; c <= 2 ; c += 1) {
				Console.println("c="+c);
			}
		}
	}
}t breder.Testxsq ~        8sq ~ Uuq ~ X   
t 0t 1t 2t 3t 4t 5t 6t 7t 8t 9t breder.Testpt forq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for(Number i = 0 ; i < 10 ; i += 1) {
			Console.println(i);
		}
	}
}q ~�sq ~        -sq ~ Uuq ~ X   t 0t 10t 20t 30t 40t 50t 60t 70t 80t 90t 100q ~�pt forq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for(Number i = 0 ; i <= 100 ; i += 10) {
			Console.println(i);
		}
	}
}q ~�sq ~        .sq ~ Uuq ~ X   t 0t 1t breder.Testpt forq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Number i = 0;
		for(; i <= 1 ; i += 1) {
			Console.println(i);
		}
	}
}q ~�sq ~        Isq ~ Uuq ~ X   t trueq ~�pt forq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Number i = 0;
		for(;false; i += 1) {
			Console.println(i);
		}
		Console.println(true);
	}
}q ~�sq ~        /sq ~ Uuq ~ X   t trueq ~�pt forq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Number i = 0;
		for(;false;) {
			Console.println(i);
		}
		Console.println(true);
	}
}q ~�sq ~        ,sq ~ Uuq ~ X   t trueq ~�pt forq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test.test();
		Console.println(true);
	}
	public static Boolean test() {
		for(;;) {
			return true;
		}
	}
}q ~�sq ~        +sq ~ Uuq ~ X   t  t breder.Testpt forq ~ sq ~ Uuq ~�   sq ~ t zpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for(;;) {
			return;
		}
	}
}q ~�sq ~        &sq ~ Uuq ~ X   t truet breder.Testpt forq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test.test();
		Console.println(true);
	}
	public static void test () {
		for(;;) {
			return;
		}
	}
}q ~�sq ~        *sq ~ Uuq ~ X   t  t breder.Testpt forq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for (Index i = 1 ; i < 10 ; i += 1) {}
	}
}t breder.Testxsq ~        2sq ~ Uuq ~ X   t  t breder.Testpt forq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for(Number i = 0 ; i < 1 ; i += 1) {
		}
	}
}t breder.Testxsq ~          sq ~ Uuq ~ X   t 1 - 1t 2 - 2t 3 - 3t 4 - 4q ~{pt for.each.duppsq ~ Uuq ~�   sq ~ tpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		IList<Index> list = new ArrayList<Index>();
		list.add(1); list.add(2); list.add(3); list.add(4);
		for (Index i , Index u > list , list) {
			Consoleprint(i + " - " + u);
		}
	}
}q ~{sq ~          sq ~ Uuq ~ X   t 4 - 4t 3 - 3t 2 - 2t 1 - 1q ~{pt for.each.duppsq ~ Uuq ~�   sq ~ tpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		IList<Index> list = new ArrayList<Index>();
		list.add(1); list.add(2); list.add(3); list.add(4);
		for (Index i , Index u < list , list) {
			Consoleprint(i + " - " + u);
		}
	}
}q ~{sq ~          sq ~ Uuq ~ X   t  q ~{pt for.each.duppsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		IList<Index> list = new ArrayList<Index>();
		for (Index i , Index u > list , list) {
			Consoleprint(i + " - " + u);
		}
	}
}q ~{sq ~          sq ~ Uuq ~ X   t  q ~{pt for.each.duppsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		IList<Index> list = new ArrayList<Index>();
		for (Index i , Index u < list , list) {
			Consoleprint(i + " - " + u);
		}
	}
}q ~{sq ~          sq ~ Uuq ~ X   t  t breder.Testpt for.each.dup.indexpsq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		IList<Index> list = new ArrayList<Index>();
		for (Index i , Index item1 ; Index u , Index item2 < list , list) {
			Consoleprint(i + " - " + u);
		}
	}
}t breder.Testxsq ~          sq ~ Uuq ~ X   t  q ~,pt for.each.dup.indexpsq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		IList<Index> list = new ArrayList<Index>();
		for (Index item1 ; Index u , Index item2 < list , list) {
			Consoleprint(item1 + " - " + u);
		}
	}
}t breder.Testxsq ~          sq ~ Uuq ~ X   t for each with different lengthq ~{pt for.each.errpsq ~ Uuq ~�   sq ~ tipackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		IList<Index> list1 = new ArrayList<Index>();
		list1.add(1); list1.add(2); list1.add(3); list1.add(4);
		IList<Index> list2 = new ArrayList<Index>();
		list2.add(1); list2.add(2); list2.add(3);
		for (Index i , Index u < list1 , list2) {
			Consoleprintln(i);
		}
	}
}q ~{sq ~          sq ~ Uuq ~ X   t for each with different lengthq ~{pt for.each.errpsq ~ Uuq ~�   sq ~ tipackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		IList<Index> list1 = new ArrayList<Index>();
		list1.add(1); list1.add(2); list1.add(3); list1.add(4);
		IList<Index> list2 = new ArrayList<Index>();
		list2.add(1); list2.add(2); list2.add(3);
		for (Index i , Index u > list1 , list2) {
			Consoleprintln(i);
		}
	}
}q ~{sq ~          sq ~ Uuq ~ X   t 1t 2t 3t 4q ~{pt for.each.singlepsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		IList<Index> list = new ArrayList<Index>();
		list.add(1); list.add(2); list.add(3); list.add(4);
		for (Index i > list) {
			Consoleprintln(i);
		}
	}
}q ~{sq ~          sq ~ Uuq ~ X   t 4t 3t 2t 1q ~{pt for.each.singlepsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		IList<Index> list = new ArrayList<Index>();
		list.add(1); list.add(2); list.add(3); list.add(4);
		for (Index i < list) {
			Consoleprintln(i);
		}
	}
}q ~{sq ~          sq ~ Uuq ~ X   t  q ~{pt for.each.singlepsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		IList<Index> list = new ArrayList<Index>();
		for (Index i > list) {
			Consoleprintln(i);
		}
	}
}q ~{sq ~          sq ~ Uuq ~ X   t  q ~{pt for.each.singlepsq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		IList<Index> list = new ArrayList<Index>();
		for (Index i < list) {
			Consoleprintln(i);
		}
	}
}q ~{sq ~          sq ~ Uuq ~ X   t 1t at 2t bt 3t ct 4t dt breder.Testpt for.each.singlepsq ~    w   
sq ~ t!package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		IList<String> list = new ArrayList<String>();
		list.add("a"); list.add("b"); list.add("c"); list.add("d");
		for (Index n , String i > list) {
			Consoleprintln(n);
			Consoleprintln(i);
		}
	}
}t breder.Testxsq ~          sq ~ Uuq ~ X   t 4t dt 3t ct 2t bt 1t aq ~�pt for.each.singlepsq ~    w   
sq ~ t!package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		IList<String> list = new ArrayList<String>();
		list.add("a"); list.add("b"); list.add("c"); list.add("d");
		for (Index n , String i < list) {
			Consoleprintln(n);
			Consoleprintln(i);
		}
	}
}t breder.Testxsq ~          sq ~ Uuq ~ X   t 1t 2t 3t 4t breder.Testpt for.each.single.continuepsq ~    w   
sq ~ t.package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		IList<String> list = new ArrayList<String>();
		list.add("a"); list.add("b"); list.add("c"); list.add("d");
		for (Index n , String i > list) {
			Consoleprintln(n);
			continue;
			Consoleprintln(i);
		}
	}
}t breder.Testxsq ~          sq ~ Uuq ~ X   t 4t 3t 2t 1q ~�pt for.each.single.continuepsq ~    w   
sq ~ t.package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		IList<String> list = new ArrayList<String>();
		list.add("a"); list.add("b"); list.add("c"); list.add("d");
		for (Index n , String i < list) {
			Consoleprintln(n);
			continue;
			Consoleprintln(i);
		}
	}
}t breder.Testxsq ~       Csq ~ Uuq ~ X   t  t breder.Testpt for.infq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for(Index i = 1 ; i <= 1024 ; i += 1) {
			for(Index u = 1 ; u <= 1024 ; u += 1) {
			}
		}
	}
}q ~�sq ~       �sq ~ Uuq ~ X   t  q ~�pt for.infq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for(Index i = 1 ; i <= 1024 ; i += 1) {
			for(Index u = 1 ; u <= 1024 ; u += 1) {
				Test.test();
			}
		}
	}
	public static void test() {}
}q ~�sq ~       �sq ~ Uuq ~ X   t  q ~�pt for.infq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for(Index i = 1 ; i <= 1024 ; i += 1) {
			for(Index u = 1 ; u <= 1024 ; u += 1) {
				for(Index p = 1 ; p <= 1 ; p += 1) {}
			}
		}
	}
}t breder.Testxsq ~        @sq ~     w   
xppt genericq ~ sq ~    w   
sq ~ t bpackage breder;
public class Test<E> {
	public static void main(notnull IList<String> args) {
	}
}t breder.Testxsq ~        ;sq ~     w   
xppt genericq ~ sq ~    w   
sq ~ t ppackage breder;
public class Test<E> {
	private E e;
	public static void main(notnull IList<String> args) {
	}
}t breder.Testxsq ~        )sq ~     w   
xppt genericq ~ sq ~    w   
sq ~ t �package breder;
public class Test<E> {
	public E e () {return null;}
	public static void main(notnull IList<String> args) {
	}
}t breder.Testxsq ~        Asq ~     w   
xppt genericq ~ sq ~    w   
sq ~ t zpackage breder;
public class Test<E> {
	public void e (E e) {}
	public static void main(notnull IList<String> args) {
	}
}t breder.Testxsq ~         sq ~     w   
xppt genericq ~ sq ~    w   
sq ~ t �package breder;
public abstract class Test<E> {
	public abstract E e () ;
	public static void main(notnull IList<String> args) {
	}
}t breder.Testxsq ~        6sq ~     w   
xppt genericq ~ sq ~    w   
sq ~ t �package breder;
public abstract class Test<E> {
	public abstract E e (E e) ;
	public static void main(notnull IList<String> args) {
	}
}t breder.Testxsq ~        Isq ~     w   
xppt genericq ~ sq ~    w   
sq ~ t �package breder;
public class Test<E> {
	public E e (E e) {return e;}
	public static void main(notnull IList<String> args) {
		Test<Number> t = new Test<Number>();
	}
}t breder.Testxsq ~        <sq ~     w   
xppt genericq ~ sq ~    w   
sq ~ t �package breder;
public class Test<E> {
	public E e (E e) {return e;}
	public static void main(notnull IList<String> args) {
		Test<Number> t = new Test<Number>();
		t.e(1);
	}
}t breder.Testxsq ~         sq ~ Uuq ~ X   t _['breder.Test','6','19','e'] : not found the method breder.Test.e (breder.lang.standard.String)q ~	�pt genericq ~ sq ~    w   
sq ~ t �package breder;
public class Test<E> {
	public E e (E e) {return e;}
	public static void main(notnull IList<String> args) {
		Test<Number> t = new Test<Number>();
		t.e("s");
	}
}t breder.Testxsq ~        *sq ~    w   
t 1xppt genericq ~ sq ~    w   
sq ~ t �package breder;
public class Test<E> {
	public E e (E e) {return e;}
	public static void main(notnull IList<String> args) {
		Test<Number> t = new Test<Number>();
		Console.println(t.e(1));
	}
}t breder.Testxsq ~        3sq ~    w   
t 1xppt genericq ~ sq ~    w   
sq ~ t �package breder;
public class Test<E,T> {
	public E e (E e) {return e;}
	public static void main(notnull IList<String> args) {
		Test<Number,String> t = new Test<Number,String>();
		Console.println(t.e(1));
	}
}t breder.Testxsq ~         sq ~ Uuq ~ X   t =['breder.Test','5','17','Test'] : number different of generict breder.Testpt genericq ~ sq ~    w   
sq ~ t �package breder;
public class Test<E> {
	public E e (E e) {return e;}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		Console.println(t.e(1));
	}
}t breder.Testxsq ~         sq ~ Uuq ~ X   t =['breder.Test','5','38','Test'] : number different of genericq ~"pt genericq ~ sq ~    w   
sq ~ t �package breder;
public class Test<E> {
	public E e (E e) {return e;}
	public static void main(notnull IList<String> args) {
		Test<Number> t = new Test();
		Console.println(t.e(1));
	}
}t breder.Testxsq ~        2sq ~    w   
t 1xppt genericq ~ sq ~    w   
sq ~ t �package breder;
public class Test<E> {
	public E e (E e) {return e;}
	public static void main(notnull IList<String> args) {
		Test<Number> t = new Test<Number>();
		Console.println(t.e(1));
	}
}t breder.Testxsq ~         sq ~ Uuq ~ X   t =['breder.Test','5','17','Test'] : number different of genericq ~"pt genericq ~ sq ~    w   
sq ~ t �package breder;
public class Test<E,T> {
	public E e (E e) {return e;}
	public static void main(notnull IList<String> args) {
		Test<Number> t = new Test<Number>();
		Console.println(t.e(1));
	}
}t breder.Testxsq ~         sq ~ Uuq ~ X   t �['breder.Test','4','38','Test'] : cannot cast breder.Test<breder.lang.standard.Number> to breder.Test<breder.lang.standard.Object>t breder.Testpt genericq ~ sq ~    w   
sq ~ t �package breder;
public class Test<E> {
	public static void main(notnull IList<String> args) {
		Test<Object> t = new Test<Number>();
	}
}t breder.Testxsq ~        !sq ~ Uuq ~ X   t  t breder.Testpt genericq ~ sq ~    w   
sq ~ t �package breder;
public class Test<E> {
	public static void main(notnull IList<String> args) {
		Test<Number> t = new Test<Number>();
	}
}t breder.Testxsq ~         sq ~    w   
t +['breder.Test','3','34','1'] : can not castxppt generic.castq ~ sq ~    w   
sq ~ t �package breder;
public class Test<E> {
	public E e (E e) {return 1;}
	public static void main(notnull IList<String> args) {
		Test<Number> t = new Test<Number>();
		Console.println(t.e(1));
	}
}t breder.Testxsq ~         sq ~ Uuq ~ X   t �['breder.Test','5','42','ArrayList'] : cannot cast breder.util.standard.ArrayList<breder.lang.standard.Number> to breder.util.IList<breder.lang.standard.String>q ~Fpt generic.castq ~ sq ~    w   
sq ~ t �package breder;
import breder.util.*;
public class Test {
	public static void main(notnull IList<String> args) {
		IList<String> list = new ArrayList<Number>();
	}
}t breder.Testxsq ~         sq ~ Uuq ~ X   t J['breder.Test','4','23','add'] : method already declared at the same classt breder.Testpt generic.classq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1<Number, Number> {
	public Number add(Number e) { return e; }
	public Number add(Number e) { return e; }
	public static void main(notnull IList<String> args) {
		Console.println(new Test().add(5));
	}
}t breder.Testsq ~ t Gpackage breder;
public abstract class Test1<E,T> extends Test2<E,T> {
}t breder.Test1sq ~ t vpackage breder;
public abstract class Test2<E,T> {
	public E add(E e) { return e; }
	public T add(T e) { return e; }
}t breder.Test2xsq ~        7sq ~     w   
xppt generic.interfaceq ~ sq ~    w   
sq ~ t _package breder;
public class Test {
	public static void main(notnull IList<String> args) {
	}
}t breder.Testsq ~ t -package breder;
public interface Test1<E> {
}t breder.Test1xsq ~        -sq ~     w   
xppt generic.interfaceq ~ sq ~    w   
sq ~ t xpackage breder;
public class Test implements Test1<Number> {
	public static void main(notnull IList<String> args) {
	}
}t breder.Testsq ~ t -package breder;
public interface Test1<E> {
}t breder.Test1xsq ~         sq ~ Uuq ~ X   t �['breder.Test','2','14','Test'] : the class not implemented the method '(<E breder.lang.standard.Object>) add (<E breder.lang.standard.Object>)'q ~Fpt generic.interfaceq ~ sq ~    w   
sq ~ t xpackage breder;
public class Test implements Test1<Number> {
	public static void main(notnull IList<String> args) {
	}
}t breder.Testsq ~ t Apackage breder;
public interface Test1<E> {
	public E add(E e);
}t breder.Test1xsq ~        )sq ~     w   
xppt generic.interfaceq ~ sq ~    w   
sq ~ t �package breder;
public class Test implements Test1<Number> {
	public Number add(Number e) { return e; }
	public static void main(notnull IList<String> args) {
	}
}t breder.Testsq ~ t Apackage breder;
public interface Test1<E> {
	public E add(E e);
}t breder.Test1xsq ~        5sq ~    w   
t 5xppt generic.interfaceq ~ sq ~    w   
sq ~ t �package breder;
public class Test implements Test1<Number> {
	public Number add(Number e) { return e; }
	public static void main(notnull IList<String> args) {
		Console.println(new Test().add(5));
	}
}t breder.Testsq ~ t Apackage breder;
public interface Test1<E> {
	public E add(E e);
}t breder.Test1xsq ~        Usq ~ Uuq ~ X   t 5t breder.Testpt generic.interfaceq ~ sq ~    w   
sq ~ t �package breder;
public class Test implements Test1<Number> {
	public Number add(Number e) { return e; }
	public static void main(notnull IList<String> args) {
		Console.println(new Test().add(5));
	}
}t breder.Testsq ~ t >package breder;
public interface Test1<E> extends Test2<E> {
}t breder.Test1sq ~ t Apackage breder;
public interface Test2<E> {
	public E add(E e);
}t breder.Test2xsq ~        +sq ~ Uuq ~ X   t 5t breder.Testpt generic.interfaceq ~ sq ~    w   
sq ~ t �package breder;
public class Test<E> implements Test1<E> {
	public E add(E e) { return e; }
	public static void main(notnull IList<String> args) {
		Console.println(new Test<Number>().add(5));
	}
}t breder.Testsq ~ t >package breder;
public interface Test1<E> extends Test2<E> {
}t breder.Test1sq ~ t Apackage breder;
public interface Test2<E> {
	public E add(E e);
}t breder.Test2xsq ~        3sq ~ Uuq ~ X   t 5q ~�pt generic.interfaceq ~ sq ~    w   
sq ~ t �package breder;
public class Test implements Test1<Number, String> {
	public Number add(Number e) { return e; }
	public static void main(notnull IList<String> args) {
		Console.println(new Test().add(5));
	}
}t breder.Testsq ~ t @package breder;
public interface Test1<E,T> extends Test2<E> {
}t breder.Test1sq ~ t Apackage breder;
public interface Test2<E> {
	public E add(E e);
}t breder.Test2xsq ~        9sq ~ Uuq ~ X   t 5q ~�pt generic.interfaceq ~ sq ~    w   
sq ~ t �package breder;
public class Test implements Test1<Number, String> {
	public Number add(Number e) { return e; }
	public static void main(notnull IList<String> args) {
		Console.println(new Test().add(5));
	}
}t breder.Testsq ~ t Bpackage breder;
public interface Test1<E,T> extends Test2<E,T> {
}t breder.Test1sq ~ t Cpackage breder;
public interface Test2<E,T> {
	public E add(E e);
}t breder.Test2xsq ~         sq ~ Uuq ~ X   t K['breder.Test2','4','18','add'] : method already declared at the same classt breder.Testpt generic.interfaceq ~ sq ~    w   
sq ~ t �package breder;
public class Test implements Test1<Number, String> {
	public Number add(Number e) { return e; }
	public String add(String e) { return e; }
	public static void main(notnull IList<String> args) {
		Console.println(new Test().add(5));
	}
}t breder.Testsq ~ t Bpackage breder;
public interface Test1<E,T> extends Test2<E,T> {
}t breder.Test1sq ~ t Wpackage breder;
public interface Test2<E,T> {
	public E add(E e);
	public E add(T e);
}t breder.Test2xsq ~         sq ~ Uuq ~ X   t K['breder.Test2','4','18','add'] : method already declared at the same classq ~�pt generic.interfaceq ~ sq ~    w   
sq ~ t �package breder;
public class Test implements Test1<Number, Number> {
	public Number add(Number e) { return e; }
	public static void main(notnull IList<String> args) {
		Console.println(new Test().add(5));
	}
}t breder.Testsq ~ t Bpackage breder;
public interface Test1<E,T> extends Test2<E,T> {
}t breder.Test1sq ~ t Wpackage breder;
public interface Test2<E,T> {
	public E add(E e);
	public T add(T e);
}t breder.Test2xsq ~         sq ~ Uuq ~ X   t J['breder.Test','4','23','add'] : method already declared at the same classt breder.Testpt generic.interfaceq ~ sq ~    w   
sq ~ t �package breder;
public class Test implements Test1<Number, Number> {
	public Number add(Number e) { return e; }
	public Number add(Number e) { return e; }
	public static void main(notnull IList<String> args) {
		Console.println(new Test().add(5));
	}
}t breder.Testsq ~ t Bpackage breder;
public interface Test1<E,T> extends Test2<E,T> {
}t breder.Test1sq ~ t Wpackage breder;
public interface Test2<E,T> {
	public E add(E e);
	public E add(T e);
}t breder.Test2xsq ~        8sq ~    w   
t 1xppt ifq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		if (true) {
			Console.println(1);
		}
	}
}t breder.Testxsq ~        (sq ~     w   
xppt ifq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		if (false) {
			Console.println("1");
		}
	}
}t breder.Testxsq ~        @sq ~    w   
t truexppt ifq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static notnull String a () {
		return "ae";
	}
	public final static void main(notnull IList<String> args) {
		if (Test.a() != null) {
			Console.println(true);
		} else {
			Console.println(false);
		}
	}
}t breder.Testxsq ~        5sq ~    w   
t truexppt ifq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static Number i;
	public final static void main(notnull IList<String> args) {
		if (Test.i == null) {
			Console.println(true);
		}
	}
}t breder.Testxsq ~        1sq ~    w   
t truexppt ifq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static Number i;
	public final static void main(notnull IList<String> args) {
		if (Test.i  != null) {
			Console.println(false);
		} else {
			Console.println(true);
		}
	}
}t breder.Testxsq ~        7sq ~    w   
t truexppt ifq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static Number i;
	public final static void main(notnull IList<String> args) {
		if (Test.i == null) {
			Console.println(true);
		}
	}
}t breder.Testxsq ~        (sq ~    w   
t truexppt ifq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static Number i;
	public final static void main(notnull IList<String> args) {
		if (Test.i != null) {
			Console.println(false);
		} else {
			Console.println(true);
		}
	}
}t breder.Testxsq ~        Gsq ~ Uuq ~ X   t 1t breder.Testpt ifq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Boolean flag = true;
		if (flag) {
			Console.println(1);
		}
	}
}q ~Tsq ~        3sq ~ Uuq ~ X   t 1q ~Tpt ifq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Boolean flag = true;
		if (flag) {
			Console.println(1);
		} elseif (flag) {}
	}
}q ~Tsq ~        9sq ~ Uuq ~ X   t 1q ~Tpt ifq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Boolean flag = true;
		if (flag) {
			Console.println(1);
		} elseif (flag) {
		} else {}
	}
}q ~Tsq ~        4sq ~    w   
t ifxppt if-elseq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		if (true) {
			Console.println("if");
		} else {
			Console.println("else");
		}
	}
}t breder.Testxsq ~        %sq ~    w   
t elsexppt if-elseq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		if (false) {
			Console.println("if");
		} else {
			Console.println("else");
		}
	}
}t breder.Testxsq ~        0sq ~    w   
t ifxppt if-elseif-elseq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		if (true) {
			Console.println("if");
		} elseif (true) {
			Console.println("elseif");
		} else {
			Console.println("else");
		}
	}
}t breder.Testxsq ~        +sq ~    w   
t elseifxppt if-elseif-elseq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		if (false) {
			Console.println("if");
		} elseif (true) {
			Console.println("elseif");
		} else {
			Console.println("else");
		}
	}
}t breder.Testxsq ~        (sq ~    w   
t elsexppt if-elseif-elseq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		if (false) {
			Console.println("if");
		} elseif (false) {
			Console.println("elseif");
		} else {
			Console.println("else");
		}
	}
}t breder.Testxsq ~        0sq ~    w   
t elseif2xppt if-elseif-elseif-elseq ~ sq ~    w   
sq ~ tpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		if (false) {
			Console.println("if");
		} elseif (false) {
			Console.println("elseif1");
		} elseif (true) {
			Console.println("elseif2");
		} else {
			Console.println("else");
		}
	}
}t breder.Testxsq ~        (sq ~    w   
t elseif1xppt if-elseif-elseif-elseq ~ sq ~    w   
sq ~ tpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		if (false) {
			Console.println("if");
		} elseif (true) {
			Console.println("elseif1");
		} elseif (false) {
			Console.println("elseif2");
		} else {
			Console.println("else");
		}
	}
}t breder.Testxsq ~        8sq ~    w   
t ifxppt if-elseif-elseif-elseq ~ sq ~    w   
sq ~ tpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		if (true) {
			Console.println("if");
		} elseif (false) {
			Console.println("elseif1");
		} elseif (false) {
			Console.println("elseif2");
		} else {
			Console.println("else");
		}
	}
}t breder.Testxsq ~        .sq ~    w   
t elsexppt if-elseif-elseif-elseq ~ sq ~    w   
sq ~ tpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		if (false) {
			Console.println("if");
		} elseif (false) {
			Console.println("elseif1");
		} elseif (false) {
			Console.println("elseif2");
		} else {
			Console.println("else");
		}
	}
}t breder.Testxsq ~        3sq ~     w   
xppt 	interfaceq ~ sq ~    w   
sq ~ t ppackage breder;
public class Test implements Test1 {
	public static void main(notnull IList<String> args) {
	}
}t breder.Testsq ~ t *package breder;
public interface Test1 {
}t breder.Test1xsq ~         sq ~    w   
t P['breder.Test','2','14','Test'] : the class not implemented the method '() a ()'xppt 	interfaceq ~ sq ~    w   
sq ~ t ppackage breder;
public class Test implements Test1 {
	public static void main(notnull IList<String> args) {
	}
}t breder.Testsq ~ t =package breder;
public interface Test1 {
	public void a ();
}t breder.Test1xsq ~         sq ~    w   
t P['breder.Test','2','14','Test'] : the class not implemented the method '() a ()'xppt 	interfaceq ~ sq ~    w   
sq ~ t ppackage breder;
public class Test implements Test1 {
	public static void main(notnull IList<String> args) {
	}
}t breder.Testsq ~ t Ppackage breder;
public interface Test1 {
	public void a ();
	public void b ();
}t breder.Test1xsq ~        2sq ~    w   
t at bxppt 	interfaceq ~ sq ~    w   
sq ~ t �package breder;
public class Test implements Test1 {
	public static void main(notnull IList<String> args) {
		new Test().a();
		new Test().b();
	}
	public void a() {Console.println("a");}
	public void b() {Console.println("b");}
}t breder.Testsq ~ t Ppackage breder;
public interface Test1 {
	public void a ();
	public void b ();
}t breder.Test1xsq ~        Fsq ~    w   
t at bxppt 	interfaceq ~ sq ~    w   
sq ~ t �package breder;
public class Test implements Test1, Test2 {
	public static void main(notnull IList<String> args) {
		new Test().a();
		new Test().b();
	}
	public void a() {Console.println("a");}
	public void b() {Console.println("b");}
}t breder.Testsq ~ t Ppackage breder;
public interface Test1 {
	public void a ();
	public void b ();
}t breder.Test1sq ~ t Ppackage breder;
public interface Test2 {
	public void a ();
	public void b ();
}t breder.Test2xsq ~        /sq ~    w   
t at bt ct dxppt 	interfaceq ~ sq ~    w   
sq ~ tcpackage breder;
public class Test implements Test1, Test2 {
	public static void main(notnull IList<String> args) {
		new Test().a();
		new Test().b();
		new Test().c();
		new Test().d();
	}
	public void a() {Console.println("a");}
	public void b() {Console.println("b");}
	public void c() {Console.println("c");}
	public void d() {Console.println("d");}
}t breder.Testsq ~ t Ppackage breder;
public interface Test1 {
	public void a ();
	public void b ();
}t breder.Test1sq ~ t Ppackage breder;
public interface Test2 {
	public void c ();
	public void d ();
}t breder.Test2xsq ~        sq ~    w   
t at bt ct dxppt 	interfaceq ~ sq ~    w   
sq ~ tcpackage breder;
public class Test implements Test1, Test2 {
	public static void main(notnull IList<String> args) {
		new Test().a();
		new Test().b();
		new Test().c();
		new Test().d();
	}
	public void a() {Console.println("a");}
	public void b() {Console.println("b");}
	public void c() {Console.println("c");}
	public void d() {Console.println("d");}
}t breder.Testsq ~ t Ppackage breder;
public interface Test1 {
	public void a ();
	public void b ();
}t breder.Test1sq ~ t ^package breder;
public interface Test2 extends Test3 {
	public void c ();
	public void d ();
}t breder.Test2sq ~ t Ppackage breder;
public interface Test3 {
	public void c ();
	public void d ();
}t breder.Test3xsq ~        0sq ~    w   
t at bt ct dxppt 	interfaceq ~ sq ~    w   
sq ~ tcpackage breder;
public class Test implements Test1, Test2 {
	public static void main(notnull IList<String> args) {
		new Test().a();
		new Test().b();
		new Test().c();
		new Test().d();
	}
	public void a() {Console.println("a");}
	public void b() {Console.println("b");}
	public void c() {Console.println("c");}
	public void d() {Console.println("d");}
}t breder.Testsq ~ t ^package breder;
public interface Test1 extends Test3 {
	public void a ();
	public void b ();
}t breder.Test1sq ~ t ^package breder;
public interface Test2 extends Test3 {
	public void c ();
	public void d ();
}t breder.Test2sq ~ t Ppackage breder;
public interface Test3 {
	public void c ();
	public void d ();
}t breder.Test3xsq ~         sq ~    w   
t P['breder.Test','2','14','Test'] : the class not implemented the method '() e ()'xppt 	interfaceq ~ sq ~    w   
sq ~ tcpackage breder;
public class Test implements Test1, Test2 {
	public static void main(notnull IList<String> args) {
		new Test().a();
		new Test().b();
		new Test().c();
		new Test().d();
	}
	public void a() {Console.println("a");}
	public void b() {Console.println("b");}
	public void c() {Console.println("c");}
	public void d() {Console.println("d");}
}t breder.Testsq ~ t ^package breder;
public interface Test1 extends Test3 {
	public void a ();
	public void b ();
}t breder.Test1sq ~ t ^package breder;
public interface Test2 extends Test3 {
	public void c ();
	public void d ();
}t breder.Test2sq ~ t Ppackage breder;
public interface Test3 {
	public void e ();
	public void f ();
}t breder.Test3xsq ~        Jsq ~    w   
t at bt ct dt et fxppt 	interfaceq ~ sq ~    w   
sq ~ t�package breder;
public class Test implements Test1, Test2 {
	public static void main(notnull IList<String> args) {
		new Test().a();
		new Test().b();
		new Test().c();
		new Test().d();
		new Test().e();
		new Test().f();
	}
	public void a() {Console.println("a");}
	public void b() {Console.println("b");}
	public void c() {Console.println("c");}
	public void d() {Console.println("d");}
	public void e() {Console.println("e");}
	public void f() {Console.println("f");}
}t breder.Testsq ~ t ^package breder;
public interface Test1 extends Test3 {
	public void a ();
	public void b ();
}t breder.Test1sq ~ t ^package breder;
public interface Test2 extends Test3 {
	public void c ();
	public void d ();
}t breder.Test2sq ~ t Ppackage breder;
public interface Test3 {
	public void e ();
	public void f ();
}t breder.Test3xsq ~         sq ~ Uuq ~ X   t 8['breder.Test1','3','29','{'] : Expected {"throws", ";"}t breder.Testpt 	interfaceq ~ sq ~    w   
sq ~ t ppackage breder;
public class Test implements Test1 {
	public static void main(notnull IList<String> args) {
	}
}t breder.Testsq ~ t Bpackage breder;
public interface Test1 {
	public void test () {}
}t breder.Test1xsq ~        9sq ~    w   
t axppt interface.castq ~ sq ~    w   
sq ~ t �package breder;
public class Test implements Test1 {
	public static void main(notnull IList<String> args) {
		((Test1)new Test()).a();
	}
	public void a() {Console.println("a");}
}t breder.Testsq ~ t =package breder;
public interface Test1 {
	public void a ();
}t breder.Test1xsq ~        0sq ~    w   
t at bt ct dt et fxppt interface.castq ~ sq ~    w   
sq ~ tqpackage breder;
public class Test extends Test4 implements Test1 {
	public static void main(notnull IList<String> args) {
		((Test1)new Test()).a();
		((Test)new Test()).b();
		((Test2)new Test()).c();
		((Test3)new Test()).d();
		((Test3)new Test()).e();
		((Test4)new Test()).f();
	}
	public void a() {Console.println("a");}
	public void b() {Console.println("b");}
}t breder.Testsq ~ t Ppackage breder;
public interface Test1 {
	public void a ();
	public void b ();
}t breder.Test1sq ~ t ^package breder;
public interface Test2 extends Test3 {
	public void c ();
	public void d ();
}t breder.Test2sq ~ t vpackage breder;
public interface Test3 {
	public void c ();
	public void d ();
	public void e ();
	public void f ();
}t breder.Test3sq ~ t �package breder;
public class Test4 implements Test2 {
	public void c() {Console.println("c");}
	public void d() {Console.println("d");}
	public void e() {Console.println("e");}
	public void f() {Console.println("f");}
}t breder.Test4xsq ~         sq ~ Uuq ~ X   t H['breder.Test1','2','32','Test2'] : interface can extend only interfacest breder.Testpt interface.extend.classq ~ sq ~    w   
sq ~ t ppackage breder;
public class Test implements Test1 {
	public static void main(notnull IList<String> args) {
	}
}t breder.Testsq ~ t 8package breder;
public interface Test1 extends Test2 {
}t breder.Test1sq ~ t &package breder;
public class Test2 {
}t breder.Test2xsq ~        1sq ~ Uuq ~ X   t  q ~�pt interface.extend.interfaceq ~ sq ~    w   
sq ~ t ppackage breder;
public class Test implements Test1 {
	public static void main(notnull IList<String> args) {
	}
}t breder.Testsq ~ t 8package breder;
public interface Test1 extends Test2 {
}t breder.Test1sq ~ t *package breder;
public interface Test2 {
}t breder.Test2xsq ~        1sq ~    w   
t at bt ct dt et fxppt interface.extendsq ~ sq ~    w   
sq ~ tCpackage breder;
public class Test extends Test4 implements Test1, Test2 {
	public static void main(notnull IList<String> args) {
		new Test().a();
		new Test().b();
		new Test().c();
		new Test().d();
		new Test().e();
		new Test().f();
	}
	public void a() {Console.println("a");}
	public void b() {Console.println("b");}
}t breder.Testsq ~ t ^package breder;
public interface Test1 extends Test3 {
	public void a ();
	public void b ();
}t breder.Test1sq ~ t ^package breder;
public interface Test2 extends Test3 {
	public void c ();
	public void d ();
}t breder.Test2sq ~ t Ppackage breder;
public interface Test3 {
	public void e ();
	public void f ();
}t breder.Test3sq ~ t �package breder;
public class Test4 {
	public void c() {Console.println("c");}
	public void d() {Console.println("d");}
	public void e() {Console.println("e");}
	public void f() {Console.println("f");}
}t breder.Test4xsq ~        sq ~    w   
t at bt ct dt et fxppt interface.extendsq ~ sq ~    w   
sq ~ t<package breder;
public class Test extends Test4 implements Test1 {
	public static void main(notnull IList<String> args) {
		new Test().a();
		new Test().b();
		new Test().c();
		new Test().d();
		new Test().e();
		new Test().f();
	}
	public void a() {Console.println("a");}
	public void b() {Console.println("b");}
}t breder.Testsq ~ t Ppackage breder;
public interface Test1 {
	public void a ();
	public void b ();
}t breder.Test1sq ~ t ^package breder;
public interface Test2 extends Test3 {
	public void c ();
	public void d ();
}t breder.Test2sq ~ t Ppackage breder;
public interface Test3 {
	public void e ();
	public void f ();
}t breder.Test3sq ~ t �package breder;
public class Test4 implements Test2 {
	public void c() {Console.println("c");}
	public void d() {Console.println("d");}
	public void e() {Console.println("e");}
	public void f() {Console.println("f");}
}t breder.Test4xsq ~         sq ~ Uuq ~ X   t D['breder.Test','9','28','f'] : not found the method breder.Test.f ()q ~"pt interface.extendsq ~ sq ~    w   
sq ~ t<package breder;
public class Test extends Test4 implements Test1 {
	public static void main(notnull IList<String> args) {
		new Test().a();
		new Test().b();
		new Test().c();
		new Test().d();
		new Test().e();
		new Test().f();
	}
	public void a() {Console.println("a");}
	public void b() {Console.println("b");}
}t breder.Testsq ~ t Ppackage breder;
public interface Test1 {
	public void a ();
	public void b ();
}t breder.Test1sq ~ t ^package breder;
public interface Test2 extends Test3 {
	public void c ();
	public void d ();
}t breder.Test2sq ~ t Ppackage breder;
public interface Test3 {
	public void e ();
	public void f ();
}t breder.Test3sq ~ t �package breder;
public class Test4 implements Test2 {
	public void c() {Console.println("c");}
	public void d() {Console.println("d");}
	public void e() {Console.println("e");}
}t breder.Test4xsq ~        1sq ~     w   
xppt method.notstaticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public void a() {}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testxsq ~        %sq ~    w   
t 1xppt method.notstaticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public void a() {
		Console.println(1);
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t.a();
	}
}t breder.Testxsq ~        @sq ~    w   
t 1xppt method.notstaticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public void a(Number i) {
		Console.println(i);
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t.a(1);
	}
}t breder.Testxsq ~        0sq ~    w   
t 1t 2xppt method.notstaticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public void a(Number i, Number u) {
		Console.println(i);
		Console.println(u);
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t.a(1,2);
	}
}t breder.Testxsq ~        3sq ~    w   
t 1t 2t 1xppt method.notstaticq ~ sq ~    w   
sq ~ tpackage breder;
public class Test {
	public Number a(Number i, Number u) {
		Console.println(i);
		Console.println(u);
		return i;
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		Number i = t.a(1,2);
		Console.println(i);
	}
}t breder.Testxsq ~        (sq ~    w   
t 1t 2t 1t 2xppt method.notstaticq ~ sq ~    w   
sq ~ t0package breder;
public class Test {
	public Number, Number a(Number i, Number u) {
		Console.println(i);
		Console.println(u);
		return i, u;
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		Number i, Number u = t.a(1,2);
		Console.println(i);
		Console.println(u);
	}
}t breder.Testxsq ~        :sq ~    w   
t 1t 2t 1t 3t 1xppt method.notstaticq ~ sq ~    w   
sq ~ tpackage breder;
public class Test {
	public Number a(Number i, Number u) {
		Console.println(i);
		Console.println(u);
		return i;
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		Number i = t.a(t.a(1,2),3);
		Console.println(i);
	}
}t breder.Testxsq ~        4sq ~    w   
t 3t 4t 1t 3t 1t 3t 1xppt method.notstaticq ~ sq ~    w   
sq ~ tpackage breder;
public class Test {
	public Number a(Number i, Number u) {
		Console.println(i);
		Console.println(u);
		return i;
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		Number i = t.a(t.a(1,t.a(3,4)),3);
		Console.println(i);
	}
}t breder.Testxsq ~        lsq ~    w   
t Test1.axppt method.notstatic.polyq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t.a();
	}
}t breder.Testsq ~ t Zpackage breder;
public class Test1 {
	public void a() {
		Console.println("Test1.a");
	}
}t breder.Test1xsq ~        ;sq ~    w   
t Test.axppt method.notstatic.polyq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public void a() {
		Console.println("Test.a");
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t.a();
	}
}t breder.Testsq ~ t Zpackage breder;
public class Test1 {
	public void a() {
		Console.println("Test1.a");
	}
}t breder.Test1xsq ~        /sq ~    w   
t Test2.axppt method.notstatic.polyq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public void a() {
		Console.println("Test.a");
	}
	public static void main(notnull IList<String> args) {
		Test2 t = new Test2();
		t.a();
	}
}t breder.Testsq ~ t hpackage breder;
public class Test1 extends Test2 {
	public void a() {
		Console.println("Test1.a");
	}
}t breder.Test1sq ~ t Zpackage breder;
public class Test2 {
	public void a() {
		Console.println("Test2.a");
	}
}t breder.Test2xsq ~        ;sq ~    w   
t Test1.axppt method.notstatic.polyq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public void a() {
		Console.println("Test.a");
	}
	public static void main(notnull IList<String> args) {
		Test1 t = new Test1();
		t.a();
	}
}t breder.Testsq ~ t hpackage breder;
public class Test1 extends Test2 {
	public void a() {
		Console.println("Test1.a");
	}
}t breder.Test1sq ~ t Zpackage breder;
public class Test2 {
	public void a() {
		Console.println("Test2.a");
	}
}t breder.Test2xsq ~        +sq ~    w   
t Test.axppt method.notstatic.polyq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public void a() {
		Console.println("Test.a");
	}
	public static void main(notnull IList<String> args) {
		Test1 t = new Test();
		t.a();
	}
}t breder.Testsq ~ t hpackage breder;
public class Test1 extends Test2 {
	public void a() {
		Console.println("Test1.a");
	}
}t breder.Test1sq ~ t Zpackage breder;
public class Test2 {
	public void a() {
		Console.println("Test2.a");
	}
}t breder.Test2xsq ~        5sq ~    w   
t Test.axppt method.notstatic.polyq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public void a() {
		Console.println("Test.a");
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t.a();
	}
}t breder.Testsq ~ t hpackage breder;
public class Test1 extends Test2 {
	public void a() {
		Console.println("Test1.a");
	}
}t breder.Test1sq ~ t Zpackage breder;
public class Test2 {
	public void a() {
		Console.println("Test2.a");
	}
}t breder.Test2xsq ~         sq ~    w   
t 8['breder.Test','5','19','a'] : ambiguidade entre metodosxppt method.notstatic.polyq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1, Test2 {
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t.a();
	}
}t breder.Testsq ~ t Zpackage breder;
public class Test1 {
	public void a() {
		Console.println("Test1.a");
	}
}t breder.Test1sq ~ t Zpackage breder;
public class Test2 {
	public void a() {
		Console.println("Test2.a");
	}
}t breder.Test2xsq ~        /sq ~    w   
t Test.axppt method.notstatic.polyq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1, Test2 {
	public void a() {
		Console.println("Test.a");
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t.a();
	}
}t breder.Testsq ~ t Zpackage breder;
public class Test1 {
	public void a() {
		Console.println("Test1.a");
	}
}t breder.Test1sq ~ t Zpackage breder;
public class Test2 {
	public void a() {
		Console.println("Test2.a");
	}
}t breder.Test2xsq ~        +sq ~    w   
t Test1.axppt method.notstatic.polyq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1, Test2 {
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		((Test1)t).a();
	}
}t breder.Testsq ~ t Zpackage breder;
public class Test1 {
	public void a() {
		Console.println("Test1.a");
	}
}t breder.Test1sq ~ t Zpackage breder;
public class Test2 {
	public void a() {
		Console.println("Test2.a");
	}
}t breder.Test2xsq ~        >sq ~    w   
t Test2.axppt method.notstatic.polyq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1, Test2 {
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		((Test2)t).a();
	}
}t breder.Testsq ~ t Zpackage breder;
public class Test1 {
	public void a() {
		Console.println("Test1.a");
	}
}t breder.Test1sq ~ t Zpackage breder;
public class Test2 {
	public void a() {
		Console.println("Test2.a");
	}
}t breder.Test2xsq ~        9sq ~    w   
t Test1.axppt method.notstatic.polyq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1, Test2 {
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		((Test1)t).a();
	}
}t breder.Testsq ~ t hpackage breder;
public class Test1 extends Test3 {
	public void a() {
		Console.println("Test1.a");
	}
}t breder.Test1sq ~ t Zpackage breder;
public class Test3 {
	public void a() {
		Console.println("Test3.a");
	}
}t breder.Test3sq ~ t Zpackage breder;
public class Test2 {
	public void a() {
		Console.println("Test2.a");
	}
}t breder.Test2xsq ~        %sq ~    w   
t Test.axppt method.notstatic.polyq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1, Test2 {
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		((Test1)t).a();
	}
	public void a() {
		Console.println("Test.a");
	}
}t breder.Testsq ~ t hpackage breder;
public class Test1 extends Test3 {
	public void a() {
		Console.println("Test1.a");
	}
}t breder.Test1sq ~ t Zpackage breder;
public class Test3 {
	public void a() {
		Console.println("Test3.a");
	}
}t breder.Test3sq ~ t Zpackage breder;
public class Test2 {
	public void a() {
		Console.println("Test2.a");
	}
}t breder.Test2xsq ~        7sq ~    w   
t Test3.axppt method.notstatic.polyq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1, Test2 {
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		((Test1)t).a();
	}
}t breder.Testsq ~ t 4package breder;
public class Test1 extends Test3 {
}t breder.Test1sq ~ t Zpackage breder;
public class Test3 {
	public void a() {
		Console.println("Test3.a");
	}
}t breder.Test3sq ~ t Zpackage breder;
public class Test2 {
	public void a() {
		Console.println("Test2.a");
	}
}t breder.Test2xsq ~        4sq ~    w   
t Test3.axppt method.notstatic.polyq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1, Test2 {
	public static void main(notnull IList<String> args) {
		new Test().a();
	}
}t breder.Testsq ~ t 4package breder;
public class Test1 extends Test3 {
}t breder.Test1sq ~ t Zpackage breder;
public class Test3 {
	public void a() {
		Console.println("Test3.a");
	}
}t breder.Test3sq ~ t &package breder;
public class Test2 {
}t breder.Test2xsq ~        (sq ~    w   
t Test3.axppt method.notstatic.polyq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1, Test2 {
	public static void main(notnull IList<String> args) {
		((Test1)new Test()).a();
	}
}t breder.Testsq ~ t 4package breder;
public class Test1 extends Test3 {
}t breder.Test1sq ~ t Zpackage breder;
public class Test3 {
	public void a() {
		Console.println("Test3.a");
	}
}t breder.Test3sq ~ t Zpackage breder;
public class Test2 {
	public void a() {
		Console.println("Test2.a");
	}
}t breder.Test2xsq ~        "sq ~    w   
t Test4.axppt method.notstatic.polyq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1, Test2, Test4 {
	public static void main(notnull IList<String> args) {
		((Test4)new Test()).a();
	}
}t breder.Testsq ~ t 4package breder;
public class Test1 extends Test3 {
}t breder.Test1sq ~ t Zpackage breder;
public class Test3 {
	public void a() {
		Console.println("Test3.a");
	}
}t breder.Test3sq ~ t Zpackage breder;
public class Test2 {
	public void a() {
		Console.println("Test2.a");
	}
}t breder.Test2sq ~ t Zpackage breder;
public class Test4 {
	public void a() {
		Console.println("Test4.a");
	}
}t breder.Test4xsq ~          sq ~    w   
t Test2.axppt method.notstatic.poly.circlepsq ~    w   
sq ~ t �package breder;
public class Test extends Test1, Test2 {
	public static void main(notnull IList<String> args) {
		((Test2)new Test()).a();
	}
}t breder.Testsq ~ t hpackage breder;
public class Test1 extends Test2 {
	public void a() {
		Console.println("Test1.a");
	}
}t breder.Test1sq ~ t Zpackage breder;
public class Test2 {
	public void a() {
		Console.println("Test2.a");
	}
}t breder.Test2xsq ~        #sq ~     w   
xppt method.staticq ~ sq ~    w   
sq ~ t {package breder;
public class Test {
	public static void a () {}
	public static void main(notnull IList<String> args) {
	}
}t breder.Testxsq ~        ?sq ~    w   
t 1xppt method.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void a () {
		Console.println(1);
	}
	public static void main(notnull IList<String> args) {
		Test.a();
	}
}t breder.Testxsq ~        (sq ~    w   
t 1xppt method.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void a (Number i) {
		Console.println(i);
	}
	public static void main(notnull IList<String> args) {
		Test.a(1);
	}
}t breder.Testxsq ~        2sq ~    w   
t 1t 2xppt method.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void a (Number i, Number u) {
		Console.println(i);
		Console.println(u);
	}
	public static void main(notnull IList<String> args) {
		Test.a(1,2);
	}
}t breder.Testxsq ~        %sq ~    w   
t 1t 2t 3xppt method.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static Number a (Number i, Number u) {
		Console.println(i);
		Console.println(u);
		return 3;
	}
	public static void main(notnull IList<String> args) {
		Number i = Test.a(1,2);
		Console.println(i);
	}
}t breder.Testxsq ~        3sq ~    w   
t 1t 2t 1xppt method.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static Number a (Number i, Number u) {
		Console.println(i);
		Console.println(u);
		return i;
	}
	public static void main(notnull IList<String> args) {
		Number i = Test.a(1,2);
		Console.println(i);
	}
}t breder.Testxsq ~        >sq ~    w   
t 1t 2t 1t 2xppt method.staticq ~ sq ~    w   
sq ~ t$package breder;
public class Test {
	public static Number, Number a (Number i, Number u) {
		Console.println(i);
		Console.println(u);
		return i, u;
	}
	public static void main(notnull IList<String> args) {
		Number i, Number u = Test.a(1,2);
		Console.println(i);
		Console.println(u);
	}
}t breder.Testxsq ~        ?sq ~    w   
t 1t 2t 1t 2xppt method.staticq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Number i, Number u = Test2.a(1,2);
		Console.println(i);
		Console.println(u);
	}
}t breder.Testsq ~ t �package breder;
public class Test2 {
	public static Number, Number a (Number i, Number u) {
		Console.println(i);
		Console.println(u);
		return i, u;
	}
}t breder.Test2xsq ~        )sq ~     w   
xppt newq ~ sq ~    w   
sq ~ t mpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		new Test();
	}
}t breder.Testxsq ~        ;sq ~     w   
xppt newq ~ sq ~    w   
sq ~ t vpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testxsq ~        6sq ~     w   
xppt newq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public Test() {
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testxsq ~        Esq ~     w   
xppt newq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public Test() {
		super();
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testxsq ~        'sq ~     w   
xppt newq ~ sq ~    w   
sq ~ t {package breder;
public class Test extends Test1 {
	public static void main(notnull IList<String> args) {
		new Test();
	}
}t breder.Testsq ~ t &package breder;
public class Test1 {
}t breder.Test1xsq ~        *sq ~     w   
xppt newq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testsq ~ t &package breder;
public class Test1 {
}t breder.Test1xsq ~        6sq ~     w   
xppt newq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public Test () {
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testsq ~ t &package breder;
public class Test1 {
}t breder.Test1xsq ~         sq ~     w   
xppt newq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public Test () {
		super();
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testsq ~ t &package breder;
public class Test1 {
}t breder.Test1xsq ~        0sq ~     w   
xppt newq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public Test () {
		super();
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testsq ~ t ;package breder;
public class Test1 {
	public Test1() {
	}
}t breder.Test1xsq ~        /sq ~     w   
xppt newq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public Test () {
		super();
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testsq ~ t Fpackage breder;
public class Test1 {
	public Test1() {
		super();
	}
}t breder.Test1xsq ~        1sq ~     w   
xppt newq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public Test () {
		super(1);
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testsq ~ t Npackage breder;
public class Test1 {
	public Test1(Number x) {
		super();
	}
}t breder.Test1xsq ~        .sq ~     w   
xppt newq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 , Test2 {
	public Test () {
		super(;);
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testsq ~ t &package breder;
public class Test1 {
}t breder.Test1sq ~ t &package breder;
public class Test2 {
}t breder.Test2xsq ~        9sq ~     w   
xppt newq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 , Test2 {
	public Test () {
		super(;);
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testsq ~ t :package breder;
public class Test1 {
	public Test1 () {}
}t breder.Test1sq ~ t :package breder;
public class Test2 {
	public Test2 () {}
}t breder.Test2xsq ~        7sq ~     w   
xppt newq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 , Test2 {
	public Test () {
		super(1;2);
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testsq ~ t Npackage breder;
public class Test1 {
	public Test1(Number x) {
		super();
	}
}t breder.Test1sq ~ t Npackage breder;
public class Test2 {
	public Test2(Number x) {
		super();
	}
}t breder.Test2xsq ~        )sq ~    w   
t 1t 2xppt newq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 , Test2 {
	public Test () {
		super(1;2);
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testsq ~ t dpackage breder;
public class Test1 {
	public Test1(Number x) {
		Console.println(x);
		super();
	}
}t breder.Test1sq ~ t dpackage breder;
public class Test2 {
	public Test2(Number x) {
		Console.println(x);
		super();
	}
}t breder.Test2xsq ~        'sq ~ Uuq ~ X   t 2t 3t 1t breder.Testpt newq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 , Test2 {
	public Test () {
		super(2;1);
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testsq ~ t }package breder;
public class Test1 extends Test3 {
	public Test1(notnull Number x) {
		Console.println(x);
		super(x+1);
	}
}t breder.Test1sq ~ t dpackage breder;
public class Test2 {
	public Test2(Number x) {
		Console.println(x);
		super();
	}
}t breder.Test2sq ~ t dpackage breder;
public class Test3 {
	public Test3(Number x) {
		Console.println(x);
		super();
	}
}t breder.Test3xsq ~        >sq ~ Uuq ~ X   t 4t 3t 2t 1t 0t breder.Testpt newq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public Test () {
		super();
		Console.println(0);
	}
	public static void main(notnull IList<String> args) {
		Test t = new Test();
	}
}t breder.Testsq ~ t jpackage breder;
public class Test1 extends Test2 {
	public Test1() {
		super();
		Console.println(1);
	}
}t breder.Test1sq ~ t jpackage breder;
public class Test2 extends Test3 {
	public Test2() {
		super();
		Console.println(2);
	}
}t breder.Test2sq ~ t jpackage breder;
public class Test3 extends Test4 {
	public Test3() {
		super();
		Console.println(3);
	}
}t breder.Test3sq ~ t \package breder;
public class Test4 {
	public Test4() {
		super();
		Console.println(4);
	}
}t breder.Test4xsq ~        5sq ~ Uuq ~ X   t  t breder.Testpt notnullq ~ sq ~ Uuq ~�   sq ~ t ~package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = 1;
		i.toString();
	}
}q ~Jsq ~         sq ~ Uuq ~ X   t 5['breder.Test','5','17','i'] : lvalue must be notnullt breder.Testpt notnullq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = null;
		i.toString();
	}
}q ~Tsq ~        Esq ~ Uuq ~ X   t  t breder.Testpt notnullq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = null;
		if (i == null) {} else {i.toString();}
	}
}q ~^sq ~         sq ~ Uuq ~ X   t 5['breder.Test','5','41','i'] : lvalue must be notnullq ~^pt notnullq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = null;
		if (i != null) {} else {i.toString();}
	}
}q ~^sq ~         sq ~ Uuq ~ X   t 5['breder.Test','5','52','i'] : lvalue must be notnullq ~^pt notnullq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = null;
		if (1 == 1 and i != null) {} else {i.toString();}
	}
}q ~^sq ~        7sq ~ Uuq ~ X   t  q ~^pt notnullq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = null;
		if (1 == 1 and i == null) {} else {i.toString();}
	}
}q ~^sq ~         sq ~ Uuq ~ X   t 5['breder.Test','5','52','i'] : lvalue must be notnullq ~^pt notnullq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = null;
		if (1 == 1) {} elseif (i == null) {i.toString();}
	}
}q ~^sq ~        4sq ~ Uuq ~ X   t  q ~^pt notnullq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = null;
		if (1 == 1) {} elseif (i != null) {i.toString();}
	}
}q ~^sq ~        (sq ~ Uuq ~ X   t  q ~^pt notnullq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = null;
		if (1 == 1) {} elseif (1 == 1 and i != null) {i.toString();}
	}
}q ~^sq ~        5sq ~ Uuq ~ X   t  q ~^pt notnullq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = null;
		if (1 == 1) {} elseif (1 == 1 and i != null) {i.toString();} else {}
	}
}q ~^sq ~        .sq ~ Uuq ~ X   t  q ~^pt notnullq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = null;
		if (1 == 1) {} elseif (i != null) {i.toString();} else {}
	}
}q ~^sq ~         sq ~ Uuq ~ X   t 5['breder.Test','5','63','i'] : lvalue must be notnullq ~^pt notnullq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = null;
		if (1 == 1) {} elseif (1 == 1 and i == null) {i.toString();} else {}
	}
}q ~^sq ~         sq ~ Uuq ~ X   t 5['breder.Test','5','82','i'] : lvalue must be notnullq ~^pt notnullq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = null;
		if (1 == 1) {} elseif (2 == 2) {} elseif (1 == 1 and i == null) {i.toString();} else {}
	}
}q ~^sq ~        .sq ~ Uuq ~ X   t  q ~^pt notnullq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = null;
		if (1 == 1) {} elseif (2 == 2) {} elseif (1 == 1 and i != null) {i.toString();} else {}
	}
}q ~^sq ~         sq ~ Uuq ~ X   t 6['breder.Test','5','103','i'] : lvalue must be notnullq ~^pt notnullq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = null;
		if (1 == 1) {} elseif (2 == 2) {} elseif (1 == 1 and i != null) {i.toString();} else {i.toString();}
	}
}q ~^sq ~        +sq ~ Uuq ~ X   t  q ~pt notnullq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = 2;
		if (i == 1 and i == null) {} elseif (2 == 2) {} elseif (1 == 1 and i != null) {i.toString();} else {i.toString();}
	}
}t breder.Testxsq ~         7sq ~ Uuq ~ X   t verificar esse casot breder.Testpt notnullpsq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		String i = "";
		String u = null;
		if (i != u) {
			i.toString();
		}
	}
}t breder.Testxsq ~         sq ~ Uuq ~ X   t F['breder.Test','5','24','='] : primitive operator with lvalue nullableq ~pt notnullq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = null;
		if (i == 1 and i == null) {} elseif (2 == 2) {} elseif (1 == 1 and i != null) {i.toString();} else {i.toString();}
	}
}t breder.Testxsq ~        +sq ~ Uuq ~ X   t  q ~^pt notnull.paramq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test.test(1);
	}
	public static void test(notnull Index i) {}
}q ~^sq ~         sq ~ Uuq ~ X   t >['breder.Test','4','27','null'] : param 'null' must be notnullq ~^pq ~�q ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test.test(null);
	}
	public static void test(notnull Index i) {}
}q ~^sq ~         sq ~ Uuq ~ X   t 8['breder.Test','5','27','i'] : param 'i' must be notnullt breder.Testpq ~�q ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = null;
		Test.test(i);
	}
	public static void test(notnull Index i) {}
}q ~sq ~        +sq ~ Uuq ~ X   t  q ~pq ~�q ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = 1;
		Test.test(i);
	}
	public static void test(notnull Index i) {}
}q ~sq ~         sq ~ Uuq ~ X   t 8['breder.Test','5','27','i'] : param 'i' must be notnullt breder.Testpq ~�q ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = Test.test();
		Test.test(i);
	}
	public static void test(notnull Index i) {}
	public static Index test() {return null;}
}q ~sq ~         sq ~ Uuq ~ X   t ;['breder.Test','8','52','null'] : rvalue 'null' not notnullq ~pq ~�q ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = Test.test();
		Test.test(i);
	}
	public static void test(notnull Index i) {}
	public static notnull Index test() {return null;}
}q ~sq ~        'sq ~ Uuq ~ X   t  q ~pq ~�q ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = Test.test();
		Test.test(i);
	}
	public static void test(notnull Index i) {}
	public static notnull Index test() {return 1;}
}q ~sq ~        3sq ~ Uuq ~ X   t  q ~pq ~�q ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Index i = Test.test();
		Test.test(i);
	}
	public static void test(Index i) {}
	public static notnull Index test() {return 1;}
}q ~sq ~        :sq ~ Uuq ~ X   t  q ~pq ~�q ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test.test(Test.test());
	}
	public static void test(Index i) {}
	public static notnull Index test() {return 1;}
}q ~sq ~        &sq ~ Uuq ~ X   t  q ~pq ~�q ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test.test(Test.test());
	}
	public static void test(notnull Index i) {}
	public static notnull Index test() {return 1;}
}q ~sq ~         sq ~ Uuq ~ X   t >['breder.Test','4','32','test'] : param 'test' must be notnullq ~�pq ~�q ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test.test(Test.test());
	}
	public static void test(notnull Index i) {}
	public static Index test() {return 1;}
}q ~�sq ~        6sq ~ Uuq ~ X   t  q ~�pq ~�q ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test.test();
	}
	public static void test(notnull Index i) {}
	public static notnull Index test() {return 1;}
}q ~�sq ~        >sq ~ Uuq ~ X   t 1q ~�pq ~�q ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(Test.test());
	}
	public static void test(notnull Index i) {}
	public static notnull Index test() {return 1;}
}q ~�sq ~         sq ~ Uuq ~ X   t ;['breder.Test','7','52','null'] : rvalue 'null' not notnullq ~�pq ~�q ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(Test.test());
	}
	public static void test(notnull Index i) {}
	public static notnull Index test() {return null;}
}q ~�sq ~        2sq ~ Uuq ~ X   t nullq ~�pq ~�q ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(Test.test());
	}
	public static void test(notnull Index i) {}
	public static Index test() {return null;}
}q ~�sq ~        ;sq ~    w   
t 1xppt operator.andq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		new Test() and new Test();
	}
	public void operatorAnd(Test t) {
		Console.println(1);
	}
}t breder.Testxsq ~        (sq ~ Uuq ~ X   t 1q ~�pt operator.andq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test t = new Test() and new Test();
	}
	public Test operatorAnd(Test t) {
		Console.println(1);
		return this;
	}
}q ~�sq ~        <sq ~ Uuq ~ X   t trueq ~�pt operator.andq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test t0 = new Test();
		Test t1 = new Test();
		Test t2 = t0 and t1;
		Console.println(t2 == t0);
	}
	public Test operatorAnd(Test t) {
		return this;
	}
}q ~�sq ~        (sq ~ Uuq ~ X   t falseq ~�pt operator.andq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test t0 = new Test();
		Test t1 = new Test();
		Test t2 = t0 and t1;
		Console.println(t2 == t1);
	}
	public Test operatorAnd(Test t) {
		return this;
	}
}q ~�sq ~         sq ~ Uuq ~ X   t 8['breder.Test','6','25','='] : rvalue not return a valuet breder.Testpt operator.andq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test t0 = new Test();
		Test t1 = new Test();
		Test t2 = t0 and t1;
		Console.println(t2 == t1);
	}
	public void operatorAnd(Test t) {
	}
}t breder.Testxsq ~         sq ~ Uuq ~ X   t 9['breder.Test','6','34','t1'] : not found the operatorAndq ~�pt operator.andq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test t0 = new Test();
		Test t1 = new Test();
		Test t2 = t0 and t1;
		Console.println(t2 == t1);
	}
	public Test operatorAnd(Index t) {
		return this;
	}
}q ~�sq ~        <sq ~ Uuq ~ X   t trueq ~�pt operator.andq ~ sq ~ Uuq ~�   sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test t0 = new Test();
		Test t1 = new Test();
		Test t2 = t0 and 1;
		Console.println(t2 == t0);
	}
	public Test operatorAnd(Index t) {
		return this;
	}
}q ~�sq ~        Msq ~ Uuq ~ X   t Indext Testt trueq ~�pt operator.andq ~ sq ~ Uuq ~�   sq ~ t{package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test t0 = new Test();
		Test t1 = new Test();
		Test t2 = t0 and 1;
		Test t3 = t1 and t0;
		Console.println(t3 == t1);
	}
	public Test operatorAnd(Index t) {
		Console.println("Index");
		return this;
	}
	public Test operatorAnd(Test t) {
		Console.println("Test");
		return this;
	}
}q ~�sq ~        3sq ~    w   
t 1xppt operator.divq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		new Test() / new Test();
	}
	public void operatorDiv(Test t) {
		Console.println(1);
	}
}t breder.Testxsq ~        3sq ~ Uuq ~ X   t 0.500000t breder.Testpt operator.divq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Number a = 1/2;
		Console.println(a);
	}
}t breder.Testxsq ~        -sq ~ Uuq ~ X   t 0q ~�pt operator.divq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Natural a = (Natural) (1/2);
		Console.println(a);
	}
}t breder.Testxsq ~        -sq ~ Uuq ~ X   t 0q ~	�pt operator.divq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Natural a = (Natural) (-1/2);
		Console.println(a);
	}
}t breder.Testxsq ~        (sq ~ Uuq ~ X   t 0q ~�pt operator.divq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Natural a = (Natural)((Natural) 1/2);
		Console.println(a);
	}
}t breder.Testxsq ~        %sq ~ Uuq ~ X   t 0q ~�pt operator.divq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Natural a = (Natural) (-1/2);
		Console.println(a);
	}
}t breder.Testxsq ~         sq ~ Uuq ~ X   t f['breder.Test','4','30','/'] : cannot cast breder.lang.standard.Number to breder.lang.standard.Naturalq ~�pt operator.divq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Natural a = 1/2;
		Console.println(a);
	}
}t breder.Testxsq ~         sq ~ Uuq ~ X   t f['breder.Test','4','30','/'] : cannot cast breder.lang.standard.Number to breder.lang.standard.Naturalq ~�pt operator.divq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Natural a = 4/2;
		Console.println(a);
	}
}t breder.Testxsq ~         sq ~ Uuq ~ X   t f['breder.Test','5','29','b'] : cannot cast breder.lang.standard.Number to breder.lang.standard.Naturalq ~�pt operator.divq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Number b = 4/2;
		Natural a = b;
		Console.println(a);
	}
}t breder.Testxsq ~        Isq ~ Uuq ~ X   t 2q ~�pt operator.divq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Natural a = (Natural) (4/2);
		Console.println(a);
	}
}t breder.Testxsq ~        esq ~ Uuq ~ X   t 2throw breder.lang.standard.CastRuntimeException : t @	method () main (breder.util.IList<breder.lang.standard.String>)q ~	�pt operator.divq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Natural a = (Natural) (-4/2);
		Console.println(a);
	}
}t breder.Testxsq ~        ,sq ~ Uuq ~ X   t 2q ~�pt operator.divq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Number b = 4/2;
		Natural a = (Natural) b;
		Console.println(a);
	}
}t breder.Testxsq ~        /sq ~ Uuq ~ X   t 1t breder.Testpt operator.equalq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		new Test() == new Test();
	}
	public notnull Boolean operatorEqual(Test t) {
		Console.println(1);
		return true;
	}
}t breder.Testxsq ~        %sq ~    w   
t 1xppt operator.genericq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t + t;
	}
	public void operatorSum(Test t) {
		Console.println(1);
	}
}t breder.Testxsq ~        Asq ~    w   
t 1xppt operator.genericq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t + 1;
	}
	public void operatorSum(Number t) {
		Console.println(1);
	}
}t breder.Testxsq ~         sq ~    w   
t 8['breder.Test','5','19','+'] : not found the operatorSumxppt operator.genericq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t + 1;
	}
	public void operatorSum(Test t, Number t) {
	}
}t breder.Testxsq ~        *sq ~    w   
t 1xppt operator.genericq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		Test t2 = t + t;
		if(t == t2) {
			Console.println(1);
		}
	}
	public Test operatorSum(Test t) {
		return t;
	}
}t breder.Testxsq ~        Bsq ~ Uuq ~ X   t 1t 4q ~pt operator.genericq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		Console.println(t + t);
		Console.println(2 * (t + t + 1));
	}
	public notnull Number operatorSum(Test t) {
		return 1;
	}
}t breder.Testxsq ~        +sq ~    w   
t 1xppt operator.highq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		new Test() > new Test();
	}
	public void operatorHigh(Test t) {
		Console.println(1);
	}
}t breder.Testxsq ~        4sq ~    w   
t 1xppt operator.highequalq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		new Test() >= new Test();
	}
	public void operatorHighEqual(Test t) {
		Console.println(1);
	}
}t breder.Testxsq ~        0sq ~    w   
t 1xppt operator.lowq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		new Test() < new Test();
	}
	public void operatorLow(Test t) {
		Console.println(1);
	}
}t breder.Testxsq ~        /sq ~    w   
t 1xppt operator.lowequalq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		new Test() <= new Test();
	}
	public void operatorLowEqual(Test t) {
		Console.println(1);
	}
}t breder.Testxsq ~        sq ~    w   
t 1xppt operator.mulq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		new Test() * new Test();
	}
	public void operatorMul(Test t) {
		Console.println(1);
	}
}t breder.Testxsq ~        'sq ~ Uuq ~ X   t 1t 2q ~pt operator.mulq ~ sq ~    w   
sq ~ t-package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		new Test() + new Test() * new Test();
	}
	public notnull Test operatorMul(Test t) {
		Console.println(1);
		return this;
	}
	public notnull Test operatorSum(Test t) {
		Console.println(2);
		return this;
	}
}t breder.Testxsq ~        'sq ~ Uuq ~ X   t 1t 2q ~pt operator.mulq ~ sq ~    w   
sq ~ t-package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		new Test() * new Test() + new Test();
	}
	public notnull Test operatorMul(Test t) {
		Console.println(1);
		return this;
	}
	public notnull Test operatorSum(Test t) {
		Console.println(2);
		return this;
	}
}t breder.Testxsq ~        esq ~ Uuq ~ X   t falset breder.Testpt operator.notq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(!Test.test());
	}
	public static notnull Boolean test() { return true; }
}t breder.Testxsq ~        .sq ~ Uuq ~ X   t 1q ~"pt operator.notequalq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		new Test() != new Test();
	}
	public notnull Boolean operatorNotEqual(Test t) {
		Console.println(1);
		return true;
	}
}t breder.Testxsq ~        7sq ~    w   
t 1xppt operator.orq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		new Test() or new Test();
	}
	public void operatorOr(Test t) {
		Console.println(1);
	}
}t breder.Testxsq ~        6sq ~    w   
t 1xppt operator.subq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		new Test() - new Test();
	}
	public void operatorSub(Test t) {
		Console.println(1);
	}
}t breder.Testxsq ~        Hsq ~ Uuq ~ X   t -1t 0t -2t 1t -1t 1t -2t breder.Testpt operator.subq ~ sq ~    w   
sq ~ tpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(-1);
		Console.println(-1+1);
		Console.println(-1-1);
		Console.println(-1*-1);
		Console.println(-1/1);
		Console.println(-1+2);
		Console.println(-1*2);
	}
}t breder.Testxsq ~        @sq ~ Uuq ~ X   t -1t 0t -2t 1t -1t 1t -2q ~�pt operator.subq ~ sq ~    w   
sq ~ tpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(-1);
		Console.println(1-1);
		Console.println(-1-1);
		Console.println(-1*-1);
		Console.println(1/-1);
		Console.println(2-1);
		Console.println(2*-1);
	}
}t breder.Testxsq ~        $sq ~ Uuq ~ X   t a1t breder.Testpt operator.sumq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println("a"+Test.test(new Test()));
	}
	public static notnull Index test(notnull Test t) { return 1; }
}t breder.Testxsq ~        6sq ~     w   
xppt packageq ~ sq ~    w   
sq ~ t _package breder;
public class Test {
	public static void main(notnull IList<String> args) {
	}
}t breder.Testxsq ~         sq ~    w   
t C['breder.Test','1','14','Test'] : package must be the name 'breder'xppt packageq ~ sq ~    w   
sq ~ t Opublic class Test {
	public static void main(notnull IList<String> args) {
	}
}t breder.Testxsq ~         sq ~    w   
t 5['Test','2','14','Test'] : package must be empty namexppt packageq ~ sq ~    w   
sq ~ t _package breder;
public class Test {
	public static void main(notnull IList<String> args) {
	}
}t Testxsq ~        $sq ~    w   
t 1xppt propertyq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public property Number i;
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t.setI(1);
		Console.println(t.getI());
	}
}t breder.Testxsq ~        +sq ~    w   
t 1xppt propertyq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public property Number i;
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t.setI(1);
		t.getI();
		t.setI(1);
		Console.println(t.getI());
	}
}t breder.Testxsq ~         sq ~    w   
t ;['breder.Test','6','19','setI'] : can not access the methodxppt propertyq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	private property Number i;
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		t.setI(1);
		t.getI();
		t.setI(1);
		Console.println(t.getI());
	}
}t breder.Testxsq ~        :sq ~    w   
t 1xppt propertyq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static property Number i;
	public static void main(notnull IList<String> args) {
		Test.setI(1);
		Test.getI();
		Test.setI(1);
		Console.println(Test.getI());
	}
}t breder.Testxsq ~        6sq ~    w   
t 1t 2xppt propertyq ~ sq ~    w   
sq ~ t,package breder;
public class Test {
	public static property Number i;
	public property Number U;
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		Test.setI(1);
		Test.getI();
		Test.setI(1);
		t.setU(2);
		Console.println(Test.getI());
		Console.println(t.getU());
	}
}t breder.Testxsq ~        <sq ~ Uuq ~ X   t 1t 2t breder.Testpt propertyq ~ sq ~    w   
sq ~ t,package breder;
public class Test {
	public property Number U;
	public static property Number i;
	public static void main(notnull IList<String> args) {
		Test t = new Test();
		Test.setI(1);
		Test.getI();
		Test.setI(1);
		t.setU(2);
		Console.println(Test.getI());
		Console.println(t.getU());
	}
}t breder.Testxsq ~        .sq ~     w   
xppt returnq ~ sq ~    w   
sq ~ tpackage breder;
public class Test {
	public notnull Boolean equal(Object expected, Object value) {
		if (expected == null and value == null) { return true; }
		if (expected == value) { return true; }
		else { return false; }
	}
	public static void main(notnull IList<String> args) {}
}t breder.Testxsq ~        /sq ~     w   
xppt returnq ~ sq ~    w   
sq ~ t$package breder;
public class Test {
	public notnull Boolean equal(Object expected, Object value) {
		if (expected == null and value == null) { return true; }
		if (expected == value) { return true; }
		throw new RuntimeException();
	}
	public static void main(notnull IList<String> args) {}
}t breder.Testxsq ~        .sq ~ Uuq ~ X   t  t breder.Testpt return.voidq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public void test() {
		return;
	}
	public static void main(notnull IList<String> args) {}
}t breder.Testxsq ~        8sq ~ Uuq ~ X   t 5q ~
#pt return.voidq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void test() {
		return;
		Console.print(1);
	}
	public static void main(notnull IList<String> args) {
		Test.test();
		Console.println(5);
	}
}t breder.Testxsq ~        3sq ~ Uuq ~ X   t 0t breder.Testpt return.voidq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void test() {
		Console.print(0);
		return;
		Console.print(1);
	}
	public static void main(notnull IList<String> args) {
		Test.test();
	}
}t breder.Testxsq ~       �sq ~ Uuq ~ X   t  q ~Jpt return.voidq ~ sq ~    w   
sq ~ tpackage breder;
public class Test {
	public static void test() {
		return;
		Console.print(1);
	}
	public static void main(notnull IList<String> args) {
		for (Index i = 1 ; i < 1024 ; i += 1) {
			for (Index u = 1 ; u < 1024 ; u += 1) {
				Test.test();
			}
		}
	}
}t breder.Testxsq ~        /sq ~ Uuq ~ X   	t 1t 2t 3t 4t 5t 6t 7t 8t 9q ~Jpt return.voidq ~ sq ~    w   
sq ~ tbpackage breder;
public class Test {
	public static void test(notnull Index n) {
		return;
		Console.print(1);
	}
	public static void main(notnull IList<String> args) {
		for (Index n = 1 ; n < 10 ; n += 1) {
			Console.println(n);
			for (Index i = 1 ; i < 100 ; i += 1) {
				for (Index u = 1 ; u < 100 ; u += 1) {
					Test.test(n);
				}
			}
		}
	}
}t breder.Testxsq ~       sq ~ Uuq ~ X   	t 1t 2t 3t 4t 5t 6t 7t 8t 9q ~Jpt return.voidq ~ sq ~    w   
sq ~ tdpackage breder;
public class Test {
	public static void test(notnull Index n) {
		return;
		Console.print(1);
	}
	public static void main(notnull IList<String> args) {
		for (Index n = 1 ; n < 10 ; n += 1) {
			Console.println(n);
			for (Index i = 1 ; i < 1024 ; i += 1) {
				for (Index u = 1 ; u < 1024 ; u += 1) {
					Test.test(n);
				}
			}
		}
	}
}t breder.Testxsq ~        (sq ~ Uuq ~ X   t 5q ~
#pt return.voidq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void test(Number a) {
		return;
	}
	public static void main(notnull IList<String> args) {
		Test.test(1);
		Console.println(5);
	}
}t breder.Testxsq ~        (sq ~ Uuq ~ X   t 5q ~
#pt return.voidq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void test(Number a, Number b) {
		return;
	}
	public static void main(notnull IList<String> args) {
		Test.test(1,2);
		Console.println(5);
	}
}t breder.Testxsq ~        7sq ~ Uuq ~ X   t 5q ~
#pt return.voidq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void test(Number a, Number b) {
		Number a = 3;
		Number b = 4;
		return;
	}
	public static void main(notnull IList<String> args) {
		Test.test(1,2);
		Console.println(5);
	}
}t breder.Testxsq ~        /sq ~     w   
xppt stressq ~ sq ~    w   
sq ~ tpackage breder;
public class Test {
	public Test(Test t) {}
	public static void main(notnull IList<String> args) {
		Test t1 = new Test(new Test(null));
		Test t2 = new Test(new Test(new Test(new Test(null))));
		t2.a(new Test(t1)).a(t2);
	}
	public Test a(Test t){
		return t;
	}
}t breder.Testxsq ~        (sq ~     w   
xppt stressq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test2 t1 = new Test2(new Test2(null));
		Test2 t2 = new Test2(new Test2(new Test2(new Test2(null))));
		t2.a(new Test2(t1)).a(t2);
	}
}t breder.Testsq ~ t ipackage breder;
public class Test2 {
	public Test2(Test2 t) {}
	public Test2 a(Test2 t){
		return t;
	}
}t breder.Test2xsq ~         sq ~    w   
t 9['test.Main','6','33','+'] : operator with wrong argumentxppt stressq ~ sq ~    w   
sq ~ t �package test;
import test2.*;
public class Main {
	public static void main(notnull IList<String> args) {
		Test2 t = new Test2();
		t.setI(1); "ddd"+
		Console.println("Hello!");
	}
}t 	test.Mainsq ~ t @package test2;
public class Test2 {
	public property Number i;
}t test2.Test2xsq ~        sq ~    w   
t falset truet 5xppt stressq ~ sq ~    w   
sq ~ tpackage breder;
import breder.util.*;
public class Test {
	public static void main(notnull IList<String> args) {
		Integer i = 1;
		Console.println(i == 200);
		Console.println(i == 1);
		ArrayList<Number> l = new ArrayList<Number>();
		l.add(5);
		Console.println(l.get(1));
	}
}t breder.Testxsq ~        8sq ~ Uuq ~ X   t test1q ~,pt super.methodq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public Test() {
		Console.println(super.toString());
	}
	public static void main(IList<String> args) {
		new Test();
	}
	public String toString() { return "test"; }
}t breder.Testsq ~ t Tpackage breder;
public class Test1 {
	public String toString() { return "test1"; }
}t breder.Test1xsq ~        4sq ~ Uuq ~ X   t 	TestBegint 
Test1Begint Test2t Test1Endt TestEndq ~,pt super.methodq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public static void main(notnull IList<String> args) {
		new Test().test();
	}
	public void test () {
		Console.println("TestBegin");
		super.test();
		Console.println("TestEnd");
	}
}t breder.Testsq ~ t �package breder;
public class Test1 extends Test2 {
	public void test () {
		Console.println("Test1Begin");
		super.test();
		Console.println("Test1End");
	}
}t breder.Test1sq ~ t \package breder;
public class Test2 {
	public void test () {
		Console.println("Test2");
	}
}t breder.Test2xsq ~        sq ~ Uuq ~ X   t testq ~,pt super.methodq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public static void main(notnull IList<String> args) {
		Console.println(new Test().toString());
	}
	public String toString() {
		return super.toString();
	}
}t breder.Testsq ~ t Spackage breder;
public class Test1 {
	public String toString() { return "test"; }
}t breder.Test1xsq ~        .sq ~ Uuq ~ X   t �['breder.Test'] : in the class 'breder.Test', not found the method starter '() main (breder.util.IList<breder.lang.standard.String>)'t breder.Testpt test1q ~ sq ~    w   
sq ~ t %package breder;
public class Test {
}t breder.Testxsq ~        0sq ~     w   
xppt test2q ~ sq ~    w   
sq ~ t _package breder;
public class Test {
	public static void main(notnull IList<String> args) {
	}
}t breder.Testxsq ~        ,sq ~ Uuq ~ X   t  q ~	Hpt test2q ~ sq ~ Uuq ~�   sq ~ t Wpackage breder;
public class Test {
	public static void main(IList<String> args) {
	}
}q ~	Hsq ~       sq ~    w   t  t truet falset -1t 0t 1t 999999999.999999t -999999999.999999t testt testtestt 	testtest1t testtest1testt testtest1testtruet testtest1testtrue1testxppt test3q ~ sq ~    w   
sq ~ t*package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println();
		Console.println(true);
		Console.println(false);
		Console.println(-1);
		Console.println(0);
		Console.println(1);
		Console.println(999999999.999999);
		Console.println(-999999999.999999);
		Console.println("test");
		Console.println("test"+"test");
		Console.println("test"+"test"+1);
		Console.println("test"+"test"+1+"test");
		Console.println("test"+"test"+1+"test"+true);
		Console.println("test"+"test"+1+"test"+true+1+"test");
	}
}t breder.Testxsq ~        'sq ~    	w   
t 0t 0t 1t 2t 4t 24t 14t 20t 5xppt test4q ~ sq ~    w   
sq ~ t@package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(0+0);
		Console.println(1-1);
		Console.println(1*1);
		Console.println(1+1);
		Console.println(2*2);
		Console.println(2*3*4);
		Console.println(2+3*4);
		Console.println((2+3)*4);
		Console.println(10/2);
	}
}t breder.Testxsq ~        5sq ~ Uuq ~ X   t testt testt breder.Testpt thisq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public Test() {
		Console.println(this.toString());
		Console.println(this.toString().toString());
	}
	public static void main(IList<String> args) {
		new Test();
	}
	public String toString() { return "test"; }
}t breder.Testxsq ~        'sq ~ Uuq ~ X   t testt breder.Testpt thisq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public Test() {
		Console.println((String)(this.toString()));
	}
	public static void main(IList<String> args) {
		new Test();
	}
	public String toString() { return "test"; }
}t breder.Testxsq ~        Bsq ~ Uuq ~ X   t  q ~Ppt 	this.typeq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
	}
	public this a () {
		return this;
	}
}t breder.Testxsq ~        5sq ~ Uuq ~ X   t  q ~Ppt 	this.typeq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		Test t = new Test().a();
	}
	public this a () {
		return this;
	}
}t breder.Testxsq ~        5sq ~ Uuq ~ X   t  q ~Ppt 	this.typeq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public static void main(notnull IList<String> args) {
		Test t = new Test().a();
	}
}t breder.Testsq ~ t Lpackage breder;
public class Test1 {
	public this a () {
		return this;
	}
}t breder.Test1xsq ~        -sq ~ Uuq ~ X   t  q ~Ppt 	this.typeq ~ sq ~    w   
sq ~ t �package breder;
public class Test<E> extends Test1<E> {
	public static void main(notnull IList<String> args) {
		Test<Number> t = new Test<Number>().a();
	}
}t breder.Testsq ~ t Opackage breder;
public class Test1<E> {
	public this a () {
		return this;
	}
}t breder.Test1xsq ~        *sq ~ Uuq ~ X   t  q ~Ppt 	this.typeq ~ sq ~    w   
sq ~ t �package breder;
public class Test<E> extends Test1<E> {
	public static void main(notnull IList<String> args) {
		Test<Number> t = new Test<Number>().a();
	}
}t breder.Testsq ~ t Cpackage breder;
public abstract class Test1<E> extends Test2<E> {
}t breder.Test1sq ~ t Opackage breder;
public class Test2<E> {
	public this a () {
		return this;
	}
}t breder.Test2xsq ~         sq ~ Uuq ~ X   t �['breder.Test','4','53','a'] : cannot cast breder.Test1<<E breder.lang.standard.Object>> to breder.Test<breder.lang.standard.Number>t breder.Testpt 	this.typeq ~ sq ~    w   
sq ~ t �package breder;
public class Test<E> extends Test1<E> {
	public static void main(notnull IList<String> args) {
		Test<Number> t = new Test<Number>().a();
		Test<String> tt = new Test<String>().a();
	}
}t breder.Testsq ~ t |package breder;
public abstract class Test1<E> extends Test2<E> {
	public Test1<E> a () {
		return new Test2<Number>();
	}
}t breder.Test1sq ~ t ^package breder;
public class Test2<E> {
	public this a () {
		return new Test2<Number>();
	}
}t breder.Test2xsq ~         sq ~ Uuq ~ X   t D['breder.Test2','4','28','Test'] : can not cast this with other typet breder.Testpt 	this.typeq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public static void main(notnull IList<String> args) {
		Test t = new Test().a();
	}
}t breder.Testsq ~ t ipackage breder;
public abstract class Test1 extends Test2 {
	public Test a () {
		return new Test();
	}
}t breder.Test1sq ~ t Rpackage breder;
public class Test2 {
	public this a () {
		return new Test();
	}
}t breder.Test2xsq ~         sq ~ Uuq ~ X   t E['breder.Test1','4','28','Test2'] : can not cast this with other typeq ~�pt 	this.typeq ~ sq ~    w   
sq ~ t �package breder;
public class Test extends Test1 {
	public static void main(notnull IList<String> args) {
		Test t = new Test().a();
	}
}t breder.Testsq ~ t jpackage breder;
public abstract class Test1 extends Test2 {
	public this a () {
		return new Test2();
	}
}t breder.Test1sq ~ t Spackage breder;
public class Test2 {
	public Test a () {
		return new Test2();
	}
}t breder.Test2xsq ~        0sq ~ Uuq ~ X   t  q ~"pt 	this.typeq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
	}
	public this a () {
		return this;
	}
}t breder.Testsq ~ t >package breder;
public interface Test1 {
	public this a () ;
}t breder.Test1xsq ~         sq ~ Uuq ~ X   t T['breder.Test','5','21','a'] : super class only override a method with super returnsq ~"pt 	this.typeq ~ sq ~    w   
sq ~ t �package breder;
public class Test implements Test1 {
	public static void main(notnull IList<String> args) {
	}
	public Test a () {
		return this;
	}
}t breder.Testsq ~ t >package breder;
public interface Test1 {
	public this a () ;
}t breder.Test1xsq ~        0sq ~ Uuq ~ X   t  t breder.Testpt 	this.typeq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
	}
	public this a () {
		return this.b();
	}
	public this b () {
		return this;
	}
}t breder.Testxsq ~        (sq ~ Uuq ~ X   t #throw breder.lang.standard.Throw : t 	method () Throw ()t @	method () main (breder.util.IList<breder.lang.standard.String>)q ~Fpt throwq ~ sq ~    w   
sq ~ t tpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		throw new Throw();
	}
}t breder.Testxsq ~        7sq ~ Uuq ~ X   t #throw breder.lang.standard.Throw : t 	method () Throw ()t @	method () main (breder.util.IList<breder.lang.standard.String>)q ~Fpt throwq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		throw new Throw();
		Console.println("a");
	}
}t breder.Testxsq ~        Asq ~ Uuq ~ X   t 'throw breder.lang.standard.Exception : t 	method () Throw ()t 	method () Exception ()t 	method () a ()t @	method () main (breder.util.IList<breder.lang.standard.String>)q ~Fpt throwq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void a () throws Exception {
		throw new Exception();
		Console.println("b");
	}
	public static void main(notnull IList<String> args) throws Exception {
		Test.a();
		Console.println("a");
	}
}t breder.Testxsq ~        sq ~ Uuq ~ X   t .throw breder.lang.standard.RuntimeException : t 	method () Throw ()t 	method () Exception ()t 	method () RuntimeException ()t @	method () main (breder.util.IList<breder.lang.standard.String>)t breder.Testpt throwq ~ sq ~    w   
sq ~ t package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		throw new RuntimeException();
	}
}t breder.Testxsq ~        isq ~ Uuq ~ X   t C['breder.Test','4','27','CompileException'] : exception not checkedq ~�pt throwq ~ sq ~    w   
sq ~ t package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		throw new CompileException();
	}
}t breder.Testxsq ~        3sq ~    w   
t 1xppt throwq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		try {
			throw new Exception();
		} catch ( Exception e ) {
			Console.println(1);
		}
	}
}t breder.Testxsq ~        <sq ~ Uuq ~ X   t 'throw breder.lang.standard.Exception : t 	method () Throw ()t 	method () Exception ()t 	method () d ()t 	method () c ()t 	method () b ()t 	method () a ()t @	method () main (breder.util.IList<breder.lang.standard.String>)q ~Fpt throwq ~ sq ~    w   
sq ~ tNpackage breder;
import breder.util.*;
public class Test {
	public static void main(notnull IList<String> args) throws Exception {
		Test.a();
	}
	public static void a () throws Exception {
		try {
			Test.b();
		} catch ( IOException e ) {
			Console.println();
		}
	}
	public static void b () throws Exception {
		try {
			Test.c();
		} catch ( IOException e ) {
			Console.println();
		}
	}
	public static void c () throws Exception {
		Test.d();
	}
	public static void d () throws Exception {
		try {
			throw new Exception();
		} catch ( IOException e ) {
			Console.println();
		}
	}
}t breder.Testxsq ~        'sq ~ Uuq ~ X   
t 1t 3t 6t 9t 11t 13t 10t 7t 4t 2t breder.Testpt throwq ~ sq ~ Uuq ~�   sq ~ t�package breder;
import breder.util.*;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		Test.a();
		Console.println(2);
	}
	public static void a () {
		try {
			Console.println(3);
			Test.b();
			Console.println(4);
		} catch ( IOException e ) {
			Console.println(5);
		}
	}
	public static void b () {
		try {
			Console.println(6);
			Test.c();
			Console.println(7);
		} catch ( IOException e ) {
			Console.println(8);
		}
	}
	public static void c () {
		Console.println(9);
		Test.d();
		Console.println(10);
	}
	public static void d () {
		try {
			Console.println(11);
			throw new Exception();
			Console.println(12);
		} catch ( Exception e ) {
			Console.println(13);
		}
	}
}q ~$sq ~        $sq ~ Uuq ~ X   t 1t 3t 6t 9t 11t 8t 4t 2t breder.Testpt throwq ~ sq ~ Uuq ~�   sq ~ tpackage breder;
import breder.util.*;
public class Test {
	public static void main(notnull IList<String> args) {
		Console.println(1);
		Test.a();
		Console.println(2);
	}
	public static void a () {
		try {
			Console.println(3);
			Test.b();
			Console.println(4);
		} catch ( IOException e ) {
			Console.println(5);
		}
	}
	public static void b () {
		try {
			Console.println(6);
			Test.c();
			Console.println(7);
		} catch ( Exception e ) {
			Console.println(8);
		}
	}
	public static void c () throws Exception {
		Console.println(9);
		Test.d();
		Console.println(10);
	}
	public static void d () throws Exception {
		try {
			Console.println(11);
			throw new Exception();
			Console.println(12);
		} catch ( IOException e ) {
			Console.println(13);
		}
	}
}q ~5sq ~        /sq ~ Uuq ~ X   t 1t 3t 6t 9t 11t 'throw breder.lang.standard.Exception : t 	method () Throw ()t 	method () Exception ()t 	method () d ()t 	method () c ()t 	method () b ()t 	method () a ()t @	method () main (breder.util.IList<breder.lang.standard.String>)q ~Fpt throwq ~ sq ~    w   
sq ~ t7package breder;
import breder.util.*;
public class Test {
	public static void main(notnull IList<String> args) throws Exception {
		Console.println(1);
		Test.a();
		Console.println(2);
	}
	public static void a () throws Exception {
		try {
			Console.println(3);
			Test.b();
			Console.println(4);
		} catch ( IOException e ) {
			Console.println(5);
		}
	}
	public static void b () throws Exception {
		try {
			Console.println(6);
			Test.c();
			Console.println(7);
		} catch ( IOException e ) {
			Console.println(8);
		}
	}
	public static void c () throws Exception {
		Console.println(9);
		Test.d();
		Console.println(10);
	}
	public static void d () throws Exception {
		try {
			Console.println(11);
			throw new Exception();
			Console.println(12);
		} catch ( IOException e ) {
			Console.println(13);
		}
	}
}t breder.Testxsq ~        Esq ~ Uuq ~ X   t  t breder.Testpt throwq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		try {
			new ArrayList<Index>().get(1);
		} catch ( Throw e ) {
		}
	}
}t breder.Testxsq ~        &sq ~ Uuq ~ X   t 'throw breder.lang.standard.Exception : t 	method () Throw ()t 	method () Exception ()t @	method () main (breder.util.IList<breder.lang.standard.String>)q ~�pt throwq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		try {
			throw new Exception();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
}t breder.Testxsq ~        (sq ~ Uuq ~ X   t 1q ~�pt throwq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		try {
			throw new CompileException();
		} catch ( CompileException e ) {
			Console.println(1);
		}
	}
}t breder.Testxsq ~        1sq ~ Uuq ~ X   t .throw breder.lang.standard.CompileException : t 	method () Throw ()t 	method () Exception ()t 	method () CompileException ()t @	method () main (breder.util.IList<breder.lang.standard.String>)q ~�pt throwq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		try {
			throw new CompileException();
		} catch ( CompileException e ) {
			e.printStackTrace();
		}
	}
}t breder.Testxsq ~        2sq ~ Uuq ~ X   t .throw breder.lang.standard.CompileException : t 	method () Throw ()t 	method () Exception ()t 	method () CompileException ()t @	method () main (breder.util.IList<breder.lang.standard.String>)t .throw breder.lang.standard.CompileException : t 	method () Throw ()t 	method () Exception ()t 	method () CompileException ()t @	method () main (breder.util.IList<breder.lang.standard.String>)t .throw breder.lang.standard.CompileException : t 	method () Throw ()t 	method () Exception ()t 	method () CompileException ()t @	method () main (breder.util.IList<breder.lang.standard.String>)t .throw breder.lang.standard.CompileException : t 	method () Throw ()t 	method () Exception ()t 	method () CompileException ()t @	method () main (breder.util.IList<breder.lang.standard.String>)q ~�pt throwq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for (Index i = 1 ; i <= 4 ; i += 1) {
			try {
				throw new CompileException();
			} catch ( CompileException e ) {
				e.printStackTrace();
			}
		}
	}
}t breder.Testxsq ~       Rsq ~ Uuq ~ X   t  q ~Tpt throw.stressq ~ sq ~    w   
sq ~ tpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for (Index i = 1 ; i < 1024 ; i += 1) {
			for (Index u = 1 ; u < 1024 ; u += 1) {
				try {
					throw new Exception();
				} catch ( Exception e ) {
				}
			}
		}
	}
}t breder.Testxsq ~       sq ~ Uuq ~ X   t  q ~Tpt throw.stressq ~ sq ~    w   
sq ~ tpackage breder;
public class Test {
	public static void main(notnull IList<String> args) {
		for (Index i = 1 ; i < 1024 ; i += 1) {
			for (Index u = 1 ; u < 1024 ; u += 1) {
				try {
					new ArrayList<Index>().get(1);
				} catch ( Throw e ) {
				}
			}
		}
	}
}t breder.Testxsq ~        Csq ~ Uuq ~ X   t g['breder.Test','5','22','a'] : the method has exception of type 'breder.lang.standard.CompileException'q ~�pt throwsq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void a () throws CompileException {}
	public static void main(notnull IList<String> args) {
		Test.a();
	}
}t breder.Testxsq ~        0sq ~     w   
xppt throwsq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void a () throws RuntimeException {}
	public static void main(notnull IList<String> args) {
		Test.a();
	}
}t breder.Testxsq ~        1sq ~     w   
xppt throwsq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void a () throws Throw {}
	public static void main(notnull IList<String> args) {
		Test.a();
	}
}t breder.Testxsq ~        ?sq ~     w   
xppt throwsq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void a () throws Exception {}
	public static void main(notnull IList<String> args) throws Exception {
		Test.a();
	}
}t breder.Testxsq ~        Isq ~ Uuq ~ X   t g['breder.Test','5','22','a'] : the method has exception of type 'breder.lang.standard.CompileException'q ~�pt throwsq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void a () throws CompileException {}
	public static void main(notnull IList<String> args) throws RuntimeException {
		Test.a();
	}
}t breder.Testxsq ~        'sq ~     w   
xppt throwsq ~ sq ~    w   
sq ~ t �package breder;
public class Test {
	public static void a () throws Exception {}
	public static void main(notnull IList<String> args) {
	}
}t breder.Testxx