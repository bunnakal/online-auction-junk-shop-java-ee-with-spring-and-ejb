<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Online Auction Junk Shop</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Auction Junk Shop</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/stylesheet.css" />
        <script src="${pageContext.request.contextPath}/resources/js/javascript.js"></script>
    </head>
    <body>
        <nav>
            <div class="left">
                <a href='${pageContext.request.contextPath}/postedByUser/${userId}'>
                    <img src='${pageContext.request.contextPath}/resources/images/avata/${avata}'/>
                    <h4>${name}</h4>
                </a>
            </div>
            <div class="right">
                <input type="submit" value="Home" onclick="location.href = '${pageContext.request.contextPath}/homePage/view.all'"/>
                <input type="submit" value="Post" onclick="location.href = '${pageContext.request.contextPath}/newPost'"/>
                <input type="submit" value="Logout" onclick="location.href = '${pageContext.request.contextPath}/'"/>
            </div>
        </nav>
        <div id="wrapper">
            <section>
                <div id="post">
                    <div class="avata">
                        <img src="${pageContext.request.contextPath}/resources/images/avata/${post.getAvata()}"/>
                    </div>
                    <div class="name">
                        <a href="${pageContext.request.contextPath}/postedByUser/${post.getUserId()}"><h4>${post.getFirstName()} &nbsp; ${post.getLastName()}</h4></a>
                        <h6>${post.getPostDate()}</h6>
                    </div>
                    <div class="title">
                        <h6>Published ID-${post.getPostId()}</h6>
                        <h4>${post.getPostTitle()}</h4>
                    </div>
                    <div class="content">
                        <p>${post.getFullDescription()}</p>
                        <h6>Price: ${post.getPrice()}</h6>
                        <h6>Tel: ${post.getPhone()}</h6>
                        <h6>Email: ${post.getEmail()}</h6>
                        <c:if test="${userId != post.getUserId()}">
                        <input class="buy" style="margin: 10px 5px 0px 60px; width: 205px;" type="button"  value="Buy"/>
                        <input class="wishList" style="width: 205px;" type="button"  value="Add to Wish List"/>
                        </c:if>
                    </div>
                    <div class="picture">
                        <img src="${pageContext.request.contextPath}/resources/images/photo/${post.getPostPhoto()}"/>
                    </div>
                    <h4 class='comment'>Comments(${nrOfComment})</h4>
                    <c:forEach items="${comments}" var="comment">
                        <div id="comment">
                            <div class="commentByAvata">
                                <img  src="${pageContext.request.contextPath}/resources/images/avata/${comment.getAvata()}"/>
                            </div>
                            <div class="commentByName">
                                <a href="${pageContext.request.contextPath}/postedByUser/${comment.getUserId()}"><h4>${comment.getFirstName()} &nbsp; ${comment.getLastName()}</h4></a>
                                <h6>${comment.getCommentDate()}</h6>
                            </div>
                            <div class="commontText">
                                <p>${comment.getComment()}</p>
                            </div>
                        </div> 
                    </c:forEach>
                    <div class="addCommentAction">
                        <div class="addCommentText">
                            <img  class='avata' src="${pageContext.request.contextPath}/resources/images/avata/${avata}" />
                            <input type="text" id="commentText${post.getPostId()}" placeholder="Enter your comment..."/>
                        </div>
                        <div class="addComment">
                            <input type="button" onclick="addComment(${post.getPostId()});" value="Add Comment"/>
                        </div>    
                    </div>
                </div> 
            </section>
            <aside>
                <h3> Related Posts</h3>
                <ul>
                    <c:forEach items="${relatedPosts}" var="relatedPost">
                    <li class="listItem"><a href="${pageContext.request.contextPath}/postDetail/${relatedPost.getId()}">${relatedPost.getPostTitle()}</a></li>  
                    </c:forEach>
                </ul>
            </aside>
        </div>
    </body>
</html>
