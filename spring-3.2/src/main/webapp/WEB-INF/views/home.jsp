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

<li><a href="matrixvars/stocks;BT.A=276.70,+10.40,+3.91;AZN=236.00,+103.00,+3.29;SBRY=375.50,+7.60,+2.07">Stocks -- BT.A=276.70,+10.40,+3.91;AZN=236.00,+103.00,+3.29;SBRY=375.50,+7.60,+2.07</a></li>
<li><a href="matrixvars/stocks;BT.A=276.70,+10.40,+3.91;AZN=236.00,,+3.29;SBRY=375.50,,">Stocks -- BT.A=276.70,+10.40,+3.91;AZN=236.00,,+3.29;SBRY=375.50,,</a></li>
<li><a href="matrixvars/stocks;BT.A=276.70,,+3.91;AZN=236.00,+103.00,+4.05;SBRY=375.50/account;name=roger;number=105;location=stoke-on-trent">Stocks -- BT.A=276.70,+10.40,+3.91;AZN=236.00,+103.00,+3.29;SBRY=375.50,+7.60,+2.07 and account info</a></li>
</ul>

</body>
</html>
