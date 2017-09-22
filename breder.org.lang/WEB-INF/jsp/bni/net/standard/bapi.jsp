<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Source : breder.net.standard.BServerSocket
</p>
<dl><dt>
<pre>
package breder.net.standard;
public class BServerSocket implements IServerSocket {
    public native BServerSocket (notnull Natural port) throws IOException ;
    public native notnull ISocket accept () throws IOException ;
}
</pre></dt></dl>

<p>
Source : breder.net.standard.BSocket
</p>
<dl><dt>
<pre>
package breder.net.standard;
public class BSocket implements ISocket {
    private BSocket () {}
    public native notnull String read (notnull Natural length) throws IOException ;
    public native notnull Natural write (notnull String content) throws IOException ;
    public native void close () throws IOException ;
}
</pre></dt></dl>

<p>
Source : breder.net.standard.BClientSocket
</p>
<dl><dt>
<pre>
package breder.net.standard;
public class BClientSocket extends BSocket implements IClientSocket {
    public native BClientSocket (notnull String hostname, notnull Natural port) throws IOException ;
}
</pre></dt></dl>