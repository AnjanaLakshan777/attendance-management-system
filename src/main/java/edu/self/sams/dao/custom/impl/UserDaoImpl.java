package edu.self.sams.dao.custom.impl;

import edu.self.sams.dao.CrudUtil;
import edu.self.sams.dao.custom.UserDao;
import edu.self.sams.entity.UserEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean save(UserEntity userEntity) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO user VALUES(?,?,?,?,?,?)",userEntity.getUserId(),userEntity.getPassword(),userEntity.getFullName(),userEntity.getRole(),userEntity.getEmail());
    }

    @Override
    public boolean update(UserEntity userEntity) throws Exception {
        return CrudUtil.executeUpdate("UPDATE user SET password=?, full_name=?, role=?, email=? WHERE user_id=?",userEntity.getPassword(),userEntity.getFullName(),userEntity.getRole(),userEntity.getEmail(),userEntity.getUserId());
    }

    @Override
    public boolean delete(String userId) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM user WHERE user_id=?",userId);
    }

    @Override
    public UserEntity get(String userId) throws Exception {
        ResultSet rst =  CrudUtil.executeQuery("SELECT * FROM user WHERE user_id=?",userId);
        if(rst.next()){
            return new  UserEntity(
                    rst.getString("user_id"),
                    rst.getString("password"),
                    rst.getString("full_name"),
                    rst.getString("role"),
                    rst.getString("email")
            );
        }
        return null;
    }

    @Override
    public ArrayList<UserEntity> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM user");
        ArrayList<UserEntity> userEntities = new ArrayList<>();
        while (rst.next()) {
            userEntities.add(new UserEntity(
                    rst.getString("user_id"),
                    rst.getString("password"),
                    rst.getString("full_name"),
                    rst.getString("role"),
                    rst.getString("email"))
            );
        }
        return userEntities;
    }
}
