<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="header.jsp"></c:import>

<ul>
	<li><h2>breder.lang</h2>
	    <ul>
		    <li><h2><a href="#BREDER_LANG_OBJECT">breder.lang.Object</a></h2></li>
			<li><h2><a href="#BREDER_LANG_STRING">breder.lang.String</a></h2></li>
			<li><h2><a href="#BREDER_LANG_BOOLEAN">breder.lang.Boolean</a></h2></li>
			<li><h2><a href="#BREDER_LANG_NUMBER">breder.lang.Number</a></h2></li>
			<li><h2><a href="#BREDER_LANG_INTEGER">breder.lang.Integer</a></h2></li>
			<li><h2><a href="#BREDER_LANG_NATURAL">breder.lang.Natural</a></h2></li>
			<li><h2><a href="#BREDER_LANG_INDEX">breder.lang.Index</a></h2></li>
			<li><h2><a href="#BREDER_LANG_CONSOLE">breder.lang.Console</a></h2></li>
			<li><h2><a href="#BREDER_LANG_MATH">breder.lang.Math</a></h2></li>
	    </ul>
    </li>
    <li><h2>breder.util</h2>
	    <ul>
			<li><h2><a href="#BREDER_UTIL_ARRAY">breder.util.IArray</a></h2></li>
			<li><h2><a href="#BREDER_UTIL_LIST">breder.util.IList</a></h2></li>
			<li><h2><a href="#BREDER_UTIL_MAP">breder.util.IMap</a></h2></li>
			<li><h2><a href="#BREDER_UTIL_STRINGBUILDER">breder.util.IStringBuilder</a></h2></li>
	    </ul>
    </li>
    <li><h2>breder.io</h2>
	    <ul>
			<li><h2><a href="#BREDER_IO_FILESYSTEM">breder.io.IFileSystem</a></h2></li>
			<li><h2><a href="#BREDER_IO_RESOURCE">breder.io.IResource</a></h2></li>
		    <li><h2><a href="#BREDER_IO_FILE">breder.io.IFile</a></h2></li>
		    <li><h2><a href="#BREDER_IO_FOLDER">breder.io.IFolder</a></h2></li>
		    <li><h2><a href="#BREDER_IO_IOCLOSEABLE">breder.io.IIOCloseable</a></h2></li>
		    <li><h2><a href="#BREDER_IO_STREAM">breder.io.IStream</a></h2></li>
		    <li><h2><a href="#BREDER_IO_INPUTSTREAM">breder.io.IInputStream</a></h2></li>
		    <li><h2><a href="#BREDER_IO_OUTPUTSTREAM">breder.io.IOutputStream</a></h2></li>
	    </ul>
    </li>
    <li><h2>breder.net</h2>
	    <ul>
		    <li><h2><a href="#BREDER_NET_SERVER">breder.net.IServerSocket</a></h2></li>
		    <li><h2><a href="#BREDER_NET_CLIENT">breder.net.IClientSocket</a></h2></li>
		    <li><h2><a href="#BREDER_NET_SOCKET">breder.net.ISocket</a></h2></li>
	    </ul>
    </li>
    <li><h2>breder.sql</h2>
	    <ul>
		    <li><h2><a href="#BREDER_SQL_CONNECTION">breder.sql.ISqlConnection</a></h2></li>
		    <li><h2><a href="#BREDER_SQL_TRANSACTION">breder.sql.ISqlTransaction</a></h2></li>
		    <li><h2><a href="#BREDER_SQL_PRIMITIVE">breder.sql.ISqlPrimitive</a></h2></li>
	    </ul>
    </li>
</ul>
<p><hr></p>

public <b></b> class <a name="BREDER_LANG_OBJECT"><b>Object</b></a>
<p><c:import url="api/lang/object.jsp"></c:import></p>
<p><hr></p>

public <b></b> class <a name="BREDER_LANG_STRING"><b>String</b></a> implements <a href="#BREDER_SQL_PRIMITIVE"><b>ISqlPrimitive</b></a>
<p><c:import url="api/lang/string.jsp"></c:import></p>
<p><hr></p>

public <b></b> class <a name="BREDER_LANG_BOOLEAN"><b>Boolean</b></a> implements <a href="#BREDER_SQL_PRIMITIVE"><b>ISqlPrimitive</b></a>
<p><c:import url="api/lang/boolean.jsp"></c:import></p>
<p><hr></p>

public <b></b> class <a name="BREDER_LANG_NUMBER"><b>Number</b></a> implements <a href="#BREDER_SQL_PRIMITIVE"><b>ISqlPrimitive</b></a>
<p><c:import url="api/lang/number.jsp"></c:import></p>
<p><hr></p>

public <b></b> class <a name="BREDER_LANG_INTEGER"><b>Integer</b></a> extends <a href="#BREDER_LANG_NUMBER"><b>Number</b></a>
<p><c:import url="api/lang/integer.jsp"></c:import></p>
<p><hr></p>

public <b></b> class <a name="BREDER_LANG_NATURAL"><b>Natural</b></a> extends <a href="#BREDER_LANG_INTEGER"><b>Integer</b></a>
<p><c:import url="api/lang/natural.jsp"></c:import></p>
<p><hr></p>

public <b></b> class <a name="BREDER_LANG_INDEX"><b>Index</b></a> extends <a href="#BREDER_LANG_NATURAL"><b>Natural</b></a>
<p><c:import url="api/lang/index.jsp"></c:import></p>
<p><hr></p>

public <b>abstract final</b> class <a name="BREDER_LANG_CONSOLE"><b>Console</b></a> 
<p><c:import url="api/lang/console.jsp"></c:import></p>
<p><hr></p>

public <b>abstract final</b> class <a name="BREDER_LANG_MATH"><b>Math</b></a> 
<p><c:import url="api/lang/math.jsp"></c:import></p>
<p><hr></p>

public interface <a name="BREDER_UTIL_ARRAY"><b>IArray</b></a>
<p><c:import url="api/util/array.jsp"></c:import></p>
<p><hr></p>

public interface <a name="BREDER_UTIL_LIST"><b>IList</b></a> extends <a href="#BREDER_UTIL_ARRAY"><b>IArray</b></a>
<p><c:import url="api/util/list.jsp"></c:import></p>
<p><hr></p>

public interface <a name="BREDER_UTIL_MAP"><b>IMap</b></a>
<p><c:import url="api/util/map.jsp"></c:import></p>
<p><hr></p>

public interface <a name="BREDER_UTIL_STRINGBUILDER"><b>IStringBuilder</b></a>
<p><c:import url="api/util/stringbuilder.jsp"></c:import></p>
<p><hr></p>

public interface <a name="BREDER_IO_FILESYSTEM"><b>IFileSystem</b></a> extends <a href="#BREDER_IO_FOLDER"><b>IFolder</b></a>
<p><c:import url="api/io/filesystem.jsp"></c:import></p>
<p><hr></p>

public interface <a name="BREDER_IO_RESOURCE"><b>IResource</b></a>
<p><c:import url="api/io/resource.jsp"></c:import></p>
<p><hr></p>

public interface <a name="BREDER_IO_FILE"><b>IFile</b></a> extends <a href="#BREDER_IO_RESOURCE"><b>IResource</b></a>
<p><c:import url="api/io/file.jsp"></c:import></p>
<p><hr></p>

public interface <a name="BREDER_IO_FOLDER"><b>IFolder</b></a> extends <a href="#BREDER_IO_RESOURCE"><b>IResource</b></a>
<p><c:import url="api/io/folder.jsp"></c:import></p>
<p><hr></p>

public interface <a name="BREDER_IO_IOCLOSEABLE"><b>IIOCloseable</b></a>
<p><c:import url="api/io/iocloseable.jsp"></c:import></p>
<p><hr></p>

public interface <a name="BREDER_IO_STREAM"><b>IStream</b></a> extends <a href="#BREDER_IO_IOCLOSEABLE"><b>IIOCloseable</b></a>, <a href="#BREDER_SQL_PRIMITIVE"><b>ISqlPrimitive</b></a>
<p><c:import url="api/io/stream.jsp"></c:import></p>
<p><hr></p>

public interface <a name="BREDER_IO_INPUTSTREAM"><b>IInputStream</b></a> extends <a href="#BREDER_IO_STREAM"><b>IStream</b></a>
<p><c:import url="api/io/inputstream.jsp"></c:import></p>
<p><hr></p>

public interface <a name="BREDER_IO_OUTPUTSTREAM"><b>IOutputStream</b></a> extends <a href="#BREDER_IO_STREAM"><b>IStream</b></a>
<p><c:import url="api/io/outputstream.jsp"></c:import></p>
<p><hr></p>

public interface <a name="BREDER_NET_SERVER"><b>IServerSocket</b></a>
<p><c:import url="api/net/server.jsp"></c:import></p>
<p><hr></p>

public interface <a name="BREDER_NET_CLIENT"><b>IClientSocket</b></a> extends <a href="#BREDER_NET_SOCKET"><b>ISocket</b></a>
<p><c:import url="api/net/client.jsp"></c:import></p>
<p><hr></p>

public interface <a name="BREDER_NET_SOCKET"><b>ISocket</b></a> extends <a href="#BREDER_IO_INPUTSTREAM"><b>IInputStream</b></a>, <a href="#BREDER_IO_OUTPUTSTREAM"><b>IOutputStream</b></a>
<p><c:import url="api/net/socket.jsp"></c:import></p>
<p><hr></p>

public interface <a name="BREDER_SQL_CONNECTION"><b>ISqlConnection</b></a>
<p><c:import url="api/sql/connection.jsp"></c:import></p>
<p><hr></p>

public interface <a name="BREDER_SQL_TRANSACTION"><b>ISqlTransaction</b></a>
<p><c:import url="api/sql/transaction.jsp"></c:import></p>
<p><hr></p>

public interface <a name="BREDER_SQL_PRIMITIVE"><b>ISqlPrimitive</b></a>
<p><c:import url="api/sql/primitive.jsp"></c:import></p>
<p><hr></p>

<c:import url="tail.jsp"></c:import>