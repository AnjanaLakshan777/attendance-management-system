package edu.self.sams.service.custom.impl;

import edu.self.sams.dao.DaoFactory;
import edu.self.sams.dao.custom.UserDao;
import edu.self.sams.entity.UserEntity;
import edu.self.sams.service.custom.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = (UserDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.USER);

    @Override
    public boolean userLogin(String userId, String password, String role) throws Exception {
        UserEntity userEntity = userDao.get(userId);
        if(userEntity != null){
            if(userEntity.getPassword().equals(password) && userEntity.getRole().equals(role)){
                return true;
            }
        }
        return false;
    }
}
