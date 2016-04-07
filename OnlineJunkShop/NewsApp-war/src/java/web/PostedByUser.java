/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.*;
import ejb.EntityBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bunna Kal
 */
@WebServlet(name = "postedByUser", urlPatterns = {"/postedByUser/*"})
public class PostedByUser extends HttpServlet {

    @EJB
    private EntityBeanLocal entityFacade;

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession(true);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Online Auction Junk Shop</title>");
            out.println("<title>Online Auction Junk Shop</title>");
            out.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath()
                    + "/resources/css/stylesheet.css' />");
            out.println("<script src='" + request.getContextPath() + "/resources/js/javascript.js'></script>");
            out.println("<script src='" + request.getContextPath() + "/resources/js/jquery-2.1.3.min.js'></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<nav>");
            out.println("<div class='left'>");
            out.println("<a href='" + request.getContextPath() + "/postedByUser/"
                    + request.getSession(false).getAttribute("userId") + "'><img src="
                    + request.getContextPath() + "/resources/images/avata/"
                    + request.getSession(false).getAttribute("avata") + "/><h4>"
                    + request.getSession(false).getAttribute("name") + "</h4></a>");
            out.println("</div>");
            out.println("<form method='post'>");
            out.println("<div class='right'>");
            out.println("<input type='text' placeholder='Enter search...'/>");
            out.println("<input type='button' value='Search'/>");
            out.println("<input type='button' value='Home' onclick=\"location.href ='" + 
                    request.getContextPath() + "/homePage/view.all'\"/>");
            out.println("<input type='button' value='New Post' onclick=\"location.href ='" + 
                    request.getContextPath() + "/newPost'\"/>");
            out.println("<input type='button' value='Logout' onclick=\"location.href ='" + 
                    request.getContextPath() + "/'\"/>");
            out.println("</div>");
            out.println("</form>");
            out.println("</nav>");
            out.println("<div id='wrapper'>");
            out.println("<section>");
            
            List<PostEntity> posts = entityFacade.getAllPostByUser(this.getUserId());
            for (PostEntity p : posts) {
                out.println("<div id='post'>");
                out.println("<div class='avata'>");
                out.println("<img src=" + request.getContextPath() + "/resources/images/avata/"
                        + p.getUser().getAvata() + "/>'");
                out.println("</div>");
                out.println("<div class='name'>");
                out.println("<a href=" + request.getContextPath() + "/postedByUser/"
                        + p.getUser().getId() + "><h4>" + p.getUser().getFirstName()
                        + "&nbsp;" + p.getUser().getLastName() + "</h4></a>");
                out.println("<h6>" + p.getPostDate() + "</h6>");
                out.println("</div>");
                out.println("<div class='title'>");
                out.println("<h6>Published ID-" + p.getId() + "</h6>");
                out.println("<h4>" + p.getPostTitle() + "</h4>");
                out.println("</div>");
                out.println("<div class='content'>");
                out.println("<p>" + p.getShortDescription() + "</p>");
                out.println("<h6>Price: " + p.getPrice() + "</h6>");
                out.println("<h6>Tel: " + p.getPhone() + " </h6>");
                out.println("<h6>Email: " + p.getEmail() + " </h6>");
                out.println("<input class='readMore' type='button' onclick=\"location.href='"
                        + request.getContextPath() + "/postDetail/" + p.getId() + "'\" value='Read More »'/>");
                if (request.getSession(false).getAttribute("userId") != p.getUser().getId()) {
                    out.println("<input class='buy' id='buy' type='button' onclick='buy(" + p.getId() + ");' value='Buy'/>");
                    out.println("<input class='wishList' id='wish' type='button' onclick='wish(" + p.getId()
                            + ");' value='Add to Wish List'/>");
                }
                out.println("</div>");
                out.println("<div class='picture'>");
                out.println("<img src=" + request.getContextPath() + "/resources/images/photo/" + p.getPostPhoto() + "/>");
                out.println("</div>");
                out.println("<h4 class='comment'>Comments(" + p.getCountComment() + ")</h4>");
                for (CommentEntity c : p.getComments()) {
                    out.println("<div id='comment'>");
                    out.println("<div class='commentByAvata'>");
                    out.println("<img  src=" + request.getContextPath() + "/resources/images/avata/"
                            + c.getUser().getAvata() + "/>");
                    out.println("</div>");
                    out.println("<div class='commentByName'>");
                    out.println("<a href=" + request.getContextPath() + "/postedByUser/"
                            + c.getUser().getId() + "><h4>" + c.getUser().getFirstName()
                            + "&nbsp;" + c.getUser().getLastName() + "</h4></a>");
                    out.println("<h6>" + c.getCommentDate() + "</h6>");
                    out.println("</div>");
                    out.println("<div class='commontText'>");
                    out.println("<p>" + c.getComment() + "</p>");
                    out.println("</div>");
                    out.println("</div>");
                }
                out.println("<div class='addCommentAction'>");
                out.println("<div class='addCommentText'>");
                out.println("<img  class='avata' src=" + request.getContextPath() + "/resources/images/avata/"
                        + request.getSession(false).getAttribute("avata") + "/>");
                out.println("<input type='text' id=\"commentText" + p.getId()
                        + "\" placeholder='Enter your comment...'/>");
                out.println("</div>");
                out.println("<div class='addComment'>");
                out.println("<input type='button' onclick='addComment(" + p.getId() + ");' value='Add Comment'/>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
            }
            out.println("</section>");
            out.println("<aside>");
            out.println("<h3> Recent Posts</h3>");
            out.println("<ul>");
            List<PostEntity> recentPosts = entityFacade.getAllRecentPostByUser(this.getUserId());
            for (PostEntity rp : recentPosts) {
                out.println("<li class='listItem'><a href='" + request.getContextPath()
                        + "/postDetail/" + rp.getId() + "'>" + rp.getPostTitle() + "</a></li>");
            }
            out.println("</ul>");
            out.println("</aside>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] pathInfo = request.getPathInfo().split("/");
        String id = pathInfo[1]; // {id}
        if (!id.isEmpty()) {
            this.setUserId(Long.parseLong(id));
        }
        processRequest(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
