package servlets;

import factories.ServiceFactory;
import entities.User;
import services.TokenService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class SignUpServlet extends HttpServlet {

    TokenService tokenService;
    UserService userService;

    @Override
    public void init() throws ServletException {
        this.tokenService = ServiceFactory.getInstance().getTokenService();
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/signup.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user_name = req.getParameter("email");
        String password = req.getParameter("pass");
        String passConf = req.getParameter("pass-conf");
        boolean remember = req.getParameter("remember") != null;

        if (user_name.equals("") || password.equals("") || passConf.equals("") || !password.equals(passConf) || userService.isRegistered(user_name)){
            resp.sendRedirect("/signup");
        }else{
            userService.save(new User.Builder().setUserName(user_name).setPassword(password).setDateRegistration(Date.valueOf(LocalDate.now())).build());
            Cookie cookie = new Cookie("qfhUser", user_name);
            Cookie cookie1 = new Cookie("qfhToken", tokenService.generateToken(user_name));
            if (remember){
                cookie.setMaxAge(180);
                cookie1.setMaxAge(180);
                resp.addCookie(cookie);
                resp.addCookie(cookie1);
            }
            req.getSession().setAttribute("current_user", user_name);
            req.getSession().setAttribute("create", true);
            resp.sendRedirect("/queue");
        }

    }
}
