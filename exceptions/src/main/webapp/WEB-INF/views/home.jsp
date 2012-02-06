<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Some Exceptional Code  
</h1>

<p>  <a href="ioexception">Simple IOException Using Single Exception Handler</a>  </p>

<h2> Exception Handler for an Array of Exception Classes</h2>
<p>  <a href="my404">404 Not found</a>  </p>
<p>  <a href="nullpointer">Throw a NullPointerException</a>  </p>

<h2> Exception Thrown by a different controller </h2>
<p>  <a href="anotherioexception">Throw another IOException using a different controller</a>  </p>

<h2> Change the ResponseStatus </h2>
<p>  <a href="dataformat">Throw a DataFormatException...</a>  </p>

</body>
</html>
