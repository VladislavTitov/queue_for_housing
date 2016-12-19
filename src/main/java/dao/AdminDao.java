package dao;

import entities.FullEntity;

import java.util.List;

public interface AdminDao {

    List<FullEntity> getNormalQueueWithPromotions();

    List<FullEntity> getNormalFirstOfQueue();

    List<FullEntity> getNormalOutOfQueue();


    List<FullEntity> getDeletedQueueWithPromotions();

    List<FullEntity> getDeletedFirstOfQueue();

    List<FullEntity> getDeletedOutOfQueue();

    void deleteFromNormalQueue(String userName);

    void deleteFromDeletedQueue(String userName);

}
