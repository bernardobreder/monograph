<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Source : breder.test.TestServer
</p>
<dl><dt>
<pre>
package breder.test;

import breder.net.standard.*;

public class TestServer {

    public static void main(notnull IList&lt;String&gt; args) throws Exception {
        IServerSocket server = new BServerSocket(5555); 
        ISocket socket = server.accept();
        Console.println(socket.read(1024));
        socket.write("Hello from Server!");
        socket.close();
        server.close();
    }
    
}
</pre></dt></dl>

<p>
Source : breder.test.TestClient
</p>
<dl><dt>
<pre>
package breder.test;

import breder.net.standard.*;

public class TestClient {

    public static void main(notnull IList&lt;String&gt; args) throws Exception {
        ISocket socket = new BClientSocket("localhost", 5555);
        socket.write("Hello from Client!");
        Console.println(socket.read(1024));
        socket.close();
    }
    
}
</pre></dt></dl>