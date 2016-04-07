<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Show User Information</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Auction Junk Shop</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/stylesheet.css" />
        <script src="${pageContext.request.contextPath}/resources/js/javascript.js"></script>

    </head>

    <body>


    <nav>      
        <div class="right">
            <input type="submit" value="Home" onclick="location.href = '${pageContext.request.contextPath}/homePage/view.all'"/>
            <input type="submit" value="Logout"onclick="location.href = '${pageContext.request.contextPath}/'"/>
        </div>
    </nav>

    <c:if test="${empty userList}">
        There are currently no persons in the list.
    </c:if>

    <c:if test="${empty weatherLeuven}">
        There are currently no weather in the list.
    </c:if>

    <div class="panes">
        <div class="pane left">

            <h4>List all the Users!!!</h4>
            <div class="table">
                <table class="order-table table" style="width: 535px;">
                    <thead>
                        <tr>
                            <th colspan="4">All User List</th>
                        </tr>

                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${userList}" var="userList">
                            <tr>
                                <td>${userList.getFirstName()}</td>
                                <td>${userList.getLastName()}</td>
                                <td style="text-align: left">${userList.getEmail()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>

        <div class="pane right" >
            <div id="demo"></div>
        </div>
    </div>    






    






<script>
        var text =${weatherLeuven};
        //alert(text);
        console.log(text.coord.lon);
        document.getElementById("demo").innerHTML = "<h4>Weather in Leuven:</h4><ul><li>Current temperature:"+Math.round((text.main.temp-273))+"C</li><li>Humidity:"+text.main.humidity+"%</li><li>Wind Speed:"+text.wind.speed+"km/h</li></ul>" 
                                                       
</script>

</body>



</html>
