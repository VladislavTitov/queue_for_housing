package factories;

import dao.*;

public class DaoFactory {
    private static DaoFactory ourInstance;
    private UserDao userDao;
    private ParametersDao parametersDao;
    private AdminDao adminDao;
    private TokenDao tokenDao;

    public static DaoFactory getInstance() {
        return ourInstance;
    }

    private DaoFactory() {
        userDao = new UserDaoImpl();
        parametersDao = new ParametersDaoImpl();
        adminDao = new AdminDaoImpl();
        tokenDao = new TokenDaoImpl();
    }

    static {
        ourInstance = new DaoFactory();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public AdminDao getAdminDao() {
        return adminDao;
    }

    public ParametersDao getParametersDao() {
        return parametersDao;
    }

    public TokenDao getTokenDao() {
        return tokenDao;
    }
}
