package edu.self.sams.service.custom.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
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
            boolean passwordMatch = BCrypt.verifyer().verify(password.toCharArray(), userEntity.getPassword()).verified;
            if(passwordMatch && userEntity.getRole().equalsIgnoreCase(role)){
                return true;
            }
        }
        return false;
    }
}
