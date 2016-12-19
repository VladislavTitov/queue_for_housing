package services;

import dao.TokenDao;
import factories.DaoFactory;
import utils.Token;

import java.util.List;

public class TokenServiceImpl implements TokenService {

    TokenDao tokenDao;

    public TokenServiceImpl() {
        tokenDao = DaoFactory.getInstance().getTokenDao();
    }

    @Override
    public String generateToken(String userName) {
        String newToken = Token.generateToken();
        tokenDao.saveToken(userName, newToken);
        return newToken;
    }

    @Override
    public boolean isExists(String userName, String token) {
        List<String> tokens = tokenDao.getTokensByUserName(userName);

        for (String token1 : tokens) {
            if (token.equals(token1)){
                return true;
            }
        }
        return false;
    }
}
