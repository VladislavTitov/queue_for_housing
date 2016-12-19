package services;

import entities.FullEntity;

public interface AdminService {

    /**
     * возращает пользователей с параметрами, которые еще стоят в очереди
     */
    FullEntity getNormalQueue();

    /**
     * возращает пользователей с параметрами, которые уже не стоят в очереди
     */
    FullEntity getDeletedQueue();


    void deleteFromNormalQueue(String userName);

    void deleteFromDeletedQueue(String userName);

}
