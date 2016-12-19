package services;

import dao.ParametersDao;
import entities.*;
import factories.DaoFactory;

import java.util.List;

public class ParametersServiceImpl implements ParametersService {

    ParametersDao parametersDao;

    public ParametersServiceImpl() {
        parametersDao = DaoFactory.getInstance().getParametersDao();
    }

    @Override
    public boolean checkRecorded(String userName, EntitiesEnum mode) {
        if (mode == EntitiesEnum.FATHER){
            try {
                Father father = parametersDao.findFather(userName);
                return father.getSurname() != null;
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }else if (mode == EntitiesEnum.MOTHER){
            try {
                return parametersDao.findMother(userName).getSurname() != null;
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }else if (mode == EntitiesEnum.CHILDREN){
            try {
                return !parametersDao.findChildren(userName).isEmpty();
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }else if (mode == EntitiesEnum.WISHES){
            try {
                return parametersDao.findWish(userName).getDistrict() != null;
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }else if (mode == EntitiesEnum.HOUSING){
            try {
                return parametersDao.findHousing(userName).getApplicationDate() != null;
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }else if (mode == EntitiesEnum.PROMOTIONS){
            try {
                return !parametersDao.findPromotions(userName).isEmpty();
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }else {
            return false;
        }
    }

    @Override
    public boolean checkRecordedChild(String userName, Child child) {
        List<Child> children = parametersDao.findChildren(userName);
        for (Child child1 : children) {
            if (child.getSurname().equals(child1.getSurname())
                    & child.getName().equals(child1.getName())
                    & child.getPatronymic().equals(child1.getPatronymic())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void save(String userName, Entity model) {
        if (model instanceof Father){
            try {
                parametersDao.saveFather(userName, (Father) model);
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }else if (model instanceof Mother){
            try {
                parametersDao.saveMother(userName, (Mother) model);
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }else if (model instanceof Child){
            try {
                parametersDao.saveChild(userName, (Child) model);
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }else if (model instanceof Wish){
            try {
                parametersDao.saveWishes(userName, (Wish) model);
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }else if (model instanceof Housing){
            try {
                parametersDao.saveHousing(userName, (Housing) model);
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }else if (model instanceof Promotions){
            try {
                parametersDao.savePromotions(userName, (Promotions) model);
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }
    }

    @Override
    public void saveChildren(String userName, List<Child> children) {
        try {
            for (Child child : children) {
                parametersDao.saveChild(userName, child);
            }
        }catch (IllegalArgumentException e){
            for (Child child : children) {
                parametersDao.deleteChild(userName, child);
            }
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public void update(String userName, Entity model) {
        if (model instanceof Father){
            try {
                parametersDao.updateFather(userName, (Father) model);
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }else if (model instanceof Mother){
            try {
                parametersDao.updateMother(userName, (Mother) model);
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }else if (model instanceof Child){
            try {
                parametersDao.updateChild(userName, (Child) model);
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }else if (model instanceof Wish){
            try {
                parametersDao.updateWishes(userName, (Wish) model);
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }else if (model instanceof Housing){
            try {
                parametersDao.updateHousing(userName, (Housing) model);
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }else if (model instanceof Promotions){
            try {
                parametersDao.updatePromotions(userName, (Promotions) model);
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }
    }

    @Override
    public void updateChildren(String userName, List<Child> children) {
        try {
            for (Child child : children) {
                parametersDao.updateChild(userName, child);
            }
        }catch (IllegalArgumentException e){
            for (Child child : children) {
                parametersDao.deleteChild(userName, child);
            }
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public void delete(String userName, EntitiesEnum mode) {
        if (mode == EntitiesEnum.FATHER){
            parametersDao.deleteFather(userName);
        }else if (mode == EntitiesEnum.MOTHER){
            parametersDao.deleteMother(userName);
        }else if (mode == EntitiesEnum.CHILDREN){
            parametersDao.deleteChildren(userName);
        }else if (mode == EntitiesEnum.WISHES){
            parametersDao.deleteWishes(userName);
        }else if (mode == EntitiesEnum.HOUSING){
            parametersDao.deleteHousing(userName);
        }else if (mode == EntitiesEnum.PROMOTIONS){
            parametersDao.deletePromotions(userName);
        }
    }

    @Override
    public void deleteChild(String userName, Child child) {
        parametersDao.deleteChild(userName, child);
    }
}
