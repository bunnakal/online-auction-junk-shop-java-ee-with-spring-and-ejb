<%-- 
    Document   : register
    Created on : May 10, 2015, 3:04:34 PM
    Author     : ubuntu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register login account</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Auction Junk Shop</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/stylesheet.css" />
        <script src="${pageContext.request.contextPath}/resources/js/javascript.js"></script>
        <style>
            .error {
                color: red; 
                font-weight: normal;
                font-size: 12pt;   
            }
            span{
                margin-left: 25px;
                float: left;
            }
        </style>
    </head>
    <body>
        <nav>
            <div class="left">
                <h4>Online Auction Junk Shop!</h4>
            </div>
        </nav>
        <div class="panes">
            <div class="pane left">
                <form action="${pageContext.request.contextPath}/login" method="POST">
                    <h4>Already have a login account</h4><br/>
                    <label>Email</label><br/>
                    <span class="errorLogin">${logedStatus}</span>
                    <input type="text" name="email" required placeholder="Enter email address..."/><br/>
                    <label>Password</label><br/>
                    <input type="password" name="password" required placeholder="Enter password..."/><br/>
                    <input type="submit" class="login" name="login" value="Login"/>
                </form>
            </div>
            
            <div class="pane right" >
                <form:form  action="${pageContext.request.contextPath}/" modelAttribute="user" method="POST" enctype="multipart/form-data">
                    <h4>Register a new login account</h4>
                    <label>Name</label><br/>
                    <form:input type="text" path="firstName" id="firstName" class="name" name="firstName"  placeholder="Enter first name..."/>
                    <span><form:errors path="firstName" cssClass="error"/> </span>
                    <form:input type="text" class="last name" path="LastName" name="lastName"  placeholder="Enter last name..."/><br/>
                    <span><form:errors path="lastName" cssClass="error"/></span>
                    <label>Gender</label>
                    <input type="radio" name="gender" value="Male">Male
                    <input type="radio" name="gender" value="Female">Female
                    <br/>
                    <label>Birthday</label><br/>
                    <select class="month" name="month">
                        <option value="0">Month</option>
                        <option value="1">January</option>
                        <option value="2">February</option>
                        <option value="3">March</option>
                        <option value="4">April</option>
                        <option value="5">May</option>
                        <option value="6">June</option>
                        <option value="7">July</option>
                        <option value="8">August</option>
                        <option value="9">September</option>
                        <option value="10">October</option>
                        <option value="11">November</option>
                        <option value="12">December</option>
                    </select>
                    <select class="day" name="date">
                        <option value='0'>Date</option>
                        <%
                            int day;
                            for (day = 1; day <= 31; day++) {
                        %>
                        <option value="<% System.out.print(day); %>"><% System.out.print(day); %></option>
                        <% } %>
                    </select>
                    <select class="year" name="year">
                        <option value='0'>Year</option>
                        <%
                            int year;
                            for (year = 1945; year <= 2015; year++) {
                        %>
                        <option value="<% System.out.print(year); %>"><% System.out.print(year); %></option>
                        <% }%>
                    </select>
                    <br/>
                    <label>Phone</label><br/>
                    <form:input type="text" path="phone" name="regPhone" placeholder="Enter phone number..."/><br/>
                    <span><form:errors path="phone" cssClass="error"/></span>
                    <label>Email</label><br/>
                    <form:input type="text" path="email" name="regEmail" placeholder="Enter email address..."/><br/>
                    <span><form:errors path="email" cssClass="error"/></span>
                    <label>Password</label><br/>
                    <form:input type="password" path="password" name="regPassword" placeholder="Enter password..."/><br/>
                    <span><form:errors path="password" cssClass="error"/></span>
                    <label>Confirm</label><br/>
                    <input type="password" placeholder="Confirm password..."/><br/>
                    <input type="file" accept='image/*' name="avata"/><br/>
                    <input type="submit" class="register" name="register" value="Register"/>
                </form:form>
            </div>
        </div>
    </body>
</html>