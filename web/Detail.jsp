<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Home Page</title>
            <link href="./css/style.css" rel="stylesheet" type="text/css" />
        </head>
        <body> 
            <div class="container">
            <jsp:include page="Header.jsp"/>
            <div class="content">
                <div class="main">
                    <c:if test="${not empty iderror}">
                        <div class="tittle">
                            ${iderror}
                        </div>
                    </c:if>
                    <c:if test="${empty iderror}">
                        <div class="tittle">
                            ${one.title}
                        </div>
                        <div class="image">
                            <img src="${imagePath}${one.image}"/>
                        </div>
                        <div class="text">
                            ${one.description}
                        </div>
                        <div class="signature">
                            <div class="icon1"></div>
                            <div class="icon2"></div>
                           By ${one.author} | ${one.dateConvert}
                        </div>
                    </c:if>
                </div>
                <jsp:include page="Right.jsp"/> 
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
