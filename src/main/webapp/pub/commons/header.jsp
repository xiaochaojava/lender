<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<div style="float: left">
	<img src="${pageContext.request.contextPath}/pub/images/banner.png" style="height:84px"/>
	</div>
	<div style="float: left;margin-top: 50px;margin-left: 400px;font-size:17px; ">
	<span style="color: #666633;">
	<a>欢迎<c:out value="${sessionScope._CURRENT_USER.username}"></c:out>
	</a>
	</span>
	<span style="color: #666633;">
	<a href="logout.html">[退出]</a>
	</span>
	</div>
</div>