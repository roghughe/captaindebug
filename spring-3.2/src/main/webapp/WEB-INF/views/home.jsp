<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Spring 3.2 Demo Home Page 
</h1>

<P>  The time on the server is ${serverTime}. </P>

<h2>@ControlerAdvice</h2>
<ul>
<li><a href="userdetails">Get My Credit Card Details (and throw an IOException)</a></li>
<li><a href="useraddress">Get My Address (and throw an IOException)</a></li>
</ul>

<h2>Matrix Variables</h2>
<ul>
<li><a href="stocks/BT.A=276.70,+10.40,+3.91;AZN=3,236.00,+103.00,+3.29;SBRY=375.50,+7.60,+2.07">Stocks -- BT.A=276.70,+10.40,+3.91;AZN=3,236.00,+103.00,+3.29;SBRY=375.50,+7.60,+2.07</a></li>
</ul>

</body>
</html>
