package services;

import entities.FullEntity;

/**
 * Created by Vladislav on 19.12.2016.
 */
public class AdminServiceImpl implements AdminService {
    @Override
    public FullEntity getNormalQueue() {
        return null;
    }

    @Override
    public FullEntity getDeletedQueue() {
        return null;
    }

    @Override
    public void deleteFromNormalQueue(String userName) {

    }

    @Override
    public void deleteFromDeletedQueue(String userName) {

    }
}
