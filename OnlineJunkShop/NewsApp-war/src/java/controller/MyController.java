/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.CommentInfo;
import model.User;
import dao.*;
import ejb.*;
import form.FileUploadForm;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Bunna Kal
 */
@Controller
public class MyController {

    private UserJdbcDaoSupportDAO userService;
    private PostJdbcDaoSupportDAO postService;

    /**
     * This annotation use for mapping Queue Connection and Message Queue that
     * use to send Message object to Message Driven Bean
     */
    @Resource(mappedName = "jms/NewMessageFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/NewMessage")
    private Queue queue;

    @Autowired
    ApplicationContext ctx;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap mav, User user) {
        user = new User();
        mav.addAttribute("user", user);
        return "welcome";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView index(ModelAndView mav,
            @Valid User user,
            BindingResult result,
            HttpServletRequest request,
            @RequestParam(required = false, value = "register") String register,
            @RequestParam("avata") MultipartFile file
            //@ModelAttribute("user") FileUploadForm uploadForm
    ) {

        if (register != null) {
            if (result.hasErrors()) {
                System.out.println("Error Occur....");
                mav.setViewName("welcome");
                return mav;
            } else {
                System.out.println("No Error Occur....");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String gender = request.getParameter("gender");
                String month = request.getParameter("month");
                String date = request.getParameter("date");
                String year = request.getParameter("year");
                Date birthdate = new Date(Integer.parseInt(month), Integer.parseInt(year), Integer.parseInt(date));
                String phone = request.getParameter("regPhone");
                String email = request.getParameter("regEmail");
                String password = request.getParameter("regPassword");
                String av = request.getParameter("avata");
                
                 if (!file.isEmpty()) {
                 try {
                    byte[] bytes = file.getBytes();
                    try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(av)))) {
                    stream.write(bytes);
                 }
                 } catch (Exception e) {
                    System.out.println(e.getMessage());
                 }
                 }
               
                /*
                 MultipartFile f = file;
                 if (null != file) {
                 String fileName = f.getOriginalFilename();
                 System.out.println("File Name:" + fileName);
                 //Handle file content - multipartFile.getInputStream()
                 }
                 */
                try {
                    try (Connection connection = connectionFactory.createConnection()) {
                        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                        try (MessageProducer messageProducer = session.createProducer(queue)) {
                            ObjectMessage message = session.createObjectMessage();
                            // here we create UserEntity, that will be sent in JMS message
                            UserEntity u = new UserEntity();
                            u.setFirstName(firstName);
                            u.setLastName(lastName);
                            u.setGender(gender);
                            u.setBirthdate(birthdate);
                            u.setPhone(phone);
                            u.setEmail(email);
                            u.setPassword(password);
                            u.setAvata(av);
                            u.setPrivilege("user");
                            message.setObject(u);
                            messageProducer.send(message);
                        }
                    }

                } catch (JMSException ex) {
                    ex.printStackTrace();
                    System.out.println("Error Occur...!");
                }

                mav.setViewName("redirect:homePage/view.all");
                return mav;
            }
        }
        System.out.println("No Error Occur....");
        mav.setViewName("welcome");
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView index(
            ModelAndView mav,
            @RequestParam(required = false, value = "login") String login,
            HttpServletRequest request) {

        /**
         * remove all session when the first start up.
         */
        HttpSession ses = request.getSession();
        ses.removeAttribute("name");
        ses.removeAttribute("userId");
        ses.removeAttribute("avata");

        userService = (UserJdbcDaoSupportDAO) ctx.getBean("userDAO");
        /**
         * check to see weather the login button is clicked
         */
        if (login != null) {
            String logedStatus = null;
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            if (userService.isAbleToLogin(email, password)) {
                UserEntity u = userService.getUserInfo(email, password);
                this.setUserId(u.getId());
                request.getSession().setAttribute("userId", this.getUserId());
                this.setFirstName(u.getFirstName());
                request.getSession().setAttribute("name", this.getFirstName());
                this.setAvata(u.getAvata());
                request.getSession().setAttribute("avata", this.getAvata());
                mav.setViewName("redirect:homePage/view.all");
                return mav;
            } else {
                //System.out.println("Loged false");
                logedStatus = "Incorrect email or password";
                mav.addObject("logedStatus", logedStatus);
            }
        }
        mav.setViewName("welcome");
        return mav;
    }

    /**
     * This method use for route to home page. When every we redirect to home
     * page the definition of this method will be made a query to get all the
     * recent post id, post title on the right hand side. And for the left hand
     * side will list all the past posted which sorted by post date DESC.
     *
     * @param mav
     * @return
     */
    @RequestMapping(value = "/home", method = {RequestMethod.POST,
        RequestMethod.GET})
    public ModelAndView home(ModelAndView mav) {

        postService = (PostJdbcDaoSupportDAO) ctx.getBean("postDAO");
        mav.addObject("name", this.getFirstName());
        mav.addObject("categoryList", postService.getAllCategory());
        mav.addObject("recentPosts", postService.getAllRecentPostTitle());
        mav.addObject("allPosts", postService.getAllPostInfo());
        mav.addObject("countViewer", this.countViewer());

        List<PostEntity> idList = postService.getAllPostId();
        String asList = "";
        for (int i = 0; i < idList.size() - 1; i++) {
            if (i == idList.size() - 2) {
                asList += idList.get(i).getId();
                break;
            }
            asList += idList.get(i).getId() + ",";
        }
        System.out.println("idList: " + asList);
        List<CommentInfo> comments = postService.getCommentByPosts(asList);
        mav.addObject("comments", comments);

        mav.setViewName("home");
        return mav;
    }

    /**
     * This mapping use to display all posted in the home page when one of
     * category is clicked
     *
     * @param mav use as ModelAndView to render the view in JSP
     * @param categoryId parameter that passed by GET method when a specific
     * category in the home page is clicked
     * @return the ModelAndView to display all the posted with the category that
     * the user has clicked
     */
    @RequestMapping(value = "/home/{categoryId}", method = {RequestMethod.POST,
        RequestMethod.GET})
    public ModelAndView home(ModelAndView mav, @PathVariable("categoryId") Long categoryId) {

        postService = (PostJdbcDaoSupportDAO) ctx.getBean("postDAO");
        mav.addObject("name", getFirstName());
        mav.addObject("categoryList", postService.getAllCategory());
        mav.addObject("recentPosts", postService.getAllRecentPostTitleInCategory(categoryId));
        mav.addObject("allPosts", postService.getAllPostInfoInCategory(categoryId));

        mav.setViewName("home");
        return mav;
    }

    /**
     * This method use for render the new post view and also display all the
     * posted which has published by a login user. Also display a list of booked
     * and wished products which done by another user.
     *
     * @param mav the ModelAndView parameter that user to render a JSP
     * @param request the request that ask by client and send all the requested
     * value to the server by using request.getParameter();
     * @param post this parameter get the post button click, every time when the
     * post button invoked or clicked by the user the will be a value in this
     * parameter.
     * @return ModelAndView to render the new post page
     */
    @RequestMapping(value = "/newPost", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView post(ModelAndView mav,
            @RequestParam(required = false, value = "post") String post,
            HttpServletRequest request
    ) {

        postService = (PostJdbcDaoSupportDAO) ctx.getBean("postDAO");
        mav.addObject("name", this.getFirstName());
        mav.addObject("userId", this.getUserId());
        mav.addObject("avata", this.getAvata());
        mav.addObject("categories", postService.getAllCategory());
        mav.addObject("newPosts", postService.getAllPostByUser(getUserId()));

        List<PostEntity> idList = postService.getAllPostId();
        String asList = "";
        for (int i = 0; i < idList.size() - 1; i++) {
            if (i == idList.size() - 2) {
                asList += idList.get(i).getId();
                break;
            }
            asList += idList.get(i).getId() + ",";
        }

        mav.addObject("bookedPosts", postService.getAllBookedPost(asList));
        mav.addObject("wishedPosts", postService.getAllWishedPost(asList));

        if (post != null) {
            String title = request.getParameter("title");
            Date postDate = new Date();
            String shortDescription = request.getParameter("shortDescription");
            String fullDescription = request.getParameter("fullDescription");
            String photoName = request.getParameter("file");
            String price = request.getParameter("price");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String categoryId = request.getParameter("category");
            Long userId = getUserId();//get from session
            try {
                try (Connection connection = connectionFactory.createConnection()) {
                    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                    try (MessageProducer messageProducer = session.createProducer(queue)) {
                        ObjectMessage message = session.createObjectMessage();
                        // here we create PostEntity, that will be sent in JMS message
                        PostEntity p = new PostEntity();
                        p.setPostTitle(title);
                        p.setPostDate(postDate);
                        p.setShortDescription(shortDescription);
                        p.setFullDescription(fullDescription);
                        p.setPostPhoto(photoName);
                        p.setStatus("new");
                        p.setPrice(Double.parseDouble(price));
                        p.setPhone(phone);
                        p.setEmail(email);
                        p.setInCategory(Long.parseLong(categoryId));
                        p.setPostBy(userId);
                        message.setObject(p);
                        messageProducer.send(message);
                    }
                    connection.close();

                    //call upload file to the server
                    //System.out.println(fileUpload(file, photo));
                }
            } catch (JMSException ex) {
                System.err.println(ex.toString());
            }
            mav.setViewName("redirect:home");
        }

        mav.setViewName("newPost");
        return mav;
    }

    /**
     * This mapping responsible for view the user contact information.
     * UserEntity can easily send an email to the user.
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/userContact/{id}", method = {RequestMethod.POST,
        RequestMethod.GET})
    public ModelAndView userContact(@PathVariable("id") int id) {

        userService = (UserJdbcDaoSupportDAO) ctx.getBean("userDAO");
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", getFirstName());
        mav.addObject("user", userService.findByUserId(id));
        mav.setViewName("userContact");
        return mav;
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/postDetail/{id}", method = {RequestMethod.POST,
        RequestMethod.GET})
    public ModelAndView postDetail(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView();
        postService = (PostJdbcDaoSupportDAO) ctx.getBean("postDAO");
        mav.addObject("name", this.getFirstName());
        mav.addObject("post", postService.findByPostId(id));
        mav.addObject("nrOfComment", postService.countComment(id));
        mav.addObject("comments", postService.getALLCommentInPost(id));
        Long categoryId = postService.getCategoryByPost(id);
        mav.addObject("relatedPosts", postService.getAllRelatedPost(categoryId));

        mav.setViewName("postDetail");
        return mav;
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/postByUser/{id}", method = {RequestMethod.POST,
        RequestMethod.GET})
    public ModelAndView postByUser(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView();
        postService = (PostJdbcDaoSupportDAO) ctx.getBean("postDAO");
        mav.addObject("name", getFirstName());
        mav.addObject("allPostsBy", postService.findAllPostByUser(id));
        mav.addObject("recentPosts", postService.getAllRecentPostTitle(id));
        mav.setViewName("postByUser");
        return mav;
    }

    /**
     *
     * @param postId
     * @return
     */
    @RequestMapping(value = "/buy/{postId}", method = RequestMethod.GET)
    public void buy(@PathVariable("postId") Long postId) {

        postService = (PostJdbcDaoSupportDAO) ctx.getBean("postDAO");
        postService.updatePostToBooked(postId);
        ActionEntity action = new ActionEntity();
        action.setActionDate(new Date());
        action.setActionStatus("Booked");
        action.setActionBy(this.getUserId());
        action.setActionOn(postId);
        postService.insertAction(action);

    }

    /**
     *
     * @param postId
     */
    @RequestMapping(value = "/wish/{postId}", method = RequestMethod.GET)
    public void wish(@PathVariable("postId") Long postId) {
        postService = (PostJdbcDaoSupportDAO) ctx.getBean("postDAO");
        postService.updatePostToWished(postId);
        ActionEntity action = new ActionEntity();
        action.setActionDate(new Date());
        action.setActionStatus("Wished");
        action.setActionBy(this.getUserId());
        action.setActionOn(postId);
        postService.insertAction(action);

    }

    /**
     *
     * @param postId
     * @param comment
     */
    @RequestMapping(value = "/addComment/postId/{postId}/comment/{comment}",
            method = RequestMethod.GET)
    public void addComment(@PathVariable("postId") Long postId,
            @PathVariable("comment") String comment) {

        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);

            ObjectMessage message = session.createObjectMessage();
            // here we create UserEntity, that will be sent in JMS message
            CommentEntity c = new CommentEntity();
            c.setCommentDate(new Date());
            c.setComment(comment);
            c.setCommentOn(postId);
            c.setCommentBy(this.getUserId());

            message.setObject(c);
            messageProducer.send(message);
            messageProducer.close();
            connection.close();

        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public @ResponseBody
    List<UserEntity> search() {

        userService = (UserJdbcDaoSupportDAO) ctx.getBean("userDAO");

        List<UserEntity> p = null;
        try {
            p = userService.getAllUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;

    }

    @RequestMapping(value = "/showAllUser", method = RequestMethod.GET)
    public String showAllUser(Model mav) {

        RestTemplate restTemplate = new RestTemplate();
        UserEntity[] user = restTemplate.getForObject("http://localhost:8080/NewsApp-war/userInfo", UserEntity[].class);
        String weather = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q=Leuven,be", String.class);

        mav.addAttribute("userList", user);
        mav.addAttribute("weatherLeuven", weather);

        return "userInfo";
    }

    private Long id;
    private String name;
    private String avata;

    public String getAvata() {
        return avata;
    }

    public void setAvata(String avata) {
        this.avata = avata;
    }

    public void setUserId(Long userId) {
        this.id = userId;
    }

    public void setFirstName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return id;
    }

    public String getFirstName() {
        return name;
    }

    public int countViewer() {
        SessionBeanManager counter = (SessionBeanManager) ctx.getBean("mySingleSessionBean");
        return counter.getActiveSessionsCount();
    }
}
