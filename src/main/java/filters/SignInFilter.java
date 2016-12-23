package filters;

import dao.UserDao;
import factories.DaoFactory;
import factories.ServiceFactory;
import services.TokenService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInFilter implements Filter {

    TokenService tokenService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        tokenService = ServiceFactory.getInstance().getTokenService();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (((HttpServletRequest)servletRequest).getSession().getAttribute("current_user") != null){
            servletRequest.getRequestDispatcher("/queue").forward(servletRequest, servletResponse);
        }else {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            String userName = null;
            Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();
            String token = null;
            for (Cookie cookie : cookies) {
                if ("qfhToken".equals(cookie.getName())){
                    token = cookie.getValue();
                }
                if ("qfhUser".equals(cookie.getName())){
                    userName = cookie.getValue();
                }
            }
            if (token != null){
                if (tokenService.isExists(userName, token)) {
                    ((HttpServletRequest) servletRequest).getSession().setAttribute("current_user", userName);
                    ((HttpServletResponse) servletResponse).sendRedirect("/queue");
                }else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
