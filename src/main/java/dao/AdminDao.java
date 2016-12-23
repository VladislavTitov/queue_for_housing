package dao;

import entities.FullEntity;

import java.util.List;

public interface AdminDao {

    List<FullEntity> getNormalUsualQueue();

    List<FullEntity> getNormalFirstOfQueue();

    List<FullEntity> getNormalOutOfQueue();


    List<FullEntity> getDeletedQueue();


    void deleteFromNormalQueue(String userName);

    void deleteFromDeletedQueue(String userName);

}
