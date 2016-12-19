package dao;

import entities.FullEntity;

import java.util.List;

/**
 * Created by Vladislav on 19.12.2016.
 */
public class AdminDaoImpl implements AdminDao {
    @Override
    public List<FullEntity> getNormalQueueWithPromotions() {
        return null;
    }

    @Override
    public List<FullEntity> getNormalFirstOfQueue() {
        return null;
    }

    @Override
    public List<FullEntity> getNormalOutOfQueue() {
        return null;
    }

    @Override
    public List<FullEntity> getDeletedQueueWithPromotions() {
        return null;
    }

    @Override
    public List<FullEntity> getDeletedFirstOfQueue() {
        return null;
    }

    @Override
    public List<FullEntity> getDeletedOutOfQueue() {
        return null;
    }

    @Override
    public void deleteFromNormalQueue(String userName) {

    }

    @Override
    public void deleteFromDeletedQueue(String userName) {

    }
}
