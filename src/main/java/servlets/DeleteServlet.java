package servlets;

import factories.ServiceFactory;
import services.ParametersService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        userService.delete((String)req.getSession().getAttribute("current_user"));
        req.getSession().removeAttribute("current_user");
        Cookie cookie = new Cookie("qfhName", "");
        Cookie cookie1 = new Cookie("qfhToken", "");
        cookie.setMaxAge(0);
        cookie1.setMaxAge(0);
        resp.addCookie(cookie);
        resp.addCookie(cookie1);
        resp.sendRedirect("/signin");
    }
}
