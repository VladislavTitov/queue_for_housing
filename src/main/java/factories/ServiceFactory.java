package factories;

import services.*;

public class ServiceFactory {
    private static ServiceFactory ourInstance;

    private UserService userService;
    private ParametersService parametersService;
    private AdminService adminService;
    private TokenService tokenService;

    public static ServiceFactory getInstance() {
        return ourInstance;
    }

    private ServiceFactory() {
        userService = new UserServiceImpl();
        parametersService = new ParametersServiceImpl();
        adminService = new AdminServiceImpl();
        tokenService = new TokenServiceImpl();
    }

    static {
        ourInstance = new ServiceFactory();
    }

    public UserService getUserService() {
        return userService;
    }

    public ParametersService getParametersService() {
        return parametersService;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public TokenService getTokenService() {
        return tokenService;
    }
}
