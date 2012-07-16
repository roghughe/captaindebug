<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Facebook Home Feed</title>
</head>
<body>
	<h3>Some of my Facebook Posts</h3>
	<ul>
	<c:forEach items="${posts}" var="post">
		<li><img src="${post.icon}" align="middle"/><br/>
		Reference Name:  <c:out value="${post.from.name}"/><br/>
		Reference ID:  <c:out value="${post.from.id}"/><br/>
		ID:  <c:out value="${post.id}"/><br/>
		Message: <c:out value="${post.message}" escapeXml="false"/><br/>
		<c:out value="${post.createdTime}"/><br/>
		<img src="${post.picture}" align="middle"/>
		<br/>Likes: <c:out value="${post.likeCount}"/>
		<br/><a href="${post.link}" target="new">Post Link</a><br/>
		<br/>Description: <c:out value="${post.description}"/>
		<hr />
		</li>
	</c:forEach>
	</ul>	

</body>
</html>