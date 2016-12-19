package dao;

import java.util.List;

public interface TokenDao {

    void saveToken(String userName, String token);

    List<String> getTokensByUserName(String userName);

}
