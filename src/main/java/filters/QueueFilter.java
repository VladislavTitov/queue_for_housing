package filters;

import dao.UserDao;
import factories.DaoFactory;
import factories.ServiceFactory;
import services.TokenService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//TODO добавить TokenService

public class QueueFilter implements Filter {

    TokenService tokenService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        tokenService = ServiceFactory.getInstance().getTokenService();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (((HttpServletRequest)servletRequest).getSession().getAttribute("current_user") == null){
            Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();
            String token = null;
            String userName = null;
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
                    filterChain.doFilter(servletRequest, servletResponse);
                }else {
                    servletRequest.getRequestDispatcher("/signin").forward(servletRequest, servletResponse);
                }
            }else {
                servletRequest.getRequestDispatcher("/signin").forward(servletRequest, servletResponse);
            }
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
