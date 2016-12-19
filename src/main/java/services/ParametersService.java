package services;

import entities.*;

import java.util.List;

public interface ParametersService {

    /**
     * проверяет существование записи заданной модели для определенного пользователя
     * в случае детей проверят существует по крайней мере один ребенок в таблице Children
     * @param userName имя пользователя
     * @param mode зназвание модели (из enum)
     * @return существует или нет
     */
    boolean checkRecorded(String userName, EntitiesEnum mode);

    /**
     * проверяет сущуствование заданного ребенка для определенного пользователя
     * @param userName имя пользователя
     * @param child модель ребенка
     * @return существует или нет
     */
    boolean checkRecordedChild(String userName, Child child);

    /**
     * сохраняет модель для заданного пользователя
     * @param userName имя пользователя
     * @param model реализация интерфейса Model
     */
    void save(String userName, Entity model);

    void saveChildren(String userName, List<Child> children);

    /**
     * обновляет данные заданной модели для заданного пользователя
     * @param userName имя пользователя
     * @param model реализация интерфейса Model
     */
    void update(String userName, Entity model);

    void updateChildren(String userName, List<Child> children);

    /**
     * удаляет заданную модель для определенного пользователя
     * в случае детей удаляет всех сразу
     * @param userName имя пользователя
     * @param mode название модели (из enum)
     */
    void delete(String userName, EntitiesEnum mode);

    void deleteChild(String userName, Child child);

}
