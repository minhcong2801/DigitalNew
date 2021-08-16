

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Right</title>
            <link href="./css/right.css" rel="stylesheet" type="text/css" />
        </head>
        <body>
            <div class="right">
                <div class="newst">
                    <div class="titleNews">
                        <span>Digital News</span>
                    </div>
                    <div class="contentNews">
                        <c:if  test="${not empty top1error}">
                            ${top1error}
                        </c:if>
                        <c:if  test="${empty top1error}">
                            ${top1.shortDes}
                        </c:if>
                    </div>
                </div>
                <div class="newst">
                    <div class="titleNews">
                        Search
                    </div>
                    <form action="SearchControl" method="post">
                        <input class="searchBox" type="text" name="txtSearch" size="15"  required>
                        <input class="searchButton" type="submit" name="btnGo" value="Go">
                    </form>                        
                </div>
                <div class="newst">
                    <div class="titleNews">
                        <span>Last Articles</span><br>
                    </div>
                    <c:if  test="${not empty top5error}">
                        ${top5error}
                    </c:if>
                    <c:if  test="${empty top5error}">
                        <c:forEach items="${top5}" var="i">
                            <div class="lastArticles">
                                <a href="DetailControl?id=${i.id}">${i.title}</a>
                            </div>
                        </c:forEach>
                    </c:if>
                
            </div>
        </div>    
    </body>
</html>
