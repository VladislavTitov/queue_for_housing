package dao;


import entities.*;

import java.util.List;

public interface ParametersDao {



    /**
     * этот блок проверяет на существование записи в заданных таблицах для определенного пользователя
     * @param userName имя пользователя
     * @return существует или нет
     */
    Father findFather(String userName);

    Mother findMother(String userName);

    List<Child> findChildren(String userName);

    Wish findWish(String userName);

    Housing findHousing(String userName);

    Promotions findPromotions(String userName);

    /**
     * записывает в таблицу Father модель Father (остальные методы аналогичны)
     * @param userName имя пользователя
     * @param father модель отца
     */
    void saveFather(String userName, Father father);

    void saveMother(String userName, Mother mother);

    void saveChild(String userName, Child child);

    void saveWishes(String userName, Wish wish);

    void saveHousing(String userName, Housing housing);

    void savePromotions(String userName, Promotions promotions);

    /**
     * обновляет данные в таблице Father для определенного пользователя (остальные методы аналогичны)
     * @param userName имя пользователя
     * @param father модель отца
     */
    void updateFather(String userName, Father father);

    void updateMother(String userName, Mother mother);

    void updateChild(String userName, Child child);

    void updateWishes(String userName, Wish wish);

    void updateHousing(String userName, Housing housing);

    void updatePromotions(String userName, Promotions promotions);


    /**
     * методы удаляют записи из таблиц для определенного пользователя
     * @param userName имя пользователя
     */
    void deleteFather(String userName);

    void deleteMother(String userName);

    void deleteChild(String userName, Child child);

    void deleteChildren(String userName);

    void deleteWishes(String userName);

    void deleteHousing(String userName);

    void deletePromotions(String userName);

}
