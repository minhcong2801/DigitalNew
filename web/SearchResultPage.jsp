<%@page import="javax.naming.Context"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Search</title>
        <link href="./css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <div class="content">
                <div class="main">
                    <c:if test="${not empty errorMessage}"><p>${errorMessage}</p></c:if>
                    <c:if test="${empty errorMessage}">
                    <div>
                        <c:forEach items="${list}" var="x">
                            <div class="item-wrapper">
                                <div class="tittle">
                                    <a href="DetailControl?id=${x.id}">      
                                        ${x.title}
                                    </a>
                                </div>
                                <div class="search-content" >
                                    <div class="image_search">
                                        <img src="${x.image}" alt=""/>
                                    </div>
                                    <div class="text_search">
                                        ${x.shortDes}
                                    </div>
                                </div>
                            </div>
                            <hr/>
                        </c:forEach>
                    </div>
                    <div class="paging">
                        <c:if test="${maxPage < 1}">
                            <h3>Not Found !!</h3>
                        </c:if>
                        <c:if test="${maxPage > 1}">
                            <c:forEach begin="1" end="${maxPage}" var="i">
                                <c:if test="${i == index}"><span class="active">${i}</span></c:if>
                                <c:if test="${i!=index}">
                                    <a href="SearchControl?index=${i}&txtSearch=${txt}">${i}</a>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </div>
                    </c:if>
                </div> 
                <jsp:include page="Right.jsp"/> 
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>

    </body>
</html>
