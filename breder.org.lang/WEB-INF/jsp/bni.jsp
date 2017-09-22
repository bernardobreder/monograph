<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="header.jsp">
</c:import>
<ul>
	<li>
		<h2>
		<a href="#API">
		Api do Breder Native Interface</h2>
	</a>
	</li>
	<li>
		<h2><a href="#BREDER_NET_STANDART">breder.net.standard
				<ul>
					<li>
						<h2><a href="#BREDER_NET_STANDART_C_LIBRARY">C Ansi Library</a></h2>
					</li>
					<li>
						<h2><a href="#BREDER_NET_STANDART_C_TEST">C Ansi Test</a></h2>
					</li>
					<li>
						<h2><a href="#BREDER_NET_STANDART_B_API">Breder Api</a></h2>
					</li>
					<li>
						<h2><a href="#BREDER_NET_STANDART_B_BNI">Breder Bni</a></h2>
					</li>
					<li>
						<h2><a href="#BREDER_NET_STANDART_B_TEST">Breder Test</a></h2>
					</li>
				</ul>
			</a></h2>
	</li>
</ul>
<p>
	<hr>
</p>
<a name="BREDER_NET_STANDART_C_LIBRARY"><b>Api do Breder Native Interface</b></a>
<p>
	<c:import url="bni/api.jsp">
	</c:import>
</p>
<p>
	<hr>
</p>
<a name="BREDER_NET_STANDART_C_LIBRARY"><b>breder.net.standard - C Ansi Library</b></a>
<p>
	<c:import url="bni/net/standard/clibrary.jsp">
	</c:import>
</p>
<p>
	<hr>
</p>
<a name="BREDER_NET_STANDART_C_TEST"><b>breder.net.standard - C Ansi Test</b></a>
<p>
	<c:import url="bni/net/standard/ctest.jsp">
	</c:import>
</p>
<p>
	<hr>
</p>
<a name="BREDER_NET_STANDART_B_API"><b>breder.net.standard - Breder Api</b></a>
<p>
	<c:import url="bni/net/standard/bapi.jsp">
	</c:import>
</p>
<p>
	<hr>
</p>
<a name="BREDER_NET_STANDART_B_BNI"><b>breder.net.standard - Breder Bni</b></a>
<p>
	<c:import url="bni/net/standard/bbni.jsp">
	</c:import>
</p>
<p>
	<hr>
</p>
<a name="BREDER_NET_STANDART_B_TEST"><b>breder.net.standard - Breder Test</b></a>
<p>
	<c:import url="bni/net/standard/btest.jsp">
	</c:import>
</p>
<p>
	<hr>
</p>
<c:import url="tail.jsp">
</c:import>
