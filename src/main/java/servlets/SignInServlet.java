package servlets;

import factories.ServiceFactory;
import services.TokenService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

    TokenService tokenService;
    UserService userService;

    @Override
    public void init() throws ServletException {
        tokenService = ServiceFactory.getInstance().getTokenService();
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/signin.jsp").forward(req, resp);
        req.getSession().setMaxInactiveInterval(120);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_name = req.getParameter("email");
        String password = req.getParameter("pass");
        boolean remember = req.getParameter("remember") != null;

        if (user_name.equals("admin@mail.ru") && password.equals("321")){
            req.getSession().setAttribute("admin", "admin");
            resp.sendRedirect("/admin");
        }else if (user_name.equals("") || password.equals("") || !userService.isRegistered(user_name) || !userService.checkPassword(user_name, password)){
            resp.sendRedirect("/signin");
        }else{

            if (remember){
                Cookie cookie = new Cookie("qfhUser", user_name);
                Cookie cookie1 = new Cookie("qfhToken", tokenService.generateToken(user_name));
                cookie.setMaxAge(180);
                cookie1.setMaxAge(180);
                resp.addCookie(cookie);
                resp.addCookie(cookie1);
            }
            req.getSession().setAttribute("current_user", req.getParameter("email"));
            resp.sendRedirect("/queue");
        }

    }
}
