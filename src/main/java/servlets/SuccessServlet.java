package servlets;

import dao.ParametersDao;
import dao.UserDao;
import entities.User;
import factories.DaoFactory;
import factories.ServiceFactory;
import services.ParametersService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class SuccessServlet extends HttpServlet{

    ParametersDao parametersDao;
    UserService userService;

    @Override
    public void init() throws ServletException {
        parametersDao = DaoFactory.getInstance().getParametersDao();
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().setAttribute("grant", userService.isGranted((String)req.getSession().getAttribute("current_user")));
        req.getSession().setAttribute("father", parametersDao.findFather((String)req.getSession().getAttribute("current_user")));
        req.getSession().setAttribute("mother", parametersDao.findMother((String)req.getSession().getAttribute("current_user")));
        req.getSession().setAttribute("children", parametersDao.findChildren((String)req.getSession().getAttribute("current_user")));
        req.getSession().setAttribute("wish", parametersDao.findWish((String)req.getSession().getAttribute("current_user")));
        req.getSession().setAttribute("housing", parametersDao.findHousing((String)req.getSession().getAttribute("current_user")));
        req.getSession().setAttribute("promotions", parametersDao.findPromotions((String)req.getSession().getAttribute("current_user")));

        req.getRequestDispatcher("/success.jsp").forward(req, resp);
    }
}
